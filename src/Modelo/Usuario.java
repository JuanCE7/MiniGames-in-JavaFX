
package Modelo;
/**
 * Este modelo indica los atributos que posee el jugador o administrador 
 * que entrará al juego, se colocan tambien sus correspondientes 
 * puntuaciones, así como su usuario, correo y contraseña que servirán como 
 * credenciales para ingresar al sistema.
 * @author Grupo
 */
public class Usuario {
    private Integer id;
    private String nombre;
    private String tipoUsuario;
    private Integer puntuacionAhorcado;
    private Integer puntuacionPares;
    private Integer Nderrotas;
    private Integer Nvictorias;
    private String usuario;
    private String password;
    private String correo;
    
    public Usuario(){
    }

    public Usuario(Integer id, String nombre, String tipoUsuario, Integer puntuacionAhorcado, Integer puntuacionPares, Integer Nderrotas, Integer Nvictorias, String user) {
        this.id = id;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.puntuacionAhorcado = puntuacionAhorcado;
        this.puntuacionPares = puntuacionPares;
        this.Nderrotas = Nderrotas;
        this.Nvictorias = Nvictorias;
        this.usuario = user;
    }
    
    public Usuario(Integer puntuacionAhorcado, Integer puntuacionPares, String usuario) {
        this.puntuacionAhorcado = puntuacionAhorcado;
        this.puntuacionPares = puntuacionPares;
        this.usuario = usuario;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPuntuacionAhorcado() {
        return puntuacionAhorcado;
    }

    public void setPuntuacionAhorcado(Integer puntuacionAhorcado) {
        this.puntuacionAhorcado = puntuacionAhorcado;
    }

    public Integer getPuntuacionPares() {
        return puntuacionPares;
    }

    public void setPuntuacionPares(Integer puntuacionPares) {
        this.puntuacionPares = puntuacionPares;
    }

    public Integer getNderrotas() {
        return Nderrotas;
    }

    public void setNderrotas(Integer Nderrotas) {
        this.Nderrotas = Nderrotas;
    }

    public Integer getNvictorias() {
        return Nvictorias;
    }

    public void setNvictorias(Integer Nvictorias) {
        this.Nvictorias = Nvictorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
