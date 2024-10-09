package Floresta;

/**
 * A classe Pedra representa uma pedra na floresta que é um tipo de Local
 * O custo de movimento através de uma pedra é mais alto, definido como 3
 */

public class Pedra extends Local{
	
	/**
     * Construtor da classe Pedra
     * Inicializa uma pedra com um custo de movimento definido para 3
     */
	
	public Pedra() {
		this.setCusto(3);
	}
}
