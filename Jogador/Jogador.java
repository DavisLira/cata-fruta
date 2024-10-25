package Jogador;

import java.awt.Point;

import Floresta.Pedra;
import Frutas.Fruta;

/**
 * A classe Jogador representa um jogador no jogo
 * Ela contém informações sobre o nome do jogador, sua posição, força, movimentos e uma mochila 
 * para armazenar frutas
 */
public class Jogador {
    private int numero;
	private Point posicao;
    private int movimentos;
    private int forca = 1;
    private Mochila mochila;

    /**
     * Construtor da classe Jogador
     * Inicializa uma instância de Jogador com o numero especificado
     * A posição inicial do jogador é (0, 0) e uma mochila é criada
     *
     * @param nome O numero do jogador
     */
    public Jogador(int numero, Point posicao) {
    	this.setMovimento();
        this.numero = numero;
        this.posicao = posicao;
        this.mochila = new Mochila();
    }

    // GETTERS
    /**
     * Obtém o numero do jogador
     *
     * @return O numerodo jogador
     */
    public int getNumero() {
        return this.numero;
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
    
    public Point getPosicao() {
    	return this.posicao;
    }
    
    public void setPosicao(Point posicao) {
    	this.posicao = posicao;
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
    
    private boolean ePedra(Object local) {
    	if (local instanceof Pedra) {
    		return true;
    	}
    	return false;
    }
    
    private Point pulaPedra(Point destino, Object[][][] floresta) {
        if (destino.x < 0 || destino.x >= floresta[0].length || destino.y < 0 || destino.y >= floresta.length) {
            System.out.println("Movimento inválido: fora dos limites do mapa!");
            return null; // Impede o movimento se estiver fora dos limites
        }
        
    	int movX = destino.x - this.getPosicao().x;
    	int movY = destino.y - this.getPosicao().y;
    
    	System.out.println("mov x: " + movX);
    	System.out.println("mov y: " + movY);
    	Point LocalQueda = new Point();
    	LocalQueda.x = destino.x + movX;
    	LocalQueda.y = destino.y + movY;

    	if (floresta[LocalQueda.y][LocalQueda.x][0] instanceof Pedra) {
    		return this.pulaPedra(LocalQueda, floresta);
    	} else {
    		return LocalQueda;
    	}
    }

    /**
     * Verifica se o jogador ainda tem movimentos disponíveis
     *
     * @return true se o jogador tem movimentos, false caso contrário
     */
    public boolean verificarMovimentos(Point destino, int custo, Object[][][] floresta){
    	Object localNovo = floresta[destino.y][destino.x][0];

    	if (this.ePedra(localNovo)) {
    		
    		if (custo > this.movimentos) {
    			return false;
    		}
    		else return true;
    	}
    	
        if (this.movimentos > 0) {
            return true;
        }
        return false;
    }
    
    public boolean acabouMovimentos() {
        if (this.movimentos <= 0) {
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
    public void mover(Point destino, Object[][][] floresta) {
    	Point localNovo = pulaPedra(destino, floresta);
    	
    	int custoX = Math.abs(this.getPosicao().x - localNovo.x);   
    	int custoY = Math.abs(this.getPosicao().y - localNovo.y);   
		int custo = custoX + custoY;
		custo = custo * 2 - 1;
		
    	System.out.println("local novo: " + localNovo);
		
		if (verificarMovimentos(destino, custo, floresta)) {
	    	this.posicao = new Point(localNovo);
	    	this.movimentos -= custo;
		} else {
			System.out.println("Não pode");
		}
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
	
	@Override
	public String toString() {
		return "J - " + this.numero;
	}

}
