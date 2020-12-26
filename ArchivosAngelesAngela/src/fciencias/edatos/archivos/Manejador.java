package fciencias.edatos.archivos;

import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
* Clase que permite el manejo de archivos de texto.
* @author Emmanuel Cruz Hernández.
* @version 1.0 Septiembre 2020.
* @since Estructuras de datos 2021-1.
*/
public class Manejador{

	/**
	* Crea un nuevo instrumento de cuerda a partir de la lectura de un archivo de texto.
	* @param nombre el nombre del archivo a leer.
	* @return el instrumento de cuerda obtenido del archivo.
	*/
    public InstrumentoDeCuerda leeArchivo(String nombre){
    	try{
    		Reader lector = new FileReader(nombre);
    		BufferedReader l = new BufferedReader(lector);

			String linea = l.readLine();
			
			String[] instrumentoInfo = linea.split(",");

			String nombreI = instrumentoInfo[0];
			int cuerda = Integer.valueOf(instrumentoInfo[1]);
			String duenio = instrumentoInfo[2];

    		InstrumentoDeCuerda nuevo = new InstrumentoDeCuerda(nombreI, cuerda, duenio);

    		return nuevo;

    	} catch(Exception e){
    		System.out.println(e.getMessage());
    	}

    	return null;
    }

    /**
    * Escribe la representación de un instrumento de cuerda en un archivo de texto.
    * @param nombre el nombre con el cual guardar el archivo.
    * @param instrumento el instrumento de cuerda a almacenar representación en un archivo.
    */
    public void escribeArchivo(String nombre, InstrumentoDeCuerda instrumento){
    	try{
    		Writer w = new FileWriter(nombre);
    		BufferedWriter buffer = new BufferedWriter(w);

    		w.write(instrumento.nombre+","+instrumento.cuerdas+","+instrumento.duenio);

    		w.close();
    	} catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }

    public static void main(String[] args) {
    	Manejador m = new Manejador();

    	InstrumentoDeCuerda inst1 = m.leeArchivo("Violin");
    	InstrumentoDeCuerda inst2 = m.leeArchivo("Guitarra");
    	InstrumentoDeCuerda inst3 = m.leeArchivo("GuitarraElectrica");
    	InstrumentoDeCuerda inst4 = m.leeArchivo("Ukelele");

    	System.out.println(inst1);
    	System.out.println(inst2);
    	System.out.println(inst3);
    	System.out.println(inst4);


    }

}
