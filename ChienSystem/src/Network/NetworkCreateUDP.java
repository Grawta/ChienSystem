package Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkCreateUDP extends ServerSocket{
	private DatagramSocket serveur;
	private Socket sockCreer;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private  int portControl; //port pour le serveur 

	public NetworkCreateUDP(int portControl) throws IOException{
		try {
			init(portControl);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void init(int port) throws IOException {
		this.serveur = new DatagramSocket(port);
	}
	
	
	
	public DatagramSocket getServeur() {
		return serveur;
	}

	public Socket getSockCreer() {
		return sockCreer;
	}


	public ObjectInputStream recupObjectInputStream() {
        byte[] incomingData = new byte[1024];
       DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            try {
                this.serveur.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                return is;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    
	public ObjectOutputStream recupObjectOutputStream() throws IOException{
		int data = 1024;
		ByteArrayOutputStream in = new ByteArrayOutputStream(data);
		ObjectOutputStream os = new ObjectOutputStream(in);
		return os;
	}
	
}
	



