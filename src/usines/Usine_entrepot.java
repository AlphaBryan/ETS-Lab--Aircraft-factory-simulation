package usines;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import resaux.Noeud;
import vente_strategy.Vente_Strategy;

public class Usine_entrepot extends Usine {
//	environnement.addPropertyChangeListener(fenetre);
	    
	private String state  ;
	private ArrayList<Integer> id_observers = new ArrayList<Integer>();
	
	private Vente_Strategy strategy ; 


	public Usine_entrepot(Noeud node) {
		super("entrepot");
		this.id = node.getId() ;
		this.type = node.getType() ; 
		this.icones = node.icones ; 
		this.position = node.getPosition() ; 
		this.enter_product_type = node.getEnter_product_type() ; 
		this.stock = node.getStock(); 
		this.output_product_type = node.getOutput_product_type() ;
		this.interval_production =  node.getInterval_production() ; 
		this.real_interval = node.getRealInterval_production() ; 
		calculate_factor();
		init_stock();
		state = "EMPTY" ;
	}
	
	public Usine_entrepot(String type) {
		super(type);
		state = "EMPTY" ;
	}

	public boolean iSubjectFull() {
		return stock.equals(enter_product_type) ;
	}
	
	public void attach(int id_usine) {
		id_observers.add(id_usine);
	}
	
	public Map<Integer, Noeud> notifyObservers(Map<Integer, Noeud> usines) {
		for (Integer id : id_observers) {
			usines.get(id).update(state);
		}
		return usines ;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Integer> getId_observers() {
		return id_observers;
	}

	public void setId_observers(ArrayList<Integer> id_observers) {
		this.id_observers = id_observers;
	}

	
	public String getSpeed() {
		if (state.equals("EMPTY")) {
			return "Vitesse Normal"; 
		}
		else if ( state.equals("NORMAL")) {
			return "Vitesse Ralentie"; 
		}
		else if ( state.equals("ALMOST_FULL")) {
			return "Vitesse Très Ralentie"; 
		}
		else if ( state.equals("FULL")) {
			return "Vitesse Nulle"; 

		}
		return "Erreur Vitesse";
	}

	public Vente_Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Vente_Strategy strategy) {
		this.strategy = strategy;
	}
	
	public boolean canSeLL(int plane_in_queue) {
		return strategy.verifyVente(this, plane_in_queue) ;
	}
	public void vente() {
		this.output(strategy.getObjectToSell(), strategy.getNumber_toSell());
	}
}
