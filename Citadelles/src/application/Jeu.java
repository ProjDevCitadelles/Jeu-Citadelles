
package application;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Quartier;

public class Jeu {

    private JFrame frame;
    private JTextArea gameInfoArea;
    private Random generateur;
    private PlateauDeJeu plateauDeJeu;

    public Jeu() {
        this.plateauDeJeu = new PlateauDeJeu();
        this.generateur = new Random();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Citadelles Jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        gameInfoArea = new JTextArea();
        gameInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameInfoArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton playButton = new JButton("Jouer une nouvelle partie");
        playButton.addActionListener(e -> jouerPartie());
        JButton rulesButton = new JButton("Afficher les règles du jeu");
        rulesButton.addActionListener(e -> afficherLesRegles());
        JButton quitButton = new JButton("Quitter l'application");
        quitButton.addActionListener(e -> quitterPartie());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(quitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    protected void afficherLesRegles() {
        gameInfoArea.append("Règles du jeu de Citadelles:\n\n[Insérer les règles ici]\n\n");
    }

    protected void jouerPartie() {
        // Logic to start a new game
        // This should include all the steps required to setup and play the game
        // ...
    }

    private void quitterPartie() {
        gameInfoArea.append("À bientôt dans Citadelles !\n");
        System.exit(0);
    }

    // Other methods for game logic
    // ...
}
