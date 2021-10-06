package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private boolean draw_node = false;
	private static final int DELAI = 100;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
			if(Simulation.Usines != null && draw_node==false) {
				firePropertyChange("Node", null, "Ceci n'est pas un test");
				draw_node = true ;
			}
		}
		return null;
	}

}