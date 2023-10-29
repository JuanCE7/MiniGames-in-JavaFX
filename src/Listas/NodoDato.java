
package Listas;

public class NodoDato <T>{
    private T dato;
    private NodoDato siguiente;

    public NodoDato(T dato, NodoDato nodo) {
        this.dato = dato;
        this.siguiente = nodo;
    }

    public NodoDato() {
        this.dato = null;
        this.siguiente = null;
    }
    
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDato getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDato siguiente) {
        this.siguiente = siguiente;
    }
    
    
}
