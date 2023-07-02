package GestionePolo;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreRiabilitaPolo {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private PannelloRiabilita pr;
    private ModuloPassword mp;

    public GestoreRiabilitaPolo(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		
        pr = new PannelloRiabilita("Sei sicuro di voler riabilitare il polo?", this);
	}

    public void creaModulo(){
        mp = new ModuloPassword();
    }
}