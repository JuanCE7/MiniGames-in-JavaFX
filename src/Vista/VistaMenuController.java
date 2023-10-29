package Vista;

import Animaciones.Animacion;
import BaseDeDatos.BDayuda;
import Constantes.Constantes;
import Controlador.ControladorMenu;
import Notificaciones.ConstructorNotificacion;
import Notificaciones.TipoNotificacion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dialogTools.jfxDialogTools;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaMenuController implements Initializable {

    private ControladorMenu cm = new ControladorMenu();

    private jfxDialogTools dialogoTop;

    private jfxDialogTools dialogoSettings;

    @FXML
    private ImageView imageAhorcado;
    @FXML
    private ImageView imagePares;
    @FXML
    private ImageView tuerca;
    @FXML
    private ImageView mando1;
    @FXML
    private ImageView mando2;
    @FXML
    private ImageView userDentro;
    @FXML
    private ImageView corona;
    @FXML
    private ImageView fondo;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnConfigGames;
    @FXML
    private ImageView tuerca1;
    @FXML
    private Label lbUsuario;
    @FXML
    private Label lbPuntuacionAhorcado;
    @FXML
    private ImageView corona1;
    @FXML
    private Label lbPuntuacionPares;
    @FXML
    private Label lbVictorias;
    @FXML
    private Label lbDerrotas;
    @FXML
    private ImageView trofeo;
    @FXML
    private ImageView losed;
    @FXML
    private ImageView top;
    @FXML
    private JFXButton btnTop;
    @FXML
    private AnchorPane ApaneTop;
    @FXML
    private AnchorPane APmenu;
    @FXML
    private StackPane stckMenu;
    @FXML
    private TableColumn colUsuario;
    @FXML
    private TableColumn colPAhorcado;
    @FXML
    private TableColumn colPPares;
    @FXML
    private TableView tblJugadores;
    @FXML
    private AnchorPane AajusteDatos;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private ImageView user2;
    private File audioBoton = new File(Constantes.AUDIOBOTON);
    private File audioError = new File(Constantes.AUDIOERROR);
    private AudioClip cancion;
    @FXML
    private JFXButton btnPause;
    @FXML
    private JFXButton btnPlay;
    @FXML
    private Label lbMusica;

    private void audio(File file) {
        javafx.scene.media.AudioClip audio = new javafx.scene.media.AudioClip(file.toURI().toString());
        audio.play();
        audio.setVolume(0.8);
    }

    public Label getLbUsuario() {
        return lbUsuario;
    }

    public Label getLbPuntuacionAhorcado() {
        return lbPuntuacionAhorcado;
    }

    public Label getLbPuntuacionPares() {
        return lbPuntuacionPares;
    }

    public Label getLbVictorias() {
        return lbVictorias;
    }

    public Label getLbDerrotas() {
        return lbDerrotas;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarImagenes();
        cargarCampos();
        btnPause.setDisable(true);
        btnPlay.setDisable(true);
    }

    public JFXButton getBtnConfigGames() {
        return btnConfigGames;
    }

    private void cargarCampos() {
        cm.setUs();
        if (cm.getUs().getTipoUsuario().equals("Administrador")) {
            btnConfigGames.setVisible(true);
        } else {
            btnConfigGames.setVisible(false);
        }
        lbDerrotas.setText("Derrotas " + cm.getUs().getNderrotas());
        lbPuntuacionAhorcado.setText("Puntuacion Ahorcado " + cm.getUs().getPuntuacionAhorcado());
        lbPuntuacionPares.setText("Puntuacion Pares " + cm.getUs().getPuntuacionPares());
        lbVictorias.setText("Victorias " + cm.getUs().getNvictorias());
        lbUsuario.setText("Usuario " + cm.getUs().getUsuario());
    }

    private void cargarImagenes() {
        imageAhorcado.setImage(new Image(Constantes.AHORCADO));
        imagePares.setImage(new Image(Constantes.PARES));
        tuerca.setImage(new Image(Constantes.TUERCA));
        tuerca1.setImage(new Image(Constantes.TUERCA));
        mando1.setImage(new Image(Constantes.MANDO));
        mando2.setImage(new Image(Constantes.MANDO));
        userDentro.setImage(new Image(Constantes.PERSONA));
        corona.setImage(new Image(Constantes.CORONA));
        corona1.setImage(new Image(Constantes.CORONA));
        fondo.setImage(new Image(Constantes.FONDOMENU));
        trofeo.setImage(new Image(Constantes.TROFEO));
        losed.setImage(new Image(Constantes.LOSED));
        top.setImage(new Image(Constantes.TOP));
    }

    @FXML
    public void exit() throws IOException {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
        abrirVentanas(Constantes.LOGIN);
    }

    @FXML
    public void configuracionJuegos() throws IOException {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
        abrirVentanas(Constantes.ADMINISTRARJUEGOS);
    }

    private void abrirVentanas(String ventana) throws IOException {
        audio(audioBoton);
        Parent root = FXMLLoader.load(getClass().getResource(ventana));
        Stage stage = new Stage(StageStyle.UNDECORATED);
        Scene scene;
        if (ventana == Constantes.LOGIN) {
            scene = new Scene(root, 600, 500);
        } else {
            scene = new Scene(root);
        }
        stage.setScene(scene);
        stage.getIcons().add(new Image(Constantes.LOGO));
        stage.show();
        closeStage();
    }

    private void closeStage() {
        ((Stage) btnRegresar.getScene().getWindow()).close();
    }

    @FXML
    public void abrirVentanaTop() {
        try {
            audio(audioBoton);
            cargarDatos();
            APmenu.setEffect(Constantes.EFECTO_BOX_BLUR);
            ApaneTop.setVisible(true);

            dialogoTop = new jfxDialogTools(ApaneTop, stckMenu);

            dialogoTop.show();
        } catch (Exception e) {
        }
        dialogoTop.setOnDialogClosed(ev -> {
            APmenu.setEffect(null);
            ApaneTop.setVisible(false);
        });
    }

    @FXML
    public void abrirVentanaSettings() {
        try {
            audio(audioBoton);
            txtUsuario.setText(cm.getUs().getUsuario());
            APmenu.setEffect(Constantes.EFECTO_BOX_BLUR);
            AajusteDatos.setVisible(true);

            dialogoSettings = new jfxDialogTools(AajusteDatos, stckMenu);

            dialogoSettings.show();
        } catch (Exception e) {
        }
        dialogoSettings.setOnDialogClosed(ev -> {
            APmenu.setEffect(null);
            AajusteDatos.setVisible(false);
            cargarCampos();
        });
    }

    public void esconderDialogoSettings() {
        if (dialogoSettings != null) {
            dialogoSettings.close();
        }
    }

    private void cargarDatos() {
        cm.llamarLista();
        tblJugadores.setItems(cm.getListaJugadores());
        colUsuario.setCellValueFactory(new PropertyValueFactory("usuario"));
        colPAhorcado.setCellValueFactory(new PropertyValueFactory("puntuacionAhorcado"));
        colPPares.setCellValueFactory(new PropertyValueFactory("puntuacionPares"));
        tblJugadores.setFixedCellSize(60);
        tblJugadores.setEditable(false);
    }

    @FXML
    public void nivelesAhorcado() {
        abrirNiveles(1);
    }

    @FXML
    public void nivelesPares() {
        abrirNiveles(2);
    }

    private void abrirNiveles(Integer juego) {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Constantes.NIVEL));
            Parent root = loader.load();
            VistaNivelesController nivel = loader.getController();
            nivel.setJuego(juego);
            nivel.cargarDatos(juego);
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

    @FXML
    public void actualizarUsuario() {
        if (txtUsuario.getText().isEmpty()) {
            Animacion.shake(txtUsuario);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "Vacío"));
            audio(audioError);
            return;
        }

        if (!cm.getUs().getUsuario().equals(txtUsuario.getText())) {
            if (BDayuda.verificarExistencia("usuarios", "username", txtUsuario.getText()) > 0) {
                Animacion.shake(txtUsuario);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Usuario", "Ya existe"));
                audio(audioError);
                return;
            }
        }

        if (cm.actualizarUsuario(txtUsuario.getText())) {
            audio(audioBoton);
            esconderDialogoSettings();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            audio(audioError);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_NO_AGREGADO);
        }
    }

    public void abrirBuscador() throws IOException {
        audio(audioBoton);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Buscar Canciones");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All", "*.mp3", "*.wap"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            cancion = new javafx.scene.media.AudioClip(file.toURI().toString());
            lbMusica.setText(file.getAbsolutePath());
            btnPause.setDisable(false);
            btnPlay.setDisable(false);
        }
    }

    public void reproducir() {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
        cancion.play();
    }

    public void pausar() {
        cancion.stop();
    }
}
