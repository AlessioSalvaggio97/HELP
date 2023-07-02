package Autenticazione;

import java.awt.Container;

import javax.swing.*;

import Connectivity.DBMSInterface;

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.SchermataPrincipale;

public class ModuloLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	String titolo = "Login";
	int width = 1280;
	int height = 720;
	private JTextField email;
	private JButton accedi;
	private JButton recuperaPassword;
	private JPasswordField passwordField;
	private SchermataPrincipale sc;
	private DBMSInterface db;

	public ModuloLogin(DBMSInterface db) {
		this.db = db;
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

		JLabel passlbl = new JLabel("Password: ");
		passwordField = new JPasswordField(50);
		passlbl.setBounds(500, 330, 100, 20);
		passwordField.setBounds(570, 330, 280, 20);

		// Pulsante accedi
		accedi = new JButton("Accedi");
		accedi.setBounds(776, 361, 74, 30);

		// Aggiunta dell'ActionListener al pulsante "Accedi"
		accedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emailValue = email.getText();
				String passwordValue = new String(passwordField.getPassword());

				// Invia i dati al GestoreLogin
				GestoreLogin gestoreLogin = new GestoreLogin(db);
				gestoreLogin.gestisciAccesso(emailValue, passwordValue, ModuloLogin.this);
			}
		});

		// Pulsante recuperaPassword
		recuperaPassword = new JButton("Recupera Password");
		recuperaPassword.setBounds(570, 361, 196, 30);

		recuperaPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GestoreRecuperoPassword gr = new GestoreRecuperoPassword(ModuloLogin.this,db);
				ModuloLogin.this.dispose();
			}
		});

		panel.add(emaillbl);
		panel.add(email);
		panel.add(passlbl);
		panel.add(accedi);
		panel.add(recuperaPassword);
		panel.add(passwordField);
		container.add(panel, BorderLayout.CENTER);
	}

	public void reindirizzamento(Utente utente, DBMSInterface db) {
		sc = new SchermataPrincipale(utente, db);
		this.setVisible(false);
	}

	public JButton getAccedi() {
		return accedi;
	}
	//// Aggiungere errore mail o password

	/*
	 * public JTextField getEmail() {
	 * return email;
	 * }
	 * 
	 * public JTextField getPassword() {
	 * return password;
	 * }
	 */
}
