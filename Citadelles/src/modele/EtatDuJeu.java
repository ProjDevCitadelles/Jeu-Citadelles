package modele;

import java.util.List;
import java.util.Map;

public class EtatDuJeu {
    private final List<Joueur> joueurs;
    private final PlateauDeJeu plateauDeJeu;
    private final Joueur joueurCourant;
    private final Map<Joueur, Integer> scores;

    public EtatDuJeu(List<Joueur> joueurs, PlateauDeJeu plateauDeJeu, Joueur joueurCourant, Map<Joueur, Integer> scores) {
        this.joueurs = joueurs;
        this.plateauDeJeu = plateauDeJeu;
        this.joueurCourant = joueurCourant;
        this.scores = scores;
    }

    // Methods to access the game state data
    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public PlateauDeJeu getPlateauDeJeu() {
        return plateauDeJeu;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Map<Joueur, Integer> getScores() {
        return scores;
    }

}
