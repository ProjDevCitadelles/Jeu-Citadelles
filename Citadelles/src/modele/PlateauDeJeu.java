package modele;

import java.util.ArrayList;

public class PlateauDeJeu {
    private Personnage[] listePersonnages = new Personnage[9]; // Comme il y a 8 ou 9 personnages
    private Joueur[] listeJoueurs = new Joueur[9]; // Pour un maximum de 9 joueurs
    //private Pioche pioche = new Pioche(); // Supposons que vous avez une classe Pioche
    private int nombrePersonnages;
    private int nombreJoueurs;

    // Constructeur sans paramètre
    public PlateauDeJeu() {
        // Initialisation des attributs si nécessaire
        nombrePersonnages = 0;
        nombreJoueurs = 0;
    }

    // Accesseurs en lecture
    public int getNombrePersonnages() {
        return nombrePersonnages;
    }

    public int getNombreJoueurs() {
        return nombreJoueurs;
    }

   // public Pioche getPioche() {
    //    return pioche;
   // }

    public Personnage getPersonnage(int i) {
        if (i >= 0 && i < nombrePersonnages) {
            return listePersonnages[i];
        }
        return null;
    }

    public Joueur getJoueur(int i) {
        if (i >= 0 && i < nombreJoueurs) {
            return listeJoueurs[i];
        }
        return null;
    }

    // Méthodes pour ajouter des éléments au plateau
    public void ajouterPersonnage(Personnage nouveau) {
        if (nouveau != null && nombrePersonnages < listePersonnages.length) {
            listePersonnages[nombrePersonnages++] = nouveau;
            nouveau.setPlateau(this); // Associe le plateau au personnage
        }
    }

    public void ajouterJoueur(Joueur nouveau) {
        if (nouveau != null && nombreJoueurs < listeJoueurs.length) {
            listeJoueurs[nombreJoueurs++] = nouveau;
        }
    }

    // Vous pouvez ajouter ici d'autres méthodes nécessaires pour la logique de votre jeu
}
