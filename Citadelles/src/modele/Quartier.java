package modele;

public class Quartier {
    private String nom;
    private String type;
    private int coutConstruction;
    private String caracteristiques;

    // Constante pour les types de quartiers
    public static final String[] TYPE_QUARTIERS = {"Religieux", "Noble", "Commerce", "Militaire"};

    // Constructeur
    public Quartier(String nom, String type, int coutConstruction, String caracteristiques) {
        this.nom = nom;
        setType(type); // Vérification du type
        setCoutConstruction(coutConstruction); // Vérification du coût de construction
        this.caracteristiques = caracteristiques;
    }

    // Accesseurs pour le nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Accesseurs pour le type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        for (String typeValide : TYPE_QUARTIERS) {
            if (typeValide.equals(type)) {
                this.type = type;
                return;
            }
        }
        throw new IllegalArgumentException("Type invalide. Les types valides sont : " + String.join(", ", TYPE_QUARTIERS));
    }

    // Accesseurs pour le coût de construction
    public int getCoutConstruction() {
        return coutConstruction;
    }

    public void setCoutConstruction(int coutConstruction) {
        if (coutConstruction < 1 || coutConstruction > 6) {
            throw new IllegalArgumentException("Le coût de construction doit être entre 1 et 6.");
        }
        this.coutConstruction = coutConstruction;
    }

    // Accesseurs pour les caractéristiques
    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }
}
