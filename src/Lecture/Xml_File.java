package lecture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import usines.Usine_aile;
import usines.Usine_assemblage;
import usines.Usine_entrepot;
import usines.Usine_matiere;
import usines.Usine_moteur;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import lecture.Xml_File;
import resaux.Chemin;
import resaux.Noeud;
import usines.Usine;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap; 

public class Xml_File {
		
	String filePath ;
	Document doc ;
	XPath xPath ; 
		
	public Xml_File(String filePath) throws Exception {
		super();
		this.filePath = filePath;
		parseXML(); 
		if(xPath==null) {
			System.out.println("Bad Path for xml return null");
		}
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
		
	public ArrayList<Usine> read_metadonnees() throws Exception {
		ArrayList<Usine> data_usines = new ArrayList<Usine>() ; 

		XPathExpression usineLink = this.xPath.compile("/configuration/metadonnees/usine");
	    NodeList usinesList = (NodeList) usineLink.evaluate(doc, XPathConstants.NODESET);
	    for (int i = 0; i < usinesList.getLength(); i++) {
	    	Element usine = (Element) usinesList.item(i);
	    	String type_usine = usine.getAttribute("type") ; 
	        //System.out.println("==>"+usine.getAttribute("type"));
	    	Usine new_usine = checkUsineType(type_usine) ; //methode checktype
	    	
	    	//GET ICONES
	        Element icones = (Element) usine.getElementsByTagName("icones").item(0) ;
	        NodeList iconesList = icones.getElementsByTagName("icone") ;
	        for (int j = 0 ; j< iconesList.getLength(); j++) {
	            Element icone = (Element) iconesList.item(j);
	            //System.out.println("==>"+icone.getAttribute("path"));
	            new_usine.addIcone(icone.getAttribute("path").toString() );
	        }
	        
	        //GET INPUT 
	        NodeList inputList = usine.getElementsByTagName("entree") ;
	        if(inputList.getLength()>0) {
	            for (int k=0 ; k< inputList.getLength() ; k++ ) {
	            	Element input = (Element) inputList.item(k) ; 
	            	String type = input.getAttribute("type") ;
	            	int quantite = -1 ; 
	            	if(type.equals("avion")) { quantite = Integer.parseInt(input.getAttribute("capacite")) ; }
	            	else { quantite = Integer.parseInt(input.getAttribute("quantite")) ; }
	            	new_usine.addEnter_product_type(type, quantite);
	            }
	        }


	        //GET OUTPUT 
	        Element output = (Element) usine.getElementsByTagName("sortie").item(0) ;
	        if (output != null) {
	        	String output_type = output.getAttribute("type") ; 
	        	new_usine.setOutput_product_type(output_type);
	    	}


	        //GET INTERVAL PROD
	        Node interval_prod = usine.getElementsByTagName("interval-production").item(0) ; 
	        if(interval_prod != null) {
	        	int interval_value = Integer.parseInt( interval_prod.getChildNodes().item(0).getNodeValue() ) ; 
	        	new_usine.setInterval_production(interval_value);
	        }

	        //System.out.println("  **Object ==>"+ new_usine ) ;
	        
	        data_usines.add(new_usine) ; 

	    }
		
		return data_usines;
	}
	
	public Map<Integer, Noeud> read_simulation_usine(ArrayList<Usine> usines) throws Exception {
		Map<Integer, Noeud> data_usines = new HashMap<Integer, Noeud>() ;
		
		XPathExpression usineLink = this.xPath.compile("/configuration/simulation/usine");
	    NodeList usinesList = (NodeList) usineLink.evaluate(doc, XPathConstants.NODESET);
	    for (int i = 0; i < usinesList.getLength(); i++) {
	    	Element usine = (Element) usinesList.item(i);
	    	
	    	String type_usine = usine.getAttribute("type") ; 
	    	int id_usine = Integer.parseInt( usine.getAttribute("id") )  ; 
	    	int x_usine = Integer.parseInt( usine.getAttribute("x") ) ; 
	    	int y_usine = Integer.parseInt( usine.getAttribute("y") ) ; 
	    	
	    	//System.out.println("["+id_usine +", "+x_usine + ", "+y_usine + " ]" );
	    		        
	    	Noeud usine_node = null ; 
			for(Usine iUsine : usines) {
				if(iUsine.getType().equals(type_usine)) {
					usine_node = new Noeud(iUsine); 
				}
			}
			
			usine_node.setId(id_usine);
			int[] position_table = {x_usine, y_usine} ; 
			usine_node.setPosition(position_table);
			data_usines.put(usine_node.getId(),usine_node) ; 
	    }
		
		return data_usines;
	}
	
	public ArrayList<Chemin> read_simulation_chemin(Map<Integer, Noeud> usine_map) throws Exception {
		ArrayList<Chemin> data_chemins = new ArrayList<Chemin>() ; 
	    XPathExpression cheminsExpression = this.xPath.compile("/configuration/simulation/chemins/chemin");
	    NodeList chemins = (NodeList) cheminsExpression.evaluate(doc, XPathConstants.NODESET);

	    for (int i = 0; i < chemins.getLength(); i++) {
	    	Element cheminElmt = (Element) chemins.item(i);
	    	int enter = Integer.parseInt(cheminElmt.getAttribute("de"))   ; 
	        int exit = Integer.parseInt(cheminElmt.getAttribute("vers"))   ; 	        
	        Chemin chemin = new Chemin(enter, exit); 
	        chemin.calcul_Longueur(usine_map);
	        //System.out.println("==> : "+chemin);
	        
	        data_chemins.add(chemin) ;
	    }
		
		return data_chemins;
	}
	
	public void parseXML() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder;
	    builder = factory.newDocumentBuilder();
		this.doc = builder.parse(new File(this.filePath));
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    this.xPath = xPathfactory.newXPath();
	}

	public Usine checkUsineType(String type) {

		Usine myUsine = new Usine(type) ; 
		if(type.equals("usine-moteur")) {
			myUsine = new Usine_moteur(type);
		}
		else if(type.equals("usine-matiere")) {
			myUsine = new Usine_matiere(type);
		}
		else if(type.equals("entrepot")) {
			myUsine = new Usine_entrepot(type);
		}
		else if(type.equals("usine-assemblage")) {
			myUsine = new Usine_assemblage(type);
		}
		else if(type.equals("usine-aile")) {
			myUsine = new Usine_aile(type);
		}
		
		return myUsine ; 
	}

}
