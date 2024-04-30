#define NUM_THREADS 10

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void *printhello(void *threadid)
{
	int tid;

	tid = (int)(long int)threadid;
	printf("Hello World! It's me, thread #%d!\n", tid);

	pthread_exit(0);
}

int main(int argc, char **argv)
{
	pthread_t threads[NUM_THREADS];
	long int rc, t;

	for (t = 0; t < NUM_THREADS; t++) {
		printf("In main: creating thread %ld\n", t);
		
		rc = pthread_create(&threads[t], NULL, printhello, (void *)t);
		if (rc) {
			printf("ERROR code is %ld\n", rc);
			return -1;
		}
	}
	
	pthread_exit(0);
}