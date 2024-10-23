package Jogador;

import Frutas.Fruta;

/**
 * A classe Jogador representa um jogador no jogo
 * Ela contém informações sobre o nome do jogador, sua posição, força, movimentos e uma mochila 
 * para armazenar frutas
 */
public class Jogador {
    private String nome;
    private int[] posicao;
    private int movimentos = 0;
    private int forca = 1;
    private Mochila mochila;

    /**
     * Construtor da classe Jogador
     * Inicializa uma instância de Jogador com o nome especificado
     * A posição inicial do jogador é (0, 0) e uma mochila é criada
     *
     * @param nome O nome do jogador
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.posicao = new int[2];
        this.mochila = new Mochila();
    }

    // GETTERS
    /**
     * Obtém o nome do jogador
     *
     * @return O nome do jogador
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * Obtém a força do jogador
     *
     * @return A força do jogador
     */
    public int getForca(){
        return this.forca;
    }

    /**
     * Obtém o número de movimentos restantes do jogador
     *
     * @return O número de movimentos
     */
    public int getMovimento(){
        return this.movimentos;
    }

    // SETTERS
    /**
     * Define o número de movimentos do jogador com um valor aleatório de 1 a 6
     */
    public void setMovimento(){
        this.movimentos = this.jogarDados();
    }
    
    /**
     * Simula um lançamento de dados, retornando um valor aleatório entre 1 e 6
     *
     * @return O resultado do dado
     */
    public int jogarDados(){
        return (int)(Math.random() * 6) + 1;
    }

    /**
     * Verifica se o jogador ainda tem movimentos disponíveis
     *
     * @return true se o jogador tem movimentos, false caso contrário
     */
    public boolean verificarMovimentos(){
        if (this.movimentos > 0) {
            return true;
        }
        return false;
    }

    /**
     * Move o jogador para uma nova posição, se houver movimentos restantes
     *
     * @param destino Um array de inteiros representando a nova posição (x, y)
     * @return true se o movimento foi bem sucedido, false caso contrário
     */
    public boolean mover(int[] destino) {
        if (this.movimentos > 0) {
            this.posicao[0] = destino[0];
            this.posicao[1] = destino[1];
            this.movimentos--;
            return true;
        }
        return false;
    }

    /**
     * O jogador pega uma fruta e a adiciona à sua mochila
     *
     * @param fruta A fruta a ser pega
     */
    public void pegarFruta(Fruta fruta) {
        this.mochila.adicionarFruta(fruta);
    }

    /**
     * O jogador come uma fruta, removendo-a de sua mochila e utilizando seu efeito
     *
     * @param fruta A fruta a ser comida
     */
    public void comer(Fruta fruta) {
        this.mochila.removerFruta(fruta);
        fruta.comer(this);
    }

	public Mochila getMochila() {
		return this.mochila;
	}

}
