package Controler;

import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Network.ControlMessage;
import Network.CreateTCPSocket;
import Network.Message;
import Network.NetworkGlobal;
import View.BackGroundImage;
import View.IhmLogin;

public class ControlerGolbal extends Thread {
	private Login login;

	public ControlerGolbal() {
		this.start();
	}

	public void run() {
		while (true) {
			ActionEvent arg0 = null;
			actionPerformed(arg0);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if (src == IhmLogin.getConnectButton()) {
			try {
				NetworkGlobal network = new NetworkGlobal();
				this.login = new Login(IhmLogin.getFieldLog().getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (src == BackGroundImage.getImagePanel().getSendLabel()) {
			String text = BackGroundImage.getImagePanel().getConvTextField().getText();
			String pseudo = null; // A MODIFIER NE PEUT PAS RESTER
											// COMME CA
			try {
				sendRequest(text, pseudo);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		} else {

		}
	}

	private void sendRequest(String text, String pseudo) throws UnknownHostException {
		Message message = new Message(Message.DataType.Text, text,pseudo, this.login.getLogin());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = socket.getOut();
		try {
			os.writeObject(message);
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
