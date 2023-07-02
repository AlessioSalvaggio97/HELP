package GestioneSmistamenti;

import GestioneDonazioni.Spedizione;
import GestioneSmistamenti.GestoreSegnalaErrore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModuloSegnalaErrore extends JFrame {
    private JTextField[] quantitaArrivateFields;
    private JButton invioButton;
    private float[] quantitàArrivate;
    private GestoreSegnalaErrore gestore;

    public void moduloSegnalaErrore(Spedizione spedizione, GestoreSegnalaErrore gestore) {
        this.gestore=gestore;
        int numeroProdotti = spedizione.size(); //da sistemare

        // Crea la finestra del modulo di segnalazione degli errori
        JFrame finestra = new JFrame("Modulo Segnalazione Errori");
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea il pannello principale
        JPanel panel = new JPanel(new GridLayout(numeroProdotti + 1, 2));

        // Aggiungi i campi di input per le quantità effettivamente arrivate
        for (int i = 0; i < numeroProdotti; i++) {

            JLabel label = new JLabel(spedizione.getProdotto()); // da rivedere
            JTextField textField = new JTextField(10);

            panel.add(label);
            panel.add(textField);
        }

        // Aggiungi il pulsante "Invio"
        JButton invioButton = new JButton("Invio");
        invioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Ottieni le quantità effettivamente arrivate dai campi di input
                quantitàArrivate = new float[numeroProdotti];
                Component[] components = panel.getComponents();

                for (int i = 0; i < components.length; i += 2) {
                    JTextField textField = (JTextField) components[i + 1];

                    try {
                        quantitàArrivate[i / 2] = Float.parseFloat(textField.getText());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(finestra,
                                "Inserisci un numero valido per " + spedizione.getProdotto(i / 2).getNome(), "Errore",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                // Invia i dati al gestore degli errori
                // Esegui qui la logica per la gestione degli errori in base alle quantità
                // arrivate
                // ...

                // Chiudi la finestra
                gestore.chiudiModulo();
            }
        });

        panel.add(new JLabel());
        panel.add(invioButton);

        // Aggiungi il pannello alla finestra e visualizzala
        finestra.add(panel);
        finestra.pack();
        finestra.setVisible(true);
    }

    public float[] getQuantitàArrivate() {
        return this.quantitàArrivate;
    }

}