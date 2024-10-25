package Floresta;

import java.awt.Point;

/**
 * A classe Pedra representa uma pedra na floresta que é um tipo de Local
 * O custo de movimento através de uma pedra é mais alto, definido como 3
 */

public class Pedra extends Local{

	private Point posicao;
	private int tipo;
	
	/**
     * Construtor da classe Pedra
     * Inicializa uma pedra com um custo de movimento definido para 3
     */
	
	public Pedra(Point posicao, int tipo) {
		this.posicao = posicao;
		this.setCusto(3);
		this.tipo = tipo;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return "Pedra";
	}
}
