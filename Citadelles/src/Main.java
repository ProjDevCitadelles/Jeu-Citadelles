import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenue dans le jeu Citadelles!");

        // Initialisation du plateau
        Plateau plateau = new Plateau();

        // Initialisation des joueurs
        System.out.print("Combien de joueurs participent? ");
        int nbJoueurs = scanner.nextInt();
        for (int i = 0; i < nbJoueurs; i++) {
            Joueur joueur = new Joueur();
            System.out.println("Joueur " + (i + 1) + " a été créé.");
            // Vous pouvez ajouter des étapes supplémentaires pour configurer chaque joueur ici
        }

        // Chargement des cartes
        Pioche pioche = new Pioche();

        // Initialisation des personnages et du roi
        Roi roi = new Roi();
        Personnage[] personnages = {
                // Initialiser les personnages spécifiques ici
        };

        // Boucle de jeu principale
        boolean jeuEnCours = true;
        while (jeuEnCours) {
            // Logique de jeu ici

            // Pour le moment, nous terminons simplement le jeu
            jeuEnCours = false;
        }

        System.out.println("Merci d'avoir joué!");
    }
}
