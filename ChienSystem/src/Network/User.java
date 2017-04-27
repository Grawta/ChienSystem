package Network;

import java.net.Socket;

public class User {
	private String name;
	private CreateTCPSocket socket;
	public User(String name, CreateTCPSocket socket){
		this.name= name;
		this.socket = socket;
		
	}
	public String getName() {
		return name;
	}
	public CreateTCPSocket getSocket() {
		return socket;
	}

}
