package Frutas;

import java.awt.Point;

/**
 * A classe Banana representa uma fruta específica que possui um poder associado
 * É uma subclasse de FrutaComPoder e herda suas propriedades, como nome, posição, e se está bichada
 * Além disso, define o poder de valor 2
 */

public class Banana extends FrutaSemPoder {
	
	/**
     * Construtor da classe Banana
     * Inicializa um objeto Banana com o nome, posição, e estado (bichada ou não)
     * 
     * @param nome O nome da fruta.
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
	public Banana(Point posicao, boolean bichada) {
		super(posicao, bichada);
	}
	
	public Banana(boolean bichada) {
		super(bichada);
	}
	
    public String toString() {
    	return "banana";
    }
	
	public String getImg() {
		return "/sprites/banana.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/banana_mochila.png";
	}	
	
	public String getImgArvore() {
		return "/sprites/bananeira.jpg";
	}
	
	public String arvore() {
		return "Bananeira";
	}
}
