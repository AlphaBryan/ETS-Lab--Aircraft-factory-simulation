package composants;

public class Composant {

	private String type ; 
	private String icone ; 
	private int position ;
	private int vitesse ; 
	
	
	public Composant(String type, String icone, int position,int vitesse) {
		super();
		this.type = type;
		this.icone = icone;
		this.position = position;
		this.vitesse = vitesse ; 
	}


	@Override
	public String toString() {
		return "Composant [type=" + type + "]";
	} 
	
	
	
	
}
