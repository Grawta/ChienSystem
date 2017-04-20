package ChienSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BackGroundImage {
	public static final String IMAGE_PATH = "/home/nathan/Images/background.jpg";
	
	
	public BackGroundImage() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI();
			}
		});
		
	}
	
	
	
	/*Fonction affichage GUI*/
	private void createAndShowUI() {
		Image image = null;
		try {
			//ImageIcon url = new ImageIcon(IMAGE_PATH);
			image = ImageIO.read(new File("/home/nathan/Images/background.jpg"));
			// JLabel label = new JLabel(new ImageIcon(image));
			ImagePanelA imagePanel = new ImagePanelA(image);
			JFrame frame = new JFrame("Chien Sytem !!");
			frame.getContentPane().add(imagePanel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setMinimumSize(new Dimension(1000,900));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


}





/*Class création GUI et image*/
