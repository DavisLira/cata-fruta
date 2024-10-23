package App;

import javax.swing.*;
import java.awt.*;

public class TelaBotoes {

    private JFrame janelaBotoes;

    public TelaBotoes(TelaJogo telaJogo, JPanel painelJogo, String[][][] estadoMapa) {
        criarJanelaBotoes(telaJogo, painelJogo, estadoMapa);
    }

    private void criarJanelaBotoes(TelaJogo telaJogo, JPanel painelJogo, String[][][] estadoMapa) {
        // Definir as dimensões da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        janelaBotoes = new JFrame("Opções");
        janelaBotoes.setBounds(0, 0, screenSize.width, 44);
        janelaBotoes.setLocation((screenSize.width - 770) / 2, 10);
        janelaBotoes.setResizable(false);
        janelaBotoes.setUndecorated(true);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton iniciarButton = criarBotao("/buttons/iniciar.png", 220, 44, e -> {
            // Chamar a função para utilizar a matriz antes de iniciar o controle
            telaJogo.utilizarMatriz(estadoMapa); // Chama a função da classe TelaJogo para utilizar a matriz
            janelaBotoes.dispose();
            new TelaControle(); // Abre a tela de controle ao iniciar o jogo
        });

        JButton gerarNovamenteButton = criarBotao("/buttons/gerar_nvmt.png", 220, 44, e -> painelJogo.repaint());

        JButton voltarMenuButton = criarBotao("/buttons/voltar_menu.png", 220, 44, e -> {
            janelaBotoes.dispose();  // Fecha a janela dos botões
            telaJogo.voltarParaMenu();  // Volta ao menu principal
        });

        painelBotoes.add(iniciarButton);
        painelBotoes.add(gerarNovamenteButton);
        painelBotoes.add(voltarMenuButton);

        janelaBotoes.add(painelBotoes, BorderLayout.CENTER);
        janelaBotoes.pack();
        janelaBotoes.setVisible(true);
    }

    private JButton criarBotao(String caminhoImagem, int largura, int altura, java.awt.event.ActionListener acao) {
        ImageIcon icon = new ImageIcon(Menu.class.getResource(caminhoImagem));
        Image image = icon.getImage();
        Image imageRedimensionada = image.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imageRedimensionada);

        JButton botao = new JButton(iconRedimensionado);
        botao.setText(null);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.addActionListener(acao);

        return botao;
    }
}
