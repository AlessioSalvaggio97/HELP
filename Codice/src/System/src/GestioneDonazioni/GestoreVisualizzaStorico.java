package GestioneDonazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreVisualizzaStorico {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    
    public GestoreVisualizzaStorico(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s=s;
        this.u=u;
        this.db=db;
    }
}
