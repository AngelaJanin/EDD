package fciencias.edatos.practica04;

import java.util.Random;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

public class Restaurante{

  Cola <Cliente> clientes;
  ListaLigada<Bitacora> listaBitacora;
  Mesa[] mesas;

  //Constructor de la clase Restaurante
  public Restaurante(Cola <Cliente> clientes, Mesa [] mesas){
    this.clientes = clientes;
    this.mesas = mesas;
    this.listaBitacora = new ListaLigada<>();
  }

  /**
  * Este método toma un nodo de la lista de Clientes del objeto Restaurente
  * para asiganrle una mesa según su cantidad de acompañantes y disponibilidad de las mesas,
  * así como una duración de estancia en la mesa.
  * También almacena los clientes atendidos y no atendido en una lista para la bitácora del día.
	* @param actual nodo de tipo Cliente de la cola de Clientes
	*/
  public boolean atender(Cliente actual){
    for(int i = 0; i < mesas.length; i ++){
      if(mesas[i].getComensales() >= actual.getAcompañantes()){
        Random tiempo = new Random();
        System.out.println("\nEl cliente " + actual.getNombre() + " ocupó la mesa " + mesas[i].getId() + ".");
        int segundos = tiempo.nextInt(10);
        Bitacora nueva = new Bitacora(actual.getNombre(), mesas[i].id, segundos);
        listaBitacora.add(0, nueva);
        try{
          Thread.sleep(segundos * 1000);
          System.out.println ("El cliente " + actual.nombre + " terminó de comer.");
        } catch(Exception e){
          System.out.println ("Ocurrió un error al dormir el hilo");
        }
        return true;
      }
    }
      Bitacora nueva = new Bitacora(actual.getNombre());
      listaBitacora.add(0,nueva);
      return false;
  }

  /**
  * Este método toma la cola de clientes por atender del restaurante
  * para atenderlos hasta que la cola sea vacía
	*/
  public void atiendeLista(){
    while(!clientes.isEmpty()){
      Cliente nuevoCliente = clientes.dequeue();
      atender(nuevoCliente);
    }
    System.out.println("\nTodos los clientes han sido atendidos.");
  }

  /**
  * Este método toma la lista de nodos de tipo Bitacora
  * escribe los resultados en un archivo .txt
  * @param nombreArchivo es el nombre del archivo con el que se guardará la bitácora
	*/
  public void escribeArchivo(String nombreArchivo){
    try{
      Writer w = new FileWriter(nombreArchivo);
      BufferedWriter buffer = new BufferedWriter(w);
      while(!listaBitacora.isEmpty()){
        Bitacora actual = listaBitacora.remove(0);
        w.write(actual + "\n");
      }
      w.close();
    }catch (Exception e){
      System.out.println("Ocurrió un error escribiendo el archivo.");
    }
  }

  public static void main (String[] args){
    //Se solicita la cantidad de clientes
    Scanner scanner = new Scanner(System.in);
    System.out.println("Ingresa la cantidad de mesas disponibles:");
    int mesasDisponibles = scanner.nextInt();

    //Estructuras de datos a utlizar
    Mesa[] arrayMesa = new Mesa[mesasDisponibles];
    Cola <Cliente> clientes = new Cola<> ();
    
    //Se llena la estructura de datos array de tipo Mesa
    for (int i = 0; i < mesasDisponibles; i++){
      System.out.println("Ingresa la capacidad de ocupación de la mesa " + i + ":");
      int capacidad = scanner.nextInt();
      Mesa mesa = new Mesa(i, capacidad);
      arrayMesa[i] = mesa;
    }

    //Se solicita la cantidad de clientes a atender en el día
    System.out.println("\nIngresa la cantidad de clientes:");
    int numClientes = scanner.nextInt();

    //Se llena la estructura de datos cola de tipo Cliente
    for (int i = 0; i < numClientes; i++){
      Scanner input = new Scanner(System.in);
      System.out.println("Ingresa el nombre del cliente " + i + ":");
      String nombre = input.nextLine();
      System.out.println("Ingrese el número de acompañantes del cliente " + nombre + ":");
      int acompañantes = scanner.nextInt();
      Cliente cliente = new Cliente(nombre, acompañantes);
      clientes.enqueue(cliente);
    }

    Restaurante restaurante = new Restaurante(clientes, arrayMesa);
    restaurante.atiendeLista();
    int bitacora = Bitacora.leeEjecucion();
    Bitacora.escribeEjecucion();
    restaurante.escribeArchivo("Bitacora"+String.valueOf(bitacora)+".txt");
  }
}
