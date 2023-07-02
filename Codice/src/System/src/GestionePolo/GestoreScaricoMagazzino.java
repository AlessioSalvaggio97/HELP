package GestionePolo;

import java.util.List;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneDonazioni.AggiornaSchemaDistribuzione;
import GestioneDonazioni.TempoBoundary;
import Main.SchermataPrincipale;
import GestioneFamiglie.SchermataConferma;

public class GestoreScaricoMagazzino {
    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private List<ProdottoInMagazzino> elencoProdotti; // prodotti presenti in magazzino
    private ModuloScaricoMagazzino modSca;
    private List<ProdottoInMagazzino> NuovoElencoProdotti;
    private PannelloErroreQuantità pannErr;
    private SchermataConferma schConf;

    public GestoreScaricoMagazzino(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        gestisciScarico();
    }

    public void gestisciScarico() {
        elencoProdotti = db.getProdottiMagazzino(this); // prodotti presenti in magazzino

        elencoProdotti.get(1).toString();

        modSca = new ModuloScaricoMagazzino(elencoProdotti, this);

    }

    public void isMore(List<ProdottoInMagazzino> nuovoElencoProdotti) {
        int sommaQuantita = 0;
        for (ProdottoInMagazzino prodotto : nuovoElencoProdotti) {
            sommaQuantita += prodotto.getQuantita();
        }

        int capacitaMassimaMagazzino = nuovoElencoProdotti.get(0).getCapienzaMax(); // Supponiamo che tutti i prodotti
                                                                                    // appartengano allo stesso
                                                                                    // magazzino
        if (sommaQuantita > capacitaMassimaMagazzino) {
            modSca = new ModuloScaricoMagazzino(elencoProdotti, this); // riforma il modulo coi prodotti iniziali
        } else {
            modSca.dispose();
            db.aggiornaMagazzino(nuovoElencoProdotti, sommaQuantita);
            schConf = new SchermataConferma("Scarico effettuato con successo!");
            s.setVisible(true);
        }
    }

    public class ProdottoInMagazzino {
        private String nomeProdotto;
        private String proprieta;
        private int quantita;
        private int idMagazzino;
        private int capienzaMax;
        private int capienzaAttuale;
        private int idProdotto;

        public ProdottoInMagazzino(String nomeProdotto, String proprieta, int quantita, int idMagazzino,
                int capienzaMax, int capienzaAttuale, int idProdotto) {
            this.nomeProdotto = nomeProdotto;
            this.proprieta = proprieta;
            this.quantita = quantita;
            this.idMagazzino = idMagazzino;
            this.capienzaMax = capienzaMax;
            this.capienzaAttuale = capienzaAttuale;
            this.idProdotto = idProdotto;
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

        public int getQuantita() {
            return quantita;
        }

        public void setQuantita(int quantita) {
            this.quantita = quantita;
        }

        public int getIdMagazzino() {
            return idMagazzino;
        }

        public void setIdMagazzino(int idMagazzino) {
            this.idMagazzino = idMagazzino;
        }

        public int getCapienzaMax() {
            return capienzaMax;
        }

        public void setCapienzaMax(int capienzaMax) {
            this.capienzaMax = capienzaMax;
        }

        public int getCapienzaAttuale() {
            return capienzaAttuale;
        }

        public void setCapienzaAttuale(int capienzaAttuale) {
            this.capienzaAttuale = capienzaAttuale;
        }

        public int getIdProdotto() {
            return idProdotto;
        }

        public void setIdProdotto(int idProdotto) {
            this.idProdotto = idProdotto;
        }
    }

}
