package Controlador;

import BaseDeDatos.BDayuda;
import Listas.ListaSimple;
import Modelo.Nivel;
import Modelo.Palabra;
import Modelo.Usuario;

/**
 * Se controla el movimiento de datos por detras del juego del ahorcado
 * además se setean objetos o variables que son utlizadas para el traspaso a la 
 * vista, asi como el almacenamiento de puntuaciones, victorias y derrotas
 * @author Grupo
 */
public class ControladorJuegoAhorcado {

    private Palabra palabra = new Palabra();
    private ListaSimple<Palabra> lstPalabra = new ListaSimple<>();
    private int nivel;
    int randomIntex = 0;
    int contador = -1;
    char[] palabraOcultaArray;
    int intentos = 0;
    String palabraOculta = "";
    boolean cambios;
    private Integer numeroPalabras;
    private Nivel nivels;
    int tamanio;

    public Nivel getNivels() {
        return nivels;
    }

    public void setNivels(Object nivels) {
        if (nivels == null) {
            this.nivels = new Nivel();
        } else {
            this.nivels = (Nivel) nivels;
            this.nivels.setId(BDayuda.idNivel(this.nivels.getNombre()));
        }
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public ListaSimple<Palabra> getLstPalabra() {
        return lstPalabra;
    }

    public void setLstPalabra(ListaSimple<Palabra> lstPalabra) {
        this.lstPalabra = lstPalabra;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public void consultarListaNiveles() {
        lstPalabra = BDayuda.listaPalabras(this.nivels.getNombre());
        lstPalabra.shuffle(lstPalabra);
        String[] aux = this.nivels.getFormatoResolver().split(" ");
        numeroPalabras = Integer.parseInt(aux[0]);
    }

    /**
     * Se trae la categoria de la palabra presente
     * @return 
     */
    public String traerCategoriaPalabra() {
        String categoria = lstPalabra.getDataByPosition(contador).getCategoria();
        return categoria;
    }

    /**
     * Se retorna el comodín de la palabra presente
     * @return 
     */
    public String traerComodinPalabra() {
        String comodin = lstPalabra.getDataByPosition(contador).getComodin();
        return comodin;
    }

    /**
     * Se hace el intercambio de letras por guiones
     * @return 
     */
    public String guionesPalabraSecreta() {
        System.out.println("numero del guiones: " + (randomIntex));
        String guiones = "";
        //llena un string con "_" 
        for (int i = 0; i < lstPalabra.getDataByPosition(contador).getPalabra().length(); i++) {
            guiones += "―";
        }
        System.out.println("Si hay");
        return guiones;
    }

    /**
     * Se trae palabra barajeada
     * @return 
     */
    public String barejearPalabra() {
        contador += 1;
        palabraOculta = lstPalabra.getDataByPosition(contador).getPalabra();
        return palabraOculta.toLowerCase();
    }

    /**
     * Se comprueba con boolean si se pasó el nivel
     * Cuando numero de palabras sea igual a contador
     * @return 
     */
    public boolean nivelSuperado() {
        if (numeroPalabras == contador) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Se evalua la letra, si es que existe en la palabra
     * @param letra
     * @param palabraGuiones
     * @param palabraOculta
     * @return 
     */
    public String evaluarLetra(char letra, char[] palabraGuiones, String palabraOculta) {
        boolean cambio = false;
        char[] palabraOcultaArra = palabraOculta.toCharArray();
        //evalua caracter por caracter    
        for (int j = 0; j < palabraOcultaArra.length; j++) {
            //si el caracter se encuentra en la palabra secreta            
            if (palabraOcultaArra[j] == letra) {
                palabraGuiones[j] = letra;//se asigna para que se pueda ver en pantalla
                cambio = true;
            }
            //cambios = false;
        }//fin for
        if (cambio == false) {
            intentos++;
            intentosRestantes();
        }
        //si no se produjo ningun cambio, quiere decir que el jugador se equivoco
        return String.valueOf(palabraGuiones);
    }

    /**
     * Se evalua la palabra coincidencia completa de palabra
     * @param palabra
     * @param palabraOculta
     * @return 
     */
    public boolean evaluarPalabra(String palabra, String palabraOculta) {
        if (palabra.equals(palabraOculta)) {
            return true;
        } else {
            return false;
        }
    }

    public int intentosRestantes() {
        return intentos;
    }

    /**
     * Se almacena puntuacion, nderrotas o nvictorias del usuario.
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
        us.setPuntuacionAhorcado(us.getPuntuacionAhorcado() + puntuacion);
        BDayuda.actualizarUsuario(us);
    }
}
