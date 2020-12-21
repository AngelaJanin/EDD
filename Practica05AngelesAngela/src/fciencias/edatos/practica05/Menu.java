package fciencias.edatos.practica05;

import java.util.Scanner;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class Menu {
    public static void main(String[] args) throws ParserConfigurationException,
    SAXException, IOException, DOMException {
        
        System.out.println("Escribe 1 para leer un archivo"+ "\n" + "Escribe 2 para escribir un archivo");
            try{
                Scanner in = new Scanner(System.in);
                int opcion = in.nextInt();
            if(opcion == 1){
                System.out.println("Escribe el nombre del archivo que deseas leer," +
                                    " NO olvides escribir el formato '.xml' al final");
                
                Scanner inp = new Scanner(System.in);
                String file = inp.nextLine();
                try {
                    Lector lector = new Lector();
                    lector.readFile(file);
                } catch (Exception e){
                    System.out.println("Error! Por favor escribe un nombre de archivo válido");
                }
            }else{
                System.out.println("¿Cuántos elementos deseas escribir?");
                Scanner input = new Scanner(System.in);
                int cantidad = input.nextInt();
                ArrayList<Auto> lista = new ArrayList<>();
                Scanner inpu = new Scanner(System.in);
                
                System.out.print("** Presiona ENTER en los campos 'placas' y 'anio' si tu auto no los tiene **" + "\n");
                
                for(int i = 0; i < cantidad; i ++){
                    System.out.println("\n" + "Marca del auto: ");
                    String marca = inpu.nextLine();
                    System.out.println("Modelo del auto: ");
                    String modelo = inpu.nextLine();
                    System.out.println("Placas del auto: ");
                    String placas = inpu.nextLine();
                    System.out.println("Anio del auto: ");
                    String anio = inpu.nextLine();
                    System.out.println("Nombre del propietario: ");
                    String nombre = inpu.nextLine();
                    System.out.println("Apellido materno del propietario: ");
                    String materno = inpu.nextLine();
                    System.out.println("Apellido paterno del propietario: ");
                    String paterno = inpu.nextLine();
                    System.out.println("Opinion del auto: ");
                    String opinion = inpu.nextLine();

                    if (placas.length() == 0) placas = "";
                    if (anio.length() == 0) anio = "";

                    Auto auto = new Auto(marca,modelo,placas,anio,nombre,materno,paterno,opinion);
                    lista.add(auto);
                }

                Escritor escritor = new Escritor();

                try{
                    escritor.escribe("Autos.xml", lista);
                    System.out.println("\n" + "Archivo 'Autos.xml' creado");
                } catch(Exception e){
                    System.out.println("Ocurrió un error en la escritura del archivo XML"); 
                }
            } 
        } catch (Exception e) {
            System.out.println("Escribe un valor numérico (1 o 2)");
        }
    }
}
