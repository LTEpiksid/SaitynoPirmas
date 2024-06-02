package lt.viko.eif.s.dieliautas.Race.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Ryšio valdymo klasė, atsakinga už ryšio su serveriu nustatymą ir valdymą.
 */
public class ConnectionManager {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;
    private Socket socket;

    /**
     * Metodas, nustatantis ryšį su serveriu.
     * @throws IOException jei nepavyksta nustatyti ryšio.
     */
    public void connect() throws IOException {
        socket = new Socket(SERVER_HOST, SERVER_PORT);
    }

    /**
     * Grąžina esamą ryšio lizdą.
     * @return ryšio lizdas
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Patikrina, ar ryšys su serveriu yra nustatytas.
     * @return true, jei ryšys yra nustatytas, kitaip false
     */
    public boolean isConnected() {
        return socket != null && !socket.isClosed();
    }

    /**
     * Metodas, nutraukiantis ryšį su serveriu.
     * @throws IOException jei nepavyksta nutraukti ryšio.
     */
    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
