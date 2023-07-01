package GestioneDonazioni;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;
public class ModuloModificaDonazione extends JFrame {
	private JTextField nomeProdottoField;
    private JTextField quantitaDonataField;
    private JTextField dataScadenzaField;

    private Connection connection;

    public ModuloModificaDonazione() {
        // Inizializzazione della finestra JFrame
        setTitle("Modifica Donazione");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creazione dei componenti della GUI
        JLabel nomeProdottoLabel = new JLabel("Nome Prodotto:");
        nomeProdottoField = new JTextField(20);

        JLabel quantitaDonataLabel = new JLabel("Quantità Donata:");
        quantitaDonataField = new JTextField(10);

        JLabel dataScadenzaLabel = new JLabel("Data Scadenza:");
        dataScadenzaField = new JTextField(10);

        JButton confermaButton = new JButton("Conferma donazione");

        // Creazione del pannello contenitore e aggiunta dei componenti
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(nomeProdottoLabel);
        contentPane.add(nomeProdottoField);
        contentPane.add(quantitaDonataLabel);
        contentPane.add(quantitaDonataField);
        contentPane.add(dataScadenzaLabel);
        contentPane.add(dataScadenzaField);
        contentPane.add(new JLabel()); // Spazio vuoto per allineare il pulsante
        contentPane.add(confermaButton);

        setContentPane(contentPane);
    }
}
