package network;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import chienSystem.*;
import controler.*;
import view.BackGroundImage;
import view.ImagePanelA;

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
		try {
			while (true) {
				ObjectInputStream testHello = serveur.recupObjectInputStream();
				ControlMessage message = (ControlMessage) testHello.readObject();
				if (userGood(message.getUserName())) {
					if (message.getData().equals("hello")) {
							ServerSocket socket = new ServerSocket(0);
							int port = socket.getLocalPort();
							InetAddress ip = message.getUserAdresse();
							envoiSocketCreate(serveur, port, ip);
							CreateTCPSocket nouveauSocket = new CreateTCPSocket(socket);
							nouveauSocket.start();
							this.userList.ajoutUser(message.getUserName(), nouveauSocket);
							UpdateListUser.miseAJour(BackGroundImage.getImagePanel().getDefaultList(),this.userList);
						
					} else if (message.getData().equals("socket_created")) {
						while(BackGroundImage.getImagePanel()==null){sleep(1);}
						CreateTCPSocket socket = new CreateTCPSocket(message.getUserAdresse(), message.getPort());
						socket.start();
						if (socket==null){System.out.println("SOCKET NUL");}
						this.userList.ajoutUser(message.getUserName(), socket);
						if (userList==null){System.out.println("userList NUL");}
						if (BackGroundImage.getImagePanel()==null){System.out.println("imagePanel NUL");}
						UpdateListUser.miseAJour(BackGroundImage.getImagePanel().getDefaultList(),this.userList);
					} else if (message.getData().equals("bye")) {
						this.testReceiveJUnit(message); 
						BackGroundImage.getImagePanel().getConvTextField().append("\n"+message.getUserName()+" s'est déconnecté(e) et ne recevra plus vos messages") ;
						this.userList.removeUser(message.getUserName());
						UpdateListUser.miseAJour(BackGroundImage.getImagePanel().getDefaultList(),this.userList);			
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void testReceiveJUnit(ControlMessage message) {
		// TODO Auto-generated method stub		
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
