package composants;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;

import resaux.Noeud;

public class Composant {

	private String type;
	// private String icone ;
	private Image icone;
	private Point position;
	private Point vitesse;

	private int idfullStop = -1; // id of full stop node
	private Point positionfullStop = new Point(); // id of full stop node

	private boolean kill = false;

	public Composant(String type, Image icone, Point position, Point vitesse) {
		super();
		this.type = type;
		this.icone = icone;
		this.position = position;
		this.vitesse = vitesse;
	}

	public Composant() {
		super();
	}

	public Composant(String type) {
		super();
		this.type = type;
		init_Icone();
		this.vitesse = null ; // Faire une fonction set vitesse en fonction du chemin
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
		String rel_Path = "src/ressources/" + type + ".png";
		String abs_Path = new File(rel_Path).getAbsolutePath();
		if (abs_Path.contains("\\src\\src\\")) {
			abs_Path = abs_Path.replaceAll("\\\\src\\\\src\\\\", "\\\\src\\\\");
		}
		this.icone = Toolkit.getDefaultToolkit().createImage(abs_Path);
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

	public int getImageWidth() {
		return icone.getWidth(null);
	}

	public int getHalfWidth() {
		return icone.getWidth(null) / 2;
	}

	public int getImageHeight() {
		return icone.getWidth(null);
	}

	public int getHalfHeight() {
		return icone.getWidth(null) / 2;
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
		this.position.translate(vitesse.x, vitesse.y);
	}

	public void kill() {
		this.kill = true;
	}

	public boolean iskill() {
		return this.kill;
	}

	public int getIDFullStop(Noeud fullstop) {
		return idfullStop;
	}

	public void setIDFullStop(int idfullStop) {
		this.idfullStop = idfullStop;
	}

	public Point getPositionfullStop() {
		return positionfullStop;
	}

	public void setPositionfullStop(Point positionfullStop) {
		this.positionfullStop = positionfullStop;
	}

	public void setFullStop(Noeud fullStop) {
		this.idfullStop = fullStop.getId();
		int[] pos = fullStop.getPosition();
		this.positionfullStop = new Point(pos[0], pos[1]);
	}

	public boolean inBound() {
		double dx = Math.abs(positionfullStop.x - position.x);		
		double dy = Math.abs(positionfullStop.y - position.y);
		if (dx > getHalfWidth()|| dy > getHalfHeight()) {
			return true;
		}
		return false;
	}

	public void updateSpeed() {
		int x = 0;
		int y = 0;

		if (position.x < positionfullStop.x) {
			x = 1;
		}
		if (position.x > positionfullStop.x) {
			x = -1;
		}
		if (position.y < positionfullStop.y) {
			y = 1;
		}
		if (position.y > positionfullStop.y) {
			y = -1;
		}

		int test_factor = 5;
		x *= test_factor;
		y *= test_factor;
		vitesse = new Point(x, y);
	}

}
