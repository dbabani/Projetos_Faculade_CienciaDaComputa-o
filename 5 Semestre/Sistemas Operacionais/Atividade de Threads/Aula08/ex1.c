#define ITERATIONS 100000

#include <pthread.h>
#include <stdio.h>


struct resource_s {
	int val;
	int (*getval)(struct resource_s *);
	void (*setval)(struct resource_s *, int);
};

int getval(struct resource_s *res)
{
	return res->val;
}

void setval(struct resource_s *res, int nval)
{
	res->val = nval;
}


pthread_mutex_t mtx;
struct resource_s resource;


void *task1(void *arg)
{
	struct resource_s *ptr_resource = &resource;
	int val;
	
	printf("task 1\n");

	pthread_mutex_lock(&mtx);	
	for (int i = 0; i < ITERATIONS; i++) {
		val = ptr_resource->getval(ptr_resource);
		ptr_resource->setval(ptr_resource, ++val);
	}
	pthread_mutex_unlock(&mtx);
}

void *task2(void *arg)
{
	struct resource_s *ptr_resource = &resource;
	int val;
	
	printf("task 2\n");

	pthread_mutex_lock(&mtx);	
	for (int i = 0; i < ITERATIONS; i++) {
		val = ptr_resource->getval(ptr_resource);
		ptr_resource->setval(ptr_resource,++val);
	}
	pthread_mutex_unlock(&mtx);
}

void *task3(void *arg)
{
	struct resource_s *ptr_resource = &resource;
	int val;
	
	printf("task 3\n");

	pthread_mutex_lock(&mtx);	
	for (int i = 0; i < ITERATIONS; i++) {
		val = ptr_resource->getval(ptr_resource);
		ptr_resource->setval(ptr_resource, ++val);
	}
	pthread_mutex_unlock(&mtx);
}

int main(int argc, char *argv[])
{
	pthread_t threads[3];
	struct resource_s *ptr_resource = &resource;
	
	ptr_resource->val = 0;
	ptr_resource->getval = getval;
	ptr_resource->setval = setval;

	pthread_mutex_init(&mtx, NULL);

	pthread_create(&threads[0], NULL, task1, NULL);
	pthread_create(&threads[1], NULL, task2, NULL);
	pthread_create(&threads[2], NULL, task3, NULL);

	pthread_join(threads[0], NULL);
	printf("fim da thread 1\n");
	pthread_join(threads[1], NULL);
	printf("fim da thread 2\n");
	pthread_join(threads[2], NULL);
	printf("fim da thread 3\n");
	
	printf("soma: %d\n", ptr_resource->getval(ptr_resource));
	
	pthread_exit(NULL);
	
	return 0;
}