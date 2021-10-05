package lecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import usines.Usine;

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
	
	public static Usine getUsine(Node node) {
		return null;
		
	}

}
