package fciencias.edatos.practica05;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;

public class Lector{

    /**
     * Realiza la lectura de un archivo XML.
     * @param nombre el nombre del archivo a leer.
     * @throws ParserConfigurationException si ocurre algún problema
     * @throws IOException si ocurre un problema al abrir el archivo
     * o en la busqueda de este.
     * @throws DOMException si se solicitara información que no existe o
     * no está disponible en las etiquetas del archivo.
     */
    public void readFile(String nombre) throws ParserConfigurationException, 
					  SAXException, IOException, DOMException{
        
        // Se realiza la lectura del archivo xml
        File file = new File(nombre);
        DocumentBuilderFactory constructorFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = constructorFactory.newDocumentBuilder();
        Document document = constructor.parse(file);

        // Se almacenan los elementos con la etiqueta Auto del xml en una lista
        NodeList autos = document.getElementsByTagName("Auto");
        Node nodo;
        
        System.out.println("\n" + "Los automóviles son:" + "\n");

        // Creamos los elementos necesarios para las etiquetas
        Element auto, propietario, opinion;

        // Creamos las listas necesarias para almacenar los elementos creados anteriormente
        NodeList propietarios;
        NodeList opiniones;

        for(int i = 0; i<autos.getLength(); i++){
            nodo = autos.item(i);
            auto = (Element) nodo;

            System.out.println("Marca: "+auto.getAttribute("marca")+
                "\nModelo: "+auto.getAttribute("modelo"));

            if(auto.hasAttribute("placas"))
                System.out.println("Placas: "+auto.getAttribute("placas"));

            if(auto.hasAttribute("anio"))
                System.out.println("Año: "+auto.getAttribute("anio"));

            // Se guardan las etiquetas contenidas en cada etiqueta Auto en listas
            propietarios = auto.getElementsByTagName("Propietario");

            opiniones = auto.getElementsByTagName("Opinion");

            for(int j = 0; j<propietarios.getLength(); j++){
                propietario = (Element) propietarios.item(j);
                System.out.println("    - Nombre: "+ propietario.getAttribute("nombre"));
                System.out.println("    - Materno: "+ propietario.getAttribute("materno"));
                System.out.println("    - Paterno: "+ propietario.getAttribute("paterno"));
            }

            for(int j = 0; j<opiniones.getLength(); j++){
                opinion = (Element) opiniones.item(j);
                System.out.println("    - Opinion: "+ opinion.getTextContent());   
            }

            System.out.println(); 
        }
    }
}
