package simulation;

import javax.swing.SwingWorker;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private boolean draw_node = false;
	private static final int DELAI = 100;
	private static int tour = 0;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			tour++;
			
			if(tour%100==0)System.out.println("==+ Product matiere");
			/**
			 * C'est ici que vous aurez à faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "tour: "+tour);
			if(Simulation.Usines != null && draw_node==false) {
				firePropertyChange("Node", null, "Ceci n'est pas un test");
				draw_node = true ;
			}
		}
		return null;
	}

}