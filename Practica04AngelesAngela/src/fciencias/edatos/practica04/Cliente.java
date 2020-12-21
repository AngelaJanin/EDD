package fciencias.edatos.practica04;

public class Cliente {

  String nombre;
  int acompañantes;

  //Constructor de la clase Cliente
  public Cliente( String nombre, int acompañantes){
    this.nombre = nombre;
    this.acompañantes = acompañantes;
  }

  public String getNombre(){
    return nombre;
  }

  public int getAcompañantes(){
    return acompañantes;
  }
}
