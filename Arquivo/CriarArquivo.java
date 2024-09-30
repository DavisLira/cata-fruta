package Arquivo;

import java.io.File;
import java.util.LinkedHashMap;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo extends Arquivo {
    public CriarArquivo(String nomeArquivo,
                        String dimensao,
                        String pedras,
                        LinkedHashMap<String, String[]> frutas,
                        String bichadas,
                        String capacidadeMochila) {
        super(nomeArquivo, dimensao, pedras, frutas, bichadas, capacidadeMochila);
    }
    public CriarArquivo() {
    	super();
    }


	private void LimparArquivo() {
        try (FileWriter escritor = new FileWriter(this.getNomeArquivo(), false)) {
            escritor.write("");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo.");
            e.printStackTrace();
        }
    }

    private boolean criarArquivo() {
        try {
            File arquivo = new File(this.getNomeArquivo());
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado");
                return true;
            } else {
                System.out.println("O arquivo já existe.");
                this.LimparArquivo();
                return false;
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo.");
            e.printStackTrace();
            return false;
        }
    }

    private void __escreverNoArquivo(String conteudo) {
        try (FileWriter escritor = new FileWriter(this.getNomeArquivo(), true)) {
            escritor.write(conteudo);
            escritor.write("\n");
            System.out.println("Conteúdo escrito no arquivo.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }

    public void escreverNoArquivo() {
        boolean arquivoCriado = this.criarArquivo();

        if (arquivoCriado || new File(this.getNomeArquivo()).exists()) {
            this.__escreverNoArquivo("dimensao " + this.getDimensao());
            this.__escreverNoArquivo("pedras " + this.getPedras());

            for (String fruta : this.getFrutas().keySet()) {
                String[] valores = this.getFrutas().get(fruta);

                if (valores.length == 2) {
                    String quantidade = valores[0];
                    String valor = valores[1];
                    this.__escreverNoArquivo(fruta + " " + quantidade + " " + valor);
                } else {
                    System.out.println("Erro: O número de valores para a fruta " + fruta + " está incorreto.");
                }
            }

            this.__escreverNoArquivo("bichadas " + this.getBichadas());
            this.__escreverNoArquivo("mochila " + this.getCapacidadeMochila());
        }
        System.out.println("Conteúdo escrito no arquivo.");
    }

    public static void main(String[] args) {
        // Criando o LinkedHashMap com frutas usando String[] em vez de List<Object>
        LinkedHashMap<String, String[]> frutas = new LinkedHashMap<>();

        frutas.put("maracuja", new String[]{"6", "1"});
        frutas.put("laranja", new String[]{"3", "2"});
        frutas.put("abacate", new String[]{"2", "1"});
        frutas.put("coco", new String[]{"1", "1"});
        frutas.put("acerola", new String[]{"3", "3"});
        frutas.put("amora", new String[]{"6", "4"});
        frutas.put("goiaba", new String[]{"1", "1"});

        String nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
        CriarArquivo arquivo = new CriarArquivo(nomeArquivo, "6", "12", frutas, "3", "10");
        arquivo.escreverNoArquivo();
    }
}
