package fciencias.edatos.archivos;

/**
 * Clase que representa un instrumento de cuerdas.
 * @author Emmanuel Cruz Hernandez.
 * @version 1.0 Septiembre 2020.
 */
public class InstrumentoDeCuerda{

    /** Nombre del instrumento. */
    public String nombre;
	
    /** Numero de cuerdas del instrumento. */
    public int cuerdas;

    /** Duenio. */
    public String duenio;

    /**
     * Crea un nuevo instrumento de cuerdas.
     * @param nombre el nombre del instrumento.
     * @param cuerdas la cantidad de cuerdas del instrumento.
     */
    public InstrumentoDeCuerda(String nombre, int cuerdas, String duenio){
	this.nombre = nombre;
	this.cuerdas = cuerdas;
	this.duenio = duenio;
    }

    /**
     * Regresa el nombre del instrumento.
     * @return el nombre del instrumento.
     */
    public String getNombre(){
	return this.nombre;
    }

    /**
     * Regresa la cantidad de cuerdas.
     * @return la cantidad de cuerdas.
     */
    public int getCuerdas(){
	return this.cuerdas;
    }

    /**
     * Modifica el nombre del instrumento.
     * @param nombre el nuevo nombre del instrumento.
     */
    public void setNombre(String nombre){
	this.nombre = nombre;
    }

    /**
     * Modifica la cantidad de cuerdas del instrumento.
     * @param cuerdas la nueva cantidad de cuerdas.
     */
    public void setCuerdas(int cuerdas){
	this.cuerdas = cuerdas;
    }

    @Override
    public String toString(){
	return "Nombre: "+nombre+"\nNo. Cuerdas: "+cuerdas+"\nDuenio: "+duenio+"\n";
    }


}
