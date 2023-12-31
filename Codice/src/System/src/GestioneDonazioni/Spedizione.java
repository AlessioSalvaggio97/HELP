package GestioneDonazioni;

import java.util.Date;

public class Spedizione {
    private int ID_Spe;
    private int ID_D;
    private int ID_U;
    private Date data_arrivo;
    private String stato;
    private String nomeProdotto;
    private int quantita;
    private String proprietà;
    private Date scadenza;

    public Spedizione() {

    }

    public Spedizione(int ID_Spe, int ID_D, int ID_U, Date data_arrivo, String stato, String nomeProdotto, int quantita,
            String proprietà, Date scadenza) {
        this.ID_Spe = ID_Spe;
        this.ID_D = ID_D;
        this.ID_U = ID_U;
        this.data_arrivo = data_arrivo;
        this.stato = stato;
        this.nomeProdotto = nomeProdotto;
        this.quantita = quantita;
        this.proprietà = proprietà;
        this.scadenza = scadenza;
    }

    public int getID_Spe() {
        return ID_Spe;
    }

    public void setID_Spe(int ID_Spe) {
        this.ID_Spe = ID_Spe;
    }

    public int getID_D() {
        return ID_D;
    }

    public void setID_D(int ID_D) {
        this.ID_D = ID_D;
    }

    public int getID_U() {
        return ID_U;
    }

    public void setID_U(int ID_U) {
        this.ID_U = ID_U;
    }

    public Date getData_arrivo() {
        return data_arrivo;
    }

    public void setData_arrivo(Date data_arrivo) {
        this.data_arrivo = data_arrivo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getProprietà() {
        return proprietà;
    }

    public void setProprietà(String proprietà) {
        this.proprietà = proprietà;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Spedizione [ID_Spe=" + ID_Spe + ", ID_D=" + ID_D + ", ID_U=" + ID_U + ", data_arrivo=" + data_arrivo
                + ", stato=" + stato + ", nomeProdotto=" + nomeProdotto + ", quantita=" + quantita + ", proprietà="
                + proprietà + ", scadenza=" + scadenza + "]";
    }
}
