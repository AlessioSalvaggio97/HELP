package GestioneFamiglie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VisualizzaDatiFamiglia extends JFrame {
    private Connection connection;
    private JTable famiglieTable;

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
        String url = "jdbc:mysql://localhost:3306/dbms";
        String username = "root";
        String password = "donazioni";

        try {
            // Carica il driver JDBC per MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crea la connessione al database
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
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
                
                JButton viewButton = new JButton("Visualizza");
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
            if (value instanceof Component) {
                button = (JButton) value;
                return button;
            }
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                        fireEditingStopped();
                    }
            });
        }


        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText("Visualizza");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int id_f = (int) table.getValueAt(row, 0); // Ottieni l'ID della famiglia dalla tabella
                    showFamilyDetails(id_f); // Chiamata al metodo per visualizzare i dettagli della famiglia
                }
            });
            return (Component) button;
        }

        public Object getCellEditorValue() {
            if (clicked) {
                int selectedRow = famiglieTable.getSelectedRow();
                int selectedFamilyId = (int) famiglieTable.getValueAt(selectedRow, 0);
                System.out.println("Visualizza dettagli per ID_F: " + selectedFamilyId);
            }
            clicked = false;
            return new JButton(label);
        }

        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
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
            EditableTableModel tableModel = new EditableTableModel(new Object[]{"Codice Fiscale", "ID_F", "Nome", "Cognome", "Data", "Indirizzo", "Bisogni"}, 0);
        
            // Popola il modello di tabella con i dati dei componenti
            while (resultSet.next()) {
                String codiceFiscale = resultSet.getString("codice_fiscale");
                int idFamily = resultSet.getInt("ID_F");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                Date data = resultSet.getDate("data_nascita");
                String indirizzo = resultSet.getString("indirizzo");
                String bisogni = resultSet.getString("bisogni");

                Object[] rowData = {codiceFiscale, idFamily, nome, cognome, data, indirizzo, bisogni};
                tableModel.addRow(rowData);
            }
    
            // Crea una tabella utilizzando il modello di tabella
            JTable componentiTable = new JTable(tableModel);
    
            // Crea uno scroll pane per la tabella dei componenti
            JScrollPane scrollPane = new JScrollPane(componentiTable);

            // Crea i pulsanti "Modifica dati", "Aggiungi membro" ed "Elimina membro"
            JButton modificaButton = new JButton("Modifica dati");
            modificaButton.addActionListener(e -> {
                tableModel.setEditable(true);
                componentiTable.setModel(tableModel);
            });

            //Da implementare questi 3
            JButton invioButton = new JButton("Invio");
            invioButton.addActionListener(e -> {
            
            });

            JButton aggiungiButton = new JButton("Aggiungi membro");
            aggiungiButton.addActionListener(e -> {
            
            });
        
            JButton eliminaButton = new JButton("Elimina membro");
            eliminaButton.addActionListener(e -> {
            
            });

            // Crea un pannello per i pulsanti
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.add(modificaButton);
            buttonsPanel.add(invioButton);
            buttonsPanel.add(aggiungiButton);
            buttonsPanel.add(eliminaButton);

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
        private boolean isEditable = false;

        public EditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public EditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
        }

        public void setEditable(boolean editable) {
            isEditable = editable;
            fireTableStructureChanged();
        }
    }
}