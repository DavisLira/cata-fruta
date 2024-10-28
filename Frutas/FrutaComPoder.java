package Frutas;

import java.awt.Point;

import Jogador.Jogador;

/**
 * A classe abstrata FrutaComPoder representa uma fruta que possui poderes especiais
 * que podem ser utilizados pelos jogadores. Esta classe herda da classe Fruta
 * e define um sistema para aplicar diferentes efeitos no jogador ao consumir a fruta
 */

public abstract class FrutaComPoder extends Fruta {
	
	/** O poder da fruta, que determina o efeito que ela terá no jogador */
    private int poder;

    /**
     * Construtor da classe FrutaComPoder
     * Inicializa uma fruta com poder, nome, posição e estado (bichada ou não)
     * 
     * @param nome O nome da fruta
     * @param posicao A posição da fruta na floresta
     * @param bichada Um booleano que indica se a fruta está bichada (true) ou não (false)
     */
    public FrutaComPoder(Point posicao, boolean bichada) {
        super(posicao, bichada);
    }
    
    public FrutaComPoder(boolean bichada) {
        super(bichada);
    }

    /**
     * Define o poder da fruta
     *
     * @param poder Um inteiro representando o poder da fruta
     */
    protected void setPoder(int poder) {
        this.poder = poder;
    }

    /**
     * Retorna o poder da fruta
     * 
     * @return O poder da fruta como um inteiro
     */
    public int getPoder() {
        return poder;
    }
    
    /**
     * Aplica o efeito de agilidade ao jogador
     * Este método deve ser implementado nas subclasses para definir como a agilidade é afetada
     * 
     * @param jogador O jogador que receberá o efeito de agilidade
     */
    public void obterAgilidade(Jogador jogador) {
        jogador.duplicarMov();
    }

    /**
     * Aplica o efeito de força ao jogador
     * Este método deve ser implementado nas subclasses para definir como a força é afetada
     * 
     * @param jogador O jogador que receberá o efeito de força
     */
    public void obterForca(Jogador jogador) {
        jogador.duplicarForca();
    }

    /**
     * Aplica o efeito de antidoto ao jogador
     * Este método deve ser implementado nas subclasses para definir como o antidoto é aplicado
     * Antidoto serve para remover o efeito aplicado por frutas bixadas
     * 
     * @param jogador O jogador que receberá o efeito de antidoto
     */
    public void obterAntidoto(Jogador jogador) {
    	jogador.setPodeSeMover(true);
    }
    
    /**
     * Método que define o comportamento ao comer a fruta
     * Dependendo do poder da fruta, o jogador recebe um dos efeitos
     * 
     * @param jogador O jogador que irá comer a fruta
     */
    public void comer(Jogador jogador) {
        switch (this.getPoder()) {
            case 1:
                this.obterAgilidade(jogador);
                break;

            case 2:
                this.obterForca(jogador);
                break;
        
            case 3:
                this.obterAntidoto(jogador);
                break;

            default:
                break;
        }

        jogador.comer(this);
    }
}
