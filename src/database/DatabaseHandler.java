package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private Connection connection = null;

    public DatabaseHandler() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException se) {
            System.out.println(EXCEPTION_TAG + " " + se.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Closed Oracle Connection");
            }
        } catch (SQLException se) {
            System.out.println(EXCEPTION_TAG + " " + se.getMessage());
        }
    }

    public void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException se) {
            System.out.println(EXCEPTION_TAG + " " + se.getMessage());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SQL DDL

    public void insertAnimal(int aid, String name, String species, String type) throws SQLException {
            PreparedStatement psAnimal = connection.prepareStatement("INSERT INTO animal VALUES (?,?,?)");
            PreparedStatement psSpecies = connection.prepareStatement("INSERT INTO animalspecies VALUES (?,?)");
            psAnimal.setInt(1, aid);
            psAnimal.setString(2, name);
            psAnimal.setString(3, species);
            psSpecies.setString(1, species);
            psSpecies.setString(2, type);

            try {
                psSpecies.executeUpdate();
            } catch (SQLException se) {
                System.out.println(EXCEPTION_TAG + " " + se.getMessage());
            }
            psAnimal.executeUpdate();

            connection.commit();
            psSpecies.close();
            psAnimal.close();
    }

    public void deleteAnimal(int animalId) throws SQLException {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM animal WHERE aid = ?");
            ps.setInt(1, animalId);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Animal " + animalId + " doesn't exist");
            }
            connection.commit();
            ps.close();
    }

    public void updateAnimalName(String newName, int aid) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE animal SET name = ? WHERE aid = ?");
        ps.setString(1, newName);
        ps.setInt(2, aid);

        int rowCount = ps.executeUpdate();
        if (rowCount == 0) {
            System.out.println(WARNING_TAG + " Animal " + aid + " doesn't exist");
        }
        connection.commit();
        ps.close();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // SQL querying

    /*
    PARAMS: string statement constructed by UI from series of checkboxes, textfields, etc.
    RETURNS: ResultSet of query execution or SQLException if there's a problem
     */
    public ResultSet runQuery(String statement) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet =  stmt.executeQuery(statement);
        connection.commit();
        return resultSet;
    }

}
