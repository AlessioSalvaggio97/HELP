package Autenticazione;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Container;

import Connectivity.DBMSInterface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloRecuperoPassword extends JFrame {
	String titolo = "Recupera Password";
	int width = 1280;
	int height = 720;
	private JTextField email;
	private JButton conferma;
	private DBMSInterface db;
	private GestoreRecuperoPassword grp;
	private String emailValue;

	public ModuloRecuperoPassword(DBMSInterface db, GestoreRecuperoPassword grp) {
		this.db = db;
		this.grp = grp;
		this.setTitle(titolo);
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.initItems();
		this.setVisible(true);
	}

	private void initItems() {
		Container container = this.getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(null);

		// Input Email e Password
		JLabel emaillbl = new JLabel("E-mail: ");
		email = new JTextField(20);
		emaillbl.setBounds(500, 300, 100, 20);
		email.setBounds(570, 300, 280, 20);

		conferma = new JButton("Conferma");
		conferma.setBounds(570, 330, 100, 20);

		conferma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emailValue = email.getText();

				grp.gestisciMail();
			}
		});

		panel.add(emaillbl);
		panel.add(email);
		panel.add(conferma);
		container.add(panel, BorderLayout.CENTER);

	}

	public String getEmail(){
		return emailValue;
	}

}
