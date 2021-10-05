package resaux;

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
	private int id ; 
	private int position ; 
		

}
