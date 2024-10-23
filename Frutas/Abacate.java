package Frutas;

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
     * @param nome O nome da fruta
     * @param posicao A posição da fruta na floresta, representada por um array de inteiros [X, Y]
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
	
    public Abacate(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
        super.setPoder(2);
    }
    
	public String getImg() {
		return "/sprites/abacate.jpg";
	}
}
