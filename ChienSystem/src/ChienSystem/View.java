package ChienSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class View {

    private JFrame mainFrame = null;
    private JLabel lab = new JLabel(new ImageIcon("/home/nathan/Images/background.jpg"));

    public View() {
        mainFrame = new JFrame("ChienSystem");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 1- Initialisation du container. */
        mainFrame.setLayout(new GridBagLayout());
  

        /* 2- Création et initialisation d'une série de composants. */
        JLabel convLabel = new JLabel("Conv :");
        JLabel sendLabel = new JLabel("Send :");
        JLabel userLabel = new JLabel("User :");

        JTextField convTextField = new JTextField("Historique de la conversation");
        JTextField sendTextField = new JTextField("Ecrire ici");
        JTextField userTextField = new JTextField("Liste des Users");

        /*3- Ajout de ces composants en spécifiant les contraintes de type GridBagConstraints. */
      
 
    	GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainFrame.add(convLabel, gbc);

        convTextField.setPreferredSize(new Dimension(300, 400));      
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 10, 0, 10);
        gbc.weightx = 0.8;
        gbc.weighty = 0.6;
		convTextField.setEditable(false);
        mainFrame.add(convTextField, gbc);
        

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainFrame.add(sendLabel, gbc);

        sendTextField.setPreferredSize(new Dimension(200, 200));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        //gbc.gridwidth =GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 15, 10);
        gbc.weighty = 0.4;
        gbc.weightx = 0.4;
        mainFrame.add(sendTextField, gbc);


        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 10, 0, 0);
        mainFrame.add(userLabel, gbc);
   
        userTextField.setPreferredSize(new Dimension(200, 400));
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        //gbc.gridheight =GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 0.2;
        gbc.insets = new Insets(15, 10, 0, 10);
        mainFrame.add(userTextField, gbc);
        
       
        mainFrame.add(lab);

        mainFrame.setMinimumSize(new Dimension(700,700));
        mainFrame.setLocationRelativeTo(null);
    }

    public void setVisible(boolean b) {
        mainFrame.setVisible(b);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	View ex01 = new View();
                ex01.setVisible(true);
            }
        });
    }
}
