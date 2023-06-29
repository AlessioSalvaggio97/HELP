package GestioneFamiglie;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;

import javax.swing.*;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestoreRegistraFamiglia { 
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private RegistraFamiglia rf;

    public GestoreRegistraFamiglia(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
    }

    private void gestisciRegistraFamiglia(){
        JButton avvia s.getRegistraFamiglia();
        ActionListener avviaLstnr = e -> {
            System.out.println("Cliccato Registra Famiglia");
        };
    }

}