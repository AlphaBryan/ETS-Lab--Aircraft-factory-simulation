package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 20;
	private static int tour = 0;

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			if(Simulation.isRunning()) {
				tour++;
				Simulation.refreshQueue(); 
				Simulation.notifyTOUR(tour); 
			}		
			firePropertyChange("Background", null, null);			
		}
		return null;			
	}

}