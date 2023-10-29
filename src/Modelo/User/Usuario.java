
package Modelo.User;

public class Usuario {
    private Integer id;
    private String nombre;
    private String tipoUsuario;
    private Integer puntuacionAhorcado;
    private Integer puntuacionPares;
    private Integer Nderrotas;
    private Integer Nvictorias;
    private Cuenta cuenta;

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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
