package application;
import java.util.Scanner;
/**
 * @author Laaouane Adam
 * @author Thizon Matéo
 * @author Poultier Victor
 * @author Reulier Titouan
 * @version 12 dec. 2023
 */
public class Application {

    public static void main(String[] args) {
// Le scanner devrait être fermé après son utilisation pour éviter les fuites de ressources
        try (Scanner scanner = new Scanner(System.in)) {
            Jeu jeu = new Jeu(); // Création d'une instance du jeu
            jeu.jouer();
            System.out.print("Entrez votre nom : ");
            String nomJoueur = scanner.nextLine();
            // Assurez-vous que la méthode setNomJoueurHumain est publique et statique dans la classe Configuration
            Configuration.setNomJoueurHumain(nomJoueur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Le scanner est automatiquement fermé ici grâce au try-with-resources
    }
}
