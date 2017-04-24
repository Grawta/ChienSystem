package Network;

import java.net.Socket;

public class User {
	private String name;
	private Socket socket;
	public User(String name, Socket socket){
		this.name= name;
		this.socket = socket;
		
	}
	public String getName() {
		return name;
	}
	public Socket getSocket() {
		return socket;
	}

}
