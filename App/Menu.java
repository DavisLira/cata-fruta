package App;

import javax.swing.*;

import Arquivo.ArquivoHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Menu {
	static ArquivoHandler arquivoHandler = new ArquivoHandler();
	
	public static void main(String[] args) {
		mostrarMenuInicial();
	}
	
    public static void mostrarMenuInicial() {

    	JFrame menuInicial = new JFrame("Cata Frutas - Menu inicial");
    	menuInicial.setBounds(500, 50, 500, 600);
        menuInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuInicial.setLayout(null);
        menuInicial.setResizable(false);

        // Cria o botão "Ler Configs"
        JButton botaoIniciar = new JButton("Iniciar jogo");
        botaoIniciar.setBounds(150, 100, 200, 50);
        menuInicial.add(botaoIniciar);

        // Cria o botão "Editar Configs"
        JButton botaoEditar = new JButton("Editar Configurações");
        botaoEditar.setBounds(150, 200, 200, 50);
        menuInicial.add(botaoEditar);

        JButton botaoEscolher = new JButton("Escolher Configurações");
        botaoEscolher.setBounds(150, 300, 200, 50);
        menuInicial.add(botaoEscolher);
        
        JButton botaoBaixar = new JButton("Baixar Configurações");
        botaoBaixar.setBounds(150, 400, 200, 50);
        menuInicial.add(botaoBaixar);
        
        // Caminho do arquivo configs.txt
        String caminho = "arqs/configs.txt";

        // Ação do botão "Ler Configs"
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	menuInicial.dispose();
                iniciarJogo();
            }
        });

        // Ação do botão "Editar Configs"
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    try {
                    	boolean resultado_arquivo = Menu.arquivoHandler.ler("arqs" + System.getProperty("file.separator") + selectedFile.getName()); 
                    	System.out.println(resultado_arquivo);

	                		if(resultado_arquivo) {
	                			// Copia o arquivo selecionado para o destino
	                			//Files.copy(selectedFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
	                			JOptionPane.showMessageDialog(menuInicial, "Arquivo de configurações substituído com sucesso!");
	                		} else{
	                			//que armengue absurdo mds do ceu
	                			Menu.arquivoHandler.reiniciarAtributos();
	                			JOptionPane.showMessageDialog(menuInicial, "Por favor, escolha um arquivo formato correto.", "Erro", JOptionPane.ERROR_MESSAGE);
	                		}

                    } catch (Exception ex) {
                    	JOptionPane.showMessageDialog(menuInicial, "Por favor, escolha um arquivo .txt no formato correto.", "Erro", JOptionPane.ERROR_MESSAGE);
	                }
                }
            }
        });
        
        // Ação do botão "Editar Configs"
        botaoBaixar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu.arquivoHandler.escreverNoArquivo();
            }
        });


        // Exibe a janela principal
        menuInicial.setVisible(true);
    }
    
    public static void iniciarJogo() {
        JFrame telaJogo = new JFrame("Jogo");
        
        int matriz = (Menu.arquivoHandler.getDimensao());
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
        JPanel painelJogo  = new JPanel() {
            private static final long serialVersionUID = 1L; // Adicionando serialVersionUID

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                gerarMapa(g, matriz, tamanhoImagem);
            }
        };

        // Define o tamanho do painel para preencher a janela
        painelJogo.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));
        painelJogo.setLayout(null); // Não usar layout para posicionar manualmente os componentes
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        
        JButton gerarNovamenteButton = new JButton("Gerar Novamente");
        gerarNovamenteButton.addActionListener(e -> painelJogo.repaint());
        
        JButton voltarMenuButton = new JButton("Voltar ao Menu Inicial");
        voltarMenuButton.addActionListener(e -> {
            telaJogo.dispose();  // Fecha a tela do jogo
            mostrarMenuInicial(); // Volta para o menu inicial
        });
        
        painelBotoes.add(gerarNovamenteButton);
        painelBotoes.add(voltarMenuButton);
        
        telaJogo.add(painelJogo, BorderLayout.CENTER);
        telaJogo.add(painelBotoes, BorderLayout.SOUTH);
        
        telaJogo.pack();
        telaJogo.setVisible(true);
    }
    
    public static void gerarMapa(Graphics g, int matriz, int tamanhoImagem) {
    	// carrega as imagens
        ImageIcon abacateIcon = new ImageIcon(Menu.class.getResource("/sprites/abacate.jpg"));
        ImageIcon abacateiraIcon = new ImageIcon(Menu.class.getResource("/sprites/abacateira.jpg"));
        ImageIcon amoraIcon = new ImageIcon(Menu.class.getResource("/sprites/amora.jpg"));
        ImageIcon amoreiraIcon = new ImageIcon(Menu.class.getResource("/sprites/amoreira.jpg"));
        ImageIcon acerolaIcon = new ImageIcon(Menu.class.getResource("/sprites/acerola.jpg"));
        ImageIcon aceroleiraIcon = new ImageIcon(Menu.class.getResource("/sprites/aceroleira.jpg"));
        ImageIcon cocoIcon = new ImageIcon(Menu.class.getResource("/sprites/coco.jpg"));
        ImageIcon coqueiroIcon = new ImageIcon(Menu.class.getResource("/sprites/coqueiro.jpg"));
        ImageIcon goiabaIcon = new ImageIcon(Menu.class.getResource("/sprites/goiaba.jpg"));
        ImageIcon goiabeiraIcon = new ImageIcon(Menu.class.getResource("/sprites/goiabeira.jpg"));
        ImageIcon gramaIcon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
        ImageIcon jogador1Icon = new ImageIcon(Menu.class.getResource("/sprites/jogador1.jpg"));
        ImageIcon jogador2Icon = new ImageIcon(Menu.class.getResource("/sprites/jogador2.jpg"));
        ImageIcon laranjaIcon = new ImageIcon(Menu.class.getResource("/sprites/laranja.jpg"));
        ImageIcon laranjeiraIcon = new ImageIcon(Menu.class.getResource("/sprites/laranjeira.jpg"));
        ImageIcon maracujaIcon = new ImageIcon(Menu.class.getResource("/sprites/maracuja.jpg"));
    	ImageIcon pedraIcon = new ImageIcon(Menu.class.getResource("/sprites/pedra.jpg"));
    	ImageIcon pedra2Icon = new ImageIcon(Menu.class.getResource("/sprites/pedra2.jpg"));
        Image abacateImg = abacateIcon.getImage();
        Image abacateiraImg = abacateiraIcon.getImage();
        Image amoraImg = amoraIcon.getImage();
        Image amoreiraImg = amoreiraIcon.getImage();
        Image acerolaImg = acerolaIcon.getImage();
        Image aceroleiraImg = aceroleiraIcon.getImage();
        Image cocoImg = cocoIcon.getImage();
        Image coqueiroImg = coqueiroIcon.getImage();
        Image goiabaImg = goiabaIcon.getImage();
        Image goiabeirImg = goiabeiraIcon.getImage();
        Image gramaImg = gramaIcon.getImage();
        Image jogador1Img = jogador1Icon.getImage();
        Image jogador2Img = jogador2Icon.getImage();
        Image laranjaImg = laranjaIcon.getImage();
        Image laranjeiraImg = laranjeiraIcon.getImage();
        Image maracujaImg = maracujaIcon.getImage();
        Image pedraImg = pedraIcon.getImage();
        Image pedra2Img = pedra2Icon.getImage();
    	
        
        // Desenha o mapa todo de grama
        for (int i = 0; i < matriz; i++) {
            for (int j = 0; j < matriz; j++) {
                // Calcula a posição para desenhar a imagem
                int x = j * tamanhoImagem;
                int y = i * tamanhoImagem;
                
                g.drawImage(gramaImg, x, y, tamanhoImagem, tamanhoImagem, null);
            }
        }
        
        java.util.Set<Point> posicoesSet = new java.util.HashSet<>();
        for (int i = 0; i < matriz; i++) {
            for (int j = 0; j < matriz; j++) {
                posicoesSet.add(new Point(j, i)); // Adiciona as coordenadas (coluna, linha)
            }
        }
        

        List<Point> posicoes = new ArrayList<>(posicoesSet);
        
        Collections.shuffle((List<?>) posicoes);
        
        colocarElemento(g, jogador1Img, posicoes.remove(0), tamanhoImagem);
        colocarElemento(g, jogador2Img, posicoes.remove(0), tamanhoImagem);

        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, abacateImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, abacateiraImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, amoraImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, amoreiraImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, acerolaImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, aceroleiraImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, cocoImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, coqueiroImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, goiabaImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, goiabeirImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, laranjaImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, laranjeiraImg, posicoes.remove(0), tamanhoImagem);
        }
        
        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
        	colocarElemento(g, maracujaImg, posicoes.remove(0), tamanhoImagem);
        }
        

        for (int i = 0; i < (Menu.arquivoHandler.getPedras()); i++) {
            // Alterna entre pedraImg e pedraImg2 com base no índice
            Image pedra = (i % 2 == 0) ? pedraImg : pedra2Img;

            colocarElemento(g, pedra, posicoes.remove(0), tamanhoImagem);
        }
    }
    
    public static void colocarElemento(Graphics g, Image imagem, Point posicao, int tamanho) {
        int x = posicao.x * tamanho;
        int y = posicao.y * tamanho;
        g.drawImage(imagem, x, y, tamanho, tamanho, null);
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

                    //colocar essa parte no local certo de salvar arquivo
                	LinkedHashMap<String, String[]> frutas = new LinkedHashMap<>();
                	frutas.put("dimensao", new String[] {dimensao});
                	frutas.put("pedras", new String[] {pedras});
                    frutas.put("maracuja", new String[] {maracujaInicio, maracujaTotal});
                    frutas.put("laranja", new String[] {laranjaArvores, laranjaInicio});
                    frutas.put("abacate", new String[] {abacateArvores, abacateInicio});
                    frutas.put("coco", new String[] {cocoArvores, cocoInicio});
                    frutas.put("acerola", new String[] {acerolaArvores, acerolaInicio});
                    frutas.put("amora", new String[] {amoraArvores, amoraInicio});
                    frutas.put("goiaba", new String[] {goiabaArvores, goiabaInicio});
                    frutas.put("bichadas", new String[] {bichadas});
                    frutas.put("mochila", new String[] {mochila});
                    
                    Menu.arquivoHandler.setElementos(frutas);
                    if (!Menu.arquivoHandler.validarGeracaoMapa(20)) {
                    	System.out.println("bote um aviso na tela do cara q eu n sei fazer isso nao");
                    	Menu.arquivoHandler.reiniciarAtributos();
                    }
                    	
                    
                    
                    // Mensagem de sucesso e fechar o formulário
                    JOptionPane.showMessageDialog(telaForm, "Configurações salvas com sucesso!");
                    telaForm.dispose(); // Fecha o formulário após salvar
                } catch (Exception ex) {
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
