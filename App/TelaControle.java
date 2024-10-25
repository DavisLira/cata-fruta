package App;

import javax.swing.*;

import Frutas.Fruta;
import Jogador.Jogador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaControle {
	private JButton mochilaButton, setaCimaButton, setaEsqButton ,setaDirButton, setaBaixoButton;
    private JFrame telaControle;
    private JPanel painelControle;
    private JFrame telaMochila;
    private boolean mochilaAberta = false;
    private Jogador jogadorAtual;
    private Jogador jogadorProx;
    private TelaJogo jogo;

    public TelaControle(Jogador jogadorAtual, Jogador jogadorProx, TelaJogo jogo) {
    	System.out.println("Movimentos inicial: " + jogadorAtual.getMovimento());
    	this.jogadorAtual = jogadorAtual;
    	this.jogadorProx = jogadorProx;
    	this.jogo = jogo;
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

        mochilaButton = criarBotaoControle("/Controles/mochila_ctrl.png", 100, 100, 100, 100);

        setaCimaButton = criarBotaoControle("/Controles/seta_cima.png", 100, 100, 100, 0);
        setaEsqButton = criarBotaoControle("/Controles/seta_esq.png", 100, 100, 0, 100);
        setaDirButton = criarBotaoControle("/Controles/seta_dir.png", 100, 100, 200, 100);
        setaBaixoButton = criarBotaoControle("/Controles/seta_baixo.png", 100, 100, 100, 200);

        // Adicionar os botões ao painel
        painelControle.add(mochilaButton);
        painelControle.add(setaCimaButton);
        painelControle.add(setaEsqButton);
        painelControle.add(setaDirButton);
        painelControle.add(setaBaixoButton);

    	
        // Adicionar listener para abrir outra tela ao clicar na mochila
        mochilaButton.addActionListener(e -> {
            alternarMochila(jogadorAtual); // Abre ou fecha a mochila
            atualizarEstadoSetas(); // Atualiza as setas de acordo com o estado da mochila
        });
        
        configurarControles();
        

        telaControle.add(painelControle, BorderLayout.CENTER);
        telaControle.pack();
        telaControle.setVisible(true);
    }
    
    public void configurarControles() {
        // Exemplo para o botão de mover para cima
        setaCimaButton.addActionListener(e -> moverJogador(new Point(0, -1))); // Clique do botão
        setaEsqButton.addActionListener(e -> moverJogador(new Point(-1, 0)));
        setaDirButton.addActionListener(e -> moverJogador(new Point(1, 0)));
        setaBaixoButton.addActionListener(e -> moverJogador(new Point(0, 1)));
        // Key Binding para mover para cima
        InputMap inputMap = painelControle.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painelControle.getActionMap();

        // Mapeando teclas para mover para cima
        inputMap.put(KeyStroke.getKeyStroke("W"), "moverCima");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moverCima");
        actionMap.put("moverCima", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(0, -1)); // Move para cima
            }
        });

     // Mapeando teclas para mover para baixo
        inputMap.put(KeyStroke.getKeyStroke("S"), "moverBaixo");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moverBaixo");
        actionMap.put("moverBaixo", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(0, 1)); // Move para baixo
            }
        });

        // Mapeando teclas para mover para esquerda
        inputMap.put(KeyStroke.getKeyStroke("A"), "moverEsquerda");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moverEsquerda");
        actionMap.put("moverEsquerda", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(-1, 0)); // Move para esquerda
            }
        });

        // Mapeando teclas para mover para direita
        inputMap.put(KeyStroke.getKeyStroke("D"), "moverDireita");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moverDireita");
        actionMap.put("moverDireita", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(1, 0)); // Move para direita
            }
        });
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
    
    private void atualizarEstadoSetas() {
        if (!mochilaAberta) {
            // Se a mochila está fechada, os botões de seta estão ativos
            setaCimaButton.addActionListener(e -> moverJogador(new Point(0,-1)));
            setaEsqButton.addActionListener(e -> moverJogador(new Point(-1, 0)));
            setaDirButton.addActionListener(e -> moverJogador(new Point(1, 0)));
            setaBaixoButton.addActionListener(e -> moverJogador(new Point(0, 1)));
        } else {
            // Se a mochila está aberta, removemos os listeners
            for (ActionListener al : setaCimaButton.getActionListeners()) {
                setaCimaButton.removeActionListener(al);
            }
            for (ActionListener al : setaEsqButton.getActionListeners()) {
                setaEsqButton.removeActionListener(al);
            }
            for (ActionListener al : setaDirButton.getActionListeners()) {
                setaDirButton.removeActionListener(al);
            }
            for (ActionListener al : setaBaixoButton.getActionListeners()) {
                setaBaixoButton.removeActionListener(al);
            }
        }
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
    	int antigoX = jogadorAtual.getPosicao().x;
    	int antigoY = jogadorAtual.getPosicao().y;
    	
        int novoX = antigoX + destino.x;
        int novoY = antigoY + destino.y;
        
        // salva o mapa
        Object[][][] mapa = Menu.geracao.estadoMapa;

        // Verifique se a nova posição está dentro dos limites do mapa
        if (novoX < 0 || novoX >= mapa[0].length || novoY < 0 || novoY >= mapa.length) {
            System.out.println("Movimento inválido: fora dos limites do mapa!");
            return; // Impede o movimento se estiver fora dos limites
        }

        if (jogadorAtual.verificarMovimentos(new Point(novoX, novoY), 1, mapa)) {
            mapa[antigoY][antigoX][1] = null; // Remove o jogador da posição antiga
            jogadorAtual.mover(new Point(novoX, novoY), mapa); // Mover para a nova posição
            mapa[novoY][novoX][1] = jogadorAtual; // Colocar o jogador na nova posição
            jogo.atualizarMapa(mapa);
            System.out.println("Movimentos restantes: " + jogadorAtual.getMovimento());
        }
        
        if (jogadorAtual.acabouMovimentos()) {
        	telaControle.dispose();  // Fecha a tela atual
        	jogadorAtual.setMovimento();
        	new TelaControle(jogadorProx, jogadorAtual, jogo);
        }

    }
}
