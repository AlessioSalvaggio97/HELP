package GestioneSmistamenti;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneDonazioni.Spedizione;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestoreSegnalaErrore {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataConfermaRicezioneSpedizione scConfRic;
    private Spedizione spedizione;
    ModuloSegnalaErrore modulo;

    public GestoreSegnalaErrore(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        this.spedizione = spedizione;
        gestisciSegnalaErrore();
    }

    public void gestisciSegnalaErrore() {
        ModuloSegnalaErrore modulo = new ModuloSegnalaErrore(spedizione, this);

        float[] quantitàArrivate = modulo.getQuantitàArrivate();

        db.invioDatiQuantitàArrivate(spedizione, quantitàArrivate);

        db.invioNotifica(spedizione);
    }

    public void chiudiModulo() {
        modulo.dispose();
        s.setVisible(true);
    }

}
