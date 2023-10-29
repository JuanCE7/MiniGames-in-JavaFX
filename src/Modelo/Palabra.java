
package Modelo;
/**
 * Este modelo acoje en su interior la palabra a adivinar en el juego
 * del ahorcado, así mismo, para un ayuda al usuario se adjunta un
 * comodín y su categoria.
 * @author Grupo
 */
public class Palabra {
    private Integer id;
    private String palabra;
    private String categoria;
    private String comodin;

    public Palabra() {
    }

    public Palabra(Integer id, String palabra, String categoria, String comodin) {
        this.id = id;
        this.palabra = palabra;
        this.categoria = categoria;
        this.comodin = comodin;
    }

    public Palabra(String palabra, String categoria, String comodin) {
        this.palabra = palabra;
        this.categoria = categoria;
        this.comodin = comodin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }   

    public String getComodin() {
        return comodin;
    }

    public void setComodin(String comodin) {
        this.comodin = comodin;
    }
    
}
