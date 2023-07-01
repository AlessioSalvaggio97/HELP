package Autenticazione;

import Main.SchermataPrincipale;

import Connectivity.DBMSInterface;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestoreLogin {
	private SchermataPrincipale s; // Da definire
	private ModuloLogin login;
	private JLabel errore;
	private Object[] tabellaUtente;
	private DBMSInterface db;
	private ResultSet res;
	private String[] credenzialiInserite;
	private String[] credenzialiCorrette;
	private Utente utente;

	public GestoreLogin(DBMSInterface db) {
		this.db = db;
	}

	public void gestisciAccesso(String email, String passwordInserita, ModuloLogin modLog) {

		tabellaUtente = db.verificaCredenziali(email); // "verifica credenziali" in realtà invia i dati dell'utente

		if (tabellaUtente != null) {

			String passwordCorretta = (String) tabellaUtente[6];

			if (passwordCorretta.equals(passwordInserita)) { // If the passwords match
				int id = (int) tabellaUtente[0];
				String nome = (String) tabellaUtente[1];
				String cognome = (String) tabellaUtente[2];
				String indirizzo = (String) tabellaUtente[3];
				String ruolo = (String) tabellaUtente[4];
				String telefono = (String) tabellaUtente[5];

				utente = new Utente();
				utente.setID_U(id);
				utente.setNome(nome);
				utente.setCognome(cognome);
				utente.setIndirizzo(indirizzo);
				utente.setRuolo(ruolo);
				utente.setTelefono(telefono);

				modLog.reindirizzamento(utente, db);
			} else {
				System.out.println(passwordInserita);
				System.out.println("Credenziali errate");
			}
		} else {System.out.println("Utente non trovato");
		}

	}

}
