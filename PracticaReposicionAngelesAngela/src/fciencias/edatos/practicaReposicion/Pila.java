package fciencias.edatos.practicaReposicion;

import java.util.EmptyStackException;

/**
 * Implementación de una pila basada en listas ligadas.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020.
 * @since Estructuras de datos 2021-1.
 */
public class Pila<T> implements TDAPila<T>{

    /** Lista que represente la pila. */
    private TDALista<T> listaPila;

    /**
    * Crea una nueva pila.
    */
    public Pila(){
        listaPila = new ListaLigada<T>();
    }

    @Override
    public void push(T t){
        try{
            listaPila.add(0, t);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T pop() throws EmptyStackException{
        if(listaPila.isEmpty()){
            throw new EmptyStackException();
        }
        T elemento = null;
        try{
            elemento = listaPila.get(0);
            listaPila.remove(0);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return elemento;
    }

    @Override
    public T top() throws EmptyStackException{
        if(listaPila.isEmpty()){
            throw new EmptyStackException();
        }
        T elemento = null;
        try{
            elemento = listaPila.get(0);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return elemento;
    }

    @Override
    public boolean isEmpty(){
        return listaPila.isEmpty();
    }

    @Override
    public void clear(){
        listaPila.clean();
    }

}
