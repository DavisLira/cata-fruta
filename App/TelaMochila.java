package App;

import javax.swing.*;

import Frutas.Fruta;
import Jogador.Jogador;

import java.awt.*;

public class TelaMochila extends JFrame {
	private static final long serialVersionUID = 1L;

	public TelaMochila(Jogador jogador) {
        super("Mochila do jogador " + jogador.getNumero());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8);

        Fruta[] frutas = jogador.getMochila().getFrutas();
        int capacidade = frutas.length;

        int lado = (int) Math.ceil(Math.sqrt(capacidade));
        int tamanhoImagem = alturaTela / lado;
        int tamanhoTela = tamanhoImagem * lado;

        this.setBounds(0, 0, tamanhoTela, tamanhoTela);
        this.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);

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

        this.add(painelFrutas, BorderLayout.CENTER);
        this.pack();
    }

    public void exibir() {
        this.setVisible(true);
    }
}
