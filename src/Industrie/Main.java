package Industrie;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Lecture.Xml_File;
import resaux.Chemin;
import resaux.Noeud;
import usines.Usine;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		//String filePath = "src/ressources/configuration.xml"; //Machine ecole 
		String filePath = "src/ressources/configuration.xml"; //Ma machine  
		
		Xml_File myXML = new Xml_File(filePath);

		System.out.println("================= Go readMetaDAta");
		ArrayList<Usine> list_usine = myXML.read_metadonnees();
		System.out.println("result liste usine => : " + list_usine);
		System.out.println("================= OFF readMetaDAta");

		System.out.println("XXXXXXXXXXXXXXXXXXXXXX");

		/*
		 * for (String name: hashMetaData.keySet()) { String key = name.toString();
		 * String value = hashMetaData.get(name).toString(); System.out.println(key +
		 * " " + value); } System.out.println(hashMetaData);
		 */

		System.out.println("================= Go simulation for usine ");
		Map<Integer, Noeud> usine_map = myXML.read_simulation_usine(list_usine);
		System.out.println("result usines_simu => : " + usine_map);
		System.out.println("================= OFF simulation for usine");

		System.out.println("XXXXXXXXXXXXXXXXXXXXXX");

		System.out.println("================= Go simulation for Chemin ");
		ArrayList<Chemin> chemins = myXML.read_simulation_chemin(usine_map);
		System.out.println("result chemins => : " + chemins);
		System.out.println("================= OFF simulation for Chemin");

	}

}
