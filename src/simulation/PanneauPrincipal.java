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
import composants.Composant;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauPrincipal() {
		super();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paint_Paths(g);
		paint_Composants(g);
		paint_Nodes(g);
	}

	public void paint_Nodes(Graphics g) {
		if (Simulation.FILE_CHARGED) {
			for (Noeud node : Simulation.Usines.values()) {
				g.drawImage(node.getImage(), node.getX(), node.getY(), null);
			}
		}
	}

	public void paint_Paths(Graphics g) {
		if (Simulation.FILE_CHARGED) {
			for (Chemin chemin : Simulation.Chemins) {
				Noeud from = Simulation.Usines.get(chemin.getOrigin());
				Noeud to = Simulation.Usines.get(chemin.getEnd());
				g.drawLine(from.getX() + 16, from.getY() + 16, to.getX() + 16, to.getY() + 16);
			}
		}
	}

	public void paint_Composants(Graphics g) {
		for (Composant element : Simulation.getQueue()) {
			if (element.inBound()) {
				element.move();
				g.drawImage(element.getIcone(), element.getX(), element.getY(), null);
			} else {
				if(element.getType().equals("avion")) {
					Simulation.plane_in_queue --;
				}
				element.kill();
			}
		}
	}

}