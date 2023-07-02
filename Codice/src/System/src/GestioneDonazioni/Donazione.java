package GestioneDonazioni;

import java.sql.Date;

public class Donazione {
	private int ID_D;
	private int ID_P;
	private int ID_M;
	private int ID_U;
	private Date data;
	private int quantita_d;
	private Date scadenza;
	private String nomeProdotto;
	
	public Donazione(int ID_D, int ID_P, int quantita_d, Date scadenza, String nomeProdotto) {
		this.ID_D = ID_D;
		this.ID_P = ID_P;
		this.ID_M = ID_M;
		this.ID_U = ID_U;
		this.data = data;
		this.quantita_d = quantita_d;
		this.scadenza = scadenza;
		this.nomeProdotto = nomeProdotto;
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

	public int getID_M() {
		return ID_M;
	}
	
	public void setID_M(int ID_M) {
		this.ID_M = ID_M;
	}

	public int getID_U() {
		return ID_U;
	}
	
	public void setID_U(int ID_U) {
		this.ID_U = ID_U;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
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
	
	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}
	
	public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }


	 public String toString() {
        return "Donazione{" +
                "ID_D=" + ID_D +
                ", ID_P=" + ID_P +
				", ID_M=" + ID_M +
				", ID_U=" + ID_U +
				", data='" + data + '\'' +
                ", quantita=" + quantita +
                ", scadenza='" + scadenza + '\'' +
				", nomeProdotto='" + nomeProdotto + '\'' +
                '}';
    }
}