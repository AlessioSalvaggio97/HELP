package GestioneSmistamenti;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SchermataSchemadiDistribuzione extends JFrame {
    private JTable table;
    private JButton closeButton;
    private GestoreVisualizzaSchemaDiDistribuzione gestore;

    public SchermataSchemadiDistribuzione(List<String[]> schema, GestoreVisualizzaSchemaDiDistribuzione gestore) {
        setTitle("Schema di Distribuzione");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        this.gestore = gestore;

        // Creazione dei dati per il modello della tabella
        Object[][] data = new Object[schema.size()][];
        for (int i = 0; i < schema.size(); i++) {
            data[i] = schema.get(i);
        }

        // Creazione delle intestazioni delle colonne
        String[] headers = { "Prodotto", "Proprietà", "Quantità", "ID_visualizzatore", "ID_ricevente", "ID_F" };

        // Creazione del modello della tabella
        DefaultTableModel model = new DefaultTableModel(data, headers);

        // Creazione della tabella
        table = new JTable(model);

        // Creazione dello scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(table);

        // Aggiunta dello scroll pane al frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Creazione del pulsante "Chiudi"
        closeButton = new JButton("Chiudi");

        // Aggiunta del listener per il pulsante "Chiudi"
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestore.chiudiSchermata(); // Cliccando chiudi, fa chiudere la schermata al gestore
            }
        });

        // Creazione del pannello per il pulsante "Chiudi"
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        // Aggiunta del pannello dei pulsanti al frame
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Visibilità del frame
        setVisible(true);
    }

    public void chiudiSchermata() {
        this.dispose(); // Chiude la schermata
    }

}
