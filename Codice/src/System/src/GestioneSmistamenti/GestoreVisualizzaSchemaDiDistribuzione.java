package GestioneSmistamenti;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreVisualizzaSchemaDiDistribuzione {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataConfermaRicezioneSpedizione scConfRic;
    private Spedizione spedizione;

    public GestoreVisualizzaSchemaDiDistribuzione(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
    }

}