package lt.viko.eif.s.dieliautas.Race.database;

import javax.swing.*;
import java.awt.*;

/**
 * Klasė, atsakinga už duomenų bazės meniu rodymą grafinėje sąsajoje.
 */
public class DatabaseMenuGUI extends JFrame {

    private JTextArea responseArea;

    /**
     * Konstruktorius, nustatantis duomenų bazės meniu GUI.
     */
    public DatabaseMenuGUI() {
        setTitle("Database Menu");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mygtukai
        JButton viewAllRacesButton = new JButton("View All Races");
        JButton viewAllRaceInfoButton = new JButton("View All RaceInfo");
        JButton viewAllRacersButton = new JButton("View All Racers");
        JButton viewEntireDatabaseButton = new JButton("Print Entire Database");
        JButton backButton = new JButton("Back to Main Menu");

        // Mygtukų veiksmų priskyrimas
        viewAllRacesButton.addActionListener(e -> displayData(DatabaseOperations::viewAllRaces));
        viewAllRaceInfoButton.addActionListener(e -> displayData(DatabaseOperations::viewAllRaceInfo));
        viewAllRacersButton.addActionListener(e -> displayData(DatabaseOperations::viewAllRacers));
        viewEntireDatabaseButton.addActionListener(e -> displayData(DatabaseOperations::viewEntireDatabase));
        backButton.addActionListener(e -> dispose());

        // Mygtukų išdėstymas
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(viewAllRacesButton, gbc);
        gbc.gridy++;
        panel.add(viewAllRaceInfoButton, gbc);
        gbc.gridy++;
        panel.add(viewAllRacersButton, gbc);
        gbc.gridy++;
        panel.add(viewEntireDatabaseButton, gbc);
        gbc.gridy++;
        panel.add(backButton, gbc);

        // Teksto sritis atsakymams rodyti
        responseArea = new JTextArea(20, 40);
        responseArea.setEditable(true);  // Leidžia kopijuoti tekstą
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(responseArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        add(panel);
        setResizable(true);
    }

    /**
     * Metodas, vykdantis duomenų užklausas ir rodo rezultatus teksto srityje.
     * @param dataSupplier funkcionalinė sąsaja, grąžinanti duomenis
     */
    private void displayData(DataSupplier dataSupplier) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                return dataSupplier.getData();
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

    /**
     * Funkcinė sąsaja duomenų tiekimui.
     */
    @FunctionalInterface
    interface DataSupplier {
        String getData();
    }
}
