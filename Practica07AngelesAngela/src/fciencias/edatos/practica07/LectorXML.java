package fciencias.edatos.practica07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LectorXML {
	
	public ChainHashMap<String,Elemento> lee() throws ParserConfigurationException, 
					  SAXException, IOException, DOMException{
        
        // Se realiza la lectura del archivo xml
        File file = new File("tabla-periodica.xml");
        DocumentBuilderFactory constructorFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = constructorFactory.newDocumentBuilder();
        Document document = constructor.parse(file);
        
        // Se almacenan los elementos con la etiqueta elemento del xml en una lista
        NodeList xmlElementos = document.getElementsByTagName("elemento");
        Node nodo;
        
        
        //Estructura de datos donde se almacenarán los elementos hashmap
        ChainHashMap<String,Elemento> elementos = new ChainHashMap<>();
        
        // Creamos los elementos necesarios para las etiquetas
        Element elem;

        for(int i = 0; i<xmlElementos.getLength(); i++){
            nodo = xmlElementos.item(i);
            elem = (Element) nodo;
			
            Elemento nuevoElemento = new Elemento(elem.getAttribute("nombre"),elem.getAttribute("simbolo"),
            Integer.parseInt(elem.getAttribute("numero")),Double.parseDouble(elem.getAttribute("masa")));
            
            elementos.put(nuevoElemento.getSimbolo(), nuevoElemento);
        } 

        return elementos;

    }
}