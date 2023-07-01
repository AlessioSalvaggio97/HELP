package GestioneDonazioni;

import java.text.SimpleDateFormat;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreAggiornaSchemaDistribuzione {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
    private TempoBoundary tmp;
    private String data;
    private AggiornaSchemaDistribuzione aggSch;

    public GestoreAggiornaSchemaDistribuzione(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		gestisciAggiornamento();
	}

	private void gestisciAggiornamento(){

        data = tmp.chiediData();

        if (data.equals("23")){
            aggSch = new AggiornaSchemaDistribuzione(db);
        }

    }
}
