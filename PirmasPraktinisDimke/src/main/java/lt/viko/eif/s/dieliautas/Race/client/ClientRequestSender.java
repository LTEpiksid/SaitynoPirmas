package lt.viko.eif.s.dieliautas.Race.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klientų užklausų siuntėjas, atsakingas už užklausų siuntimą ir atsakymų gavimą iš serverio.
 */
public class ClientRequestSender {
    private final ConnectionManager connectionManager;
    private final JTextArea responseArea;

    /**
     * Konstruktorius, nustatantis ryšio valdymo ir atsakymų rodymo sritį.
     * @param connectionManager ryšio valdymo klasės egzempliorius
     * @param responseArea atsakymų rodymo sritis
     */
    public ClientRequestSender(ConnectionManager connectionManager, JTextArea responseArea) {
        this.connectionManager = connectionManager;
        this.responseArea = responseArea;
    }

    /**
     * Metodas, siunčiantis užklausą serveriui.
     * @param request užklausos tekstas
     */
    public void sendRequest(String request) {
        if (!connectionManager.isConnected()) {
            try {
                connectionManager.connect();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the server.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                Socket socket = connectionManager.getSocket();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(request);
                String response;
                StringBuilder responseBuilder = new StringBuilder();
                while ((response = in.readLine()) != null) {
                    if (response.equals("END_OF_DATA")) {
                        break;
                    }
                    responseBuilder.append(response).append("\n");
                }
                return responseBuilder.toString();
            }

            @Override
            protected void done() {
                try {
                    responseArea.setText(get());
                } catch (Exception e) {
                    e.printStackTrace();
                    responseArea.setText("Error: " + e.getMessage());
                }
            }
        }.execute();
    }
}
