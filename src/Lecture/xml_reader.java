package Lecture;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xml_reader {
	
	
	
	public void read(String path_file) throws Exception {
		
		File file = new File(path_file);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(file);
		Element doc = dom.getDocumentElement() ; 
		doc.normalize();
		

        NodeList nList = dom.getElementsByTagName("usine");

		System.out.println("Root Element :" + nList );
		
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
		    Node nNode = nList.item(temp);
		    System.out.println("\nCurrent Element :" + nNode.getNodeName());
		    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        Element eElement = (Element) nNode;
		        System.out.println("Type : " + eElement.getAttribute("type"));
		        System.out.println("Icones : " + eElement.getElementsByTagName("icones").item(0) );
		        
		        
		       
		    }
		} 
	}

		
}
