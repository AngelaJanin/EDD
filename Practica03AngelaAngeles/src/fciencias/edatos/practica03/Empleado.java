package fciencias.edatos.practica03;

public class Empleado {

  private String nombre;
  private String fechaNacimiento;
  private String rfc;
  private int sueldo;
  private int id;
  private String puesto;

  //Constructor de la clase Empleado
  public Empleado(int id, int sueldo,String nombre, String fechaNacimiento, String rfc, String puesto){
      this.id = id;
      this.nombre = nombre;
      this.fechaNacimiento = fechaNacimiento;
      this.rfc = rfc;
      this.sueldo = sueldo;
      this.puesto = puesto;
  }

  public String toString(){
    return id+","+sueldo+","+nombre+","+fechaNacimiento+","+rfc+","+puesto;
  }

  public String getNombre(){
    return nombre;
  }

  public String getNacimiento(){
    return fechaNacimiento;
  }

  public String getRfc(){
    return rfc;
  }

  public int getSueldo(){
    return sueldo;
  }

  public void setSueldo(int sueldo){
    this.sueldo = sueldo;
  }

  public String getPuesto(){
    return puesto;
  }

  public int getId(){
    return id;
  }
}

