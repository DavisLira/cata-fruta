package Arquivo;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Arquivo {
	private File arquivo;
	private String nomeArquivo;
    private int dimensao;
    private int pedras;
    private LinkedHashMap<String, int[]> frutas = new LinkedHashMap<>(); 
    private int bichadas = 0;
    private int capacidadeMochila = 0;
    
    //para a classe de escrever o arquivo
	public Arquivo( String nomeArquivo,
					int dimensao,
					int pedras,
					LinkedHashMap<String, int[]> frutas,
					int bichadas,
					int capacidadeMochila)
	{
		this.nomeArquivo = nomeArquivo;
		this.dimensao = dimensao;
		this.pedras = pedras;
		this.frutas = frutas;
		this.bichadas = bichadas;
		this.capacidadeMochila = capacidadeMochila;
		
	}
	
	//para a classe ler o arquivo do usuario
    public Arquivo(File arquivo) {
    	this.arquivo = arquivo;
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

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public int getPedras() {
        return pedras;
    }

    public void setPedras(int pedras) {
        this.pedras = pedras;
    }

    public HashMap<String, int[]> getFrutas() {
        return frutas;
    }

    public void setFrutas(LinkedHashMap<String, int[]> frutas) {
        this.frutas = frutas;
    }

    public int getBichadas() {
        return bichadas;
    }

    public void setBichadas(int bichadas) {
        this.bichadas = bichadas;
    }

    public int getCapacidadeMochila() {
        return capacidadeMochila;
    }

    public void setCapacidadeMochila(int capacidadeMochila) {
        this.capacidadeMochila = capacidadeMochila;
    }




}
