#define MAX_THREADS 10

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <malloc.h>

void *printhello(void *name)
{

	while (1) {
		printf("Hello World! It's me, thread %s!, (thread: %x)\n", name, pthread_self());
		sleep(1);
	}
}

void *otherthread(void *arg)
{
	while (1) {
		printf("I am another thread!\n");
		sleep(2);
	}
}

int main(int argc, char **argv){
	pthread_t thread[MAX_THREADS];
	pthread_t tother;
	int err_code, i = 0;
	char *name;

	pthread_create(&tother, NULL, otherthread, 0);

	printf ("Enter thread name at any time to create thread\n");
	while (1){
		name = malloc(80 * sizeof(char));
		scanf("%s", name);
		
		printf("In main: creating thread %d\n", i);
		err_code = pthread_create(&thread[i], NULL, printhello, (void *)name);

		if (err_code) {
			printf("ERROR code is %d\n", err_code);
			return -1;
		} else i++;
		
		if (i >= MAX_THREADS)
			return 0;
		
	}
	
	pthread_exit(NULL);
}