package GestionePolo;

import java.sql.Date;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestionePolo.SospensioneAutomaticaBoundary;
import GestioneDonazioni.TempoBoundary;
import Main.SchermataPrincipale;

public class GestoreVerificaUltimoScarico {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
    private TempoBoundary tmp;
    private Date data;
    private SospensioneAutomaticaBoundary aggSch;

    public GestoreVerificaUltimoScarico(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		verificaUltimoScarico();
	}

	private void verificaUltimoScarico(){

       data = tmp.chiediData();

        db.richiediUltimoScarico();

        //da continuare

        if (==true){
            aggSch = new SospensioneAutomaticaBoundary(db);
        }

    }

}
