package ChienSystem;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Network.NetworkGlobal;
import View.IhmLogin;

public class TestIHM {
	public static void main(String[] args) throws IOException {
		JPanel iHMLogin = new IhmLogin();
		JFrame frame = new JFrame("Login");
		frame.getContentPane().add(iHMLogin);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(150,100));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
