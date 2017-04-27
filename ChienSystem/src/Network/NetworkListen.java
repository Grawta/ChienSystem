package Network;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import ChienSystem.*;
import Controler.*;
import View.BackGroundImage;
import View.ImagePanelA;

public class NetworkListen extends Thread {

	/* Creation de socket pour chaque client */

	private NetworkCreateUDP serveur;
	private UserList userList;

	public UserList getuserList() {
		return userList;
	}

	public NetworkListen(NetworkCreateUDP serveur) throws IOException {
		this.serveur = serveur;
		this.userList = new UserList();
	}

	private void traitements(NetworkCreateUDP serveur) throws IOException {
		UserList userList = new UserList();
		try {
			while (true) {
				ObjectInputStream testHello = serveur.recupObjectInputStream();
				ControlMessage message = (ControlMessage) testHello.readObject();
				if ((message.getUserAdresse() != SelfAddress.getLocalHostLANAddress())) {
					if (message.getData().equals("hello")) {
						if (userGood(message.getUserName())) {
							ServerSocket socket = new ServerSocket(0);
							int port = socket.getLocalPort();
							InetAddress ip = message.getUserAdresse();
							envoiSocketCreate(serveur, port, ip);
							CreateTCPSocket nouveauSocket = new CreateTCPSocket(socket);
							this.userList.ajoutUser(message.getUserName(), nouveauSocket);
							UpdateListUser.miseAJour(BackGroundImage.getImagePanel().getDefaultList(),this.userList);
							
							System.out.println("Connexion avec le client : " + socket.getInetAddress());
						} else {
							// ErreurPseudo();
						}
					} else if (message.getData().equals("socket_created")) {
						CreateTCPSocket socket = new CreateTCPSocket(message.getUserAdresse(), message.getPort());
						this.userList.ajoutUser(message.getUserName(), socket);
						UpdateListUser.miseAJour(BackGroundImage.getImagePanel().getDefaultList(),this.userList);
					} else if (message.getData().equals("disconnect")) {
						this.userList.removeUser(message.getUserName());

					}
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

	private void envoiSocketCreate(NetworkCreateUDP serveur, int port, InetAddress ip) throws IOException {
		ControlMessage message = new ControlMessage(Login.getLogin(), SelfAddress.getLocalHostLANAddress(), port,
				"socket_created");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		os = new ObjectOutputStream(outputStream);
		os.writeObject(message);
		byte[] buffer = outputStream.toByteArray();
		DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, serveur.getServeur().getLocalPort());
		serveur.getServeur().send(sendPacket);
		os.flush();
	}

}
