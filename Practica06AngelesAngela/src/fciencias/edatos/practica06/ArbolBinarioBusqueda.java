package fciencias.edatos.practica06;
/**
 * Implementación de un árbol binario de búsqueda.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0, Noviembre 2020.
 * @since Estructuras de datos 2021-1.
 */
public class ArbolBinarioBusqueda<T> implements TDAArbolBinarioBusqueda<T>{

	/**
	* Nodo binario de un arbol de binario de busqueda.
	*/
	private class NodoBinario<T>{

		/** Nodo padre. */
		private NodoBinario<T> padre;

		/** Hijo izquierdo. */
		private NodoBinario<T> izquierdo;

		/** Hijo derecho. */
		private NodoBinario<T> derecho;

		/** Elemento almacenado en el nodo. */
		private T elemento;

		/** Peso del nodo. */
		private String k;

		/**
		* Crea un nuevo nodo binario.
		* @param elemento el elemento almacenar en el nodo.
		* @param k el peso del nodo.
		*/
		public NodoBinario(T elemento, String k){
			this.elemento = elemento;
			this.k = k;
		}

		/**
		* Crear un nuevo nodo binario con padre.
		* @param elemento el elemento almacenar en el nodo.
		* @param k el peso del nodo.
		* @param padre el padre del nodo creado.
		*/
		public NodoBinario(T elemento, String k, NodoBinario<T> padre){
			this.elemento = elemento;
			this.k = k;
			this.padre = padre;
		}

	}

	/** Root. */
	private NodoBinario<T> raiz;

	@Override
	public T retrieve(String k){
		NodoBinario<T> buscado = retrieveAux(k, raiz);
		if(buscado == null)
			return null;
		return buscado.elemento;
	}

	/**
	* Recorrer los nodos del arbol hasta llegar
	* a aquel con clave k.
	* @param k la clave buscada.
	* @param actual el nodo actual del recorrido sobre el arbol.
	* @return el nodo con clave k.
	*/
	private NodoBinario<T> retrieveAux(String k, NodoBinario<T> actual){
		if(actual == null)
			return null;

		if(k.compareTo(actual.k) == 0)
			return actual;

		if(k.compareTo(actual.k) < 0)
			return retrieveAux(k, actual.izquierdo);
		else
			return retrieveAux(k, actual.derecho);
	}

	@Override
	public void insert(T e, String k){
		NodoBinario<T> nuevo = new NodoBinario<T>(e, k);
		if(raiz == null){
			raiz = nuevo;
		} else{
			insertAux(nuevo, raiz);
		}
	}

	/**
	* Permite agregar un nuevo elemento a un arbol.
	* @param nuevo el nuevo nodo a agregar.
	* @param actual el nodo auxiliar del recorrido.
	*/
	private void insertAux(NodoBinario<T> nuevo, NodoBinario<T> actual){
		if((nuevo.k).compareTo(actual.k) < 0){
			if(actual.izquierdo == null){
				nuevo.padre = actual;
				actual.izquierdo = nuevo;
			} else{
				insertAux(nuevo, actual.izquierdo);
			}
		} else{
			if(actual.derecho == null){
				nuevo.padre = actual;
				actual.derecho = nuevo;
			} else{
				insertAux(nuevo, actual.derecho);
			}
		}
	}

	@Override
	public T delete(String k){
		NodoBinario<T> eliminado = retrieveAux(k, raiz);
		return removeAux(eliminado);
	}

	/**
	* Permite eliminar un nodo de un arbol.
	* @param eliminado el nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar.
	*/
	public T removeAux(NodoBinario<T> eliminado){
		if(eliminado == null){
			return null;
		}

		NodoBinario<T> padre = eliminado.padre;
		T elemento = eliminado.elemento;

		// Cuando eliminado no tiene hijos.
		if(eliminado.izquierdo==null && eliminado.derecho==null){
			if(padre == null){
				raiz = null;
				return elemento;
			}
			if(padre.izquierdo == eliminado){
				padre.izquierdo = null;
			} else{
				padre.derecho = null;
			}
			return elemento;
		}

		// Cuando eliminado tiene dos hijos.
		if(eliminado.izquierdo!=null && eliminado.derecho!=null){
			NodoBinario<T> maximo = findMax(eliminado.izquierdo);

			eliminado.k = maximo.k;
			eliminado.elemento = maximo.elemento;

			removeAux(maximo);
			return elemento;
		}

		// Cuando eliminado tiene un hijo.

		NodoBinario<T> reemplazado = eliminado.derecho!=null? eliminado.derecho : eliminado.izquierdo;

		if(padre == null){
			if(eliminado.izquierdo != null){
				raiz = eliminado.izquierdo;
			} else{
				raiz = eliminado.derecho;
			}
			return elemento;
		}

		if(padre.izquierdo == eliminado){ // Hijo izquierdo
			padre.izquierdo = reemplazado;
			reemplazado.padre = padre;
		} else{ // Hijo derecho
			padre.derecho = reemplazado;
			reemplazado.padre = padre;
		}

		return elemento;
	}

	/**
	* Encuentra el elemento máximo a partir de un nodo actual
	* @param actual el nodo a partir del cual buscar el máximo.
	* @return nodo que contiene al elemento con peso máximo
	* a partir del nodo actual.
	*/
	private NodoBinario<T> findMax(NodoBinario<T> actual){
		NodoBinario<T> max = actual;

		while(max.derecho != null){
			max = max.derecho;
		}

		return max;
	}

	@Override
	public T findMin(){
		if(raiz == null)
			return null;
		NodoBinario<T> min = raiz;

		while(min.izquierdo != null)
			min = min.izquierdo;

		return min.elemento;
	}

	@Override
	public T findMax(){
		if(raiz == null)
			return null;
		NodoBinario<T> max = raiz;

		while(max.derecho != null)
			max = max.derecho;

		return max.elemento;
	}

	private ListaLigada<T> postOrderAux(NodoBinario<T> nodo, ListaLigada<T> nodos){
		if(nodo == null) return nodos;
		postOrderAux(nodo.izquierdo, nodos);
		postOrderAux(nodo.derecho, nodos);
		nodos.add(0, nodo.elemento);
		return nodos;
	}

	/**
	* Realiza el recorrido postOrder sobre los nodos del árbol.
	* @return la lista que contiene todos los nodos del árbol.
	*/
	@Override
	public ListaLigada<T> postOrder(){
		ListaLigada<T> nodos = new ListaLigada<>();
		nodos = postOrderAux(raiz, nodos);
		return nodos;
	}
}