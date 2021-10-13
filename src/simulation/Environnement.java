package simulation;

import java.util.Queue;

import javax.swing.SwingWorker;

import composants.Composant;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
	private static int tour = 0;

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			if(Simulation.isRunning()) {
				tour++;
				Simulation.refreshQueue(); 
				if (Simulation.production_isOn()) {
					
					Simulation.checkProductionState(tour);
					
					if(tour%20==0) {
						System.out.println("*********Product matiere");
						//Simulation.usine_build(start);
						//Simulation.usine_build(11);
						//Simulation.usine_build(12);
						//Simulation.usine_build(13);
						//Simulation.usine_build(31);
						//Simulation.usine_build(21);
						//Simulation.usine_build(41);

						// check interval s'il à pas de composant il fait rien 
					}
					
				}
				
				
				
				firePropertyChange("TEST", null, "tour: "+tour);			
			}			
		}
		return null;
	}

}