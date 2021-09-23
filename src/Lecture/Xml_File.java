package Lecture;

import java.util.ArrayList;
import java.util.List;

public class Xml_File extends Xml_Reader {
	
	
	
	String filePath ;
	
		
	public Xml_File(String filePath) {
		super();
		this.filePath = filePath;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public void read_metadonnees() throws Exception {
		readXML(filePath, "metadonnees");
	}
	
	public void read_simulation() throws Exception {
		readXML(filePath, "simulation");
	}
	

}
