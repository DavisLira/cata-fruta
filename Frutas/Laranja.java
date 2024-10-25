package Frutas;

import java.awt.Point;

/**
 * A classe Laranja representa uma fruta com poderes especiais
 * Esta classe herda da classe FrutaComPoder e define uma fruta específica
 * que pode conceder habilidades ao jogador quando consumida
 */
public class Laranja extends FrutaComPoder {
	
	 /**
     * Construtor da classe Laranja
     * Inicializa uma instância de Laranja com o nome, posição e estado (bichada ou não)
     *
     * @param nome O nome da laranja
     * @param posicao A posição da laranja na floresta
     * @param bichada Um booleano que indica se a laranja está bichada (true) ou não (false)
     */
    public Laranja(Point posicao, boolean bichada) {
        super(posicao, bichada);
        super.setPoder(3);
    }
    
    public Laranja(boolean bichada) {
        super(bichada);
        super.setPoder(3);
    }
    
    public String toString() {
    	return "laranja";
    }
    
	public String getImg() {
		return "/sprites/laranja.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/laranja_mochila.png";
	}
	
	public String getImgArvore() {
		return "/sprites/laranjeira.jpg";
	}
}
