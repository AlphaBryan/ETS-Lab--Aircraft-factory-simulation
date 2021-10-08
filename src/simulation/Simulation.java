package simulation;

import java.awt.Point;
import java.util.ArrayList;
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
	//private static ArrayList<Integer> production_queue = new ArrayList<Integer>() ; 
	public static ArrayList<Composant> production_queue = new ArrayList<Composant>() ; 

	private static boolean running = false ; 
	
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}
	
	public static void run() {
		running = true ; 
		System.out.println("--> Launch Simulation !");
	}
	
	public static void stop() {
		running = false ; 
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
		int exit = 21;
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
		addQueue(element);

	}
	
	//TODO METHODE MAGIQUE OU ON DONNE L'ID START et il trouve le chemin 
	//Hash id_end / nom type 

	
}
