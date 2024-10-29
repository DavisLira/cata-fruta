package App;

import javax.swing.*;

import Frutas.Abacate;
import Frutas.Coco;
import Frutas.Fruta;
import Frutas.Maracuja;
import Jogador.Jogador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a tela da mochila do jogador
 * Exibe as frutas armazenadas na mochila e permite interações
 */
public class TelaMochila extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel painelFrutas;
    private Jogador jogador; // Armazena o jogador para ser usado em atualizações

    /**
     * Construtor da classe TelaMochila
     * Inicializa a tela da mochila do jogador e configura a interface gráfica
     * 
     * @param jogador O jogador cujas frutas serão exibidas
     */
    public TelaMochila(Jogador jogador) {
        super("Mochila do jogador " + jogador.getNumero());
        this.jogador = jogador; // Inicializa o jogador

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8);

        int capacidade = jogador.getMochila().getFrutas().length;
        int lado = (int) Math.ceil(Math.sqrt(capacidade));
        int tamanhoImagem = alturaTela / lado;
        int tamanhoTela = tamanhoImagem * lado;

        this.setBounds(0, 0, tamanhoTela, tamanhoTela);
        this.setLocation((screenSize.width - tamanhoTela) / 2, (screenSize.height - tamanhoTela) / 2);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);

        painelFrutas = new JPanel(null);
        painelFrutas.setPreferredSize(new Dimension(tamanhoTela, tamanhoTela));
        
        this.add(painelFrutas, BorderLayout.CENTER);
        atualizarTela(); // Chamada inicial para montar a tela
        this.pack();
    }

    /**
     * Atualiza a tela com o estado atual das frutas na mochila
     * Remove todos os componentes anteriores e recria os botões para as frutas
     */
    private void atualizarTela() {
        painelFrutas.removeAll(); // Remove todos os componentes anteriores

        Fruta[] frutas = jogador.getMochila().getFrutas(); // Obtém o estado atualizado das frutas
        int capacidade = frutas.length;
        int lado = (int) Math.ceil(Math.sqrt(capacidade));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int alturaTela = (int) (screenSize.height * 0.8);
        int tamanhoImagem = alturaTela / lado;

        for (int i = 0; i < lado * lado; i++) {
            JButton frutaButton;
            ImageIcon icon;

            if (i < capacidade && frutas[i] != null) {
                icon = new ImageIcon(Menu.class.getResource(frutas[i].getImgMochila()));
            } else if (i < capacidade) {
                icon = new ImageIcon(Menu.class.getResource("/sprites/Mochila.png"));
            } else {
                icon = new ImageIcon(Menu.class.getResource("/sprites/grama.jpg"));
            }

            Image img = icon.getImage().getScaledInstance(tamanhoImagem, tamanhoImagem, Image.SCALE_SMOOTH);
            frutaButton = new JButton(new ImageIcon(img));

            frutaButton.setText(null);
            frutaButton.setBorderPainted(false);
            frutaButton.setFocusPainted(false);
            frutaButton.setContentAreaFilled(false);

            int linha = i / lado;
            int coluna = i % lado;
            int x = coluna * tamanhoImagem;
            int y = linha * tamanhoImagem;
            frutaButton.setBounds(x, y, tamanhoImagem, tamanhoImagem);

            final int index = i;
            frutaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	
                	
                	
                	if (index >= frutas.length) {
                		JOptionPane.showMessageDialog(null, "Espaço sem uso.");
                	} else if (frutas[index] instanceof Maracuja) {
                        JOptionPane.showMessageDialog(null, "Não pode comer maracujá.");
                		return;
                	} else if (frutas[index] instanceof Fruta) {
                		if (jogador.getComeuAbacate() && frutas[index] instanceof Abacate) {
                			JOptionPane.showMessageDialog(null, "Não pode comer abacate novamente");
                			return; 
                			
                		} else if (jogador.getComeuCoco() && frutas[index] instanceof Coco) {
                			JOptionPane.showMessageDialog(null, "Não pode comer coco novamente");
                			return;
                		} 
                        if (frutas[index].isBichada()) {
                            JOptionPane.showMessageDialog(null, "A fruta estava bichada!");
                        }
                        
                        frutas[index].comer(jogador);
                        jogador.resetarForca();
                        atualizarTela(); // Atualiza a tela após comer a fruta
                    } else if (index < capacidade) {
                        JOptionPane.showMessageDialog(null, "Espaço vazio na mochila.");
                    }
                }
            });

            painelFrutas.add(frutaButton);
        }

        painelFrutas.revalidate(); // Atualiza a interface gráfica
        painelFrutas.repaint();
    }

    /**
     * Exibe a tela da mochila
     */
    public void exibir() {
        this.setVisible(true);
    }
}
