package network;

public class User {
	private String name;
	private CreateTCPSocket socket;

	
	public User( String name, CreateTCPSocket socket2){
		this.name = name;
		this.socket = socket2;
	}
	public String getName() {
		return name;
	}
	public CreateTCPSocket getSocket() {
		return socket;
	}
	
	

}
