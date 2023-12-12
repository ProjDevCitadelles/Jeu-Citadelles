package application;

import controleur.Interaction;
import vue.*;

public class Application {
    public static void main(String[] args) {
        // Création du modèle (et chargement des données si nécessaire)
        Jeu jeu = new Jeu(); // Supposant que Jeu est votre classe modèle principale
        // Création de la vue
        CitadellesUI citadellesUI = new CitadellesUI();

        // Création du contrôleur et liaison avec la vue et le modèle
        Interaction interaction = new Interaction(jeu, citadellesUI);

        // Démarrage de l'interface utilisateur
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                citadellesUI.createAndShowGUI();
            }
        });
    }
}
