package GestioneDonazioni;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreModificaDonazione {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private Date dataCorrente;
    private List<Donazione> donazioni;
    private ModuloModificaDonazione mmd;

    public GestoreModificaDonazione(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;

        gestisciModificaDonazione();
    }

    public void gestisciModificaDonazione() {
        dataCorrente = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String giornoCorrente = formatter.format(dataCorrente);

        if (Integer.parseInt(giornoCorrente)< 23) {
            donazioni = db.getDonazione();

            mmd = new ModuloModificaDonazione(donazioni, this);
            mmd.setVisible(true);
        }
    }

    public void verificaCapienza(List<Donazione> nuoveDonazioni) {

    }
}
