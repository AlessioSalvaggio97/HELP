package GestioneFamiglie;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisualizzaDatiFamiglia {
    private List<Famiglia> famiglie;
    private GestoreVisualizzaDatiFamiglia gvdf;
    private JFrame frame;
    private JTable table;

    public VisualizzaDatiFamiglia(List<Famiglia> famiglie, GestoreVisualizzaDatiFamiglia gvdf) {
        this.famiglie = famiglie;
        this.gvdf = gvdf;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Visualizza Dati Famiglia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a set to store unique ID_F values
        Set<Integer> uniqueID_F = new HashSet<>();

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[] { "ID_F", "ID_U", "Numero Componenti", "Azione" }, 0);

        // Populate the table model with data and filter duplicate ID_F values
        for (Famiglia famiglia : famiglie) {
            int idF = famiglia.getID_F();
            if (!uniqueID_F.contains(idF)) {
                uniqueID_F.add(idF);
                Object[] rowData = new Object[4];
                rowData[0] = idF;
                rowData[1] = famiglia.getID_U();
                rowData[2] = famiglia.getComponenti();
                rowData[3] = createButton(famiglia);
                tableModel.addRow(rowData);
            }
        }

        // Create the table
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());

        // Create the frame and display it
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton createButton(Famiglia famiglia) {
        JButton button = new JButton("Visualizza");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gvdf.visualizza(famiglia);
            }
        });
        return button;
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.convertRowIndexToModel(table.getEditingRow());
                Famiglia famiglia = (Famiglia) table.getModel().getValueAt(row, 0);
                gvdf.visualizza(famiglia);
            }
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        setText("Visualizza");
        return this;
    }
}



}
