package GestioneDonazioni;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
 public class GestoreVisualizzaStorico{
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
    private SchermataVisualizzaStorico visStorico;

    public GestoreVisualizzaStorico(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		visualizzaStorico();
	}

    public void visualizzaStorico(){
        ArrayList<Donazione> donazioni = db.getDonazione(u.getID_U());
        visStorico = new SchermataVisualizzaStorico();


    }


    public void reindirizzamento(){
        s.setVisible(true);
    }
 }
