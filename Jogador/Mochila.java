package Jogador;

import java.awt.Point;

import Frutas.Abacate;
import Frutas.Banana;
import Frutas.Fruta;
import Frutas.Goiaba;

/**
 * A classe Mochila representa a mochila de um jogador, onde ele pode armazenar frutas
 */
public class Mochila {
    private Fruta[] frutas;
    private int capacidade;

    /**
     * Construtor da classe Mochila
     * Inicializa uma nova mochila com capacidade para 10 frutas
     */
    public Mochila(){
        this.frutas = new Fruta[10];
        this.frutas[0] = new Banana("Banana", new int[] {0,0}, false);
        this.frutas[4] = new Abacate("Abacate", new int[] {0,0}, false);
        this.frutas[8] = new Goiaba("Goiaba", new int[] {0,0}, false);
        this.capacidade = 10;
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
                this.capacidade++;
                return true;
            }
        }
        return false;
    }

}
