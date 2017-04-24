package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;

import ChienSystem.Login;

public class NetworkSendDisconnect {

	public void envoieDisconnect(NetworkCreateUDP global) throws IOException{
		ControlMessage message = new ControlMessage(Login.getLogin(), InetAddress.getLocalHost(), 15530, "disconect");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		global.getServeur().setBroadcast(true);
		try {
			os = new ObjectOutputStream(outputStream);
			os.writeObject(message);
			byte[] buffer = outputStream.toByteArray();
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"),global.getServeur().getLocalPort());
			global.getServeur().send(sendPacket);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
