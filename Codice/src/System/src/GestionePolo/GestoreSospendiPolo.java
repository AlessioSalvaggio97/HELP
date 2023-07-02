package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreSospendiPolo {
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;

    public GestoreSospendiPolo(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		
	}
}