package GestionePolo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SchermataScaricaReport extends JFrame {
    private List<Report> elencoReport;
    private Report reportSelezionato;
    private GestoreScaricaReport gestoreScaricaReport;

    public SchermataScaricaReport(List<Report> elencoReport, GestoreScaricaReport gestoreScaricaReport) {
        this.elencoReport = elencoReport;
        this.gestoreScaricaReport = gestoreScaricaReport;

        setTitle("Schermata Scarica Report");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        String[] columnNames = { "Seleziona", "ID_Scarico", "ID_P", "Nome Prodotto", "Proprietà", "Quantità",
                "Data Scarico", "ID_M" };
        Object[][] rowData = new Object[elencoReport.size()][columnNames.length];

        for (int i = 0; i < elencoReport.size(); i++) {
            Report report = elencoReport.get(i);
            rowData[i] = new Object[] { false, report.getID_Report(), report.getID_P(), report.getNome_prodotto(),
                    report.getProprietà(), report.getQuantità(), report.getData_scarico(), report.getID_M() };
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

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(0).setCellRenderer(table.getDefaultRenderer(Boolean.class));
        columnModel.getColumn(0).setCellEditor(table.getDefaultEditor(Boolean.class));

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    reportSelezionato = elencoReport.get(selectedRow);
                } else {
                    reportSelezionato = null;
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

        JButton scaricaReportButton = new JButton("Scarica Report");
        scaricaReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (reportSelezionato != null) {
                   gestoreScaricaReport.scaricaReport(); //dovrebbe passare quello selezionato
                } else {
                    JOptionPane.showMessageDialog(SchermataScaricaReport.this, "Seleziona un report.", "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        container.add(scaricaReportButton, BorderLayout.SOUTH);
    }
}
