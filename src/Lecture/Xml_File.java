package Lecture;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	
	public HashMap<String, String> read_metadonnees() throws Exception {
		HashMap<String, String> hasMetadata = new HashMap<String, String>();
		return readXML(filePath, "metadonnees",hasMetadata);
	}
	
	public HashMap<String, String> read_simulation() throws Exception {
		HashMap<String, String> hasMetadata = new HashMap<String, String>();
		return readXML(filePath, "simulation", hasMetadata);
	}
	

}
