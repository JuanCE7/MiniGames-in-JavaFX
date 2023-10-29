package Controlador;

import BaseDeDatos.BDayuda;
import Listas.ListaSimple;
import Modelo.Imagen;
import Modelo.Nivel;
import Modelo.Usuario;

/**
 * Se controla el juego de pares, el movimiento de datos, imagenes que se 
 * presentan en el videojuego, asi como almacenar puntuaciones, nderrotas y m
 * victorias
 * @author Grupo
 */
public class ControladorJuegoPares {

    private ListaSimple<Imagen> listaImagenes = new ListaSimple<>();
    private Nivel nivel;

    public ListaSimple<Imagen> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ListaSimple<Imagen> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Object nivel) {
        if (nivel == null) {
            this.nivel = new Nivel();
        } else {
            this.nivel = (Nivel) nivel;
            this.nivel.setId(BDayuda.idNivel(this.nivel.getNombre()));
        }
    }

    /**
     * Consulta a la base de datos la lista de Imagenes almacenada
     */
    public void llamarListaImagenes() {
        listaImagenes = BDayuda.listaImagenes(nivel.getNombre());
        listaImagenes.shuffle(listaImagenes);
    }

    /**
     * Se extrae del string los valores de numero total de cartas
     * @return 
     */
    public int numeroCartas() {
        String[] aux = nivel.getFormatoResolver().split(" ");
        return Integer.parseInt(aux[2]);
    }

    /**
     * Se extrae del string los valores de numero total de columnas
     * @return 
     */
    public int numeroColumnas() {
        String[] aux = nivel.getFormatoResolver().split(" ");
        return Integer.parseInt(aux[0]);
    }

    /**
     * Se extrae del string los valores de numero total de filas
     * @return 
     */
    public int numeroFilas() {
        String[] aux = nivel.getFormatoResolver().split(" ");
        return Integer.parseInt(aux[1]);
    }

    /**
     * Se almacena la puntuación obtenida en el videojuego
     * @param puntuacion
     * @param tipoS 
     */
    public void guardarPuntuacion(Integer puntuacion, Integer tipoS) {
        Usuario us = BDayuda.ultimoIngreso();
        if (tipoS == 1) {
            us.setNvictorias(us.getNvictorias() + 1);
        } else {
            us.setNderrotas(us.getNderrotas() + 1);
        }
        us.setPuntuacionPares(us.getPuntuacionPares() + puntuacion);
        BDayuda.actualizarUsuario(us);
    }
}
