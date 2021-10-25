package vente_strategy;

import resaux.Noeud;
import usines.Usine_entrepot;

public class Vente_Fixe implements Vente_Strategy {

	public int number_toSell = 1 ;
	
	@Override
	public boolean verifyVente(Noeud entrepot, int plane_in_queue) {
		double planes_count = 0 ; 
		planes_count += entrepot.calculate_inStock() ; 
		planes_count += plane_in_queue ; 

		if(planes_count >= 4) {
			return true ; 
		}
		return false ;
	}
	
	@Override
	public boolean verifyVente(Usine_entrepot entrepot, int plane_in_queue) {
		double planes_count = 0 ; 
		planes_count += entrepot.calculate_inStock() ; 
		planes_count += plane_in_queue ; 

		if(planes_count >= 4) {
			return true ; 
		}
		return false ;
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
		return "Vente Fixe" ;
	}


	

	
	

}
