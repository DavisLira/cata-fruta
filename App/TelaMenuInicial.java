package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Mostra o menu inicial do jogo
 * Cria uma janela com botões para iniciar o jogo, editar configurações,
 * escolher arquivos de configuração e baixar configurações
 */

public class TelaMenuInicial {

    private JFrame menuInicial;

    public TelaMenuInicial() {
        mostrarMenuInicial();
    }

    public void mostrarMenuInicial() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        menuInicial = new JFrame("Cata Frutas - Menu inicial");
        menuInicial.setBounds(0, 0, 500, 575);
        menuInicial.setLocation((screenSize.width - 500) / 2, (screenSize.height - 575) / 2);
        menuInicial.setLayout(null);
        menuInicial.setResizable(false);
        menuInicial.setUndecorated(true);

        // Painel com imagem de fundo
        JPanel painelComFundo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carrega a imagem de fundo
                ImageIcon backgroundIcon = new ImageIcon(Menu.class.getResource("/sprites/imagem-bg-menu.jpg"));
                Image backgroundImage = backgroundIcon.getImage();
                // Desenha a imagem de fundo
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        painelComFundo.setLayout(null);
        painelComFundo.setBounds(0, 0, 500, 575);

        // Cria o botão "Ler Configs"
        ImageIcon iconIniciar = new ImageIcon(Menu.class.getResource("/buttons/iniciar.png"));
        Image imageIniciar = iconIniciar.getImage();
        Image imageIniciarRedimensionada = imageIniciar.getScaledInstance(330, 66, Image.SCALE_SMOOTH);
        ImageIcon iconIniciarRedimensionado = new ImageIcon(imageIniciarRedimensionada);
        JButton botaoIniciar = new JButton(iconIniciarRedimensionado);
        botaoIniciar.setBounds(85, 40, 330, 66);
        botaoIniciar.setText(null);
        botaoIniciar.setBorderPainted(false);
        botaoIniciar.setFocusPainted(false);
        botaoIniciar.setContentAreaFilled(false);
        painelComFundo.add(botaoIniciar);

        // Cria o botão "Editar Configs"
        ImageIcon iconEditar = new ImageIcon(Menu.class.getResource("/buttons/editar_config.png"));
        Image imageEditar = iconEditar.getImage();
        Image imageEditarRedimensionada = imageEditar.getScaledInstance(330, 66, Image.SCALE_SMOOTH);
        ImageIcon iconEditarRedimensionado = new ImageIcon(imageEditarRedimensionada);
        JButton botaoEditar = new JButton(iconEditarRedimensionado);
        botaoEditar.setText(null);
        botaoEditar.setBorderPainted(false);
        botaoEditar.setFocusPainted(false);
        botaoEditar.setContentAreaFilled(false);
        botaoEditar.setBounds(85, 120, 330, 66);
        painelComFundo.add(botaoEditar);

        ImageIcon iconEscolher = new ImageIcon(Menu.class.getResource("/buttons/escolher_config.png"));
        Image imageEscolher = iconEscolher.getImage();
        Image imageEscolherRedimensionada = imageEscolher.getScaledInstance(330, 66, Image.SCALE_SMOOTH);
        ImageIcon iconEscolherRedimensionado = new ImageIcon(imageEscolherRedimensionada);
        JButton botaoEscolher = new JButton(iconEscolherRedimensionado);
        botaoEscolher.setText(null);
        botaoEscolher.setBorderPainted(false);
        botaoEscolher.setFocusPainted(false);
        botaoEscolher.setContentAreaFilled(false);
        botaoEscolher.setBounds(85, 275, 330, 66);
        painelComFundo.add(botaoEscolher);

        ImageIcon iconBaixar = new ImageIcon(Menu.class.getResource("/buttons/baixar_config.png"));
        Image imageBaixar = iconBaixar.getImage();
        Image imageBaixarRedimensionada = imageBaixar.getScaledInstance(330, 66, Image.SCALE_SMOOTH);
        ImageIcon iconBaixarRedimensionado = new ImageIcon(imageBaixarRedimensionada);
        JButton botaoBaixar = new JButton(iconBaixarRedimensionado);
        botaoBaixar.setText(null);
        botaoBaixar.setBorderPainted(false);
        botaoBaixar.setFocusPainted(false);
        botaoBaixar.setContentAreaFilled(false);
        botaoBaixar.setBounds(85, 400, 330, 66);
        painelComFundo.add(botaoBaixar);

        // Caminho do arquivo configs.txt
        String caminho = "arqs/configs.txt";

        // Ação do botão "Ler Configs"
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Menu.arquivoHandler.validarGeracaoMapa(20)) {
                    menuInicial.dispose();
                    Menu.gerarMapa(); // Certifique-se de que este método está em uma classe acessível
                } else {
                    JOptionPane.showMessageDialog(menuInicial, "Não foi possível gerar um mapa com essas configurações!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Editar Configs"
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.abrirFormularioEdicao(caminho); // Certifique-se de que este método está em uma classe acessível
            }
        });

        botaoEscolher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Escolher arquivo de configurações");

                int userSelection = fileChooser.showOpenDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        boolean resultado_arquivo = Menu.arquivoHandler.ler("arqs" + System.getProperty("file.separator") + selectedFile.getName());
                        if (resultado_arquivo) {
                            JOptionPane.showMessageDialog(menuInicial, "Arquivo de configurações substituído com sucesso!");
                        } else {
                            Menu.arquivoHandler.reiniciarAtributos();
                            JOptionPane.showMessageDialog(menuInicial, "Não é possível gerar um mapa com essas configurações!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(menuInicial, "Por favor, escolha um arquivo .txt no formato correto!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        botaoBaixar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.arquivoHandler.escreverNoArquivo(); // Certifique-se de que este método está em uma classe acessível
            }
        });

        // Adiciona o painel de fundo à janela
        menuInicial.setContentPane(painelComFundo);

        // Exibe a janela principal
        menuInicial.setVisible(true);
    }
}
