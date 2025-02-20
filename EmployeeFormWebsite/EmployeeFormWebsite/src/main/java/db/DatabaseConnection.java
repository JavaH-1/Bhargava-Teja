package db;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/Employee_DB";
        String dbUsername = "root"; 
        String dbPassword = "teja@929"; 

        Class.forName(dbDriver); 
        return DriverManager.getConnection(dbURL, dbUsername, dbPassword); 
    }
}
