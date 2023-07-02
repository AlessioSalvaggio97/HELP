package GestioneDonazioni;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModuloModificaDonazione extends JFrame {
    private JTable table;
    private JButton invioButton;
    private List<Donazione> donazioni;
    private GestoreModificaDonazione gestore;

    public ModuloModificaDonazione(List<Donazione> donazioni, GestoreModificaDonazione gestore) {
        this.donazioni = donazioni;
        this.gestore = gestore;
        initialize();
        populateTable();
    }

    private void initialize() {
        setTitle("Modifica Donazione");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        invioButton = new JButton("Invio");
        panel.add(invioButton, BorderLayout.SOUTH);
        invioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inviaDonazioniModificate();
            }
        });
    }

    private void populateTable() {
        String[] columnNames = { "Nome Prodotto", "Proprietà", "Quantità" };
        Object[][] data = new Object[donazioni.size()][3];

        for (int i = 0; i < donazioni.size(); i++) {
            Donazione donazione = donazioni.get(i);
            data[i][0] = donazione.getNomeProdotto();
            data[i][1] = donazione.getProprietà();
            data[i][2] = donazione.getQuantita_d();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only allow editing of the "Quantità" column
                return column == 2;
            }
        };

        table.setModel(tableModel);
    }

    private void inviaDonazioniModificate() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        List<Donazione> nuoveDonazioni = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String nomeProdotto = tableModel.getValueAt(i, 0).toString();
            String proprieta = tableModel.getValueAt(i, 1).toString();
            int nuovaQuantita = Integer.parseInt(tableModel.getValueAt(i, 2).toString());

            Donazione donazione = new Donazione(0, 0, 0, 0, "", nuovaQuantita, "", nomeProdotto, proprieta);
            nuoveDonazioni.add(donazione);
        }

        gestore.verificaCapienza(nuoveDonazioni);
        dispose();
    }
}
