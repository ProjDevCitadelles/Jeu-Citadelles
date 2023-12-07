package application;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        // Le scanner devrait être fermé après son utilisation pour éviter les fuites de ressources
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre nom : ");
            String nomJoueur = scanner.nextLine();
            // Assurez-vous que la méthode setNomJoueurHumain est publique et statique dans la classe Configuration
            Configuration.setNomJoueurHumain(nomJoueur);
            Jeu jeu = new Jeu(); // Création d'une instance du jeu
            jeu.jouer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Le scanner est automatiquement fermé ici grâce au try-with-resources
    }
}
