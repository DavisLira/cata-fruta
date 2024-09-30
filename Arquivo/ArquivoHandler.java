package Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoHandler extends Arquivo {  
    
	public ArquivoHandler() {
		super();
	}
    
    public boolean ler(String caminho) {
    	File arquivo = new File(caminho);
        if (!arquivo.exists() || !arquivo.canRead()) {
            System.out.println("Não foi possível ler o arquivo selecionado!");
            return false;
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha;
            int contador = 0;

            while ((linha = leitor.readLine()) != null) {
                contador++;

                switch (contador) {
                    case 1:
                        if (!linha.startsWith("dimensao ")) {
                            System.out.println("Dimensão tem que estar no formato 'dimensao x'!");
                            return false;
                        }
                        this.setDimensao(validarNumero(linha, "dimensao"));
                        break;
                    case 2:
                        if (!linha.startsWith("pedras ")) {
                            System.out.println("Pedras tem que estar no formato 'pedras x'!");
                            return false;
                        }
                        this.setPedras(validarNumero(linha, "pedras"));
                        break;
                    case 3:
                        if (!linha.startsWith("maracuja ")) {
                            System.out.println("Maracuja tem que estar no formato 'maracuja x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 4:
                        if (!linha.startsWith("laranja ")) {
                            System.out.println("Laranja tem que estar no formato 'laranja x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 5:
                        if (!linha.startsWith("abacate ")) {
                            System.out.println("Abacate tem que estar no formato 'abacate x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 6:
                        if (!linha.startsWith("coco ")) {
                            System.out.println("Coco tem que estar no formato 'coco x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 7:
                        if (!linha.startsWith("acerola ")) {
                            System.out.println("Acerola tem que estar no formato 'acerola x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 8:
                        if (!linha.startsWith("amora ")) {
                            System.out.println("Amora tem que estar no formato 'amora x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 9:
                        if (!linha.startsWith("goiaba ")) {
                            System.out.println("Goiaba tem que estar no formato 'goiaba x y'!");
                            return false;
                        }
                        validarFrutas(linha);
                        break;
                    case 10:
                        if (!linha.startsWith("bichadas ")) {
                            System.out.println("Bichadas tem que estar no formato 'bichadas x'!");
                            return false;
                        }
                        this.setBichadas(validarNumero(linha, "bichadas"));
                        break;
                    case 11:
                        if (!linha.startsWith("mochila ")) {
                            System.out.println("Mochila tem que estar no formato 'mochila x'!");
                            return false;
                        }
                        this.setCapacidadeMochila(validarNumero(linha, "mochila"));
                        break;
                    default:
                        System.out.println("Arquivo diferente do modelo!");
                        return false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String validarNumero(String linha, String chave) {
        try {
            String[] partes = linha.split(" ");
            if (partes.length != 2) {
                System.out.println(chave + "Formato correto: " + chave + " X");
                return "-1";
            }
            return partes[1];
        } catch (NumberFormatException e) {
            System.out.println(chave + " não é um número válido.");
            return "-1";
        }
    }

    private void validarFrutas(String linha) {
        try {
            String[] partes = linha.split(" ");
            if (partes.length != 3) {
                System.out.println("Erro na linha de frutas: Formato incorreto. Esperado: 'nome quantidade valor'. Linha: " + linha);
                return;
            }
            String nomeFruta = partes[0];
            String quantidade = partes[1];
            String valor = partes[2];
            getFrutas().put(nomeFruta, new String[]{quantidade, valor});
        } catch (NumberFormatException e) {
            System.out.println("Erro na linha de frutas: Quantidade ou valor não são números válidos. Linha: " + linha);
        }
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
        String caminhoArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";

        ArquivoHandler abridor = new ArquivoHandler();
        boolean result = abridor.ler(caminhoArquivo);

        if (result) {
            System.out.println("Arquivo válido!");
        } else {
            System.out.println("Erro na validação do arquivo.");
        }
    }
}
