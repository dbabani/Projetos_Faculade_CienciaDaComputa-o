#include <pthread.h>
#include <stdio.h>

pthread_mutex_t m1, m2, m3;

void *task1(void *arg)
{
	while (1) {
		pthread_mutex_lock(&m1);
		printf("task 1\n");
		pthread_mutex_unlock(&m2);
	}
}

void *task2(void *arg)
{
	while (1) {
		pthread_mutex_lock(&m2);
		printf("task 2\n");
		pthread_mutex_unlock(&m3);
	}
}

void *task3(void *arg)
{
	while (1) {
	    pthread_mutex_lock(&m3);
		printf("task 3\n");
		pthread_mutex_unlock(&m1);
	}
}

int main(int argc, char *argv[])
{
	pthread_t threads[3];
	
	pthread_mutex_init(&m1, NULL);
	pthread_mutex_init(&m2, NULL);
	pthread_mutex_init(&m3, NULL);
	pthread_mutex_lock(&m2);
	pthread_mutex_lock(&m3);

	pthread_create(&threads[0], NULL, task1, NULL);
	pthread_create(&threads[1], NULL, task2, NULL);
	pthread_create(&threads[2], NULL, task3, NULL);
	
	pthread_exit(NULL);
	
	return 0;
}