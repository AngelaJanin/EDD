package fciencias.edatos.practica03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class Archivo {
    static ListaLigada<Empleado> catalogo = new ListaLigada<>();

    /**
     * Realiza la lectura de un archivo txt que funciona como catálogo de empleados.
     * Separa los atributos de cada empleado para posteriormente crear
     * instancias de tipo Empleado.
     * Inserta todos los empleados en una lista simplemente ligada. 
     */
    static public ListaLigada<Empleado> leerCatalogo(){
        try {
            FileReader fileReader = new FileReader("Empleados.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Empleado empleado = new Empleado(Integer.parseInt(data[0]), Integer.parseInt(data[1]), 
                data[2], data[3], data[4], data[5]);
                catalogo.add(0,empleado);
            }
            bufferedReader.close();
            return catalogo;
        } catch (Exception e) {
            System.out.println("Ocurrió un error leyendo el catálogo de empleados");
            return catalogo;
        }
    }

    /**
     * Permite escribir el catálogo de empleados desde la lista de empleados a un archivo txt.
     * @param catalogo Es la lista simplemente ligada que en sus nodos almacena 
     * instancias de la clase Empleado.
     */
    static public void escribeCatalogo(ListaLigada<Empleado> catalogo){
        try{
          Writer w = new FileWriter("Empleados.txt");
          BufferedWriter buffer = new BufferedWriter(w);
          for(int i = 0; i < catalogo.size(); i++){
            w.write(catalogo.get(i) + "\n");
          }
          w.close();
        }catch (Exception e){
          System.out.println("Ocurrió un error escribiendo el catalógo");
        }
    }
}
