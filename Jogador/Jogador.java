package Jogador;

import Frutas.Fruta;

public class Jogador {
    private String nome;
    private int[] posicao;
    private int movimentos = 0;
    private int forca = 1;
    private Mochila mochila;

    // CONSTRUTOR
    public Jogador(String nome) {
        this.nome = nome;
        this.posicao = new int[2];
        this.mochila = new Mochila();
    }

    // GETTERS
    public String getNome() {
        return this.nome;
    }

    public int getForca(){
        return this.forca;
    }

    public int getMovimento(){
        return this.movimentos;
    }

    // SETTERS
    public void setMovimento(){
        this.movimentos = this.jogarDados();
    }

    public int jogarDados(){
        return (int)(Math.random() * 6) + 1;
    }

    public boolean verificarMovimentos(){
        if (this.movimentos > 0) {
            return true;
        }
        return false;
    }

    public boolean mover(int[] destino) {
        if (this.movimentos > 0) {
            this.posicao[0] = destino[0];
            this.posicao[1] = destino[1];
            this.movimentos--;
            return true;
        }
        return false;
    }

    public void pegarFruta(Fruta fruta) {
        this.mochila.adicionarFruta(fruta);
    }

    public void comer(Fruta fruta) {
        this.mochila.removerFruta(fruta);
        fruta.comer(this);
    }

}
