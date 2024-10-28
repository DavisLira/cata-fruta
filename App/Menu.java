package App;

import Arquivo.ArquivoHandler;
import Jogador.Jogador;

/**
 * A classe Menu representa a interface do menu inicial do jogo "Cata Frutas"
 * Ela fornece opções para iniciar o jogo, editar configurações e escolher arquivos de configuração
 */
public class Menu {
	public static ArquivoHandler arquivoHandler = new ArquivoHandler();
	public static TelaGeracao geracao;
	public static Jogador jogador1;
	public static Jogador jogador2;
	public static Jogador jogadorAtual = jogador1;
	private static int qtdMaracuja = arquivoHandler.getMaracujaTotal();
	
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
    	jogador1 = geracao.jogador1;
    	jogador2 = geracao.jogador2;
    	new TelaControle(jogador1, jogador2, new TelaJogo(geracao.estadoMapa), qtdMaracuja);
    }

}
