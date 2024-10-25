package App;

import javax.swing.*;
import java.awt.*;

public class TelaBotoes {

    private JFrame telaBotoes;

    public TelaBotoes(TelaGeracao telaGeracao, JPanel painelJogo) {
        criarJanelaBotoes(telaGeracao, painelJogo);
    }

    private void criarJanelaBotoes(TelaGeracao telaGeracao, JPanel painelJogo) {
        // Definir as dimensões da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        telaBotoes = new JFrame("Opções");
        telaBotoes.setBounds(0, 0, screenSize.width, 44);
        telaBotoes.setLocation((screenSize.width - 770) / 2, 10);
        telaBotoes.setResizable(false);
        telaBotoes.setUndecorated(true);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());

        JButton iniciarButton = criarBotao("/buttons/iniciar.png", 220, 44, e -> {
            // Chamar a função para utilizar a matriz antes de iniciar o controle
            telaBotoes.dispose();
            telaGeracao.dispose();
            Menu.iniciarJogo();
        });

        JButton gerarNovamenteButton = criarBotao("/buttons/gerar_nvmt.png", 220, 44, e -> painelJogo.repaint());

        JButton voltarMenuButton = criarBotao("/buttons/voltar_menu.png", 220, 44, e -> {
            telaBotoes.dispose();  // Fecha a janela dos botões
            telaGeracao.voltarParaMenu();  // Volta ao menu principal
        });

        painelBotoes.add(iniciarButton);
        painelBotoes.add(gerarNovamenteButton);
        painelBotoes.add(voltarMenuButton);

        telaBotoes.add(painelBotoes, BorderLayout.CENTER);
        telaBotoes.pack();
        telaBotoes.setVisible(true);
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
