package GestioneDonazioni;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class SchermataVisualizzaStorico extends JFRame{
	private int width = 1280;
	private int height = 720;
	private JTable tabellaDonazioni;
	private Container cont = this.getContentPane();
	private JButton chiudi;
	private JTable table;
	ArrayList<Spedizione> spedizioni;
	private GestoreVisualizzaStorico gest;

	public SchermataVisualizzaStorico(ArrayList<Spedizione> spedizioni, GestoreVisualizzaStorico gest) {
		this.setTitle("Storico donazioni");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.spedizioni = spedizioni;
		this.gest=gest;
		initItems();
		popolaTabella();

	}

	private void initItems() {
		cont.setLayout(null);

		tabellaDonazioni = new JTable();
		tabellaDonazioni.setBounds(10, 11, 1244, 530);
		this.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(tabellaSpedizioni);
		cont.add(scrollPane);

		chiudi = new JButton("Chiudi");
		chiudi.setBounds(10, 617, 239, 53);
		cont.add(chiudi);

		// Aggiungi l'ActionListener al pulsante Chiudi
    	chiudi.addActionListener(e -> {
		gest.reindirizzamento(); //il gestore reindirizza alla schermata principale
		dispose(); //chiude la schermata corrente
    });

	}

	private void popolaTabella() {
		String[] columnNames = { "Data", "Proprietà"};
		Object[][] data = new Object[spedizioni.size()][3];

		for (int i = 0; i < spedizioni.size(); i++) {
			Spedizione spedizione = spedizioni.get(i);
			data[i][0] = spedizione.getData();
			data[i][1] = spedizione.getProprieta();
		}

		tabellaSpedizioni.setModel(new DefaultTableModel(data, columnNames));
	}

	public JTable getTabellaSpedizioni() {
		return tabellaSpedizioni;
	}

	*/

	//Dobbiamo fare tabella con ID_D e Data Donazione
	//Tabella Dettagli spedizione in altro file
}
