package usines;

import java.util.ArrayList;

import composants.Composant;

public class Usine {

	private int id ; 
	private int x ; 
	private int y ; 
	
	private String icone ; //check sur les autres ²
	
	protected Composant enter_product_type ;
	private ArrayList<Composant> enter_products = new ArrayList<Composant>() ; 
	private int max_capacity ;
	private Composant output_product_type ; 
	private int interval_production ; 

	public Usine(int id, int x, int y, Composant enter_product_type, int max_capacity, Composant output_product_type,
			int interval_production) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.enter_product_type = enter_product_type;
		this.max_capacity = max_capacity;
		this.output_product_type = output_product_type;
		this.interval_production = interval_production;
	}

	public void isProducing() {
		System.out.println("Je produit des : "+ enter_product_type.toString());
	}
	public void getproduct(Composant product) {
		enter_products.add(product) ; 
	}
	
	public void sendProduct() {
		enter_products.remove(0) ; 
	}
	
	public void build_product() {
		if (max_capacity == enter_products.size()) {
			enter_products.clear();
		}
	}
	
}

