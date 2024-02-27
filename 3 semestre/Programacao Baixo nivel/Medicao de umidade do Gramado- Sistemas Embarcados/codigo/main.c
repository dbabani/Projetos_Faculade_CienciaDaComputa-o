#include <stdint.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

#define MAX_UMIDITY 5

// Variáveis globais
volatile uint8_t umidity_level = 0;
volatile uint8_t is_irrigating = 0;
volatile int piscar = 0b011111;

// Interrupção do botão de irrigação
ISR(INT0_vect) {
    is_irrigating = !is_irrigating;
}

int main(void) {
    cli();
    DDRB |= (1 << PB0) | (1 << PB1) | (1 << PB2) | (1 << PB3) | (1 << PB4); // PB0 até Pb4 = saida
    DDRD &= ~(1<<PD2);// PD2 como entrada

    // Configuração do registrador de pull-up interno
    PORTD |= (1 << PD2);

    // Configuração do registrador MCUCR para configurar a borda de detecção da interrupção externa INT0
    
    EICRA = (1 << ISC01) | (1 << ISC00);

    // Habilita a interrupção externa INT0
    EIMSK = (1 << INT0);
    sei();

    while (1) {
        // Verifica o nível de umidade e atualiza os LEDs
        // Verifica se o gramado está sendo irrigado
        if (is_irrigating) {//irrigacao
            // Verifica se o nível máximo de umidade foi alcançado durante a irrigação
            if (umidity_level == MAX_UMIDITY) {//se alcancou maximo - seca automatica
                is_irrigating = 0;
                while (umidity_level > 0) {
                    for(int i = 0; i < 10; i++){
                      _delay_ms(500);
                        if(is_irrigating == 1){
                            break;
                        } 

                        if(i == 9){
                            piscar |= (1 << (umidity_level -1));  
                            PORTB &= ~(1 << (umidity_level -1));
                            umidity_level--;
                        }
                    }
                    
                    if(is_irrigating == 1){
                        break;
                    } 
                    
                }
            } else {//se nao alcancou maximo - irrigacao
                // Aumenta o nível de umidade a cada 3 segundos
                for(int i = 0; i<10; i++){
                    _delay_ms(300);
                    PORTB ^= piscar;

                    if(is_irrigating == 0){
                        break;
                    }

                    if(i == 9){
                        umidity_level++;
                        PORTB |= (1 << (umidity_level -1));
                        piscar &= ~(1 << (umidity_level -1));  
                    }
                }
                
                if(PB4 == 1){
                    is_irrigating = !is_irrigating;
                }
            }
        }
        else{// seca
            while(umidity_level > 0){
                for(int i = 0; i< 10; i++){
                    _delay_ms(500);
                    if(is_irrigating == 1){
                        break;
                    }
                    if(i == 9){
                        
                        piscar |= (1 << (umidity_level -1));  
                        PORTB &= ~(1 << (umidity_level -1));
                        umidity_level--;
                    } 
                }
                if(is_irrigating == 1){
                    break;
                }
            }
        }
    }
}
