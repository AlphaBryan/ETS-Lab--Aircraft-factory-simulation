package simulation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import resaux.Noeud;

public class FenetrePrincipale extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
	private static final Dimension DIMENSION = new Dimension(700, 700);

	public FenetrePrincipale() {
		int[] pos_test = {320,352} ; 
		ArrayList<String> icones_test = new ArrayList<String>() ; 
		icones_test.add("C:\\School-stuff-ETS\\JAVAWORKSPACE-ETS\\lab1-squelette\\src\\ressources\\moteur.png");
		Noeud node_test = new Noeud("x",pos_test,icones_test); 
		PanneauPrincipal panneauPrincipal = new PanneauPrincipal(node_test);
		MenuFenetre menuFenetre = new MenuFenetre();
		add(panneauPrincipal);
		add(menuFenetre, BorderLayout.NORTH);
		// Faire en sorte que le X de la fenêtre ferme la fenêtre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		// Rendre la fenêtre visible
		setVisible(true);
		// Mettre la fenêtre au centre de l'écran
		setLocationRelativeTo(null);
		// Empêcher la redimension de la fenêtre
		setResizable(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("TEST")) {
			repaint();
			System.out.println(evt.getNewValue());
		}
	}
}
