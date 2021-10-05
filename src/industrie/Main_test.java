package industrie;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import lecture.Xml_File;
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

public class Main_test {



public static void main(String[] args) throws Exception {
	
	String filePath = "../src/ressources/configuration.xml" ;
	
	// BEGIN: DOM Boilerplate
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new File(filePath));
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    // END: DOM Boilerplate

    // Find and iterate over CHARGE_SUMMARY nodes
    XPathExpression usineLink = xpath.compile("/configuration/metadonnees/usine");
    NodeList usinesList = (NodeList) usineLink.evaluate(doc, XPathConstants.NODESET);
    for (int i = 0; i < usinesList.getLength(); i++) {
    	Element usine = (Element) usinesList.item(i);
        System.out.println("==>"+usine.getAttribute("type"));
    	Usine new_usine = new Usine("g") ; //methode checktype
    	
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

        
        System.out.println("  **Object ==>"+ new_usine ) ;
      

    }

    /*
    // Find and iterate over VALUE nodes
    XPathExpression valueQuery = xpath.compile("/REPORT/REPORT_DETAIL/RESOURCE/CHARGE/VALUE");
    NodeList values = (NodeList) valueQuery.evaluate(doc, XPathConstants.NODESET);
    String lastResourceName = null;
    for (int i = 0; i < values.getLength(); i++) {
        Element value = (Element) values.item(i);

        String resourceName = ((Element) value.getParentNode().getParentNode()).getAttribute("name");

        if (!resourceName.equals(lastResourceName)) {
            lastResourceName = resourceName;
            System.out.println();
        }

        System.out.printf("RESOURCE name=\"%s\" CHARGE name=\"%s\" VALUE amount=\"%s\" datetime=\"%s\"%n",
                resourceName,
                ((Element) value.getParentNode()).getAttribute("name"),
                value.getAttribute("amount"),
                value.getAttribute("datetime"));
    }
*/





	} 

}


