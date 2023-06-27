package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    
    public Connection getConnection() throws Exception{
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/dbms","root", "donazioni");
        System.out.println("Database connesso !");
        return conn;
    }
        
}