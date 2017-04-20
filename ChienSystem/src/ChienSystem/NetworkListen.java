package ChienSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkListen extends Thread {

	/* Creation de socket pour chaque client */

	
	private NetworkCreateUDP serveur;
	public NetworkListen(NetworkCreateUDP serveur) throws IOException {
		this.serveur = serveur;
	}


	public void traitements(NetworkCreateUDP serveur) throws IOException {
		FileWriter tabUser = new FileWriter("tabUser");
		try {
			while (true) {
				ObjectInputStream testHello = serveur.recupeObjectInputStream();
				ControlMessage message = (ControlMessage) testHello.readObject();

				if (message.getData().equals("hello")) {
					if (userGood(message.getUserName())) {
						ServerSocket socket = new ServerSocket();
						int port = socket.getLocalPort();
						envoiSocketCreate(serveur,port);
						CreateTCPSocket nouveauSocket = new CreateTCPSocket(socket);
						EcritureBufferFichier.ecritureFichier(tabUser,nouveauSocket.getSocketCreer().getPort()+" "+message.getUserName());
						System.out.println("Connexion avec le client : " + socket.getInetAddress());
					} else {
				//		ErreurPseudo();
					}
				} else if (message.getData().equals("socket_create")) {
					Socket socket = new Socket(message.getUserAdresse(), message.getPort());
					EcritureBufferFichier.ecritureFichier(tabUser,message.getPort()+" "+message.getUserName());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			traitements(this.serveur);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean userGood(String pseudo) {
		if (pseudo.equals(Login.getLogin())) {
			return false;
		} else {
			return true;
		}
	}
	
	private void envoiSocketCreate(NetworkCreateUDP serveur ,int port) throws IOException{
		ControlMessage message = new ControlMessage(Login.getLogin(), InetAddress.getLocalHost(), port, "socket_created");
		ObjectOutputStream outputStream = serveur.recupeObjectOutputStream();
		outputStream.writeObject(message);
	}
	

}
