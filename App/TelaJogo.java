package App;

import javax.swing.*;

import Floresta.Arvore;
import Floresta.Pedra;
import Frutas.Fruta;
import Jogador.Jogador;

import java.awt.*;

public class TelaJogo {
    private JFrame telaJogo;
    private JPanel painelJogo;
    private int matriz; // Tamanho da matriz
    private static int tamanhoImagem; // Tamanho de cada célula
    public Object[][][] estadoMapa; // Estado do mapa com objetos e jogadores

    public TelaJogo(Object[][][] estadoMapa) {
        this.estadoMapa = estadoMapa;
        iniciarJogo(); // Inicia a tela do jogo com base no estadoMapa
    }

    public void iniciarJogo() {
        telaJogo = new JFrame("Tela de Jogo");
        telaJogo.setUndecorated(true); // Remove as bordas da janela

        // Define as dimensões da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8); // Usa 80% da altura da tela

        matriz = estadoMapa.length; // Obtém o tamanho da matriz do estadoMapa
        tamanhoImagem = alturaTela / matriz; // Calcula o tamanho de cada célula com base na altura disponível
        int tamanhoTela = tamanhoImagem * matriz;

        // Configura a janela para centralizá-la na tela
        telaJogo.setBounds(0, 0, tamanhoTela, tamanhoTela);
        telaJogo.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
        telaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaJogo.setResizable(false);

        // Cria o painel de jogo e define seu tamanho e layout
        painelJogo = new JPanel() {
            private static final long serialVersionUID = 1L;

            // Sobrescreve o método paintComponent para desenhar o mapa do jogo
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gerarMapa(g, matriz); // Desenha o mapa
            }
        };

        painelJogo.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));
        painelJogo.setLayout(null);

        telaJogo.add(painelJogo, BorderLayout.CENTER);
        telaJogo.pack();
        telaJogo.setVisible(true); // Mostra a tela do jogo
    }

    // Método para desenhar o mapa baseado no estado atual do mapa
    private void gerarMapa(Graphics g, int matriz) {
    	// carrega as imagens
        ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
    	ImageIcon pedraIcon = new ImageIcon(Menu.class.getResource("/sprites/pedra.jpg"));
    	ImageIcon pedra2Icon = new ImageIcon(Menu.class.getResource("/sprites/pedra2.jpg"));
        ImageIcon jogador1Icon = new ImageIcon(Menu.class.getResource("/sprites/player1.jpg"));
        ImageIcon jogador2Icon = new ImageIcon(Menu.class.getResource("/sprites/player2.jpg"));
    	
        Image gramaImg = gramaIcon.getImage();
        Image jogador1Img = jogador1Icon.getImage();
        Image jogador2Img = jogador2Icon.getImage();
        Image pedraImg = pedraIcon.getImage();
        Image pedra2Img = pedra2Icon.getImage();

    	
        for (int y = 0; y < matriz; y++) {
            for (int x = 0; x < matriz; x++) {
                Object tipoElemento = estadoMapa[y][x][0];
                Jogador jogador = (Jogador) estadoMapa[y][x][1];

                // Desenhar o tipo de elemento no mapa
                if (tipoElemento != null) {
                    if (tipoElemento instanceof Floresta.Grama) {
                        colocarElemento(g, gramaImg, new Point(x, y));
                        
                    } else if (tipoElemento instanceof Floresta.Pedra) {
                    	Pedra elementoPedra = (Pedra) tipoElemento;
                    	if (elementoPedra.getTipo() == 0) 
                    		colocarElemento(g, pedraImg, new Point(x, y));
                    	if (elementoPedra.getTipo() == 1)
                    		colocarElemento(g, pedra2Img, new Point(x, y));
                    	
                    } else if (tipoElemento instanceof Floresta.Arvore) {
                    	Arvore elementoArvore = (Arvore) tipoElemento;
                    	ImageIcon icon = new ImageIcon(Menu.class.getResource(elementoArvore.getImg()));
                        Image img = icon.getImage();
                		colocarElemento(g, img, new Point(x, y));
                		
                    } else if (tipoElemento instanceof Frutas.Fruta) {
                    	Fruta elementoFruta = (Fruta) tipoElemento;
                    	ImageIcon icon = new ImageIcon(Menu.class.getResource(elementoFruta.getImg()));
                        Image img = icon.getImage();
                		colocarElemento(g, img, new Point(x, y));
                    }
                }

                // Desenhar o jogador, se houver
                if (jogador != null) {
                	if (jogador.getNumero() == 1)
                		colocarElemento(g, jogador1Img, new Point(x,y));

                	if (jogador.getNumero() == 2)
                		colocarElemento(g, jogador2Img, new Point(x,y));
                }
            }
        }
    }
    
    public static void colocarElemento(Graphics g, Image imagem, Point posicao) {
        int x = posicao.x * tamanhoImagem;
        int y = posicao.y * tamanhoImagem;
        g.drawImage(imagem, x, y, tamanhoImagem, tamanhoImagem, null);
    }
    
    public void atualizarMapa(Object[][][] novoEstadoMapa) {
        this.estadoMapa = novoEstadoMapa; // Atualiza o estado do mapa
        painelJogo.repaint(); // Redesenha o painel com o novo estado do mapa
    }

	public void dispose() {
		telaJogo.dispose();
	}
}
