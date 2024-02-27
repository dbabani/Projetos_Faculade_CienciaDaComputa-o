# Compiler options
CC = avr-gcc
CFLAGS = -Wall -Os -DF_CPU=16000000UL -mmcu=atmega328p

# Object files
OBJS = main.o

# Targets
all: main.hex

main.elf: $(OBJS)
	$(CC) $(CFLAGS) $^ -o $@

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

%.hex: %.elf
	avr-objcopy -O ihex -R .eeprom $< $@

clean:
	rm -f *.o *.elf *.hex

.PHONY: all clean
