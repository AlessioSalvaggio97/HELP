package GestioneDonazioni;

public class Richiesta {
    private int ID_R;
    private int ID_P;
    private int ID_U;
    private int quantita;
    private String nomeProdotto;
    private String proprieta;

    public Richiesta(int ID_R, int ID_P, int ID_U, int quantita, String nomeProdotto, String proprieta) {
        this.ID_R = ID_R;
        this.ID_P = ID_P;
        this.ID_U = ID_U;
        this.quantita = quantita;
        this.nomeProdotto = nomeProdotto;
        this.proprieta = proprieta;
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

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getProprieta() {
        return proprieta;
    }

    public void setProprieta(String proprieta) {
        this.proprieta = proprieta;
    }

    public String toString() {
        return "Richiesta{" +
                "ID_R=" + ID_R +
                ", ID_P=" + ID_P +
                ", ID_U=" + ID_U +
                ", quantita=" + quantita +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                ", proprieta='" + proprieta + '\'' +
                '}';
    }
}
