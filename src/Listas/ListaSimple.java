    package Listas;

import java.util.Random;

public class ListaSimple<T> {

    public NodoDato cabecera;

    public Integer tamanio;

    public ListaSimple() {
        this.cabecera = null;
        tamanio = 0;
    }

    public boolean isEmpty() {
        return (this.cabecera == null);
    }

    public void lists() {
        if (!isEmpty()) {
            NodoDato tmp = cabecera;
            while (tmp != null) {
                System.out.print(tmp.getDato() + "\t");
                tmp = tmp.getSiguiente();
            }
        }
    }

    private void insert(T dato) {
        NodoDato tmp = new NodoDato(dato, cabecera);
        cabecera = tmp;
    }

    public boolean insertData(T dato) {
        try {
            insertEnd(dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T extractHeader() {
        T dato = null;
        if (!isEmpty()) {
            dato = (T) cabecera.getDato();
            cabecera = cabecera.getSiguiente();
        }
        return dato;
    }

    public T getDataByPosition(int pos) {
        T dato = null;
        if (!isEmpty() && (pos <= tamanio)) {
            NodoDato tmp = cabecera;
            for (int i = 0; i < pos; i++) {
                tmp = tmp.getSiguiente();
                if (tmp == null) {
                    break;
                }
            }
            if (tmp != null) {
                dato = (T) tmp.getDato();
            }
        }
        return dato;
    }

    public int size() {
        return tamanio;
    }

    public boolean updateByReference(T referencia, T valor) {
        if (searchFor(referencia)) {
            NodoDato aux = cabecera;
            while (aux.getDato() != referencia) {
                aux = aux.getSiguiente();
            }
            aux.setDato(valor);
            return true;
        }
        return false;
    }

    public boolean updateByPosition(int pos, T dato) {
        if (!isEmpty() && (pos <= tamanio)) {
            NodoDato tmp = cabecera;
            for (int i = 0; i < pos; i++) {
                tmp = tmp.getSiguiente();
                if (tmp == null) {
                    break;
                }
            }
            if (tmp != null) {
                tmp.setDato(dato);
                return true;
            }
        }
        return false;
    }

    public void insertByPosition(T dato, int pos) {
        if (isEmpty() || pos < 0) {
            insert(dato);
        } else {
            NodoDato iterador = cabecera;
            for (int i = 0; i < pos; i++) {
                if (iterador.getSiguiente() == null) {
                    break;
                }
                iterador = iterador.getSiguiente();
            }
            NodoDato tmp = new NodoDato(dato, iterador.getSiguiente());
            iterador.setSiguiente(tmp);
        }
        tamanio++;
    }

    private void insertEnd(T dato) {
        insertByPosition(dato, tamanio);
    }

    public boolean searchFor(T dato) {
        NodoDato aux = cabecera;
        boolean encontrado = false;
        while (aux != null && encontrado != true) {
            if (dato == aux.getDato()) {
                encontrado = true;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return encontrado;
    }

    public void removeByReference(T referencia) {
        if (searchFor(referencia)) {
            if (cabecera.getDato() == referencia) {
                cabecera = cabecera.getSiguiente();
            } else {
                NodoDato aux = cabecera;
                while (aux.getSiguiente().getDato() != referencia) {
                    aux = aux.getSiguiente();
                }
                NodoDato siguiente = aux.getSiguiente().getSiguiente();
                aux.setSiguiente(siguiente);
            }
            tamanio--;
        } else {
            System.out.println("La referencia a eliminar, no existe");
        }
    }

    public void removeByPosition(int posicion) {
        if (posicion >= 0 && posicion < tamanio) {
            if (posicion == 0) {
                cabecera = cabecera.getSiguiente();
            } else {
                NodoDato aux = cabecera;
                for (int i = 0; i < posicion - 1; i++) {
                    aux = aux.getSiguiente();
                }
                NodoDato siguiente = aux.getSiguiente();
                aux.setSiguiente(siguiente.getSiguiente());
            }
            tamanio--;
        } else {
            System.out.println("La posición a eliminar, no existe");
        }
    }

    public int getPosition(T referencia) throws Exception {
        if (searchFor(referencia)) {
            NodoDato aux = cabecera;
            int cont = 0;
            while (referencia != aux.getDato()) {
                cont++;
                aux = aux.getSiguiente();
            }
            return cont;
        } else {
            throw new Exception("Valor inexistente en la lista.");
        }
    }
    
    public void shuffle(ListaSimple<T> lista) {
        Random random = new Random();
        int i = 0;
        for (int j : random.ints(lista.size(), 0, lista.size()).toArray()) {
            intercambio(lista, i++, j);
        }
    }

    protected void intercambio(ListaSimple<T> lista, int i, int j) {
        T temp = lista.getDataByPosition(i);
        lista.updateByPosition(i, lista.getDataByPosition(j));
        lista.updateByPosition(j, temp);
    }
}
