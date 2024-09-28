package CataFrutas;

public abstract class FrutaComPoder extends Fruta {
    private int poder;

    public FrutaComPoder(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
    }

    protected void setPoder(int poder) {
        this.poder = poder;
    }

    public int getPoder() {
        return poder;
    }

    public void obterAgilidade(Jogador jogador) {
        
    }

    public void obterForca(Jogador jogador) {

    }

    public void obterAntidoto(Jogador jogador) {
        
    }

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
    }
}
