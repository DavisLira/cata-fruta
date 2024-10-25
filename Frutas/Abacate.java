package Frutas;

import java.awt.Point;

/**
 * A classe Abacate representa uma fruta específica que possui um poder associado
 * É uma subclasse de FrutaComPoder e herda suas propriedades, como nome, posição, e se está bichada
 * Além disso, define o poder de valor 2
 */

public class Abacate extends FrutaComPoder {
	
	/**
     * Construtor da classe Abacate
     * Inicializa um objeto Abacate com o nome, posição, e estado (bichada ou não)
     * Define o poder do abacate para 2
     * 
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
    public Abacate(Point posicao, boolean bichada) {
        super(posicao, bichada);
        super.setPoder(2);
    }
    
    public Abacate(boolean bichada) {
        super(bichada);
        super.setPoder(2);
    }
    
    public String toString() {
    	return "abacate";
    }
    
	public String getImg() {
		return "/sprites/abacate.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/abacate_mochila.png";
	}
	
	public String getImgArvore() {
		return "/sprites/abacateiro.jpg";
	}
}
