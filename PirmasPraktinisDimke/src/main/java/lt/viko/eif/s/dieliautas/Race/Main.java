package lt.viko.eif.s.dieliautas.Race;

import lt.viko.eif.s.dieliautas.Race.model.Race;
import lt.viko.eif.s.dieliautas.Race.util.JAXBTransformer;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.*;

/**
 * Klasė, skirta serverio ir kliento komunikacijai su XML failais.
 */
public class Main {
    /**
     * Pagrindinis metodas paleidžiantis serverį ir klientą.
     * @param args argumentai
     */
    public static void main(String[] args) {
        try {
            // Serveris
            new Thread(() -> {
                try (ServerSocket serverSocket = new ServerSocket(8080)) {
                    while (true) {
                        try (Socket clientSocket = serverSocket.accept();
                             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                            StringBuilder xmlBuilder = new StringBuilder();
                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                xmlBuilder.append(inputLine);
                            }
                            String xml = xmlBuilder.toString();
                            Race race = JAXBTransformer.transformToPOJO(xml, Race.class);
                            System.out.println("Received race data: " + race);
                        }
                    }
                } catch (IOException | JAXBException e) {
                    e.printStackTrace();
                }
            }).start();

            // Klientas
            Thread.sleep(2000); // Užtikrina, kad serveris paleidžiamas pirmiau
            try (Socket socket = new Socket("localhost", 8080);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                File file = new File("path/to/races.xml");
                BufferedReader fileReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
