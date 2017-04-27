package Network;

import java.io.IOException;

public class NetworkGlobal {
	private NetworkListen listen;
	private NetworkCreateUDP serveur ;
	public NetworkGlobal() throws IOException{
		serveur = new NetworkCreateUDP(15530);
		this.listen = new NetworkListen(serveur);
		listen.start();
	}
	public NetworkListen getListen() {
		return listen;
	}
	public NetworkCreateUDP getServeur() {
		return serveur;
	}

}
