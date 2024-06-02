package lt.viko.eif.s.dieliautas.Race.database;

import java.sql.*;
import java.util.Properties;

/**
 * Duomenų bazės operacijų klasė, atsakinga už sąveiką su duomenų baze.
 */
public class DatabaseOperations {

    /**
     * Sukuria ir grąžina ryšį su duomenų baze.
     *
     * @return Connection objektas
     * @throws SQLException jei nepavyksta užmegzti ryšio
     */
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/race_database";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "admin");
        return DriverManager.getConnection(url, props);
    }

    /**
     * Gražina visų lenktynių duomenis.
     *
     * @return String su visų lenktynių duomenimis
     */
    public static String viewAllRaces() {
        return executeQuery("SELECT * FROM race", resultSet -> {
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Name: ").append(resultSet.getString("name"))
                        .append(", Location: ").append(resultSet.getString("location"))
                        .append(", Date: ").append(resultSet.getDate("date"))
                        .append("\n");
            }
            return result.toString();
        });
    }

    /**
     * Gražina visų lenktynių informacijos duomenis.
     *
     * @return String su visų lenktynių informacijos duomenimis
     */
    public static String viewAllRaceInfo() {
        return executeQuery("SELECT * FROM raceinfo", resultSet -> {
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Race ID: ").append(resultSet.getInt("race_id"))
                        .append(", Racer ID: ").append(resultSet.getInt("racer_id"))
                        .append(", Finish Time: ").append(resultSet.getTime("finish_time"))
                        .append(", Position Number: ").append(resultSet.getInt("position_number"))
                        .append("\n");
            }
            return result.toString();
        });
    }

    /**
     * Gražina visų lenktynininkų duomenis.
     *
     * @return String su visų lenktynininkų duomenimis
     */
    public static String viewAllRacers() {
        return executeQuery("SELECT * FROM racer", resultSet -> {
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", First Name: ").append(resultSet.getString("first_name"))
                        .append(", Last Name: ").append(resultSet.getString("last_name"))
                        .append(", Phone Number: ").append(resultSet.getString("phone_number"))
                        .append(", Status ID: ").append(resultSet.getInt("status_id"))
                        .append("\n");
            }
            return result.toString();
        });
    }

    /**
     * Gražina visų statusų duomenis.
     *
     * @return String su visų statusų duomenimis
     */
    public static String viewStatus() {
        return executeQuery("SELECT * FROM status", resultSet -> {
            StringBuilder result = new StringBuilder();
            while (resultSet.next()) {
                result.append("ID: ").append(resultSet.getInt("id"))
                        .append(", Status Name: ").append(resultSet.getString("status_name"))
                        .append("\n");
            }
            return result.toString();
        });
    }

    /**
     * Gražina visos duomenų bazės duomenis.
     *
     * @return String su visos duomenų bazės duomenimis
     */
    public static String viewEntireDatabase() {
        return viewAllRaces() + viewAllRaceInfo() + viewAllRacers() + viewStatus();
    }

    private static String executeQuery(String query, ResultSetHandler handler) {
        StringBuilder result = new StringBuilder();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            result.append(handler.handle(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            result.append("ERROR: ").append(e.getMessage());
        }
        return result.toString();
    }

    @FunctionalInterface
    private interface ResultSetHandler {
        String handle(ResultSet resultSet) throws SQLException;
    }
}
