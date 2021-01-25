package fciencias.edatos.practicaReposicion;

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

/**
* Implementación de un lector y escritor de archivos xml.
* @author Ángela Janín Ángeles Martínez.
* @version 1.0 Enero 2021.
* @since Estructuras de Datos 2021-1.
*/
public class XmlHandler {
	
	/**
	* Crea un archivo xml con los elementos encontrados en la pila que recibe como parámetro.	
	* @param storage la pila de donde obtendrá las palabras a escribir.
	*/
	public void escribe(Pila<String> storage) throws ParserConfigurationException, TransformerConfigurationException, 
								 TransformerException{
   
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.newDocument();

        // Creación de la raíz y se añade al documento
	    Element raiz = documento.createElement("Storage");
	    documento.appendChild(raiz);

	    // Se crean los elementos correspondientes a las etiquetas del xml.
	    Element elemento;

		String wordSize;

		//Mientras la pila no esté vacía, tratará de leer cada entero k y 
		//obtendrá la palabra consecuente a éste.

	    while(!storage.isEmpty()){
			
			wordSize = storage.pop();

			// Se crea una etiqueta para el objeto.
	    	elemento = documento.createElement("Item");
			
			// Se agrega la etiqueta como hijo de la raíz.
	    	raiz.appendChild(elemento);

	    	// Se agrega size como atributo de Item
			Attr size = documento.createAttribute("size");
			
	    	size.setValue(wordSize);
            elemento.setAttributeNode(size);
			
			//Una vez obtenido el entero k, se realiza pila.pop() k veces para
			//obtener la palabra completa
			String stackedWord = "";
			for(int i = 0; i < Integer.parseInt(wordSize); i++){
				stackedWord = stackedWord + storage.pop();
			}

            // Se agrega word como atributo de Item
	    	Attr word = documento.createAttribute("word");
	    	word.setValue(stackedWord);
            elemento.setAttributeNode(word);
		}

	    // Se crean las fábricas para dar formato al archivo
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(documento);
	    StreamResult result = new StreamResult(new File("Storage.xml"));

	    // Se asigna un DTD asociado al archivo XML
	    try{
	    	transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Storage.dtd");
	    	// Permite indentar el archivo XML
	    	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    	transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    	transformer.transform(source, result);
	    }catch(TransformerConfigurationException tce){
	    }catch(TransformerException te){}
	}
	
	/**
	* Lee los elementos contenidos en un archivo xml fuente para llenar la pila que
	* almacena cadenas, si es que existen éstos.
	* @return Devuelve la pila storage con los elementos encontrados en el archivo xml.
	*/
	public Pila<String> lee() throws ParserConfigurationException, 
					  SAXException, IOException, DOMException{
        
        // Se realiza la lectura del archivo xml
        File file = new File("Storage.xml");
        DocumentBuilderFactory constructorFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = constructorFactory.newDocumentBuilder();
        Document document = constructor.parse(file);

        // Se almacenan los elementos con la etiqueta Item del xml en una lista
        NodeList items = document.getElementsByTagName("Item");
        Node nodo;
        
        //Estructura de datos donde se almacenarán las palabras
        Pila<String> storage = new Pila<>();
        
        // Creamos los elementos necesarios para las etiquetas
        Element item;

        for(int i = 0; i<items.getLength(); i++){
            nodo = items.item(i);
			item = (Element) nodo;
			if(Integer.parseInt(item.getAttribute("size")) > 255){
				System.out.println("La palabra '" + item.getAttribute("word") +
				"' excede el límite de caracteres permitidos. No será almacenada.");
			}else{
				String reversed = new StringBuilder(item.getAttribute("word")).reverse().toString();
				for (int j = 0; j < item.getAttribute("word").length(); j++) {
					storage.push(String.valueOf(reversed.charAt(j)));
				}
				storage.push(item.getAttribute("size"));
			}
		}
		return storage;
	}        
}
