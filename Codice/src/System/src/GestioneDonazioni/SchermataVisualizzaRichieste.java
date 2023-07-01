package GestioneDonazioni;

import javax.swing.*;
import java.awt.*;

public class SchermataVisualizzaRichieste extends JFrame{
	private int width =  1280;
	private int height = 720;
	private JTable tabellaRichieste;
	private Container cont = this.getContentPane();
	private JButton chiudi;
	private JTable table;
	
	
	public SchermataVisualizzaRichieste() {
		this.setTitle("Richieste in arrivo");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initItems();
		
	}
	
	private void initItems() {
		cont.setLayout(null);
		
		
		tabellaRichieste = new JTable();
		tabellaRichieste.setBounds(10, 11, 1244, 530);
		this.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(tabellaRichieste);
		cont.add(scrollPane);
		
		chiudi = new JButton("Chiudi");
		chiudi.setBounds(10, 617, 239, 53);
		cont.add(chiudi);
		
	}
	
	public JTable getTabellaRichieste() {
		return tabellaRichieste;
	}
	
	public JButton getChiudi() {
		return chiudi;
	}
	
	
}
