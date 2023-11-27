import java.util.Scanner;
import modele.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Citadelles!");

        System.out.print("Combien de joueurs participent? ");
        int nbJoueurs = scanner.nextInt();
        scanner.nextLine(); // Consommez la nouvelle ligne restante

        // Création des joueurs
        Joueur[] joueurs = new Joueur[nbJoueurs];
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.print("Entrez le nom pour le Joueur " + (i + 1) + ": ");
            String nomJoueur = scanner.nextLine();
            joueurs[i] = new Joueur(nomJoueur);
            System.out.println("Joueur " + nomJoueur + " a été créé.");
        }

        // Initialisation des personnages disponibles
        Personnage[] personnagesDisponibles = {
                new Personnage("Roi", 1, new String[]{"Noble", "Couronne"}),
                new Personnage("Assassin", 2, new String[]{"Secret", "Mortel"}),
                new Personnage("Voleur", 3, new String[]{"Rapide", "Furtif"}),
        };

        // Chaque joueur choisit un personnage
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getNom() + ", choisissez un personnage par son numéro:");
            for (int i = 0; i < personnagesDisponibles.length; i++) {
                Personnage p = personnagesDisponibles[i];
                System.out.println((i + 1) + ". " + p.getNom() + " - " + String.join(", ", p.getCaracteristiques()));
            }
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommez la nouvelle ligne restante

            // Vérifiez si le choix est valide et si le personnage n'est pas déjà pris
            if (choix > 0 && choix <= personnagesDisponibles.length) {
                Personnage personnageChoisi = personnagesDisponibles[choix - 1];
                // Vous pouvez ajouter une vérification pour s'assurer que le personnage n'est pas déjà pris
                joueur.setPersonnage(personnageChoisi);
                personnageChoisi.setJoueur(joueur);
                System.out.println(joueur.getNom() + " a choisi " + personnageChoisi.getNom());
            } else {
                System.out.println("Choix invalide, veuillez réessayer.");
                // Ajoutez ici une logique pour gérer un choix invalide
                // Par exemple, demandez au joueur de faire un nouveau choix
            }
        }

        // Boucle de jeu principale
        boolean jeuEnCours = true;
        while (jeuEnCours) {
            // Logique de jeu ici

            // Pour le moment, nous terminons simplement le jeu
            jeuEnCours = false;
        }

        System.out.println("Merci d'avoir joué!");
        scanner.close();
    }
}
