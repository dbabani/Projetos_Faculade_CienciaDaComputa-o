//
//  Envelope.hpp
//  OpenGLTest
//
//  Created by Márcio Sarroglia Pinho on 28/08/22.
//  Copyright © 2022 Márcio Sarroglia Pinho. All rights reserved.
//

#ifndef Envelope_hpp
#define Envelope_hpp

#include <iostream>
using namespace std;


#ifdef WIN32
#include <windows.h>
#include <glut.h>
#endif

#ifdef __APPLE__
#include <GLUT/glut.h>
#endif

#ifdef __linux__
#include <glut.h>
#endif

#include "Ponto.h"

class Envelope
{
    Ponto Meio, MeiaLargura;
public:
    Ponto Min,Max; // envelope
    
    Envelope();
    Envelope(Ponto P1, Ponto P2);
    bool temColisao(Envelope E);
    void GeraEnvelope(Ponto P1, Ponto P2); // 
    void AtualizaEnvelope();
    bool pontoEstaDentro(Ponto P);
    void imprime();
    void Desenha();
    
};

#endif /* Envelope_hpp */
