package vente_strategy;

import resaux.Noeud;
import usines.Usine_entrepot;

public interface Vente_Strategy {

	static int number_toSell = 0 ; 
	static String object_toSell = "avion" ; 
	
	public boolean verifyVente(Noeud entrepot, int planes_in_queue);

	public int getNumber_toSell();
	
	public String getObjectToSell() ;

	public boolean verifyVente(Usine_entrepot entrepot, int plane_in_queue); 

}