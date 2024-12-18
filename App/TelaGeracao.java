package App;

import javax.swing.*;

import Floresta.Arvore;
import Floresta.Grama;
import Floresta.Pedra;
import Frutas.Abacate;
import Frutas.Amora;
import Frutas.Banana;
import Frutas.Coco;
import Frutas.Goiaba;
import Frutas.Laranja;
import Frutas.Maracuja;
import Jogador.Jogador;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Inicia a interface do jogo e exibe a tela de jogo.
 * Cria uma janela com um painel para desenhar o mapa e botões para interagir com o jogo.
 */

public class TelaGeracao {

    private JFrame telaGeracao;
    JPanel painelJogo;
    private int matriz;
    private int tamanhoImagem;
    public Jogador jogador1;
    public Jogador jogador2;
    Object[][][] estadoMapa; // Matriz para armazenar o tipo de local e jogadores
    Random random = new Random();

    /**
     * Construtor que inicializa a geração da tela
     */
    public TelaGeracao() {
        iniciarGeracao();
    }
    
    /**
     * Inicia a tela de geração do mapa e configura as propriedades da janela
     */
    public void iniciarGeracao() {
        telaGeracao = new JFrame("Geração de mapa");
        telaGeracao.setUndecorated(true);

        // Definir as dimensões da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8);

        matriz = Menu.arquivoHandler.getDimensao();
        tamanhoImagem = alturaTela / matriz;
        estadoMapa = new Object[matriz][matriz][2]; // Inicializa a matriz com o tamanho apropriado

        int tamanhoTela = tamanhoImagem * matriz;

        // Ajuste o tamanho da janela do jogo de acordo com as dimensões do monitor
        telaGeracao.setBounds(0, 0, tamanhoTela, tamanhoTela);
        telaGeracao.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
        telaGeracao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaGeracao.setResizable(false);

        painelJogo = new JPanel() {
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gerarMapa(g, matriz, tamanhoImagem);
            }
        };

        painelJogo.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));
        painelJogo.setLayout(null);

        telaGeracao.add(painelJogo, BorderLayout.CENTER);
        telaGeracao.pack();
        telaGeracao.setVisible(true);
    }
    
    /**
     * Utiliza a matriz do estado do mapa para exibir informações de cada posição
     *
     * @param estadoMapa Matriz representando o estado do mapa, incluindo tipo e jogadores
     */
    public void utilizarMatriz(Object[][][] estadoMapa) {
        // Lógica para utilizar a matriz
        for (int i = 0; i < estadoMapa.length; i++) {
            for (int j = 0; j < estadoMapa[i].length; j++) {
                System.out.println("Posição: (" + i + ", " + j + ") - Tipo: " + estadoMapa[i][j][0] + ", Jogador: " + estadoMapa[i][j][1]);
            }
        }
    }
    
    /**
     * Obtém a dimensão da matriz do mapa
     *
     * @return Dimensão da matriz do mapa
     */
    public int getMatriz() {
    	return this.matriz;
    }
    
    /**
     * Fecha a tela de geração
     */
    public void dispose() {
    	telaGeracao.dispose();
    }
    
    /**
     * Retorna para o menu inicial ao fechar a tela de geração
     */
    public void voltarParaMenu() {
        telaGeracao.dispose();
        new TelaMenuInicial(); // Retorna ao menu inicial
    }
    
    /**
     * Gera o mapa do jogo, desenhando os elementos na tela usando gráficos
     *
     * Este método preenche o mapa com grama e posiciona aleatoriamente
     * os jogadores, diferentes tipos de frutas e pedras de acordo com
     * as configurações especificadas. Ele utiliza um conjunto de pontos
     * para garantir que não haja sobreposição de elementos no mapa
     *
     * O método carrega as imagens necessárias a partir dos recursos
     * do aplicativo, desenha o fundo do mapa e distribui os elementos
     * com base nas quantidades definidas em um arquivo de configuração
     *
     * @param g Objeto Graphics utilizado para desenhar as imagens no mapa
     * @param matriz Dimensão do mapa, representando o número de linhas e colunas
     * @param tamanhoImagem Tamanho de cada imagem a ser desenhada no mapa
     */
    private void gerarMapa(Graphics g, int matriz, int tamanhoImagem) {
    	// carrega as imagens
        ImageIcon abacateIcon = new ImageIcon(Menu.class.getResource("/sprites/abacate.jpg"));
        ImageIcon abacateiraIcon = new ImageIcon(Menu.class.getResource("/sprites/abacateiro.jpg"));
        ImageIcon amoraIcon = new ImageIcon(Menu.class.getResource("/sprites/amora.jpg"));
        ImageIcon amoreiraIcon = new ImageIcon(Menu.class.getResource("/sprites/amoreira.jpg"));
        ImageIcon bananaIcon = new ImageIcon(Menu.class.getResource("/sprites/banana.jpg"));
        ImageIcon bananeiraIcon = new ImageIcon(Menu.class.getResource("/sprites/bananeira.jpg"));
        ImageIcon cocoIcon = new ImageIcon(Menu.class.getResource("/sprites/coco.jpg"));
        ImageIcon coqueiroIcon = new ImageIcon(Menu.class.getResource("/sprites/coqueiro.jpg"));
        ImageIcon goiabaIcon = new ImageIcon(Menu.class.getResource("/sprites/goiaba.jpg"));
        ImageIcon goiabeiraIcon = new ImageIcon(Menu.class.getResource("/sprites/goiabeira.jpg"));
        ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
        ImageIcon laranjaIcon = new ImageIcon(Menu.class.getResource("/sprites/laranja.jpg"));
        ImageIcon laranjeiraIcon = new ImageIcon(Menu.class.getResource("/sprites/laranjeira.jpg"));
        ImageIcon maracujaIcon = new ImageIcon(Menu.class.getResource("/sprites/maracuja.jpg"));
    	ImageIcon pedraIcon = new ImageIcon(Menu.class.getResource("/sprites/pedra.jpg"));
    	ImageIcon pedra2Icon = new ImageIcon(Menu.class.getResource("/sprites/pedra2.jpg"));
        ImageIcon jogador1Icon = new ImageIcon(Menu.class.getResource("/sprites/player1.jpg"));
        ImageIcon jogador2Icon = new ImageIcon(Menu.class.getResource("/sprites/player2.jpg"));
    	
        Image abacateImg = abacateIcon.getImage();
        Image abacateiraImg = abacateiraIcon.getImage();
        Image amoraImg = amoraIcon.getImage();
        Image amoreiraImg = amoreiraIcon.getImage();
        Image bananaImg = bananaIcon.getImage();
        Image bananeiraImg = bananeiraIcon.getImage();
        Image cocoImg = cocoIcon.getImage();
        Image coqueiroImg = coqueiroIcon.getImage();
        Image goiabaImg = goiabaIcon.getImage();
        Image goiabeirImg = goiabeiraIcon.getImage();
        Image gramaImg = gramaIcon.getImage();
        Image jogador1Img = jogador1Icon.getImage();
        Image jogador2Img = jogador2Icon.getImage();
        Image laranjaImg = laranjaIcon.getImage();
        Image laranjeiraImg = laranjeiraIcon.getImage();
        Image maracujaImg = maracujaIcon.getImage();
        Image pedraImg = pedraIcon.getImage();
        Image pedra2Img = pedra2Icon.getImage();

        // Desenha o mapa todo de grama
        for (int i = 0; i < matriz; i++) {
            for (int j = 0; j < matriz; j++) {
                int x = j * tamanhoImagem;
                int y = i * tamanhoImagem;
                g.drawImage(gramaImg, x, y, tamanhoImagem, tamanhoImagem, null);
                estadoMapa[i][j][0] = new Grama(); // Inicializa o estado como "grama"
                estadoMapa[i][j][1] = null; // Sem jogadores
            }
        }

        List<Point> posicoes = gerarPosicoesAleatorias(matriz);

        Point posicaoJogador1 = posicoes.remove(0);
        colocarElemento(g, jogador1Img, posicaoJogador1, tamanhoImagem);
        jogador1 = new Jogador(1, posicaoJogador1);
        estadoMapa[(int) posicaoJogador1.getY()][(int) posicaoJogador1.getX()][1] = jogador1; // Atualiza o estado do jogador
        
        Point posicaoJogador2 = posicoes.remove(0);
        colocarElemento(g, jogador2Img, posicaoJogador2, tamanhoImagem);
        jogador2 = new Jogador(2, posicaoJogador2);
        estadoMapa[(int) posicaoJogador2.getY()][(int) posicaoJogador2.getX()][1] = jogador2; // Atualiza o estado do jogador

        for (int i = 0; i < (Menu.arquivoHandler.getAbacate()); i++) {
            Point posicao = posicoes.remove(0);
            colocarElemento(g, abacateImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Abacate(posicao,bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getAbacateiras()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, abacateiraImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Abacate(bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getAmora()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, amoraImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Amora(posicao,bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getAmoreira()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, amoreiraImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Amora(bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getBanana()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, bananaImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Banana(posicao, bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getBananeira()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, bananeiraImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Banana(bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getCoco()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, cocoImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Coco(posicao, bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getCoqueiro()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, coqueiroImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Coco(bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getGoiaba()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, goiabaImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Goiaba(posicao, bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getGoiabeira()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, goiabeirImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Goiaba(bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getLaranja()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, laranjaImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Laranja(posicao, bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getLaranjeiras()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, laranjeiraImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Arvore(new Laranja(posicao, bichada));
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getMaracujaInicio()); i++) {
            Point posicao = posicoes.remove(0);
        	colocarElemento(g, maracujaImg, posicao, tamanhoImagem);
            
            int chance = random.nextInt(101);
            boolean bichada = (chance <= Menu.arquivoHandler.getBichadas());
            estadoMapa[(int) posicao.getY()][(int) posicao.getX()][0] = new Maracuja(posicao, bichada);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
            // Alterna entre pedraImg e pedraImg2 com base no índice
            Image pedra = (i % 2 == 0) ? pedraImg : pedra2Img;

            Point posicaoPedra = posicoes.remove(0);
            colocarElemento(g, pedra, posicaoPedra, tamanhoImagem);
            estadoMapa[(int) posicaoPedra.getY()][(int) posicaoPedra.getX()][0] = new Pedra(posicaoPedra, i % 2); // Atualiza o estado
        }
    }
    
    /**
     * Gera uma lista de posições aleatórias para o mapa
     *
     * @param matriz Dimensão do mapa
     * @return Lista de pontos aleatórios no mapa
     */
    private List<Point> gerarPosicoesAleatorias(int matriz) {
        java.util.Set<Point> posicoesSet = new java.util.HashSet<>();
        for (int i = 0; i < matriz; i++) {
            for (int j = 0; j < matriz; j++) {
                posicoesSet.add(new Point(j, i));
            }
        }

        List<Point> posicoes = new ArrayList<>(posicoesSet);
        Collections.shuffle(posicoes);
        return posicoes;
    }
    
    /**
     * Coloca uma imagem em uma posição específica na tela, redimensionando-a conforme necessário
     *
     * @param g Objeto Graphics usado para desenhar a imagem na tela
     * @param imagem Imagem a ser desenhada
     * @param posicao Ponto onde a imagem será desenhada
     * @param tamanho Tamanho (largura e altura) que a imagem deve ter ao ser desenhada
     */
    public static void colocarElemento(Graphics g, Image imagem, Point posicao, int tamanho) {
        int x = posicao.x * tamanho;
        int y = posicao.y * tamanho;
        g.drawImage(imagem, x, y, tamanho, tamanho, null);
    }
}
