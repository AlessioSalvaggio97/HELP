package GestioneSmistamenti;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
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

    public GestoreSegnalaErrore(Spedizione spedizione) {
        this.s = s;
        this.u = u;
        this.db = db; // da gestire schermata, utente e db
        this.spedizione = spedizione;
        ModuloSegnalaErrore modulo = new moduloSegnalaErrore();

        float[] quantitàArrivate = modulo.getQuantitàArrivate();

        db.invioDatiQuantitàArrivate(spedizione, quantitàArrivate);

        db.invioNotifica(spedizione);

    }

    public void destroyERedirect() { //mettere che chiude le schermate e pannello e torna alla schermata principale

    }

}
