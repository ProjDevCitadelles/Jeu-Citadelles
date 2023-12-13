package vue;

import application.*;
// import modele.JeuObservateur;
import modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;

public class CitadellesUI extends JFrame {

    private JPanel gameBoardPanel;
    private JPanel settingsPanel;
    private JPanel playerStatsPanel;
    private JLabel statsLabel;
    private JTextArea messageArea;
    private JTextArea gameStateUpdates;
    private JLabel labelPoints;
    private int pointsJoueur = 0;
    private JButton takeActionButton;

    public CitadellesUI() {
        initializeMessageArea();
        setUpGameUpdateStream();
    }

    private void setUpGameUpdateStream() {
        gameStateUpdates = new JTextArea();
        gameStateUpdates.setEditable(false); // Ensure it's not editable
        // ... (Any additional setup for gameStateUpdates like adding it to a JScrollPane, etc.)

        // Redirect standard output stream to the text area
        OutputStream textAreaOutputStream = new OutputStream() {
            @Override
            public void write(int b) {
                // Append the character to the JTextArea
                gameStateUpdates.append(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) {
                // Append the string to the JTextArea
                gameStateUpdates.append(new String(b, off, len));
            }
        };

        // Set the custom output stream to System.out
        PrintStream printStream = new PrintStream(textAreaOutputStream);
        System.setOut(printStream);
        System.setErr(printStream); // You can also redirect the error stream if needed
    }
    private void initializeMessageArea() {
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false); // Assurez-vous que l'utilisateur ne peut pas éditer ce texte
        // Ajouter le messageArea à un JScrollPane si vous attendez beaucoup de messages
        this.add(new JScrollPane(messageArea), BorderLayout.SOUTH);
    }

    public void afficherMessage(String message) {
        messageArea.append(message + "\n");
    }
        public void createAndShowGUI() {
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
                String nomJoueur = JOptionPane.showInputDialog(this, "Entrez votre nom :");
                if (nomJoueur != null && !nomJoueur.trim().isEmpty()) {
                    Configuration.setNomJoueurHumain(nomJoueur.trim());
                    Jeu jeu = new Jeu();
                    jeu.initialisation();
                    jeu.getPlateauDeJeu().getJoueur(3).setNom(nomJoueur.trim());
                    initializeUI();
                    // Run the game loop in a SwingWorker
                    SwingWorker<Void, Void> gameWorker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            jeu.jouerPartie();
                            return null;
                        }
                        @Override
                        protected void done() {
                            // This method is called when the background
                            // thread is finished without interrupting the EDT
                            jeu.calculDesPoints();
                            // You might also want to update the UI here to reflect the end of the game
                        }
                    };
                    gameWorker.execute();
                } else {
                    // Display an error message if the name is invalid
                    JOptionPane.showMessageDialog(this, "Vous devez entrer un nom pour jouer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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
        gameBoardPanel.setBackground(new Color(220, 206, 206));
        gameBoardPanel.setLayout(new BorderLayout()); // Utiliser BorderLayout pour les images dans les coins
        gameBoardPanel.setPreferredSize(new Dimension(300, 600)); // Simuler la zone du plateau de jeu

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


    private void initialiserPanelStatsJoueur() {
        playerStatsPanel = new JPanel();
        playerStatsPanel.setLayout(new BoxLayout(playerStatsPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        playerStatsPanel.setBackground(Color.WHITE);
        playerStatsPanel.setPreferredSize(new Dimension(200, 600));

        statsLabel = new JLabel("Vos Stats");
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
        // Game state updates
        JTextArea gameStateUpdates = new JTextArea(8, 20); // Adjust the rows and columns to fit your UI
        gameStateUpdates.setEditable(false);
        JScrollPane gameStateScrollPane = new JScrollPane(gameStateUpdates);
        gameStateScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerStatsPanel.add(gameStateScrollPane);

        // User entry field
        JTextField userEntryTextField = new JTextField(15); // Adjust the width as needed
        userEntryTextField.setMaximumSize(userEntryTextField.getPreferredSize()); // Ensure the text field does not grow beyond its preferred size
        playerStatsPanel.add(userEntryTextField);

        // Submit button for user entries
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerStatsPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the user entry text field
                String userText = userEntryTextField.getText();

                // Check if the userText is not empty
                if (!userText.trim().isEmpty()) {
                    // Append the user text to the chat box (gameStateUpdates JTextArea)
                    gameStateUpdates.append(userText + "\n"); // Add a newline character to separate entries

                    // Clear the user entry text field for the next entry
                    userEntryTextField.setText("");
                }
            }
        });

        // Ensure the player stats panel components do not stretch horizontally
        for (Component comp : playerStatsPanel.getComponents()) {
            ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
        }
    }
/*
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
    public void afficherMainJoueur(Joueur joueur) {
        String mainTexte = "Voici votre Main :\n";
        for (Quartier quartier : joueur.getMain()) {
            mainTexte += quartier.getNom() + " - type : " + quartier.getType() + " - pièces : " + quartier.getCout() + "\n";
        }
        mettreAJourZoneTexte(mainTexte); // mettreAJourZoneTexte est une méthode hypothétique que vous devriez créer
    }

    public void afficherCiteJoueur(Joueur joueur) {
        String citeTexte = "Voici votre Cité :\n";
        if (joueur.nbQuartiersDansCite() > 0) {
            for (Quartier quartier : joueur.getCite()) {
                citeTexte += quartier.getNom() + " - type : " + quartier.getType() + " - pièces : " + quartier.getCout() + "\n";
            }
        } else {
            citeTexte += "Votre cité est vide !\n";
        }
        mettreAJourZoneTexte(citeTexte); // mettreAJourZoneTexte est une méthode hypothétique que vous devriez créer
    }
    public void mettreAJourZoneTexte(String message) {
        SwingUtilities.invokeLater(() -> {
            messageArea.append(message + "\n"); // Ajouter le message à la zone de texte
            messageArea.setCaretPosition(messageArea.getDocument().getLength()); // Auto-scroll vers le bas
        });
    }
    */
        public static void main(String[] args) {

        SwingUtilities.invokeLater(CitadellesUI::new);
    }

}

