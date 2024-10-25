package App;

import javax.swing.*;

import Frutas.Fruta;
import Jogador.Jogador;

import java.awt.*;

public class TelaControle {
    private JFrame telaControle;
    private JPanel painelControle;
    private JFrame telaMochila;
    private boolean mochilaAberta = false;
    private Jogador jogadorAtual;

    public TelaControle(Jogador jogador) {
    	this.jogadorAtual = jogador;
        iniciarControle();
    }

    private void iniciarControle() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        telaControle = new JFrame("Controles");
        telaControle.setUndecorated(true);
        telaControle.setBounds(0, 0, 300, 300);
        telaControle.setLocation(30, (screenSize.height - 300) / 2);
        telaControle.setResizable(false);
        
        painelControle = new JPanel(null) {
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gerarImagens(g, 3, 100);
            }
        };
        
        painelControle.setPreferredSize(new Dimension(300, 300));

        JButton mochilaButton = criarBotaoControle("/Controles/mochila_ctrl.png", 100, 100, 100, 100);

        JButton setaCimaButton = criarBotaoControle("/Controles/seta_cima.png", 100, 100, 100, 0);
        JButton setaEsqButton = criarBotaoControle("/Controles/seta_esq.png", 100, 100, 0, 100);
        JButton setaDirButton = criarBotaoControle("/Controles/seta_dir.png", 100, 100, 200, 100);
        JButton setaBaixoButton = criarBotaoControle("/Controles/seta_baixo.png", 100, 100, 100, 200);

        // Adicionar os botões ao painel
        painelControle.add(mochilaButton);
        painelControle.add(setaCimaButton);
        painelControle.add(setaEsqButton);
        painelControle.add(setaDirButton);
        painelControle.add(setaBaixoButton);


        // Adicionar listener para abrir outra tela ao clicar na mochila
        mochilaButton.addActionListener(e -> alternarMochila(jogadorAtual));
        setaCimaButton.addActionListener(e -> moverJogador(new Point(0,-1)));
        setaEsqButton.addActionListener(e -> moverJogador(new Point(-1, 0)));
        setaDirButton.addActionListener(e -> moverJogador(new Point(1, 0)));
        setaBaixoButton.addActionListener(e -> moverJogador(new Point(0,1)));


        telaControle.add(painelControle, BorderLayout.CENTER);
        telaControle.pack();
        telaControle.setVisible(true);
    }
    
    public JButton criarBotaoControle(String caminhoImagem, int largura, int altura, int x, int y) {
        // Carregar a imagem a partir do caminho fornecido
        ImageIcon icon = new ImageIcon(Menu.class.getResource(caminhoImagem));
        Image img = icon.getImage();
        
        // Redimensionar a imagem para o tamanho desejado
        Image imgRedimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
        
        // Criar o botão com o ícone redimensionado
        JButton botao = new JButton(iconRedimensionado);
        botao.setText(null);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBounds(x, y, largura, altura);

        return botao;
    }


    private void gerarImagens(Graphics g, int matriz, int tamanhoImagem) {
        ImageIcon canto1 = new ImageIcon(Menu.class.getResource("/Controles/canto1.png"));
        ImageIcon canto2 = new ImageIcon(Menu.class.getResource("/Controles/canto2.png"));
        ImageIcon canto3 = new ImageIcon(Menu.class.getResource("/Controles/canto3.png"));
        ImageIcon canto4 = new ImageIcon(Menu.class.getResource("/Controles/canto4.png"));
    	
        Image canto1Img = canto1.getImage();
        Image canto2Img = canto2.getImage();
        Image canto3Img = canto3.getImage();
        Image canto4Img = canto4.getImage();
    	
        colocarElemento(g, canto1Img, new Point(0,0), tamanhoImagem);
        colocarElemento(g, canto2Img, new Point(2,0), tamanhoImagem);
        colocarElemento(g, canto3Img, new Point(0,2), tamanhoImagem);
        colocarElemento(g, canto4Img, new Point(2,2), tamanhoImagem);
    }

    public static void colocarElemento(Graphics g, Image imagem, Point posicao, int tamanho) {
        int x = posicao.x * tamanho;
        int y = posicao.y * tamanho;
        g.drawImage(imagem, x, y, tamanho, tamanho, null);
    }
    
    public void alternarMochila(Jogador jogador) {
        if (mochilaAberta) {
            fecharMochila();
        } else {
            abrirMochila(jogador);
        }
    }
    
    public void abrirMochila(Jogador jogador) {
        if (telaMochila == null) {
            telaMochila = new JFrame("Mochila do jogador " + jogador.getNumero());

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int alturaTela = (int) (screenSize.height * 0.8);

            Fruta[] frutas = jogador.getMochila().getFrutas();
            int capacidade = frutas.length;

            int lado = (int) Math.ceil(Math.sqrt(capacidade));
            int tamanhoImagem = alturaTela / lado;
            int tamanhoTela = tamanhoImagem * lado;

            telaMochila.setBounds(0, 0, tamanhoTela, tamanhoTela);
            telaMochila.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
            telaMochila.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            telaMochila.setResizable(true);
            telaMochila.setUndecorated(true);

            JPanel painelFrutas = new JPanel(null);
            painelFrutas.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));

            for (int i = 0; i < lado * lado; i++) {
                JButton frutaButton;

                if (i < capacidade && frutas[i] != null) {
                    ImageIcon frutaIcon = new ImageIcon(Menu.class.getResource(frutas[i].getImgMochila()));
                    Image frutaImg = frutaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                    frutaButton = new JButton(new ImageIcon(frutaImg));
                } else if (i < capacidade) {
                    ImageIcon mochilaIcon = new ImageIcon(Menu.class.getResource("/sprites/Mochila.png"));
                    Image mochilaImg = mochilaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                    frutaButton = new JButton(new ImageIcon(mochilaImg));
                } else {
                    ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
                    Image gramaImg = gramaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                    frutaButton = new JButton(new ImageIcon(gramaImg));
                }

                frutaButton.setText(null);
                frutaButton.setBorderPainted(false);
                frutaButton.setFocusPainted(false);
                frutaButton.setContentAreaFilled(false);

                int linha = i / lado;
                int coluna = i % lado;
                int x = coluna * tamanhoImagem;
                int y = linha * tamanhoImagem;
                frutaButton.setBounds(x, y, tamanhoImagem, tamanhoImagem);
                painelFrutas.add(frutaButton);
            }

            telaMochila.add(painelFrutas, BorderLayout.CENTER);
            telaMochila.pack();
        }

        telaMochila.setVisible(true);
        mochilaAberta = true;
    }
    
    public void fecharMochila() {
        if (telaMochila != null) {
            telaMochila.setVisible(false);
        }
        mochilaAberta = false;
    }

    private void moverJogador(Point destino) {
        int movimentos = 6;
    	// Calcula a nova posição
        
        int novoX = jogadorAtual.getPosicao().x + destino.x;
        int novoY = jogadorAtual.getPosicao().y + destino.y;
        
        Object[][][] mapa = Menu.geracao.estadoMapa;

        // Verifique se a nova posição está dentro dos limites do mapa
        if (novoX < 0 || novoX >= mapa.length || novoY < 0 || novoY >= mapa[0].length) {
            System.out.println("Movimento inválido: fora dos limites do mapa.");
            return; // Impede o movimento se estiver fora dos limites
        }

        jogadorAtual.setPosicao(new Point(novoX, novoY));
        System.out.println("Movimento realizado para: (" + novoX + ", " + novoY + ")");
    }
}
