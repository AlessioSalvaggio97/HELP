package GestioneDonazioni;

import java.sql.Date;

public class Donazione {
	private int ID_D;
	private int ID_P;
	private int quantita_d;
	private Date scadenza;
	
	public Donazione(int ID_D, int ID_P, int quantita, Date scadenza) {
		this.ID_D = ID_D;
		this.ID_P = ID_P;
		this.quantita_d = quantita_d;
		this.scadenza = scadenza;
	}
	
	public int getID_D() {
		return ID_D;
	}
	
	public void setID_D(int ID_D) {
		this.ID_D = ID_D;
	}
	
	
	public int getID_P() {
		return ID_P;
	}
	
	public void setID_P(int ID_P) {
		this.ID_P = ID_P;
	}
	public int getQuantita_d() {
		return quantita_d;
	}
	
	public void setQuantita_d(int quantita_d) {
		this.quantita_d = quantita_d;
	}
	
	public Date getScadenza() {
		return scadenza;
	}
	
	public void setScadenza(int scadenza) {
		this.scadenza = scadenza;
	}