package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vue.*;

public class Application {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> createAndShowGUI());

    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Citadelles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centre la fenêtre sur l'écran

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajoute des marges

        JLabel welcomeLabel = new JLabel("Bienvenue dans Citadelles");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20)); // Modifie la police et la taille du texte
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centre le texte horizontalement

        JButton playButton = new JButton("Jouer une nouvelle partie");
        JButton rulesButton = new JButton("Afficher les r\u00E8gles du jeu");
        JButton quitButton = new JButton("Quitter l'application");

        // Ajoute un espacement entre les boutons
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Style des boutons
        Font buttonFont = new Font("SansSerif", Font.PLAIN, 16);
        playButton.setFont(buttonFont);
        rulesButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);

        // Centre les boutons horizontalement
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomJoueur = JOptionPane.showInputDialog(frame, "Entrez votre nom :");
                Configuration.setNomJoueurHumain(nomJoueur);
                Jeu jeu = new Jeu();
                jeu.jouerPartie();
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Jeu jeu = new Jeu();
                jeu.afficherLesRegles();          }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Ajoute un espacement
        panel.add(playButton);
        panel.add(rulesButton);
        panel.add(quitButton);

        frame.add(panel);
        frame.setVisible(true);

    }
}
