package GestioneSmistamenti;

import Connectivity.DBMSInterface;
import Autenticazione.Utente;
import Main.SchermataPrincipale;
import GestioneDonazioni.Spedizione;

public class GestoreRicezioneSpedizioni {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataConfermaRicezioneSpedizione scConfRic;
    private Spedizione spedizione;

    public GestoreRicezioneSpedizioni(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        scConfRic = new schermataConfermaRicezioneSpedizione(getSpedizione());
    }

    public void invioDati() { // registra la quantità dei prodotti ricevuti e aggiorna i dati di magazzino
        db.invioDatiRicezioneSpedizione(spedizione.getId(), utente.getId());
    }

    public Spedizione getSpedizione() {
        return db.getSpedizione();
    }

    public DBMSInterface getDb (){
        return this.db;
    }

}
