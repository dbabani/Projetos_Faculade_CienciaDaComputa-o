#define NUM_THREADS 10

#include <pthread.h>
#include <stdio.h>

void *printhello(void *threadid)
{
	int tid;

	tid = (int)(long int)threadid;
	printf("Hello World! It's me, thread #%d!\n", tid);
	
	pthread_exit(NULL);
}

int main(int argc, char *argv[])
{
	pthread_t threads[NUM_THREADS];
	long int rc, t;

	for (t = 0; t < NUM_THREADS; t++) {
		printf("In main: creating thread %d\n", t);
		rc = pthread_create(&threads[t], NULL, printhello, (void *)t);
		if (rc) {
			printf("ERROR code is %d\n", rc);
			return -1;
		}
	}
	for (t = 0; t < NUM_THREADS; t++){
		pthread_join(threads[t], NULL);
		printf("Thread %d terminated.\n", t);
	}
	
	printf("All threads terminated.\n");
	pthread_exit(NULL);
}