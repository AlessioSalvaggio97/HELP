package GestioneDonazioni;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import GestioneDonazioni.GestoreAggiungiProdotto.Prodotto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModuloAggiungiProdotto extends JFrame {
    private List<Prodotto> listaProdotti;
    private GestoreAggiungiProdotto gestoreModuloAggiungi;

    public ModuloAggiungiProdotto(List<Prodotto> listaProdotti, GestoreAggiungiProdotto gestoreModuloAggiungi) {
        this.listaProdotti = listaProdotti;
        this.gestoreModuloAggiungi = gestoreModuloAggiungi;

        setTitle("Modulo Aggiungi Prodotto");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        String[] columnNames = { "", "ID_P", "Nome Prodotto", "Proprietà" };
        Object[][] rowData = new Object[listaProdotti.size()][columnNames.length];

        for (int i = 0; i < listaProdotti.size(); i++) {
            Prodotto prodotto = listaProdotti.get(i);
            rowData[i] = new Object[] { false, prodotto.getID_P(), prodotto.getNome_prodotto(),
                    prodotto.getProprietà() };
        }

        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

        JButton invioButton = new JButton("Invio");
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length > 0) {
                    for (int selectedRow : selectedRows) {
                        Prodotto prodottoSelezionato = listaProdotti.get(selectedRow);
                        gestoreModuloAggiungi.aggiungiProdotto(prodottoSelezionato);
                    }
                } else {
                    JOptionPane.showMessageDialog(ModuloAggiungiProdotto.this, "Seleziona almeno un prodotto.",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        container.add(invioButton, BorderLayout.SOUTH);
    }

}
