package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {
    private String nom;
    private int pieces;
    private ArrayList<Quartier> main;
    private Quartier[] cite;
    private boolean possedeCouronne;
    private Personnage personnage;

    // Constructeur
    public Joueur(String nom) {
        this.nom = nom;
        this.pieces = 0;
        this.main = new ArrayList<>();
        this.cite = new Quartier[8]; // Tableau initialisé avec une taille maximale de 8
        this.possedeCouronne = false;
    }

    // Accesseurs en lecture
    public String getNom() {
        return nom;
    }

    public int getNbPieces() {
        return pieces;
    }

    public int getNbQuartiersDansCite() {
        int count = 0;
        for (Quartier quartier : cite) {
            if (quartier != null) {
                count++;
            }
        }
        return count;
    }

    public Quartier[] getCite() {
        return cite;
    }

    public ArrayList<Quartier> getMain() {
        return main;
    }

    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    // Accesseur en écriture
    public void setPossedeCouronne(boolean possedeCouronne) {
        this.possedeCouronne = possedeCouronne;
    }

    // Méthodes pour gérer les pièces
    public void ajouterPieces(int nbPieces) {
        if (nbPieces > 0) {
            this.pieces += nbPieces;
        }
    }

    public void retirerPieces(int nbPieces) {
        if (nbPieces > 0 && this.pieces >= nbPieces) {
            this.pieces -= nbPieces;
        }
    }

    // Méthodes pour gérer les quartiers
    public void ajouterQuartierDansCite(Quartier quartier) {
        for (int i = 0; i < cite.length; i++) {
            if (cite[i] == null) {
                cite[i] = quartier;
                return;
            }
        }
    }

    public boolean quartierPresentDansCite(String nomQuartier) {
        for (Quartier quartier : cite) {
            if (quartier != null && quartier.getNom().equals(nomQuartier)) {
                return true;
            }
        }
        return false;
    }

    public Quartier retirerQuartierDansCite(String nomQuartier) {
        for (int i = 0; i < cite.length; i++) {
            if (cite[i] != null && cite[i].getNom().equals(nomQuartier)) {
                Quartier quartierRetire = cite[i];
                cite[i] = null;
                return quartierRetire;
            }
        }
        return null;
    }

    public void ajouterQuartierDansMain(Quartier quartier) {
        main.add(quartier);
    }

    public Quartier retirerQuartierDansMain() {
        if (!main.isEmpty()) {
            Random random = new Random();
            return main.remove(random.nextInt(main.size()));
        }
        return null;
    }

    // Méthode pour réinitialiser le joueur
    public void reinitialiser() {
        main.clear();
        for (int i = 0; i < cite.length; i++) {
            cite[i] = null;
        }
        pieces = 0;
        possedeCouronne = false;
    }
    public void setPersonnage(Personnage personnageChoisi) {
        // Ici, vous pouvez ajouter une logique pour vérifier si le personnage est déjà pris
        // Mais pour l'instant, nous assignons simplement le personnage au joueur
        this.personnage = personnageChoisi;

        // Assurez-vous également de mettre à jour l'association dans l'objet Personnage
        if (personnageChoisi != null) {
            personnageChoisi.setJoueur(this);
        }
    }

    // Méthode pour obtenir le personnage du joueur
    public Personnage getPersonnage() {
        return personnage;
    }
}

