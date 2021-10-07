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
	
	public Composant() {
		super();
	}
	public Composant(String type) {
		super();
		this.type = type ; 
	}


	@Override
	public String toString() {
		return " Composant [type=" + type + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	
	
	
}
