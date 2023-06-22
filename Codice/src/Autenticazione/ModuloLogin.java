package Autenticazione;

import java.awt.Container;

import javax.swing.*;
import java.awt.BorderLayout;


public class ModuloLogin extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String titolo = "Login";
	int width = 1280;
	int height = 720;
	private JTextField email;
	private JTextField password;
	private JButton accedi;
	private JButton recuperaPassword;
	private JPasswordField passwordField;
	
	
	
	public ModuloLogin() {
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
		
		//Input Email e Password
		JLabel emaillbl = new JLabel("E-mail: ");
		email = new JTextField(20);
		emaillbl.setBounds(500, 300, 100, 20);
		email.setBounds(570, 300, 280, 20);
		
		JLabel passlbl = new JLabel("Password: ");
		password = new JTextField(50);
		passlbl.setBounds(500, 330, 100, 20);
		password.setBounds(570, 330, 280, 20);
		
		
		//Pulsante accedi
		accedi = new JButton("Accedi");
		accedi.setBounds(776, 361, 74, 30);
		
		
		//Pulsante recuperaPassword
		recuperaPassword = new JButton("Recupera Password");
		recuperaPassword.setBounds(570, 361, 196, 30);
		
		panel.add(emaillbl);
		panel.add(email);
		panel.add(passlbl);
		panel.add(accedi);
		panel.add(recuperaPassword);
		container.add(panel, BorderLayout.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(570, 330, 280, 20);
		panel.add(passwordField);
	}
	
	public JButton getAccedi() {
		return accedi;
	}
	////Aggiungere errore mail o password
	
	public JTextField getEmail() {
		return email;
	}
	
	public JTextField getPassword() {
		return password;
	}
}
