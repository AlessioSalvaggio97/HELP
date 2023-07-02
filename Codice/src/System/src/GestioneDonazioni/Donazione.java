package GestioneDonazioni;

public class Donazione {
	private int idD;
	private int idP;
	private int idM;
	private int idU;
	private String data;
	private int quantitaD;
	private String scadenza;
	private String nomeProdotto;
	private String proprieta;

	public Donazione(int idD, int idP, int idM, int idU, String data, int quantitaD, String scadenza,
			String nomeProdotto, String proprieta) {
		this.idD = idD;
		this.idP = idP;
		this.idM = idM;
		this.idU = idU;
		this.data = data;
		this.quantitaD = quantitaD;
		this.scadenza = scadenza;
		this.nomeProdotto = nomeProdotto;
		this.proprieta = proprieta;
	}

	public int getIdD() {
		return idD;
	}

	public int getIdP() {
		return idP;
	}

	public int getIdM() {
		return idM;
	}

	public int getIdU() {
		return idU;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public int getQuantitaD() {
		return quantitaD;
	}

	public String getScadenza() {
		return scadenza;
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
