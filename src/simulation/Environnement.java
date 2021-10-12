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
					
					if(tour%20==0) {
						System.out.println("*********Product matiere");
						int start = 11 ; 
						//Simulation.usine_build(start);
						Simulation.usine_build(11);
						Simulation.usine_build(12);
						Simulation.usine_build(13);
						Simulation.usine_build(31);
						Simulation.usine_build(21);
						Simulation.usine_build(41);

					}
					
				}
				
				
				
				firePropertyChange("TEST", null, "tour: "+tour);			
			}			
		}
		return null;
	}

}