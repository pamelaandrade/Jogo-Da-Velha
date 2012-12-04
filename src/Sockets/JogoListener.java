package Sockets;

import java.io.BufferedReader;
import java.io.IOException;

import Interface.Principal;
import classes.JogoVelha;

public class JogoListener implements Runnable {

	private Principal p;
	//private JogoVelha jv;

	public JogoListener(Principal p) {
		super();
		this.p = p;
		//jv = p.getJogoVelha();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader entrada = p.getInFromServer();
		try {
			// TODO x ou 'O'
			String msgStart = entrada.readLine();
			
//			if(msgStart.equals("ESPERANDO")){
//				p.trocaVez();
//			}
//			
			while (!Thread.interrupted()) {
				String msgSim = entrada.readLine();
				String msgI = entrada.readLine();
				String msgJ = entrada.readLine();
				int i = Integer.parseInt(msgI);
				int j = Integer.parseInt(msgJ);

				if (msgSim == null)

					break;

				System.out.printf(
						"Jogo Listener informa: sim =%s i= %d  j=%d\n", msgSim,
						i, j);

				//jv.fazerJogada(i, j);
				p.fazerJogadaRemota(i, j);
				

			}
		} catch (IOException ioex) {
			// Problem reading from socket (communication is broken)

			ioex.printStackTrace();
		}
	}

}
