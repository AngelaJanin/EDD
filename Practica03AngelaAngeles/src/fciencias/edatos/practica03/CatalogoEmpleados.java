package fciencias.edatos.practica03;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CatalogoEmpleados {
    ListaLigada<Empleado> catalogo = new ListaLigada<>();

    //Constructor de la clase Catalogo Empleados
    public CatalogoEmpleados(ListaLigada<Empleado> catalogo){
        this.catalogo = catalogo;
    }
    
     /**
     * Permite eliminar un empleado del catálogo.
     * @param id Es el atributo ID que posee un empleado, por medio de éste
     * será identificado entre los nodos de la lista simplemente ligada.
     */
    public void darBaja(int id){
        for(int i = 0; i < catalogo.size(); i++){
            if(catalogo.get(i).getId() == id){
                catalogo.remove(i);
            }
        }
    }

    /**
     * Permite agregar un empleado al catálogo.
     * @param nuevoEmpleado Es la instancia de tipo Empleado que fue
     * construida a partir de los atributos solicitados por medio de la terminal 
     * al usuario.
     */
    public void darAlta(Empleado nuevoEmpleado){
        catalogo.add(0, nuevoEmpleado);
    }

    /**
     * Permite filtrar los empleados que cumplen con tener un sueldo no mayor 
     * al parámetro sueldo y con tener el puesto definido en los parámetros.
     * @param puesto Puesto que deben tener los empleados a filtrar
     * @param sueldo Sueldo que deben tener los empleados a filtar
     */
    public ListaLigada<Empleado> filtraPuestoSueldo(String puesto, int sueldo){
        ListaLigada<Empleado> filtrados = new ListaLigada<>();
        for(int i = 0; i < catalogo.size(); i++){
            if(catalogo.get(i).getPuesto().equals(puesto) && catalogo.get(i).getSueldo() <= sueldo){
                filtrados.add(0,catalogo.get(i));
            }
        }
        return filtrados;
    }

    /**
     * Permite aumentar el sueldo de los empleados con una fecha de 
     * nacimiento mayor a la definida en los parámetros.
     * @param fechaNacimiento fecha de nacimiento a partir de la cuál 
     * se comenzará a subir el sueldo por mes
     */
    public void aumentaSueldo(String fechaNacimiento){
        Date fecha = convierteFecha(fechaNacimiento);
        for(int i = 0; i < catalogo.size(); i++){
            Date empleado = convierteFecha(catalogo.get(i).getNacimiento());
            if(empleado.after(fecha)){
                catalogo.get(i).setSueldo(catalogo.get(i).getSueldo() + 1000);
            }
        }
    }
    
    /**
     * Método auxiliar que permite convertir una fecha de tipo
     * String a tipo Date.
     * @param fecha fecha en formato (dd/mm/yyyy) que se desea convertir
     */
    public Date convierteFecha(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (Exception e) {
            System.out.println("Ocurrió un error leyendo la fecha de nacimiento");
        }
        return fechaDate;
    }

    public static void main(String[] args) {
        int opcion = 0;
        CatalogoEmpleados nuevaLista = new CatalogoEmpleados(Archivo.leerCatalogo());

        System.out.println("Escribe un número" + "\n" +
                            "1: Ver los empleados del catálogo"+ "\n" +
                            "2: Añadir un empleado"+ "\n" +
                            "3: Eliminar un empleado"+ "\n" +
                            "4: Buscar empleados por sueldo y puesto"+ "\n" +
                            "5: Aumentar sueldo por fecha de nacimiento"+ "\n");
        try{
            Scanner in = new Scanner(System.in);
            opcion = in.nextInt();
            if(opcion >= 6) System.out.println("Escribe una opción válida");  
        } catch (Exception e) {
            System.out.println("Escribe un valor numérico válido");
        }

        if(opcion == 1){
            for(int i = 0; i < nuevaLista.catalogo.size(); i++){
                System.out.println("    - ID: "+ nuevaLista.catalogo.get(i).getId());
                System.out.println("    - Nombre: "+ nuevaLista.catalogo.get(i).getNombre());
                System.out.println("    - Fecha nacimiento: "+ nuevaLista.catalogo.get(i).getNacimiento());
                System.out.println("    - RFC: "+ nuevaLista.catalogo.get(i).getRfc());
                System.out.println("    - Sueldo: "+ nuevaLista.catalogo.get(i).getSueldo());
                System.out.println("    - Puesto: "+ nuevaLista.catalogo.get(i).getPuesto() + "\n");
            }
        } else if (opcion == 2) {
            System.out.println("Ingresa los datos del empleado" + "\n");
            Scanner str = new Scanner(System.in);
            Scanner num = new Scanner(System.in);

            System.out.println("\n" + "Nombre: ");
            String nombre = str.nextLine();
            System.out.println("ID (escribe digitos): ");
            int id = num.nextInt();
            System.out.println("Fecha de nacimiento (dd/mm/yyyy): ");
            String fechaNacimiento = str.nextLine();
            System.out.println("RFC: ");
            String rfc = str.nextLine();
            System.out.println("Sueldo (escribe digitos): ");
            int sueldo = num.nextInt();
            System.out.println("Puesto: ");
            String puesto = str.nextLine();

            nuevaLista.darAlta(new Empleado(id, sueldo, nombre, fechaNacimiento, rfc, puesto));
            Archivo.escribeCatalogo(nuevaLista.catalogo);
        } else if (opcion == 3) {
            Scanner num = new Scanner(System.in);
            System.out.println("Escribe el ID del empleado a eliminar: ");
            int eliminado = num.nextInt();
            nuevaLista.darBaja(eliminado);
            Archivo.escribeCatalogo(nuevaLista.catalogo);
        } else if (opcion == 4) {
            ListaLigada<Empleado> filtra = new ListaLigada<>();
            Scanner str = new Scanner(System.in);
            System.out.println("Escribe el puesto: ");
            String puesto = str.nextLine();
            Scanner num = new Scanner(System.in);
            System.out.println("Escribe el sueldo (escribe digitos): ");
            int sueldo = num.nextInt();
            filtra = nuevaLista.filtraPuestoSueldo(puesto, sueldo);
            for(int i = 0; i < filtra.size(); i++){
                System.out.println("\n    - ID: "+ filtra.get(i).getId());
                System.out.println("    - Nombre: "+ filtra.get(i).getNombre());
                System.out.println("    - Fecha nacimiento: "+ filtra.get(i).getNacimiento());
                System.out.println("    - RFC: "+ filtra.get(i).getRfc());
                System.out.println("    - Sueldo: "+ filtra.get(i).getSueldo());
                System.out.println("    - Puesto: "+ filtra.get(i).getPuesto());
            }
        } else {
            Scanner str = new Scanner(System.in);
            System.out.println("Escribe la fecha de nacimiento (dd/mm/yyyy): ");
            String fechaNacimiento = str.nextLine();
            nuevaLista.aumentaSueldo(fechaNacimiento);
            Archivo.escribeCatalogo(nuevaLista.catalogo);
        }
    }
}
