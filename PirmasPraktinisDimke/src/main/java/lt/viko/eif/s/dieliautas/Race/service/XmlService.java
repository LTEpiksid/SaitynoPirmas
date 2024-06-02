package lt.viko.eif.s.dieliautas.Race.service;

import lt.viko.eif.s.dieliautas.Race.database.DatabaseOperations;
import lt.viko.eif.s.dieliautas.Race.model.*;
import lt.viko.eif.s.dieliautas.Race.util.JAXBTransformer;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Paslauga, atsakinga už XML operacijas.
 */
public class XmlService {

    /**
     * Siunčia duomenis XML formatu klientui.
     *
     * @param out PrintWriter objektas siųsti duomenis klientui
     */
    public static void sendXMLData(PrintWriter out) {
        try {
            RaceDatabase raceDatabase = new RaceDatabase();

            // Pridėti lenktynes
            String query = "SELECT * FROM race";
            try (Connection connection = DatabaseOperations.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    Race race = new Race();
                    race.setId(resultSet.getInt("id"));
                    race.setName(resultSet.getString("name"));
                    race.setLocation(resultSet.getString("location"));
                    race.setDate(resultSet.getString("date"));
                    raceDatabase.getRaces().add(race);
                }
            }

            // Pridėti lenktynininkus
            query = "SELECT * FROM racer";
            try (Connection connection = DatabaseOperations.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    Racer racer = new Racer();
                    racer.setId(resultSet.getInt("id"));
                    racer.setFirstName(resultSet.getString("first_name"));
                    racer.setLastName(resultSet.getString("last_name"));
                    racer.setPhoneNumber(resultSet.getString("phone_number"));
                    racer.setStatusId(resultSet.getInt("status_id"));
                    raceDatabase.getRacers().add(racer);
                }
            }

            // Pridėti lenktynių informaciją
            query = "SELECT * FROM raceinfo";
            try (Connection connection = DatabaseOperations.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    RaceInfo raceInfo = new RaceInfo();
                    raceInfo.setId(resultSet.getInt("id"));
                    raceInfo.setRaceId(resultSet.getInt("race_id"));
                    raceInfo.setRacerId(resultSet.getInt("racer_id"));
                    raceInfo.setFinishTime(resultSet.getString("finish_time"));
                    raceInfo.setPositionNumber(resultSet.getInt("position_number"));
                    raceDatabase.getRaceInfos().add(raceInfo);
                }
            }

            // Pridėti statusus
            query = "SELECT * FROM status";
            try (Connection connection = DatabaseOperations.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    Status status = new Status();
                    status.setId(resultSet.getInt("id"));
                    status.setStatusName(resultSet.getString("status_name"));
                    raceDatabase.getStatuses().add(status);
                }
            }

            String xml = JAXBTransformer.transformToXML(raceDatabase);
            out.println(xml);

            // Išsaugoti XML faile
            try (FileWriter fileWriter = new FileWriter("race_database.xml")) {
                fileWriter.write(xml);
            }

        } catch (JAXBException | SQLException | IOException e) {
            e.printStackTrace();
            out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Siunčia XML duomenis kaip objektą klientui.
     *
     * @param out PrintWriter objektas siųsti duomenis klientui
     */
    public static void sendXMLToObject(PrintWriter out) {
        try {
            // Skaityti XML iš failo
            StringBuilder xmlBuilder = new StringBuilder();
            try (BufferedReader fileReader = new BufferedReader(new FileReader("race_database.xml"))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    xmlBuilder.append(line);
                }
            }

            String xml = xmlBuilder.toString();
            RaceDatabase raceDatabase = JAXBTransformer.transformToPOJO(xml, RaceDatabase.class);

            out.println("Race Database:");
            for (Race race : raceDatabase.getRaces()) {
                out.println("Race ID: " + race.getId() + ", Name: " + race.getName() +
                        ", Location: " + race.getLocation() + ", Date: " + race.getDate());
            }
            for (Racer racer : raceDatabase.getRacers()) {
                out.println("Racer ID: " + racer.getId() + ", First Name: " + racer.getFirstName() +
                        ", Last Name: " + racer.getLastName() + ", Phone: " + racer.getPhoneNumber() +
                        ", Status ID: " + racer.getStatusId());
            }
            for (RaceInfo raceInfo : raceDatabase.getRaceInfos()) {
                out.println("RaceInfo ID: " + raceInfo.getId() + ", Race ID: " + raceInfo.getRaceId() +
                        ", Racer ID: " + raceInfo.getRacerId() + ", Finish Time: " + raceInfo.getFinishTime() +
                        ", Position Number: " + raceInfo.getPositionNumber());
            }
            for (Status status : raceDatabase.getStatuses()) {
                out.println("Status ID: " + status.getId() + ", Status Name: " + status.getStatusName());
            }

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            out.println("ERROR: " + e.getMessage());
        }
    }
}
