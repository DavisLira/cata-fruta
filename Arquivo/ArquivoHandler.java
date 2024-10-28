package Arquivo;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
* A classe ArquivoHandler estende a classe Arquivo e fornece funcionalidades adicionais 
* para manipulação de arquivos, como leitura, escrita e validação de conteúdo com base 
* em um formato específico
*/

public class ArquivoHandler extends Arquivo {  
    
	/**
     * Construtor da classe ArquivoHandler. Inicializa os atributos da classe mãe
     */
	
	public ArquivoHandler() {
		super();
	}
	
	/**
     * Lê o conteúdo de um arquivo e popula os elementos com base em um formato predefinido
     * 
     * @param caminho O caminho do arquivo a ser lido
     * @return true se o arquivo foi lido e validado com sucesso, caso contrário false
     */	
    
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
                    	String dimensao = validarNumero(linha, "dimensao");
                        if (linha.startsWith("dimensao ") && dimensao != "-1") {
                            this.setElemento("dimensao", new String[] {dimensao});
                            break;
                        }
                        System.out.println("Dimensão tem que estar no formato 'dimensao x'!");
                        return false;
                    case 2:
                    	String pedras = validarNumero(linha, "pedras");
                        if (linha.startsWith("pedras ") && pedras != "-1") {
                        	this.setElemento("pedras", new String[] {pedras});
                            break;
                        }
                        System.out.println("Pedras tem que estar no formato 'pedras x'!");
                        return false;
                    case 3:
                        if (linha.startsWith("maracuja ") && validarFrutas(linha)) {
                        	break;
                        }
                        System.out.println("Maracuja tem que estar no formato 'maracuja x y'!");
                        return false;
                    case 4:
                        if (linha.startsWith("laranja ") && validarFrutas(linha)) {
                        	break;
                        }                        
                        System.out.println("Laranja tem que estar no formato 'laranja x y'!");
                        return false;
                    case 5:
                        if (linha.startsWith("abacate ") && validarFrutas(linha)) {
                            break;
                        }
                        System.out.println("Abacate tem que estar no formato 'abacate x y'!");
                        return false;
                        
                    case 6:
                        if (linha.startsWith("coco ") && validarFrutas(linha)) {
                            break;
                        }
                        System.out.println("Coco tem que estar no formato 'coco x y'!");
                        return false;
                        
                    case 7:
                        if (linha.startsWith("banana ") && validarFrutas(linha)) {
                            break;
                        }
                        System.out.println("Banana tem que estar no formato 'banana x y'!");
                        return false;
                        
                    case 8:
                        if (linha.startsWith("amora ") && validarFrutas(linha)) {
                            break;
                        }
                        System.out.println("Amora tem que estar no formato 'amora x y'!");
                        return false;
                        
                    case 9:
                        if (linha.startsWith("goiaba ") && validarFrutas(linha)) {
                            break;
                        }
                        System.out.println("Goiaba tem que estar no formato 'goiaba x y'!");
                        return false;
                        
                    case 10:
                    	String bichada = validarNumero(linha, "bichada");
                        if (linha.startsWith("bichadas ") && bichada != "-1") {
                        	this.setElemento("bichadas", new String[] {bichada});
                            break;
                        }
                        System.out.println("Bichadas tem que estar no formato 'bichadas x'!");
                        return false;
                    case 11:
                    	String capacidade = validarNumero(linha, "mochila");
                        if (linha.startsWith("mochila ") && capacidade != "-1") {
                        	this.setElemento("mochila", new String[] {capacidade});
                        	break;
                        }
                        System.out.println("Mochila tem que estar no formato 'mochila x'!");
                        return false;
                    default:
                        System.out.println("Arquivo diferente do modelo!");
                        return false;
                }
            }

        
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!this.validarGeracaoMapa(20)) {
        	return false;
        } else {        	
        	this.setElementos(this.getElementos());
        	return true;
        }
        
    }
    
    /**
     * Valida a geração do mapa com base em uma porcentagem de ocupação da matriz
     * 
     * @param porcentagemGrama A porcentagem de grama (espaços vazios) que deve ser preservada no mapa
     * @return true se a geração do mapa for válida, false caso contrário
     */

    public boolean validarGeracaoMapa(int porcentagemGrama) {
    	String[] frutas = {"laranja", "abacate", "coco", "banana", "amora", "goiaba"};
    	int soma = 2; //começa em dois por sao dois players
    	System.out.println("to aqui");
    	//int dimensao = Integer.parseInt(this.Elementos.get("dimensao")[0]);
    	int dimensao = Integer.parseInt(this.getElementos().get("dimensao")[0]);
    	//int maracuja = Integer.parseInt(this.Elementos.get("maracuja")[1]);
    	int maracuja = Integer.parseInt(this.getElementos().get("maracuja")[1]);
    	//int pedras = Integer.parseInt(this.Elementos.get("pedras")[0]);
    	int pedras = Integer.parseInt(this.getElementos().get("pedras")[0]);
    	soma = soma + maracuja + pedras;
    	int matriz = dimensao * dimensao;
    	
    	for (String fruta : frutas) {
    		String[] valores = this.getElementos().get(fruta);
    		int arvores = Integer.parseInt(valores[0]);
    		int fruta_chao = Integer.parseInt(valores[1]);
    		soma = soma + arvores + fruta_chao;
    	}
    	
    	double porcentagem = (double) soma / matriz * 100;
    	System.out.println(soma);
    	System.out.println(porcentagem);
    	return porcentagem <= (100 - porcentagemGrama) ? true : false;
    }
    
    /**
     * Valida se a linha contém um número válido para um determinado elemento do arquivo
     * 
     * @param linha A linha a ser validada.
     * @param chave A chave do elemento que está sendo validado
     * @return O número válido como String ou "-1" em caso de erro
     */
    
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
    
    /**
     * Valida se a linha de entrada contém os valores corretos para as frutas
     * 
     * @param linha A linha a ser validada
     * @return true se a linha estiver no formato correto, false caso contrário
     */

    private boolean validarFrutas(String linha) {
        try {
            String[] partes = linha.split(" ");
            if (partes.length != 3) {
                System.out.println("Erro na linha de frutas: Formato incorreto. Esperado: 'nome quantidade valor'. Linha: " + linha);
                return false;
            }
            String nomeFruta = partes[0];
            String quantidade = partes[1];
            String valor = partes[2];
            this.setElemento(nomeFruta, new String[]{quantidade, valor});
        } catch (NumberFormatException e) {
            System.out.println("Erro na linha de frutas: Quantidade ou valor não são números válidos. Linha: " + linha);
            return false;
        }
        return true;
    }
    
    /**
     * Limpa o conteúdo do arquivo
     */
    
	private void LimparArquivo() {
        try (FileWriter escritor = new FileWriter(this.getNomeArquivo(), false)) {
            escritor.write("");
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo.");
            e.printStackTrace();
        }
    }
	
	/**
     * Cria um novo arquivo com base no nome do arquivo especificado na classe Arquivo
     * Se o arquivo já existir, ele será limpo
     * 
     * @return true se o arquivo foi criado com sucesso, false caso contrário
     */

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
    
    /**
     * Escreve conteúdo no arquivo, adicionando informações sobre os elementos
     * Este método deve ser chamado após a criação ou limpeza do arquivo
     * 
     * @param conteudo O conteúdo a ser escrito no arquivo
     */

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

    /**
     * Método público que escreve as informações dos elementos no arquivo
     * Ele cria o arquivo, se necessário, e escreve todas as configurações no arquivo
     */
    
    public void escreverNoArquivo() {
        boolean arquivoCriado = this.criarArquivo();

        if (arquivoCriado || new File(this.getNomeArquivo()).exists()) {
            this.__escreverNoArquivo("dimensao " + this.getDimensao());
            this.__escreverNoArquivo("pedras " + this.getPedras());
            this.__escreverNoArquivo("maracuja " + this.getMaracujaTotal() + " "+ this.getMaracujaInicio());
            this.__escreverNoArquivo("laranja " + this.getLaranjeiras() + " "+ this.getLaranja());
            this.__escreverNoArquivo("abacate " + this.getAbacateiras() + " "+ this.getAbacate());
            this.__escreverNoArquivo("coco " + this.getCoqueiro() + " "+ this.getCoco());
            this.__escreverNoArquivo("banana " + this.getBananeira() + " "+ this.getBanana());
            this.__escreverNoArquivo("amora " + this.getAmoreira() + " "+ this.getAmora());
            this.__escreverNoArquivo("goiaba " + this.getGoiabeira() + " "+ this.getGoiaba());
            this.__escreverNoArquivo("bichadas " + this.getBichadas());
            this.__escreverNoArquivo("mochila " + this.getMochila());
        }
        System.out.println("Conteúdo escrito no arquivo.");
    }
    
    /**
     * Método principal para testar a funcionalidade de validação do mapa
     * 
     * @param args Argumentos da linha de comando
     */
    
    public static void main(String[] args) {
        ArquivoHandler abridor = new ArquivoHandler();
        boolean result = abridor.validarGeracaoMapa(30);

        if (result) {
            System.out.println("Arquivo válido!");
        } else {
            System.out.println("Erro na validação do arquivo.");
        }
    }
  
}
