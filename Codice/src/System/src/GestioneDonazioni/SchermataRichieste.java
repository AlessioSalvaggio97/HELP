package GestioneDonazioni;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class SchermataRichieste extends JFrame {
	private int width = 1280;
	private int height = 720;
	private JTable tabellaRichieste;
	private Container cont = this.getContentPane();
	private JButton chiudi;
	private JTable table;
	ArrayList<Richiesta> richieste;
	private GestoreVisualizzaRichieste gest;

	public SchermataRichieste(ArrayList<Richiesta> richieste, GestoreVisualizzaRichieste gest) {
		this.setTitle("Richieste in arrivo");
		this.setSize(width, height);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.richieste = richieste;
		this.gest=gest;
		initItems();
		popolaTabella();

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

		// Aggiungi l'ActionListener al pulsante Chiudi
    	chiudi.addActionListener(e -> {
		gest.reindirizzamento(); //il gestore reindirizza alla schermata principale
		dispose(); //chiude la schermata corrente
    });

	}

	private void popolaTabella() {
		String[] columnNames = { "Nome Prodotto", "Proprietà", "Quantità" };
		Object[][] data = new Object[richieste.size()][3];

		for (int i = 0; i < richieste.size(); i++) {
			Richiesta richiesta = richieste.get(i);
			data[i][0] = richiesta.getNomeProdotto();
			data[i][1] = richiesta.getProprieta();
			data[i][2] = richiesta.getQuantita();
		}

		tabellaRichieste.setModel(new DefaultTableModel(data, columnNames));
	}

	public JTable getTabellaRichieste() {
		return tabellaRichieste;
	}
}
