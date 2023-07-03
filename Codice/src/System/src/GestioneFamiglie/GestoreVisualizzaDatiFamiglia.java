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
    private SchermataVisualizzaElencoFamiglie svdf;
    private Famiglia famiglia;

    public GestoreVisualizzaDatiFamiglia(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        gestisciVisualizzaDatiFamiglia();
    }

    public void gestisciVisualizzaDatiFamiglia() {
        famiglie = db.getElencoFamiglie();

        svdf = new SchermataVisualizzaElencoFamiglie(famiglie, this);
    }

    public void visualizza(Famiglia famiglia){
        
    }

}
