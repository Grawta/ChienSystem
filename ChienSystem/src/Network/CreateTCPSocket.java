package Network;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Controler.EcritureBufferFichier;

public class CreateTCPSocket extends Thread{

	private Socket socketCreer;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerSocket socketServer;
	private boolean type;

	public CreateTCPSocket(ServerSocket socket) throws IOException {
		this.socketServer = socket;
		try {
			type = true;
			this.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CreateTCPSocket(InetAddress address, int port) throws IOException {
		Socket socket = new Socket(address, port);
		try {
			type = false;
			this.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			if (type) {
				this.socketCreer = this.socketServer.accept();
			}
			this.in = new ObjectInputStream(socketCreer.getInputStream());
			this.out = new ObjectOutputStream(socketCreer.getOutputStream());

			while (true) {
				Message message = (Message) in.readObject();
				String userPseudo = message.getSrcPseudo();
				FileWriter fUserPseudo = new FileWriter(userPseudo);
				EcritureBufferFichier.ecritureFichier(fUserPseudo, message.getData());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Socket getSocketCreer() {
		return socketCreer;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

}