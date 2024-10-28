package App;

import javax.swing.*;

import Floresta.Arvore;
import Floresta.Grama;
import Floresta.Local;
import Frutas.Fruta;
import Frutas.Maracuja;
import Jogador.Jogador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;  // Para a classe Point
import java.util.ArrayList;  // Para a classe ArrayList
import java.util.List;  // Para a interface List


public class TelaControle {
	private JButton mochilaButton, fimTurnoButton, cataButton, setaCimaButton, setaEsqButton ,setaDirButton, setaBaixoButton;
    private JFrame telaControle;
    private JPanel painelControle;
    private TelaMochila telaMochila;
    private boolean mochilaAberta = false;
    private Jogador jogadorAtual;
    private Jogador jogadorProx;
    private TelaJogo jogo;
    private int qtdMaracujaInicio;
    private int qtdMaracujaTotal;
    private int qtdMaracujaCriados;
    private int rodada;

    public TelaControle(Jogador jogadorAtual, Jogador jogadorProx, TelaJogo jogo, int qtdMaracujaInicio, int qtdMaracujaTotal,int qtdMaracujaCriados, int rodada) {
    	this.jogadorAtual = jogadorAtual;
    	this.jogadorAtual.setSeMovimentou(false);
    	this.jogadorAtual.setPodeSeMover(true);
    	this.jogadorAtual.resetarForca();
    	this.jogadorProx = jogadorProx;
    	this.jogadorProx.resetarForca();
    	this.jogo = jogo;
    	this.qtdMaracujaInicio = qtdMaracujaInicio;
    	this.qtdMaracujaTotal = qtdMaracujaTotal;
    	this.qtdMaracujaCriados = qtdMaracujaCriados;
    	this.rodada = rodada;
        iniciarControle();
    }

    private void iniciarControle() {
    	System.out.println("RODADA: " + rodada);
    	
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        telaControle = new JFrame("Controles");
        telaControle.setUndecorated(true);
        telaControle.setBounds(0, 0, 300, 300);
        telaControle.setLocation(30, (screenSize.height - 300) / 2);
        telaControle.setResizable(false);
        
        painelControle = new JPanel(null) {
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gerarImagens(g, 3, 100);
            }
        };
        
        painelControle.setPreferredSize(new Dimension(300, 300));

        mochilaButton = criarBotaoControle("/Controles/mochila_ctrl.png", 100, 100, 100, 100);
        fimTurnoButton = criarBotaoControle("/Controles/fim_turno.png", 100, 100, 0, 0);
        cataButton = criarBotaoControle("/Controles/cata_btn.png", 100, 100, 0, 200);

        setaCimaButton = criarBotaoControle("/Controles/seta_cima.png", 100, 100, 100, 0);
        setaEsqButton = criarBotaoControle("/Controles/seta_esq.png", 100, 100, 0, 100);
        setaDirButton = criarBotaoControle("/Controles/seta_dir.png", 100, 100, 200, 100);
        setaBaixoButton = criarBotaoControle("/Controles/seta_baixo.png", 100, 100, 100, 200);

        // Adicionar os botões ao painel
        painelControle.add(mochilaButton);
        painelControle.add(fimTurnoButton);
        painelControle.add(cataButton);
        painelControle.add(setaCimaButton);
        painelControle.add(setaEsqButton);
        painelControle.add(setaDirButton);
        painelControle.add(setaBaixoButton);

        configurarControles();
        

        telaControle.add(painelControle, BorderLayout.CENTER);
        telaControle.pack();
        telaControle.setVisible(true);
    }
    
    public void catar() {
        // Obtém o mapa atual
        Object[][][] mapa = Menu.geracao.estadoMapa;
    	
        Local localAtual = (Local) mapa[jogadorAtual.getPosicao().y][jogadorAtual.getPosicao().x][0]; // Pega a posição atual do jogador no mapa

        if (localAtual instanceof Fruta) {
        	Fruta frutaAtual = (Fruta) localAtual;
        	
            if ( jogadorAtual.pegarFruta(frutaAtual) ) {
            	mapa[jogadorAtual.getPosicao().y][jogadorAtual.getPosicao().x][0] = new Grama();
            	jogadorAtual.resetarForca();
            }
            
        } else if (localAtual instanceof Arvore) {

        	Arvore arvoreAtual = (Arvore) localAtual;
        	
        	if (jogadorAtual.getSeMovimentou()) {
                JOptionPane.showMessageDialog(null, "Aguarde o proximo round!");
                return;
        	}
        	
        	if (!arvoreAtual.temFruto()) {
                JOptionPane.showMessageDialog(null, "A árvore ainda nao tem frutos");
                JOptionPane.showMessageDialog(null, "Faltam: " + (5 - arvoreAtual.getMadura()));
                return;
        	}

            if (arvoreAtual.darFruta(jogadorAtual)) {
            	JOptionPane.showMessageDialog(null, "Pegou a fruta " + arvoreAtual.toString());
            } else {
            	JOptionPane.showMessageDialog(null, "Mochila cheia");
            }
        	
        	
        } else {
            // Caso seja grama ou outro objeto, nada acontece
            JOptionPane.showMessageDialog(null, "Não tem nada para catar aqui!");
        }
    }

    
    public void configurarControles() {
    	
        mochilaButton.addActionListener(e -> {
            alternarMochila(jogadorAtual); // Alterna entre abrir ou fechar a mochila
            atualizarEstadoSetas(); // Atualiza as setas de acordo com o estado da mochila
        });

    	fimTurnoButton.addActionListener(e -> finalizarTurno());
    	
    	cataButton.addActionListener(e -> catar());
    	
        // Exemplo para o botão de mover para cima
        setaCimaButton.addActionListener(e -> moverJogador(new Point(0, -1))); // Clique do botão
        setaEsqButton.addActionListener(e -> moverJogador(new Point(-1, 0)));
        setaDirButton.addActionListener(e -> moverJogador(new Point(1, 0)));
        setaBaixoButton.addActionListener(e -> moverJogador(new Point(0, 1)));
        // Key Binding para mover para cima
        InputMap inputMap = painelControle.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = painelControle.getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke("I"), "mochila");
        actionMap.put("mochila", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
            	alternarMochila(jogadorAtual);
                atualizarEstadoSetas();
            }
        });
        

        inputMap.put(KeyStroke.getKeyStroke("F"), "fimTurno");
        actionMap.put("fimTurno", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
            	finalizarTurno(); 
            }
        });

        // Mapeando teclas para mover para cima
        inputMap.put(KeyStroke.getKeyStroke("W"), "moverCima");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moverCima");
        actionMap.put("moverCima", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(0, -1)); // Move para cima
            }
        });

        // Mapeando teclas para mover para baixo
        inputMap.put(KeyStroke.getKeyStroke("S"), "moverBaixo");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moverBaixo");
        actionMap.put("moverBaixo", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(0, 1)); // Move para baixo
            }
        });

        // Mapeando teclas para mover para esquerda
        inputMap.put(KeyStroke.getKeyStroke("A"), "moverEsquerda");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "moverEsquerda");
        actionMap.put("moverEsquerda", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(-1, 0)); // Move para esquerda
            }
        });

        // Mapeando teclas para mover para direita
        inputMap.put(KeyStroke.getKeyStroke("D"), "moverDireita");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "moverDireita");
        actionMap.put("moverDireita", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!mochilaAberta) moverJogador(new Point(1, 0)); // Move para direita
            }
        });
    }
    
    public JButton criarBotaoControle(String caminhoImagem, int largura, int altura, int x, int y) {
        // Carregar a imagem a partir do caminho fornecido
        ImageIcon icon = new ImageIcon(Menu.class.getResource(caminhoImagem));
        Image img = icon.getImage();
        
        // Redimensionar a imagem para o tamanho desejado
        Image imgRedimensionada = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imgRedimensionada);
        
        // Criar o botão com o ícone redimensionado
        JButton botao = new JButton(iconRedimensionado);
        botao.setText(null);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBounds(x, y, largura, altura);
        
        return botao;
    }
    
    private void atualizarEstadoSetas() {
        if (!mochilaAberta) {
            // Se a mochila está fechada, os botões de seta estão ativos
            setaCimaButton.addActionListener(e -> moverJogador(new Point(0,-1)));
            setaEsqButton.addActionListener(e -> moverJogador(new Point(-1, 0)));
            setaDirButton.addActionListener(e -> moverJogador(new Point(1, 0)));
            setaBaixoButton.addActionListener(e -> moverJogador(new Point(0, 1)));
            

        	fimTurnoButton.addActionListener(e -> finalizarTurno());
        	cataButton.addActionListener(e -> catar());
        } else {
            // Se a mochila está aberta, removemos os listeners
            for (ActionListener al : setaCimaButton.getActionListeners()) {
                setaCimaButton.removeActionListener(al);
            }
            for (ActionListener al : setaEsqButton.getActionListeners()) {
                setaEsqButton.removeActionListener(al);
            }
            for (ActionListener al : setaDirButton.getActionListeners()) {
                setaDirButton.removeActionListener(al);
            }
            for (ActionListener al : setaBaixoButton.getActionListeners()) {
                setaBaixoButton.removeActionListener(al);
            }
            for (ActionListener al : fimTurnoButton.getActionListeners()) {
            	fimTurnoButton.removeActionListener(al);
            }
            for (ActionListener al : cataButton.getActionListeners()) {
            	cataButton.removeActionListener(al);
            }
        }
    }


    private void gerarImagens(Graphics g, int matriz, int tamanhoImagem) {
        ImageIcon canto2 = new ImageIcon(Menu.class.getResource(jogadorAtual.getDado()));
        ImageIcon canto4 = new ImageIcon(Menu.class.getResource(jogadorAtual.imgControle()));
    	
        Image canto2Img = canto2.getImage();
        Image canto4Img = canto4.getImage();
    	
        colocarElemento(g, canto2Img, new Point(2,0), tamanhoImagem);
        colocarElemento(g, canto4Img, new Point(2,2), tamanhoImagem);
    }

    public static void colocarElemento(Graphics g, Image imagem, Point posicao, int tamanho) {
        int x = posicao.x * tamanho;
        int y = posicao.y * tamanho;
        g.drawImage(imagem, x, y, tamanho, tamanho, null);
    }

    public void alternarMochila(Jogador jogador) {
        if (mochilaAberta) {
            fecharMochila();
        } else {
            abrirMochila(jogador);
        }
    }

    public void abrirMochila(Jogador jogador) {
        // Toda vez que abrir a mochila, crie uma nova instância para garantir que ela seja atualizada
        telaMochila = new TelaMochila(jogador);
        
        // Exibe a nova tela da mochila
        telaMochila.setVisible(true);
        mochilaAberta = true;
    }
    
    public void fecharMochila() {
        if (telaMochila != null) {
            telaMochila.setVisible(false);
            telaMochila.dispose(); // Libera os recursos da tela da mochila
            telaMochila = null;    // Define a variável como nula para garantir que seja recriada ao abrir
        }
        mochilaAberta = false;
    }


    private void moverJogador(Point destino) {
        int antigoX = jogadorAtual.getPosicao().x;
        int antigoY = jogadorAtual.getPosicao().y;

        // Calcula as novas coordenadas baseadas no movimento
        int novoX = antigoX + destino.x;
        int novoY = antigoY + destino.y;

        // Obtém o mapa atual
        Object[][][] mapa = Menu.geracao.estadoMapa;

        // Verifica se a nova posição está dentro dos limites do mapa
        if (novoX < 0 || novoX >= mapa[0].length || novoY < 0 || novoY >= mapa.length) {
            JOptionPane.showMessageDialog(null, "Movimento inválido: fora dos limites do mapa!");
            return; // Impede o movimento se estiver fora dos limites
        }

        // Cria o ponto de destino com as novas coordenadas
        Point novaPosicao = new Point(novoX, novoY);
        
        
        
        // Verifica se o jogador tem movimentos suficientes para se mover para o novo destino
        if (jogadorAtual.verificarMovimentos(novaPosicao, mapa)) {
        	
        	if (!jogadorAtual.getPodeSeMover()) {
        		JOptionPane.showMessageDialog(null, "Jogador não pode se mover");
        		return;
        	}
        	
        	if (jogadorAtual.getEmpurrou() && mapa[novaPosicao.y][novaPosicao.x][1] == jogadorProx){
            	JOptionPane.showMessageDialog(null, "Jogador não pode ser empurrado novamente");
        		return;
    		}       	
            // Remove o jogador da posição antiga no mapa
            mapa[antigoY][antigoX][1] = null;

            // Move o jogador para a nova posição considerando o custo
            Point posicaoFinal = jogadorAtual.mover(novaPosicao, mapa);
            
        	if (mapa[posicaoFinal.y][posicaoFinal.x][1] == jogadorProx) {
        		
	        		int forcaJogadorAtacante = jogadorAtual.getForca();
	        		int forcaJogadorDefensor = jogadorProx.getForca();
	        		
	        		if (forcaJogadorAtacante > forcaJogadorDefensor) {
		        		jogadorAtual.setEmpurrou(true);
		        		int fa = (int) Math.round(Math.log(forcaJogadorAtacante) / Math.log(2));
		        		int fd = (int) Math.round(Math.log(forcaJogadorDefensor) / Math.log(2));
		        		int qtsFrutaCai = fa - fd;
		        		System.out.println(qtsFrutaCai);
		        		processarEmpurrarJogador(jogadorAtual, jogadorProx, forcaJogadorAtacante, forcaJogadorDefensor, mapa);
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(null, "Fraco demais para atacar!");
	        		}
	        		
	                mapa[antigoY][antigoX][1] = jogadorAtual;
	                jogadorAtual.setPosicao(new Point(antigoX, antigoY));
	                jogadorProx.droparFrutas();
        	} else {
                // Atualiza o mapa com a nova posição do jogador
                mapa[posicaoFinal.y][posicaoFinal.x][1] = jogadorAtual;
        	}

            jogo.atualizarMapa(mapa);

            // Força a repintura do painel para atualizar o canto 2
            painelControle.repaint();
            jogadorAtual.setSeMovimentou(true);
        } else {
            JOptionPane.showMessageDialog(null, "Movimento inválido: não há movimentos suficientes.");
        }

        // Verifica se os movimentos do jogador acabaram e alterna para o próximo jogador
        //if (jogadorAtual.acabouMovimentos()) {
        //    finalizarTurno();
        //}
    }


    public void finalizarTurno() {
        telaControle.dispose(); // Fecha a tela atual
        jogadorAtual.setMovimento(); // Rola novos movimentos para o próximo turno
        
        // Obtém o mapa atual
        Object[][][] mapa = Menu.geracao.estadoMapa;
        
        boolean j1Ganhou = false;
        boolean j2Ganhou = false;
        
        if (jogadorAtual.getNumero() == 2) {
            j1Ganhou = jogadorProx.getMochila().checarVitoria(qtdMaracujaTotal);
            j2Ganhou = jogadorAtual.getMochila().checarVitoria(qtdMaracujaTotal);

            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa.length; j++) {
                	if (mapa[i][j][0] instanceof Arvore) {
                		Arvore arvore = (Arvore) mapa[i][j][0];
                		arvore.amadurecer();
                	}
                }
            }

    		this.rodada = this.rodada + 1;
        
            if (rodada % 2 == 0) {
                
                Point lugarMaracuja = gerarMaracuja(mapa);

                if (lugarMaracuja != null) {
                	mapa[lugarMaracuja.y][lugarMaracuja.x][0] = new Maracuja(lugarMaracuja, false);
                    jogo.atualizarMapa(mapa);
                }
            }
        }
        
        jogadorAtual.setComeuCoco(false);
        jogadorAtual.setComeuAbacate(false);
        jogadorAtual.setEmpurrou(false);
        
        if (!j1Ganhou && !j2Ganhou) {
        	new TelaControle(jogadorProx, jogadorAtual, jogo, qtdMaracujaInicio, qtdMaracujaTotal, qtdMaracujaCriados, rodada); // Alterna para o próximo jogado
        } else {
        	jogo.dispose();
        }
        
    }
    
    public Point gerarMaracuja(Object[][][] mapa) {
    	if (qtdMaracujaCriados + qtdMaracujaInicio == qtdMaracujaTotal) {
    		return null;
    	}
    	
    	
    	
        int numeroArvore = (int) (Math.random() * Menu.arquivoHandler.getArvores());
        int contador = 0;

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j][0] instanceof Arvore) {
                    
                    if (contador == numeroArvore) {
                        
                        List<Point> casasLivres = new ArrayList<>();
                        
                        int[][] direcoes = {
                            {-1, -1}, {-1, 0}, {-1, 1}, 
                            {0, -1}, {0, 1}, 
                            {1, -1}, {1, 0}, {1, 1}
                        };

                        for (int[] direcao : direcoes) {
                            int novoI = i + direcao[0];
                            int novoJ = j + direcao[1];

                            // Verifica se o ponto está dentro dos limites da matriz
                            if (novoI >= 0 && novoI < mapa.length && novoJ >= 0 && novoJ < mapa[0].length) {
                                
                                if (mapa[novoI][novoJ][0] instanceof Grama) {
                                    casasLivres.add(new Point(novoJ, novoI));
                                }
                            }
                        }

                        // Se houver casas livres
                        if (!casasLivres.isEmpty()) {
                            int indiceAleatorio = (int) (Math.random() * casasLivres.size());
                            qtdMaracujaCriados++;
                            return casasLivres.get(indiceAleatorio);
                        } else {

                            return null;
                        }
                    }
                    contador++;
                }
            }
        }
        
        return null;
    }
    public void processarEmpurrarJogador(Jogador jogadorAtacante, Jogador jogadorDefensor, int forcaJogadorAtacante, int forcaJogadorDefensor, Object[][][] mapa) {
        if (forcaJogadorAtacante > forcaJogadorDefensor) {
            jogadorAtacante.setEmpurrou(true);
            int fa = (int) Math.round(Math.log(forcaJogadorAtacante) / Math.log(2));
            int fd = (int) Math.round(Math.log(forcaJogadorDefensor) / Math.log(2));
            int qtsFrutaCai = fa - fd;
            System.out.println(qtsFrutaCai);

            // Posição do jogador defensor (aquele que foi atacado)
            Point posicaoJogadorDefensor = jogadorDefensor.getPosicao(); 
            int x = posicaoJogadorDefensor.x;
            int y = posicaoJogadorDefensor.y;

            // Começando com um tamanho de área padrão (3x3)
            int tamanhoArea = 3;

            // Variável para rastrear quantas frutas foram colocadas
            int frutasColocadas = 0;

            // Enquanto ainda há frutas para colocar
            while (frutasColocadas < qtsFrutaCai) {
                List<Point> casasLivres = new ArrayList<>();
                // Percorre a área ao redor do jogador defensor com base no tamanho da área
                for (int i = -tamanhoArea / 2; i <= tamanhoArea / 2; i++) {
                    for (int j = -tamanhoArea / 2; j <= tamanhoArea / 2; j++) {
                        int novoX = x + j;
                        int novoY = y + i;

                        // Verifica se o ponto está dentro dos limites da matriz e se é uma casa livre
                        if (novoX >= 0 && novoX < mapa[0].length && novoY >= 0 && novoY < mapa.length) {
                            if (mapa[novoY][novoX][0] instanceof Grama && !(novoX == x && novoY == y)) { // Verifica se a casa é livre e não é a posição do jogador
                                casasLivres.add(new Point(novoX, novoY));
                            }
                        }
                    }
                }

                // Se houver casas livres, remova as frutas da mochila e coloque no mapa
                while (frutasColocadas < qtsFrutaCai && !casasLivres.isEmpty()) {
                    int indiceAleatorio = (int) (Math.random() * casasLivres.size());
                    Point casaSelecionada = casasLivres.get(indiceAleatorio);
                    
                    // Remova a fruta da mochila do jogador defensor
                    Fruta frutaRemovida = jogadorDefensor.removerFruta(); // Remove a fruta da mochila
                    
                    if (frutaRemovida != null) { // Verifica se a remoção foi bem-sucedida
                        frutaRemovida.setPosicao(new Point(casaSelecionada.x, casaSelecionada.y)); // Define a nova posição
                        mapa[casaSelecionada.y][casaSelecionada.x][0] = frutaRemovida; // Coloca a fruta no mapa
                    }

                    casasLivres.remove(indiceAleatorio); // Remove a casa já utilizada
                    frutasColocadas++;
                }

                // Se ainda não colocamos todas as frutas e não há mais casas livres, aumentamos a área
                if (frutasColocadas < qtsFrutaCai) {
                    tamanhoArea += 2; // Aumenta o tamanho da área (de 3x3 para 5x5, etc.)
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fraco demais para atacar!");
        }
    }


}
