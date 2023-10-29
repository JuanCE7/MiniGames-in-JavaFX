package Controlador;

import BaseDeDatos.BDayuda;
import Listas.ListaSimple;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Se controla los datos en el menu de presentación del sistema, además
 * puntuaciones y modificación de datos del usuario.
 *
 * @author Grupo
 */
public class ControladorMenu {

    private ListaSimple<Usuario> usuarios;

    private ObservableList<Usuario> listaJugadores;

    private Usuario us;

    public Usuario getUs() {
        return us;
    }

    public void setUs() {
        this.us = BDayuda.ultimoIngreso();
    }

    /**
     * Se actualiza datos del usuario
     *
     * @param usuario
     * @return
     */
    public boolean actualizarUsuario(String usuario) {
        us.setUsuario(usuario);
        return BDayuda.actualizarUsuario(us);
    }

    /**
     * Devuelve observablelist de top de jugadores
     *
     * @return
     */
    public ObservableList<Usuario> getListaJugadores() {
        ordenar();
        listaJugadores = FXCollections.observableArrayList();
        llenarLista();
        return listaJugadores;
    }

    /**
     * Se llama a la lista de jugadores con sus puntajes y se los ordena
     */
    public void llamarLista() {
        usuarios = BDayuda.listaTopJugadores();
    }

    private void ordenar() {
        for (int i = 0; i < usuarios.size(); i++) {
            for (int j = 0; j < usuarios.size(); j++) {
                Integer sum = usuarios.getDataByPosition(i).getPuntuacionAhorcado() + usuarios.getDataByPosition(i).getPuntuacionPares();
                Integer sum2 = usuarios.getDataByPosition(j).getPuntuacionAhorcado() + usuarios.getDataByPosition(j).getPuntuacionPares();
                if(sum > sum2){
                    Usuario us = usuarios.getDataByPosition(i);
                    usuarios.updateByPosition(i, usuarios.getDataByPosition(j));
                    usuarios.updateByPosition(j, us);
                }
            }
        }
    }

    /**
     * Se traspasa la lista simple al observableList
     */
    private void llenarLista() {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.getDataByPosition(i) != null) {
                listaJugadores.add(usuarios.getDataByPosition(i));
            }
        }
    }
}
