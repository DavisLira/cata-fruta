package App;

import javax.swing.*;

import Frutas.Fruta;
import Jogador.Jogador;

import java.awt.*;

public class TelaControle {
    private JFrame telaControle;
    private JPanel painelControle;

    public TelaControle() {
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
        mochilaButton.addActionListener(e -> mostrarMochila(new Jogador("Davi")));
        setaCimaButton.addActionListener(e -> System.out.println("Subindo"));
        setaEsqButton.addActionListener(e -> System.out.println("Esquerda"));
        setaDirButton.addActionListener(e -> System.out.println("Direita"));
        setaBaixoButton.addActionListener(e -> System.out.println("Descendo"));


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
    
    public void mostrarMochila(Jogador jogador) {
        // Criar um novo JFrame para a mochila
        JFrame telaMochila = new JFrame("Mochila de " + jogador.getNome());

        // Definir as dimensões da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8);
        
        // Obter as frutas da mochila do jogador
        Fruta[] frutas = jogador.getMochila().getFrutas();
        int capacidade = frutas.length;
        
        int lado = (int) Math.ceil(Math.sqrt(capacidade));

        int tamanhoImagem = alturaTela / lado; // Tamanho de cada imagem
        int tamanhoTela = tamanhoImagem * lado; // Tamanho total da tela

        telaMochila.setBounds(0, 0, tamanhoTela, tamanhoTela);
        telaMochila.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
        telaMochila.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaMochila.setResizable(true);

        // Painel que vai conter as frutas
        JPanel painelFrutas = new JPanel(null);
        painelFrutas.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));

        // Iterar pelas frutas e adicionar ao painel
        for (int i = 0; i < lado * lado; i++) {
            JButton frutaButton;

            if (i < capacidade && frutas[i] != null) {
                // Se a fruta existir, usar o sprite retornado por frutas[i].getImg()
                ImageIcon frutaIcon = new ImageIcon(Menu.class.getResource(frutas[i].getImg()));
                Image frutaImg = frutaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                ImageIcon frutaImgRedimensionada = new ImageIcon(frutaImg);

                frutaButton = new JButton(frutaImgRedimensionada);
            } else if (i < capacidade) {
                // Se não houver fruta, mas o espaço na mochila ainda está vazio, usar o sprite /sprites/Mochila.png
                ImageIcon mochilaIcon = new ImageIcon(Menu.class.getResource("/sprites/Mochila.png"));
                Image mochilaImg = mochilaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                ImageIcon mochilaImgRedimensionada = new ImageIcon(mochilaImg);

                frutaButton = new JButton(mochilaImgRedimensionada);
            } else {
                // Se não houver mais espaço na mochila, usar o sprite /sprites/grama.jpg
                ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
                Image gramaImg = gramaIcon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
                ImageIcon gramaImgRedimensionada = new ImageIcon(gramaImg);

                frutaButton = new JButton(gramaImgRedimensionada);
            }

            // Remover borda, texto, foco e fundo
            frutaButton.setText(null);
            frutaButton.setBorderPainted(false);
            frutaButton.setFocusPainted(false);
            frutaButton.setContentAreaFilled(false);
            
            // Calcular a posição do botão baseado no índice i
            int linha = i / lado;
            int coluna = i % lado;
            int x = coluna * tamanhoImagem;
            int y = linha * tamanhoImagem;

            // Definir posição e tamanho do botão
            frutaButton.setBounds(x, y, tamanhoImagem, tamanhoImagem);

            // Adicionar o botão ao painel
            painelFrutas.add(frutaButton);
        }

        telaMochila.add(painelFrutas, BorderLayout.CENTER);
        telaMochila.pack();
        telaMochila.setVisible(true);
        telaMochila.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fechar apenas a janela da mochila
    }


}
