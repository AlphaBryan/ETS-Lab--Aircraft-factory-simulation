package simulation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import vente_strategy.Vente_Aleatoire;
import vente_strategy.Vente_Fixe;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Stratégie 1");
		JRadioButton strategie2 = new JRadioButton("Stratégie 2");	
		
		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			// TODO - Appeler la bonne stratégie ***********************
			String str_strategy = getSelectedButtonText(groupeBoutons) ; 
		    if (str_strategy.equals("Stratégie 1")) {
		    	Simulation.setStrategy(new Vente_Fixe() );
		    }
		    else if (str_strategy.equals("Stratégie 2")) {
		    	System.out.println("Je veux le 2");
		    	Simulation.setStrategy(new Vente_Aleatoire() );
		    }
			System.out.println("• Sélection "+str_strategy+ " ["+ Simulation.getStrategy().toString() + "]"+ " : OK");

		    if(Simulation.Chemins == null || Simulation.Usines == null) {
		    	System.out.println(" • Erreur : Ficher Configuration non chargé");
		    }
		    else {
				Simulation.run(); 
		    }
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);		
		add(strategie1);
		add(strategie2);		
		add(boutonConfirmer);
		add(boutonAnnuler);

	}

	/**
	 * Retourne le bouton sélectionné dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

}
