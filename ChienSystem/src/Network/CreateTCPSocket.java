package Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateTCPSocket {
	
	private Socket socketCreer;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public CreateTCPSocket(ServerSocket socket) throws IOException {
		this.socketCreer = socket.accept();
		this.in = new ObjectInputStream(socketCreer.getInputStream());
		this.out = new ObjectOutputStream(socketCreer.getOutputStream());
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