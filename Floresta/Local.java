package Floresta;

/**
 * A classe abstrata Local representa um local na floresta que tem um custo de movimento associado e uma posição
 * Ela serve como base para diferentes tipos de locais que podem existir na floresta
 */

public abstract class Local {
	
	 /**
     * O custo de movimento para atravessar ou interagir com este local
     * O valor padrão é 1
     */
	
	private int custoMovimento = 1;
	
	/**
     * A posição deste local na floresta, representada como um array de inteiros
     * O primeiro valor representa a coordenada X e o segundo valor representa a coordenada Y
     */
	
	private int[] posicao;
	
	/**
     * Construtor padrão da classe Local
     * Inicializa um local com os valores padrão
     */
	
	public Local() {
		
	}
	
	/**
     * Retorna o custo de movimento necessário para atravessar este local
     * 
     * @return O custo de movimento
     */
	
	public int getCusto() {
		return this.custoMovimento;
	}
	
	/**
     * Define o custo de movimento necessário para atravessar este local
     * 
     * @param custo O novo valor do custo de movimento
     */
	
	protected void setCusto(int custo) {
		this.custoMovimento = custo;
	}
	
	
	/**
     * Retorna a posição deste local na floresta
     * 
     * @return Um array de inteiros representando a posição [X, Y]
     */
	
	public int[] getPosicao() {
		return this.posicao;
	}
}
