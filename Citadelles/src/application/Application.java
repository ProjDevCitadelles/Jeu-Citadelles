package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Citadelles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Centre la fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajoute une marge autour du panel

        JLabel welcomeLabel = new JLabel("Bienvenue dans Citadelles", SwingConstants.CENTER);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Change la police et la taille
        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Ajoute de l'espace entre les composants

        JButton playButton = createButton("Jouer une nouvelle partie");
        JButton rulesButton = createButton("Afficher les r\u00E8gles du jeu");
        JButton quitButton = createButton("Quitter l'application");

        playButton.addActionListener(e -> {
            String nomJoueur = JOptionPane.showInputDialog(frame, "Entrez votre nom :");
            Configuration.setNomJoueurHumain(nomJoueur);
            Jeu jeu = new Jeu();
            jeu.jouer();
        });

        rulesButton.addActionListener(e -> {
            Jeu jeu = new Jeu();
            jeu.afficherLesRegles();
        });

        quitButton.addActionListener(e -> System.exit(0));

        panel.add(playButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajoute de l'espace entre les boutons
        panel.add(rulesButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajoute de l'espace entre les boutons
        panel.add(quitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.PLAIN, 16)); // Change la police et la taille
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
        return button;
    }
}
