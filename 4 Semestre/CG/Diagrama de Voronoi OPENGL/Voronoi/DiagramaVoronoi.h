//
//  DiagramaVoronoi.hpp
//  OpenGLTest
//
//  Created by Márcio Sarroglia Pinho on 23/08/23.
//  Copyright © 2023 Márcio Sarroglia Pinho. All rights reserved.
//

#ifndef DiagramaVoronoi_h
#define DiagramaVoronoi_h

#include <iostream>
#include <fstream>
using namespace std;

#include "Poligono.h"


class Voronoi
{
    Poligono Diagrama[1000];
    unsigned int qtdDePoligonos;
    Ponto Min, Max;
    //Ponto que guarda o ultimo ponto
    Ponto ultimoPonto;
    Poligono ultimoPoligono;


public:
    Voronoi();
    Poligono LeUmPoligono();
    void LePoligonos(const char *nome);
    Poligono getPoligono(int i);
    void obtemLimites(Ponto &min, Ponto &max);
    unsigned int getNPoligonos();

    //Novos
    //Funcao que gerencia quem é chamado
    void testaPonto(Ponto &pontoClicado, int &testeAtual);

    //Teste convexo
    bool testeConvexo(Ponto &ponto, Poligono &poligono);
    //Teste concavo se ta no poligono
    bool testeConcavo(Ponto &pontoClicado, Ponto &esq, Poligono poligono);
    //Carrega os vizinhos no init
    void obtemVizinhosDasArestas();


     //Teste contra todos os poligonos
    void testaTodos(Ponto &pontoClicado);

    //Teste que faz apenas contra poligonos os quais baterem o envelope com a reta
    void testaRetaEnvelope(Ponto &pontoClicado);

    //Teste contra quem tem o ponto dentro do envelope
    void testaDentroEnvelope(Ponto &pontoClicado);

    //Teste em qual aresta e o vizinho
    void testaAresta(Ponto &pontoClicado);

    Ponto getUltimoPonto();

};

#endif /* DiagramaVoronoi_h */
