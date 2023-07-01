package Autenticazione;

public class Utente {
	private int ID_U;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String ruolo;
	private String telefono;
	
	public int getID_U(){
		return ID_U;
	}
	
	public void setID_U(int ID_U) {
		this.ID_U = ID_U;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
