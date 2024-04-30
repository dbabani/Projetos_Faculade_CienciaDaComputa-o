#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>

void *thr_func(void *arg)
{
	int tid;

	tid = (int)(long int)arg;
	
	while(1){
		printf("thread %d, entra na RC\n", tid);
		usleep(((unsigned int)random() % 500) * 1000);
		printf("thread %d, sai na RC\n", tid);
	}
}

int main(void)
{
	pthread_t thread0, thread1;
	int now;

	now = (int)time(NULL);
	srand(now);
	

	pthread_create(&thread0, NULL, thr_func, (void *)0);
	pthread_create(&thread1, NULL, thr_func, (void *)1);

	pthread_exit(NULL);

	return 0;
}