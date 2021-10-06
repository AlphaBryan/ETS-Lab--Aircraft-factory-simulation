package simulation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

import resaux.Noeud;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	

	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	private String imgPath = "C:\\School-stuff-ETS\\JAVAWORKSPACE-ETS\\lab1-squelette\\src\\ressources\\moteur.png";
    private Image image = Toolkit.getDefaultToolkit().createImage(imgPath);

	public PanneauPrincipal(Noeud node) {
		super();
		this.position = new Point(node.getX(),node.getY()) ; 
		this.vitesse = new Point(node.getVitesseX(), node.getVitesseY()) ; 
		String imgPath = node.getTheIcones(0);
		this.image = Toolkit.getDefaultToolkit().createImage(imgPath) ; 
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);
        g.drawImage(image, position.x, position.y-1, null);

	}
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(null, 0, 0, null);
	}


}