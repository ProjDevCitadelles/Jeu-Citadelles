package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel {
    // Déclaration des composants de l'interface
    private JSlider volumeSlider;
    private JComboBox<String> resolutionComboBox;
    private JCheckBox fullscreenCheckbox;
    private JButton saveButton;
    private JButton cancelButton;

    public SettingsPanel() {
        // Initialisation et configuration des composants
        setLayout(new GridLayout(0, 1)); // Layout en grille pour un agencement simple

        // Volume Slider
        volumeSlider = new JSlider(0, 100);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        add(new JLabel("Volume:"));
        add(volumeSlider);

        // Résolution ComboBox
        String[] resolutions = {"1024x768", "1280x720", "1920x1080"};
        resolutionComboBox = new JComboBox<>(resolutions);
        add(new JLabel("R\u00E9solution:"));
        add(resolutionComboBox);

        // Fullscreen Checkbox
        fullscreenCheckbox = new JCheckBox("Plein \u00E9cran");
        add(fullscreenCheckbox);

        // Boutons
        saveButton = new JButton("Sauvegarder");
        cancelButton = new JButton("Annuler");

        // Ajout des écouteurs d'événements aux boutons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSettings();
            }
        });

        add(saveButton);
        add(cancelButton);
    }

    private void saveSettings() {
        // Logique pour sauvegarder les paramètres
        final JOptionPane optionPane = new JOptionPane("Paramètres sauvegardés", JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog("Confirmation");
        // Crée un Timer pour fermer le dialogue après 5 secondes
        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // S'assurer que le Timer ne se répète pas
        timer.start();

        dialog.setVisible(true);

        // Exemple de logique  : Récupérer la valeur du slider, de la combobox, etc.
    }

    private void cancelSettings() {
        // Logique pour annuler les modifications
        final JOptionPane optionPane = new JOptionPane("Modifications annulées", JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = optionPane.createDialog("Confirmation");

        // Crée un Timer pour fermer le dialogue après 5 secondes
        Timer timer = new Timer(5000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // S'assurer que le Timer ne se répète pas
        timer.start();

        dialog.setVisible(true);
    }
}
