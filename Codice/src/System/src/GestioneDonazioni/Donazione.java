package GestioneDonazioni;

public class Donazione {
    private int ID_D;
    private int ID_P;
    private int ID_M;
    private int ID_U;
    private String data;
    private int quantita_d;
    private String scadenza;
    private String nomeProdotto;
    private String proprietà;

    public Donazione(int ID_D, int ID_P, int ID_M, int ID_U, String data, int quantita_d, String scadenza, String nomeProdotto, String proprietà) {
        this.ID_D = ID_D;
        this.ID_P = ID_P;
        this.ID_M = ID_M;
        this.ID_U = ID_U;
        this.data = data;
        this.quantita_d = quantita_d;
        this.scadenza = scadenza;
        this.nomeProdotto = nomeProdotto;
        this.proprietà = proprietà;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantita_d() {
        return quantita_d;
    }

    public void setQuantita_d(int quantita_d) {
        this.quantita_d = quantita_d;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getProprietà() {
        return proprietà;
    }

    public void setProprietà(String proprietà) {
        this.proprietà = proprietà;
    }

	@Override
    public String toString() {
        return "Donazione{" +
                "ID_D=" + ID_D +
                ", ID_P=" + ID_P +
                ", ID_M=" + ID_M +
                ", ID_U=" + ID_U +
                ", data='" + data + '\'' +
                ", quantita_d=" + quantita_d +
                ", scadenza='" + scadenza + '\'' +
                ", nomeProdotto='" + nomeProdotto + '\'' +
                ", proprietà='" + proprietà + '\'' +
                '}';
    }
}
