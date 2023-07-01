package GestioneDonazioni;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GestoreVisualizzaRichieste{
	private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
	private SchermataVisualizzaRichieste visRichieste;
	
	public GestoreVisualizzaRichieste(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		visualizzaRichieste();
	}
	
	private void visualizzaRichieste() {
		JButton visualizza = s.getVisualizzaRichieste(); //Da definire nella Schermata Principale
		ActionListener visLstnr = e ->{
			System.out.println("Visualizza Richieste");
			visRichieste = new SchermataVisualizzaRichieste();
			visRichieste.getChiudi().addActionListener(e1 ->{
				visRichieste.dispose();
			});
			
			ArrayList<Richiesta> richieste = db.getRichieste(u.getID_U()); //Creare classe Richiesta
			CustomTableModel model = (DefaultTableModel) visRichieste.getTabellaRichieste().getModel();
			String []colonne = {"Prodotto", "Quantità richiesta", ""};
			model.setColumnIdentifiers(colonne);
			String[] riga = new String[3];
			for(int i = 0; i<richieste.size(); i++) {
				riga[0] = richieste.get(i).getProdotto();
				riga[1] = richieste.get(i).getQuantita();
				riga[2] = "Programma Donazione";
				model.addRow(riga);
				
			}
		};
		visualizza.addActionListener(visLstnr);
	}
	

}
