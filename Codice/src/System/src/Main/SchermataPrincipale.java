package Main;

import Autenticazione.Utente;
import GestioneDonazioni.*;
import Connectivity.DBMSInterface;
import GestioneSmistamenti.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale extends JFrame {

    String titolo;
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    private Utente u;
    DBMSInterface db;
    JButton notificheButton;

    public SchermataPrincipale(Utente u, DBMSInterface db) {
        this.u = u;
        this.db = db;
        this.setSize(width, heigth);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();
        switch (u.getRuolo()) {
            case "Azienda":
                initAzienda();
                break;

            case "Diocesi":
                initDiocesi();
                break;
            case "RPT":
                initPolo();
                break;

            case "Amministratore":
                initAmministratore();
                break;

        }
    }

    public void initItems() {

        // Pannello superiore con il pulsante Notifiche
        JPanel headerPnl = new JPanel();
        headerPnl.setLayout(new GridLayout());

        JPanel notifichePnl = new JPanel();
        notifichePnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        headerPnl.add(notifichePnl);
        notificheButton = new JButton();
        notificheButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        notificheButton.setText("Notifiche");
        notifichePnl.add(notificheButton);

        cont.add(headerPnl, BorderLayout.NORTH);

        // Pannello Inferiore con il pulsante Modifica Dati
        JPanel footerPnl = new JPanel();
        footerPnl.setLayout(new GridLayout());

        JPanel modificaDatiPnl = new JPanel();
        modificaDatiPnl.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 30));
        footerPnl.add(modificaDatiPnl);
        JButton modificaButton = new JButton();
        modificaButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        modificaButton.setText("Modifica Dati");
        modificaDatiPnl.add(modificaButton);

        cont.add(footerPnl, BorderLayout.SOUTH);
    }

    // Azienda
    public void initAzienda() {
        JButton visualizzaRichieste = new JButton("Visualizza Richieste");

        JButton aggiungiProdotto = new JButton("Aggiungi Prodotto");

        JButton visualizzaStorico = new JButton("Visualizza storico");

        JButton modificaDonazione = new JButton("Modifica Donazione");

    }

    public void initDiocesi() {
        System.out.println("initDiocesi");
        // Diocesi o Polo
        JButton spedizioneArrivo = new JButton("Visualizza spedizione in arrivo");
        JButton segnalaErrore = new JButton("Segnala errore");
        JButton visualizzaSchema = new JButton("Visualizza Schema");
        JButton confermaSmistamento = new JButton("Conferma smistamento");

        spedizioneArrivo.setVisible(true);
        segnalaErrore.setVisible(true);
        visualizzaSchema.setVisible(true);
        confermaSmistamento.setVisible(true);

        spedizioneArrivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreRicezioneSpedizioni ges_rs = new GestoreRicezioneSpedizioni(SchermataPrincipale.this, u, db);
            }
        });

        visualizzaSchema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreVisualizzaSchemaDiDistribuzione ges_vs = new GestoreVisualizzaSchemaDiDistribuzione(
                        SchermataPrincipale.this, u, db);
            }
        });

        confermaSmistamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreConfermaSmistamento ges_cs = new GestoreConfermaSmistamento(SchermataPrincipale.this, u, db);
            }
        });

        JPanel gestioneSmistamentiPnl = new JPanel();
        gestioneSmistamentiPnl.setLayout(new GridLayout(4, 1, 10, 10));
        gestioneSmistamentiPnl.add(spedizioneArrivo);
        gestioneSmistamentiPnl.add(segnalaErrore);
        gestioneSmistamentiPnl.add(visualizzaSchema);
        gestioneSmistamentiPnl.add(confermaSmistamento);
        cont.add(gestioneSmistamentiPnl, BorderLayout.CENTER);
    }

    public void initPolo() {
        // Polo
        JButton effettuaScarico = new JButton("Effettua scarico");
        JButton sospendiPolo = new JButton("Sospendi Polo");
        JButton riabilitaPolo = new JButton("Riabilita Polo");
        JButton scaricaReport = new JButton("Scarica Report");
        JButton regFamiglia = new JButton("Registra famiglia");
        JButton elencoFamiglia = new JButton("Elenco famiglie");
    }

    public void initAmministratore() {
        JButton invioNotifica = new JButton("Invio notifica");
        JButton regFamiglia = new JButton("Registra famiglia");

    }

    /*
     * 
     * public void initAzienda() {
     * JPanel gst_Donazioni = new JPanel(new GridLayout(4,1,10,10));
     * initGstDonazioni(gst_Donazioni);
     * 
     * visualizzaRichieste.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * SchermataPrincipale.this.setVisible(false);
     * GestoreVisualizzaRichieste ges_r = new
     * GestoreVisualizzaRichieste(SchermataPrincipale.this, u, db);
     * }
     * });
     * 
     * aggiungiProdotto.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * SchermataPrincipale.this.setVisible(false);
     * GestoreAggiungiProdotto ges_a = new
     * GestoreAggiungiProdotto(SchermataPrincipale.this, u, db);
     * }
     * });
     * 
     * visualizzaStorico.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * SchermataPrincipale.this.setVisible(false);
     * GestoreVisualizzaStorico ges_s = new
     * GestoreVisualizzaStorico(SchermataPrincipale.this, u, db);
     * }
     * });
     * 
     * modificaDonazione.addActionListener(new ActionListener() {
     * public void actionPerformed(ActionEvent e) {
     * SchermataPrincipale.this.setVisible(false);
     * GestoreModificaDonazione ges_r = new
     * GestoreModificaStorico(SchermataPrincipale.this, u, db);
     * }
     * });
     * }
     */
    /*
     * public void initDiocesi() {
     * // JPanel gst_Smistamenti = new JPanel(new GridLayout(4, 1, 10, 10));
     * // initGstSmistamenti(gst_Smistamenti);
     * 
     * 
     * }
     */

    /*
     * private void initGstDonazioni(JPanel g) {
     * g.add(visualizzaRichieste);
     * g.add(aggiungiProdotto);
     * g.add(visualizzaStorico);
     * g.add(modificaDonazione);
     * }
     * 
     * private void initGstSmistamenti(JPanel g) {
     * g.add(spedizioneArrivo);
     * g.add(segnalaErrore);
     * g.add(visualizzaSchema);
     * g.add(confermaSmistamento);
     * }
     */

}
