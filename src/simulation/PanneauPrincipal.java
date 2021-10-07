package simulation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resaux.Chemin;
import resaux.Noeud;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	

	// Variables temporaires de la demonstration:
	private Point position = new Point(1,1);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	Point pos = new Point(32,32) ; 
	String abs_Path = "C:\\School-stuff-ETS\\JAVAWORKSPACE-ETS\\lab1-squelette\\src\\ressources\\metal.png" ; 
	Image image_test = Toolkit.getDefaultToolkit().createImage(abs_Path);

	
    
	public PanneauPrincipal(Noeud node) {
		super();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);
		//g.drawLine(0, 0, 300, 300);
		

    	paint_paths(g);
    	paint_nodes(g);

	}
	
	public void paint_nodes(Graphics g) {
        if(Simulation.Usines != null ) {
    		for(Noeud node : Simulation.Usines.values()) {
    	        g.drawImage(node.getImage(), node.getX(), node.getY(), null);
    		}	
        }
	}
	
	public void paint_paths(Graphics g) {
		if(Simulation.Chemins != null ) {
			for(Chemin chemin : Simulation.Chemins) {
				Noeud from = Simulation.Usines.get(chemin.enter) ;
				Noeud to = Simulation.Usines.get(chemin.exit) ; 
				g.drawLine(from.getX()+16, from.getY()+16, to.getX()+16, to.getY()+16);
			}
	    	paint_composant(g,pos);

		}
		
	}
	public void paint_composant(Graphics g, Point pos) {
		Point start = new Point(32,32);
		Point end = new Point(320,32);
		Point vit = new Point(5,0);
		pos.translate(vit.x, vit.y);
		if(pos.x<end.x) {
			//g.fillRect(pos.x+25, pos.y+11, 10, 10);
	        g.drawImage(image_test, pos.x, pos.y, null);
		}
		else {pos.x=32;}
	}

	



}