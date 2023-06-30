package Connectivity;

import Autenticazione.ModuloLogin;
import Main.SchermataPrincipale;

import javax.swing.*;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

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
            spedizione = st.executeQuery(query);

            return spedizione;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
}
