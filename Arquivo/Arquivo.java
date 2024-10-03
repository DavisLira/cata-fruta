package Arquivo;

import java.io.File;
import java.util.LinkedHashMap;

public class Arquivo {
	private File arquivo;
	private String nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
    private LinkedHashMap<String, String[]> Elementos = ElementosPadrao(); 

    //para a classe de escrever o arquivo
    public Arquivo() {
	}

   
	private LinkedHashMap<String, String[]> ElementosPadrao() {
	    LinkedHashMap<String, String[]> Elementos = new LinkedHashMap<>();
		
	    Elementos.put("dimensao", new String[]{"6"});
	    Elementos.put("pedras", new String[]{"7"});
	    Elementos.put("maracuja", new String[]{"3", "1"});
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
	
	public void setElemento(String Chave, String[] Valor) {
		this.Elementos.put(Chave, Valor);
	}
	
	public void reiniciarAtributos() {
		this.nomeArquivo = "arqs" + System.getProperty("file.separator") + "ConfigCataFruta.txt";
	   	this.Elementos = ElementosPadrao(); 
	}
	
	public File getArquivo() {
        return this.arquivo;
    }

	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public void setElementos(LinkedHashMap<String, String[]> Elementos) {
    	this.Elementos = Elementos;
    }
    
    public LinkedHashMap<String, String[]> getElementos() {
    	return this.Elementos;
    }
    
    public int getDimensao() {
        String[] valores = this.Elementos.get("dimensao");
		return Integer.parseInt(valores[0]);
    }
    
    public int getPedras() {
        String[] valores = this.Elementos.get("pedras");
		return Integer.parseInt(valores[0]);
    }
    
    public int getBichadas() {
        String[] valores = this.Elementos.get("bichadas");
		return Integer.parseInt(valores[0]);
    }
    
    public int getMochila() {
        String[] valores = this.Elementos.get("mochila");
		return Integer.parseInt(valores[0]);
    }
    
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
    
    public static void main(String[] args) {
    	Arquivo a = new Arquivo();
    	int ab = a.getPedras();
    	System.out.println(ab);
    }
    
    

}
