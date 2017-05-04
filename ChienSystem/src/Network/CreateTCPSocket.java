package Network;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Controler.EcritureBufferFichier;

public class CreateTCPSocket extends Thread {

	private Socket socketCreer;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerSocket socketServer;
	private boolean type;

	public CreateTCPSocket(ServerSocket socket) throws IOException {
		this.socketServer = socket;
		try {
			this.type = true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CreateTCPSocket(InetAddress address, int port) throws IOException {
		socketCreer = new Socket(address, port);
		try {
			this.type = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			
			if (this.type) {
				this.socketCreer = this.socketServer.accept();
			}
			this.out = new ObjectOutputStream(socketCreer.getOutputStream());
			this.in = new ObjectInputStream(socketCreer.getInputStream());

			while (true) {
				Message message = (Message) in.readObject();
				String userPseudo = message.getSrcPseudo();
				EcritureBufferFichier.ecritureFichier(userPseudo, userPseudo+" : "+ message.getData() + "\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Socket getSocketCreer() {
		return this.socketCreer;
	}

	public ObjectOutputStream getOut() {
		return this.out;
	}

	public ObjectInputStream getIn() {
		return this.in;
	}
	public Boolean getType(){
		return this.type;
	}

}