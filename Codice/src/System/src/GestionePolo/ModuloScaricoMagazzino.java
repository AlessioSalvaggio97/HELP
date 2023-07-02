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
    private List<JTextField> quantitaFields;
    private JButton invioButton;
    private GestoreScaricoMagazzino gestore;
    private List<ProdottoInMagazzino> elencoProdotti;

    public ModuloScaricoMagazzino(List<ProdottoInMagazzino> elencoProdotti, GestoreScaricoMagazzino gestore) {
        this.elencoProdotti=elencoProdotti;
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
        quantitaFields = new ArrayList<>();
        for (int i = 0; i < elencoProdotti.size(); i++) {
            JTextField quantitaField = new JTextField(10);
            quantitaFields.add(quantitaField);
            inputPanel.add(new JLabel("Quantità " + elencoProdotti.get(i).getNomeProdotto() + ":"));
            inputPanel.add(quantitaField);
        }
        invioButton = new JButton("Invio");
        invioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateQuantitaProdotti();
                gestore.isMore(elencoProdotti);
            }
        });
        inputPanel.add(invioButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Mostra il modulo
        setVisible(true);
    }

    private void updateQuantitaProdotti() {
        for (int i = 0; i < table.getRowCount(); i++) {
            int quantita = Integer.parseInt(quantitaFields.get(i).getText());
            table.setValueAt(quantita, i, 1);
            elencoProdotti.get(i).setQuantita(quantita);
        }
    }
}
