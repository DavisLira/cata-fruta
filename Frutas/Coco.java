package Frutas;

import java.awt.Point;

/**
 * A classe Coco representa uma fruta específica que possui um poder associado
 * É uma subclasse de FrutaComPoder e herda suas propriedades, como nome, posição, e se está bichada
 * Além disso, define o poder de valor 2
 */

public class Coco extends FrutaComPoder {
	
	/**
     * Construtor da classe Coco.
     * Inicializa um objeto Coco com o nome, posição, e estado (bichada ou não)
     * Define o poder do abacate para 1
     * 
     * @param nome O nome da fruta
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
    public Coco(Point posicao, boolean bichada) {
        super(posicao, bichada);
        super.setPoder(1);
    }
    
    public Coco(boolean bichada) {
        super(bichada);
        super.setPoder(1);
    }
    
    public String toString() {
    	return "coco";
    }
    
	public String getImg() {
		return "/sprites/coco.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/coco_mochila.png";
	}	
	
	public String getImgArvore() {
		return "/sprites/coqueiro.jpg";
	}
	
	public String arvore() {
		return "Coqueiro";
	}
}
