package Arquivo;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * A classe Arquivo gerencia a configuração do jogo, 
 * armazenando elementos como dimensões, número de pedras e frutas
 * Essa classe permite que os atributos padrão sejam redefinidos,
 * elementos sejam modificados e arquivos sejam manipulados
 */
public class Arquivo {
    private File arquivo;
    private String nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
    private LinkedHashMap<String, String[]> Elementos = ElementosPadrao();

    /**
     * Construtor padrão da classe Arquivo
     */
    public Arquivo() {
    }

    /**
     * Retorna um LinkedHashMap com os elementos padrão do jogo
     *
     * @return LinkedHashMap com os elementos padrão
     */
    private LinkedHashMap<String, String[]> ElementosPadrao() {
        LinkedHashMap<String, String[]> Elementos = new LinkedHashMap<>();
        Elementos.put("dimensao", new String[]{"6"});
        Elementos.put("pedras", new String[]{"7"});
        Elementos.put("maracuja", new String[]{"3", "1"});
        Elementos.put("laranja", new String[]{"2", "1"});
        Elementos.put("abacate", new String[]{"2", "3"});
        Elementos.put("coco", new String[]{"2", "1"});
        Elementos.put("banana", new String[]{"1", "2"});
        Elementos.put("amora", new String[]{"1", "1"});
        Elementos.put("goiaba", new String[]{"1", "1"});
        Elementos.put("bichadas", new String[]{"1", "1"});
        Elementos.put("mochila", new String[]{"1", "1"});
        return Elementos;
    }

    /**
     * Define ou atualiza um elemento no mapa de elementos
     *
     * @param Chave a chave do elemento a ser adicionado ou atualizado
     * @param Valor o valor associado à chave
     */
    public void setElemento(String Chave, String[] Valor) {
        this.Elementos.put(Chave, Valor);
    }

    /**
     * Redefine os atributos da classe para seus valores padrão
     */
    public void reiniciarAtributos() {
        this.nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
        this.Elementos = ElementosPadrao();
    }

    /**
     * Retorna o arquivo associado a esta classe
     *
     * @return o arquivo associado
     */
    public File getArquivo() {
        return this.arquivo;
    }

    /**
     * Retorna o nome do arquivo de configuração
     *
     * @return o nome do arquivo de configuração
     */
    public String getNomeArquivo() {
        return this.nomeArquivo;
    }

    /**
     * Define o nome do arquivo de configuração
     *
     * @param nomeArquivo o nome do arquivo a ser definido
     */
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * Define o arquivo associado a esta classe
     *
     * @param arquivo o arquivo a ser definido
     */
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * Define os elementos do jogo
     *
     * @param Elementos o mapa de elementos a ser definido
     */
    public void setElementos(LinkedHashMap<String, String[]> Elementos) {
        this.Elementos = Elementos;
    }

    /**
     * Retorna os elementos do jogo
     *
     * @return um LinkedHashMap com os elementos do jogo
     */
    public LinkedHashMap<String, String[]> getElementos() {
        return this.Elementos;
    }

    // Métodos de obtenção dos atributos individuais

    /**
     * Retorna a dimensão do jogo
     *
     * @return a dimensão do jogo
     */
    public int getDimensao() {
        String[] valores = this.Elementos.get("dimensao");
        return Integer.parseInt(valores[0]);
    }

    /**
     * Retorna o número de pedras do jogo
     *
     * @return o número de pedras
     */
    public int getPedras() {
        String[] valores = this.Elementos.get("pedras");
        return Integer.parseInt(valores[0]);
    }

    /**
     * Retorna o número de frutas bichadas
     *
     * @return o número de frutas bichadas
     */
    public int getBichadas() {
        String[] valores = this.Elementos.get("bichadas");
        return Integer.parseInt(valores[0]);
    }

    /**
     * Retorna a capacidade da mochila
     *
     * @return a capacidade da mochila
     */
    public int getMochila() {
        String[] valores = this.Elementos.get("mochila");
        return Integer.parseInt(valores[0]);
    }

    // Métodos de obtenção das frutas

    public int getMaracujaVitoria() {
        String[] valores = this.Elementos.get("maracuja");
        return Integer.parseInt(valores[0]);
    }

    public int getMaracujaTotal() {
        String[] valores = this.Elementos.get("maracuja");
        return Integer.parseInt(valores[1]);
    }

    public int getLaranjeiras() {
        String[] valores = this.Elementos.get("laranja");
        return Integer.parseInt(valores[0]);
    }

    public int getLaranja() {
        String[] valores = this.Elementos.get("laranja");
        return Integer.parseInt(valores[1]);
    }

    public int getAbacateiras() {
        String[] valores = this.Elementos.get("abacate");
        return Integer.parseInt(valores[0]);
    }

    public int getAbacate() {
        String[] valores = this.Elementos.get("abacate");
        return Integer.parseInt(valores[1]);
    }

    public int getCoqueiro() {
        String[] valores = this.Elementos.get("coco");
        return Integer.parseInt(valores[0]);
    }

    public int getCoco() {
        String[] valores = this.Elementos.get("coco");
        return Integer.parseInt(valores[1]);
    }

    public int getBananeira() {
        String[] valores = this.Elementos.get("banana");
        return Integer.parseInt(valores[0]);
    }

    public int getBanana() {
        String[] valores = this.Elementos.get("banana");
        return Integer.parseInt(valores[1]);
    }

    public int getAmoreira() {
        String[] valores = this.Elementos.get("amora");
        return Integer.parseInt(valores[0]);
    }

    public int getAmora() {
        String[] valores = this.Elementos.get("amora");
        return Integer.parseInt(valores[1]);
    }

    public int getGoiabeira() {
        String[] valores = this.Elementos.get("goiaba");
        return Integer.parseInt(valores[0]);
    }

    public int getGoiaba() {
        String[] valores = this.Elementos.get("goiaba");
        return Integer.parseInt(valores[1]);
    }

    /**
     * Método principal para execução de testes da classe
     *
     * @param args argumentos da linha de comando
     */
    public static void main(String[] args) {
        Arquivo a = new Arquivo();
        int ab = a.getPedras();
        System.out.println(ab);
    }
}
