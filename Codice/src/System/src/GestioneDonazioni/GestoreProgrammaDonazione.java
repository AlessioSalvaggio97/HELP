package GestioneDonazioni;

import Connectivity.DBMSInterface;
import javax.swing.*;
import java.awt.event.ActionListener;

public class GestoreProgrammaDonazione {
	private SchermataVisualizzaRichieste sv;
	private DBMSInterface db;
	private SchermataProgrammaDonazione sp;
	
	public GestoreProgrammaDonazione(SchermataVisualizzaRichieste sv, DBMSInterface db, SchermataProgrammaDonazione sp) {
		this.sv = sv;
		this.db = db;
		schermataProgrammaDonazione();
		
	}
	
	public void schermataProgrammaDonazione() {
		JButton programma = new JButton();
		ActionListener visLstnr = e ->{
			System.out.println("Programma Donazione");
			sp = new SchermataProgrammaDonazione();
			sp.getChiudi().addActionListener(e1 ->{
				sp.dispose();
			}); 
			
			
	}
}
