package Floresta;

import java.util.Random;

import App.Menu;
import Frutas.*;
import Jogador.*;

/**
 * A classe Arvore representa uma árvore que possui uma fruta. A árvore é um local onde o jogador pode pegar frutas.
 * O estado de maturidade da fruta é controlado pela variável `madura`, que indica quando a fruta está pronta para ser 
 * colhida.
 */

public class Arvore extends Local{

    Random random = new Random();
	/**
     * A fruta que cresce nesta árvore.
     */
	
	private Fruta fruta;
	
	 /**
     * Indica o estado de maturidade da fruta. O valor máximo é 5, quando a fruta está madura e pode ser colhida.
     */
	
	private int madura = 5;
	
	 /**
     * Construtor da classe Arvore. Inicializa a árvore com a fruta especificada
     * 
     * @param fruta A fruta que esta árvore terá
     */
	
	public Arvore(Fruta fruta) {
		this.fruta = fruta;
	}
	
	public void criarFruta(Fruta fruta, boolean bichada) {
	    try {
	        // Obtém o tipo da fruta e cria uma nova instância usando o construtor com o parâmetro boolean (bichada)
	        this.fruta = fruta.getClass().getConstructor(boolean.class).newInstance(bichada);
	    } catch (Exception e) {
	        e.printStackTrace(); // Para tratar exceções caso o construtor não seja encontrado ou ocorra outro erro
	    }
	}
	
	/**
     * Permite que um jogador pegue a fruta desta árvore, caso ela esteja madura
     * Se a fruta for colhida, o estado de maturidade é resetado para 0
     * 
     * @param jogador O jogador que está tentando pegar a fruta
     */
	
	public boolean darFruta(Jogador jogador) {
		if (madura >= 5) {

            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
			criarFruta(this.fruta, bichada);
			if (jogador.pegarFruta(this.fruta)) {
				madura = 0;
				return true;
			}
		}
		return false;
	}
	
	public void amadurecer() {
		this.madura++;
	}
	
	public int getMadura() {
		return this.madura;
	}
	
	public String getImg() {
		return this.fruta.getImgArvore();
	}
	
	public String nomeFruta() {
		return this.fruta.toString();
	}
	
	public String toString() {
		return this.fruta.arvore();
	}

	public boolean temFruto() {
		return this.madura >= 5;
	}
	
}
