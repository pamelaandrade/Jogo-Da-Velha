package Sockets;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class JogoServer {
	public static final int LISTENING_PORT = 2002;
		public static void main(String[] args) throws Exception {
		// Open server socket for listening
		ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);

		System.out.printf("Jogo da Velha.\nstarted on port %s:%d. \n ", InetAddress
				.getLocalHost().getHostAddress(), LISTENING_PORT);

		while (!Thread.interrupted()) {

			PartidaSocket i= new PartidaSocket();
			Socket primeiroSocket = serverSocket.accept();
			i.setPrimeiroJogador(primeiroSocket);
			System.out.println("Primeiro jogador , esperando jogador 2...");

			Socket segundoSocket = serverSocket.accept();
			i.setSegundoJogador(segundoSocket);
			System.out.println("Segundo jogador  ....");
			
			Thread t = new Thread(i);
			t.start();
			
			System.out.printf("Inicia Jogo");
			
			
			
			
			
		}

	}

}
