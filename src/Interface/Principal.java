package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import classes.Jogador;
import classes.JogoVelha;

import Sockets.JogoClient;
import Sockets.JogoListener;


// a intera��o esta dentro da interface, entao inicia-se aqui
@SuppressWarnings("serial")
public class Principal extends javax.swing.JFrame {
	/**
	 * INICIALIZA��O DO JOGO:
	 */

	//private JogoClient jc;
	private Socket socket;
	private BufferedReader inFromServer = null;
	private PrintWriter outToServer = null;
	
	

	public PrintWriter getOutToServer() {
		return outToServer;
	}

	public void setOutToServer(PrintWriter outToServer) {
		this.outToServer = outToServer;
	}

	public BufferedReader getInFromServer() {
		return inFromServer;
	}

	public Socket getSocket() {
		return socket;
	}
	
	

	public void setSocket(Socket socket) {
		this.socket = socket;
		JogoListener jl = new JogoListener(this);
		Thread tjl = new Thread(jl);
		
		
		try {
			inFromServer = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tjl.start();
		try {
			outToServer = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Principal().setVisible(true);
			}
		});
	}

	// Declara��o das vari�veis:
	private javax.swing.JButton btIniciar;
	private javax.swing.JButton btSair;
	private JButton[][] botoes;
	

	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JMenuItem jmnIniciar;
	private javax.swing.JMenu jmnJogo;
	private javax.swing.JMenuItem jmnJogoVelha;
	private javax.swing.JMenuItem jmnMostrarRanking;
	private javax.swing.JRadioButtonMenuItem jmnPC;
	private javax.swing.JRadioButtonMenuItem jmnPP;
	private javax.swing.JMenu jmnRanking;
	private javax.swing.JMenuItem jmnSair;
	private javax.swing.JMenu jmnSobre;
	private javax.swing.JMenu jmnTipo;
	private javax.swing.JMenuItem jmnZerarRanking;
	private javax.swing.JLabel lbJogo;
	private javax.swing.ButtonGroup mnGrupo;
	private javax.swing.JPanel pnBotoes;
	private javax.swing.JPanel pnJogadas;

	// criar os jogadores
	private Jogador jogador1 = new Jogador('X');
	private Jogador jogador2 = new Jogador('0');

	// criar o jogo
	private JogoVelha jogoVelha = new JogoVelha(jogador1, jogador2);
	

	public JogoVelha getJogoVelha() {
		return jogoVelha;
	}

	public void setJogoVelha(JogoVelha jogoVelha) {
		this.jogoVelha = jogoVelha;
	}

	// Criando novo formulario JogoVelhaGUI
	public Principal() {
		initComponents();
		setSize(300, 300); // determina o tamanho da interface;
	}

	// metodo limpa tela
	public void limparTela() {
		for (int count = 0; count < pnJogadas.getComponentCount(); count++) {
			if (pnJogadas.getComponent(count) instanceof JButton) {
				JButton botao = (JButton) (pnJogadas.getComponent(count));
				botao.setText(" ");
			}
		}
	}

	/* METODO chamado de dentro do construtor para inicializar o formulario */
	public void initComponents() {

		// Conjunto de bot�es;
		mnGrupo = new javax.swing.ButtonGroup();

		// Titulo jogo;
		lbJogo = new javax.swing.JLabel();

		// Painel dos botoes;
		pnBotoes = new javax.swing.JPanel();

		// Botao de iniciar;
		btIniciar = new javax.swing.JButton();

		// Botao de sair;
		btSair = new javax.swing.JButton();

		// Painel do jogo;
		pnJogadas = new javax.swing.JPanel();

		
		botoes = new JButton[3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				final JButton b = new JButton();
				final int x =i;
				final int y =j;
				botoes[i][j] = b;
				b.setFont(new java.awt.Font("Tahoma", 0, 24));
				b.setName(String.format("%d%d",x,y)); 
				b.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {

						char sim = jogoVelha.fazerJogada(x, y);
						b.setText("" + sim);
						b.setEnabled(false);
						avisaJogada(sim, x,y);
					}
				});
				pnJogadas.add(b);
			}
		}


		
		
		/* BARRA DE MENU SUPERIOR */

		// Barra do menu;
		jMenuBar1 = new javax.swing.JMenuBar();

		// Botao JOGO do menu;
		jmnJogo = new javax.swing.JMenu();

		// Componente do botao jogo, botao INICIAR;
		jmnIniciar = new javax.swing.JMenuItem();

		// Componente do bot�o jogo, bot�o TIPO DE JOGO;
		jmnTipo = new javax.swing.JMenu();

		// Botao do menu escolha PESSOA e PESSOA;
		jmnPP = new javax.swing.JRadioButtonMenuItem();

		// Botao do menu escolha PESSOA e COMPUTADOR;
		jmnPC = new javax.swing.JRadioButtonMenuItem();

		// Linha de separacao do botao sair;
		jSeparator1 = new javax.swing.JSeparator();

		// Componente do botao jogo, botao SAIR;
		jmnSair = new javax.swing.JMenuItem();

		// Botao RANKING do menu;
		jmnRanking = new javax.swing.JMenu();

		// Componente do bot�o ranking, botao MOSTRAR RANKING;
		jmnMostrarRanking = new javax.swing.JMenuItem();

		// Componente do botao ranking, botao ZERAR RANKING;
		jmnZerarRanking = new javax.swing.JMenuItem();

		// Botao SOBRE JOGO do menu;
		jmnSobre = new javax.swing.JMenu();

		// Componente do botao sobreojogo, botao JOGO DA VELHA;
		jmnJogoVelha = new javax.swing.JMenuItem();

		/* CONFIGURACAO DOS BOTOES */

		// Serve para sair da aplicacao ao iniciar uma funcao se fechamento;
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// Titulo da janela do jogo;
		setTitle("Trabalho - Jogo da Velha");

		// Declarando qual sera a fonte usada no jogo;
		lbJogo.setFont(new java.awt.Font("Tahoma", 0, 36));

		// Mudan�a da cor do rotulo ao clicar em um botao;
		lbJogo.setForeground(new java.awt.Color(0, 51, 204));

		// Posicao onde se encontrara as palavras dentro dos botoes;
		lbJogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		// Titulo dentro do painel do jogo;
		lbJogo.setText("JOGO DA VELHA");

		// Retorna o conteudo do paine adicionado que seria o Titulo do jogo a
		// um layout;
		getContentPane().add(lbJogo, java.awt.BorderLayout.NORTH);

		// Serve para controlar as posicoes dos botoes adicionados no painel;
		pnBotoes.setLayout(new java.awt.GridLayout(1, 0));

		/* CONFIGRACAO DO BOTAO INICIAR DO PAINEL PRINCIPAL */
		// Fonte:
		btIniciar.setFont(new java.awt.Font("Tahoma", 1, 11));
		// Texto do botao:
		btIniciar.setText("Iniciar Jogo");
		// Adiciona uma funaao ao botao:
		btIniciar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				iniciarJogo(evt);
			}
		});

		// Adiciona o botao INICIAR ao painel;
		pnBotoes.add(btIniciar);

		/* CONFIGURACAO DO BOT�O SAIR DO PAINEL PRINCIPAL */
		// Fonte:
		btSair.setFont(new java.awt.Font("Tahoma", 1, 11));
		// Texto do botao:
		btSair.setText("Sair do Jogo");
		// Adiciona uma funcao ao botao:
		btSair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sair(evt);
			}
		});

		// Adiciona o botao SAIR ao painel:
		pnBotoes.add(btSair);

		// Retorna o conteudo do paine adicionado que seria os botoes acima;
		getContentPane().add(pnBotoes, java.awt.BorderLayout.SOUTH);

		// Serve para controlar as jogadas dos botoes;
		pnJogadas.setLayout(new java.awt.GridLayout(3, 3));



		// Retorna o conteudo do paine adicionado que seria as jogadas
		// efetuadas;
		getContentPane().add(pnJogadas, java.awt.BorderLayout.CENTER);

		/* CONFIGURACOES DOS BOTOES DA BARRA DE MENU */

		/* BOTAO JOGO: */
		jmnJogo.setText("Jogo");
		// Define as teclas que chama a acao do botao selecionado:
		jmnIniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_F2, 0));

		/* Sub botao do jogo: INICIAR */
		// Texto do botao:
		jmnIniciar.setText("Iniciar");
		// Adiciona funcao ao botao relacionado:
		jmnIniciar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				iniciarJogo(evt);
			}
		});
		// Adiciona botao ao botao do menu:
		jmnJogo.add(jmnIniciar);

		/* Sub botao do jogo: TIPO DE JOGO */
		// Texto do botao:
		jmnTipo.setText("Tipo de jogo");

		/* Sub botao do tipo de jogo: PESSOA E PESSOA */
		// Adiciona botao ao bot�o tipo de jogo:
		mnGrupo.add(jmnPP);
		// Recebe valor para inicializar:
		jmnPP.setSelected(true);
		// Texto da opcao:
		jmnPP.setText("Pessoa vs Pessoa");
		// Adiciona funcao ao botao relacionado:
		jmnPP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//jogoVelha.setSncpu(false);
			}
		});
		// Adiciona escolha ao botao:
		jmnTipo.add(jmnPP);

		/* Sub botao do tipo de jogo: PESSOA E PESSOA */
		// Adiciona botao ao botao tipo de jogo:
		mnGrupo.add(jmnPC);
		// Texto da opcao:
		jmnPC.setText("Pessoa vs Computador");
		// Adiciona funcao ao botao relacionado:
		jmnPC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				//jogoVelha.setSncpu(true);
			}
		});
		// Adiciona escolha ao botao:
		jmnTipo.add(jmnPC);

		// Adiciona botao ao botao jogo:
		jmnJogo.add(jmnTipo);
		// Adiciona separador entre botao tipo e sair:
		jmnJogo.add(jSeparator1);

		/* Sub botao do jogo: SAIR */
		// Texto do botao:
		jmnSair.setText("Sair");
		// Adiciona funcao ao botao relacionado:
		jmnSair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sair(evt);
			}
		});
		// Adiciona botao ao botao do menu:
		jmnJogo.add(jmnSair);

		// Adiciona botao a barra menu:
		jMenuBar1.add(jmnJogo);

		/* BOT�O RANKING: */
		jmnRanking.setText("Ranking");

		/* Sub botao do ranking: MOSTRAR RANKING */
		// Texto do botao:
		jmnMostrarRanking.setText("Mostrar Ranking");
		// Adiciona funcao ao botao relacionado:
		jmnMostrarRanking
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						mostrarRanking(evt);
					}
				});
		// Adiciona botao ao botao do menu:
		jmnRanking.add(jmnMostrarRanking);

		/* Sub botao do ranking: ZERAR RANKING */
		// Texto do botao:
		jmnZerarRanking.setText("Zerar Ranking");
		// Adiciona funcao ao botao relacionado:
		jmnZerarRanking.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				zerarRanking(evt);
			}
		});
		// Adiciona botao ao botao do menu:
		jmnRanking.add(jmnZerarRanking);

		// Adiciona botao a barra menu:
		jMenuBar1.add(jmnRanking);

		/* BOT�O SOBRE: */
		jmnSobre.setText("Sobre");

		/* Sub botao do ranking: MOSTRAR RANKING */
		// Texto do botao:
		jmnJogoVelha.setText("Jogo da Velha");
		// Adiciona funcao ao botao relacionado:
		jmnJogoVelha.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showAbout(evt);
			}
		});

		// Adiciona botao ao botao sobre:
		jmnSobre.add(jmnJogoVelha);

		// Adiciona botao a barra menu
		jMenuBar1.add(jmnSobre);

		// Aplica a barra de menu:
		setJMenuBar(jMenuBar1);

		pack(); // Metodo que ajusta tamanho da janela;
	}

	/* METODOS: */

	// Metodo que mostra o ranking:
	private void mostrarRanking(java.awt.event.ActionEvent evt) {

		String rank;
		rank = "Vitorias do Jogador 1:  " + jogador1.getVitorias() + "\n"
				+ "Vitorias do Jogador 2:  " + jogador2.getVitorias() + "\n"
				+ "Empates :  " + jogoVelha.getContaEmpates();
		JOptionPane.showMessageDialog(null, rank, "Ranking", 1);
	}

	// Metodo que zera o ranking:
	private void zerarRanking(java.awt.event.ActionEvent evt) {
		jogador1.zerarVitorias();
		jogador2.zerarVitorias();
		jogoVelha.zeraEmpates();
	}

	// Metodo sobre o jogo da velha(mensagem):
	private void showAbout(java.awt.event.ActionEvent evt) {

		String sobre = "Jogo da Velha\nAlunas:  Alessadra Ribeiro\n                Pamela Andrade\n\n";
		JOptionPane.showMessageDialog(null, sobre);
	}

	// Metodo que inicializacao de jogo:
	private void iniciarJogo(java.awt.event.ActionEvent evt) {
		//jogoVelha.iniciar(this.jogoVelha.isSncpu());
		// limpa a tela
		jogoVelha.getJogador1();
		jogoVelha.getJogador2();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
		botoes[i][j].setEnabled(true);
			}
		}
		
	
		
		this.limparTela();
	}

	// FIXME mudar vez do jogador
	private char jogadorVez(java.awt.event.ActionEvent evt) {
		Jogador jogador = jogoVelha.jogadorDaVez();
		return jogador.getSimbolo();
	}

	// FIXME controlar fim e vencedor do jogo
	private void controlaFimDeJogo(java.awt.event.ActionEvent evt) {
		jogoVelha.verificaFimDoJogo();
		jogoVelha.verificaVencedor();
	}

	// Metodo de sair:
	private void sair(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}
	
	public void setErro(Socket segundoSocket){
		//if();
		
	}
	
	public void avisaJogada(char sim, int i, int j){
		System.out.printf("sim: %c, i=%d, j=%d\n",sim,i,j);
		if(socket!= null){
			outToServer.println(sim);
			outToServer.println(i);
			outToServer.println(j);
			outToServer.flush();

		}
		
	}

	public void fazerJogadaRemota(int i, int j) {
		// TODO Auto-generated method stub
		final JButton b;
		b = botoes[i][j] ;
		char sim = jogoVelha.fazerJogada(i, j);
		b.setText("" + sim);
		b.setEnabled(false);
	}

	public void trocaVez() {
jogoVelha.trocaVez();		
	}
}
