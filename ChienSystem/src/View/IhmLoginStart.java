package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controler.ControlerGolbal;

public class IhmLoginStart extends JFrame implements ActionListener{
	private static JFrame frameNew;

	public IhmLoginStart(ControlerGolbal controler){
		JPanel iHMLogin = new IhmLogin(controler);
		frameNew = new JFrame("Login");
		frameNew.getContentPane().add(iHMLogin);
		frameNew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameNew.pack();
		frameNew.setMinimumSize(new Dimension(150,100));
		frameNew.setResizable(false);
		frameNew.setLocationRelativeTo(null);
		frameNew.setVisible(true);
	}

	public static JFrame getFrameNew() {
		return frameNew;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

