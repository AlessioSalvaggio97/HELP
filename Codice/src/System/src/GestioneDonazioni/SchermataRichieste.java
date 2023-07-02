package GestioneDonazioni;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SchermataRichieste extends JFrame {
    private int width = 1280;
    private int height = 720;
    private JTable tabellaRichieste;
    private Container cont = getContentPane();
    private JButton chiudi;
    private ArrayList<Richiesta> richieste;
    private GestoreVisualizzaRichieste gest;

    public SchermataRichieste(ArrayList<Richiesta> richieste, GestoreVisualizzaRichieste gest) {
        this.setTitle("Richieste in arrivo");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.richieste = richieste;
        this.gest = gest;
        initItems();
        popolaTabella(richieste);
        setVisible(true);
    }

    private void initItems() {
        cont.setLayout(new BorderLayout());

        tabellaRichieste = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaRichieste);
        cont.add(scrollPane, BorderLayout.CENTER);

        chiudi = new JButton("Chiudi");
        cont.add(chiudi, BorderLayout.SOUTH);

        // Aggiungi l'ActionListener al pulsante Chiudi
        chiudi.addActionListener(e -> {
            gest.reindirizzamento(); // il gestore reindirizza alla schermata principale
            dispose(); // chiude la schermata corrente
        });
    }

    private void popolaTabella(ArrayList<Richiesta> richieste) {
        String[] columnNames = {"Nome Prodotto", "Proprietà", "Quantità"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Richiesta richiesta : richieste) {
            Object[] rowData = {richiesta.getNomeProdotto(), richiesta.getProprieta(), richiesta.getQuantita()};
            tableModel.addRow(rowData);
        }

        tabellaRichieste.setModel(tableModel);
    }

    public JTable getTabellaRichieste() {
        return tabellaRichieste;
    }
}
