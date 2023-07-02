package GestioneSmistamenti;

import Connectivity.DBMSInterface;

import java.util.List;

import Autenticazione.Utente;
import Main.SchermataPrincipale;
import GestioneDonazioni.Spedizione;

public class GestoreRicezioneSpedizioni {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataConfermaRicezioneSpedizione scConfRic;
    private List<Spedizione> spedizioni;

    public GestoreRicezioneSpedizioni(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        GestisciRicezioneSpedizioni();

    }

    public void GestisciRicezioneSpedizioni() {
        spedizioni = db.getSpedizionePrevista();
        scConfRic = new SchermataConfermaRicezioneSpedizione(spedizioni, this);
    }

    public void invioDati() { // registra la quantità dei prodotti ricevuti e aggiorna i dati di magazzino
        db.invioDatiRicezioneSpedizione(spedizioni, u.getID_U());
        s.setVisible(true);
    }

    public DBMSInterface getDb() {
        return this.db;
    }

}
