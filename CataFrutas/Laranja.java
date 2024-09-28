package CataFrutas;

public class Laranja extends FrutaComPoder {
    public Laranja(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
        super.setPoder(3);
    }
}
