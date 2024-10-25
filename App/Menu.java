package App;

import Arquivo.ArquivoHandler;

/**
 * A classe Menu representa a interface do menu inicial do jogo "Cata Frutas"
 * Ela fornece opções para iniciar o jogo, editar configurações e escolher arquivos de configuração
 */
public class Menu {
	public static ArquivoHandler arquivoHandler = new ArquivoHandler();
	static TelaGeracao geracao;
	
	/**
     * O ponto de entrada principal do programa
     * Exibe o menu inicial do jogo
     *
     * @param args Os argumentos da linha de comando
     */
	
	public static void main(String[] args) {
        new TelaMenuInicial(); // Mostra o menu inicial
    }

    // Método estático que pode ser chamado a partir de outros lugares para iniciar o jogo
    public static void gerarMapa() {
        geracao = new TelaGeracao();
    	new TelaBotoes(geracao, geracao.painelJogo);
    }
    
    public static void abrirFormularioEdicao(String caminho) {
        new TelaFormularioConfiguracao(caminho); // Abre o formulário de edição
    }
    
    public static void iniciarJogo() {
    	geracao.utilizarMatriz(geracao.estadoMapa);
    	new TelaJogo(geracao.estadoMapa); // mudar para usar classe jogo e passar o a matriz salva
    }

}
