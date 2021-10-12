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

public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static Map<Integer, Noeud> Usines ; 
	public static ArrayList<Chemin> Chemins ;
	
	// # Map<id Origin, Map< nameType, id Fin >> Du point de vue l'élément
	private static Map<Integer, Map<String, Integer>> simulationMap = new HashMap<Integer, Map<String, Integer>>(); 

	public static ArrayList<Composant> production_queue = new ArrayList<Composant>() ; 

	private static boolean running = false ;
	private static boolean production_isOn = false;

	
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}
	
	public static void run() {
		updatePaths();
		running = true ; 
		production_isOn = true ; 
		System.out.println("***Launch Simulation !");
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
		System.out.println("creation from: "+start.getType());
//		Noeud end = Usines.get(exit) ; 
//		Chemin chemin ;
//		for(Chemin iChemin : Simulation.Chemins) {
//			if (iChemin.checkNodes(enter, exit)) {
//				chemin = iChemin ;
//			}
//		}		
		Composant element = start.build_product() ;
		//Composant element = new Composant_metal("metal") ;
		element.setPosition(new Point(start.getX(),start.getY()));
		
		int idEnd = simulationMap.get(start.getId()).get(element.getType());
		Noeud end = Usines.get(idEnd);
		element.setFullStop(end);
		element.updateSpeed();

		addQueue(element);

	}
	
	public static void refreshQueue() {
		for(int i = 0 ; i<production_queue.size() ; i++) {
			if(production_queue.get(i).iskill()) {
				production_queue.remove(i);
				System.out.println("We kill someone");
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

	
}
