package GestioneSmistamenti;

import Connectivity.DBMSInterface;

import java.util.Date;
import java.util.List;

import Autenticazione.Utente;
import Main.SchermataPrincipale;

public class GestoreConfermaSmistamento {

    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private SchermataConfermaSmistamento scConfSmi;
    private List<Smistamento> smi;

    public GestoreConfermaSmistamento(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;

        smi = db.getSmistamento();

        scConfSmi = new SchermataConfermaSmistamento(smi, this);

    }

    public void chiudiSchermata() {

        //Chiude la schermata
        scConfSmi.dispose();

        // Mostra la SchermataPrincipale
        s.setVisible(true);
    }

    public void confermaSmistamento(int ID_Smi) {

        //Conferma lo smistamento di quel singolo ID_Smi
        db.confermaSmistamento(ID_Smi);
    }

    public static class Smistamento {
        private int ID_Smi;
        private String nome_prodotto;
        private String proprietà;
        private int quantità_d;
        private Date scadenza;
        private Date data_corrente;
        private Date data_arrivo;
        private String stato;

        public int getID_Smi() {
            return ID_Smi;
        }

        public void setID_Smi(int ID_Smi) {
            this.ID_Smi = ID_Smi;
        }

        public String getNomeProdotto() {
            return nome_prodotto;
        }

        public void setNomeProdotto(String nome_prodotto) {
            this.nome_prodotto = nome_prodotto;
        }

        public String getProprieta() {
            return proprietà;
        }

        public void setProprieta(String proprietà) {
            this.proprietà = proprietà;
        }

        public int getQuantitaD() {
            return quantità_d;
        }

        public void setQuantitaD(int quantità_d) {
            this.quantità_d = quantità_d;
        }

        public Date getScadenza() {
            return scadenza;
        }

        public void setScadenza(Date scadenza) {
            this.scadenza = scadenza;
        }

        public Date getDataCorrente() {
            return data_corrente;
        }

        public void setDataCorrente(Date data_corrente) {
            this.data_corrente = data_corrente;
        }

        public Date getDataArrivo() {
            return data_arrivo;
        }

        public void setDataArrivo(Date data_arrivo) {
            this.data_arrivo = data_arrivo;
        }

        public String getStato() {
            return stato;
        }

        public void setStato(String stato) {
            this.stato = stato;
        }
    }

}
