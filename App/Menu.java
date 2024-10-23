package App;

import javax.swing.*;

import Arquivo.ArquivoHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.awt.*;

/**
 * A classe Menu representa a interface do menu inicial do jogo "Cata Frutas"
 * Ela fornece opções para iniciar o jogo, editar configurações e escolher arquivos de configuração
 */
public class Menu {
	static ArquivoHandler arquivoHandler = new ArquivoHandler();
	
	/**
     * O ponto de entrada principal do programa
     * Exibe o menu inicial do jogo
     *
     * @param args Os argumentos da linha de comando
     */
	
	public static void main(String[] args) {
        new TelaMenuInicial(); // Mostra o menu inicial
    }

    // Método estático que pode ser chamado a partir de outros lugares para iniciar o jogo
    public static void iniciarJogo() {
        new TelaJogo(); // Inicia o jogo
    }


    /**
     * Abre um formulário para editar as configurações do jogo
     *
     * @param caminho O caminho do arquivo de configuração a ser editado
     * Este método cria uma interface gráfica onde o usuário pode definir parâmetros como 
     * o tamanho do mapa, quantidade de pedras e outras propriedades relacionadas a frutas
     */
    public static void abrirFormularioEdicao(String caminho) {
        JFrame telaForm = new JFrame("Editar Configurações");
        telaForm.setBounds(100, 100, 450, 450);
        telaForm.setLayout(null);
        
        String[] listaFrutas = new String[6];
        listaFrutas[0] = "Laranja";
        listaFrutas[1] = "Abacate";
        listaFrutas[2] = "Coco";
        listaFrutas[3] = "Banana";
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
        fieldMaracujaTotal.setBounds(320, 70, 100, 20);

        // Laranja e frutas similares
        JLabel labelLaranja = new JLabel("Laranja - Árvores:");
        labelLaranja.setBounds(10, 100, 150, 20);
        JTextField fieldLaranjaArvores = new JTextField();
        fieldLaranjaArvores.setBounds(160, 100, 100, 20);
        JLabel labelLaranjaInicio = new JLabel("Início:");
        labelLaranjaInicio.setBounds(270, 100, 50, 20);
        JTextField fieldLaranjaInicio = new JTextField();
        fieldLaranjaInicio.setBounds(320, 100, 100, 20);
        
        JLabel labelAbacate = new JLabel("Abacate - Árvores:");
        labelAbacate.setBounds(10, 130, 150, 20);
        JTextField fieldAbacateArvores = new JTextField();
        fieldAbacateArvores.setBounds(160, 130, 100, 20);
        JLabel labelAbacateInicio = new JLabel("Início:");
        labelAbacateInicio.setBounds(270, 130, 50, 20);
        JTextField fieldAbacateInicio = new JTextField();
        fieldAbacateInicio.setBounds(320, 130, 100, 20);
        
        JLabel labelCoco = new JLabel("Coco - Árvores:");
        labelCoco.setBounds(10, 160, 150, 20);
        JTextField fieldCocoArvores = new JTextField();
        fieldCocoArvores.setBounds(160, 160, 100, 20);
        JLabel labelCocoInicio = new JLabel("Início:");
        labelCocoInicio.setBounds(270, 160, 50, 20);
        JTextField fieldCocoInicio = new JTextField();
        fieldCocoInicio.setBounds(320, 160, 100, 20);
        
        JLabel labelBanana = new JLabel("Banana - Árvores:");
        labelBanana.setBounds(10, 190, 150, 20);
        JTextField fieldBananaArvores = new JTextField();
        fieldBananaArvores.setBounds(160, 190, 100, 20);
        JLabel labelBananaInicio = new JLabel("Início:");
        labelBananaInicio.setBounds(270, 190, 50, 20);
        JTextField fieldBananaInicio = new JTextField();
        fieldBananaInicio.setBounds(320, 190, 100, 20);
        
        JLabel labelAmora = new JLabel("Amora - Árvores:");
        labelAmora.setBounds(10, 220, 150, 20);
        JTextField fieldAmoraArvores = new JTextField();
        fieldAmoraArvores.setBounds(160, 220, 100, 20);
        JLabel labelAmoraInicio = new JLabel("Início:");
        labelAmoraInicio.setBounds(270, 220, 50, 20);
        JTextField fieldAmoraInicio = new JTextField();
        fieldAmoraInicio.setBounds(320, 220, 100, 20);
        
        JLabel labelGoiaba = new JLabel("Goiaba - Árvores:");
        labelGoiaba.setBounds(10, 250, 150, 20);
        JTextField fieldGoiabaArvores = new JTextField();
        fieldGoiabaArvores.setBounds(160, 250, 100, 20);
        JLabel labelGoiabaInicio = new JLabel("Início:");
        labelGoiabaInicio.setBounds(270, 250, 50, 20);
        JTextField fieldGoiabaInicio = new JTextField();
        fieldGoiabaInicio.setBounds(320, 250, 100, 20);

        JLabel labelBichadas = new JLabel("Porcentagem bichadas:");
        labelBichadas.setBounds(10, 280, 150, 20);
        JTextField fieldBichadas = new JTextField();
        fieldBichadas.setBounds(160, 280, 100, 20);

        JLabel labelMochila = new JLabel("Tamanho da mochila:");
        labelMochila.setBounds(10, 310, 150, 20);
        JTextField fieldMochila = new JTextField();
        fieldMochila.setBounds(160, 310, 100, 20);

        // Botão para salvar as configurações        
        ImageIcon iconSalvar = new ImageIcon(Menu.class.getResource("/buttons/salvar.png"));
        Image imageSalvar = iconSalvar.getImage();
        Image imageSalvarRedimensionada = imageSalvar.getScaledInstance(330, 66, Image.SCALE_SMOOTH);
        ImageIcon iconSalvarRedimensionado = new ImageIcon(imageSalvarRedimensionada);
        JButton buttonSalvar = new JButton(iconSalvarRedimensionado);
        buttonSalvar.setText(null);
        buttonSalvar.setBorderPainted(false);
        buttonSalvar.setFocusPainted(false);
        buttonSalvar.setContentAreaFilled(false);
        buttonSalvar.setBounds(60, 340, 330, 66);
        

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
                    String bananaArvores = fieldBananaArvores.getText().isEmpty() ? "1" : fieldBananaArvores.getText();
                    String bananaInicio = fieldBananaInicio.getText().isEmpty() ? "2" : fieldBananaInicio.getText();
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
                    frutas.put("banana", new String[] {bananaArvores, bananaInicio});
                    frutas.put("amora", new String[] {amoraArvores, amoraInicio});
                    frutas.put("goiaba", new String[] {goiabaArvores, goiabaInicio});
                    frutas.put("bichadas", new String[] {bichadas});
                    frutas.put("mochila", new String[] {mochila});
                    
                    Menu.arquivoHandler.setElementos(frutas);
                    if (!Menu.arquivoHandler.validarGeracaoMapa(20)) {
                    	Menu.arquivoHandler.reiniciarAtributos();
                    	JOptionPane.showMessageDialog(telaForm, "Não é possível gerar um mapa com essas configurações!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {                    	
                    	JOptionPane.showMessageDialog(telaForm, "Configurações salvas com sucesso!");
                    }
                    
                    // Mensagem de sucesso e fechar o formulário
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
        telaForm.add(labelBanana);
        telaForm.add(fieldBananaArvores);
        telaForm.add(labelBananaInicio);
        telaForm.add(fieldBananaInicio);
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
