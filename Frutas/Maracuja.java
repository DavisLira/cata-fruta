package Frutas;

import java.awt.Point;

/**
 * A classe Maracuja representa uma fruta específica que possui um poder associado
 * É uma subclasse de FrutaComPoder e herda suas propriedades, como nome, posição, e se está bichada
 * Além disso, define o poder de valor 4
 */

public class Maracuja extends FrutaComPoder {
	
	/**
     * Construtor da classe Maracuja
     * Inicializa um objeto Maracuja com o nome, posição, e estado (bichada ou não)
     * Define o poder do maracuja para 4
     * 
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
    public Maracuja(Point posicao, boolean bichada) {
        super(posicao, bichada);
        super.setPoder(4);
    }
    
    public Maracuja(boolean bichada) {
        super(bichada);
        super.setPoder(4);
    }
    
    public String toString() {
    	return "maracuja";
    }
    
	public String getImg() {
		return "/sprites/maracuja.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/maracuja_mochila.png";
	}
	
	public String getImgArvore() {
		return "";
	}

	public String arvore() {
		return "";
	}
}
