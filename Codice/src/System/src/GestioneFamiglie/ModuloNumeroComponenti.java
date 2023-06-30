package GestioneFamiglie;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ModuloNumeroComponenti extends JFrame {
    String titolo = "Numero componenti";
    int width = 800;
    int height = 400;

    private int numeroSelezionato;
    private JButton conferma;
    private JList<String> lista;

    public ModuloNumeroComponenti() {

        this.setTitle(titolo);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Selezione numero componenti
        JLabel seleziona = new JLabel("Seleziona il numero di componenti");

        String numeroComp[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        lista = new JList<>(numeroComp);

        conferma = new JButton("Conferma", null);

        conferma.addActionListener(e -> {
            numeroSelezionato = Integer.parseInt(lista.getSelectedValue());
            //setvisible false?
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        panel.add(seleziona, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        panel.add(conferma,BorderLayout.SOUTH);

        this.setContentPane(panel);

    }

    public int getNum() {
        return numeroSelezionato;
    }
}
