package GestionePolo;

import java.util.Date;

public class Report {
    private String ID_Report;
    private String ID_P;
    private String nome_prodotto;
    private String proprietà;
    private int quantità;
    private Date data_scarico;
    private String ID_M;

    public Report(String ID_Report, String ID_P, String nome_prodotto, String proprietà, int quantità,
            Date data_scarico, String ID_M) {
        this.ID_Report = ID_Report;
        this.ID_P = ID_P;
        this.nome_prodotto = nome_prodotto;
        this.proprietà = proprietà;
        this.quantità = quantità;
        this.data_scarico = data_scarico;
        this.ID_M = ID_M;
    }

    @Override
    public String toString() {
        return "ID_Report: " + getID_Report() +
                "\nNome Prodotto: " + getNome_prodotto() +
                "\nProprietà: " + getProprietà() +
                "\nQuantità: " + getQuantità() +
                "\nID_M: " + getID_M() +
                "\nID_P: " + getID_P() +
                "\nData Scarico: " + getData_scarico();
    }

    // Getters and Setters
    public String getID_Report() {
        return ID_Report;
    }

    public void setID_Report(String ID_Report) {
        this.ID_Report = ID_Report;
    }

    public String getID_P() {
        return ID_P;
    }

    public void setID_P(String ID_P) {
        this.ID_P = ID_P;
    }

    public String getNome_prodotto() {
        return nome_prodotto;
    }

    public void setNome_prodotto(String nome_prodotto) {
        this.nome_prodotto = nome_prodotto;
    }

    public String getProprietà() {
        return proprietà;
    }

    public void setProprietà(String proprietà) {
        this.proprietà = proprietà;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public Date getData_scarico() {
        return data_scarico;
    }

    public void setData_scarico(Date data_scarico) {
        this.data_scarico = data_scarico;
    }

    public String getID_M() {
        return ID_M;
    }

    public void setID_M(String ID_M) {
        this.ID_M = ID_M;
    }
}
