package fciencias.edatos.practica03;

import java.lang.IndexOutOfBoundsException;

/**
* Implementación de una lista ligada.
* @author Emmanuel Cruz Hernández.
* @version 1.0 2020.
* @since Estructuras de datos 2021-1.
*/
public class ListaLigada<T> implements TDALista<T>{

    private class Nodo<T>{

        private T elemento;

        private Nodo<T> siguiente;

        public Nodo(T elemento){
            this.elemento = elemento;
        }

        public T getElemento(){
            return elemento;
        }

        public Nodo<T> getSiguiente(){
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente){
            this.siguiente = siguiente;
        }

        public void setElemento(T elemento){
            this.elemento = elemento;
        }
    }

    private Nodo<T> cabeza;

    private int cantidad;

    @Override
    public boolean isEmpty(){
        return cantidad==0;
    }

    @Override
    public int size(){
        return cantidad;
    }

    @Override
    public T get(int i) throws IndexOutOfBoundsException{
        if(i<0 || i>=cantidad){
            throw new IndexOutOfBoundsException("Índice fuera de rango válido");
        }

        Nodo<T> iterador = cabeza;

        for(int j = 0; j<i; j++)
            iterador = iterador.getSiguiente();

        return iterador.getElemento();
    }

    @Override
    public void add(int i, T obj) throws IndexOutOfBoundsException{
        if(i<0 || i>cantidad){
            throw new IndexOutOfBoundsException("Índice fuera de rango válido");
        }

        Nodo<T> nuevo = new Nodo<>(obj);

        if(i == 0){
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        } else{
            Nodo<T> iterador = cabeza;

            for(int j = 0; j<i-1; j++){
                iterador = iterador.getSiguiente();
            }

            nuevo.setSiguiente(iterador.getSiguiente());
            iterador.setSiguiente(nuevo);
        }

        cantidad++;
    }

    @Override
    public T set(int i, T obj) throws IndexOutOfBoundsException{
        if(i<0 || i>=cantidad){
            throw new IndexOutOfBoundsException("Índice fuera de rango válido");
        }

        Nodo<T> iterador = cabeza;

        for(int j = 0; j<i; j++)
            iterador = iterador.getSiguiente();

        T anterior = iterador.getElemento();

        iterador.setElemento(obj);

        return anterior;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
        if(i<0 || i>=cantidad){
            throw new IndexOutOfBoundsException("Índice fuera de rango válido");
        }

        T eliminado = null;

        if(i == 0){
            eliminado = cabeza.getElemento();
            cabeza = cabeza.getSiguiente();
        }else{
            Nodo<T> iterador = cabeza;

            for(int j = 0; j<i-1; j++){
                iterador = iterador.getSiguiente();
            }

            eliminado = iterador.getSiguiente().getElemento();

            iterador.setSiguiente(iterador.getSiguiente().getSiguiente());
        }

        cantidad--;

        return eliminado;
    }

    /**
    * Permite saber si un elemento está contenido en una lista.
    * @param elemento a verificar si está en la lista.
    * @return true si el elemento está contenido, false en otro.
    */
    public boolean isContained(T obj){
        Nodo<T> iterador = cabeza;

        while(iterador!=null){
            if(obj.equals(iterador.getElemento()))
                return true;
            iterador = iterador.getSiguiente();
        }

        return false;
    }

    /**
    * No permite vaciar la lista.
    */
    public void clean(){
        cabeza = null;
        cantidad = 0;
    }

    /**
    * Muestra los elementos contenidos en una lista.
    */
    public void muestra(){
        Nodo<T> iterador = cabeza;

        for(int i = 0; i<cantidad; i++){
            System.out.println(iterador.getElemento());
            iterador = iterador.getSiguiente();
        }
    }

    public static void main(String[] args) {
    	ListaLigada<String> l = new ListaLigada<>();

        l.add(0, "Hola");
        l.add(1, "Hola1");
        l.add(2, "Hola2");
        l.add(3, "Hola3");
        l.add(4, "Hola4");
        l.add(5, "Hola5");
        l.add(6, "Hola6");
        l.add(7, "Hola7");
        l.add(8, "Hola8");

        l.remove(6);

        l.muestra();

        System.out.println(l.size());
        System.out.println(l.get(7));

        l.clean();

        l.muestra();

        System.out.println(l.isEmpty());
    }
}