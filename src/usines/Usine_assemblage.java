package usines;

import composants.Composant;

public class Usine_assemblage extends Usine {

	public Usine_assemblage(int id, int x, int y, Composant enter_product_type, int max_capacity,
			Composant output_product_type, int interval_production) {
		super(id, x, y, enter_product_type, max_capacity, output_product_type, interval_production);
	}

}
