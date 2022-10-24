package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    static final String  USER = "root";
    static final String PASS = "12345";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected to database");
        return conn;
    }

}
