package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Network.ControlMessage;
import Network.CreateTCPSocket;
import Network.Message;
import Network.NetworkGlobal;
import Network.NetworkSendDisconnect;
import Network.User;
import View.BackGroundImage;
import View.IhmLogin;
import View.IhmLoginStart;

public class ControlerGolbal implements ActionListener, WindowListener {
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
			if (BackGroundImage.getImagePanel().getUserTextField().getSelectedValue()!= null){
				String text = BackGroundImage.getImagePanel().getSendTextField().getText();
				String pseudo = (String) BackGroundImage.getImagePanel().getUserTextField().getSelectedValue();
				try {
					sendRequest(text, pseudo);
					try {
						EcritureBufferFichier.ecritureFichier(pseudo,Login.getLogin() + " : " + text + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				BackGroundImage.getImagePanel().getSendTextField().setText("");
			}
		}
	}

	private void sendRequest(String text, String pseudo)
			throws UnknownHostException {
		if (this.network != null) {
			if (this.network.getListen() != null) {
				if (!(this.network.getListen().getuserList().isEmpty())) {
					Message message = new Message(Message.DataType.Text, text, pseudo, Login.getLogin());
					if (this.network.getListen().getuserList().isEmpty()) {
						System.out.println("tabUser NUL");
					}
					User user = this.network.getListen().getuserList().findUser(pseudo);
					if (user == null) {
						System.out.println("USER NUL");
					}
					ObjectOutputStream os = user.getSocket().getOut();
					if (os == null) {
						System.out.println("OS null");
					}
					try {
						os.writeObject(message);
						os.flush();

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}		
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			NetworkSendDisconnect.envoieDisconnect(network.getServeur());
			System.exit(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
