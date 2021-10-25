package simulation;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import composants.Composant;
import composants.Composant_metal;
import resaux.Chemin;
import resaux.Noeud;
import usines.Usine;
import usines.Usine_entrepot;
import vente_strategy.Vente_Strategy;

public class Simulation {
	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static Map<Integer, Noeud> Usines ; 
	public static ArrayList<Chemin> Chemins ;
	public static boolean FILE_CHARGED = false ; 
	private static Usine_entrepot OBS_Subject ; 
	
	private static Map<Integer, Map<String, Integer>> simulationMap = new HashMap<Integer, Map<String, Integer>>(); 

	public static ArrayList<Composant> production_queue = new ArrayList<Composant>() ; 
	public static int plane_in_queue = 0 ; 
	
	private static boolean running = false ;
	private static boolean production_isOn = false;
	private static Vente_Strategy strategy ; 
	private static int sells ; 

	
	public static void main(String[] args) {
		System.out.println( "# Laboratoire 1 : LOG121 - Simulation " );
		System.out.println( "# Author : Bryan MEVO \n" );
		
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();
		environnement.addPropertyChangeListener(fenetre);
		
		environnement.execute();
		System.out.println("• Lancement Programme : OK");
	}
	
	public static void run() {
		updatePaths();
		updateNodes();
		setSubject();
		attachListerToSubject();
		running = true ; 
		production_isOn = true ; 
		System.out.println("• Lancement Simulation : OK");
	}
	
	public static void stop() {
		running = false ; 
	}
	
	public static boolean production_isOn() {
		return production_isOn;
	}

	public static void setProduction_isOn(boolean production_isOn) {
		Simulation.production_isOn = production_isOn;
	}

	public static boolean isRunning() {
		return running ; 
	}

	
	public static Vente_Strategy getStrategy() {
		return strategy;
	}

	public static void setStrategy(Vente_Strategy strategy) {
		Simulation.strategy = strategy;
		System.out.println("set strat =>"+strategy.toString());
	}

	public static void addQueue(Composant element) {
		production_queue.add(element);
	}
	public static void outQueue(Composant element) {
		int index = production_queue.indexOf(element) ;
		production_queue.remove(index) ;
	}
	
	public static boolean queueEmpty() {
		return production_queue.isEmpty();
	}
	
	public static ArrayList<Composant> getQueue() {
		return production_queue ;
	}
	
	public static void usine_build(int enter) {
		Noeud start = Usines.get(enter) ;
		Composant element = start.build_product() ;
		element.setPosition(new Point(start.getX(),start.getY()));
		int idEnd = simulationMap.get(start.getId()).get(element.getType());
		Noeud end = Usines.get(idEnd);
		element.setFullStop(end);
		element.updateSpeed();
		addQueue(element);
		if(element.getType().equals("avion")) {
			plane_in_queue ++ ; 
		}
	}
	
	public static void refreshQueue() {
		for(int i = 0 ; i<production_queue.size() ; i++) {
			Composant iComp = production_queue.get(i) ;
			if(iComp.iskill()) {
				int endId = iComp.getIDFullStop();
				Usines.get(endId).new_input(iComp.getType());
				production_queue.remove(i);

			}	
		}
	}
	
	public static void updatePaths() {
		for (Chemin path : Chemins) {
			Noeud output = Usines.get(path.getOrigin());
			Noeud input = Usines.get(path.getEnd());
			String typeProduct = output.getOutput_product_type() ; 
			if( input.accept( typeProduct ) ) {
				path.setType_composant( typeProduct );
				Map<String, Integer> iproduct_iEnd = new HashMap<String, Integer>();
				iproduct_iEnd.put(typeProduct, input.getId());
				simulationMap.put(output.getId()  , iproduct_iEnd ); 
			}
		}
	}

	public static void updateNodes() {
		for(Noeud node : Usines.values()) {
			if(node.getType().equals("usine-matiere")) {
				node.setInProduction(true);
			}
		}
	}
	
	public static void notifyTOUR(int tour) {
		printConsole(tour);
		for (Noeud usine : Usines.values()) {
			if (usine.getId() == OBS_Subject.getId()) {
				OBS_Subject.setState( usine.waitSubProduction() );
				sendSubjectNotifiction();
				verify_sellStrategy();
			}
			if(usine.isInProduction()) {
				if (usine.getId() != OBS_Subject.getId()) {
					if(usine.waitProduction() == true) {
						Simulation.usine_build(usine.getId());
					}
				}
			}
		}
	}

	public static void setSubject() {
		for(Noeud node : Usines.values()) {
			if(node.getType().equals("entrepot")) {
				OBS_Subject = new Usine_entrepot( node ) ;
			}
		}
	}

	public static void attachListerToSubject() {
		for (Noeud usine : Usines.values()) {
			if( usine.getId() != OBS_Subject.getId() ) {
				OBS_Subject.attach(usine.getId());
			}
		}		
	}
	
	public static void sendSubjectNotifiction() {
		Usines = OBS_Subject.notifyObservers(Usines);		
	}
	
	public static void verify_sellStrategy() {
		if( strategy.verifyVente(Usines.get(OBS_Subject.getId()), plane_in_queue)) {
			Usines.get(OBS_Subject.getId()).output(strategy.getObjectToSell(), strategy.getNumber_toSell());
			//OBS_Subject.vente();
			setSubject();
			sells++;

		}


	}
	
	public static void printConsole(int tour) {
		System.out.println("\n - tour: "+tour);
		System.out.println("  • Stratégie utilisée: " + strategy.toString() );
		System.out.println("  • Stock Avion: "+ (int)OBS_Subject.calculate_inStock() + "/" + OBS_Subject.getCapacity());
		System.out.println("  • Vitesse de Production: "+ OBS_Subject.getSpeed() );
		System.out.println("  • Nombre de ventes: " + sells + " vente(s)" );
	}
	
}
