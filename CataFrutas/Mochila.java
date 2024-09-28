package CataFrutas;

public class Mochila {
    private Fruta[] frutas;
    private int capacidade;

    public Mochila(){
        this.frutas = new Fruta[10];
        this.capacidade = 10;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public Fruta[] getFrutas(){
        return this.frutas;
    }

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
