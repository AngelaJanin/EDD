package fciencias.edatos.practica06;

import java.util.Scanner;

public class Directorio {
    ArbolBinarioBusqueda<Ciudad> directorio;
    ListaLigada<Ciudad> ciudades;
    
    //Constructor de la clase Directorio
    public Directorio(){
        this.directorio = new ArbolBinarioBusqueda<>();
        this.ciudades = new ListaLigada<>();
    }

    /**
	* Agrega una ciudad al árbol.
	* @param ciudad la ciudad que se desea agregar.
    * @return actualiza la lista de ciudades a partir
    * del recorrido post order de los nodos.
	*/
    public ListaLigada<Ciudad> agregarCiudad(Ciudad ciudad){
        directorio.insert(ciudad, ciudad.getNombre());
        ciudades = directorio.postOrder();
        return ciudades;
    }

    /**
	* Elimina una ciudad del árbol.
	* @param nombre clave de la ciudad que se desea eliminar.
    * @return actualiza la lista de ciudades a partir
    * del recorrido post order de los nodos.
	*/
    public ListaLigada<Ciudad> eliminarCiudad(String nombre){
        directorio.delete(nombre);
        ciudades = directorio.postOrder();
        return ciudades;
    }

    /**
	* Imprime en consola la información de una ciudad contenida en el directorio.
	* @param nombre clave de la ciudad que se desea mostrar.
	*/
    public void mostrarCiudad(String nombre){
        Ciudad ciudad = directorio.retrieve(nombre);
        System.out.println("    - Ciudad: "+ ciudad.getNombre());
        System.out.println("    - Estado de la República: "+ ciudad.getEstado());
        System.out.println("    - Coordenadas: "+ ciudad.getCoordenadas()+"\n");
    }

    /**
    * Imprime en consola la información de las ciudades
    * encontradas dentro de un rango dado por el usuario.
    * @param primero primera coordenada del rango.
	* @param segundo segunda coordenada del rango.
	*/
    public void obtenerPorRango(Double primero, Double segundo){
        ciudades = directorio.postOrder();
        for(int i = 0; i < ciudades.size(); i++){
            if(ciudades.get(i).getPrimero() >= primero && ciudades.get(i).getSegundo() <= segundo){
                System.out.println("    - Ciudad: "+ ciudades.get(i).getNombre());
                System.out.println("    - Estado de la República: "+ ciudades.get(i).getEstado());
                System.out.println("    - Coordenadas: "+ ciudades.get(i).getCoordenadas()+"\n");
            }
        }
    }

    //Auxiliar que imprime las opciones del usuario en consola
    private void menu(){
        System.out.println("Elige una opción:" + "\n" +
        "    1: Agregar una ciudad al directorio"+ "\n" +
        "    2: Eliminar una ciudad del directorio"+ "\n" +
        "    3: Muestra la información de una ciudad"+ "\n" +
        "    4: Muestra las ciudades dentro de un rango"+ "\n" +
        "    5: Terminar programa"+ "\n");
    }

    public static void main(String[] args) {
        Directorio directorio = new Directorio();
        ManejadorXML manejador = new ManejadorXML();
        Scanner str = new Scanner(System.in);

        //Leemos los elementos del directorio desde el xml
        try{
            directorio.ciudades = manejador.lee();
            for(int i = 0; i < directorio.ciudades.size(); i++){
                directorio.directorio.insert(directorio.ciudades.get(i), directorio.ciudades.get(i).getNombre());
            }  
        } catch(Exception e){
            System.out.println("Ocurrió un error en la lectura del archivo XML"); 
        }

        directorio.menu();
        String opcion = str.nextLine();
        
        while(!opcion.equals("5")){
            if(opcion.equals("1")){
                System.out.println("Ingresa la información de la ciudad:");
                System.out.println("\n" + "Nombre de la ciudad: ");
                String nombre = str.nextLine();
                System.out.println("Estado de la República: ");
                String estado = str.nextLine();
                System.out.println("Primera coordenada (ej: 19.79916): ");
                double primero = Double.parseDouble(str.nextLine());
                System.out.println("Segunda coordenada (ej: -123.47140): ");
                double segundo = Double.parseDouble(str.nextLine());

                Ciudad nueva = new Ciudad(nombre, estado, primero, segundo);
                directorio.ciudades = directorio.agregarCiudad(nueva);
                
                try{
                    manejador.escribe(directorio.ciudades);               
                    System.out.println("\n    ¡Añadido con éxito! \n");   
                } catch(Exception e){
                    System.out.println("Ocurrió un error en la escritura del archivo XML"); 
                }

                directorio.menu();
                opcion = str.nextLine();    
            } else if (opcion.equals("2")) {
                System.out.println("Ingresa el nombre de la ciudad a eliminar:");
                String eliminado = str.nextLine();
                directorio.ciudades = directorio.eliminarCiudad(eliminado);

                try{
                    manejador.escribe(directorio.ciudades);
                    System.out.println("\n    ¡Eliminado con éxito! \n");  
                } catch(Exception e){
                    System.out.println("Ocurrió un error en la escritura del archivo XML"); 
                }

                directorio.menu();
                opcion = str.nextLine(); 
            } else if (opcion.equals("3")) {
                System.out.println("Ingresa el nombre de la ciudad que deseas ver:");
                String buscado = str.nextLine();
                directorio.mostrarCiudad(buscado);

                directorio.menu();
                opcion = str.nextLine(); 
            } else if (opcion.equals("4")) {
                System.out.println("Ingresa el rango por el que deseas buscar:");
                System.out.println("Primera coordenada (ej: 19.79916): ");
                double primero = Double.parseDouble(str.nextLine());
                System.out.println("Segunda coordenada (ej: -123.47140): \n");
                double segundo = Double.parseDouble(str.nextLine());
                directorio.obtenerPorRango(primero, segundo);

                directorio.menu();
                opcion = str.nextLine(); 
            }
        }
    }
}
