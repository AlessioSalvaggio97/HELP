package Main;

import java.util.List;

import Autenticazione.*;
import Connectivity.DBMSInterface;
import GestioneDonazioni.TempoBoundary;
import GestioneFamiglie.*;
import GestioneSmistamenti.SchermataSchemadiDistribuzione;

public class Main {
    public static void main(String[] args) {

        DBMSInterface db = new DBMSInterface();
        ModuloLogin mdl = new ModuloLogin(db);
        
       /*
       TempoBoundary tmp = new TempoBoundary();
       PannelloErroreRegistrazione pn = new PannelloErroreRegistrazione();

        SchermataConferma schConf = new SchermataConferma("Famiglia inserita con successo!");
        
         * ModuloLogin log = new ModuloLogin();
         * Utente utente = new Utente();
         * SchermataPrincipale s = new SchermataPrincipale();
         * DBMSInterface db = new DBMSInterface(log, s);
         * ModuloLogin log = new ModuloLogin();
         * Utente utente = new Utente();
         */

        /*
         * per testare
         * RegistraFamiglia reg = new RegistraFamiglia();
         * VisualizzaDatiFamiglia vis = new VisualizzaDatiFamiglia();
         
        // SchermataConfermaRicezioneSpedizione sc = new
        // SchermataConfermaRicezioneSpedizione();

        List<String[]> schema = List.of(
                new String[] { "Prodotto1", "Proprietà1", "10", "ID_visualizzatore1", "ID_ricevente1", "ID_F1" },
                new String[] { "Prodotto2", "Proprietà2", "20", "ID_visualizzatore2", "ID_ricevente2", "ID_F2" },
                new String[] { "Prodotto3", "Proprietà3", "30", "ID_visualizzatore3", "ID_ricevente3", "ID_F3" });

        SchermataSchemadiDistribuzione scDistr = new SchermataSchemadiDistribuzione(schema); */
        
    }
}
