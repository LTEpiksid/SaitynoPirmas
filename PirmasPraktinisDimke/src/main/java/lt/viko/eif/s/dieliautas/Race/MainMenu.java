package lt.viko.eif.s.dieliautas.Race;

import lt.viko.eif.s.dieliautas.Race.client.ClientGUI;
import lt.viko.eif.s.dieliautas.Race.database.DatabaseMenuGUI;
import lt.viko.eif.s.dieliautas.Race.database.DatabaseSetup;

import javax.swing.*;
import java.awt.*;

/**
 * Pagrindinis meniu langas su galimybėmis paleisti serverį, klientą, nustatyti duomenų bazę ir parodyti duomenų bazės meniu.
 */
public class MainMenu extends JFrame {

    /**
     * Sukuria pagrindinio meniu langą.
     */
    public MainMenu() {
        setTitle("Main Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Įkelia paveikslėlį
        ImageIcon icon = new ImageIcon(getClass().getResource("/Logo.png"));
        JLabel imageLabel = new JLabel(icon);

        // Sukuria mygtukus
        JButton startServerButton = new JButton("Start Server");
        JButton startClientButton = new JButton("Start Client");
        JButton setupDatabaseButton = new JButton("Setup Database");
        JButton showDatabaseMenuButton = new JButton("Show Database Menu");
        JButton quitButton = new JButton("Quit");

        // Prideda veiksmų klausytojus mygtukams
        startServerButton.addActionListener(e -> startServer());
        startClientButton.addActionListener(e -> startClient());
        setupDatabaseButton.addActionListener(e -> setupDatabase());
        showDatabaseMenuButton.addActionListener(e -> showDatabaseMenu());
        quitButton.addActionListener(e -> System.exit(0));

        // Prideda komponentus prie panelės
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(imageLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(startServerButton, gbc);
        gbc.gridy++;
        panel.add(startClientButton, gbc);
        gbc.gridy++;
        panel.add(setupDatabaseButton, gbc);
        gbc.gridy++;
        panel.add(showDatabaseMenuButton, gbc);
        gbc.gridy++;
        panel.add(quitButton, gbc);

        add(panel);
    }

    /**
     * Paleidžia serverį ir parodo pranešimą.
     */
    private void startServer() {
        lt.viko.eif.s.dieliautas.Race.server.Server.start();
        JOptionPane.showMessageDialog(this, "Server started on port 8080", "Server Status", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Paleidžia klientą.
     */
    private void startClient() {
        SwingUtilities.invokeLater(() -> new ClientGUI().setVisible(true));
    }

    /**
     * Nustato duomenų bazę ir parodo pranešimą.
     */
    private void setupDatabase() {
        DatabaseSetup.setup();
        JOptionPane.showMessageDialog(this, "Database setup completed", "Database Status", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Parodo duomenų bazės meniu.
     */
    private void showDatabaseMenu() {
        SwingUtilities.invokeLater(() -> new DatabaseMenuGUI().setVisible(true));
    }

    /**
     * Pagrindinis metodas paleidžiantis pagrindinį meniu.
     * @param args argumentai
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
