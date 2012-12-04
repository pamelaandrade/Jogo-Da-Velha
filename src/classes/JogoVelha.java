package classes;

import javax.swing.JOptionPane;
import Interface.Principal;

public class JogoVelha {

	/* ATRIBUTOS */
	// contem os jogadores do jogo
	private Jogador jogador1;
	private Jogador jogador2;

	// contem o tabuleiro do jogo
	private Tabuleiro tabuleiro = new Tabuleiro(3, 3);

	// contem se � ou nao a vez do primeiro jogador
	private boolean vezDoJogador1;
	// conta o numero de jogadas em uma partida
	private int contaJogadas;
	// conta o numero de partidas empatadas
	private int contaEmpates;
	// contem se e ou nao o fim do jogo
	private boolean fimDoJogo;
	// contem o jogador que venceu o jogo
	private Jogador vencedor;
	//private boolean sncpu;
	

	// Creates a new instance of JogoVelha
	public JogoVelha(Jogador jogador1, Jogador jogador2) {

		// recebe os jogadores
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		//this.sncpu = false; // computador jogando

	}

	// Define as configura��es de inicializacao do jogo
//	public void iniciar() {
//		this.iniciar();
//	}

//	public boolean isSncpu() {
//		return this.sncpu;
//	}
//
//	// Retorna se o computador joga ou nao
//	public void setSncpu(boolean sncpu) {
//		this.sncpu = sncpu;
//	}

	public void iniciar() {
		this.vezDoJogador1 = true;
		this.contaJogadas = 0;
		this.fimDoJogo = false;
		this.vencedor = null;
		this.tabuleiro.esvaziarTabuleiro();
		//this.sncpu = sncpu;

	}

	// Retorna o jogador que esta na vez de jogar
	public Jogador jogadorDaVez() {
		Jogador jogadorVez = null;

		if (this.vezDoJogador1) {
			jogadorVez = jogador1;
		} else {
			jogadorVez = jogador2;
		}
		return jogadorVez;
	}

	// Retorna se uma determinada jogada � valida ou nao
	public boolean JogadaValida(int linha, int coluna) {
		return (linha >= 0 && linha <= 2 && coluna >= 0 && coluna <= 2 && this.tabuleiro
				.estaLivre(linha, coluna));
	}

	// Efetivar uma jogada e retornar o simbolo do jogador que jogou
	public char fazerJogada(int linha, int coluna) {

		// se a jogada � valida
		if (JogadaValida(linha, coluna)) {

			// pegar o simbolo do jogador da vez
			char simbolo = jogadorDaVez().getSimbolo();
				
			// colocar o simbolo no tabuleiro do jogador da vez
			tabuleiro.setPega(linha, coluna, simbolo);

			// contar o numero de jogadas
			contaJogadas++;

			// verificar se o jogo nao terminou
			if (!verificaFimDoJogo()) {
				// muda de jogador
				trocaVez();
			} else {
				this.fimDoJogo = true;
				if (this.vencedor != null) { // caso empatou..
					this.vencedor.incrementaVitorias();
					String vence = "Terminou partida! Jogador " + vencedor
							+ " ganhou!";
					JOptionPane.showMessageDialog(null, vence);
					tabuleiro.esvaziarTabuleiro();
					
				}
			}
			// retornar o simbolo do jogador
			return simbolo;

		}
		return ' ';
	}

	public void trocaVez() {
		vezDoJogador1 = !vezDoJogador1;
		
	}

	public void verificaVencedor() {
		if (this.vezDoJogador1) {
			this.vencedor = this.jogador1;
		} else {
			this.vencedor = this.jogador2;
		}
	}

	/** Verificar se o jogo terminou e definir o vencedor ou empate */
	public boolean verificaFimDoJogo() {

		if (this.contaJogadas >= 5) {

			int linha;
			int coluna;
			char c1, c2, c3;

			// verifica em linha
			coluna = 0;
			for (linha = 0; linha < 3; linha++) {
				c1 = tabuleiro.getPega(linha, coluna);
				c2 = tabuleiro.getPega(linha, coluna + 1);
				c3 = tabuleiro.getPega(linha, coluna + 2);

				if ((c1 == c2) && (c1 == c3) && (c1 != ' ')) {
					this.verificaVencedor();
					return true;
				}
			}

			// verifica em coluna
			linha = 0;
			for (coluna = 0; coluna < 3; coluna++) {
				c1 = tabuleiro.getPega(linha, coluna);
				c2 = tabuleiro.getPega(linha + 1, coluna);
				c3 = tabuleiro.getPega(linha + 2, coluna);
				if ((c1 == c2) && (c1 == c3) && (c1 != ' ')) {
					this.verificaVencedor();
					return true;
				}
			}

			// verifica em diagonal
			linha = 0;
			coluna = 0;
			c1 = tabuleiro.getPega(linha, coluna);
			c2 = tabuleiro.getPega(linha + 1, coluna + 1);
			c3 = tabuleiro.getPega(linha + 2, coluna + 2);
			if ((c1 == c2) && (c1 == c3) && (c1 != ' ')) {
				this.verificaVencedor();
				return true;
			}

			// verifica em linha
			linha = 0;
			coluna = 0;
			c1 = tabuleiro.getPega(linha, coluna + 2);
			c2 = tabuleiro.getPega(linha + 1, coluna + 1);
			c3 = tabuleiro.getPega(linha + 2, coluna);
			if ((c1 == c2) && (c1 == c3) && (c1 != ' ')) {
				this.verificaVencedor();
				return true;
			}

			// verifica empate
			if (this.contaJogadas == 9) {
				contaEmpates++;
				return true;
			}
		}
		// caso contrario...
		return false;
	}

	// Informa se houve vencedor
	public boolean houveVencedor() {
		return (vencedor != null);
	}

	// Zerar o numero de partidas empatadas
	public void zeraEmpates() {
		contaEmpates = 0;
	}

	// Retornar o vencedor do jogo
	public boolean isFimDoJogo() {
		return fimDoJogo;
	}

	// Retornar o vencedor
	public Jogador getVencedor() {
		return vencedor;
	}

	// Retorna o tabuleiro
	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	// Retornar o jogador1
	public Jogador getJogador1() {
		return jogador1;
	}

	// Retornar o jogador2
	public Jogador getJogador2() {
		return jogador2;
	}

	// Retorna contagem de empates
	public int getContaEmpates() {
		return contaEmpates;
	}

}
