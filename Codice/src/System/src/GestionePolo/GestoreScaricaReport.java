package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreScaricaReport {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;

    public GestoreScaricaReport(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		
	}
}