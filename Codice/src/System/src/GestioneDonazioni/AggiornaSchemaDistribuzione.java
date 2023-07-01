package GestioneDonazioni;

import Connectivity.DBMSInterface;

public class AggiornaSchemaDistribuzione{
    private DBMSInterface db;
    
    public AggiornaSchemaDistribuzione(DBMSInterface db) {
        db.AggiornaSchemaDistribuzione();
    }
    
}
