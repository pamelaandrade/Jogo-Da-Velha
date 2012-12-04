package Sockets;



import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class JogoSender {

	/**
	 * @param args
	 */
	
	private JogoInfo mClientInfo;
	private PrintWriter mOut;
	public JogoSender(JogoInfo aJogoInfo, PrintWriter mOut) throws IOException {
		super();
		this.mClientInfo = aJogoInfo;
		Socket socket = aJogoInfo.mSocket;
		this.mOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));;
	}
	
	
	

}
