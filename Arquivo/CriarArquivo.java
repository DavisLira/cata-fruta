package Arquivo;

import java.io.File;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo extends Arquivo{
	public CriarArquivo(String nomeArquivo,
						int dimensao,
						int pedras,
						HashMap<String, int[]> frutas,
						int bichadas,
						int capacidadeMochila)
	{
		super(nomeArquivo, dimensao, pedras, frutas, bichadas, capacidadeMochila);
		
        try (FileWriter escritor = new FileWriter(this.getNomeArquivo(), false)) {
            escritor.write("");

        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo.");
            e.printStackTrace();
        }
    }
	
	
    public boolean criarArquivo(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo criado");
                return true;
            } else {
                System.out.println("O arquivo já existe.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo.");
            e.printStackTrace();
            return false;
        }
    }
    
    public void escreverNoArquivo(String nomeArquivo, String conteudo) {
        try (FileWriter escritor = new FileWriter(nomeArquivo, true)) {
            escritor.write(conteudo);
            escritor.write("\n");
            System.out.println("Conteúdo escrito no arquivo.");

        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        HashMap<String, int[]> frutas = new HashMap<>();
        
        frutas.put("maracuja", new int[] {6, 1});
        frutas.put("laranja", new int[] {3, 2});
        frutas.put("abacate", new int[] {2, 1});
        frutas.put("coco", new int[] {1, 1});
        frutas.put("acerola", new int[] {3, 3});
        frutas.put("amora", new int[] {6, 4});
        frutas.put("goiaba", new int[] {1, 1});
        
        String nomeArquivo = "arqs" + System.getProperty("file.separator") + "teste.txt";
    	CriarArquivo arquivo = new CriarArquivo(nomeArquivo, 6, 12, frutas, 3, 10);

        boolean arquivoCriado = arquivo.criarArquivo(arquivo.getNomeArquivo());
        
        if (arquivoCriado || new File(nomeArquivo).exists()) {
        	arquivo.escreverNoArquivo(arquivo.getNomeArquivo(), "dimensao " + arquivo.getDimensao());
        	arquivo.escreverNoArquivo(arquivo.getNomeArquivo(), "pedras " + arquivo.getDimensao());
        	
            for (String fruta : arquivo.getFrutas().keySet()) {
                int[] valores = arquivo.getFrutas().get(fruta);
                arquivo.escreverNoArquivo(arquivo.getNomeArquivo(), fruta + " " + valores[0] + " " + valores[1]);
            }
        	arquivo.escreverNoArquivo(arquivo.getNomeArquivo(), "bichadas " + arquivo.getBichadas());
        	arquivo.escreverNoArquivo(arquivo.getNomeArquivo(), "mochila " + arquivo.getCapacidadeMochila());
        	
        }
    }
}
