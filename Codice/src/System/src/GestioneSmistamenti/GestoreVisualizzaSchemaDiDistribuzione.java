package GestioneSmistamenti;

import java.util.List;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class GestoreVisualizzaSchemaDiDistribuzione {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataSchemadiDistribuzione schDis;
    private List<String[]> schema;

    public GestoreVisualizzaSchemaDiDistribuzione(SchermataPrincipale s, Utente u, DBMSInterface db) {

        this.s = s;
        this.u = u;
        this.db = db;
        this.schema = db.getSchema();

        SchermataSchemadiDistribuzione schDis = new SchermataSchemadiDistribuzione(schema, this);
    }

    public void chiudiSchermata() {
        // Chiude la schermata dello schema
        schDis.chiudiSchermata();
        
        // Mostra la SchermataPrincipale
        s.setVisible(true);
    }
}
