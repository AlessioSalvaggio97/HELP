package GestioneDonazioni;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 
 public class GestoreAggiungiProdotto{
    private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
    private ModuloAggiungiProdotto modAggiungi;

    public GestoreVisualizzaStorico(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		aggiungiProdotto();
	}

    public void aggiungiProdotto(){
        modAggiungi = new ModuloAggiungiProdotto;
    }