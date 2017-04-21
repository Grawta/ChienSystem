package Network;

import java.io.IOException;

public class NetworkGlobal {
	public NetworkGlobal() throws IOException{
		NetworkCreateUDP serveur = new NetworkCreateUDP(15530);
		NetworkListen listen = new NetworkListen(serveur);
		listen.start();
	}

}
