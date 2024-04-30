// arquivo: prodcons.c
// descricao: Programa produtor-consumidor com mutex
// Utiliza a biblioteca pthreads.
// para compilar:  cc -o phtread pthread.c -lpthread

#include <pthread.h>
#include <stdio.h>
#include <unistd.h>


#define  FALSE 0
#define  TRUE  1


//Buffer
#define  BUFFERVAZIO 0
#define  BUFFERCHEIO 1
int buffer;
int estado = BUFFERVAZIO;
int flag[2];
int turn;

void lock_init()
{
    flag[0] = flag[1] = 0;
    turn = 0;
}

void mutex_lock(int self){
    flag[self] = 1;

    turn = 1 - self;

    while(flag[1-self] == 1 && turn == 1 -self){
        // do nothing
    }
}

void mutex_unlock(int self){
    flag[self] == 0;

}

void produtor(int id)
{
	int i=0;
	int item;
    int aguardar;

	printf("Inicio produtor %d \n",id);
	while (i < 10)
	    {
            //produzir item
	    item = i + (id*1000);

            do
	        {
            mutex_lock(id);
	        aguardar = FALSE;
	        if (estado == BUFFERCHEIO)
		    {
		    aguardar = TRUE;
	            mutex_unlock(id);
		    }
		} while (aguardar == TRUE);

	    //inserir item
            printf("Produtor %d inserindo item %d\n", id, item); 
            buffer = item;
	    estado = BUFFERCHEIO;
	    
	    mutex_unlock(id);
	    i++;
	    sleep(2);
	    }
	printf("Produtor %d terminado \n", id); 
        }

void consumidor(int id)
        {
	int item;
	int aguardar;

	printf("Inicio consumidor %d \n",id);
	while (1)
	    {
            // retirar item da fila
            do
		{
            mutex_lock(id);
	        aguardar = FALSE;
	        if (estado == BUFFERVAZIO)
		    {
	            aguardar = TRUE;
		    mutex_unlock(id);
		    }
	        } while (aguardar == TRUE);
	    item = buffer;
	    estado = BUFFERVAZIO;
	    mutex_unlock(id);

	    // processar item
            printf("Consumidor %d consumiu item %d\n", id, item); 

 	    sleep(2);
	    }
	printf("Consumidor %d terminado \n", id); 
        }

int main()
{ 
	pthread_t prod1;
	pthread_t prod2;
	pthread_t prod3;
	pthread_t cons1;
	pthread_t cons2;

	printf("Programa Produtor-Consumidor\n");

	printf("Iniciando variaveis de sincronizacao.\n");
	lock_init();

    printf("Disparando threads produtores\n");
	pthread_create(&prod1, NULL, (void*) produtor,1);
	pthread_create(&prod2, NULL, (void*) produtor,2);
	pthread_create(&prod3, NULL, (void*) produtor,3);

    printf("Disparando threads consumidores\n");
	pthread_create(&cons1, NULL, (void*) consumidor,1);
	pthread_create(&cons2, NULL, (void*) consumidor,2);

	pthread_join(prod1,NULL);
	pthread_join(prod2,NULL);
	pthread_join(prod3,NULL);
	pthread_join(cons1,NULL);
	pthread_join(cons2,NULL);
	
    printf("Terminado processo Produtor-Consumidor.\n\n");
}