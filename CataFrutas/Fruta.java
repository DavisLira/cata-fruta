package CataFrutas;

public abstract class Fruta {
    private String nome;
    private int[] posicao;
    private boolean bichada;

    public Fruta(String nome, int[] posicao, boolean bichada) {
        this.setNome(nome);
        this.setPosicao(posicao);
        this.setBichada(bichada);
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPosicao(int[] posicao) {
        this.posicao = posicao;
    }

    public int[] getPosicao() {
        return posicao;
    }

    public void setBichada(boolean bichada) {
        this.bichada = bichada;
    }

    public boolean isBichada() {
        return bichada;
    }

    public abstract void comer(Jogador jogador);
}
