package View;

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

import Controler.ControlerGolbal;

public class BackGroundImage {
	public static final String IMAGE_PATH = "/home/nathan/Images/background.jpg";
	private static ImagePanelA imagePanel;
	
	public static ImagePanelA getImagePanel() {
		return imagePanel;
	}



	public void setImagePanel(ImagePanelA imagePanel) {
		this.imagePanel = imagePanel;
	}



	public BackGroundImage(final ControlerGolbal controler) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				createAndShowUI(controler);
			}
		});
		
	}
	
	
	
	/*Fonction affichage GUI*/
	private void createAndShowUI(ControlerGolbal controler) {
		Image image = null;
		try {
			//ImageIcon url = new ImageIcon(IMAGE_PATH);
			image = ImageIO.read(new File("src/background.jpg"));
			// JLabel label = new JLabel(new ImageIcon(image));
			this.imagePanel = new ImagePanelA(image);
			System.out.println("IMAGE PANEL CRE");
			if (imagePanel==null){System.out.println("imagePanel NUL");}
			JFrame frame = new JFrame("Chien Sytem !!");
			frame.getContentPane().add(imagePanel);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setMinimumSize(new Dimension(1000,900));
			frame.setLocationRelativeTo(null);
			imagePanel.getSendLabel().addActionListener(controler) ;
			frame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


}





/*Class création GUI et image*/
