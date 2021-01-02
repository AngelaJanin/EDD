package fciencias.edatos.practica06;

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

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ManejadorXML {
    /**
     * Permite escribir un archivo XML.
     * @param nombre el nombre del archivo a escribir.
     * @param estructura la estructura que contiene los elementos a escribir.
     * @throws ParserConfigurationException si ocurre un problema en la configuración
     * del parser.
     * @throws TransformerConfigurationException si ocurre un problema en la configuración
     * del transformador al parser.
     * @throws TransformerException si ocurre un error en el transformador.
     */
    public void escribe(ListaLigada<Ciudad> ciudades) throws 
    ParserConfigurationException, TransformerConfigurationException, TransformerException{
   
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.newDocument();

        // Creación de la raíz y se añade al documento
	    Element raiz = documento.createElement("Directorio");
	    documento.appendChild(raiz);

	    // Se crean los elementos correspondientes a las etiquetas del xml.
	    Element elemento;

	    Ciudad actual;

	    for(int i = 0; i<ciudades.size(); i++){
	    	actual = ciudades.get(i);

	    	// Se crea una etiqueta para el objeto.
	    	elemento = documento.createElement("Ciudad");
	    	// Se agrega la etiqueta como hijo de la raíz.
	    	raiz.appendChild(elemento);

	    	// Se agrega marca como atributo de Ciudad
	    	Attr nombre = documento.createAttribute("nombre");
	    	nombre.setValue(actual.getNombre());
            elemento.setAttributeNode(nombre);
            
            // Se agrega marca como atributo de Ciudad
	    	Attr estado = documento.createAttribute("estado");
	    	estado.setValue(actual.getEstado());
            elemento.setAttributeNode(estado);
            
            // Se agrega marca como atributo de Ciudad
	    	Attr primeraCoord = documento.createAttribute("primeraCoord");
	    	primeraCoord.setValue(String.valueOf(actual.getPrimero()));
            elemento.setAttributeNode(primeraCoord);
            
            // Se agrega marca como atributo de Ciudad
	    	Attr segundaCoord = documento.createAttribute("segundaCoord");
	    	segundaCoord.setValue(String.valueOf(actual.getSegundo()));
			elemento.setAttributeNode(segundaCoord);
	    }

	    // Se crean las fábricas para dar formato al archivo
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(documento);
	    StreamResult result = new StreamResult(new File("Directorio.xml"));

	    // Se asigna un DTD asociado al archivo XML
	    try{
	    	transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Directorio.dtd");
	    	// Permite indentar el archivo XML
	    	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    	transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    	transformer.transform(source, result);
	    }catch(TransformerConfigurationException tce){
	    }catch(TransformerException te){}
	}
	
	public ListaLigada<Ciudad> lee() throws ParserConfigurationException, 
					  SAXException, IOException, DOMException{
        
        // Se realiza la lectura del archivo xml
        File file = new File("Directorio.xml");
        DocumentBuilderFactory constructorFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = constructorFactory.newDocumentBuilder();
        Document document = constructor.parse(file);

        // Se almacenan los elementos con la etiqueta Ciudad del xml en una lista
        NodeList ciudades = document.getElementsByTagName("Ciudad");
        Node nodo;
        
        //Estructura de datos donde se almacenarán los elementos del árbol
        ListaLigada<Ciudad> ciudadesTotal = new ListaLigada<>();
        
        // Creamos los elementos necesarios para las etiquetas
        Element ciudad;

        for(int i = 0; i<ciudades.getLength(); i++){
            nodo = ciudades.item(i);
            ciudad = (Element) nodo;
            String estado = ciudad.getAttribute("estado");
            String nombre = ciudad.getAttribute("nombre");
            String primero = ciudad.getAttribute("primeraCoord");
			String segundo = ciudad.getAttribute("segundaCoord");
			
			Ciudad nuevaCiudad = new Ciudad(nombre, estado, Double.parseDouble(primero), Double.parseDouble(segundo));
			ciudadesTotal.add(0, nuevaCiudad);
		}
		
		return ciudadesTotal;
    }
    
}
