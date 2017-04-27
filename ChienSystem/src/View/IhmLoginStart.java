package View;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IhmLoginStart extends JFrame {
	private static JFrame frameNew;

	public IhmLoginStart(){
		JPanel iHMLogin = new IhmLogin();
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
	
}

