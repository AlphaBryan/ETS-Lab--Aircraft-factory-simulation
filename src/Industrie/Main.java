package Industrie;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Lecture.Xml_File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream; 

public class Main {



public static void main(String[] args) throws Exception {
	
	String filePath = "./src/ressources/configuration.xml" ;
	
	Xml_File myXML = new Xml_File(filePath) ;
	/*
	 * System.out.println("================= Go readMetaDAta");
	 * myXML.read_metadonnees();
	 * System.out.println("================= OFF readMetaDAta");
	 */

	System.out.println("XXXXXXXXXXXXXXXXXXXXXX");
	
	System.out.println("================= Go simulation");
	myXML.read_simulation();
	System.out.println("================= OFF simulation");
  }


}