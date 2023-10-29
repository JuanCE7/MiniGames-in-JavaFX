
package Modelo;

import Listas.ListaSimple;

public class Juego {
    private Integer id;
    private String nombre;    
    private ListaSimple<Usuario> puntuacion;
    private ListaSimple<Nivel> niveles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListaSimple<Usuario> getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(ListaSimple<Usuario> puntuacion) {
        this.puntuacion = puntuacion;
    }

    public ListaSimple<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(ListaSimple<Nivel> niveles) {
        this.niveles = niveles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean nuevoNivel(){
        return true;
    }
    
    public boolean agregarPuntuacion(){
        return true;
    }
}
