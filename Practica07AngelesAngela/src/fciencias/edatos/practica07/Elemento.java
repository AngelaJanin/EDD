package fciencias.edatos.practica07;

public class Elemento{
    private String nombre = "";
    private String simbolo = "";
    private int numero = 0;
    private double masa = 0.0;

    //Constructor de la clase Elemento
    public Elemento(String nombre, String simbolo, int numero, double masa){
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.numero = numero;
        this.masa = masa;
    }

    public String getNombre(){
        return nombre;
    }

    public String getSimbolo(){
        return simbolo;
    }

    public int getNumero(){
        return numero;
    }

    public Double getMasa(){
        return masa;
    }
}