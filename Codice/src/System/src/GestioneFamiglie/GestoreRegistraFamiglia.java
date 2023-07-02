package GestioneFamiglie;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestoreRegistraFamiglia {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private ModuloNumeroComponenti modNum;
    private int num;
    private ModuloRegistraFamiglia modReg;
    private List<Famiglia.Componente> membriFamiglia;
    private boolean isRegistered;
    private PannelloErroreRegistrazione pannErr;
    private SchermataConferma schConf;

    public GestoreRegistraFamiglia(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        gestisciRegistraFamiglia();
    }

    private void gestisciRegistraFamiglia() {
        modNum = new ModuloNumeroComponenti();

        num = modNum.getNum();

        modReg = new ModuloRegistraFamiglia(num);

        membriFamiglia = modReg.getMembriFamiglia();

        isRegistered = db.verificaDatiFamiglia(membriFamiglia);

        while (isRegistered) {
            pannErr = new PannelloErroreRegistrazione();
            modReg = new ModuloRegistraFamiglia(num);
            membriFamiglia = modReg.getMembriFamiglia();
            isRegistered = db.verificaDatiFamiglia(membriFamiglia);
        }

        db.inviaDatiFamiglia(membriFamiglia);

        schConf = new SchermataConferma("Famiglia inserita con successo!");

        // reindirizzare
        s.setVisible(true);
    }

}