package Connectivity;

import Autenticazione.ModuloLogin;
import Autenticazione.Utente;
import GestioneDonazioni.Donazione;
import GestioneDonazioni.GestoreAggiungiProdotto;
import GestioneDonazioni.GestoreAggiungiProdotto.Prodotto;
import GestioneDonazioni.Richiesta;
import GestioneDonazioni.Spedizione;
import Main.SchermataPrincipale;
import GestioneFamiglie.Famiglia;
import GestionePolo.GestoreScaricoMagazzino;
import GestionePolo.GestoreScaricoMagazzino.ProdottoInMagazzino;
import GestionePolo.Report;
import GestioneSmistamenti.GestoreConfermaSmistamento;
import GestioneSmistamenti.GestoreConfermaSmistamento.Smistamento;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class DBMSInterface {
    ConnectionClass connClass = new ConnectionClass();
    Connection connDatabase;

    // Autenticazione
    public DBMSInterface() {
        connetti();
    }

    private void connetti() {
        try {
            connDatabase = connClass.getConnection();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Problema con la connessione");

            /*
             * JOptionPane.showMessageDialog(login,
             * "Problema con la connessione al DB, Ritenta", "Errore",
             * JOptionPane.ERROR_MESSAGE);
             * connetti(login);
             */
        }
    }

    public Object[] verificaCredenziali(String email) {
        Statement st;
        ResultSet results;
        String query = "SELECT * FROM utente WHERE email = '" + email + "';";
        try {
            st = connDatabase.createStatement();
            results = st.executeQuery(query);

            if (results.next()) {
                Object[] credentials = new Object[7];
                credentials[0] = results.getInt("ID_U");
                credentials[1] = results.getString("nome");
                credentials[2] = results.getString("cognome");
                credentials[3] = results.getString("indirizzo");
                credentials[4] = results.getString("ruolo");
                credentials[5] = results.getString("telefono");
                credentials[6] = results.getString("password");
                return credentials;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verificaOTP(int OTPInserito, String emailInserita){
        
        return true;
    }
    
    // GestioneFamiglia

    public boolean verificaDatiFamiglia(List<Famiglia.Componente> membriFamiglia) {
        PreparedStatement st;

        String query = "SELECT COUNT(*) FROM componenti WHERE codice = ?";
        try {
            st = connDatabase.prepareStatement(query);
            for (Famiglia.Componente componente : membriFamiglia) {
                st.setString(1, componente.getCodice());
                ResultSet resultSet = st.executeQuery();
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        return true; // The codice already exists in the componenti
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // The codice doesn't exist in the componenti
    }

    public void inviaDatiFamiglia(List<Famiglia.Componente> membriFamiglia) {
        try {
            // Inserisce il numero di componenti nella tabella famiglia (aggiungere ID_U del
            // polo utente che lo sta mettendo?)
            int componentiSize = membriFamiglia.size();
            String famigliaQuery = "INSERT INTO famiglia (componenti) VALUES (?)";

            PreparedStatement famigliaStatement = connDatabase.prepareStatement(famigliaQuery);
            famigliaStatement.setInt(1, componentiSize);
            famigliaStatement.executeUpdate();
            famigliaStatement.close();

            // Inserisce tutti i dati dei componenti nella tabella componente
            String componenteQuery = "INSERT INTO componente (codice_fiscale, nome, cognome, data_nascita, indirizzo, bisogni) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement componenteStatement = connDatabase.prepareStatement(componenteQuery);

            for (Famiglia.Componente componente : membriFamiglia) {
                componenteStatement.setString(1, componente.getCodice());
                componenteStatement.setString(2, componente.getNome());
                componenteStatement.setString(3, componente.getCognome());
                componenteStatement.setString(4, componente.getData());
                componenteStatement.setString(5, componente.getIndirizzo());
                componenteStatement.setString(6, componente.getBisogni());
                componenteStatement.executeUpdate();
            }

            componenteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VisualizzaDatiFamiglia

    public List<Famiglia> getElencoFamiglie() {
        List<Famiglia> famiglie = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            // Crea lo statement
            st = connDatabase.createStatement();

            // Esegui la query
            String query = "SELECT * FROM famiglia JOIN componente ON famiglia.ID_F=componente.ID_F";
            rs = st.executeQuery(query);

            // Itera sui risultati e crea gli oggetti Famiglia e Componente
            while (rs.next()) {
                int ID_F = rs.getInt("ID_F");
                int ID_U = rs.getInt("ID_U");
                int componenti = rs.getInt("componenti");

                Famiglia famiglia = new Famiglia(ID_F, ID_U, componenti);

                // Crea l'oggetto Componente
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String codice = rs.getString("codice_fiscale");
                String data = rs.getString("data_nascita");
                String indirizzo = rs.getString("indirizzo");
                String bisogni = rs.getString("bisogni");

                Famiglia.Componente componente = famiglia.new Componente(nome, cognome, codice, data, indirizzo,
                        bisogni);

                // Aggiungi il componente all'oggetto Famiglia
                famiglia.getListaComponenti().add(componente);

                // Aggiungi la famiglia alla lista
                famiglie.add(famiglia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Chiudi le risorse
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return famiglie;
    }

    public void eliminaMembro(String codice) {
        Statement st;
        String query = "DELETE FROM componente WHERE codice_fiscale = " + codice;
        System.out.println(query);
        try {
            riduciMembriFamiglia(codice);
            st = connDatabase.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void riduciMembriFamiglia(String codice_membro) {
        Statement st;
        String query = "SELECT COUNT(*) AS membri_famiglia FROM componente WHERE ID_F = ( SELECT ID_F  FROM componente WHERE codice_fiscale = '"
                + codice_membro + "')";
        System.out.println(query);

        try {
            st = connDatabase.createStatement();

            // Esegue la query
            ResultSet resultSet = st.executeQuery(query);

            int nuoviMembriFamiglia = resultSet.getInt("membri_famiglia") - 1;

            query = "UPDATE famiglia SET componenti = " + nuoviMembriFamiglia
                    + "WHERE ID_F = ( SELECT ID_F  FROM componente WHERE codice_fiscale = '"
                    + codice_membro + "')";
            System.out.println(query);

            try {
                st = connDatabase.createStatement();
                st.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // GestioneDonazioni

    public ArrayList<Richiesta> getRichieste(int ID_U) {
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM richiesta JOIN prodotto ON richiesta.ID_P=prodotto.ID_P";
        ArrayList<Richiesta> richieste = new ArrayList<>();

        try {
            st = connDatabase.createStatement();
            res = st.executeQuery(query);

            while (res.next()) {
                int ID_R = res.getInt("ID_R");
                int ID_P = res.getInt("ID_P");
                int quantita = res.getInt("quantità");
                String nomeProdotto = res.getString("nome_prodotto");
                String proprieta = res.getString("proprietà");

                Richiesta r = new Richiesta(ID_R, ID_P, ID_U, quantita, nomeProdotto, proprieta);
                richieste.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return richieste;
    }

    // Aggiornamento schema di distribuzione da continuare
    public void AggiornaSchemaDistribuzione() {
        Statement st;

        String query = "UPDATE schema_di_distr";
    }

    // Modifica donazione
    public List<Donazione> getDonazione() {
        Statement st;
        List<Donazione> donazioni = new ArrayList<>();

        String query = "SELECT ID_D, donazione.ID_P, ID_M, donazione.ID_U, data, quantità_d, scadenza, nome_prodotto, proprietà FROM donazione JOIN prodotto WHERE donazione.ID_P = prodotto.ID_P";
        try {
            st = connDatabase.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                int idD = resultSet.getInt("ID_D");
                int idP = resultSet.getInt("ID_P");
                int idM = resultSet.getInt("ID_M");
                int idU = resultSet.getInt("ID_U");
                String data = resultSet.getString("data");
                int quantitaD = resultSet.getInt("quantità_d");
                String scadenza = resultSet.getString("scadenza");
                String nomeProdotto = resultSet.getString("nome_prodotto");
                String proprieta = resultSet.getString("proprietà");

                Donazione donazione = new Donazione(idD, idP, idM, idU, data, quantitaD, scadenza, nomeProdotto,
                        proprieta);
                donazioni.add(donazione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions here
        } finally {
            // Close the resultSet, statement, and connection if needed
        }

        return donazioni;
    }

    // Gestione smistamenti (con conferma ricezione spedizione)

    public List<Spedizione> getSpedizionePrevista() {
        Statement st;
        List<Spedizione> spedizioni = new ArrayList<>();

        // Seleziona le spedizioni previste con i dettagli
        String query = "SELECT nome_prodotto, proprietà, quantità_d, scadenza, stato FROM spedizione JOIN donazione JOIN prodotto WHERE ID_Spe = (SELECT MAX(ID_Spe) FROM spedizione) AND donazione.ID_P = prodotto.ID_P";
        try {
            st = connDatabase.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()) {
                String nomeProdotto = resultSet.getString("nome_prodotto");
                String proprietà = resultSet.getString("proprietà");
                int quantita = resultSet.getInt("quantità_d");
                Date scadenza = resultSet.getDate("scadenza");
                String stato = resultSet.getString("stato");

                Spedizione spedizione = new Spedizione();
                spedizione.setNomeProdotto(nomeProdotto);
                spedizione.setProprietà(proprietà);
                spedizione.setQuantita(quantita);
                spedizione.setScadenza(scadenza);
                spedizione.setStato(stato);

                spedizioni.add(spedizione);
            }

            resultSet.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spedizioni;
    }

    public void invioDatiRicezioneSpedizione(List<Spedizione> spedizioni, int ID_U) {
        Statement st;
        int id_u = ID_U;

        String queryAggiorna = "UPDATE magazzino SET capienza_attuale = ";

        try {
            st = connDatabase.createStatement();

            for (Spedizione spedizione : spedizioni) {
                int id_spe = spedizione.getID_Spe();
                String queryInvio = "UPDATE spedizione SET stato='Consegnato!' WHERE ID_Spe=" + id_spe;

                st.executeUpdate(queryInvio);

                // update magazzino?
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void invioDatiQuantitàArrivate(Spedizione spedizione, float[] quantitàArrivate) {
        Statement st;
        Spedizione spe = spedizione;
        float[] qa = new float[quantitàArrivate.length];
        System.arraycopy(quantitàArrivate, 0, qa, 0, quantitàArrivate.length);

        String queryInvio = "INSERT INTO famiglia ()";
        // gestione da aggiungere
        // Aggiungere query per aggiornamento database
        try {
            st = connDatabase.createStatement();
            st.executeUpdate(queryInvio);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void invioNotifica() {
        Statement st;
        Spedizione spe;

        String queryInvio = "INSERT INTO notifica descrizione VALUES ('Segnalato errore di spedizione.')";
        // gestione da aggiungere
        // Aggiungere query per aggiornamento database
        try {
            st = connDatabase.createStatement();
            st.executeUpdate(queryInvio);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String[]> getSchema() {
        Statement st;
        List<String[]> schema = new ArrayList<>();

        String csvFilePath = "Reviews-export.csv";

        String query = "SELECT nome_prodotto, proprietà, quantità, ID_visualizzatore, ID_ricevente, ID_F FROM schema_di_distr JOIN prodotto using (ID_P)";

        try {
            st = connDatabase.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                String nome_prodotto = result.getString("nome_prodotto");
                String proprietà = result.getString("proprietà");
                float quantità = result.getFloat("quantità");
                int ID_visualizzatore = result.getInt("ID_visualizzatore");
                int ID_ricevente = result.getInt("ID_ricevente");
                int ID_F = result.getInt("ID_F");

                String[] row = { nome_prodotto, proprietà, String.valueOf(quantità), String.valueOf(ID_visualizzatore),
                        String.valueOf(ID_ricevente), String.valueOf(ID_F) };

                schema.add(row);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schema;
    }

    // Gestione donazioni - Aggiungi prodotto

    public List<Prodotto> getLista(int ID_U, GestoreAggiungiProdotto gap) {
        List<Prodotto> listaProdotti = new ArrayList<>();

        try {
            String query = "SELECT * FROM prodotto WHERE prodotto.ID_U NOT IN (SELECT azienda.ID_U FROM azienda WHERE ID_U = ?) OR ID_U IS NULL";
            PreparedStatement statement = connDatabase.prepareStatement(query);
            statement.setInt(1, ID_U);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Prodotto prodotto = gap.new Prodotto();
                prodotto.setID_P(resultSet.getInt("ID_P"));
                prodotto.setNome_prodotto(resultSet.getString("nome_prodotto"));
                prodotto.setProprietà(resultSet.getString("proprietà"));
                prodotto.setID_U(resultSet.getInt("ID_U"));

                listaProdotti.add(prodotto);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProdotti;
    }

    public void inviaProdottiSelezionati(List<Prodotto> prodotti, int ID_U) {
        try {
            // Itera sulla lista dei prodotti selezionati
            for (Prodotto prodotto : prodotti) {
                // Verifica se esiste già una riga per il prodotto e l'ID_U forniti
                String selectQuery = "SELECT * FROM prodotti WHERE nome_prodotto = ? AND ID_U IS NULL";
                PreparedStatement selectStatement = connDatabase.prepareStatement(selectQuery);
                selectStatement.setString(1, prodotto.getNome_prodotto());
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    // Una riga esiste già, quindi aggiorna la colonna ID_U
                    int existingID_P = resultSet.getInt("ID_P");
                    String updateQuery = "UPDATE prodotto SET ID_U = ? WHERE ID_P = ?";
                    PreparedStatement updateStatement = connDatabase.prepareStatement(updateQuery);
                    updateStatement.setInt(1, ID_U);
                    updateStatement.setInt(2, existingID_P);
                    updateStatement.executeUpdate();
                } else {
                    // Nessuna riga trovata, quindi inserisci una nuova riga
                    String insertQuery = "INSERT INTO prodotto (nome_prodotto, proprietà, ID_U) VALUES (?, ?, ?)";
                    PreparedStatement insertStatement = connDatabase.prepareStatement(insertQuery);
                    insertStatement.setString(1, prodotto.getNome_prodotto());
                    insertStatement.setString(2, prodotto.getProprietà());
                    insertStatement.setInt(3, ID_U);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            // Gestisci eventuali eccezioni
        }
    }

    public List<Donazione> getRecuperoStorico(){ ///
        List<Donazione> storicoDonazioni=null;
        return storicoDonazioni;
    }

    // GestoreConfermaSmistamento
    public List<GestoreConfermaSmistamento.Smistamento> getSmistamento() {
        Statement st;
        List<Smistamento> smistamenti = new ArrayList<>();

        String query = "SELECT ID_Smi, nome_prodotto, proprietà, quantità_d, scadenza, data_corrente, data_arrivo, stato FROM smistamento JOIN prodotto ON smistamento.ID_P = prodotto.ID_P WHERE stato='In transito!' ";

        try {
            st = connDatabase.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()) {
                Smistamento smistamento = new Smistamento();
                smistamento.setID_Smi(resultSet.getInt("ID_Smi"));
                smistamento.setNomeProdotto(resultSet.getString("nome_prodotto"));
                smistamento.setProprieta(resultSet.getString("proprietà"));
                smistamento.setQuantitaD(resultSet.getInt("quantità_d"));
                smistamento.setScadenza(resultSet.getDate("scadenza"));
                smistamento.setDataCorrente(resultSet.getDate("data_corrente"));
                smistamento.setDataArrivo(resultSet.getDate("data_arrivo"));
                smistamento.setStato(resultSet.getString("stato"));

                smistamenti.add(smistamento);
            }

            resultSet.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return smistamenti;
    }

    public void confermaSmistamento(int ID_Smi) {
        Statement st;

        String queryConferma = "UPDATE smistamento SET stato='Consegnato!' WHERE ID_Smi=" + ID_Smi;

        try {
            st = connDatabase.createStatement();
            st.executeUpdate(queryConferma);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GestionePolo

    // Gestore Scarico Magazzino

    public List<ProdottoInMagazzino> getProdottiMagazzino(GestoreScaricoMagazzino gest) {
        List<ProdottoInMagazzino> prodotti = new ArrayList<>();
        String query = "SELECT nome_prodotto, proprietà, quantità, magazzino.ID_M, capienza_max, capienza_attuale, magazzino.ID_U, prodotto.ID_P FROM magazzino JOIN contiene JOIN prodotto ON magazzino.ID_M=contiene.ID_M AND prodotto.ID_P=contiene.ID_P";

        ProdottoInMagazzino prodotto;

        try {
            Statement statement = connDatabase.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String nomeProdotto = resultSet.getString("nome_prodotto");
                String proprieta = resultSet.getString("proprietà");
                int quantita = resultSet.getInt("quantità");
                int idMagazzino = resultSet.getInt("ID_M");
                int capienzaMax = resultSet.getInt("capienza_max");
                int capienzaAttuale = resultSet.getInt("capienza_attuale");
                int idProdotto = resultSet.getInt("ID_P");

                prodotto = gest.new ProdottoInMagazzino(nomeProdotto, proprieta, quantita, idMagazzino,
                        capienzaMax, capienzaAttuale, idProdotto);
                prodotti.add(prodotto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prodotti;
    }

    public void aggiornaMagazzino(List<ProdottoInMagazzino> nuovoElencoProdotti, float sommaQuantita) {
        PreparedStatement updateMagazzino = null;
        PreparedStatement updateContiene = null;

        try {
            // Aggiorna la tabella magazzino
            String updateMagazzinoQuery = "UPDATE magazzino SET capienza_attuale = ? WHERE ID_M = ?";
            updateMagazzino = connDatabase.prepareStatement(updateMagazzinoQuery);

            for (ProdottoInMagazzino prodotto : nuovoElencoProdotti) {
                int nuovaCapienzaAttuale = prodotto.getCapienzaAttuale() + (int) sommaQuantita;
                updateMagazzino.setInt(1, nuovaCapienzaAttuale);
                updateMagazzino.setInt(2, prodotto.getIdMagazzino());
                updateMagazzino.executeUpdate();
            }

            // Aggiorna la tabella contiene
            String updateContieneQuery = "UPDATE contiene SET quantità = ? WHERE ID_P = ? AND ID_M = ?";
            updateContiene = connDatabase.prepareStatement(updateContieneQuery);

            for (ProdottoInMagazzino prodotto : nuovoElencoProdotti) {
                int nuovaQuantita = prodotto.getQuantita() - (int) sommaQuantita;
                updateContiene.setInt(1, nuovaQuantita);
                // updateContiene.setInt(2, prodotto.getID_P());
                updateContiene.setInt(3, prodotto.getIdMagazzino());
                updateContiene.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (updateMagazzino != null) {
                    updateMagazzino.close();
                }
                if (updateContiene != null) {
                    updateContiene.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Report> getDatiScarico(Utente u) {
        List<Report> datiScarico = new ArrayList<>();

        String query = "SELECT ID_Scarico, scarico.ID_P, nome_prodotto, proprietà, quantità_smist, data_scarico, ID_M FROM scarico JOIN prodotto ON scarico.ID_P = prodotto.ID_P";

        if (!u.getRuolo().equals("Amministratore")) {
            query += " WHERE scarico.ID_U = ?";
        }

        try (PreparedStatement stmt = connDatabase.prepareStatement(query)) {
            if (!u.getRuolo().equals("Amministratore")) {
                stmt.setInt(1, u.getID_U());
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String ID_Scarico = rs.getString("ID_Scarico");
                String ID_P = rs.getString("scarico.ID_P");
                String nome_prodotto = rs.getString("nome_prodotto");
                String proprietà = rs.getString("proprietà");
                int quantità_smist = rs.getInt("quantità_smist");
                Date data_scarico = rs.getDate("data_scarico");
                String ID_M = rs.getString("ID_M");

                Report report = new Report(ID_Scarico, ID_P, nome_prodotto, proprietà, quantità_smist, data_scarico,
                        ID_M);
                datiScarico.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datiScarico;
    }

    // da continuare public Report scaricaReport(Utente u) {}

    //Sospendi polo
    public void sospendiPolo(){

    }

}
