package GestioneSmistamenti;

// import GestioneDonazioni.Spedizione;

import javax.swing.*;

import GestioneDonazioni.Spedizione;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataConfermaRicezioneSpedizione extends JFrame {
    private int width = 700;
    private int height = 420;
    private JLabel nomeProdottoLabel;
    private JLabel proprietaLabel;
    private JLabel quantitaDLabel;
    private JLabel scadenzaLabel;
    private JLabel statoLabel;
    private JLabel dataArrivoLabel;
    private JButton confermaButton;
    private JButton segnalaErroreButton;
    private Spedizione spedizione;
    // private Spedizione spedizione;

    public SchermataConfermaRicezioneSpedizione(Spedizione spedizione) {
        this.spedizione=spedizione;
        this.setTitle("Conferma Ricezione Spedizione");
        this.setSize(width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initItems();
        this.setVisible(true);

    }

    public void initItems() {
        // Imposta il layout del pannello

        setLayout(new GridLayout(7, 2));

        // Inizializza le etichette per le informazioni sulla spedizione
        nomeProdottoLabel = new JLabel("Nome Prodotto:");
        proprietaLabel = new JLabel("Proprietà:");
        quantitaDLabel = new JLabel("Quantità:");
        scadenzaLabel = new JLabel("Scadenza:");
        statoLabel = new JLabel("Stato:");
        dataArrivoLabel = new JLabel("Data Arrivo:");

        // Aggiunge le etichette al pannello
        add(nomeProdottoLabel);
        add(proprietaLabel);
        add(quantitaDLabel);
        add(scadenzaLabel);
        add(statoLabel);
        add(dataArrivoLabel);

        /*
         * setNomeProdotto(spedizione.getNomeProdotto());
         * setProprieta(spedizione.getProprieta());
         * setQuantitaD(spedizione.getQuantitaD());
         * setScadenza(spedizione.getScadenza());
         * setStato(spedizione.getStato());
         * setDataArrivo(spedizione.getDataArrivo());
         */

        // Inizializza il pulsante di conferma ricezione spedizione
        confermaButton = new JButton("Conferma ricezione spedizione");
        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestoreRicezioneSpedizioni.invioDati();

                // da aggiungere ritorno a schermata principale
                
                // JFrame frame = (JFrame)
                // SwingUtilities.getRoot(SchermataConfermaRicezioneSpedizione.this);
                // frame.getContentPane().removeAll();
                
                // frame.revalidate();
            }
        });

        // Aggiunge il pulsante al pannello
        add(confermaButton);

        // Inizializza il pulsante di segnala errore
        segnalaErroreButton = new JButton("Segnala errore");
        segnalaErroreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestoreSegnalaErrore segnalaErrore = new GestoreSegnalaErrore(GestoreRicezioneSpedizioni.getDb());
            }
        });

        // Aggiunge il pulsante segnala errore
        add(segnalaErroreButton);
    }

}
