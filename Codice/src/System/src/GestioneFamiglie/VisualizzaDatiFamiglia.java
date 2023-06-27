package GestioneFamiglie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Connectivity.ConnectionClass;

public class VisualizzaDatiFamiglia extends JFrame {
    private Connection connection;
    private JTable famiglieTable;
    private JButton viewButton;
    private JButton eliminaButton;

    public VisualizzaDatiFamiglia() {

        setTitle("Elenco famiglie");

        // Inizializza la connessione al DBMS
        initializeDBConnection();


        // Imposta le dimensioni e la chiusura dell'applicazione
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea un pannello principale con layout BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Mostra i dati delle famiglie nella tabella
        showFamilyData();

        // Crea uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(famiglieTable);

        // Aggiungi lo scroll pane al pannello principale
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Aggiungi il pannello principale alla finestra
        add(mainPanel);

        // Mostra la finestra principale
        setVisible(true);
    }


    private void initializeDBConnection() {

        ConnectionClass conn = new ConnectionClass();

        try {
            connection = conn.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showFamilyData() {
        // Esegue la query per ottenere tutti i dati delle famiglie
        String query = "SELECT * FROM famiglia";

        try {
            // Crea uno statement
            Statement statement = connection.createStatement();

            // Esegue la query
            ResultSet resultSet = statement.executeQuery(query);

            // Crea un modello di tabella personalizzato per i dati delle famiglie
            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                    public boolean isCellEditable(int row, int column) {
                    return column==3; // Rende tutte le celle non modificabili tranne Visualizza
                    }
            };

            // Aggiungi le colonne al modello di tabella
            tableModel.addColumn("ID_F");
            tableModel.addColumn("ID_U");
            tableModel.addColumn("componenti");
            tableModel.addColumn("Visualizza");

            // Popola il modello di tabella con i dati delle famiglie
            while (resultSet.next()) {
                // Ottieni i dati della famiglia corrente dal risultato della query
                int id_f = resultSet.getInt("ID_F");
                String id_u = resultSet.getString("ID_U");
                String componenti = resultSet.getString("componenti");

                // Crea un oggetto per rappresentare la riga della tabella
                Object[] rowData = new Object[4];
                rowData[0] = id_f;
                rowData[1] = id_u;
                rowData[2] = componenti;
                
                viewButton = new JButton("Visualizza");
                viewButton.addActionListener(new ActionListener() {
                    @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedRow = famiglieTable.getSelectedRow();
                            int selectedFamilyId = (int) famiglieTable.getValueAt(selectedRow, 0);
                            System.out.println("Visualizza dettagli per ID_F: " + selectedFamilyId);
                        }
                });
                rowData[3] = viewButton;
                
                tableModel.addRow(rowData);
            }

            // Crea una tabella utilizzando il modello di tabella
            famiglieTable = new JTable(tableModel);
            famiglieTable.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
            famiglieTable.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));

            // Imposta l'allineamento al centro per le colonne desiderate
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            famiglieTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // ID_F
            famiglieTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // ID_U
            famiglieTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // componente
            
            // Chiude il result set e lo statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }

    private class ButtonRenderer extends DefaultTableCellRenderer {
    private JButton button;

    public ButtonRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof JButton) {
        return (JButton) value;
    } else if (value instanceof Boolean) {
        boolean boolValue = (Boolean) value;
        String buttonText = boolValue ? "Visualizza" : "Elimina";
        JButton button = new JButton(buttonText);
        button.setOpaque(true);
        return button;
    }

    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
}

}


    private class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private int selectedRow;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // L'azione del pulsante viene gestita qui
                if (button.getText().equals("Elimina")) {
                    int confirmResult = JOptionPane.showConfirmDialog(null, "Vuoi eliminare il membro? L'azione è irreversibile.", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        // Esegui l'eliminazione dal DBMS
                        eliminazione(selectedRow);

                        // Mostra un messaggio di successo
                        JOptionPane.showMessageDialog(null, "Membro eliminato con successo!");

                        // Rimuovi la riga dalla tabella
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        int selectedRow = table.convertRowIndexToModel(table.getEditingRow());
                        model.removeRow(selectedRow);
                    }
                }
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    this.table = table;

    if (value instanceof JButton) {
        button = (JButton) value;
    } else {
        button = new JButton();

        if ((Boolean) value) {
            button.setText("Visualizza");
        } else {
            button.setText("Elimina");
        }
    }

    button.setOpaque(true);

    button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (button.getText().equals("Visualizza")) {
                int id_f = (int) table.getValueAt(row, 0);
                showFamilyDetails(id_f);
            } else if (button.getText().equals("Elimina")) {
                int confirmResult = JOptionPane.showConfirmDialog(null, "Vuoi eliminare il membro? L'azione è irreversibile.", "Conferma eliminazione", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    // Esegui l'eliminazione dal DBMS
                    // ...

                    // Mostra un messaggio di successo
                    JOptionPane.showMessageDialog(null, "Membro eliminato con successo!");

                    // Rimuovi la riga dalla tabella
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRow = table.convertRowIndexToModel(row);
                    model.removeRow(selectedRow);
                }
            }
        }
    });

    return button;
}



    public Object getCellEditorValue() {
        return Boolean.valueOf(button.isSelected());
    }

    public boolean stopCellEditing() {
        ActionListener[] listeners = button.getActionListeners();
        for (ActionListener listener : listeners) {
            button.removeActionListener(listener);
        }
        boolean stopped = super.stopCellEditing();
        button = null;
        return stopped;
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}





    private void showFamilyDetails(int id_f) {
    
        // Esegue la query per ottenere i dati dei componenti della famiglia con l'ID specificato
        String query = "SELECT * FROM componente WHERE ID_F = ?";
    
        try {
            // Crea un prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_f);

            // Esegue la query
            ResultSet resultSet = statement.executeQuery(); 

            // Crea un modello di tabella per i dati dei componenti
            EditableTableModel tableModel = new EditableTableModel(new Object[]{"Codice Fiscale", "ID_F", "Nome", "Cognome", "Data", "Indirizzo", "Bisogni", "Elimina"}, 0);
            
            

            while (resultSet.next()) {
                String codiceFiscale = resultSet.getString("codice_fiscale");
                int idFamily = resultSet.getInt("ID_F");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                Date data = resultSet.getDate("data_nascita");
                String indirizzo = resultSet.getString("indirizzo");
                String bisogni = resultSet.getString("bisogni");

                eliminaButton = new JButton("Elimina");
                
                
                // Crea un array con la dimensione corretta
                Object[] rowData = new Object[tableModel.getColumnCount()];

                // Imposta i valori per ciascuna colonna nell'array rowData
                rowData[0] = codiceFiscale;
                rowData[1] = idFamily;
                rowData[2] = nome;
                rowData[3] = cognome;
                rowData[4] = data;
                rowData[5] = indirizzo;
                rowData[6] = bisogni;
                rowData[7] = eliminaButton;

                tableModel.addRow(rowData);
            }
            
            // Crea una tabella utilizzando il modello di tabella
            JTable componentiTable = new JTable(tableModel);

            componentiTable.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
            componentiTable.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));

            // Crea uno scroll pane per la tabella dei componenti
            JScrollPane scrollPane = new JScrollPane(componentiTable);

            // Crea i pulsanti "Modifica dati", "Aggiungi membro" ed "Elimina membro"
            JButton modificaButton = new JButton("Modifica dati");
            modificaButton.addActionListener(e -> {
    for (int column = 0; column < tableModel.getColumnCount(); column++) {
        if (column != 7) { // Indice della settima colonna
            tableModel.setColumnEditable(column, true);
        }
    }
    componentiTable.setModel(tableModel);
});

            //Da implementare questi 3
            JButton invioButton = new JButton("Invio");
            invioButton.addActionListener(e -> {
            
            });

            JButton aggiungiButton = new JButton("Aggiungi membro");
            aggiungiButton.addActionListener(e -> {
                
            });
            
            // Crea un pannello per i pulsanti
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.add(modificaButton);
            buttonsPanel.add(invioButton);
            buttonsPanel.add(aggiungiButton);

            // Crea una nuova finestra per visualizzare la tabella dei componenti e i pulsanti
            JFrame detailsFrame = new JFrame("Dettagli Famiglia");
            detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            detailsFrame.setLayout(new BorderLayout());
            detailsFrame.add(scrollPane, BorderLayout.CENTER);
            detailsFrame.add(buttonsPanel, BorderLayout.SOUTH);
            detailsFrame.pack();
            detailsFrame.setVisible(true);

            // Chiude il result set e lo statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class EditableTableModel extends DefaultTableModel {
    private boolean[] isEditableColumns;

    public EditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
        isEditableColumns = new boolean[columnNames.length];
        Arrays.fill(isEditableColumns, false); // Imposta tutte le colonne come non modificabili
    }

    public EditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        isEditableColumns = new boolean[columnNames.length];
        Arrays.fill(isEditableColumns, true);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
    if (column == getColumnCount() - 1) { // Ultima colonna (colonna del tasto "Elimina")
        return true;
    }
    return isEditableColumns[column];
}

    public void setColumnEditable(int column, boolean editable) {
        isEditableColumns[column] = editable;
        fireTableStructureChanged();
    }
}

    public void eliminazione(int selectedRow) {
      //  int idToDelete = (int) table.getValueAt(selectedRow, );

    }

}
