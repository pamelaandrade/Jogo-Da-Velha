package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.IllegalSelectorException;

import classes.Jogador;
import classes.JogoVelha;

import Interface.Principal;

public class PartidaSocket implements Runnable {

	private InicioJogo status;
	private Socket jogador1;
	private Socket jogador2;
	private PrintWriter j1Saida;
	private BufferedReader j1Entrada;
	private PrintWriter j2Saida;
	private BufferedReader j2Entrada;
	private Principal p;

	public PartidaSocket() {
		status = InicioJogo.CRIADO;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		status = InicioJogo.TERMINADO;
		try {
			while (!Thread.interrupted()) {
				
				
				String msgSim = j1Entrada.readLine();
				String msgI = j1Entrada.readLine();
				String msgJ = j1Entrada.readLine();
				
				
				if (msgSim == null)
					//System.out.println("Mensagem null");
					break;
				j2Saida.println(msgSim);
				j2Saida.println(msgI);
				j2Saida.println(msgJ);
				j2Saida.flush();
				
				//TODO passar a vez a cada laço
				 msgSim = j2Entrada.readLine();
				 msgI = j2Entrada.readLine();
				 msgJ = j2Entrada.readLine();
				
				
				if (msgSim == null)
					//System.out.println("Mensagem null");
					break;
				j1Saida.println(msgSim);
				j1Saida.println(msgI);
				j1Saida.println(msgJ);
				j1Saida.flush();
					
				
			}
		} catch (IOException ioex) {
			// Problem reading from socket (communication is broken)
		}

		// Communication is broken. Interrupt both listener and sender threads
//		mClientInfo.mClientSender.interrupt();
//		mServerDispatcher.deleteClient(mClientInfo);
	}

	public void setPrimeiroJogador(Socket primeiroSocket) {
		// TODO Auto-generated method stub
		if (status != InicioJogo.CRIADO) {
			throw new IllegalSelectorException();
		}
		this.jogador1 = primeiroSocket;
		try {
			j1Saida = new PrintWriter(new OutputStreamWriter(
					primeiroSocket.getOutputStream()));
			j1Entrada = new BufferedReader(new InputStreamReader(
					primeiroSocket.getInputStream()));
			System.out.println(1);
			j1Saida.println("ESPERANDO");
			j1Saida.flush();
			System.out.println(2);

			// String message = executarEntrada.readLine();

			// new Principal().setVisible(true);
			// JogoClient jc = new JogoClient();
			// jc.run();

			status = InicioJogo.ESPERANDO;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSegundoJogador(Socket segundoSocket) {
		if (status != InicioJogo.ESPERANDO) {
			throw new IllegalStateException();
		}
		this.jogador2 = segundoSocket;
		try {
			j2Saida = new PrintWriter(new OutputStreamWriter(
					segundoSocket.getOutputStream()));
			j2Entrada = new BufferedReader(new InputStreamReader(
					segundoSocket.getInputStream()));
			System.out.println(1);
			j2Saida.println("COMECANDO");
			j2Saida.flush();
			System.out.println(2);
			status = InicioJogo.COMECANDO;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// new Principal().setVisible(true);
		// JogoClient jc = new JogoClient();
		// jc.run();
		// TODO Avisar os dois jogadores que o jogo começou

	}
	
//	void jogar(){
//		JogoVelha jv = new JogoVelha(null, null);
//	}

}
