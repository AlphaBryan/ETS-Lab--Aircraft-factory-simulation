package resaux;

import java.util.HashMap;
import java.util.Map;

public class Chemin {

	public int enter ; 
	public int exit ; 
	public double longueur ;
	
	
	
	public Chemin(int enter, int exit) {
		super();
		this.enter = enter;
		this.exit = exit;
	}
	
	@Override
	public String toString() {
		return "Chemin [enter=" + enter + ", exit=" + exit + ", longueur=" + longueur + "]";
	}

	public int getEnter() {
		return enter;
	}
	public void setEnter(int enter) {
		this.enter = enter;
	}
	public int getExit() {
		return exit;
	}
	public void setExit(int output) {
		this.exit = output;
	}
	public double getLongeur() {
		return longueur;
	}
	public void setLongeur(int longeur) {
		this.longueur = longeur;
	} 
	
	public void calcul_Longueur(Map<Integer, Noeud> usine_map) {
		int[] enter_node = usine_map.get(enter).getPosition() ; 
		int[] exit_node = usine_map.get(exit).getPosition()  ; 
		
		double x = exit_node[0] - enter_node[0] ;
		double x2 = Math.pow(x, 2) ;	
		double y = exit_node[1] - enter_node[1] ;
		double y2 = Math.pow(y, 2) ;
		
		double somme = x2 + y2 ;
		double distance = Math.sqrt(somme) ; 
		
		this.longueur = distance ; 
		
	}
	
	
	
}


