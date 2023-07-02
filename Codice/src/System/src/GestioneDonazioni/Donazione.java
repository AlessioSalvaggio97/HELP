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

	public int getQuantitaD() {
		return quantitaD;
	}

	public String getScadenza() {
		return scadenza;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public String getProprieta() {
		return proprieta;
	}

	// Optionally, you can override the toString() method to provide a string
	// representation of the object

	@Override
	public String toString() {
		return "Donazione{" +
				"idD=" + idD +
				", idP=" + idP +
				", idM=" + idM +
				", idU=" + idU +
				", data='" + data + '\'' +
				", quantitaD=" + quantitaD +
				", scadenza='" + scadenza + '\'' +
				", nomeProdotto='" + nomeProdotto + '\'' +
				", proprieta='" + proprieta + '\'' +
				'}';
	}
}
