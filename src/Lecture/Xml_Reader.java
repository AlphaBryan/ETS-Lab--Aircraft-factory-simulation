package Lecture;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml_Reader {
	

	
	public static void readXML(String filePath, String myPrincipaleNode ) throws Exception {
		

	      // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try (InputStream is = new FileInputStream(new File(filePath))){

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          // read from a project's resources folder
	          Document doc = db.parse(is);
	          
	    	  String myRoot =  doc.getDocumentElement().getNodeName() ;
	          System.out.println("Notre Element Root :" + myRoot);

	    	  
	          if (doc.hasChildNodes()) {
	              printNode(doc.getChildNodes(),false, myRoot, myPrincipaleNode);
	          }

	      } catch (ParserConfigurationException | SAXException | IOException e) {

	          e.printStackTrace();
	      }

	}
	
	private static void printNode(NodeList nodeList, boolean start, String myRoot, String myPrincipaleNode ) {

			//  boolean start= false ;
			  boolean stop = false ;

		      for (int count = 0; count < nodeList.getLength() && stop != true ; count++) {

		          Node tempNode = nodeList.item(count);
		                    
		          if(tempNode.getNodeName() == myPrincipaleNode) {
		        	  System.out.println("started");
		        	  start = true ;
		          }

		          // make sure it's element node.
		          if (tempNode.getNodeType() == Node.ELEMENT_NODE  && start==true || tempNode.getNodeType() == Node.ELEMENT_NODE && tempNode.getNodeName() == myRoot ) {
		        	  
		                // get node name and value
		              System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		              //System.out.println("Node Value =" + tempNode.getTextContent());

		//Prendre les infos 
		              if (tempNode.hasAttributes()) {
		                  NamedNodeMap nodeMap = tempNode.getAttributes();
		                  for (int i = 0; i < nodeMap.getLength(); i++) {
		                      Node node = nodeMap.item(i);
		                      System.out.println("attr name : " + node.getNodeName());
		                      System.out.println("attr value : " + node.getNodeValue());
		                  }
		              }
		//Recursion
		              if (tempNode.hasChildNodes()) {
		                  // loop again if has child nodes
		                  printNode(tempNode.getChildNodes(),start, myRoot, myPrincipaleNode);
		              }

		              if(tempNode.getNodeName() == myPrincipaleNode) {
		            	  System.out.println("stoped");
		            	  stop = true ;
		              }
		              System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

		          }

		      }

		  }

		
}
