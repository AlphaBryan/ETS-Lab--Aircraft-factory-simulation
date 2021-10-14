package resaux;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import usines.Usine;

public class Noeud extends Usine {
	
	private int id ; 
	private int position ; 
	private String imgPath ;
    private Image image ;
    private int image_state =-1 ; 
    
    private double startTime = 0 ;
    
    private double b = 5 /2 ; 

	public Noeud(String type) {
		super(type);
		update_Image(0);
	}
	
	public Noeud(Usine usine) {
		super(usine);
		update_Image(0);
	}
	
	public Noeud(String type, int[] position, ArrayList<String> icones) {
		super(type, position, icones);
		update_Image(0);
	}
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
		
	public void update_Image(int state) {
		if( state != image_state ) {
			image_state = state ; 
			String rel_Path = ' ' + this.getTheIcone(state);
			String abs_Path = new File(rel_Path).getAbsolutePath().replaceAll(" ", "");
			if(abs_Path.contains( "\\src\\src\\")) {
				abs_Path = abs_Path.replaceAll("\\\\src\\\\src\\\\", "\\\\src\\\\");
			}
			this.image = Toolkit.getDefaultToolkit().createImage(abs_Path);		
		}
		
	}
	

	public void new_input(String type) {
		if( this.enter_product_type.get(type) > this.stock.get(type) ) {
			this.stock.put(type, stock.get(type)+1);
		}
		checkStock();
	}
	
	public void checkStock() {
		if ( stock.equals(enter_product_type)   ) {
			inProduction = true ; 
			System.out.println(" �The Stock Usine "+getType()+" is Full");
		}
	}
	
	public boolean waitProduction() {
		startTime +=1 ; 
		double ratio = startTime / Double.valueOf( getInterval_production() ) ;
		if (ratio < 0.33) {
			update_Image(0);
		}
		else if (0.33 <= ratio && ratio < 0.66) {
			update_Image(1);
		}
		else if (0.66 <= ratio && ratio < (1)  ) {
			update_Image(2);
		}
		else if (ratio >= (1) ) {
			update_Image(3);
			startTime = 0 ; 
			if(getOutput_product_type() != null) {
				System.out.println("  � The Usine "+getType()+" produce a " + getOutput_product_type());
				return true ;
			}
		}
		return false;
	}
	
    
    
		

}
