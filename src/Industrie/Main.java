package Industrie;

import java.awt.AWTException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Lecture.xml_reader;
import composants.Composant;
import usines.Usine_matiere;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		System.out.println("Hello");
		
		Composant metal1 = new Composant("metal", null, 0, 0) ; 
		Composant aile1 = new Composant("aile", null, 0, 0) ; 

		
		Usine_matiere u_matiere1 = new Usine_matiere(0, 0, 0, metal1, 0, aile1, 0) ; 
		
		u_matiere1.isProducing();
		
		xml_reader xml = new xml_reader(); 
		
		xml.read("./src/ressources/configuration.xml");
	}

}
