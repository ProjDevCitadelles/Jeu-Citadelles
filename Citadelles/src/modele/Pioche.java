package modele;

import java.util.ArrayList;
import java.util.Random;

public class Pioche {
	private ArrayList<Quartier> liste;
	Random generateur;
	public Pioche() {
		liste = new ArrayList<Quartier>();
	}
	public Quartier piocher() {
		Quartier pioche = null;
		if (liste.isEmpty()) {
			return pioche;
		} else {
			pioche = liste.get(liste.size()-1);
			liste.remove(liste.size()-1);
		}
		return pioche;
	}
	public void ajouter(Quartier nouveau) {
		liste.add(0, nouveau);
	}
	public int nombreElements() {
		return liste.size();
	}
	public void melanger() {
		generateur = new Random();
		for (int compteur = 0; compteur<nombreElements();compteur++) {
			int i = generateur.nextInt(nombreElements());
			int j = generateur.nextInt(nombreElements());
			Quartier quartier = liste.get(i);
			liste.set(i, liste.get(j));
			liste.set(j, quartier);
		}
		
		
	}
	
}
