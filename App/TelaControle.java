package App;

import javax.swing.*;
import java.awt.*;

public class TelaControle {
    private JFrame telaControle;

    public TelaControle() {
        iniciarControle();
    }

    private void iniciarControle() {
        telaControle = new JFrame("Controles");
        telaControle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaControle.setSize(400, 400);
        telaControle.setLayout(new GridLayout(3, 3)); // Define um layout 3x3

        // Adiciona as setas
        ImageIcon setaCima = new ImageIcon(Menu.class.getResource("/buttons/seta_cima.png"));
        ImageIcon setaEsq = new ImageIcon(Menu.class.getResource("/buttons/seta_esq.png"));
        ImageIcon setaDir = new ImageIcon(Menu.class.getResource("/buttons/seta_dir.png"));
        ImageIcon setaBaixo = new ImageIcon(Menu.class.getResource("/buttons/seta_baixo.png"));
        // ImageIcon mochilaIcon = new ImageIcon(Menu.class.getResource("/imagens/mochila.png"));

        // Adicionando as setas aos quadrantes
        telaControle.add(new JLabel(setaEsq)); // 4º quadrante
        telaControle.add(new JLabel(setaCima)); // 2º quadrante
        telaControle.add(new JLabel(setaDir)); // 6º quadrante
        telaControle.add(new JLabel(setaBaixo)); // 8º quadrante
        //telaControle.add(new JLabel(mochilaIcon)); // 5º quadrante

        // Adicionando labels vazias para preencher a grade
        for (int i = 0; i < 4; i++) {
            telaControle.add(new JLabel()); // Preencher quadrantes vazios
        }

        telaControle.setVisible(true);
    }
}
