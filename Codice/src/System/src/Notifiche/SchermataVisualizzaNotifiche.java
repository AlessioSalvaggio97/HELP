package Notifiche;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.BorderLayout;

import GestioneSmistamenti.Notifica;

public class SchermataVisualizzaNotifiche extends JFrame {
    private int width = 1280;
    private int height = 720;
    private JTable tabellaNotifiche;
    private Container cont = getContentPane();
    private JButton chiudi;
    private ArrayList<Notifica> notifiche;
    private GestoreVisualizzaNotifiche gest;

    public SchermataVisualizzaNotifiche(ArrayList<Notifica> notifiche, GestoreVisualizzaNotifiche gest) {
        this.setTitle("Notifiche in arrivo");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.notifiche = notifiche;
        this.gest = gest;
        initItems();
        popolaTabella(notifiche);
        setVisible(true);
    }

    private void initItems() {
        cont.setLayout(new BorderLayout());

        tabellaNotifiche = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabellaNotifiche);
        cont.add(scrollPane, BorderLayout.CENTER);

        chiudi = new JButton("Chiudi");
        cont.add(chiudi, BorderLayout.SOUTH);

        // Aggiungi l'ActionListener al pulsante Chiudi
        chiudi.addActionListener(e -> {
            gest.reindirizzamento(); // il gestore reindirizza alla schermata principale
            dispose(); // chiude la schermata corrente
        });
    }

    private void popolaTabella(ArrayList<Notifica> notifiche) {
        String[] columnNames = {"Descrizione notifica", "Proprietà"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Notifica notifiche : notifiche) {
            Object[] rowData = {notifiche.getDescrizione()};
            tableModel.addRow(rowData);
        }

        tabellaNotifiche.setModel(tableModel);
    }

    public JTable getTabellaNotifiche() {
        return tabellaNotifiche;
    }
}
}
