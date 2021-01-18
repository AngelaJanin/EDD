package fciencias.edatos.practica07;

import java.util.Scanner;
import javax.swing.text.TabableView;

public class Calculadora {
    ChainHashMap<String,Elemento> elementos; 
    double pesoTotal = 0.0;

    //Constrcutor de nuestra clase calculadora
    public Calculadora(){
        this.elementos = new ChainHashMap<>();
    }

    /**
    * Valida mediante un regex que la entrada ingresada por usuario 
    * cumpla con las características solicitadas para una entrada.
    * @param entrada es la sustancia ingresada por el usuario separada por '.' en un arreglo.
    * @return Devuelve un booleano que confirma la validez de la entrada del usuario.
	*/
    private boolean validaEntrada(String[] entrada){
        for(int i = 0; i < entrada.length; i++){
            if(!entrada[i].matches("[A-Z][a-z]*\\d*")){
                System.out.println("\n¡Atención! " + entrada[i] + " no parece una sustancia correcta"  + "\n");
                return false;
            }
        }
        return true;
    }

    /**
    * Devulve la suma total de los pesos de una sustancia ingresada por el usuario, 
    * consultando en el ChainHashMap el valor de la masa para cada simbolo encontrado.
    * Si el símbolo no es identificado, se ignora su valor en la suma.
    * @param sustancia es la sustancia ingresada por el usuario separada por '.' en un arreglo.
    * @return Devulve el peso total de la sustancia ingresada por el usuario.
	*/
    public double procesaPeso(String[] sustancia){
        for(int i = 0; i < sustancia.length; i++){
            int cantidad = 0;
            String simbolo = "";
            
            if(sustancia[i].matches(".*\\d.*")){
                cantidad = Integer.parseInt(sustancia[i].replaceAll("\\D+",""));
                simbolo = sustancia[i].replaceAll("\\d","");
                if(this.elementos.get(simbolo) != null){
                    this.pesoTotal = this.pesoTotal + this.elementos.get(simbolo).getMasa() * cantidad;
                }else{
                    System.out.println("\nNo encontramos el peso de " + simbolo + ". No será considerado en la suma.\n");
                }
            }else{
                simbolo = sustancia[i].replaceAll("\\d","");
                if(this.elementos.get(simbolo) != null){
                    this.pesoTotal = this.pesoTotal + this.elementos.get(simbolo).getMasa();
                }else{
                    System.out.println("\nNo encontramos el peso de " + simbolo + ". No será considerado en la suma.\n");
                }        
            }
        }
        return this.pesoTotal;
    }

    //Auxiliar que imprime las opciones del usuario en consola
    private void menu(){
        System.out.println("¡Hola! Elige una opción:" + "\n" +
        "    1: Escribir una sustancia a calcular"+ "\n" +
        "    2: Salir del programa"+ "\n");
    }

    public static void main(String[] args) {
        //Inicializamos los objetos necesarios
        LectorXML lector = new LectorXML();
        Calculadora tabla = new Calculadora();
        Scanner str = new Scanner(System.in);
        
        //Leemos el archivo XML que contiene la información de los elementos
        try{
            tabla.elementos = lector.lee();
        } catch(Exception e){
            System.out.println("Ocurrió un error en la lectura del archivo XML");
        }

        //Interactuamos con el usuario mediante el menú
        tabla.menu();
        String opcion = str.nextLine();

        while(!opcion.equals("2")){
            if(opcion.equals("1")){
                System.out.println("\nIngresa la sustancia que deseas calcular: ");
                String formula = str.nextLine();
                String[] entrada = formula.split("\\.");
                boolean esValido = tabla.validaEntrada(entrada);
                if(esValido) System.out.println("    El peso total de tu fórmula es: " + tabla.procesaPeso(entrada) + "\n");
                tabla.menu();
                tabla.pesoTotal = 0.0;
                opcion = str.nextLine();  
            }else{
                System.out.println("\nIngresa una opción válida del menú\n");
                tabla.menu();
                tabla.pesoTotal = 0.0;
                opcion = str.nextLine();  
            }
        }
    }
    
}
