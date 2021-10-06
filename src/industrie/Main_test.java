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
    XPathExpression usineLink = xpath.compile("/configuration/simulation/chemins/chemin");
    NodeList usinesList = (NodeList) usineLink.evaluate(doc, XPathConstants.NODESET);
    System.out.println("===>: "+usinesList.getLength());
    for (int i = 0; i < usinesList.getLength(); i++) {
    	Element usine = (Element) usinesList.item(i);
    	String type_usine = usine.getAttribute("de") ; 
        System.out.println("==>"+usine.getAttribute("de"));
    }

    /*
    // Find and iterate over VALUE nodes
    XPathExpression valueQuery = xpath.compile("/REPORT/REPORT_DETAIL/RESOURCE/CHARGE/VALUE");
    NodeList values = (NodeList) valueQuery.evaluate(, XPathConstants.NODESET);
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


