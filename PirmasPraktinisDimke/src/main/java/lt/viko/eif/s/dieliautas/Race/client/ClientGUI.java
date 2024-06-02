package lt.viko.eif.s.dieliautas.Race.client;

import javax.swing.*;
import java.awt.*;

/**
 * Klientų GUI klasė, atsakinga už klientų sąsają ir ryšio su serveriu valdymą.
 */
public class ClientGUI extends JFrame {

    private final ConnectionManager connectionManager;
    private final ClientRequestSender requestSender;
    private JTextArea responseArea;

    /**
     * Klientų GUI konstruktorius, nustatantis sąsają ir pradinį ryšį.
     */
    public ClientGUI() {
        connectionManager = new ConnectionManager();
        responseArea = new JTextArea(20, 40);
        requestSender = new ClientRequestSender(connectionManager, responseArea);

        setTitle("Client Menu");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mygtukai
        JButton loadRaceButton = new JButton("Load Race");
        JButton loadRaceInfoButton = new JButton("Load RaceInfo");
        JButton loadRacerButton = new JButton("Load Racer");
        JButton loadStatusButton = new JButton("Load Status");
        JButton loadEntireDatabaseButton = new JButton("Load Entire Database");
        JButton loadXMLButton = new JButton("Load XML");
        JButton loadXMLToObjectButton = new JButton("Load XML to Object");
        JButton backButton = new JButton("Back to Main Menu");

        // Mygtukų veiksmų priskyrimas
        loadRaceButton.addActionListener(e -> requestSender.sendRequest("LOAD_RACE"));
        loadRaceInfoButton.addActionListener(e -> requestSender.sendRequest("LOAD_RACEINFO"));
        loadRacerButton.addActionListener(e -> requestSender.sendRequest("LOAD_RACER"));
        loadStatusButton.addActionListener(e -> requestSender.sendRequest("LOAD_STATUS"));
        loadEntireDatabaseButton.addActionListener(e -> requestSender.sendRequest("LOAD_ENTIRE_DATABASE"));
        loadXMLButton.addActionListener(e -> requestSender.sendRequest("LOAD_XML"));
        loadXMLToObjectButton.addActionListener(e -> requestSender.sendRequest("LOAD_XML_TO_OBJECT"));
        backButton.addActionListener(e -> dispose());

        // Mygtukų išdėstymas
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loadRaceButton, gbc);
        gbc.gridy++;
        panel.add(loadRaceInfoButton, gbc);
        gbc.gridy++;
        panel.add(loadRacerButton, gbc);
        gbc.gridy++;
        panel.add(loadStatusButton, gbc);
        gbc.gridy++;
        panel.add(loadEntireDatabaseButton, gbc);
        gbc.gridy++;
        panel.add(loadXMLButton, gbc);
        gbc.gridy++;
        panel.add(loadXMLToObjectButton, gbc);
        gbc.gridy++;
        panel.add(backButton, gbc);

        // Teksto sritis atsakymams rodyti
        responseArea.setEditable(true);  // Leidžia kopijuoti tekstą
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(responseArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        add(panel);
        setResizable(true);
    }

    /**
     * Pagrindinis metodas, paleidžiantis klientų GUI.
     * @param args programos argumentai
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI().setVisible(true));
    }
}
