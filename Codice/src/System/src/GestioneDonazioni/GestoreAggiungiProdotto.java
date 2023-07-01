package GestioneDonazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreAggiungiProdotto {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    
    public GestoreAggiungiProdotto(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s=s;
        this.u=u;
        this.db=db;
    }
}
