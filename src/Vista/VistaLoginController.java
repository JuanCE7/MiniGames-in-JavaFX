package Vista;

import Animaciones.Animacion;
import BaseDeDatos.BDayuda;
import Constantes.Constantes;
import Controlador.ControladorInicio;
import Expresiones.Expresion;
import Notificaciones.ConstructorNotificacion;
import Notificaciones.TipoNotificacion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaLoginController implements Initializable {

    @FXML
    private ImageView fondo1;
    @FXML
    private ImageView user1;
    @FXML
    private ImageView key1;
    @FXML
    private ImageView fondo2;
    @FXML
    private ImageView email;
    @FXML
    private ImageView user2;
    @FXML
    private ImageView pad1;

    private TranslateTransition preShow;

    private TranslateTransition preHide;

    private double x, y;

    @FXML
    private AnchorPane registro;

    @FXML
    private ImageView mas;
    @FXML
    private JFXTextField txtUsuarioLogin;
    @FXML
    private JFXTextField txtPasswordLogin;
    @FXML
    private Pane icono;
    @FXML
    private FontAwesomeIconView icon;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private JFXTextField txtUsuarioRegistro;
    @FXML
    private JFXTextField txtPasswordRegistro;
    @FXML
    private Pane icono2;
    @FXML
    private FontAwesomeIconView icon2;
    @FXML
    private JFXPasswordField pfPasswordLogin;
    @FXML
    private JFXPasswordField pfPasswordRegistro;
    @FXML
    private Label lbCorreo;
    @FXML
    private ImageView ingreso;

    private ControladorInicio ci = new ControladorInicio();
    @FXML
    private ImageView nombre;
    @FXML
    private JFXTextField txtNombre;

    private File audioBoton = new File(Constantes.AUDIOBOTON);
    private File audioError = new File(Constantes.AUDIOERROR);

    private void audio(File file){
        javafx.scene.media.AudioClip audio = new javafx.scene.media.AudioClip(file.toURI().toString());
        audio.play();
        audio.setVolume(0.8);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarImagenes();
        verContraseña();
        verContraseña1();
    }

    private void cargarImagenes() {
        fondo1.setImage(new Image(Constantes.FONDO));
        fondo2.setImage(new Image(Constantes.FONDO));
        user1.setImage(new Image(Constantes.USUARIO));
        key1.setImage(new Image(Constantes.LLAVE));
        email.setImage(new Image(Constantes.SOBRE));
        user2.setImage(new Image(Constantes.USUARIO));
        pad1.setImage(new Image(Constantes.CANDADO));
        mas.setImage(new Image(Constantes.MAS));
        ingreso.setImage(new Image(Constantes.PLAY));
        nombre.setImage(new Image(Constantes.NOMBRE));
    }

    @FXML
    public void preShow() {
        preShow = new TranslateTransition();
        preShow.setDuration(javafx.util.Duration.seconds(0.6));
        preShow.setNode(registro);
        preShow.setToX(-570);
        preShow.play();
    }

    @FXML
    public void postHide() {
        preHide = new TranslateTransition();
        preHide.setDuration(javafx.util.Duration.seconds(0.4));
        preHide.setNode(registro);
        preHide.setToX(0);
        preHide.play();
    }

    @FXML
    public void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    public void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    public void register() {
        String nombres = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String usuario = txtUsuarioRegistro.getText().trim();
        String contraseña = pfPasswordRegistro.getText().trim();

        if (nombres.isEmpty() && correo.isEmpty() && usuario.isEmpty() && contraseña.isEmpty()) {
            Animacion.shake(txtNombre);
            Animacion.shake(txtCorreo);
            Animacion.shake(txtUsuarioRegistro);
            Animacion.shake(pfPasswordRegistro);
            Animacion.shake(icono2);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
            audio(audioError);
            return;
        }

        if (nombres.isEmpty()) {
            txtNombre.requestFocus();
            Animacion.shake(txtNombre);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nombre", "Vacio"));
            audio(audioError);
            return;
        }

        if (usuario.isEmpty()) {
            txtUsuarioRegistro.requestFocus();
            Animacion.shake(txtUsuarioRegistro);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "Vacio"));
            audio(audioError);
            return;
        }

        if (contraseña.isEmpty()) {
            pfPasswordRegistro.requestFocus();
            Animacion.shake(pfPasswordRegistro);
            Animacion.shake(icono2);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Contraseña", "Vacio"));
            audio(audioError);
            return;
        }

        if (!correo.isEmpty()) {
            if (BDayuda.verificarExistencia("usuarios", "correo_usuario", correo) > 0) {
                txtCorreo.requestFocus();
                Animacion.shake(txtCorreo);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Correo", "Ya Existe"));
                audio(audioError);
                return;
            }
        }

        if (Expresion.correo(correo) == false) {
            txtCorreo.requestFocus();
            Animacion.shake(txtCorreo);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Correo", "Invalido"));
            audio(audioError);
            return;
        }

        if (usuario.length() < 4) {
            txtUsuarioRegistro.requestFocus();
            Animacion.shake(txtUsuarioRegistro);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "No Puede tener menos de 4 caracteres"));
            audio(audioError);
            return;
        }

        if (!usuario.isEmpty()) {
            if (BDayuda.verificarExistencia("usuarios", "username", usuario) > 0) {
                txtUsuarioRegistro.requestFocus();
                Animacion.shake(txtUsuarioRegistro);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "Ya Existe"));
                audio(audioError);
                return;
            }
        }

        if (contraseña.length() < 4) {
            pfPasswordRegistro.requestFocus();
            Animacion.shake(pfPasswordRegistro);
            Animacion.shake(icono2);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Contraseña", "No Puede tener menos de 4 caracteres"));
            audio(audioError);
            return;
        }

        ci.setUsuario(null);
        ci.getUsuario().setNombre(nombres);
        ci.getUsuario().setTipoUsuario("player");
        ci.getUsuario().setNderrotas(0);
        ci.getUsuario().setNvictorias(0);
        ci.getUsuario().setPuntuacionAhorcado(0);
        ci.getUsuario().setPuntuacionPares(0);
        ci.getUsuario().setUsuario(usuario);
        ci.getUsuario().setPassword(contraseña);
        ci.getUsuario().setCorreo(correo);
        if (ci.registro()) {
            audio(audioBoton);
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            audio(audioError);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_ERROR_CONEXION_MYSQL);
        }
        limpiarTxtRegistro();
    }
    
    private void limpiarTxtRegistro() {
        txtNombre.clear();
        txtCorreo.clear();
        txtPasswordRegistro.clear();
        pfPasswordRegistro.clear();
        txtUsuarioRegistro.clear();
    }

    @FXML
    private void login() {
        String usuario = txtUsuarioLogin.getText();
        String contraseña = pfPasswordLogin.getText();

        if (usuario.isEmpty() && contraseña.isEmpty()) {
            Animacion.shake(txtUsuarioLogin);
            Animacion.shake(pfPasswordLogin);
            Animacion.shake(icono);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
            audio(audioError);
            return;
        }

        if (usuario.isEmpty()) {
            txtUsuarioLogin.requestFocus();
            Animacion.shake(txtUsuarioLogin);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "Vacio"));
            audio(audioError);
            return;
        }

        if (contraseña.isEmpty()) {
            pfPasswordLogin.requestFocus();
            Animacion.shake(pfPasswordLogin);
            Animacion.shake(icono);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Contraseña", "Vacio"));
            audio(audioError);
            return;
        }

        ci.setUsuario(null);
        if (ci.login(usuario, contraseña)) {
            audio(audioBoton);
            loadMain();
            ConstructorNotificacion.create(TipoNotificacion.INFORMATION, "BIENVENIDO AL SISTEMA " + usuario + "!");
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CREDENCIAL_INVALIDA);
            Animacion.shake(txtUsuarioLogin);
            Animacion.shake(pfPasswordLogin);
            Animacion.shake(txtPasswordLogin);
            Animacion.shake(icono);
            audio(audioError);
        }
    }

    private void loadMain() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Constantes.MENU));
            Parent root = loader.load();
            Stage stage = new Stage(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image(Constantes.LOGO));
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            closeStage();
        } catch (IOException ex) {
            Logger.getLogger(VistaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeStage() {
        ((Stage) btnIngresar.getScene().getWindow()).close();
    }

    @FXML
    public void exit() {
        audio(audioBoton);
        try {
            Process p = Runtime.getRuntime().exec("C:/xampp/xampp_stop.exe");
            System.out.println("BASE APAGADA");
        } catch (IOException e) {
            System.out.println("NO SE APAGÓ LA BASE");
        }
        System.exit(0);
    }

    private void verContraseña() {
        txtPasswordLogin.managedProperty().bind(icon.pressedProperty());
        txtPasswordLogin.visibleProperty().bind(icon.pressedProperty());
        txtPasswordLogin.textProperty().bindBidirectional(pfPasswordLogin.textProperty());

        pfPasswordLogin.managedProperty().bind(icon.pressedProperty().not());
        pfPasswordLogin.visibleProperty().bind(icon.pressedProperty().not());

        icon.pressedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                icon.setIcon(FontAwesomeIcon.EYE);
            } else {
                icon.setIcon(FontAwesomeIcon.EYE_SLASH);
            }
        });
    }

    private void verContraseña1() {
        txtPasswordRegistro.managedProperty().bind(icon2.pressedProperty());
        txtPasswordRegistro.visibleProperty().bind(icon2.pressedProperty());
        txtPasswordRegistro.textProperty().bindBidirectional(pfPasswordRegistro.textProperty());

        pfPasswordRegistro.managedProperty().bind(icon2.pressedProperty().not());
        pfPasswordRegistro.visibleProperty().bind(icon2.pressedProperty().not());

        icon2.pressedProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                icon2.setIcon(FontAwesomeIcon.EYE);
            } else {
                icon2.setIcon(FontAwesomeIcon.EYE_SLASH);
            }
        });
    }

    @FXML
    public void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtUsuarioLogin)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || txtUsuarioLogin.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(pfPasswordLogin)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || pfPasswordLogin.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(txtUsuarioRegistro)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || txtUsuarioRegistro.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(pfPasswordRegistro)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) && !Character.isDigit(event.getCharacter().charAt(0)) || pfPasswordRegistro.getText().length() > 19) {
                event.consume();
            }
        }
    }

    @FXML
    public void eventKeyTwo(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtCorreo)) {
            if (Expresion.correo(txtCorreo.getText()) == false) {
                txtCorreo.requestFocus();
                lbCorreo.setText("Correo Invalido");
            } else {
                lbCorreo.setText("");
            }
            if (txtCorreo.getText().isEmpty()) {
                lbCorreo.setText("");
            }
        }
    }
}
