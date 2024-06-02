package lt.viko.eif.s.dieliautas.Race.server;

import lt.viko.eif.s.dieliautas.Race.database.DatabaseOperations;
import lt.viko.eif.s.dieliautas.Race.service.XmlService;

import java.io.*;
import java.net.Socket;

/**
 * Kliento tvarkyklė, atsakinga už kiekvieno kliento ryšio valdymą.
 */
public class ClientHandler extends Thread {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);

                switch (request) {
                    case "LOAD_RACE":
                        out.println(DatabaseOperations.viewAllRaces());
                        break;
                    case "LOAD_RACEINFO":
                        out.println(DatabaseOperations.viewAllRaceInfo());
                        break;
                    case "LOAD_RACER":
                        out.println(DatabaseOperations.viewAllRacers());
                        break;
                    case "LOAD_STATUS":
                        out.println(DatabaseOperations.viewStatus());
                        break;
                    case "LOAD_ENTIRE_DATABASE":
                        out.println(DatabaseOperations.viewEntireDatabase());
                        break;
                    case "LOAD_XML":
                        XmlService.sendXMLData(out);
                        break;
                    case "LOAD_XML_TO_OBJECT":
                        XmlService.sendXMLToObject(out);
                        break;
                    default:
                        out.println("INVALID_REQUEST");
                }

                out.println("END_OF_DATA"); // Signalizuoja duomenų pabaigą
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
