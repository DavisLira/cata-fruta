package CataFrutas;

public class Abacate extends FrutaComPoder {
    public Abacate(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
        super.setPoder(2);
    }
}