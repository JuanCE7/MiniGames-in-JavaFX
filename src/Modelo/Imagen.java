
package Modelo;

import javafx.scene.image.Image;
/**
 * Este modelo almacena el nombre o url de la Imagen a ser puesta en el juego 
 * de Pares, así como un id para identificarlo.
 * @author Grupo
 */
public class Imagen {
    private Integer id;
    private Image imagen;
    private String url;

    public Imagen() {
    }

    public Imagen(Integer id, Image imagen) {
        this.id = id;
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
}
