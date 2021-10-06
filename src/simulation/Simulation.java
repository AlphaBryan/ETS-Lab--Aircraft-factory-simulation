package simulation;

import java.util.ArrayList;
import java.util.Map;

import resaux.Chemin;
import resaux.Noeud;
import usines.Usine;

public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static Map<Integer, Noeud> Usines ; 
	public static ArrayList<Chemin> Chemins ;
	
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
