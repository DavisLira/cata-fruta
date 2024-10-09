package Frutas;

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
	public Goiaba(String nome, int[] posicao, boolean bichada) {
		super(nome, posicao, bichada);
	}
}
