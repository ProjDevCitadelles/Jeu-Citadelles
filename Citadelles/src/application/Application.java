package application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CitadElles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Bienvenue dans CitadElles");
        panel.add(welcomeLabel);

        JButton playButton = new JButton("Jouer une nouvelle partie");
        JButton rulesButton = new JButton("Afficher les règles du jeu");
        JButton quitButton = new JButton("Quitter l'application");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomJoueur = JOptionPane.showInputDialog(frame, "Entrez votre nom :");
                Configuration.setNomJoueurHumain(nomJoueur);
                Jeu jeu = new Jeu();
                jeu.jouer();
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(playButton);
        panel.add(rulesButton);
        panel.add(quitButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
