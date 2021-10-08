package simulation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import resaux.Chemin;
import resaux.Noeud;
import composants.Composant ;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	

	// Variables temporaires de la demonstration:
	private Point position = new Point(1,1);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	Point pos = new Point(32,32) ; 
	String abs_Path = "C:\\School-stuff-ETS\\JAVAWORKSPACE-ETS\\lab1-squelette\\src\\ressources\\metal.png" ; 
	//String abs_Path = "H:\\Log121\\lab01\\lab1-squelette\\src\\ressources\\metal.png" ; 
	Image image_test = Toolkit.getDefaultToolkit().createImage(abs_Path);
  
	public PanneauPrincipal() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
		//position.translate(vitesse.x, vitesse.y);
		//g.fillRect(position.x, position.y, taille, taille);
		//g.drawLine(0, 0, 300, 300);
    	paint_Paths(g);
    	paint_Nodes(g);
    	paint_Composants(g);

	}
	
	public void paint_Nodes(Graphics g) {
        if(Simulation.Usines != null ) {
    		for(Noeud node : Simulation.Usines.values()) {
    	        g.drawImage(node.getImage(), node.getX(), node.getY(), null);
    		}	
        }
	}
	
	public void paint_Paths(Graphics g) {
		if(Simulation.Chemins != null ) {
			for(Chemin chemin : Simulation.Chemins) {
				Noeud from = Simulation.Usines.get(chemin.enter) ;
				Noeud to = Simulation.Usines.get(chemin.exit) ; 
				g.drawLine(from.getX()+16, from.getY()+16, to.getX()+16, to.getY()+16);
			}
	    	//paint_composant_test(g,pos);
	    	//paint_composant(g,11);
	    	//paint_composant_test(g, pos);
	    	

		}
		
	}

	public void paint_composant_test(Graphics g, Point pos) {
		Point start = new Point(32,32);
		Point end = new Point(320,32);
		Point vit = new Point(5,0);
		pos.translate(vit.x, vit.y);
		if(pos.x<end.x) {
			//g.fillRect(pos.x+25, pos.y+11, 10, 10);
	        g.drawImage(image_test, pos.x, pos.y, null);
		}
		else {pos.x=32;
		}
	}
	
	public void paint_composant(Graphics g, int enter) {
		int exit = 21;
		Noeud start = Simulation.Usines.get(enter) ;
		Noeud end = Simulation.Usines.get(exit) ; 
		Chemin chemin ;
		for(Chemin iChemin : Simulation.Chemins) {
			if (iChemin.checkNodes(enter, exit)) {
				chemin = iChemin ;
			}
		}
		Composant element = start.build_product() ;
		element.move();
		if(element.getX()<end.getX()-element.getImageWidth()) {
	        g.drawImage(element.getIcone(), element.getX()+element.getImageWidth(), element.getY()+element.getImageHeight()-2, null);
		}
		else {
			//element.position.x=start.getX()-30;
			//Simulation.outQueue(enter);
		}
	}

	public void paint_Composants(Graphics g) {
		for(Composant element : Simulation.getQueue() ) {
			if(element.getX()<320-element.getHalfWidth()) {
				element.move(); //Regler le movement de l'element avec un kill ou quoi 
		        //g.drawImage(element.getIcone(), element.getX(), element.getY(), null);
			}
			else {
				//Simulation.outQueue(element); //TODO GET L'arrivée autrement, et notifier le end 
			}
			
		}
	}
	



}