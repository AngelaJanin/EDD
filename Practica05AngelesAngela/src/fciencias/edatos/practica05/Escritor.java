package fciencias.edatos.practica05;

import java.io.File;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Escritor{

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
    public void escribe(String nombre, ArrayList<Auto> lista) throws 
    ParserConfigurationException, TransformerConfigurationException, TransformerException{
   
        DocumentBuilderFactory fabricaDeConstructores = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabricaDeConstructores.newDocumentBuilder();
        Document documento = constructor.newDocument();

        // Creación de la raíz y se añade al documento
	    Element raiz = documento.createElement("Automoviles");
	    documento.appendChild(raiz);

	    // Se crean los elementos correspondientes a las etiquetas del xml.
	    Element elemento, propietario, opinion;

	    Auto actual;

	    for(int i = 0; i<lista.size(); i++){
	    	actual = lista.get(i);

	    	// Se crea una etiqueta para el objeto.
	    	elemento = documento.createElement("Auto");
	    	// Se agrega la etiqueta como hijo de la raíz.
	    	raiz.appendChild(elemento);

	    	// Se agrega marca como atributo de Auto
	    	Attr marca = documento.createAttribute("marca");
	    	marca.setValue(actual.marca);
			elemento.setAttributeNode(marca);
			
			// Se agrega modelo como atributo de Auto
	    	Attr modelo = documento.createAttribute("modelo");
	    	modelo.setValue(actual.modelo);
			elemento.setAttributeNode(modelo);
			
			// Si el Auto posee información de su placa, se añade
			if (actual.placas != ""){
				Attr placas = documento.createAttribute("placas");
				placas.setValue(actual.placas);
				elemento.setAttributeNode(placas);
			}

			// Si el Auto posee información de su anio, se añade
			if (actual.anio != ""){
				Attr anio = documento.createAttribute("anio");
				anio.setValue(actual.anio);
				elemento.setAttributeNode(anio);
			}

	    	// Se agrega Propietario a las etiquetas de Auto
	    	propietario = documento.createElement("Propietario");
			elemento.appendChild(propietario);
			
	    	// Se agrega nombre como atributo de Propietario
	    	Attr nombreP = documento.createAttribute("nombre");
	    	nombreP.setValue(""+actual.nombre);
			propietario.setAttributeNode(nombreP);
			
			// Se agrega materno como atributo de Propietario
	    	Attr materno = documento.createAttribute("materno");
	    	materno.setValue(""+actual.materno);
			propietario.setAttributeNode(materno);
			
			// Se agrega paterno como atributo de Propietario
	    	Attr paterno = documento.createAttribute("paterno");
	    	paterno.setValue(""+actual.paterno);
	    	propietario.setAttributeNode(paterno);

	    	// Se agrega Opinion a las etiquetas de Auto
	    	opinion = documento.createElement("Opinion");
	    	// Se añade texto a la etiqueta Opinion
	    	opinion.appendChild(documento.createTextNode(actual.opinion));
	    	elemento.appendChild(opinion);

	    }

	    // Se crean las fábricas para dar formato al archivo
	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    DOMSource source = new DOMSource(documento);
	    StreamResult result = new StreamResult(new File(nombre));

	    // Se asigna un DTD asociado al archivo XML
	    try{
	    	transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Automoviles.dtd");
	    	// Permite indentar el archivo XML
	    	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    	transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    	transformer.transform(source, result);
	    }catch(TransformerConfigurationException tce){
	    }catch(TransformerException te){}
    }
}
