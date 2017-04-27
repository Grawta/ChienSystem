package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;

@SuppressWarnings("serial")
public class IhmLogin extends JPanel {
	private JTextField fieldLog;
	private JButton connectButton;
	private GridBagConstraints gbc;

	public IhmLogin() {
		setLayout(new GridBagLayout());
		this.setFieldLog(new JTextField("Entrez votre Login"));
		this.setConnectButton(new JButton("Connect"));
		this.gbc = new GridBagConstraints();

		gbc.gridx = gbc.gridy = 0;
		gbc.gridheight = gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 10, 0);
		this.add(this.fieldLog, gbc);
		

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 0, 0, 0);
        this.add(connectButton, gbc);

	}

	public JTextField getFieldLog() {
		return fieldLog;
	}

	public void setFieldLog(JTextField fieldLog) {
		this.fieldLog = fieldLog;
	}

	public JButton getConnectButton() {
		return connectButton;
	}

	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}

}
