package usines;

import java.util.ArrayList;

import composants.Composant;

public class Usine {

	private int id ; 
	private ArrayList<Integer> position ; 	
	private ArrayList<String> icones  ; //check sur les autres ²
	
	protected Composant enter_product_type ;
	private ArrayList<Composant> enter_products = new ArrayList<Composant>() ; 
	private int max_capacity ;
	private Composant output_product_type ; 
	private int interval_production ; 

	public Usine(ArrayList<Integer> position, Composant enter_product_type, int max_capacity, Composant output_product_type,
			int interval_production) {
		super();
		this.id = -1 ;
		this.position = position;
		this.enter_product_type = enter_product_type;
		this.max_capacity = max_capacity;
		this.output_product_type = output_product_type;
		this.interval_production = interval_production;
	}
	
	public Usine() {
		super();
		this.id = -1 ;
	}

	@Override
	public String toString() {
		return "Usine [id=" + id + ", enter_product_type=" + enter_product_type + ", output_product_type="
				+ output_product_type + ", interval_production=" + interval_production + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getPosition() {
		return position;
	}

	public void setPosition(ArrayList<Integer> position) {
		this.position = position;
	}


	public ArrayList<String> getIcone() {
		return icones;
	}

	public void setIcone(ArrayList<String> icones) {
		this.icones = icones;
	}

	public void addIcone(String icone) {
		this.icones.add(icone);
	}
	 

	public Composant getEnter_product_type() {
		return enter_product_type;
	}

	public void setEnter_product_type(Composant enter_product_type) {
		this.enter_product_type = enter_product_type;
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

	public Composant getOutput_product_type() {
		return output_product_type;
	}

	public void setOutput_product_type(Composant output_product_type) {
		this.output_product_type = output_product_type;
	}

	public int getInterval_production() {
		return interval_production;
	}

	public void setInterval_production(int interval_production) {
		this.interval_production = interval_production;
	}
	
	

	
	
}

