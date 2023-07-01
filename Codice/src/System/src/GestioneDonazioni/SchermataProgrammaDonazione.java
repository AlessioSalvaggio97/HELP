package GestioneDonazioni;

import javax.swing.*;
import java.awt.GridLayout;

public class SchermataProgrammaDonazione extends JFrame{
	private int width = 1280;
	private int height = 720;
	public SchermataProgrammaDonazione() {
		this.setTitle("Programma Donazione");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		initItems();
	}
	
	public initItems() {
		
	}
} 
