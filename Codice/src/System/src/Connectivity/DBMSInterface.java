package Connectivity;

import Autenticazione.ModuloLogin;
import GestioneDonazioni.Richiesta;
import Main.SchermataPrincipale;
import GestioneFamiglie.Famiglia;
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
    public DBMSInterface(ModuloLogin login, SchermataPrincipale s) {
        connetti(login);
    }

    private void connetti(ModuloLogin login) {
        try {
            connDatabase = connClass.getConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(login, "Problema con la connessione al DB, Ritenta", "Errore",
                    JOptionPane.ERROR_MESSAGE);
            connetti(login);
        }
    }

    public ResultSet checkCredentials(String email, String pass) {
        Statement st;
        ResultSet results;
        String query = "SELECT * FROM utente WHERE email = '" + email + "' AND password='" + pass + "';";
        try {
            st = connDatabase.createStatement();
            results = st.executeQuery(query);

            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    // GestioneDonazioni

    public ArrayList<Richiesta> getRichieste(int ID_U){
    	Statement st;
    	ResultSet res;
    	String query = "SELECT * FROM richiesta"; //aggiungere query
    	ArrayList<Richiesta> richieste = new ArrayList<>(); 
    	try {
    		st=connDatabase.createStatement();
    		res = st.executeQuery(query);
    		if(!res.next()) {
    			return null;
    		}else {
    			do {
    				Richiesta r = new Richiesta(); //aggiungere attributi richiesta
    				richieste.add(r);
    			}while(res.next());
    		}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return richieste;
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

            // Mappa le famiglie con i rispettivi componenti
            Map<Integer, Famiglia> famiglieMap = new HashMap<>();

            // Itera sui risultati e crea gli oggetti Famiglia e Componente
            while (rs.next()) {
                int ID_F = rs.getInt("ID_F");
                int ID_U = rs.getInt("ID_U");
                int componenti = rs.getInt("componenti");

                Famiglia famiglia;

                // Controlla se la famiglia è già presente nella mappa
                if (famiglieMap.containsKey(ID_F)) {
                    famiglia = famiglieMap.get(ID_F);
                } else {
                    famiglia = new Famiglia(ID_F, ID_U, componenti);
                    famiglieMap.put(ID_F, famiglia);
                }

                // Crea l'oggetto Componente
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String codice = rs.getString("codice");
                String data = rs.getString("data");
                String indirizzo = rs.getString("indirizzo");
                String bisogni = rs.getString("bisogni");

                Famiglia.Componente componente = famiglia.new Componente(nome, cognome, codice, data, indirizzo,
                        bisogni);

                // Aggiungi il componente all'oggetto Famiglia
                famiglia.getListaComponenti().add(componente);
            }

            // Aggiungi le famiglie alla lista
            famiglie.addAll(famiglieMap.values());
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

    // Gestione spedizione

    public Spedizione getSpedizione() {
        Statement st;
        Spedizione spedizione;

        // Seleziona la spedizione con ID più alto con i dettagli
        String query = "SELECT nome_prodotto, proprietà, quantità_d, scadenza, stato, data_arrivo FROM spedizione JOIN donazione JOIN prodotto WHERE ID_Spe = (SELECT MAX(ID_Spe) FROM spedizione) AND donazione.ID_P = prodotto.ID_P";
        try {
            st = connDatabase.createStatement();
            ResultSet resultSet = st.executeQuery(query);

            if (resultSet.next()) {
                spedizione = new Spedizione();
                spedizione.setNomeProdotto(resultSet.getString("nome_prodotto"));
                spedizione.setProprieta(resultSet.getString("proprietà"));
                spedizione.setQuantitaD(resultSet.getInt("quantità_d"));
                spedizione.setScadenza(resultSet.getDate("scadenza"));
                spedizione.setStato(resultSet.getString("stato"));
                spedizione.setDataArrivo(resultSet.getDate("data_arrivo"));
            }

            resultSet.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spedizione;
    }

    public void invioDatiRicezioneSpedizione(int ID_Spe, int ID_U) { // registra la quantità dei prodotti ricevuti e
                                                                     // aggiorna i dati di magazzino
        Statement st;
        int id_spe = ID_Spe;
        int id_u = ID_U;

        String queryInvio = "UPDATE spedizione SET stato='Consegnato!' WHERE ID_Spe=" + id_spe;
        // Aggiungere l'aggiornamento del magazzino
        // String queryAggiorna = "UPDATE magazzino SET capienza_attuale = "
        try {
            st = connDatabase.createStatement();
            st.executeUpdate(queryInvio);

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
        Spedizione spe = spedizione;

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

    // GestoreConfermaSmistamento
    public List<GestoreConfermaSmistamento.Smistamento> getSmistamento() {
        Statement st;
        List<Smistamento> smistamenti = new ArrayList<>();

        String query = "SELECT ID_Smi, nome_prodotto, proprietà, quantità, scadenza, data_corrente, data_arrivo, stato FROM smistamento JOIN prodotto ON smistamento.ID_P = prodotto.ID_P WHERE stato='In transito!' ";

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

}
