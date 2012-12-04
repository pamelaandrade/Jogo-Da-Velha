package Sockets;

import java.net.Socket;

import Interface.Principal;

public class JogoClient extends Thread {

	public static final String SERVER_HOSTNAME = "localhost";
	public static final int SERVER_PORT = 2002;

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);

		Principal p = new Principal();
		p.setSocket(socket);

		System.out.println("Conectado ao servidor " + SERVER_HOSTNAME + ":"
				+ SERVER_PORT);

		p.setVisible(true);

	}

}
