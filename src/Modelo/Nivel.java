package Modelo;

/**
 * Este modelo muestra el formato editable del nivel, en este apartado se ideó 
 * que sea editable por tiempo y en el formate de resolver, el cual varia de 
 * acuerdo al juego, pues en el se almacena el número de palabras a resolver en 
 * el caso del ahorcado y en el otro se almacena las dimensiones y numero de 
 * cartas a resolver (caso pares)
 * @author Grupo
 */
public class Nivel {
    private Integer id;
    private String nombre;
    private Integer tiempo;
    private String formatoResolver;

    public String getFormatoResolver() {
        return formatoResolver;
    }

    public void setFormatoResolver(String formatoResolver) {
        this.formatoResolver = formatoResolver;
    }

    public Nivel() {
    }

    public Nivel(String nombre, Integer tiempo, String formatoResolver) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.formatoResolver = formatoResolver;
    }

    public Nivel(String nombre, Integer tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo() {
        if (tiempo == null) {
            tiempo = 0;
        }
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }
}
