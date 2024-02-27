//
//  DiagramaVoronoi.cpp
//  OpenGLTest
//
//  Created by Márcio Sarroglia Pinho on 23/08/23.
//  Copyright © 2023 Márcio Sarroglia Pinho. All rights reserved.
//

#include "DiagramaVoronoi.h"

ifstream input;            // ofstream arq;

Voronoi::Voronoi()
{

}
Poligono Voronoi::LeUmPoligono()
{
    Poligono P;
    unsigned int qtdVertices;
    input >> qtdVertices;  // arq << qtdVertices
    for (int i=0; i< qtdVertices; i++)
    {
        double x,y;
        // Le um ponto
        input >> x >> y;
        Ponto(x, y).imprime();
        if(!input)
        {
            cout << "Fim inesperado da linha." << endl;
            break;
        }
        P.insereVertice(Ponto(x,y));
    }
    cout << "Poligono lido com sucesso!" << endl;
    return P;
}

void Voronoi::LePoligonos(const char *nome)
{
    input.open(nome, ios::in); //arq.open(nome, ios::out);
    if (!input)
    {
        cout << "Erro ao abrir " << nome << ". " << endl;
        exit(0);
    }
    string S;

    input >> qtdDePoligonos;
    cout << "qtdDePoligonos:" << qtdDePoligonos << endl;
    Ponto A, B;
    Diagrama[0] = LeUmPoligono();
    Diagrama[0].obtemLimites(Min, Max);// obtem o envelope do poligono


    for (int i=1; i< qtdDePoligonos; i++)
    {
        Diagrama[i] = LeUmPoligono();
        Diagrama[i].obtemLimites(A, B); // obtem o envelope do poligono

        Min = ObtemMinimo (A, Min);
        Max = ObtemMaximo (B, Max);
    }
    cout << "Lista de Poligonos lida com sucesso!" << endl;

}

Poligono Voronoi::getPoligono(int i)
{
    if (i >= qtdDePoligonos)
    {
        cout << "Nro de Poligono Inexistente" << endl;
        return Diagrama[0];
    }
    return Diagrama[i];
}
unsigned int Voronoi::getNPoligonos()
{
    return qtdDePoligonos;
}
void Voronoi::obtemLimites(Ponto &min, Ponto &max)
{
    min = this->Min;
    max = this->Max;
}

//Novos
//FUNCAO PRINCIPAL QUE GERENCIA AS OUTRAS
void Voronoi::testaPonto(Ponto &pontoClicado, int &testeAtual)
{
    //reseta o contador sempre antes de qualquer operacao
    resetContadorInt();

    //ultimoPonto.imprime("\nUltimo Ponto Interno Antes: ");
    //Testa se ta no poligono atual
    bool estaNoAtual = testeConvexo(pontoClicado,Diagrama[pontoClicado.getUltimoPoligono()]);


    //se estiver
    if(estaNoAtual){ //se continua no mesmo poligono
        cout << "\nMESMO POLIGONO" << endl;
        cout << "Esta no poligono de numero: " << pontoClicado.posUltimoPoligono << endl;
        cout << "Numero de ProdVet: " <<getContadorInt()<< endl;
        resetContadorInt(); //reseta o contador e faz o proximo teste

    }
    else{ //caso tenha saído do poligono
        switch (testeAtual) {
        case 1:
            //Testa x todos os poligonos
            testaTodos(pontoClicado);
            break;
        case 2:
            //testa os que tem interseccao da reta com envelope e usa teste concavo
            testaRetaEnvelope(pontoClicado);
            break;
        case 3:
            //testa os que tem o envelope cujo o ponto ta dentro
            testaDentroEnvelope(pontoClicado);
            break;
        case 4:
            //testa a aresta ultrapassada e o vizinho
            testaAresta(pontoClicado);
            break;
        }
    }
    //Guarda a nova posicao do ponto, que sera a anterior na proxima execucao
    ultimoPonto.set(pontoClicado.x,pontoClicado.y,0);
}
//Teste convexo
bool Voronoi::testeConvexo(Ponto &ponto, Poligono &poligono)
{
    // Verificar se o ponto está à esquerda de todas as arestas do polígono
    //For por todos os vertices do poligono
    for (int i = 0; i < poligono.getNVertices(); ++i) {
        //pega os dois pontos que formam a aresta
        Ponto p1, p2;
        poligono.getAresta(i,p1,p2);

        // Vetor da aresta do polígono
        Ponto vetorAresta = Ponto(p2.x - p1.x, p2.y - p1.y);

        // Vetor do ponto ao ponto inicial da aresta
        Ponto vetorPonto = Ponto(ponto.x - p1.x, ponto.y - p1.y);

        // Verifica o produto vetorial
        Ponto prodVet;
        ProdVetorial(vetorAresta,vetorPonto,prodVet);

        // Se o produto vetorial for positivo, o ponto está fora do poligono, logo para a busca
        if (prodVet.z >= 0) {
            return false;
        }
    }
    // se nenhum teste disse que estava fora, é pq esta dentro
    return true;

}
//Testa contra todos os poligonos
void Voronoi::testaTodos(Ponto &pontoClicado){

    cout << "\nTESTE X TODOS" << endl;
    for(int i = 0; i < qtdDePoligonos; i++)
    {
        //testa o poligono
        bool achou;
        achou = testeConvexo(pontoClicado,Diagrama[i]);

        if(achou){
            //se achou atualiza o ultimo e printa
            pontoClicado.setUltimoPoligono(i);
            cout << "Esta no poligono de numero: " << i << endl;
            cout <<"Numero de ProdVetor: " << getContadorInt()<< endl;
            break;
        }
    }
}

//Os tres testes
void Voronoi::testaRetaEnvelope(Ponto &pontoClicado)
{
    cout << "\nTESTE RETA X ENVELOPE:" << endl;
    //passa por todos os poligonos pegando os envelopes
    for(int i = 0; i < qtdDePoligonos; i++)
    {
        Envelope envelopeAtual = Diagrama[i].envelope;

        //Cria um ponto bem para esquerda para testar se tem colisao
        Ponto Esq;
        Ponto Dir (-1,0);
        Esq = pontoClicado + Dir * (1000);
        //testa se o ponto esta entre a as alturas do envelope
        if(pontoClicado.y<envelopeAtual.Max.y | pontoClicado.y>envelopeAtual.Min.y){

        //se tiver, testa se tem colisao com o envelope
            //cria um ponto que faz parte da aresta do envelope
            Ponto pontoArestaEnvelope;
            int novoX,novoY;
            novoX = envelopeAtual.Min.x;
            novoY = envelopeAtual.Max.y;
            pontoArestaEnvelope.set(novoX,novoY,0);

            //testa se a linha cruza a aresta do envelope
            if(HaInterseccao(envelopeAtual.Min,pontoArestaEnvelope,pontoClicado,Esq))
            {
                cout << "Testando com poligono de numero: " << i << endl;
                //testa se esta no poligono
                if(testeConcavo(pontoClicado,Esq,Diagrama[i])){
                    //se esta no poligono
                    //atualiza o ultimo poligono
                    pontoClicado.setUltimoPoligono(i);

                    cout << "Esta no poligono de numero: " << i << endl;
                    cout <<"Numero de HaInterseccao: " << getContadorInt()<< endl;
                    //nao precisa procurar mais
                    break;
                }
            }
        }
    }
}
bool Voronoi::testeConcavo (Ponto &pontoClicado, Ponto &esq, Poligono poligono)
{
    //pontos auxiliares da aresta e contador de interseccoes
    Ponto P1,P2;
    int countIntersec = 0;
    //testa todos os vertices do poligono
    for (int i=0; i < poligono.getNVertices();i++)
        {
            //atribui a p1 e p2 os pontos da aresta
            poligono.getAresta(i, P1, P2);
            //se tem interseccao soma no contador
            if (HaInterseccao(pontoClicado,esq, P1, P2))
                countIntersec++;
        }
    if(countIntersec % 2 == 0){
        return false;
    }
    return true;
}
void Voronoi::testaDentroEnvelope(Ponto &pontoClicado)
{
    cout << "\nTESTE DENTRO ENVELOPE:" << endl;
    //passa por todos os poligonos pegando os envelopes
    for(int i = 0; i < qtdDePoligonos; i++)
    {
        //Pega o envelope
        Envelope envelopeAtual = Diagrama[i].envelope;

        //Testa se o ponto ta dentro
        if(envelopeAtual.pontoEstaDentro(pontoClicado)){
            cout << "Ta dentro do envelope: "<< i<<endl;

            //se tiver, testa se esta dentro do poligono
            if(testeConvexo(pontoClicado,Diagrama[i])){
                cout << "O ponto esta no poligono: "<< i<<endl;
                pontoClicado.setUltimoPoligono(i);
                cout << "Numero de ProdVetorial: " <<getContadorInt()<<endl;
                //Se quiser testar todos os poligonos que ele ta dentro, so comentar o break
                break;
            }
        }
    }

}
void Voronoi::testaAresta(Ponto &pontoClicado)
{
    cout<< "\nTESTE ARESTA:"<< endl;
    cout<<"Ultimo Poligono: "<<pontoClicado.getUltimoPoligono()<<endl;
    if(!testeConvexo(pontoClicado,Diagrama[pontoClicado.getUltimoPoligono()])){


        //percorre a lista de arestas do ultimo poligono
        for(int i = 0; i < Diagrama[pontoClicado.getUltimoPoligono()].getNVertices(); i++){

            //Pega os dois pontos da aresta, os pontos da outra reta ja tem
            Ponto inicioAresta;
            Ponto finalAresta;
            Diagrama[pontoClicado.getUltimoPoligono()].getAresta(i,inicioAresta,finalAresta);

            //cria os vetores
            Ponto vetorAresta,vetorArestaAnterior,vetorArestaPosterior;
            vetorAresta.set(finalAresta.x - inicioAresta.x,finalAresta.y - inicioAresta.y,0);
            vetorArestaAnterior.set(ultimoPonto.x - inicioAresta.x, ultimoPonto.y - inicioAresta.y,0);
            vetorArestaPosterior.set(pontoClicado.x - inicioAresta.x, pontoClicado.y - inicioAresta.y,0);

            //faz o prodVetorial
            Ponto resultadoUm,resultadoDois;
            ProdVetorial(vetorAresta,vetorArestaAnterior,resultadoUm);
            ProdVetorial(vetorAresta,vetorArestaPosterior,resultadoDois);

            //se os dois prod vetoriais sao de sinais diferentes, estao de lados diferentes,
            if((resultadoUm.z*resultadoDois.z)<0){
                //Tem interseccao

                cout<<"Houve interseccao da aresta " << i << endl;
                cout<<"Z: "<<resultadoUm.z<< " e "<<resultadoDois.z<<endl;
                cout<<"Foi para o poligono vizinho: "<< inicioAresta.getVizinho()<<endl;
                cout<<"Numero de ProdVetorial:"<<getContadorInt()<<endl;

                //seta o novo poligono
                pontoClicado.setUltimoPoligono(inicioAresta.getVizinho());
                break;
            }
        }
    }

}

void Voronoi::obtemVizinhosDasArestas()
{
    //Para todos os poligonos
    for (int poligonoAtual=0; poligonoAtual < qtdDePoligonos; poligonoAtual++)
    {
        //para todos os vertices do poligonoAtual
        for(int verticeAtual=0;verticeAtual < Diagrama[poligonoAtual].getNVertices();verticeAtual++)
        {
            //pega as informacoes da aresta deste vertice
            Ponto inicioArestaUm;
            Ponto finalArestaUm;
            Diagrama[poligonoAtual].getAresta(verticeAtual,inicioArestaUm,finalArestaUm);

            //passa pela lista de poligonos a partir do atual
            for(int vizinhoAtual = poligonoAtual+1; vizinhoAtual <qtdDePoligonos;vizinhoAtual++){

                //passa pela lista de arestas/vertices do vizinho
                for(int verticeVizinho = 0; verticeVizinho<Diagrama[vizinhoAtual].getNVertices();verticeVizinho++){

                    //pega informacoes da aresta do vizinho
                    Ponto inicioArestaDois;
                    Ponto finalArestaDois;
                    Diagrama[vizinhoAtual].getAresta(verticeVizinho,inicioArestaDois,finalArestaDois);

                       //Se for a mesma aresta
                       if(inicioArestaUm == finalArestaDois && finalArestaUm == inicioArestaDois){
                            //seta no poligono atual o vizinho para tal vertice e no vizinho tb
                            Diagrama[poligonoAtual].setVizinho(verticeAtual,vizinhoAtual);
                            Diagrama[vizinhoAtual].setVizinho(verticeVizinho,poligonoAtual);


                        //cout << "\nVizinhos: " << verticeAtual.vizinho<<verticeVizinho.vizinho << endl;

                        //cout <<"A aresta "<< verticeAtual <<" do poligono " << poligonoAtual << " eh vizinho de " << vizinhoAtual<< "\n"<<endl;

                        }
                }
            }
        }
    }
    cout << "\nArestas vizinhas encontradas com sucesso!\n" << endl;
}

Ponto Voronoi::getUltimoPonto()
{
    return ultimoPonto;
}
