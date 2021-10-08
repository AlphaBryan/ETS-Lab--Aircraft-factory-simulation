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
				System.out.println("==> the queue : "+Simulation.production_queue);
				if(tour%20==0) {
					System.out.println("*********Product matiere");
					int start = 11 ; 
					Simulation.usine_build(start);
				}
				/*
				 * for(Composant element: Simulation.production_queue) { element.move(); }
				 */
				

				
				
				firePropertyChange("TEST", null, "tour: "+tour);			
			}			
		}
		return null;
	}

}