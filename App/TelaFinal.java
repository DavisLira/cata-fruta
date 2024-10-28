package App;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável por exibir a tela de vitória do jogador.
 */
public class TelaFinal {

    private JFrame telaFinal;

    /**
     * Construtor que inicializa a tela final com a imagem do vencedor.
     *
     * @param jogadorVencedor Número do jogador que venceu (1 ou 2)
     */
    public TelaFinal(int jogadorVencedor) {
        criarTelaFinal(jogadorVencedor);
    }

    /**
     * Cria a janela com a imagem de vitória do jogador.
     *
     * @param jogadorVencedor Número do jogador que venceu
     */
    private void criarTelaFinal(int jogadorVencedor) {
        // Definir a imagem com base no jogador vencedor
        String caminhoImagem = jogadorVencedor == 1 ? "/sprites/j1_venceu.png" : "/sprites/j2_venceu.png";

        // Criar a janela
        telaFinal = new JFrame("Resultado Final");
        telaFinal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaFinal.setResizable(false);

        // Criar o painel que contém a imagem de fundo
        JPanel painelFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Menu.class.getResource(caminhoImagem));
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        painelFundo.setLayout(null); // Layout nulo para desenhar imagem
        painelFundo.setPreferredSize(new Dimension(520, 256)); // Tamanho da janela

        // Adicionar o painel à janela
        telaFinal.add(painelFundo);
        telaFinal.pack();
        telaFinal.setLocationRelativeTo(null); // Centralizar a janela
        telaFinal.setVisible(true);
    }
}
