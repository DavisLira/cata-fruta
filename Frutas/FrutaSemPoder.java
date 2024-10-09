package Frutas;

import Jogador.Jogador;

/**
 * A classe abstrata FrutaSemPoder representa uma fruta que não possui poderes especiais
 * Esta classe herda da classe Fruta e define o comportamento ao ser consumida
 * por um jogador, delegando a ação de comer para o jogador
 */
public abstract class FrutaSemPoder extends Fruta{
	/**
     * Construtor da classe FrutaSemPoder
     * Inicializa uma fruta sem poder, nome, posição e estado (bichada ou não)
     * 
     * @param nome O nome da fruta
     * @param posicao A posição da fruta na floresta
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
    public FrutaSemPoder(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
    }

    /**
     * Método que define o comportamento ao comer a fruta
     * Este método chama o método de comer do jogador, passando a fruta atual
     * 
     * @param jogador O jogador que irá comer a fruta
     */
    public void comer(Jogador jogador) {
        jogador.comer(this);
    }
}
