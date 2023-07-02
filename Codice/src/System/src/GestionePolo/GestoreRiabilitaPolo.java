package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreRiabilitaPolo {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private PannelloRiabilita pr;
    private ModuloPassword mp;
    private String passwordInserita;
    private Object tabellaUtente;

    public GestoreRiabilitaPolo(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;

        pr = new PannelloRiabilita("Sei sicuro di voler riabilitare il polo?", this);
    }

    public void creaModulo() {
        mp = new ModuloPassword(this);
    }

    public void gestisciPassword(String passwordInserita) {
        Object tabellaUtente = db.verificaCredenziali(u.getEmail());

        if (tabellaUtente != null) {
            //da continuare
        } else {
            System.out.println("Utente non trovato");
        }


    }
}