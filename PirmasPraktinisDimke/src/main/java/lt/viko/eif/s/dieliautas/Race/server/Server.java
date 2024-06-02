package lt.viko.eif.s.dieliautas.Race.server;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server klasė, atsakinga už serverio paleidimą ir klientų ryšio priėmimą.
 */
public class Server {
    private static final int PORT = 8080;

    /**
     * Paleidžia serverį naujame siūle.
     */
    public static void start() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Server started on port " + PORT, "Server Status", JOptionPane.INFORMATION_MESSAGE));
                System.out.println("Server started on port " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new ClientHandler(clientSocket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
