package fciencias.edatos.practica06;

public class Ciudad{
    private String nombre;
    private String estado;
    private double primero;
    private double segundo;
    
    //Constructor de la clase Ciudad
    public Ciudad(String nombre, String estado, Double primero, Double segundo){
        this.nombre = nombre;
        this.estado = estado;
        this.primero = primero;
        this.segundo = segundo;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEstado(){
        return estado;
    }

    public String getCoordenadas(){
        return "("+primero+","+segundo+")";
    }

    public Double getPrimero(){
        return primero;
    }

    public Double getSegundo(){
        return segundo;
    }
}