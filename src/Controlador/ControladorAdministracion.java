package Controlador;

import BaseDeDatos.BDayuda;
import Listas.ListaSimple;
import Modelo.Imagen;
import Modelo.Nivel;
import Vista.ObjetoTablaPares;
import Modelo.Palabra;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * En este controlador se administra listas provenientes de la Base de datos,
 * aqui se controla lo que ingresa y el camino para ser almacenados, extracción,
 * eliminación y modificación de datos. En si, en esta sección se gestiona los
 * niveles de cada juego.
 * @author Grupo
 */
public class ControladorAdministracion {

    private ListaSimple<Nivel> niveles = new ListaSimple<>();
    private ListaSimple<Palabra> palabras = new ListaSimple<>();
    private ListaSimple<Imagen> imagenes = new ListaSimple<>();
    private ObservableList<Nivel> listaNiveles = FXCollections.observableArrayList();
    private ObservableList<Palabra> listaPalabras = FXCollections.observableArrayList();
    private ObservableList<ObjetoTablaPares> listaImagenes = FXCollections.observableArrayList();

    private Palabra palabra = new Palabra();
    private Image image;
    private Imagen imagen = new Imagen();
    private Usuario usuario = new Usuario();
    private Nivel nivel = new Nivel();

    public Image getImage() {
        if (image == null) {
            image = new Image("");
        }
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    /**
     * Se recibe un object y se setea el objeto nivel
     * @param nivel 
     */
    public void setNivel(Object nivel) {
        if (nivel == null) {
            this.nivel = new Nivel();
        } else {
            this.nivel = (Nivel) nivel;
            this.nivel.setId(BDayuda.idNivel(this.nivel.getNombre()));
        }
    }

    public Imagen getImagen() {
        if (imagen == null) {
            imagen = new Imagen();
        }
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public ListaSimple<Nivel> getNiveles() {
        return niveles;
    }

    public void setNiveles(ListaSimple<Nivel> niveles) {
        this.niveles = niveles;
    }

    public ListaSimple<Palabra> getPalabras() {
        if (palabras == null) {
            palabras = new ListaSimple<>();
        }
        return palabras;
    }

    public void setPalabras(ListaSimple<Palabra> palabras) {
        if (palabras == null) {
            palabras = new ListaSimple<>();
        }
        this.palabras = palabras;
    }

    public void llenarPalabras(String nombre) {
        this.palabras = BDayuda.listaPalabras(nombre);
    }

    public ListaSimple<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ListaSimple<Imagen> imagenes) {
        if (imagenes == null) {
            imagenes = new ListaSimple<>();
        }
        this.imagenes = imagenes;
    }

    public Palabra getPalabra() {
        if (palabra == null) {
            palabra = new Palabra();
        }
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario() {
        this.usuario = BDayuda.ultimoIngreso();
    }
    /**
     * Se inicializa el observableList y se almacenan los valores de la 
     * lista simple en ella para luego retornar el observable list por completo
     * @return 
     */
    public ObservableList<Nivel> getListaNiveles() {
        listaNiveles = FXCollections.observableArrayList();
        llenarLista();
        return listaNiveles;
    }
    /**
     * Se hace llamado al metodo de lista Nivel que hace una consulta a la base 
     * de datos y devuelve una lista simple que llena la creada en este metodo.
     * @param nivel 
     */
    public void llamarLista(Integer nivel) {
        niveles = BDayuda.listaNivel(nivel);
    }

    /**
     * Se llena la lista de niveles con lo de la base de datos.
     */
    private void llenarLista() {
        for (int i = 0; i < niveles.size(); i++) {
            if (niveles.getDataByPosition(i) != null) {
                listaNiveles.add(niveles.getDataByPosition(i));
            }
        }
    }

    /**
     * Se llena la lista de palabras
     */
    private void llenarListaPalabras() {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.getDataByPosition(i) != null) {
                listaPalabras.add(palabras.getDataByPosition(i));
            }
        }
    }

    /**
     * Se llena las lista de imagenes.
     */
    private void llenarListaImagenes() {
        for (int i = 0; i < imagenes.size(); i++) {
            if (imagenes.getDataByPosition(i) != null) {
                ImageView aux = new ImageView(imagenes.getDataByPosition(i).getImagen());
                aux.setFitHeight(140.00);
                aux.setFitWidth(100.00);
                ObjetoTablaPares auxi = new ObjetoTablaPares(aux);
                listaImagenes.add(auxi);
            }
        }
    }

    /**
     * Se inicializa y devuelve la lista de palabras del observablelist
     * @return 
     */
    public ObservableList<Palabra> getListaPalabras() {
        listaPalabras = FXCollections.observableArrayList();
        if (palabras != null) {
            if (palabras.size() > 0) {
                llenarListaPalabras();
            }
        }
        return listaPalabras;
    }

    /**
     * Se inicializa y se llena la lista imagenes
     * @return 
     */
    public ObservableList<ObjetoTablaPares> getListaImagenes() {
        listaImagenes = FXCollections.observableArrayList();
        llenarListaImagenes();
        return listaImagenes;
    }

    /**
     * Se almacena en la lista simple la palabra proveniente de la vista
     * @return 
     */
    public boolean agregarPalabra() {
        return palabras.insertData(palabra);
    }

    /**
     * Se elimina una palabra de la base de datos
     * @param nombre
     * @return 
     */
    public boolean eliminarPalabras(String nombre) {
        return BDayuda.eliminarPalabras(nombre);
    }
    
    /**
     * Se elimina un objeto imagen por completo
     * @param nombre
     * @return 
     */
    public boolean eliminarImagenes(String nombre) {
        return BDayuda.eliminarImagenes(nombre);
    }
    
    /**
     * Se llena la lista de Imagenes
     * @param nombre 
     */
    public void llenarImagenes(String nombre) {
        this.imagenes = BDayuda.listaImagenes(nombre);
    }
    
    /**
     * Se elimina una imagen
     * @param imagen 
     */
    public void eliminarImagen(Object imagen){
        ObjetoTablaPares img = (ObjetoTablaPares) imagen;
        ListaSimple<Imagen> aux = new ListaSimple<>();
        for(int i = 0; i < imagenes.size(); i++){
            if(img.getImagen().getImage() == imagenes.getDataByPosition(i).getImagen()){
                if(imagenes.getDataByPosition(i).getId() != null){
                    BDayuda.eliminarImagen(imagenes.getDataByPosition(i).getId());
                }                
            }else{
                aux.insertData(imagenes.getDataByPosition(i));
            }            
        }
        imagenes = aux;
    }
    /**
     * Se elimina una palabra de la base y de la lista simple
     * @param pala 
     */
    public void eliminarPalabra(Object pala){
        Palabra pal = (Palabra) pala;
        ListaSimple<Palabra> aux = new ListaSimple<>();
        for(int i = 0; i < palabras.size(); i++){
            if(pal.getPalabra() == palabras.getDataByPosition(i).getPalabra()){
                if(palabras.getDataByPosition(i).getId() != null){
                    BDayuda.eliminarPalabra(palabras.getDataByPosition(i).getId());
                }                
            }else{
                aux.insertData(palabras.getDataByPosition(i));
            }            
        }
        palabras = aux;
    }
}
