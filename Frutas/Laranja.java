package Frutas;

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
    public Laranja(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
        super.setPoder(3);
    }
    
	public String getImg() {
		return "/sprites/laranja.jpg";
	}
}
