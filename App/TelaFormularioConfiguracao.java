package App;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.awt.*;

/**
 * A classe TelaFormularioConfiguracao exibe um formulário de edição de configurações do jogo.
 */
public class TelaFormularioConfiguracao {

    private JFrame telaForm;
    private JTextField fieldDimensao, fieldPedras, fieldMaracujaInicio, fieldMaracujaTotal;
    private JTextField fieldLaranjaArvores, fieldLaranjaInicio, fieldAbacateArvores, fieldAbacateInicio;
    private JTextField fieldCocoArvores, fieldCocoInicio, fieldBananaArvores, fieldBananaInicio;
    private JTextField fieldAmoraArvores, fieldAmoraInicio, fieldGoiabaArvores, fieldGoiabaInicio;
    private JTextField fieldBichadas, fieldMochila;

    /**
     * Construtor para inicializar a tela de formulário de edição.
     */
    public TelaFormularioConfiguracao(String caminho) {
        criarTelaFormulario();
    }

    /**
     * Método para criar a interface do formulário e configurar seus componentes.
     */
    private void criarTelaFormulario() {
        telaForm = new JFrame("Editar Configurações");
        telaForm.setBounds(100, 100, 450, 450);
        telaForm.setLayout(null);

        // Adicionar todos os campos e labels
        adicionarComponentes();

        // Botão de salvar
        JButton buttonSalvar = criarBotaoSalvar();

        // Adicionar botão à tela
        telaForm.add(buttonSalvar);

        // Torna o formulário visível
        telaForm.setVisible(true);
    }

    /**
     * Adiciona os componentes do formulário (labels e campos de texto).
     */
    private void adicionarComponentes() {
        JLabel labelDimensao = new JLabel("Tamanho do mapa:");
        labelDimensao.setBounds(10, 10, 150, 20);
        fieldDimensao = new JTextField();
        fieldDimensao.setBounds(160, 10, 100, 20);

        JLabel labelPedras = new JLabel("Quantidade de pedras:");
        labelPedras.setBounds(10, 40, 150, 20);
        fieldPedras = new JTextField();
        fieldPedras.setBounds(160, 40, 100, 20);

        JLabel labelMaracuja = new JLabel("Maracuja - Início:");
        labelMaracuja.setBounds(10, 70, 150, 20);
        fieldMaracujaInicio = new JTextField();
        fieldMaracujaInicio.setBounds(160, 70, 100, 20);
        JLabel labelMaracujaTotal = new JLabel("Total:");
        labelMaracujaTotal.setBounds(270, 70, 50, 20);
        fieldMaracujaTotal = new JTextField();
        fieldMaracujaTotal.setBounds(320, 70, 100, 20);

        // Laranja
        JLabel labelLaranja = new JLabel("Laranja - Árvores:");
        labelLaranja.setBounds(10, 100, 150, 20);
        fieldLaranjaArvores = new JTextField();
        fieldLaranjaArvores.setBounds(160, 100, 100, 20);
        JLabel labelLaranjaInicio = new JLabel("Início:");
        labelLaranjaInicio.setBounds(270, 100, 50, 20);
        fieldLaranjaInicio = new JTextField();
        fieldLaranjaInicio.setBounds(320, 100, 100, 20);

        JLabel labelAbacate = new JLabel("Abacate - Árvores:");
        labelAbacate.setBounds(10, 130, 150, 20);
        fieldAbacateArvores = new JTextField();
        fieldAbacateArvores.setBounds(160, 130, 100, 20);
        JLabel labelAbacateInicio = new JLabel("Início:");
        labelAbacateInicio.setBounds(270, 130, 50, 20);
        fieldAbacateInicio = new JTextField();
        fieldAbacateInicio.setBounds(320, 130, 100, 20);
        
        JLabel labelCoco = new JLabel("Coco - Árvores:");
        labelCoco.setBounds(10, 160, 150, 20);
        fieldCocoArvores = new JTextField();
        fieldCocoArvores.setBounds(160, 160, 100, 20);
        JLabel labelCocoInicio = new JLabel("Início:");
        labelCocoInicio.setBounds(270, 160, 50, 20);
        fieldCocoInicio = new JTextField();
        fieldCocoInicio.setBounds(320, 160, 100, 20);
        
        JLabel labelBanana = new JLabel("Banana - Árvores:");
        labelBanana.setBounds(10, 190, 150, 20);
        fieldBananaArvores = new JTextField();
        fieldBananaArvores.setBounds(160, 190, 100, 20);
        JLabel labelBananaInicio = new JLabel("Início:");
        labelBananaInicio.setBounds(270, 190, 50, 20);
        fieldBananaInicio = new JTextField();
        fieldBananaInicio.setBounds(320, 190, 100, 20);
        
        JLabel labelAmora = new JLabel("Amora - Árvores:");
        labelAmora.setBounds(10, 220, 150, 20);
        fieldAmoraArvores = new JTextField();
        fieldAmoraArvores.setBounds(160, 220, 100, 20);
        JLabel labelAmoraInicio = new JLabel("Início:");
        labelAmoraInicio.setBounds(270, 220, 50, 20);
        fieldAmoraInicio = new JTextField();
        fieldAmoraInicio.setBounds(320, 220, 100, 20);
        
        JLabel labelGoiaba = new JLabel("Goiaba - Árvores:");
        labelGoiaba.setBounds(10, 250, 150, 20);
        fieldGoiabaArvores = new JTextField();
        fieldGoiabaArvores.setBounds(160, 250, 100, 20);
        JLabel labelGoiabaInicio = new JLabel("Início:");
        labelGoiabaInicio.setBounds(270, 250, 50, 20);
        fieldGoiabaInicio = new JTextField();
        fieldGoiabaInicio.setBounds(320, 250, 100, 20);

        JLabel labelBichadas = new JLabel("Porcentagem bichadas:");
        labelBichadas.setBounds(10, 280, 150, 20);
        fieldBichadas = new JTextField();
        fieldBichadas.setBounds(160, 280, 100, 20);

        JLabel labelMochila = new JLabel("Tamanho da mochila:");
        labelMochila.setBounds(10, 310, 150, 20);
        fieldMochila = new JTextField();
        fieldMochila.setBounds(160, 310, 100, 20);

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
    }

    /**
     * Cria o botão de salvar e define sua ação.
     */
    private JButton criarBotaoSalvar() {
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
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarConfiguracoes();
            }
        });
        return buttonSalvar;
    }

    /**
     * Método para salvar as configurações quando o botão é clicado.
     */
    private void salvarConfiguracoes() {
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

            float maracujaTotalVal = Float.parseFloat(maracujaTotal);
            float mochilaVal = Float.parseFloat(mochila);

            if (mochilaVal <= (maracujaTotalVal)/2) {
                JOptionPane.showMessageDialog(telaForm, "Erro: O valor de 'mochila' deve ser maior ou igual a metade + 1 de 'maracuja total'.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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

            telaForm.dispose(); // Fecha o formulário após salvar
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
