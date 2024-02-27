//
//  Envelope.cpp
//  OpenGLTest
//
//  Created by Márcio Sarroglia Pinho on 28/08/22.
//  Copyright © 2022 Márcio Sarroglia Pinho. All rights reserved.
//

#include "Envelope.h"


Envelope::Envelope()
{
    
}
Envelope::Envelope(Ponto P1, Ponto P2)
{
    GeraEnvelope(P1,P2);
}
bool Envelope::temColisao(Envelope E)
{
    /*
    cout << "Campo de Visao: ";
    imprime();
    cout << endl;
    cout << "Env 2: ";
    E.imprime();
    cout << endl;
     */
    float distancia;
    distancia = fabs(Meio.x - E.Meio.x);
   // cout << "distancia X: " << distancia << endl;
    if (distancia > (MeiaLargura.x + E.MeiaLargura.x))
        return false;
    distancia = fabs(Meio.y - E.Meio.y);
    //cout << "distancia Y: " << distancia << endl;
    if (distancia > (MeiaLargura.y + E.MeiaLargura.y))
        return false;
    return true;
}
void Envelope::GeraEnvelope(Ponto P1, Ponto P2)
{
    Min = ObtemMinimo(P1, P2);
    Max = ObtemMaximo(P1, P2);

    Meio = (Max+Min) * 0.5;
    MeiaLargura = (Max-Min) * 0.5;
    
    // Min.imprime("Minimo: ", "\n");
    // Max.imprime("Maximo: ", "\n");
    // Meio.imprime("Meio: ", "\n");
    // MeiaLargura.imprime("Meia Largura: ", "\n");
}
void Envelope::AtualizaEnvelope()
{
    Meio = (Max+Min) * 0.5;
    MeiaLargura = (Max-Min) * 0.5;
}

bool Envelope::pontoEstaDentro(Ponto P)
{
    if (P.x < Min.x) return false;
    if (P.x > Max.x) return false;
    if (P.y < Min.y) return false;
    if (P.y > Max.y) return false;
    
    return true;
}
void Envelope::imprime()
{
    Min.imprime("Minimo: ");
    Max.imprime(" Maximo: ");
}
void Envelope::Desenha()
{
    glBegin(GL_LINE_LOOP);
        glVertex2d(Min.x, Min.y);
        glVertex2d(Min.x, Max.y);
        glVertex2d(Max.x, Max.y);
        glVertex2d(Max.x, Min.y);
    glEnd();
}

