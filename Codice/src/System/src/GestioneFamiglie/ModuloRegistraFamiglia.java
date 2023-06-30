package GestioneFamiglie;

import GestioneFamiglie.Famiglia.Componente;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ModuloRegistraFamiglia extends JFrame {

    String titolo = "Registra Famiglia";
    int width = 800;
    int height = 400;

    private JTextField nome;
    private JTextField cognome;
    private JTextField codice;
    private JTextField data;
    private JTextField indirizzo;
    private JTextField bisogni;

    private JButton conferma;
    private JList<String> lista;
    private List<Famiglia.Componente> membriFamiglia;

    public ModuloRegistraFamiglia(int numComp) {
        this.nome = new JTextField(20);
        this.cognome = new JTextField(20);
        this.codice = new JTextField(20);
        this.data = new JTextField(20);
        this.indirizzo = new JTextField(20);
        this.bisogni = new JTextField(20);

        Famiglia famiglia = new Famiglia(0, 0, numComp);

        membriFamiglia = new ArrayList<>();

        this.setTitle(titolo);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new BoxLayout(panelPrincipale, BoxLayout.Y_AXIS));

        for (int i = 0; i < numComp; i++) {

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.insets = new Insets(5, 5, 5, 5);

            JPanel panelMembro = new JPanel(new GridBagLayout());

            JLabel istruzioni = new JLabel("Inserisci i dati del membro n." + (i + 1));
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            panelMembro.add(istruzioni, constraints);

            // Input nome, cognome, codice fiscale, data di nascita, indirizzo di residenza
            // e bisogni speciali
            JLabel nomelbl = new JLabel("Nome: ");
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 1;
            panelMembro.add(nomelbl, constraints);

            this.nome = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 1;
            panelMembro.add(nome, constraints);

            JLabel cognomelbl = new JLabel("Cognome: ");
            constraints.gridx = 0;
            constraints.gridy = 2;
            panelMembro.add(cognomelbl, constraints);

            this.cognome = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 2;
            panelMembro.add(cognome, constraints);

            JLabel codicelbl = new JLabel("Codice fiscale: ");
            constraints.gridx = 0;
            constraints.gridy = 3;
            panelMembro.add(codicelbl, constraints);

            this.codice = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 3;
            panelMembro.add(codice, constraints);

            JLabel datalbl = new JLabel("Data di nascita (AAAA-MM-GG): ");
            constraints.gridx = 0;
            constraints.gridy = 4;
            panelMembro.add(datalbl, constraints);

            this.data = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 4;
            panelMembro.add(data, constraints);

            JLabel indirizzolbl = new JLabel("Indirizzo di residenza: ");
            constraints.gridx = 0;
            constraints.gridy = 5;
            panelMembro.add(indirizzolbl, constraints);

            this.indirizzo = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 5;
            panelMembro.add(indirizzo, constraints);

            JLabel bisognilbl = new JLabel("Bisogni speciali: ");
            constraints.gridx = 0;
            constraints.gridy = 6;
            panelMembro.add(bisognilbl, constraints);

            this.bisogni = new JTextField(20);
            constraints.gridx = 1;
            constraints.gridy = 6;
            panelMembro.add(bisogni, constraints);

            panelPrincipale.add(panelMembro);

        }

        JScrollPane scrollPane = new JScrollPane(panelPrincipale);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(conferma, BorderLayout.SOUTH);

        this.setContentPane(panel);
        this.revalidate();
        this.repaint();

        // click su Conferma
        conferma.addActionListener(e -> {
            for (int i = 0; i < numComp; i++) {
                Componente membroFamiglia = famiglia.new Componente(nome.getText(), cognome.getText(), codice.getText(),
                        data.getText(), indirizzo.getText(), bisogni.getText());

                membriFamiglia.add(membroFamiglia);
            }

        });

    }

    public List<Famiglia.Componente> getMembriFamiglia(){
        return membriFamiglia;
    }

}
