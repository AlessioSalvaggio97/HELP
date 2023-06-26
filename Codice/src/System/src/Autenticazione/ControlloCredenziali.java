package Autenticazione;

import Connectivity.DBMSInterface;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlloCredenziali {
	private final SchermataPrincipale s; //Da definire
	private final ModuloLogin login;
	private JLabel errore;
	private final Utente utente;
	private final DBMSInterface db;
	private ResultSet res;

	public ControlloCredenziali(SchermataPrincipale sc, Utente u, DBMSInterface dbms, ModuloLogin login){
		this.s = sc;
		this.utente = u;
		this.db = dbms;
		this.login = login;
		gestisciAccesso();
	}
	
	private void gestisciAccesso(){
		JButton accedi = login.getAccedi();
		errore = login.getErrore();
		ActionListener accediLstnr = e -> {
			JTextField email = login.getEmail();
			JTextField pass = login.getPassword();
			res = db.checkCredentials(email.getText(), pass.getText());
			checkResults(res, "utente");
			};
			
		accedi.addActionListener(accediLstnr);
		}
	
	private void checkResults(ResultSet r, String ruolo) {
		if(r!= null) {
			try {
				if(!r.next()) {
					errore.setVisible(true);
				}else {
					do {
						utente.setID_U(res.getInt("ID_U"));
						utente.setNome(res.getString("nome"));
						utente.setCognome(res.getString("cognome"));
						utente.setRuolo(res.getString("ruolo"));
						utente.setIndirizzo(res.getString("indirizzo"));
						utente.setTelefono(res.getInt("telefono"));
						
						switch(res.getString("ruolo")){
							case "Azienda":
								s.initAzienda();
								break;
							
							case "Diocesi":
								s.initDiocesi();
								break;
							case "RPT":
								s.initRPT();
								break;
							
							case "Amministratore":
								s.initAmministratore();
								break;
								
						}
						
						s.setVisible(true);
						login.setVisible(false);
						
					} while(r.next());
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		
	
	
	
	}
	
	
	 
}
