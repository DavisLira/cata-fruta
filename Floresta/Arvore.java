package Floresta;

import Frutas.*;
import Jogador.*;

public class Arvore extends Local{
	private Fruta fruta;
	private int madura = 5;
	
	public Arvore(Fruta fruta) {
		this.fruta = fruta;
	}
	
	public void darFruta(Jogador jogador) {
		if (madura == 5) {
			jogador.pegarFruta(this.fruta);
			madura = 0;
		}
		madura++;
	}
	
}
