package modele;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
	private ArrayList<Quartier> liste;

	public Pioche() {
		liste = new ArrayList<>();
	}

	public Quartier piocher() {
		if (liste.isEmpty()) {
			return null;
		} else {
			return liste.remove(liste.size() - 1);
		}
	}

	public void ajouter(Quartier nouveau) {
		liste.add(nouveau); // Ajoute à la fin de la liste pour être pioché en dernier
	}

	public int nombreElements() {
		return liste.size();
	}

	public void melanger() {
		Collections.shuffle(liste); // Utilise la méthode shuffle de Collections pour mélanger
	}
}
