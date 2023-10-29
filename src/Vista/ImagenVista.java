
package Vista;

import javafx.scene.image.Image;

public class ImagenVista {
    private int id;
    private Image valor;
    private Image background;

    public ImagenVista(int id, Image valor, Image background) {
        this.id = id;
        this.valor = valor;
        this.background = background;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getValor() {
        return valor;
    }

    public void setValor(Image valor) {
        this.valor = valor;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }
    

    
    
    
    


    
}
