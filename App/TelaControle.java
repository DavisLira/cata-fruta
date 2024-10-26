package App;

import javax.swing.*;

import Jogador.Jogador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaControle {
	private JButton mochilaButton, fimTurnoButton, setaCimaButton, setaEsqButton ,setaDirButton, setaBaixoButton;
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
        fimTurnoButton = criarBotaoControle("/Controles/fim_turno.png", 100, 100, 0, 0);

        setaCimaButton = criarBotaoControle("/Controles/seta_cima.png", 100, 100, 100, 0);
        setaEsqButton = criarBotaoControle("/Controles/seta_esq.png", 100, 100, 0, 100);
        setaDirButton = criarBotaoControle("/Controles/seta_dir.png", 100, 100, 200, 100);
        setaBaixoButton = criarBotaoControle("/Controles/seta_baixo.png", 100, 100, 100, 200);

        // Adicionar os botões ao painel
        painelControle.add(mochilaButton);
        painelControle.add(fimTurnoButton);
        painelControle.add(setaCimaButton);
        painelControle.add(setaEsqButton);
        painelControle.add(setaDirButton);
        painelControle.add(setaBaixoButton);

        mochilaButton.addActionListener(e -> {
            alternarMochila(jogadorAtual); // Alterna entre abrir ou fechar a mochila
            atualizarEstadoSetas(); // Atualiza as setas de acordo com o estado da mochila
        });
        
        fimTurnoButton.addActionListener(e -> {
            finalizarTurno();
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
        ImageIcon canto2 = new ImageIcon(Menu.class.getResource(jogadorAtual.getDado()));
        ImageIcon canto3 = new ImageIcon(Menu.class.getResource("/Controles/canto3.png"));
        ImageIcon canto4 = new ImageIcon(Menu.class.getResource(jogadorAtual.imgControle()));
    	
        Image canto2Img = canto2.getImage();
        Image canto3Img = canto3.getImage();
        Image canto4Img = canto4.getImage();
    	
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
            // Aqui você configura a tela da mochila, como já foi descrito
            telaMochila = new TelaMochila(jogador);
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

        // Calcula as novas coordenadas baseadas no movimento
        int novoX = antigoX + destino.x;
        int novoY = antigoY + destino.y;

        // Obtém o mapa atual
        Object[][][] mapa = Menu.geracao.estadoMapa;

        // Verifica se a nova posição está dentro dos limites do mapa
        if (novoX < 0 || novoX >= mapa[0].length || novoY < 0 || novoY >= mapa.length) {
            System.out.println("Movimento inválido: fora dos limites do mapa!");
            return; // Impede o movimento se estiver fora dos limites
        }

        // Cria o ponto de destino com as novas coordenadas
        Point novaPosicao = new Point(novoX, novoY);

        // Verifica se o jogador tem movimentos suficientes para se mover para o novo destino
        if (jogadorAtual.verificarMovimentos(novaPosicao, mapa)) {
            // Remove o jogador da posição antiga no mapa
            mapa[antigoY][antigoX][1] = null;

            // Move o jogador para a nova posição considerando o custo
            Point posicaoFinal = jogadorAtual.mover(novaPosicao, mapa);

            // Atualiza o mapa com a nova posição do jogador
            mapa[posicaoFinal.y][posicaoFinal.x][1] = jogadorAtual;
            jogo.atualizarMapa(mapa);

            // Força a repintura do painel para atualizar o canto 2
            painelControle.repaint();

            System.out.println("Movimentos restantes: " + jogadorAtual.getMovimento());
        } else {
            System.out.println("Movimento inválido: não há movimentos suficientes.");
        }

        // Verifica se os movimentos do jogador acabaram e alterna para o próximo jogador
        if (jogadorAtual.acabouMovimentos()) {
            finalizarTurno();
        }
    }


    public void finalizarTurno() {
        telaControle.dispose(); // Fecha a tela atual
        jogadorAtual.setMovimento(); // Rola novos movimentos para o próximo turno
        new TelaControle(jogadorProx, jogadorAtual, jogo); // Alterna para o próximo jogado
    }
}
