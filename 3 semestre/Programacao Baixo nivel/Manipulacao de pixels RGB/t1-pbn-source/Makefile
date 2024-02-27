CC      = gcc
CFLAGS  = -c -O2 -Wall
LDFLAGS =

all: zoom

zoom: lib_ppm.o zoom.o
	$(CC) $(LDFLAGS) lib_ppm.o zoom.o -o zoom

lib_ppm.o: lib_ppm.c
	$(CC) $(CFLAGS) -c lib_ppm.c

zoom.o: zoom.c
	$(CC) $(CFLAGS) -c zoom.c

clean:
	-rm -f *.o *~ zoom lena_copy.ppm test.ppm
