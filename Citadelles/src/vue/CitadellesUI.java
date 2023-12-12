package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CitadellesUI extends JFrame {

    private JPanel gameBoardPanel;
    private JPanel settingsPanel;
    private JPanel playerStatsPanel;
    private JLabel statsLabel;

    public CitadellesUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Citadelles ");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeGameBoardPanel();
        initializeSettingsPanel();
        initialiserPanelStatsJoueur();

        // Adding to the main frame
        add(gameBoardPanel, BorderLayout.CENTER);
        add(settingsPanel, BorderLayout.EAST);
        add(playerStatsPanel, BorderLayout.WEST);

        setVisible(true);
    }

    private void initializeGameBoardPanel() {
        gameBoardPanel = new JPanel();
        gameBoardPanel.setBackground(new Color(218, 133, 6)); // Vert foncé pour l'esthétique
        gameBoardPanel.setLayout(new BorderLayout()); // Utiliser BorderLayout pour les images dans les coins
        gameBoardPanel.setPreferredSize(new Dimension(400, 600)); // Simuler la zone du plateau de jeu

        // Créer des panneaux d'angle
        JPanel topLeftPanel = new JPanel(new BorderLayout());
        JPanel topRightPanel = new JPanel(new BorderLayout());
        JPanel bottomLeftPanel = new JPanel(new BorderLayout());
        JPanel bottomRightPanel = new JPanel(new BorderLayout());

        JLabel player1 = createPlayerLabel("src/img/p1.png", 60, 60);
        JLabel player2 = createPlayerLabel("src/img/p2.png", 60, 60);
        JLabel player3 = createPlayerLabel("src/img/p3.png", 60, 60);
        JLabel player4 = createPlayerLabel("src/img/p4.png", 60, 60);

        // Ajouter les labels aux panneaux d'angle
        topLeftPanel.add(player1, BorderLayout.WEST);
        topRightPanel.add(player2, BorderLayout.EAST);
        bottomLeftPanel.add(player3, BorderLayout.WEST);
        bottomRightPanel.add(player4, BorderLayout.EAST);

        // Créer des panneaux de bordure
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Ajouter les panneaux d'angle aux panneaux de bordure
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRightPanel, BorderLayout.EAST);
        bottomPanel.add(bottomLeftPanel, BorderLayout.WEST);
        bottomPanel.add(bottomRightPanel, BorderLayout.EAST);

        // Ajouter les panneaux de bordure au gameBoardPanel
        gameBoardPanel.add(topPanel, BorderLayout.NORTH);
        gameBoardPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    // Helper method to create a player label with a scaled image
    private JLabel createPlayerLabel(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel playerLabel = new JLabel(new ImageIcon(scaledImage));
        playerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // Ensure the label size matches the image size
        playerLabel.setPreferredSize(new Dimension(width, height));
        return playerLabel;
    }



    private void initializeSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ImageIcon originalIcon = new ImageIcon("src/img/settings.png");
        Image originalImage = originalIcon.getImage();
        int newWidth = 40;
        int newHeight = 40;
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton settingsButton = new JButton(scaledIcon);
        settingsButton.setPreferredSize(new Dimension(newWidth, newHeight)); // Optionally set the preferred size of the button
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySettingsMenu();
            }
        });

        settingsPanel.add(settingsButton);

    }
    private void displaySettingsMenu() {
        // Crée une nouvelle instance de SettingsPanel
        vue.SettingsPanel settingsPanel = new vue.SettingsPanel();

        // Crée un nouveau JFrame ou JDialog pour afficher le SettingsPanel
        JFrame frame = new JFrame("Param\u00E8tres");
        frame.setContentPane(settingsPanel);
        frame.setSize(400, 400); // Définissez la taille selon vos besoins
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pour fermer uniquement cette fenêtre sans arrêter l'application
        frame.setVisible(true);
    }
    private JLabel labelPoints;
    private int pointsJoueur = 0; // Ce compteur gardera la trace des points du joueur

    private void initialiserPanelStatsJoueur() {
        playerStatsPanel = new JPanel();
        playerStatsPanel.setLayout(new BoxLayout(playerStatsPanel, BoxLayout.Y_AXIS)); // Utiliser BoxLayout pour l'empilement vertical
        playerStatsPanel.setBackground(Color.WHITE);
        playerStatsPanel.setPreferredSize(new Dimension(200, 600));

        statsLabel = new JLabel("Vos Stats de la partie");
        statsLabel.setHorizontalAlignment(JLabel.CENTER);
        playerStatsPanel.add(statsLabel);

        // Compteur de points
        labelPoints = new JLabel("Points : " + pointsJoueur);
        labelPoints.setAlignmentX(Component.CENTER_ALIGNMENT); // Aligner le label au centre
        playerStatsPanel.add(labelPoints);

        // Panneau pour les boutons
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // FlowLayout avec un écart horizontal de 10 et un écart vertical de 10
        JButton boutonPrendreDeuxPieces = new JButton("Prendre 2 pi\u00E8ces");
        JButton boutonPrendreUneCarte = new JButton("Prendre une carte");

        // Ajouter les boutons au panneau des boutons
        panelBoutons.add(boutonPrendreDeuxPieces);
        panelBoutons.add(boutonPrendreUneCarte);

        // Ajouter le panneau des boutons au panel des statistiques
        playerStatsPanel.add(panelBoutons);
    }

    private void prendreDeuxPieces() {
        pointsJoueur += 2;
        mettreAJourAffichagePoints();
    }

    private void prendreUneCarte() {
        pointsJoueur += 1;
        mettreAJourAffichagePoints();
    }

    private void mettreAJourAffichagePoints() {
        labelPoints.setText("Points : " + pointsJoueur);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(CitadellesUI::new);
    }
}

