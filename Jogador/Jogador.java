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
    
    public String imgControle() {
    	if (this.numero == 1) {
    		return "/Controles/jogador1_cntrl.png";
    	}

		return "/Controles/jogador2_cntrl.png";
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
    
    public String getDado() {
    	switch (this.movimentos) {
			case 1: {
				return "/dados/num_1.png";
			}
			case 2: {
				return "/dados/num_2.png";
			}
			case 3: {
				return "/dados/num_3.png";
			}
			case 4: {
				return "/dados/num_4.png";
			}
			case 5: {
				return "/dados/num_5.png";
			}
			case 6: {
				return "/dados/num_6.png";
			}
			case 7: {
				return "/dados/num_7.png";
			}
			case 8: {
				return "/dados/num_8.png";
			}
			case 9: {
				return "/dados/num_9.png";
			}
			case 10: {
				return "/dados/num_10.png";
			}
			case 11: {
				return "/dados/num_11.png";
			}
			case 12: {
				return "/dados/num_12.png";
			}
			default: {
				return "/dados/num_0.png";
			}
    	}
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
        return local instanceof Pedra;
    }
    
    private boolean eJogador(Object local) {
    	return local instanceof Jogador;
    }

    
    private Point pulaPedra(Point destino, Object[][][] floresta) {
        int movX = destino.x - this.getPosicao().x;
        int movY = destino.y - this.getPosicao().y;

        // Verifica se o movimento é válido (não permanece no mesmo lugar)
        if (movX == 0 && movY == 0) {
            return destino;  // Se não está tentando se mover, retorne a posição original
        }

        Point proximoBloco = new Point(destino.x, destino.y);

        // Continue pulando pedras até encontrar um bloco que não seja pedra
        while (proximoBloco.x >= 0 && proximoBloco.x < floresta[0].length && 
               proximoBloco.y >= 0 && proximoBloco.y < floresta.length && 
               ePedra(floresta[proximoBloco.y][proximoBloco.x][0])) {
            // Pula uma casa no eixo X e Y na direção do movimento
            proximoBloco = new Point(proximoBloco.x + movX, proximoBloco.y + movY);
        }

        return proximoBloco; // Retorna a posição da casa após a(s) pedra(s)
    }
    
    private Point empurrarJogador(Point destino, Object[][][] floresta) {
    	
    	Point proximoBloco = new Point(destino.x, destino.y);
    	if(eJogador(floresta[destino.y][destino.x][0])) {
    		
    	}
    	
    	return posicao;
    }


    /**
     * Verifica se o jogador ainda tem movimentos disponíveis
     *
     * @return true se o jogador tem movimentos, false caso contrário
     */
    public boolean verificarMovimentos(Point destino, Object[][][] floresta) {
        // Inicialmente, custo 1 para movimento simples
        int custo = 1;

        // Se o destino for uma pedra
        if (this.ePedra(floresta[destino.y][destino.x][0])) {
            custo += 2; // Custo para subir a primeira pedra e mover para o próximo bloco

            // Calcula o próximo ponto
            Point proximoBloco = new Point(destino.x + (destino.x - this.posicao.x), destino.y + (destino.y - this.posicao.y));

            // Se o próximo bloco também for uma pedra, aumenta o custo em 2 para cada pedra
            while (proximoBloco.x >= 0 && proximoBloco.x < floresta[0].length &&
                   proximoBloco.y >= 0 && proximoBloco.y < floresta.length &&
                   this.ePedra(floresta[proximoBloco.y][proximoBloco.x][0])) {
                custo += 2;
                proximoBloco = new Point(proximoBloco.x + (destino.x - this.posicao.x), proximoBloco.y + (destino.y - this.posicao.y));
            }
        }

        // Verifica se o jogador tem movimentos suficientes
        return this.movimentos >= custo;
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
    public Point mover(Point destino, Object[][][] floresta) {
        // Encontra a nova posição, pulando pedras
        Point localNovo = pulaPedra(destino, floresta);
        
        // Calcula a distância e custo do movimento
        int custoX = Math.abs(this.getPosicao().x - localNovo.x);   
        int custoY = Math.abs(this.getPosicao().y - localNovo.y);
        int custo = custoX + custoY;
        custo = custo * 2 - 1;  // Cálculo correto do custo, especialmente ao pular pedras

        // Verifica se o novo local está dentro dos limites do mapa
        if (localNovo.x < 0 || localNovo.x >= floresta[0].length || localNovo.y < 0 || localNovo.y >= floresta.length) {
            System.out.println("Movimento inválido: fora dos limites do mapa!");
            return this.posicao;  // Impede o movimento se estiver fora dos limites
        }

        // Verifica se há movimentos suficientes
        if (verificarMovimentos(localNovo, floresta)) {
            this.posicao = new Point(localNovo);  // Atualiza a posição do jogador
            this.movimentos -= custo;  // Aplica o custo correto do movimento
            return localNovo;  // Retorna a nova posição
        } else {
            System.out.println("Não pode mover, movimentos insuficientes.");
            return this.posicao;  // Retorna a posição atual se não puder se mover
        }
    }

    /**
     * O jogador pega uma fruta e a adiciona à sua mochila
     *
     * @param fruta A fruta a ser pega
     */
    public boolean pegarFruta(Fruta fruta) {
        return this.mochila.adicionarFruta(fruta);
    }

    /**
     * O jogador come uma fruta, removendo-a de sua mochila e utilizando seu efeito
     *
     * @param fruta A fruta a ser comida
     */
    public void comer(Fruta fruta) {
        this.mochila.removerFruta(fruta);
        System.out.println("comeu");
        //fruta.comer(this);
    }

	public Mochila getMochila() {
		return this.mochila;
	}
	
	@Override
	public String toString() {
		return "J - " + this.numero;
	}

}
