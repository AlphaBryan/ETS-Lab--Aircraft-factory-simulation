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
				System.out.println("- tour: "+tour);
				Simulation.refreshQueue(); 
				if (Simulation.production_isOn()) {
					
					Simulation.checkProductionState(tour); ///observer  ?
					
				}
								
			}		
			firePropertyChange("Background", null, null);			
		}
		return null;
	}

}