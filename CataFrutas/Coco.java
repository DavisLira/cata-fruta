package CataFrutas;

public class Coco extends FrutaComPoder {
    public Coco(String nome, int[] posicao, boolean bichada) {
        super(nome, posicao, bichada);
        super.setPoder(1);
    }
}