package Frutas;

import java.awt.Point;

/**
 * A classe Goiaba representa uma fruta sem poderes especiais
 * Esta classe herda da classe FrutaSemPoder e define uma fruta específica
 * que pode ser consumida por um jogador
 */
public class Goiaba extends FrutaSemPoder {
	/**
     * Construtor da classe Goiaba.
     * Inicializa uma instância de Goiaba com o nome, posição e estado (bichada ou não)
     *
     * @param nome O nome da goiaba
     * @param posicao A posição da goiaba na floresta
     * @param bichada Um booleano que indica se a goiaba está bichada (true) ou não (false)
     */
	public Goiaba(Point posicao, boolean bichada) {
		super(posicao, bichada);
	}
	
	public Goiaba(boolean bichada) {
		super(bichada);
	}
	
    public String toString() {
    	return "goaiaba";
    }
	
	public String getImg() {
		return "/sprites/goiaba.jpg";
	}
	
	public String getImgMochila() {
		return "/sprites/goiaba_mochila.png";
	}
	
	public String getImgArvore() {
		return "/sprites/goiabeira.jpg";
	}
	
	public String arvore() {
		return "Goiabeira";
	}
}
