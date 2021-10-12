package resaux;

import java.util.HashMap;
import java.util.Map;

public class Chemin {

	private int origin ; 
	private int end ; 
	private String type_composant ;
	public double longueur ;
	
	
	
	public Chemin(int origin, int end) {
		super();
		this.origin = origin;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "Chemin [origin=" + origin + ", end=" + end + ", composant=" + type_composant + "]";
	}

	public int getOrigin() {
		return origin;
	}
	public void setOrigin(int origin) {
		this.origin = origin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int output) {
		this.end = output;
	}
	public double getLongeur() {
		return longueur;
	}
	public void setLongeur(int longeur) {
		this.longueur = longeur;
	} 
	
	public void calcul_Longueur(Map<Integer, Noeud> usine_map) {
		int[] enter_node = usine_map.get(origin).getPosition() ; 
		int[] exit_node = usine_map.get(end).getPosition()  ; 
		
		double x = exit_node[0] - enter_node[0] ;
		double x2 = Math.pow(x, 2) ;	
		double y = exit_node[1] - enter_node[1] ;
		double y2 = Math.pow(y, 2) ;
		
		double somme = x2 + y2 ;
		double distance = Math.sqrt(somme) ; 
		this.longueur = distance ; 
		
	}
	
	public boolean checkNodes(int id_enter, int id_exit) {
		if(id_enter == this.origin && id_exit == this.end) {
			return true ;
		}
		return false ;
	}

	public String getType_composant() {
		return type_composant;
	}

	public void setType_composant(String type_composant) {
		this.type_composant = type_composant;
	}
	
	public boolean carry_from(String carry , int from ) {
		if (carry==type_composant) {
			if(from == origin) {
				return true ; 
			}
		}
		return false ; 
	}
	
	
	
}


