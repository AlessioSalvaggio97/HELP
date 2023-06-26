package Connectivity;

import Autenticazione.ModuloLogin;
import javax.swing.*;
import java.sql.*;

public class DBMSInterface {
    ConnectionClass connClass = new ConnectionClass();
    Connection connDatabase;
    public DBMSInterface(ModuloLogin login, SchermataPrincipale s){
        connetti(login);
    }
    
    private void connetti(ModuloLogin login){
        try {
            connDatabase = connClass.getConnection();
        }catch (Exception e){
            JOptionPane.showMessageDialog(login, "Problema con la connessione al DB, Ritenta", "Errore", JOptionPane.ERROR_MESSAGE);
            connetti(login);
        }
    }
    
    public ResultSet checkCredentials(String email, String pass){
        Statement st;
        ResultSet results;
        String query="SELECT * FROM utente WHERE email = '"+email+"' AND password='"+pass+"';";
        try {
            st = connDatabase.createStatement();
            results = st.executeQuery(query);

            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
}
    