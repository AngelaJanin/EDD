package fciencias.edatos.practica04;

public class Mesa {

  int id;
  int comensales;
  boolean ocupada;

  //Constructor de la clase Mesa
  public Mesa(int id, int comensales){
    this.id = id;
    this.comensales = comensales;
    this.ocupada = false;
  }

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public int getComensales(){
    return comensales;
  }

  public void setComensales(int comensales){
    this.comensales = comensales;
  }

  public boolean getOcupada(){
    return ocupada;
  }

  public void setOcupada(boolean ocupada){
    this.ocupada = ocupada;
  }
}
