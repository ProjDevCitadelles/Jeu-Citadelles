package modele;

import java.util.Iterator;

public class Roi extends Personnage {
    
    public Roi() {
        super("Roi", 4, Caracteristiques.ROI);
    }
// le roi est le personnage qui possede la couronne 
    public void utiliserPouvoir(){
      if (this.getJoueur()!=null) {
    	  this.getJoueur().setPossedeCouronne(true);
      } 
    }
// Un pouvoir du roi est de recevoir une piece pour chaque quartier noble qu'il y a dans la cité
    public void percevoirRessourcesSpecifiques(){
    	if(super.getJoueur()!=null) {
	      int nombreQuartierNoble = 0;
	      for (int i = 0; i < super.getJoueur().getCite().length; i++) {
			if (super.getJoueur().getCite()[i]!=null && super.getJoueur().getCite()[i].getType()=="NOBLE") {
				nombreQuartierNoble++;
			}
	    }
      super.getJoueur().ajouterPieces(nombreQuartierNoble);
      String message = "Le nombre de piece en plus :"+nombreQuartierNoble;
      System.out.println(message);
    }
    }
	//public Roi(String unNom, char unRang, String lesCaracteristiques) {
		//super(unNom, unRang, lesCaracteristiques);

	//}
	//::
//@Override
	//public void utiliserPouvoirAvatar() {
		
		//if (this.getJoueur()!=null) {
	    //	  this.getJoueur().setPossedeCouronne(true);
		//}
	//}

}
