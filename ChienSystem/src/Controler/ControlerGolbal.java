package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Network.ControlMessage;
import Network.CreateTCPSocket;
import Network.Message;
import Network.NetworkGlobal;
import Network.User;
import View.BackGroundImage;
import View.IhmLogin;
import View.IhmLoginStart;

public class ControlerGolbal extends Thread implements ActionListener{
	private IhmLoginStart ihmLogin;
	private NetworkGlobal network;
	private BackGroundImage backGround;
	public ControlerGolbal() {
		this.ihmLogin = new IhmLoginStart(this);
		this.network = null;
		this.backGround =null;
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
				network = new NetworkGlobal();
				Login.setLogin(IhmLogin.getFieldLog().getText());
				this.ihmLogin.getFrameNew().dispose();
				this.backGround = new BackGroundImage(this);
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
		}
	}

	private void sendRequest(String text, String pseudo) throws UnknownHostException {
		Message message = new Message(Message.DataType.Text, text,pseudo, Login.getLogin());
		User user = this.network.getListen().getuserList().findUser(pseudo);
		
		ObjectOutputStream os = user.getSocket().getOut();
		try {
			os.writeObject(message);
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
