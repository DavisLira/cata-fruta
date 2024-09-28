package Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivo extends Arquivo{
    public LerArquivo(String caminho_arquivo) {
    	super(new File(caminho_arquivo));
    }

    public boolean ValidaArquivo() {
        if (!this.getArquivo().exists() || !this.getArquivo().canRead()) {
            System.out.println("Não foi possível ler o arquivo selecionado!");
            return false;
        }
        
        try (BufferedReader leitor = new BufferedReader(new FileReader(this.getArquivo()))) {
            String linha;
            int contador = 0;
            
            while ((linha = leitor.readLine()) != null) {
                contador++;

                switch (contador) {
                    case 1:
                        if (!linha.startsWith("dimensao ")) {
                            System.out.println("Dimensao tem que estar no formado 'dimensao x'!");
                            return false;
                        }
                        this.setDimensao(validarNumero(linha, "dimensao"));
                        break;
                    case 2:
                        if (!linha.startsWith("pedras ")) {
                            System.out.println("Pedras tem que estar no formado 'pedras x'!");
                            return false;
                        }
                        this.setPedras(validarNumero(linha, "pedras"));
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
           
                        validarFrutas(linha);
                        break;
                    case 10:
                        if (!linha.startsWith("bichadas ")) {
                            System.out.println("Bichadas tem que estar no formado 'bichadas x'!");
                            return false;
                        }
                        this.setBichadas(validarNumero(linha, "bichadas"));
                        break;
                    case 11:
                        if (!linha.startsWith("mochila ")) {
                            System.out.println("Mochila tem que estar no formado 'mochila x'!");
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


    private int validarNumero(String linha, String chave) {
        try {
            String[] partes = linha.split(" ");
            if (partes.length != 2) {
                System.out.println(chave + "Formato correto: " + chave + " X");
                return -1;
            }
            return Integer.parseInt(partes[1]);
        } catch (NumberFormatException e) {
            System.out.println(chave + "não é um número válido.");
            return -1;
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
            int quantidade = Integer.parseInt(partes[1]);
            int valor = Integer.parseInt(partes[2]);
            getFrutas().put(nomeFruta, new int[]{quantidade, valor});
        } catch (NumberFormatException e) {
            System.out.println("Erro na linha de frutas: Quantidade ou valor não são números válidos. Linha: " + linha);
        }
    }


    public void imprimirAtributos() {
        System.out.println("Dimensão: " + this.getDimensao());
        System.out.println("Pedras: " + this.getPedras());
        System.out.println("Frutas: ");
        for (String fruta : getFrutas().keySet()) {
            int[] valores = getFrutas().get(fruta);
            System.out.println("  " + fruta + " - Quantidade: " + valores[0] + ", Valor: " + valores[1]);
        }
        System.out.println("Bichadas: " + this.getBichadas());
        System.out.println("Capacidade da Mochila: " + this.getCapacidadeMochila());
    }

    public static void main(String[] args) {
        String caminhoArquivo = "arqs" + System.getProperty("file.separator") + "teste.txt";
        LerArquivo abridor = new LerArquivo(caminhoArquivo);
        boolean result = abridor.ValidaArquivo();
        
        if (result) {
            System.out.println("Arquivo válido!");
            abridor.imprimirAtributos();
        } else {
            System.out.println("Erro na validação do arquivo.");
        }
    }
}
