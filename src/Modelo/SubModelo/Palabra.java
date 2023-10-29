
package Modelo.SubModelo;

public class Palabra {
    private Integer id;
    private String palabra;
    private String categoria;
    private String comodin;

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
