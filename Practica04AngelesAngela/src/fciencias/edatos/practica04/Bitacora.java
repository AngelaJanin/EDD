package fciencias.edatos.practica04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.util.Scanner;
import java.io.FileWriter;

public class Bitacora {
  String nombreCliente;
  boolean atendido;
  int idMesa;
  int permanencia;

  //Constructor de la clase Bitacora
  public Bitacora(String nombreCliente, int idMesa, int permanencia) {
    this.nombreCliente = nombreCliente;
    this.atendido = true;
    this.idMesa = idMesa;
    this.permanencia = permanencia;
  }

  //Constructor de la clase Bitacora
  public Bitacora(String nombreCliente){
    this.nombreCliente = nombreCliente;
    this.atendido = false;
  }

  /**
	* Este método lee el numero de ejecución almacenado en el archivo Ejecucion.txt,
  * es un auxiliar para escribir el nombre de la Bitácora considerando el 
  * día (el número de ejecución).
	*/
  public static int leeEjecucion(){
    try {
      File file = new File("Ejecucion.txt");
      Scanner reader = new Scanner(file);
      String data = reader.nextLine();
      reader.close();
      return Integer.parseInt(data);
    
    } catch (Exception e) {
      System.out.println("Ocurrió un error leyendo el archivo.");
      return -1;
    }
  }

  /**
  * Este método actualiza el día (numero de ejecución) almacenado 
  * en el archivo Ejecucion.txt.
  * Es un auxiliar para escribir el nombre de la Bitácora considerando el 
  * día (el número de ejecución).
	*/
  public static void escribeEjecucion(){
    try{
      int counter = leeEjecucion() + 1;
      Writer w = new FileWriter("Ejecucion.txt");
      BufferedWriter buffer = new BufferedWriter(w);
      w.write(String.valueOf(counter));
      w.close();
    }catch (Exception e){
      System.out.println("Ocurrió un error escribiendo el archivo.");
    }
  }

  public String toString(){
    if(atendido){
      return "El cliente " + nombreCliente + " fue atendido en la mesa " + idMesa + " durante " + permanencia + " minutos.";
    } else {
      return "El cliente " + nombreCliente + " no fue atendido.";
    }
  }
}
