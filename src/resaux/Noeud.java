package resaux;

import java.util.ArrayList;

import usines.Usine;

public class Noeud extends Usine {

	public Noeud(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public Noeud(Usine usine) {
		super(usine.getType());
		// TODO Auto-generated constructor stub
	}
	
	public Noeud(String type, int[] position, ArrayList<String> icones) {
		// TODO Auto-generated constructor stub
		super(type, position, icones);
	}
	
	private int id ; 
	private int position ; 
		

}
