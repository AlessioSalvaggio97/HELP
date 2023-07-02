package Notifiche;

import java.util.List;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreInvioNotifiche {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;

    public GestoreInvioNotifiche(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        
    }

}
