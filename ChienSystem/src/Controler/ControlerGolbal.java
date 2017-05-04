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

public class ControlerGolbal implements ActionListener {
	private IhmLoginStart ihmLogin;
	private NetworkGlobal network;
	private BackGroundImage backGround;

	public ControlerGolbal() {
		this.ihmLogin = new IhmLoginStart(this);
		this.network = null;
		this.backGround = null;
	}

	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if (src == IhmLogin.getConnectButton()) {
			try {
				Login.setLogin(IhmLogin.getFieldLog().getText());
				this.ihmLogin.getFrameNew().dispose();
				this.backGround = new BackGroundImage(this);
				network = new NetworkGlobal();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (src == BackGroundImage.getImagePanel().getSendLabel()) {
			String text = BackGroundImage.getImagePanel().getConvTextField().getText();
			String pseudo = (String) BackGroundImage.getImagePanel().getUserTextField().getSelectedValue(); // A
																											// MODIFIER
																											// NE
																											// PEUT
																											// PAS
																											// RESTER
																											// ça
																											// //
																											// COMME
																											// CA
			try {
				sendRequest(text, pseudo);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendRequest(String text, String pseudo) throws UnknownHostException {
		while (this.network == null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (this.network.getListen() == null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (this.network.getListen().getuserList().isEmpty()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Message message = new Message(Message.DataType.Text, text, pseudo, Login.getLogin());
		if (this.network.getListen().getuserList().isEmpty()) {
			System.out.println("tabUser NUL");
		}
		User user = this.network.getListen().getuserList().findUser(pseudo);
		System.out.println("on envoie");
		this.network.getListen().getuserList().print();
		if (user == null) {
			System.out.println("USER NUL");
		}
		ObjectOutputStream os = user.getSocket().getOut();
		try {
			os.writeObject(message);
			os.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
