package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbUtils {
    final String DB_URL = "jdbc:mysql://localhost:3306/library";
    final String  USER = "root";
    final String PASS = "12345";
    private Connection conn;
    private static DbUtils instance;

    DbUtils() throws SQLException {
        try {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    public Connection getConnection() {
        return conn;
    }
    public static DbUtils getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbUtils();
        } else if (instance.getConnection().isClosed()) {
            instance = new DbUtils();
        }
        return instance;
    }

}

