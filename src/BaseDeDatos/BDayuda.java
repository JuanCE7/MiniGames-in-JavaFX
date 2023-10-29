package BaseDeDatos;

import Listas.ListaSimple;
import Modelo.Imagen;
import Modelo.Nivel;
import Modelo.Palabra;
import Modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class BDayuda {

    public static void insertarIngreso(Usuario us) {
        try {
            String sql2 = "INSERT INTO ingresos (id_usuarioIngreso, nombre_ingreso, fecha_ingreso) VALUES (?, ?, ?)";
            PreparedStatement preparedStatementTwo = BDconexion.getInstance().getConnection().prepareStatement(sql2);
            preparedStatementTwo.setInt(1, us.getId());
            preparedStatementTwo.setString(2, us.getNombre());
            preparedStatementTwo.setDate(3, new java.sql.Date(new Date().getTime()));
            preparedStatementTwo.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean loginUsuario(String usuario, String password) {
        try {
            Usuario us = null;
            String sql = "SELECT id_usuario, nombre_usuario, tipo_usuario, puntuacion_Ahorcado, puntuacion_Pares, Nderrotas, NVictorias,username FROM usuarios WHERE username = '" + usuario + "' AND password_usuario = '" + password + "'";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre_usuario");
                String tipoUsuario = rs.getString("tipo_usuario");
                Integer PAhorcado = rs.getInt("puntuacion_Ahorcado");
                Integer PPares = rs.getInt("puntuacion_Pares");
                Integer Nderrotas = rs.getInt("Nderrotas");
                Integer Nvictorias = rs.getInt("NVictorias");
                String user = rs.getString("username");
                if (id != null) {
                    us = new Usuario(id, nombre, tipoUsuario, PAhorcado, PPares, Nderrotas, Nvictorias, user);
                }
            }
            if (us != null) {
                insertarIngreso(us);
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean insertNuevoUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre_usuario, tipo_usuario, puntuacion_Ahorcado, puntuacion_Pares, "
                    + "NDerrotas, NVictorias, username, password_usuario, correo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getTipoUsuario());
            preparedStatement.setInt(3, usuario.getPuntuacionAhorcado());
            preparedStatement.setInt(4, usuario.getPuntuacionPares());
            preparedStatement.setInt(5, usuario.getNderrotas());
            preparedStatement.setInt(6, usuario.getNvictorias());
            preparedStatement.setString(7, usuario.getUsuario());
            preparedStatement.setString(8, usuario.getPassword());
            preparedStatement.setString(9, usuario.getCorreo());
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean actualizarUsuario(Usuario usuario) {
        try {
            Integer id = idultimoIngreso();
            String sql = "UPDATE usuarios SET puntuacion_Ahorcado = ?, puntuacion_Pares = ?, Nderrotas = ?, "
                    + "NVictorias = ?,username = ? WHERE id_usuario = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, usuario.getPuntuacionAhorcado());
            preparedStatement.setInt(2, usuario.getPuntuacionPares());
            preparedStatement.setInt(3, usuario.getNderrotas());
            preparedStatement.setInt(4, usuario.getNvictorias());
            preparedStatement.setString(5, usuario.getUsuario());
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int verificarExistencia(String tabla, String consulta, String cambio) {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE " + consulta + " = ? ";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, cambio);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static ListaSimple<Usuario> listaTopJugadores() {
        ListaSimple<Usuario> usuarios = new ListaSimple<>();
        try {
            String sql = "SELECT username, puntuacion_Ahorcado, puntuacion_Pares FROM usuarios";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user = rs.getString("username");
                Integer puntA = rs.getInt("puntuacion_Ahorcado");
                Integer puntP = rs.getInt("puntuacion_Pares");
                usuarios.insertData(new Usuario(puntA, puntP, user));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public static ListaSimple<Nivel> listaNivel(Integer idJuego) {
        ListaSimple<Nivel> niveles = new ListaSimple<>();
        try {
            String sql = "SELECT nombre_nivel, tiempo_nivel, formatoResolver FROM niveles WHERE id_Juego = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idJuego);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre_nivel");
                Integer tiempo = rs.getInt("tiempo_nivel");
                String frtRes = rs.getString("formatoResolver");
                niveles.insertData(new Nivel(nombre, tiempo, frtRes));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return niveles;
    }

    public static Usuario ultimoIngreso() {
        Usuario us = null;
        try {
            String sql = "SELECT id_usuario, nombre_usuario, tipo_usuario, puntuacion_Ahorcado, "
                    + "puntuacion_Pares, Nderrotas, NVictorias,username FROM usuarios WHERE"
                    + " id_usuario = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idultimoIngreso());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre_usuario");
                String tipoU = resultSet.getString("tipo_usuario");
                Integer pAhorcado = resultSet.getInt("puntuacion_Ahorcado");
                Integer pPares = resultSet.getInt("puntuacion_Pares");
                Integer nderrotas = resultSet.getInt("Nderrotas");
                Integer nvictorias = resultSet.getInt("NVictorias");
                String username = resultSet.getString("username");
                us = new Usuario(id, nombre, tipoU, pAhorcado, pPares, nderrotas, nvictorias, username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }

    private static Integer idultimoIngreso() {
        Integer id = 0;
        try {
            String sql = "SELECT MAX(id_Ingreso) AS id FROM ingresos";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            String sql2 = "SELECT id_usuarioIngreso FROM ingresos WHERE id_Ingreso = ?";
            PreparedStatement preparedStatement2 = BDconexion.getInstance().getConnection().prepareStatement(sql2);
            preparedStatement2.setInt(1, id);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                id = resultSet2.getInt("id_usuarioIngreso");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static Integer ingresarNivel(Nivel nivel, Integer juego) {
        Integer id = 0;
        try {
            String sql = "INSERT INTO niveles (nombre_nivel, tiempo_nivel, id_Juego, formatoResolver) VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nivel.getNombre());
            preparedStatement.setInt(2, nivel.getTiempo());
            preparedStatement.setInt(3, juego);
            preparedStatement.setString(4, nivel.getFormatoResolver());
            preparedStatement.execute();

            String sql2 = "SELECT MAX(id_nivel) AS id FROM niveles";
            PreparedStatement preparedStatement2 = BDconexion.getInstance().getConnection().prepareStatement(sql2);
            ResultSet resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static void ingresarPalabras(Palabra palabra, Integer nivel) {
        try {
            String sql = "INSERT INTO palabras (palabra, categoria, comodin, idNivel) VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, palabra.getPalabra());
            preparedStatement.setString(2, palabra.getCategoria());
            preparedStatement.setString(3, palabra.getComodin());
            preparedStatement.setInt(4, nivel);
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void ingresarImagenes(String ruta, Integer nivel) {
        FileInputStream fi = null;
        try {
            String sql = "INSERT INTO imagenes (Imagen, idNivel) VALUES (?,?)";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            File file = new File(ruta);
            fi = new FileInputStream(file);

            preparedStatement.setBinaryStream(1, fi);
            preparedStatement.setInt(2, nivel);
            preparedStatement.executeLargeUpdate();
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ListaSimple<Palabra> listaPalabras(String nombre) {
        ListaSimple<Palabra> palabras = new ListaSimple<>();
        try {
            Integer nivel = idNivel(nombre);
            String sql = "SELECT id_palabra, palabra, categoria, comodin FROM palabras WHERE idNivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, nivel);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_palabra");
                String palabra = rs.getString("palabra");
                String categoria = rs.getString("categoria");
                String comodin = rs.getString("comodin");
                palabras.insertData(new Palabra(id, palabra, categoria, comodin));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return palabras;
    }

    public static ListaSimple<Imagen> listaImagenes(String nombre) {
        ListaSimple<Imagen> imagenes = new ListaSimple<>();
        try {
            Integer nivel = idNivel(nombre);
            String sql = "SELECT id_imagen, Imagen FROM imagenes WHERE idNivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, nivel);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id_imagen");
                Image image = null;
                try (InputStream is = rs.getBinaryStream("Imagen")) {
                    BufferedImage bg = ImageIO.read(is);
                    if (bg != null) {
                        image = SwingFXUtils.toFXImage(bg, null);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
                imagenes.insertData(new Imagen(id, image));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imagenes;
    }

    public static Integer idNivel(String nombre) {
        Integer nivel = 0;
        try {
            String sql2 = "SELECT id_nivel FROM niveles WHERE nombre_nivel = ?";
            PreparedStatement preparedStatement2 = BDconexion.getInstance().getConnection().prepareStatement(sql2);
            preparedStatement2.setString(1, nombre);
            ResultSet rs2 = preparedStatement2.executeQuery();
            while (rs2.next()) {
                nivel = rs2.getInt("id_nivel");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nivel;
    }

    public static void actualizarNivel(Nivel nivel) {
        try {
            String sql = "UPDATE niveles SET nombre_nivel = ?, tiempo_nivel = ?, formatoResolver = ? WHERE id_nivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, nivel.getNombre());
            preparedStatement.setInt(2, nivel.getTiempo());
            preparedStatement.setString(3, nivel.getFormatoResolver());
            preparedStatement.setInt(4, nivel.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void actualizarPalabra(Palabra palabra) {
        try {
            String sql = "UPDATE palabras SET palabra = ?, categoria = ?, comodin = ? WHERE id_palabra = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, palabra.getPalabra());
            preparedStatement.setString(2, palabra.getCategoria());
            preparedStatement.setString(3, palabra.getComodin());
            preparedStatement.setInt(4, palabra.getId());
            preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Integer eliminarNivel(String nombre) {
        Integer id = idNivel(nombre);
        try {
            String sql = "DELETE FROM niveles WHERE id_nivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static boolean eliminarPalabras(String nombre) {
        try {
            Integer id = eliminarNivel(nombre);
            String sql = "DELETE FROM palabras WHERE idNivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminarImagenes(String nombre) {
        try {
            Integer id = eliminarNivel(nombre);
            String sql = "DELETE FROM imagenes WHERE idNivel = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminarImagen(Integer id) {
        try {
            String sql = "DELETE FROM imagenes WHERE id_Imagen = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean eliminarPalabra(Integer id) {
        try {
            String sql = "DELETE FROM palabras WHERE id_palabra = ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void guardarImagen(String ruta) {
        FileInputStream fi = null;
        try {
            String sql = "INSERT INTO imagenes Imagen VALUES ?";
            PreparedStatement preparedStatement = BDconexion.getInstance().getConnection().prepareStatement(sql);
            File file = new File(ruta);
            fi = new FileInputStream(file);
            preparedStatement.setBinaryStream(1, fi);
            preparedStatement.execute();
        } catch (FileNotFoundException | SQLException ex) {
            Logger.getLogger(BDayuda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
