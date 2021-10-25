package vente_strategy;

import resaux.Noeud;
import usines.Usine_entrepot;

public class Vente_Aleatoire implements Vente_Strategy {


	private static int number_toSell = 2 ;
	private int randNumber = -1 ; 
	
	public boolean verifyVente(Noeud entrepot, int plane_in_queue) {
		double planes_count = 0 ; 
		planes_count += entrepot.calculate_inStock() ; 
		//planes_count += plane_in_queue ; 

		if(planes_count >= random_count() ) {
			return true ; 
		}
		return false ;
	}
	
	@Override
	public boolean verifyVente(Usine_entrepot entrepot, int plane_in_queue) {
		double planes_count = 0 ; 
		planes_count += entrepot.calculate_inStock() ; 
		//planes_count += plane_in_queue ; 

		if(planes_count >= random_count() ) {
			return true ; 
		}
		return false ;
	}
	
	public int random_count() {
		 int min = 1;
		 int max = 700;
		 int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		 return random_int;
	}
	
	@Override
	public int getNumber_toSell() {
		return number_toSell;
	}
	
	@Override
	public String getObjectToSell() {
		return object_toSell;
	}

	@Override
	public String toString() {
		return "Vente Aléatoire" ;
	}


	
	






}
