package GestioneDonazioni;

public class Richiesta {
	private int ID_R;
	private int ID_P;
	private int ID_U;
	private int quantita;
	
	public Richiesta(int ID_R, int ID_P, int ID_U, int quantita) {
		this.ID_R = ID_R;
		this.ID_P = ID_P;
		this.ID_U = ID_U;
		this.quantita = quantita;
	}
	
	public int getID_U() {
		return ID_U;
	}
	
	public void setID_U(int ID_U) {
		this.ID_U = ID_U;
	}
	
	
	public int getID_R() {
		return ID_R;
	}
	
	public void setID_R(int ID_R) {
		this.ID_R = ID_R;
	}
	public int getID_P() {
		return ID_P;
	}
	
	public void setID_P(int ID_P) {
		this.ID_P = ID_P;
	}
	
	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	public String toString() {
		return "Richiesta{" + ""
	}



}
