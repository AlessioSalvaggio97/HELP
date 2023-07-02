package GestioneSmistamenti;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import GestioneDonazioni.Spedizione;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SchermataConfermaRicezioneSpedizione extends JFrame {
    private List<Spedizione> spedizioni;
    private GestoreRicezioneSpedizioni grs;

    public SchermataConfermaRicezioneSpedizione(List<Spedizione> spedizioni, GestoreRicezioneSpedizioni grs) {
        this.spedizioni = spedizioni;
        this.grs = grs;
        this.setTitle("Conferma Ricezione Spedizione");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initItems();
        this.pack();
        this.setVisible(true);
    }

    public void initItems() {
        JPanel panel = new JPanel(new BorderLayout());

        // Inizializza le colonne della tabella
        String[] columns = {"Nome Prodotto", "Proprietà", "Quantità", "Stato"};

        // Crea il modello dei dati per la tabella
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Aggiungi i dati delle spedizioni al modello
        for (Spedizione spedizione : spedizioni) {
            Object[] rowData = {
                    spedizione.getNomeProdotto(),
                    spedizione.getProprietà(),
                    spedizione.getQuantita(),
                    spedizione.getStato()
            };
            model.addRow(rowData);
        }

        // Crea la tabella con il modello dei dati
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Inizializza il pulsante di conferma ricezione spedizione
        JButton confermaButton = new JButton("Conferma ricezione spedizione");
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grs.invioDati();
            }
        });
        panel.add(confermaButton, BorderLayout.SOUTH);

        // Aggiungi il pannello al frame
        this.getContentPane().add(panel);
    }
}
