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

	public Noeud(String type) {
		super(type);
		init_Image();
	}
	
	public Noeud(Usine usine) {
		super(usine);
		init_Image();
		// TODO Auto-generated constructor stub
	}
	
	public Noeud(String type, int[] position, ArrayList<String> icones) {
		// TODO Auto-generated constructor stub
		super(type, position, icones);
		init_Image();
	}
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void init_Image() {
		String rel_Path = ' ' + this.getTheIcone(0).replaceAll("../src/", "") ;
		String abs_Path = new File(rel_Path).getAbsolutePath().replaceAll(" ", "");
		this.image = Toolkit.getDefaultToolkit().createImage(abs_Path);
	}
    
    
		

}
