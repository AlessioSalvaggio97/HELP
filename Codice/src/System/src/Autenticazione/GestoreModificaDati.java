package Autenticazione;

import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;
import Autenticazione.Utente;


public class GestoreModificaDati {
    private SchermataPrincipale s; 
    private ModuloModificaDati m;
    private DBMSInterface db;
    private Utente u;

    public GestoreModificaDati(SchermataPrincipale s, Utente u, DBMSInterface db){
        this.s = s;
        this.u = u;
        this.db = db;

        modificaDati();

    }

    public void modificaDati(){
        nome = u.getNome();
        cognome = u.getCognome();
        indirizzo = u.getIndirizzo();
        telefono = u.getTelefono();

        m = new ModuloModificaDati(nome, cognome, indirizzo, telefono);
        m.setVisible(true);
    }
}
