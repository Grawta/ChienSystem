package ChienSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ImagePanelA extends JPanel {
	
	private Image image;
	private JScrollPane scrollSend;
	private JScrollPane scrollConv;
	private JLabel convLabel;
	private JButton sendLabel;
	private JLabel userLabel;
	private JTextArea convTextField;
	private JTextArea sendTextField;
	private JTextField userTextField;
	private GridBagConstraints gbc;
    private JComboBox<String> convExistante ;	
	public ImagePanelA(Image image) {
		this.image = image;
		setLayout(new GridBagLayout());


        /* 2- Création et initialisation d'une série de composants. */
        convLabel = new JLabel("Conv :");
        sendLabel = new JButton("Send :");
        userLabel = new JLabel("User :");

        convTextField = new JTextArea("Historique de la conversation");
        sendTextField = new JTextArea("Ecrire ici");
        userTextField = new JTextField("Liste des Users");
        scrollConv = new JScrollPane(convTextField);
		scrollSend = new JScrollPane(sendTextField);
		convExistante = new JComboBox<String>();    
		gbc = new GridBagConstraints();

        /*3- Ajout de ces composants en spécifiant les contraintes de type GridBagConstraints. */
      
    	
    	
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        convLabel.setForeground(Color.white);
        convLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        this.add(convLabel, gbc);

        convTextField.setPreferredSize(new Dimension(300, 400));      
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 10, 0, 10);
        gbc.weightx = 0.8;
        gbc.weighty = 0.6;
		convTextField.setEditable(false);
        this.add(scrollConv,gbc);

     //   this.add(convTextField, gbc);
        

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(sendLabel, gbc);

        sendTextField.setPreferredSize(new Dimension(200, 200));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        //gbc.gridwidth =GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 15, 10);
        gbc.weighty = 0.4;
        gbc.weightx = 0.4;
    	this.add(scrollSend,gbc);
       // this.add(sendTextField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 0, 0);
        userLabel.setForeground(Color.white);
        userLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        this.add(userLabel, gbc);
   
        userTextField.setPreferredSize(new Dimension(200, 400));
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        //gbc.gridheight =GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.8;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(15, 10, 0, 10);
        this.add(userTextField, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.2;
        gbc.anchor = GridBagConstraints.PAGE_START;

        this.add(convExistante);
        initListe("b2a");
        
        
        
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension superSize = super.getPreferredSize();
		if (isPreferredSizeSet() || image == null) {
			return superSize;
		}
		int prefW = Math.max(image.getWidth(null), superSize.width);
		int prefH = Math.max(image.getHeight(null), superSize.height);
		return new Dimension(prefW, prefH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(),this);
		}
	}
	public void setListeDeroul (String phrase){
		this.convExistante.addItem("test");
		
	}
	
	
	
	
	
	public void initListe(String nomFichier){
		String [] tabConv = LectureBufferFichier.lectureFichier(nomFichier);
		int j =0;
		while (tabConv[j] !=null){
			System.out.println(j);
			System.out.println(tabConv[j]);
			j++;
		}
		int i = 0;
		while (tabConv[i] !=null){
			convExistante.addItem(tabConv[i]);
			i++;
		}
		
	}
	
	
}
