package db;

import java.sql.*;

public class BookRepository {
    static final String QUERY = "SELECT id, title, genre, language FROM book";
    private static final String SQL_INSERT_BOOK = "INSERT INTO book (title, genre, language) VALUES (?,?,?)";
    private static final String SQL_INSERT_AUTHOR = "INSERT INTO author (name) VALUES (?)";
    static final String deleteSQL = "DELETE FROM book WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE book SET title=? WHERE id=?";
    static DbUtils connect;

    static {
        try {
            connect = new DbUtils();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertBook(String bookName, String genre, String language) {
        try (Connection con =connect.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT_BOOK)) {

            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, genre);
            preparedStatement.setString(3, language);
            int row = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean whetherBookExists(String title){
        try(Connection con = connect.getConnection();
            PreparedStatement ps =
                    con.prepareStatement
                            ("SELECT title FROM book WHERE title = ?")){
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }
    public static void insertAuthor(String authorName) {

        try (Connection conn = connect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_AUTHOR)) {

            preparedStatement.setString(1, authorName);
            int row = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord(int id, String title) {
        try (Connection conn = connect.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();

            System.out.println(row);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteRecord(int ID) {
        try (Connection connection = connect.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, ID);
            System.out.println("Record Deleted successfully from database.");

        } catch (SQLException e) {
            System.out
                    .println("An exception occurs while deleting data from database. Exception is :: "
                            + e);
        }
    }
    public static void printInfo() {
        try(Connection conn = connect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY)) {

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Title: " + rs.getString("title"));
                System.out.print(", Genre: " + rs.getString("genre"));
                System.out.println(", Language: " + rs.getString("language"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
