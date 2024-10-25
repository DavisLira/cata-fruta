package Frutas;

import java.awt.Point;

import Jogador.Jogador;

/**
 * A classe abstrata Fruta representa uma fruta genérica dentro do jogo, com propriedades comuns como
 * nome, posição e estado (bichada ou não).
 * Classes concretas que herdam de Fruta devem implementar o método comer
 */

public abstract class Fruta {
	private Point posicao;
    private boolean bichada;

    /**
     * Construtor da classe Fruta
     * Inicializa uma fruta com nome, posição e estado (bichada ou não)
     * 
     * @param posicao A posição da fruta na floresta
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
    
    public Fruta(Point posicao, boolean bichada) {
        this.setPosicao(posicao);
        this.setBichada(bichada);
    }
    
    public Fruta(boolean bichada) {
        this.setBichada(bichada);
    }
    
    /**
     * Define a posição da fruta na floresta
     * 
     * @param posicao A posição da fruta representada por um array de inteiros [X, Y]
     */
    
    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }
    
    /**
     * Retorna a posição da fruta
     * 
     * @return A posição da fruta como um array de inteiros [X, Y]
     */

    public Point getPosicao() {
        return posicao;
    }
    
    /**
     * Define se a fruta está bichada
     * 
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */

    public void setBichada(boolean bichada) {
        this.bichada = bichada;
    }
    
    /**
     * Verifica se a fruta está bichada
     * 
     * @return true se a fruta estiver bichada, false caso contrário
     */

    public boolean isBichada() {
        return bichada;
    }
    
    /**
     * Método abstrato para comer a fruta
     * As subclasses devem fornecer a implementação de como a fruta afeta o jogador
     *
     * @param jogador O jogador que irá comer a fruta
     */

    public abstract void comer(Jogador jogador);
    
    @Override
    public abstract String toString();
    
    public abstract String getImg();
    
	public abstract String getImgMochila();
	
	public abstract String getImgArvore();
	
	public abstract String arvore();
}
