package modele;

import java.util.Random;

import controleur.Interaction;

public class Condottiere extends Personnage {


    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE);

    }

    public void percevoirRessourcesSpecifiques() {
        Quartier[] citeJ = getJoueur().getCite();
        int nbQuartiers = getJoueur().nbQuartiersDansCite();
        for (int i = 0; i < nbQuartiers; i++) {
            if (citeJ[i] != null && citeJ[i].getType().equals(Quartier.TYPE_QUARTIERS[1])) {
                getJoueur().ajouterPieces(1);
            }
        }
    }

    public void utiliserPouvoir() {
        System.out.println("Voulez-vous utiliser votre pouvoir de destruction ? (o/n)");
        boolean utiliserPouvoir = Interaction.lireOuiOuNon();

        if (utiliserPouvoir) {
            System.out.println("Voici la liste des joueurs et le contenu de leur cit\u00E9 :");

            for (int i = 0; i < this.getPlateau().getNombreJoueurs(); i++) {
                Joueur joueur = this.getPlateau().getJoueur(i);
                System.out.println("\n" + (i + 1) + " " + joueur.getNom() + " : ");

                for (int j = 0; j < joueur.nbQuartiersDansCite(); j++) {
                    Quartier quartier = joueur.getCite()[j];
                    if (quartier != null) {
                        System.out.println((j + 1) + " " + quartier.getNom() + " (co\u00FBt " + quartier.getCout() + "), ");
                    }
                }
            }

            System.out.println("\n" + "Pour information, vous avez " + this.getJoueur().nbPieces() + " pi\u00E8ces d\u2019or dans votre tr\u00E9sor");

            System.out.println("Quel joueur choisissez-vous ? (0 pour ne rien faire)");
            int listeJoueur = Interaction.lireUnEntier(0, (getPlateau().getNombreJoueurs() + 1));
            if (listeJoueur == 0) {
                return;
            }
            Joueur joueurChoisi = getPlateau().getJoueur(listeJoueur - 1);
            boolean choixDuJoueur = false;

            do {
                if (joueurChoisi.getPersonnage().getNom().equals("Eveque") && joueurChoisi.getPersonnage().getAssassine() == false) {
                    System.out.println("Impossible de choisir un Eveque vivant");
                } else {
                    choixDuJoueur = true;

                    boolean choixDuQuartier = false;

                    do {
                        System.out.println("Quel quartier choisissez-vous ? ");
                        int listeQuartiers = Interaction.lireUnEntier(1, (joueurChoisi.nbQuartiersDansCite() + 1));

                        Quartier quartierChoisi = joueurChoisi.getCite()[listeQuartiers - 1];

                        if (this.getJoueur().nbPieces() < (quartierChoisi.getCout() - 1)) {
                            System.out.println("Votre tr\u00E9sor n\u2019est pas suffisant");
                        } else {
                            choixDuQuartier = true;
                            System.out.println(joueurChoisi.nbQuartiersDansCite());
                            joueurChoisi.retirerQuartierDansCite(quartierChoisi.getNom());
                            System.out.println("Vous avez d\u00E9moli " + quartierChoisi.getNom() + " de la cit\u00E9 de " + joueurChoisi.getNom());
                        }


                    } while (!choixDuQuartier);
                }
            } while (!choixDuJoueur);
        }
    }

    public void utiliserPouvoirAvatar() {
        Random rand = new Random();
        boolean choixDuJoueur = false;

        int listeJoueur = rand.nextInt(this.getPlateau().getNombreJoueurs() + 1);
        Joueur joueurChoisi = getPlateau().getJoueur(listeJoueur - 1);

        while (joueurChoisi.nbQuartiersDansCite() == 0 || ((joueurChoisi.getPersonnage().getNom().equals("Eveque") && joueurChoisi.getPersonnage().getAssassine() == false))) {
            listeJoueur = rand.nextInt(this.getPlateau().getNombreJoueurs() + 1);
            joueurChoisi = getPlateau().getJoueur(listeJoueur - 1);
        }

        int listeQuartiers = rand.nextInt(joueurChoisi.nbQuartiersDansCite());
        Quartier quartierChoisi = joueurChoisi.getCite()[listeQuartiers];

        while (this.getJoueur().nbPieces() < (quartierChoisi.getCout() - 1)) {
            listeQuartiers = rand.nextInt(joueurChoisi.nbQuartiersDansCite());
            quartierChoisi = joueurChoisi.getCite()[listeQuartiers];
        }

        joueurChoisi.retirerQuartierDansCite(quartierChoisi.getNom());
        System.out.println("La condottiere a démoli " + quartierChoisi.getNom() + " de la cit\u00E9 de " + joueurChoisi.getNom());
        choixDuJoueur = true;

    }

}