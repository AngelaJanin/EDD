package fciencias.edatos.practicaReposicion;

import java.util.EmptyStackException;

/**
 * Tipo de Datos Abstracto de una pila.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 2020.
 * @since Estructuras de datos 2021-1.
 */
public interface TDAPila<T>{

    /**
     * Empuja un nuevo elemento, agregándolo en el tope de la pila.
     * @param t el elemento a agregar.
     */
    public void push(T t);

    /**
    * Regresa y elimina el elemento en el tope de la pila.
    * @return el elemento en el tope de la pila.
    * @throws EmptyStackException si la lista no tiene elementos.
    */
    public T pop() throws EmptyStackException;

    /**
    * Regresa el elemento en el tope de la pila. Sin eliminarlo.
    * @return el elemento en el tope de la pila.
    * @throws EmptyStackException si la lista no tiene elementos.
    */
    public T top() throws EmptyStackException;

    /**
    * Verifica si la lista contiene elementos.
    * @return <i>true</i> si la pila es vacía, <i>false</i> en otro caso.
    */
    public boolean isEmpty();

    /**
    * Elimina todos los elementos contenidos en la pila.
    */
    public void clear();

}
