package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    // Connect to your database.
    // Replace server name, username, and password with your credentials

    public Connection getConnection() throws Exception{
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/Database","root", "donazioni");
        System.out.println("Database connesso !");
        return conn;
    }
        
}