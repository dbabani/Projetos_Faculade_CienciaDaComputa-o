#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>
#include <stdbool.h>
#include "nokia5110.h"

#define BOTAO_025       PD0
#define BOTAO_050       PD1
#define BOTAO_100       PD2
#define BOTAO_INICIO    PD3
#define LED_VERDE       PC5
#define LED_AMARELO     PC4
#define LED_VERMELHO    PC3

#define MOEDA_025       0.25
#define MOEDA_050       0.50
#define MOEDA_100       1.00
#define CREDITO_MAXIMO  1.50
#define TEMPO_MAXIMO    10

float credito = 48;
float decimo = 48;
float centesimo = 48;
int tempoRestante = 48;
bool contagemIniciada = false;
bool redON = false;

void lcd_atualizar() {
    nokia_lcd_init();
    nokia_lcd_clear();
    nokia_lcd_write_string("Credito: ", 1);
    nokia_lcd_write_char(credito, 1);
    nokia_lcd_write_char('.', 1);
    nokia_lcd_write_char(decimo, 1);
    nokia_lcd_write_char(centesimo, 1);
    nokia_lcd_write_string("\n\n\n\nTempo: ", 1);
    nokia_lcd_write_char(tempoRestante, 1);
    nokia_lcd_render();
}

void timer_init() {
    TCCR1B |= _BV(WGM12);
    OCR1A = 15624;  // Frequência de interrupção a cada 1 segundo (16MHz / 1024)
    TIMSK1 |= _BV(OCIE1A);
    TCCR1B |= _BV(CS12) | _BV(CS10);  // Prescaler de 1024
}

int main() {
    // Configuração dos pinos
    DDRD &= ~(_BV(BOTAO_025) | _BV(BOTAO_050) | _BV(BOTAO_100) | _BV(BOTAO_INICIO));
    PORTD |= _BV(BOTAO_025) | _BV(BOTAO_050) | _BV(BOTAO_100) | _BV(BOTAO_INICIO);
    DDRC |= _BV(LED_VERDE) | _BV(LED_AMARELO) | _BV(LED_VERMELHO);
    
    lcd_atualizar();
    // Inicialização do Timer1
    timer_init();

    sei();  // Habilita interrupções globais
    
    while (1) {
        if(redON == false){
            PORTC |= _BV(LED_VERDE); // Liga o LED verde
        }
        while((credito >= 49 && decimo >= 53) || credito > 49){
            // Verifica se o botão de início foi pressionado
            if (!(PIND & _BV(BOTAO_INICIO))) {
                tempoRestante = 57;
                contagemIniciada = true;
                PORTC &= ~(_BV(LED_VERDE)); // Desliga o LED verde
                PORTC |= _BV(LED_AMARELO); // Liga o LED amarelo
                lcd_atualizar();
                while (contagemIniciada == true && tempoRestante > 48){
                    // O negocio é muito esperto
                    if(tempoRestante == 48){
                        credito = 48;
                        decimo = 48;
                        centesimo = 48;
                        contagemIniciada = false;
                        PORTC &= ~(_BV(LED_AMARELO)); // Desliga o LED amarelo
                        PORTC |= _BV(LED_VERMELHO); // Liga o LED vermelho
                        redON = true;
                        lcd_atualizar();
                    }
                }
            }
        }
        // Verifica se o botão de R$ 0,25 foi pressionado
        if (!(PIND & _BV(BOTAO_025))) {
            if(redON == true){ // Verifica se o LED vermelho está ligado
                PORTC &= ~(_BV(LED_VERMELHO)); // Desliga o LED vermelho
                redON = false;
            }
            if (contagemIniciada == false) {
                decimo += 2;
                centesimo += 5;
                if(centesimo >= 58){
                    decimo += 1;
                    centesimo = 48;
                }
                if(decimo >= 58){
                    credito += 1;
                    decimo = 48;
                }
                lcd_atualizar();
            }
            while (!(PIND & _BV(BOTAO_025))); // Aguarda o botão ser solto
        }

        // Verifica se o botão de R$ 0,50 foi pressionado
        if (!(PIND & _BV(BOTAO_050))) {
            if(redON == true){ // Verifica se o LED vermelho está ligado
                PORTC &= ~(_BV(LED_VERMELHO)); // Desliga o LED vermelho
                redON = false;
            }
            if (contagemIniciada == false) {
                decimo += 5;
                if (decimo >= 58){
                    decimo = 48;
                    credito += 1;
                }
                lcd_atualizar();
            }
            while (!(PIND & _BV(BOTAO_050))); // Aguarda o botão ser solto
        }

        // Verifica se o botão de R$ 1,00 foi pressionado
        if (!(PIND & _BV(BOTAO_100))) {
            if(redON == true){ // Verifica se o LED vermelho está ligado
                PORTC &= ~(_BV(LED_VERMELHO)); // Desliga o LED vermelho
                redON = false;
            }
            if (contagemIniciada == false) {
                credito += 1;
                lcd_atualizar();
            }
            while (!(PIND & _BV(BOTAO_100))); // Aguarda o botão ser solto
        }

        
    }

}

ISR(TIMER1_COMPA_vect) {
    if (contagemIniciada && tempoRestante > 48) {
        tempoRestante -= 1;
        lcd_atualizar();
        if (tempoRestante == 48) {
            // Fazer qualquer ação necessária quando o tempo acabar
            credito = 48;
            decimo = 48;
            centesimo = 48;
            contagemIniciada = false;
            PORTC &= ~(_BV(LED_AMARELO)); // Desliga o LED amarelo
            PORTC |= _BV(LED_VERMELHO); // Liga o LED vermelho
            redON = true;
            lcd_atualizar();
        }
    }
}
