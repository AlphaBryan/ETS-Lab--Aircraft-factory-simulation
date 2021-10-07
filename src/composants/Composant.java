package composants;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;

public class Composant {

	private String type ; 
	//private String icone ; 
    private Image icone ;
	public Point position ;
	private Point vitesse ; 
	
	
	public Composant(String type, Image icone, Point position, Point vitesse) {
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
		init_Icone();
		this.vitesse = new Point(5,0) ; //Faire une fonction set vitesse en fonction du chemin
	}


	@Override
	public String toString() {
		return "Composant [type=" + type + ", icone=" + icone + ", position=" + position + ", vitesse=" + vitesse + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	public void init_Icone() {
		String rel_Path = "src/ressources/"+type+".png";
		String abs_Path = new File(rel_Path).getAbsolutePath();
		this.icone= Toolkit.getDefaultToolkit().createImage(abs_Path);
	}

	public Point getPosition() {
		return position;
	}

	public int getX() {
		return position.x;
	}
	
	public int getY() {
		return position.y;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getIcone() {
		return icone;
	}
	
	public int getImageWidth(){
		return icone.getWidth(null);
	}
	public int getHalfWidth(){
		return icone.getWidth(null)/2;
	}
	
	public int getImageHeight(){
		return icone.getWidth(null);
	}
	public int getHalfHeight(){
		return icone.getWidth(null)/2;
	}

	public void setIcone(Image icone) {
		this.icone = icone;
	}

	public Point getVitesse() {
		return vitesse;
	}

	public void setVitesse(Point vitesse) {
		this.vitesse = vitesse;
	}
	
	public void move() {
		position.translate(vitesse.x, vitesse.y);
	}
	
	
}
