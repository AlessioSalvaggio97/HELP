package Main;

import Autenticazione.Utente;
import GestioneDonazioni.*;
import GestioneFamiglie.GestoreRegistraFamiglia;
import GestioneFamiglie.GestoreVisualizzaDatiFamiglia;
import Connectivity.DBMSInterface;
import GestioneSmistamenti.*;
import GestionePolo.*;
import Notifiche.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SchermataPrincipale extends JFrame {

    String titolo = "HELP - Schermata Principale";
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    private Utente u;
    DBMSInterface db;
    JButton notificheButton;

    public SchermataPrincipale(Utente u, DBMSInterface db) {
        this.u = u;
        this.db = db;
        this.setTitle(titolo);
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

        JPanel aziendaPnl = new JPanel();
        aziendaPnl.setLayout(new GridLayout(4, 1, 10, 10));
        aziendaPnl.add(visualizzaRichieste);
        aziendaPnl.add(aggiungiProdotto);
        aziendaPnl.add(visualizzaStorico);
        aziendaPnl.add(modificaDonazione);

        cont.add(aziendaPnl, BorderLayout.CENTER);

        visualizzaRichieste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreVisualizzaRichieste gvr = new GestoreVisualizzaRichieste(SchermataPrincipale.this, u, db);
            }
        });

        aggiungiProdotto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreAggiungiProdotto gap = new GestoreAggiungiProdotto(SchermataPrincipale.this, u, db);
            }
        });

        visualizzaStorico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreVisualizzaStorico gvs = new GestoreVisualizzaStorico(SchermataPrincipale.this, u, db);
            }
        });

        modificaDonazione.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreModificaDonazione gmd = new GestoreModificaDonazione(SchermataPrincipale.this, u, db);
            }
        });
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

        JPanel poloPnl = new JPanel();
        poloPnl.setLayout(new GridLayout(6, 1, 10, 10));
        poloPnl.add(effettuaScarico);
        poloPnl.add(sospendiPolo);
        poloPnl.add(riabilitaPolo);
        poloPnl.add(scaricaReport);
        poloPnl.add(regFamiglia);
        poloPnl.add(elencoFamiglia);

        cont.add(poloPnl, BorderLayout.CENTER);

        effettuaScarico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreScaricoMagazzino gsm = new GestoreScaricoMagazzino(SchermataPrincipale.this, u, db);
            }
        });

        sospendiPolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreSospendiPolo gsp = new GestoreSospendiPolo(SchermataPrincipale.this, u, db);
            }
        });

        riabilitaPolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreRiabilitaPolo grp = new GestoreRiabilitaPolo(SchermataPrincipale.this, u, db);
            }
        });

        scaricaReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreScaricaReport gsr = new GestoreScaricaReport(SchermataPrincipale.this, u, db);
            }
        });

        regFamiglia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreRegistraFamiglia grf = new GestoreRegistraFamiglia(SchermataPrincipale.this, u, db);
            }
        });

        elencoFamiglia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SchermataPrincipale.this.setVisible(false);
                GestoreVisualizzaDatiFamiglia gvdf = new GestoreVisualizzaDatiFamiglia(SchermataPrincipale.this, u, db);
            }
        });
    }

    public void initAmministratore() {
        JButton invioNotifica = new JButton("Invio notifica");
        JButton regFamiglia = new JButton("Registra famiglia");
        JButton visualizzaFamiglia = new JButton("Visualizza dati famiglia");
        JButton visualizzaRichieste = new JButton("Visualizza richieste");
        JButton modificaDonazione = new JButton("Modifica donazione");
        JButton visualizzaStorico = new JButton("Visualizza storico donazioni");
        JButton scaricaReport = new JButton("Scarica report");

        JPanel amministratorePnl = new JPanel();
        amministratorePnl.setLayout(new GridLayout(7, 1, 10, 10));
        amministratorePnl.add(invioNotifica);
        amministratorePnl.add(regFamiglia);
        amministratorePnl.add(visualizzaFamiglia);
        amministratorePnl.add(visualizzaRichieste);
        amministratorePnl.add(modificaDonazione);
        amministratorePnl.add(visualizzaStorico);
        amministratorePnl.add(scaricaReport);

        invioNotifica.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreInvioNotifiche gin = new GestoreInvioNotifiche(SchermataPrincipale.this, u, db);
        });

        regFamiglia.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreRegistraFamiglia grf = new GestoreRegistraFamiglia(SchermataPrincipale.this, u, db);
        });

        visualizzaFamiglia.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreVisualizzaDatiFamiglia gvdf = new GestoreVisualizzaDatiFamiglia(SchermataPrincipale.this, u, db);
        });

        visualizzaRichieste.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreVisualizzaRichieste gvr = new GestoreVisualizzaRichieste(SchermataPrincipale.this, u, db);
        });

        modificaDonazione.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreModificaDonazione gmd = new GestoreModificaDonazione(SchermataPrincipale.this, u, db);
        });

        visualizzaStorico.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreVisualizzaStorico gvs = new GestoreVisualizzaStorico(SchermataPrincipale.this, u, db);
        });

        scaricaReport.addActionListener(e -> {
            SchermataPrincipale.this.setVisible(false);
            GestoreScaricaReport gsr = new GestoreScaricaReport(SchermataPrincipale.this, u, db);
        });

        cont.add(amministratorePnl, BorderLayout.CENTER);

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
