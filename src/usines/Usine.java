package usines;

import java.awt.Point;
import java.security.spec.ECPoint;
import java.util.ArrayList;
import java.util.HashMap;

import composants.Composant;
import composants.Composant_aile;
import composants.Composant_avion;
import composants.Composant_metal;
import composants.Composant_moteur;

public class Usine {

	private int id ;
	private String type ; 
	private int[] position ; 	
	private ArrayList<String> icones  ; //check sur les autres �
	protected HashMap<String, Integer> enter_product_type ; //hash importance, nom
	private ArrayList<Composant> enter_products = new ArrayList<Composant>() ; 
	private int max_capacity ;
	private String output_product_type ; 
	private int interval_production ; 

	public Usine(Usine usine) {
		super();
		this.id = usine.getId() ;
		this.type = usine.getType() ; 
		this.position = usine.getPosition();
		this.icones = usine.getIcones() ;
 		this.enter_product_type = usine.getEnter_product_type();
 		this.enter_products = usine.getEnter_products() ;
		this.max_capacity = usine.getMax_capacity();
		this.output_product_type = usine.getOutput_product_type();
		this.interval_production = usine.getInterval_production();
	}
	

	public Usine(String type) {
		this.id = -1 ;
		this.type = type ; 
		this.icones = new ArrayList<String>() ; 
		this.position = new int[2] ; 
		this.enter_product_type = new HashMap<String, Integer>() ; 
		this.output_product_type = null ;
		this.interval_production =  0 ; 
	}
	

	public Usine(String type , int[] position,ArrayList<String> icones) {
		this.id = -1 ;
		this.type = type ; 
		this.icones = icones ; 
		this.position = position; 
		this.enter_product_type = new HashMap<String, Integer>() ; 
		this.output_product_type = null ;
		this.interval_production =  0 ; 
	}
	/*
	 * @Override public String toString() { return "Usine [id=" + id +
	 * ", enter_product_type=" + enter_product_type + ", output_product=" +
	 * output_product + ", interval_production=" + interval_production + "]"; }
	 */

	@Override
	public String toString() {
		return "Usine [id=" + id + ", type=" + type + ", position= [" + position[0] +","+position[1] +"]"+ ", icones:" + icones.size()
				+ ", enter_product_type=" + enter_product_type + ", enter_products=" + enter_products
				+ ", max_capacity=" + max_capacity + ", output_product=" + output_product_type
				+ ", interval_production=" + interval_production + "]";
	}


	public int getId() {
		return id;
	}
	
	public int getVitesseX() {
		return 0;
	}
	public int getVitesseY() {
		return 0;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setIcones(ArrayList<String> icones) {
		this.icones = icones;
	}
	
	public String getTheIcone(int i) {
		return icones.get(i);
	}

	public int[] getPosition() {
		return position;
	}
	
	public int getX() {
		return position[0];
	}
	
	public int getY() {
		return position[1];
	}
	
	public void setPosition(int[] position) {
		this.position = position;
	}

	public ArrayList<String> getIcones() {
		return icones;
	}

	public void setIcone(ArrayList<String> icones) {
		this.icones = icones;
	}

	public void addIcone(String icone) {
		this.icones.add(icone);
	}
	 
	public HashMap<String, Integer> getEnter_product_type() {
		return enter_product_type;
	}

	public void setEnter_product_type(HashMap<String, Integer> enter_product_type) {
		this.enter_product_type = enter_product_type;
	}
	
	public void addEnter_product_type(String enter_name, int enter_max) {
		this.enter_product_type.put(enter_name, enter_max) ;
	}	

	public ArrayList<Composant> getEnter_products() {
		return enter_products;
	}

	public void setEnter_products(ArrayList<Composant> enter_products) {
		this.enter_products = enter_products;
	}

	public int getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}

	public int getInterval_production() {
		return interval_production;
	}

	public void setInterval_production(int interval_production) {
		this.interval_production = interval_production;
	}
	
	public String getOutput_product_type() {
		return output_product_type;
	}
	
	public Composant build_product() {		
		if(output_product_type.equals("metal") ) { return new Composant_metal(output_product_type);}
		else if(output_product_type.equals("aile") ) { return new Composant_aile(output_product_type);}
		else if(output_product_type.equals("moteur") ) { return  new Composant_moteur(output_product_type);}
		else if(output_product_type.equals("avion") ) { return  new Composant_avion(output_product_type);}
		
		return null;
			}


	public void setOutput_product_type(String output_type) {
		this.output_product_type = output_type ; 
	}
	

	public boolean accept(String test_product_type) {
		return enter_product_type.containsKey(test_product_type);
	}
	
}

