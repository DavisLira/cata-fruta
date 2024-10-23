package App;

import javax.swing.*;

import App.Menu;

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
        
        painelControle = new JPanel() {
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gerarControle(g, 3, 100);
            }
        };
        
        painelControle.setLayout(null);
        painelControle.setPreferredSize(new Dimension(300, 300));
        

        telaControle.add(painelControle, BorderLayout.CENTER);
        telaControle.pack();
        telaControle.setVisible(true);
    }
    

    private void gerarControle(Graphics g, int matriz, int tamanhoImagem) {
        ImageIcon canto1 = new ImageIcon(Menu.class.getResource("/Controles/canto1.png"));
        ImageIcon setaCima = new ImageIcon(Menu.class.getResource("/Controles/seta_cima.png"));
        ImageIcon canto2 = new ImageIcon(Menu.class.getResource("/Controles/canto2.png"));
        ImageIcon setaEsq = new ImageIcon(Menu.class.getResource("/Controles/seta_esq.png"));
        ImageIcon mochilaIcon = new ImageIcon(Menu.class.getResource("/Controles/mochila_ctrl.png"));
        ImageIcon setaDir = new ImageIcon(Menu.class.getResource("/Controles/seta_dir.png"));
        ImageIcon canto3 = new ImageIcon(Menu.class.getResource("/Controles/canto3.png"));
        ImageIcon setaBaixo = new ImageIcon(Menu.class.getResource("/Controles/seta_baixo.png"));
        ImageIcon canto4 = new ImageIcon(Menu.class.getResource("/Controles/canto4.png"));
    	
        
        Image canto1Img = canto1.getImage();
        Image setaCimaImg = setaCima.getImage();
        Image canto2Img = canto2.getImage();
        Image setaEsqImg = setaEsq.getImage();
        Image mochilaIconImg = mochilaIcon.getImage();
        Image setaDirImg = setaDir.getImage();
        Image canto3Img = canto3.getImage();
        Image setaBaixoImg = setaBaixo.getImage();
        Image canto4Img = canto4.getImage();
    	
        colocarElemento(g, canto1Img, new Point(0,0), tamanhoImagem);
        colocarElemento(g, setaCimaImg, new Point(1,0), tamanhoImagem);
        colocarElemento(g, canto2Img, new Point(2,0), tamanhoImagem);
        colocarElemento(g, setaEsqImg, new Point(0,1), tamanhoImagem);
        colocarElemento(g, mochilaIconImg, new Point(1,1), tamanhoImagem);
        colocarElemento(g, setaDirImg, new Point(2,1), tamanhoImagem);
        colocarElemento(g, canto3Img, new Point(0,2), tamanhoImagem);
        colocarElemento(g, setaBaixoImg, new Point(1,2), tamanhoImagem);
        colocarElemento(g, canto4Img, new Point(2,2), tamanhoImagem);
    }
    
    public static void colocarElemento(Graphics g, Image imagem, Point posicao, int tamanho) {
        int x = posicao.x * tamanho;
        int y = posicao.y * tamanho;
        g.drawImage(imagem, x, y, tamanho, tamanho, null);
    }
}
