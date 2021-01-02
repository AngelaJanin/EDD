package fciencias.edatos.practica06;

/**
* Interfaz que define las operaciones sobre un arbol
* binario de busqueda.
* @author Emmanuel Cruz Hernández.
* @version 1.0, Noviembre 2020.
* @since Estructuras de Datos 2021-1.
*/
public interface TDAArbolBinarioBusqueda<T>{

	/**
	* Recupera el objeto con clave k.
	* @param k la clave a buscar.
	* @return el elemento con clave k o null si no existe.
	*/
	public T retrieve(String k);

	/**
	* Inserta un nuevo elemento al árbol.
	* @param e el elemento a ingresar.
	* @param k la clave del elemento a ingresar.
	*/
	public void insert(T e, String k);

	/**
	* Elimina el nodo con clave k del árbol.
	* @param k la clave perteneciente al nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar.
	* null si el nodo con clave k no existe.
	*/
	public T delete(String k);

	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
	public T findMin();

	/**
	* Encuentra la clave k con valor o peso máximo del árbol.
	* @return el elemento con llave de peso máximo.
	*/
	public T findMax();

	/**
	* Realiza el recorrido postOrder sobre los nodos del árbol.
	* @return la lista que contiene todos los nodos del árbol.
	*/
	public ListaLigada<T> postOrder();
}