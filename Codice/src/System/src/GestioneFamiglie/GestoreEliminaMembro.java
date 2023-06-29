package GestioneFamiglie;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreEliminaMembro {
    private SchermataPrincipale s;
    private Utente u;
    DBMSInterface db;

    public GestoreEliminaMembro(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
    }

    public GestoreEliminaMembro(String codice) {

        System.out.println("Chiamato gestore elimina membro");
        db.eliminaMembro(codice);
    }
}
