package modele;

public class Personnage {
    private String nom;
    private int rang;
    private String[] caracteristiques;
    private Joueur joueur;
    private boolean vole;
    private boolean assassine;

    // Constructeur
    public Personnage(String nom, int rang, String[] caracteristiques) {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.joueur = null;
        this.vole = false;
        this.assassine = false;
    }

    // Accesseurs en lecture
    public String getNom() {
        return nom;
    }

    public int getRang() {
        return rang;
    }

    public String[] getCaracteristiques() {
        return caracteristiques;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public boolean isVole() {
        return vole;
    }

    public boolean isAssassine() {
        return assassine;
    }

    // Accesseurs en écriture
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setVole(boolean vole) {
        this.vole = vole;
    }

    public void setAssassine(boolean assassine) {
        this.assassine = assassine;
    }

    // Méthodes spécifiques demandées par les tests
    public void ajouterPieces(int nombreDePieces) {
        if (!vole && !assassine && joueur != null) {
            joueur.ajouterPieces(nombreDePieces);
        }
    }

    // Supposons que vous ayez besoin de méthodes pour gérer les quartiers.
    // Vous devez définir ce que ces méthodes doivent faire et les implémenter ici.
    // Par exemple, vous pourriez avoir une méthode pour ajouter un quartier à la cité du joueur.
    public void ajouterQuartierDansCite(Quartier quartier) {
        if (joueur != null) {
            joueur.ajouterQuartierDansCite(quartier);
        }
    }

    // Méthode pour utiliser le pouvoir du personnage
    // L'implémentation dépendra des règles spécifiques du jeu.
    public void utiliserPouvoir() {
        // Logique pour utiliser le pouvoir
    }

    // Méthode pour réinitialiser les attributs du personnage pour un nouveau tour
    public void reinitialiser() {
        this.vole = false;
        this.assassine = false;
        // Vous pouvez ajouter d'autres réinitialisations si nécessaire.
    }

}
