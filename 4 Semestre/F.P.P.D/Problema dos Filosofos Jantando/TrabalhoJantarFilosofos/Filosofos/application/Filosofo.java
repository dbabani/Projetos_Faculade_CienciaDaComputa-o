package application;

import application.AcaoFilosofo.Acao;

public class Filosofo extends Thread {

    private Acao acao;
    private String nome;
    private int tempoComendo = 0;
    private double contaFome = 0;
    private boolean segurandoGarfo = false;

    public int tempoComFome;

    public Filosofo(Acao s) {
        acao = s;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getTempoComendo() {
        return tempoComendo;
    }

    public void setTempoComendo(int n) {
        tempoComendo = n;
    }

    public void addTempoComendo() {
        tempoComendo++;
    }

    public void setSegurandoGarfo(boolean b) {
        segurandoGarfo = b;
    }

    public boolean getSegurandoGarfo() {
        return segurandoGarfo;
    }

    public boolean pegaGarfos(boolean garfoD, boolean garfoE) {
        synchronized (this) {

            if ((garfoD == true) && (garfoE == true)) {
                tempoComFome++;
                acao = Acao.COMENDO;

                return true;

            } else {
                try {
                    tempoComFome++;
                    sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(" Erro : " + e);
                }
                return false;
            }
        }
    }

    public double getTempoComFome() {
        return tempoComFome;

    }

    public double getContaFome() {
        return contaFome;
    }

    public void setContaFome(double i) {
        contaFome = i;
    }
}