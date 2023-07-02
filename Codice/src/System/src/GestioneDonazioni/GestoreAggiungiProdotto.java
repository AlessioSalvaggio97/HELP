package GestioneDonazioni;

import Main.SchermataPrincipale;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestoreAggiungiProdotto {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private ModuloAggiungiProdotto modAggiungi;
    private List<Prodotto> listaProdottiSelezionabili;

    public GestoreAggiungiProdotto(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        GestisciAggiungiProdotto();
    }

    public void GestisciAggiungiProdotto() {

        listaProdottiSelezionabili = db.getLista(u.getID_U(), this);

        System.out.println(listaProdottiSelezionabili.get(1).getNome_prodotto());

        modAggiungi = new ModuloAggiungiProdotto(listaProdottiSelezionabili, this);
        modAggiungi.setVisible(true);

    }

    public void aggiungiProdotti(List<Prodotto> prodottiSelezionati) {
        System.out.println(prodottiSelezionati.get(0).getNome_prodotto());
        db.inviaProdottiSelezionati(prodottiSelezionati, u.getID_U());
    }

    public class Prodotto {
        private int ID_P;
        private String nome_prodotto;
        private String proprietà;
        private int ID_U;

        public Prodotto() {

        }

        public Prodotto(int ID_P, String nome_prodotto, String proprietà, int ID_U) {
            this.ID_P = ID_P;
            this.nome_prodotto = nome_prodotto;
            this.proprietà = proprietà;
            this.ID_U = ID_U;
        }

        public int getID_P() {
            return ID_P;
        }

        public void setID_P(int ID_P) {
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

        public int getID_U() {
            return ID_U;
        }

        public void setID_U(int ID_U) {
            this.ID_U = ID_U;
        }
    }

}
