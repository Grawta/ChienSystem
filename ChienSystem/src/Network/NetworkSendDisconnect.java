package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import Controler.Login;

public class NetworkSendDisconnect {

	public static void envoieDisconnect(NetworkCreateUDP global) throws IOException{
		ControlMessage message = new ControlMessage(Login.getLogin(), InetAddress.getLocalHost(), 15530, "bye");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(outputStream);
			os.writeObject(message);
			byte[] buffer = outputStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"),global.getServeur().getLocalPort());
			global.getServeur().send(sendPacket);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
