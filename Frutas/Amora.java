package Frutas;

import java.awt.Point;

/**
 * A classe Amora representa uma fruta específica que possui um poder associado
 * É uma subclasse de FrutaComPoder e herda suas propriedades, como nome, posição, e se está bichada
 * Além disso, define o poder de valor 2
 */

public class Amora extends FrutaSemPoder {
	
	/**
     * Construtor da classe Amora.
     * Inicializa um objeto Amora com o nome, posição, e estado (bichada ou não)
     * 
     * @param nome O nome da fruta.
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
	public Amora(Point posicao, boolean bichada) {
		super(posicao, bichada);
	}
	
	public Amora(boolean bichada) {
		super(bichada);
	}
	
    public String toString() {
    	return "amora";
    }
	
	public String getImg() {
		return "/sprites/amora.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/amora_mochila.png";
	}	
	
	public String getImgArvore() {
		return "/sprites/amoreira.jpg";
	}
	
	public String arvore() {
		return "Amoreira";
	}
}
