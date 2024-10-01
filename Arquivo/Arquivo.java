package Arquivo;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Arquivo {
	private File arquivo;
	private String nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
    private String dimensao = "6";
    private String pedras = "7";
    private LinkedHashMap<String, String[]> frutas = FrutasPadrao(); 
    private String bichadas = "25";
    private String capacidadeMochila = "10";
    
	
    //para a classe de escrever o arquivo
    public Arquivo() {
	}

   
	private LinkedHashMap<String, String[]> FrutasPadrao() {
	    LinkedHashMap<String, String[]> frutas = new LinkedHashMap<>();
		
	    frutas.put("maracuja", new String[]{"3", "1"});
	    frutas.put("laranja", new String[]{"2", "1"});
	    frutas.put("abacate", new String[]{"2", "3"});
	    frutas.put("coco", new String[]{"2", "1"});
	    frutas.put("acerola", new String[]{"1", "2"});
	    frutas.put("amora", new String[]{"1", "1"});
	    frutas.put("goiaba", new String[]{"1", "1"});
		return frutas;
	}
	
	public void reiniciarAtributos() {
		this.nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
	    this.dimensao = "6";
	    this.pedras = "7";
	   	this.frutas = FrutasPadrao(); 
	   	this.bichadas = "25";
	   	this.capacidadeMochila = "10";
	}
	
	public File getArquivo() {
        return arquivo;
    }

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public String getPedras() {
        return pedras;
    }

    public void setPedras(String pedras) {
        this.pedras = pedras;
    }

    public LinkedHashMap<String, String[]> getFrutas() {
        return frutas;
    }

    public void setFrutas(LinkedHashMap<String, String[]> frutas) {
        this.frutas = frutas;
    }

    public String getBichadas() {
        return bichadas;
    }

    public void setBichadas(String bichadas) {
        this.bichadas = bichadas;
    }

    public String getCapacidadeMochila() {
        return capacidadeMochila;
    }

    public void setCapacidadeMochila(String capacidadeMochila) {
        this.capacidadeMochila = capacidadeMochila;
    }




}
