package App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.*;


public class Menu {

    public static void main(String[] args) {

        JFrame tela = new JFrame("Cata Frutas - Menu inicial");
        tela.setBounds(500, 50, 500, 600);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);

        // Cria o botão "Ler Configs"
        JButton botaoIniciar = new JButton("Iniciar jogo");
        botaoIniciar.setBounds(150, 100, 200, 50);
        tela.add(botaoIniciar);

        // Cria o botão "Editar Configs"
        JButton botaoEditar = new JButton("Editar Configurações");
        botaoEditar.setBounds(150, 200, 200, 50);
        tela.add(botaoEditar);

        JButton botaoEscolher = new JButton("Escolher Configurações");
        botaoEscolher.setBounds(150, 300, 200, 50);
        tela.add(botaoEscolher);
        
        JButton botaoSalvar = new JButton("Salvar Configurações");
        botaoSalvar.setBounds(150, 400, 200, 50);
        tela.add(botaoSalvar);
        
        // Caminho do arquivo configs.txt
        String caminho = "arqs/configs.txt";

        // Ação do botão "Ler Configs"
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tela.dispose();
                iniciarJogo();
            }
        });

        // Ação do botão "Editar Configs"
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	tela.dispose();
                abrirFormularioEdicao(caminho);
            }
        });
        
        botaoEscolher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre um JFileChooser para o usuário escolher o arquivo
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Escolher arquivo de configurações");
                
                int userSelection = fileChooser.showOpenDialog(null);
                
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    
                    // Verifica se o arquivo tem a extensão .txt
                    if (selectedFile.getName().endsWith(".txt")) {
                        File destino = new File("arqs/configs.txt");
                        
                        try {
                            // Copia o arquivo selecionado para o destino
                            Files.copy(selectedFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(tela, "Arquivo de configurações substituído com sucesso!");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(tela, "Erro ao copiar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(tela, "Por favor, escolha um arquivo .txt.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        // Exibe a janela principal
        tela.setVisible(true);
    }
    
    public static void iniciarJogo() {
        JFrame telaJogo = new JFrame("Jogo");
        int matriz = 9;
        final int tamanhoImagem;

        if (matriz > 14) {
        	tamanhoImagem = 50;
        } else  if (matriz > 10) {
            tamanhoImagem = 70;
        } else {
            tamanhoImagem = 100;
        }

        int tamanhoTela = tamanhoImagem * matriz;

        telaJogo.setBounds(500, 20, tamanhoTela, tamanhoTela);
        telaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaJogo.setResizable(false);

        // Cria um JPanel personalizado para desenhar a imagem
        JPanel painel = new JPanel() {
            private static final long serialVersionUID = 1L; // Adicionando serialVersionUID

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Carrega a imagem
                ImageIcon pedraIcon = new ImageIcon(Menu.class.getResource("/sprites/pedra.jpg"));
                ImageIcon bananaIcon = new ImageIcon(Menu.class.getResource("/sprites/banana.jpg"));
                ImageIcon cocoIcon = new ImageIcon(Menu.class.getResource("/sprites/coco.jpg"));
                ImageIcon goiabaIcon = new ImageIcon(Menu.class.getResource("/sprites/goiaba.jpg"));
                ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
                Image pedraImg = pedraIcon.getImage();
                Image bananaImg = bananaIcon.getImage();
                Image cocoImg = cocoIcon.getImage();
                Image goiabaImg = goiabaIcon.getImage();
                Image gramaImg = gramaIcon.getImage();

                // Defina o novo tamanho desejado para a imagem
                int Largura = tamanhoImagem; // Largura desejada
                int Altura = tamanhoImagem; // Altura desejada

                int c;
                // Desenha a imagem em uma matriz
                for (int i = 0; i < matriz; i++) {
                    for (int j = 0; j < matriz; j++) {
                        // Calcula a posição para desenhar a imagem
                        int x = j * Largura;
                        int y = i * Altura;
                        
                        c = (int)(Math.random() * 5) + 1;
                        
                        switch (c) {
                        case 1:
                        	g.drawImage(pedraImg, x, y, Largura, Altura, null);
                        	break;
                        
                        case 2:
                        	g.drawImage(bananaImg, x, y, Largura, Altura, null);
                        	break;
                        	
                        case 3:
                        	g.drawImage(cocoImg, x, y, Largura, Altura, null);
                        	break;
                        
                        case 4:
                        	g.drawImage(goiabaImg, x, y, Largura, Altura, null);
                        	break;
                        	
                        case 5:
                        	g.drawImage(gramaImg, x, y, Largura, Altura, null);
                        }
                    }
                }
            }
        };

        // Define o tamanho do painel para preencher a janela
        painel.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));
        painel.setLayout(null); // Não usar layout para posicionar manualmente os componentes
        telaJogo.add(painel);
        telaJogo.pack(); // Ajusta a janela com base no tamanho do painel
        telaJogo.setVisible(true);
    }



    // Método para abrir o formulário de edição
    public static void abrirFormularioEdicao(String caminho) {
        JFrame telaForm = new JFrame("Editar Configurações");
        telaForm.setBounds(100, 100, 500, 500);
        telaForm.setLayout(null);
        
        String[] listaFrutas = new String[6];
        listaFrutas[0] = "Laranja";
        listaFrutas[1] = "Abacate";
        listaFrutas[2] = "Coco";
        listaFrutas[3] = "Acerola";
        listaFrutas[4] = "Amora";
        listaFrutas[5] = "Goiaba";

        // Labels e campos de texto para as configurações
        JLabel labelDimensao = new JLabel("Tamanho do mapa:");
        labelDimensao.setBounds(10, 10, 150, 20);
        JTextField fieldDimensao = new JTextField();
        fieldDimensao.setBounds(160, 10, 100, 20);

        JLabel labelPedras = new JLabel("Quantidade de pedras:");
        labelPedras.setBounds(10, 40, 150, 20);
        JTextField fieldPedras = new JTextField();
        fieldPedras.setBounds(160, 40, 100, 20);

        JLabel labelMaracuja = new JLabel("Maracuja - Início:");
        labelMaracuja.setBounds(10, 70, 150, 20);
        JTextField fieldMaracujaInicio = new JTextField();
        fieldMaracujaInicio.setBounds(160, 70, 100, 20);
        JLabel labelMaracujaTotal = new JLabel("Total:");
        labelMaracujaTotal.setBounds(270, 70, 50, 20);
        JTextField fieldMaracujaTotal = new JTextField();
        fieldMaracujaTotal.setBounds(320, 70, 50, 20);

        // Laranja e frutas similares
        JLabel labelLaranja = new JLabel("Laranja - Árvores:");
        labelLaranja.setBounds(10, 100, 150, 20);
        JTextField fieldLaranjaArvores = new JTextField();
        fieldLaranjaArvores.setBounds(160, 100, 100, 20);
        JLabel labelLaranjaInicio = new JLabel("Início:");
        labelLaranjaInicio.setBounds(270, 100, 50, 20);
        JTextField fieldLaranjaInicio = new JTextField();
        fieldLaranjaInicio.setBounds(320, 100, 50, 20);
        
        JLabel labelAbacate = new JLabel("Abacate - Árvores:");
        labelAbacate.setBounds(10, 130, 150, 20);
        JTextField fieldAbacateArvores = new JTextField();
        fieldAbacateArvores.setBounds(160, 130, 100, 20);
        JLabel labelAbacateInicio = new JLabel("Início:");
        labelAbacateInicio.setBounds(270, 130, 50, 20);
        JTextField fieldAbacateInicio = new JTextField();
        fieldAbacateInicio.setBounds(320, 130, 50, 20);
        
        JLabel labelCoco = new JLabel("Coco - Árvores:");
        labelCoco.setBounds(10, 160, 150, 20);
        JTextField fieldCocoArvores = new JTextField();
        fieldCocoArvores.setBounds(160, 160, 100, 20);
        JLabel labelCocoInicio = new JLabel("Início:");
        labelCocoInicio.setBounds(270, 160, 50, 20);
        JTextField fieldCocoInicio = new JTextField();
        fieldCocoInicio.setBounds(320, 160, 50, 20);
        
        JLabel labelAcerola = new JLabel("Acerola - Árvores:");
        labelAcerola.setBounds(10, 190, 150, 20);
        JTextField fieldAcerolaArvores = new JTextField();
        fieldAcerolaArvores.setBounds(160, 190, 100, 20);
        JLabel labelAcerolaInicio = new JLabel("Início:");
        labelAcerolaInicio.setBounds(270, 190, 50, 20);
        JTextField fieldAcerolaInicio = new JTextField();
        fieldAcerolaInicio.setBounds(320, 190, 50, 20);
        
        JLabel labelAmora = new JLabel("Amora - Árvores:");
        labelAmora.setBounds(10, 220, 150, 20);
        JTextField fieldAmoraArvores = new JTextField();
        fieldAmoraArvores.setBounds(160, 220, 100, 20);
        JLabel labelAmoraInicio = new JLabel("Início:");
        labelAmoraInicio.setBounds(270, 220, 50, 20);
        JTextField fieldAmoraInicio = new JTextField();
        fieldAmoraInicio.setBounds(320, 220, 50, 20);
        
        JLabel labelGoiaba = new JLabel("Goiaba - Árvores:");
        labelGoiaba.setBounds(10, 250, 150, 20);
        JTextField fieldGoiabaArvores = new JTextField();
        fieldGoiabaArvores.setBounds(160, 250, 100, 20);
        JLabel labelGoiabaInicio = new JLabel("Início:");
        labelGoiabaInicio.setBounds(270, 250, 50, 20);
        JTextField fieldGoiabaInicio = new JTextField();
        fieldGoiabaInicio.setBounds(320, 250, 50, 20);

        JLabel labelBichadas = new JLabel("Porcentagem bichadas:");
        labelBichadas.setBounds(10, 280, 150, 20);
        JTextField fieldBichadas = new JTextField();
        fieldBichadas.setBounds(160, 280, 100, 20);

        JLabel labelMochila = new JLabel("Tamanho da mochila:");
        labelMochila.setBounds(10, 310, 150, 20);
        JTextField fieldMochila = new JTextField();
        fieldMochila.setBounds(160, 310, 100, 20);

        // Botão para salvar as configurações
        JButton buttonSalvar = new JButton("Salvar");
        buttonSalvar.setBounds(140, 350, 100, 30);
        

        // Ação do botão "Salvar"
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    // Escreve no arquivo configs.txt
                    BufferedWriter writer = new BufferedWriter(new FileWriter(caminho));

                    // Verifica se os campos estão vazios e insere o valor padrão, se necessário
                    String dimensao = fieldDimensao.getText().isEmpty() ? "6" : fieldDimensao.getText();
                    String pedras = fieldPedras.getText().isEmpty() ? "7" : fieldPedras.getText();
                    String maracujaInicio = fieldMaracujaInicio.getText().isEmpty() ? "3" : fieldMaracujaInicio.getText();
                    String maracujaTotal = fieldMaracujaTotal.getText().isEmpty() ? "1" : fieldMaracujaTotal.getText();
                    String laranjaArvores = fieldLaranjaArvores.getText().isEmpty() ? "2" : fieldLaranjaArvores.getText();
                    String laranjaInicio = fieldLaranjaInicio.getText().isEmpty() ? "1" : fieldLaranjaInicio.getText();
                    String abacateArvores = fieldAbacateArvores.getText().isEmpty() ? "2" : fieldAbacateArvores.getText();
                    String abacateInicio = fieldAbacateInicio.getText().isEmpty() ? "3" : fieldAbacateInicio.getText();
                    String cocoArvores = fieldCocoArvores.getText().isEmpty() ? "2" : fieldCocoArvores.getText();
                    String cocoInicio = fieldCocoInicio.getText().isEmpty() ? "1" : fieldCocoInicio.getText();
                    String acerolaArvores = fieldAcerolaArvores.getText().isEmpty() ? "1" : fieldAcerolaArvores.getText();
                    String acerolaInicio = fieldAcerolaInicio.getText().isEmpty() ? "2" : fieldAcerolaInicio.getText();
                    String amoraArvores = fieldAmoraArvores.getText().isEmpty() ? "1" : fieldAmoraArvores.getText();
                    String amoraInicio = fieldAmoraInicio.getText().isEmpty() ? "1" : fieldAmoraInicio.getText();
                    String goiabaArvores = fieldGoiabaArvores.getText().isEmpty() ? "1" : fieldGoiabaArvores.getText();
                    String goiabaInicio = fieldGoiabaInicio.getText().isEmpty() ? "1" : fieldGoiabaInicio.getText();
                    String bichadas = fieldBichadas.getText().isEmpty() ? "25" : fieldBichadas.getText();
                    String mochila = fieldMochila.getText().isEmpty() ? "10" : fieldMochila.getText();

                    // Escreve os valores no arquivo
                    writer.write("dimensao " + dimensao + "\n");
                    writer.write("pedras " + pedras + "\n");
                    writer.write("maracuja " + maracujaInicio + " " + maracujaTotal + "\n");
                    writer.write("laranja " + laranjaArvores + " " + laranjaInicio + "\n");
                    writer.write("abacate " + abacateArvores + " " + abacateInicio + "\n");
                    writer.write("coco " + cocoArvores + " " + cocoInicio + "\n");
                    writer.write("acerola " + acerolaArvores + " " + acerolaInicio + "\n");
                    writer.write("amora " + amoraArvores + " " + amoraInicio + "\n");
                    writer.write("goiaba " + goiabaArvores + " " + goiabaInicio + "\n");
                    writer.write("bichadas " + bichadas + "\n");
                    writer.write("mochila " + mochila + "\n");

                    writer.close();

                    // Mensagem de sucesso e fechar o formulário
                    JOptionPane.showMessageDialog(telaForm, "Configurações salvas com sucesso!");
                    telaForm.dispose(); // Fecha o formulário após salvar
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        // Adiciona os componentes ao formulário
        telaForm.add(labelDimensao);
        telaForm.add(fieldDimensao);
        telaForm.add(labelPedras);
        telaForm.add(fieldPedras);
        telaForm.add(labelMaracuja);
        telaForm.add(fieldMaracujaInicio);
        telaForm.add(labelMaracujaTotal);
        telaForm.add(fieldMaracujaTotal);
        telaForm.add(labelLaranja);
        telaForm.add(fieldLaranjaArvores);
        telaForm.add(labelLaranjaInicio);
        telaForm.add(fieldLaranjaInicio);
        telaForm.add(labelAbacate);
        telaForm.add(fieldAbacateArvores);
        telaForm.add(labelAbacateInicio);
        telaForm.add(fieldAbacateInicio);
        telaForm.add(labelCoco);
        telaForm.add(fieldCocoArvores);
        telaForm.add(labelCocoInicio);
        telaForm.add(fieldCocoInicio);
        telaForm.add(labelAcerola);
        telaForm.add(fieldAcerolaArvores);
        telaForm.add(labelAcerolaInicio);
        telaForm.add(fieldAcerolaInicio);
        telaForm.add(labelAmora);
        telaForm.add(fieldAmoraArvores);
        telaForm.add(labelAmoraInicio);
        telaForm.add(fieldAmoraInicio);
        telaForm.add(labelGoiaba);
        telaForm.add(fieldGoiabaArvores);
        telaForm.add(labelGoiabaInicio);
        telaForm.add(fieldGoiabaInicio);
        telaForm.add(labelBichadas);
        telaForm.add(fieldBichadas);
        telaForm.add(labelMochila);
        telaForm.add(fieldMochila);
        telaForm.add(buttonSalvar);

        // Torna o formulário visível
        telaForm.setVisible(true);
    }
}
