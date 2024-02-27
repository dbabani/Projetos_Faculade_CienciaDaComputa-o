#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

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

int main(int argc, char** argv) {
    int i,j;
    int num_vect, size_vect;   // will receive command line arguments
    char sort_algorithm[6];    // "qsort" or "bsort"
    clock_t start, end;
    double cpu_time_used;

    if ( argc < 4 )    // test command line arguments
        {
		printf("Usage: master_slave <number of vectors> <size of vectors> <algorithm qsort/bsort>\n");
		return 1;
	    }

    num_vect  = atoi(argv[1]);    // read command line arguments
    size_vect = atoi(argv[2]);
    strcpy(sort_algorithm,argv[3]);

    int (*bag)[size_vect] = malloc (num_vect * sizeof *bag);     // bag of tasks

    printf("\nBag with %d tasks of size %d.", num_vect, size_vect);
    printf("\nSorting with %s.\n", sort_algorithm);

    for ( i=0 ; i < num_vect ; i++ )    // initialize bag of tasks
	        for ( j=0 ; j < size_vect ; j++ )
		        bag[i][j] = size_vect-j;    // fill vectors with numbers in decremetnal order

    start = clock();

    printf("\nSorting ");
    for ( i=0 ; i < num_vect ; i++ ) {
        if (strcmp(sort_algorithm, "qsort" ) == 0)    // execute operation
            qsort (&bag[i][0], size_vect, sizeof(int), compare);
        else
            bsort (size_vect, &bag[i][0] );
        printf(".");
    }

    end = clock();
    
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;

    printf("\n\nTime: %.4fs\n\n", cpu_time_used);
    return 0;
}