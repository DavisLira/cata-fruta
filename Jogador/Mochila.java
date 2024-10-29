package Jogador;

import Frutas.Fruta;
import Frutas.Maracuja;
import App.Menu;

/**
 * A classe Mochila representa a mochila de um jogador, onde ele pode armazenar frutas
 */
public class Mochila {
    private Fruta[] frutas;
    private int capacidade, quantidadeMaracuja = 0;

    /**
     * Construtor da classe Mochila 
     * Inicializa uma nova mochila com capacidade para 10 frutas
     */
    public Mochila(){
        this.setCapacidade();
        //this.frutas[0] = new Abacate(new Point(), false);
        //this.frutas[1] = new Banana(new Point(), false);
        //this.frutas[7] = new Goiaba(new Point(), false);
    }
    
    /**
     * Obtém a capacidade atual da mochila
     *
     * @return A capacidade da mochila
     */
    public int getCapacidade(){
        return this.capacidade;
    }
    
    /**
     * Define a capacidade da mochila com base nas configurações do jogo
     */
    public void setCapacidade() {
    	int tamanho = Menu.arquivoHandler.getMochila();
    	this.capacidade = tamanho;
        this.frutas = new Fruta[tamanho];
    }

    /**
     * Obtém as frutas armazenadas na mochila
     *
     * @return Um array de frutas na mochila
     */
    public Fruta[] getFrutas(){
        return this.frutas;
    }

    /**
     * Adiciona uma fruta à mochila, se houver espaço disponível
     *
     * @param fruta A fruta a ser adicionada
     * @return true se a fruta foi adicionada com sucesso, false se a mochila está cheia
     */
    public boolean adicionarFruta(Fruta fruta){
        for (int i = 0; i < this.frutas.length; i++) {
            if (this.frutas[i] == null) {
            	System.out.println(fruta.getClass().getName());
                this.frutas[i] = fruta;
                this.capacidade--;
                return true;
            }
        }
        return false; 
    }

    /**
     * Remove uma fruta da mochila
     *
     * @param fruta A fruta a ser removida
     * @return true se a fruta foi removida com sucesso, false se a fruta não estava na mochila
     */
    public boolean removerFruta(Fruta fruta){
        for(int i = 0; i < this.frutas.length; i++){
            if(this.frutas[i] == fruta){
                this.frutas[i] = null;
                return true;
            }
        }
        return false;
    }
   
    /**
     * Verifica se o jogador ganhou com base na quantidade de frutas do tipo Maracujá
     *
     * @param maracujaJogo A quantidade total de maracujás necessários para ganhar
     * @return true se o jogador ganhou, false caso contrário
     */
    public boolean checarVitoria(int maracujaJogo) {
    	System.out.println("qtd total: " + maracujaJogo);
    	for(int i = 0; i < this.frutas.length; i++) {
    		if(this.frutas[i] instanceof Maracuja) {
    			quantidadeMaracuja++;
    			System.out.println("qtd mochi: "+ quantidadeMaracuja);
    		}
    	}
    	if (quantidadeMaracuja >= ((maracujaJogo/2) +1)) {
    		System.out.println("GANHOU");
    		return true;
    	}

		quantidadeMaracuja = 0;
    	return false;
    }
    
}
