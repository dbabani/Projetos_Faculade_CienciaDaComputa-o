#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void *print_xs(void *arg)
{
	while (1)
		fputc('x', stderr);
		
	return 0;
}

int main()
{
	pthread_t thread_id;

	pthread_create(&thread_id, NULL, &print_xs, NULL);
	
	while (1)
		fputc('o', stderr);

	return 0;
}