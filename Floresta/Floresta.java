package Floresta;

/**
 * A classe Floresta representa uma floresta com uma matriz que contém diferentes elementos como frutas de ouro, 
 * frutas comuns, pedras, e frutas bichadas (danificadas). Esses elementos são usados para definir o estado e 
 * o conteúdo da floresta
 */

public class Floresta {
	
	/**
     * Matriz que representa o mapa da floresta
     * Cada célula pode conter um tipo de objeto (fruta, pedra, etc.)
     */
	
	private int matriz[][];
	
	 /**
     * Número de frutas ouro disponíveis na floresta
     */
	
	private int frutasOuro;
	
	/**
     * Número de frutas comuns disponíveis na floresta
     */
	
	private int frutasNaoOuro;

	/**
     * Número de pedras presentes na floresta
     */
	
	private int pedras;
	
	/**
     * Porcentagem de frutas bichadas presentes na floresta
     */
	
	private float bichadas;
	
	/**
     * Construtor da classe Floresta. Inicializa uma nova instância da floresta
     * Pode ser expandido para incluir parâmetros ou lógica de inicialização
     */
	
	public Floresta() {
		
	}
}
