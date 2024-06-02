package lt.viko.eif.s.dieliautas.Race.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasė, atsakinga už duomenų bazės ir lentelių kūrimą bei pradinių duomenų įterpimą.
 */
public class DatabaseSetup {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";

    /**
     * Metodas, atliekantis duomenų bazės nustatymus, sukuriant lenteles ir įterpiant pradinius duomenis.
     */
    public static void setup() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Sukuria duomenų bazę
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS race_database");

            // Perjungia į race_database
            statement.executeUpdate("USE race_database");

            // Ištrina esamas lenteles, kad išvengtų pasikartojančių įrašų problemų
            statement.executeUpdate("DROP TABLE IF EXISTS raceinfo");
            statement.executeUpdate("DROP TABLE IF EXISTS racer");
            statement.executeUpdate("DROP TABLE IF EXISTS race");
            statement.executeUpdate("DROP TABLE IF EXISTS status");

            // Sukuria lenteles
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS race (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "location VARCHAR(100) NOT NULL, " +
                    "date DATE NOT NULL)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS status (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "status_name VARCHAR(50) NOT NULL)");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS racer (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "first_name VARCHAR(100) NOT NULL, " +
                    "last_name VARCHAR(100) NOT NULL, " +
                    "phone_number VARCHAR(15) NOT NULL, " +
                    "status_id INT, " +
                    "FOREIGN KEY (status_id) REFERENCES status(id))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS raceinfo (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "race_id INT, " +
                    "racer_id INT, " +
                    "finish_time TIME, " +
                    "position_number INT, " +
                    "FOREIGN KEY (race_id) REFERENCES race(id), " +
                    "FOREIGN KEY (racer_id) REFERENCES racer(id))");

            // Įterpia pradinių duomenų
            statement.executeUpdate("INSERT INTO status (status_name) VALUES ('Active'), ('Retired')");

            statement.executeUpdate("INSERT INTO race (name, location, date) VALUES " +
                    "('Spring Marathon', 'New York', '2023-05-28')," +
                    "('Summer Sprint', 'Los Angeles', '2023-06-15')," +
                    "('Autumn Run', 'Chicago', '2023-07-20')," +
                    "('Winter Dash', 'Houston', '2023-08-25')," +
                    "('Desert Rally', 'Phoenix', '2023-09-30')," +
                    "('Mountain Climb', 'Denver', '2023-10-05')," +
                    "('City Loop', 'San Francisco', '2023-11-11')," +
                    "('Beachside Race', 'Miami', '2023-12-20')," +
                    "('Forest Trail', 'Portland', '2024-01-15')," +
                    "('Urban Challenge', 'Seattle', '2024-02-18')");

            statement.executeUpdate("INSERT INTO racer (first_name, last_name, phone_number, status_id) VALUES " +
                    "('John', 'Doe', '1234567890', 1)," +
                    "('Jane', 'Smith', '0987654321', 1)," +
                    "('Jim', 'Beam', '5555555555', 1)," +
                    "('Jack', 'Daniels', '4444444444', 2)," +
                    "('Jose', 'Cuervo', '3333333333', 2)," +
                    "('Jill', 'Valentine', '2222222222', 1)," +
                    "('Chris', 'Redfield', '1111111111', 1)," +
                    "('Leon', 'Kennedy', '6666666666', 2)," +
                    "('Claire', 'Redfield', '7777777777', 1)," +
                    "('Ada', 'Wong', '8888888888', 1)");

            statement.executeUpdate("INSERT INTO raceinfo (race_id, racer_id, finish_time, position_number) VALUES " +
                    "(1, 1, '01:30:00', 1)," +
                    "(1, 2, '01:35:00', 2)," +
                    "(2, 3, '01:40:00', 1)," +
                    "(2, 4, '01:45:00', 2)," +
                    "(3, 5, '01:50:00', 1)," +
                    "(3, 6, '01:55:00', 2)," +
                    "(4, 7, '02:00:00', 1)," +
                    "(4, 8, '02:05:00', 2)," +
                    "(5, 9, '02:10:00', 1)," +
                    "(5, 10, '02:15:00', 2)," +
                    "(6, 1, '01:20:00', 1)," +
                    "(6, 2, '01:25:00', 2)," +
                    "(7, 3, '01:30:00', 1)," +
                    "(7, 4, '01:35:00', 2)," +
                    "(8, 5, '01:40:00', 1)," +
                    "(8, 6, '01:45:00', 2)," +
                    "(9, 7, '01:50:00', 1)," +
                    "(9, 8, '01:55:00', 2)," +
                    "(10, 9, '02:00:00', 1)," +
                    "(10, 10, '02:05:00', 2)");

            System.out.println("Database setup completed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
