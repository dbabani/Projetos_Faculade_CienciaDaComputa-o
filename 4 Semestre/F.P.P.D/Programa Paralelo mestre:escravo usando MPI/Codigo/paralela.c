
/*************************************************************
Execution:
    master_slave <number of vectors> <size of vectors> <algorithm qsort/bsort>

Description:
    MPI programm based on the master/slave scheme (slave initiative) to sort a
    bag of tasks composed of num_vect vectors of size size_vect with the standard
    C Quicksort function (qsort) or bubble sort (configurable by comando line
    parameters). Production version with time measurement and the possibility to
    turn progress messages off (version 2)

Functionality:
    - slave initiative
    - bag,vector sizes and sort algorithm as parameters
      (no need to recompile if changed)
    - dynamic memory management (limited by the nodes memory)
    - progress messages (may be turned off with macros)
    - time measurement with MPI time functions
    - suicide message to kill slaves
    - tasks are stored in the bag in the original order
    - load balancing
    - profile messages like sort time and task counter in slave a (may be turned off with macros)

Limitations:

Executions
    - 10.000 vectors of size 1.000 with bsort (in 6.64 seconds with np=4 in MacBookPro)
    - 10.000 vectors of size 10.000 with bsort (in 666 seconds with np=4 in MacBookPro)
    - 10.000 vectors of size 100.000 with qsort (in 15.05 seconds with np=4 in MacBookPro)
    - 1.000 vectors of size 1.000.000 with qsort (in 14.12 seconds with np=4 in MacBookPro)
    - 10.000 vectors of size 1.000.000 with qsort (in 14.12 seconds with np=4 in MacBookPro)
    - 4.000 vectors of size 4.000 with bsort (in 108.63 seconds with np=4 in MacBookPro)


Updated:
    3/2022

Author:
    César A. F. De Rose
*************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "mpi.h"

#define KILLTAG 666
#define WORKTAG 111
#define VERBOSE 1    // 1 for progress messages, 0 for none
#define PROFILE 1    // 1 for profile, 0 for none


int compare (const void * a, const void * b)  // used by qsort()
    {
    return ( *(int*)a - *(int*)b );
    }

void bsort (int n, int * vetor)
    {
    int c = 0, d, troca, trocou = 1;

    while ( (c < (n-1)) & trocou )
        {
        trocou = 0;
        for (d = 0 ; d < n - c - 1; d++)
            if (vetor[d] > vetor[d+1])
                {
                troca      = vetor[d];
                vetor[d]   = vetor[d+1];
                vetor[d+1] = troca;
                trocou = 1;
                }
        c++;
        }
    }

int main(int argc, char** argv)
    {
    int i,j;
    int num_vect, size_vect;   // will receive command line arguments
    char sort_algorithm[6];    // "qsort" or "bsort"
    int my_rank;     	       // process id
    int proc_n;                // number of processes (np at command line)
    double t1,t2;              // timestamps
    int bag_position[128];     // store wich process (index) is processing wich task - max np = 128
    int kill_msg;              // number of sended killl messages to slaves (initialized with np-1 slaves)
    int next_task;             // next task to be processed in bag
    MPI_Status status;         // MPI_RECV/PROBE struct with return status

    if ( argc < 4 )    // test command line arguments
        {
		printf("Usage: master_slave <number of vectors> <size of vectors> <algorithm qsort/bsort>\n");
		return 1;
	    }

    num_vect  = atoi(argv[1]);    // read command line arguments
    size_vect = atoi(argv[2]);
    strcpy(sort_algorithm,argv[3]);

    MPI_Init(&argc , &argv);    // initialize MPI, MPI calls after this point
    MPI_Comm_rank(MPI_COMM_WORLD, &my_rank);    // get process id (rank)
    MPI_Comm_size(MPI_COMM_WORLD, &proc_n);     // get number of active processes

    if ( my_rank == 0 )    // what is my role, master or slave?
        {
        // I am the master!

        int (*bag)[size_vect] = malloc (num_vect * sizeof *bag);     // master bag of tasks

        for ( i=0 ; i < num_vect ; i++ )    // initialize bag of tasks
	        for ( j=0 ; j < size_vect ; j++ )
		        bag[i][j] = size_vect-j;    // fill vectors with numbers in decremetnal order

        printf("\nMaster[%d]: bag with %d tasks of size %d.", my_rank, num_vect, size_vect);
        printf("\nMaster[%d]: sorting with %s.\n", my_rank, sort_algorithm);
        fflush(stdout);

        t1 = MPI_Wtime();    // count time from this point

        // send first round of work to the slaves

        next_task = 0;
        for ( i = 1 ; i < proc_n ; i++ )  // begin with first slave (process 1, since master is 0)
            {
            MPI_Send( &bag[i-1][0], size_vect, MPI_INT, i, WORKTAG, MPI_COMM_WORLD );
            bag_position[i] = i-1; // processes 1 to proc_n-1 got first proc_n-1 tasks
            next_task++;
            }

        // wait for results from slaves

        kill_msg = proc_n-1;            // will send np-1 kill messages before job is done

        while ( kill_msg > 0 )  // continue while kill_msg kill messages are not send
            {
			// receive first sorted vector independently from source and tag

            MPI_Probe( MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status );  // probe first message to read it to the right bag position
            MPI_Recv( &bag[bag_position[status.MPI_SOURCE]][0], size_vect, MPI_INT, status.MPI_SOURCE, status.MPI_TAG, MPI_COMM_WORLD, &status );

            #if VERBOSE
			    printf("\nMaster[%d]: message received from slave %d [%d][%d].", my_rank, status.MPI_SOURCE,
                    bag[bag_position[status.MPI_SOURCE]][0], bag[bag_position[status.MPI_SOURCE]][size_vect-1]);
                fflush(stdout);
            #endif

            if ( next_task < num_vect )
                {
                // still some work to do, send it to the free slave

                MPI_Send( &bag[next_task][0], size_vect, MPI_INT, status.MPI_SOURCE, WORKTAG, MPI_COMM_WORLD );
                #if VERBOSE
			        printf("\nMaster[%d]: sending new work (task %d) to slave %d.", my_rank, next_task, status.MPI_SOURCE);
                    fflush(stdout);
                #endif
                bag_position[status.MPI_SOURCE] = next_task;
                next_task++;
                }
            else
                {
                // no more work to do, send a kill to the free slave

		        MPI_Send( NULL, 0, MPI_INT, status.MPI_SOURCE, KILLTAG, MPI_COMM_WORLD );
                kill_msg--; // decrease number of kill messages to be sent

                #if VERBOSE
			        printf("\nMaster[%d]: sending KILLTAG to slave %d.", my_rank, status.MPI_SOURCE);
                    fflush(stdout);
                #endif
                }

            }

        t2 = MPI_Wtime(); // count time to this point
        printf("\n\n");

		printf("Execution time in seconds: %f\n\n", t2-t1);   // show time difference between timestamps
        fflush(stdout);

        free(bag);
        }
	else
        {
        // I am the slave!

        int task_counter = 0;
        double t1,t2;              // timestamps
        double total_sort_time = 0; // acumulate sort times

        int (*message) = malloc (size_vect * sizeof(int));     // slave message buffer

        while(1)
            {
            MPI_Recv(message, size_vect, MPI_INT, 0, MPI_ANY_TAG, MPI_COMM_WORLD, &status);    // receive task

            if (status.MPI_TAG == KILLTAG)
                {
                #if VERBOSE
                    printf("\nSlave[%d]: kill message revceived from master. Bye bye!\n", my_rank );
                    fflush(stdout);
                #endif
		        break;
                }
            t1 = MPI_Wtime();    // count time from this point

            if (strcmp(sort_algorithm, "qsort" ) == 0)    // execute operation
                qsort (message, size_vect, sizeof(int), compare);
            else
                bsort (size_vect, message);

            t2 = MPI_Wtime(); // count time to this point
            total_sort_time += t2 - t1; // acumulate sort times

            MPI_Send( message, size_vect, MPI_INT, 0, WORKTAG, MPI_COMM_WORLD );    // return task to master
            task_counter++;
            }
        free(message);

        #if PROFILE
            printf("\n Slave[%d] Sorted %d tasks in %f seconds (mean per task %f).\n", my_rank, task_counter, total_sort_time, total_sort_time / task_counter);
            fflush(stdout);
        #endif
        }
	MPI_Finalize();
}