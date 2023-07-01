package GestionePolo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import GestionePolo.GestoreScaricoMagazzino.ProdottoInMagazzino;

public class ModuloScaricoMagazzino extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField quantitaField;
    private JButton invioButton;
    private GestoreScaricoMagazzino gestore;

    public ModuloScaricoMagazzino(List<ProdottoInMagazzino> elencoProdotti, GestoreScaricoMagazzino gestore) {
        this.gestore = gestore;

        // Configura il JFrame
        setTitle("Scarico Magazzino");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crea la tabella per visualizzare l'elenco dei prodotti
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome Prodotto");
        tableModel.addColumn("Quantità");
        table = new JTable(tableModel);

        // Popola la tabella con i dati degli elencoProdotti
        for (ProdottoInMagazzino prodotto : elencoProdotti) {
            Object[] row = { prodotto.getNomeProdotto(), prodotto.getQuantita() };
            tableModel.addRow(row);
        }

        // Aggiungi la tabella a uno scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crea il pannello per inserire la quantità e il pulsante di invio
        JPanel inputPanel = new JPanel(new FlowLayout());
        quantitaField = new JTextField(10);
        inputPanel.add(new JLabel("Quantità:"));
        inputPanel.add(quantitaField);
        invioButton = new JButton("Invio");
        invioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestore.isMore(getNuoviProdotti());
            }
        });
        inputPanel.add(invioButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Mostra il modulo
        setVisible(true);
    }

    private List<ProdottoInMagazzino> getNuoviProdotti() {
        List<ProdottoInMagazzino> prodotti = new ArrayList<>();

        for (int i = 0; i < table.getRowCount(); i++) {
            String nomeProdotto = table.getValueAt(i, 0).toString();
            String proprieta = table.getValueAt(i, 1).toString();
            int quantita = Integer.parseInt(table.getValueAt(i, 2).toString());
            int idMagazzino = Integer.parseInt(table.getValueAt(i, 3).toString());
            int capienzaMax = Integer.parseInt(table.getValueAt(i, 4).toString());
            int capienzaAttuale = Integer.parseInt(table.getValueAt(i, 5).toString());
            int idProdotto = Integer.parseInt(table.getValueAt(i, 6).toString());

            ProdottoInMagazzino prodotto = gestore.new ProdottoInMagazzino(nomeProdotto, proprieta, quantita,
                    idMagazzino,
                    capienzaMax, capienzaAttuale, idProdotto);
            prodotti.add(prodotto);
        }

        /*
         * Pulisce tutti i campi di testo
         * for (int i = 0; i < table.getRowCount(); i++) {
         * for (int j = 0; j < table.getColumnCount(); j++) {
         * table.setValueAt("", i, j);
         * }
         * }
         */

        return prodotti;
    }

}
