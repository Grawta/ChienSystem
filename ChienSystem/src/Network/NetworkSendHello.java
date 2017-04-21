package Network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import ChienSystem.*;


public class NetworkSendHello {

	public NetworkSendHello(NetworkCreateUDP global) throws IOException {
		init(global);
	}

	private void init(NetworkCreateUDP global) throws IOException {
		ControlMessage message = new ControlMessage(Login.getLogin(), InetAddress.getLocalHost(), 15530, "hello");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		global.getServeur().setBroadcast(true);
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
