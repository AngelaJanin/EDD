package fciencias.edatos.practicaReposicion;

import java.util.EmptyStackException;
import java.util.Scanner;

/**
* Implementación de un manejador de almacenamiento de cadenas en una pila.
* @author Ángela Janín Ángeles Martínez.
* @version 1.0 Enero 2021.
* @since Estructuras de Datos 2021-1.
*/
public class StringHandler {
    Pila <String> storage = new Pila<>();;
    String stackedWord = "";
    int k = 0;
    
    /**
	* Almacena una cadena de longitud k en la pila.
	* @param word la plabra a almacenar en la pila.
	*/
    public void pushString(String word){
        int size = word.length();
        //Se valida que la longitud de la palabra a insertar sea menor a 255 caracteres.
        if(size > 255){
            System.out.println("\nLa palabra que deseas agregar excede el límite" +
            "de caracteres permitidos. No será almacenada.");
        }else{
            String reversed = new StringBuilder(word).reverse().toString();
            for (int j = 0; j < size; j++){
                this.storage.push(String.valueOf(reversed.charAt(j)));
            }
            this.storage.push(Integer.toString(size));
            System.out.println("\nLa palabra '" + word + "' ha sido agregada con éxito.");
        }
    }

    /**
	* Devuelve un número k que a su vez nos indica que la primer cadena que podemos sacar de la
    * pila es de tamaño k.
	* @return el entero k que pertenece a la longitud de la última cadena almacenada en la pila.
	*/
    public int topString() throws EmptyStackException{
        //Antes de devolver un elemento, se asegura de que la pila no sea vacía.
        if(this.storage.isEmpty()){
            throw new EmptyStackException();
        }else{
            return Integer.parseInt(this.storage.pop());
        }
    }

    /**
	* Devuelve la  ́ultima cadena insertada en la pila.
	* @return la última cadena que fue insertada en la pila.
	*/
    public String popString(){
        this.stackedWord = "";
        this.k = topString();
        for(int i = 0; i < k; i++){
            this.stackedWord = this.stackedWord + this.storage.pop();
        }
        return this.stackedWord;
    }

    /**
	* Auxiliar que nos ayuda a desplegar el menú de acciones disponibles al usuario.
	*/
    private void displayMenu(){
        System.out.println("\nElige una opción:\n" +
        "    1: Agregar una palabra\n" +
        "    2: Eliminar una palabra\n" +
        "    3: Salir del programa\n");
    }
    
    public static void main(String[] args) {
        StringHandler strHandler = new StringHandler();
        XmlHandler xmlHandler = new XmlHandler();
        Scanner str = new Scanner(System.in);

        //Se lee el archivo xml en busca de elementos previamente guardados y se inicializa con ellos la pila.
        try{
            strHandler.storage = xmlHandler.lee();
        } catch(Exception e){
            System.out.println("Ocurrió un error en la lectura del archivo XML");
        }

        //Comienza el flujo de acciones para el usuario.
        System.out.println("¡Bienvenido!");
        strHandler.displayMenu();
        String opcion = str.nextLine();

        while(!opcion.equals("3")){
            if(opcion.equals("1")){
                System.out.println("\nIngresa la palabra que deseas almacenar: ");
                String palabra = str.nextLine();
                strHandler.pushString(palabra);
                strHandler.displayMenu();
                opcion = str.nextLine();  
            }else if(opcion.equals("2")){
                System.out.println("\nLa palabra '" + strHandler.popString() + "' ha sido eliminada con éxito.");
                strHandler.displayMenu();
                opcion = str.nextLine(); 
            }else{
                System.out.println("\n¡Atención! Debes escribir una opción válida del menú.");
                strHandler.displayMenu();
                opcion = str.nextLine(); 
            }
        }

        //Cuando el usuario sale del programa, se escriben sus cambios realizados en el archivo xml fuente.
        try{
            xmlHandler.escribe(strHandler.storage);
            System.out.println("\nEl archivo xml ha sido actualizado correctamente.");
        } catch(Exception e){
            System.out.println("\nOcurrió un error en la escritura del archivo XML");
        }
        
    }
}
