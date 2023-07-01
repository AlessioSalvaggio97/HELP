package GestioneFamiglie;

import java.util.List;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreVisualizzaDatiFamiglia {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private List<Famiglia> famiglie;

    GestoreVisualizzaDatiFamiglia(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        gestisciVisualizzaDatiFamiglia();
    }

    private void gestisciVisualizzaDatiFamiglia() {
        famiglie = db.getElencoFamiglie();

        //da continuare
    }

}
