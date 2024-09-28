package Frutas;

import Jogador.Jogador;

public abstract class FrutaSemPoder extends Fruta{
    public FrutaSemPoder(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
    }

    public void comer(Jogador jogador) {
        jogador.comer(this);
    }
}
