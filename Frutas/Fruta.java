package Frutas;

import Jogador.Jogador;

/**
 * A classe abstrata Fruta representa uma fruta genérica dentro do jogo, com propriedades comuns como
 * nome, posição e estado (bichada ou não).
 * Classes concretas que herdam de Fruta devem implementar o método comer
 */

public abstract class Fruta {
    private String nome;
    private int[] posicao;
    private boolean bichada;

    /**
     * Construtor da classe Fruta
     * Inicializa uma fruta com nome, posição e estado (bichada ou não)
     * 
     * @param nome O nome da fruta
     * @param posicao A posição da fruta na floresta
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
    
    public Fruta(String nome, int[] posicao, boolean bichada) {
        this.setNome(nome);
        this.setPosicao(posicao);
        this.setBichada(bichada);
    }
    
    /**
     * Define o nome da fruta
     * 
     * @param nome O nome da fruta
     */
    
    private void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Retorna o nome da fruta
     * 
     * @return O nome da fruta
     */
    
    public String getNome() {
        return nome;
    }
    
    /**
     * Define a posição da fruta na floresta
     * 
     * @param posicao A posição da fruta representada por um array de inteiros [X, Y]
     */
    
    public void setPosicao(int[] posicao) {
        this.posicao = posicao;
    }
    
    /**
     * Retorna a posição da fruta
     * 
     * @return A posição da fruta como um array de inteiros [X, Y]
     */

    public int[] getPosicao() {
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
}
