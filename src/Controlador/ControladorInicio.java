package Controlador;

import BaseDeDatos.BDayuda;
import Constantes.Constantes;
import Modelo.Usuario;
import java.io.IOException;

/**
 * Este controlador gestiona el movimiento de datos en lo que compete al 
 * login, se controla el acceso mediante las credenciales y se almacena los 
 * registros de los usuarios para usarlos como identificador de quien se 
 * encuentra en el sistema.
 * @author Grupo
 */
public class ControladorInicio {

    private Usuario usuario;

    public Usuario getUsuario() {
        if(usuario == null)
            usuario = new Usuario();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Se enciende la base de datos
     */
    public void encenderBase() {
        try {
            Process p = Runtime.getRuntime().exec(Constantes.ENCENDERBASE);
            System.out.println("BASE PRENDIDA");
        } catch (IOException e) {
            System.out.println("NO SE ENCENDIÓ LA BASE");
        }
    }
    /**
     * Se apaga la base de datos
     */
    public void apagarBase() {
        try {
            Process p = Runtime.getRuntime().exec(Constantes.APAGARBASE);
            System.out.println("BASE APAGADA");
        } catch (IOException e) {
            System.out.println("NO SE APAGÓ LA BASE");
        }
    }

    public boolean registro(){   
        return BDayuda.insertNuevoUsuario(usuario);
    }
    
    public Boolean login(String usuarioI, String password){ 
        return BDayuda.loginUsuario(usuarioI, password);
    }
}
