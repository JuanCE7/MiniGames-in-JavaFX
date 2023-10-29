package Vista;

import Animaciones.Animacion;
import BaseDeDatos.BDayuda;
import Constantes.Constantes;
import Controlador.ControladorAdministracion;
import Notificaciones.ConstructorNotificacion;
import Notificaciones.TipoNotificacion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.exceptions.DataTruncationException;
import dialogTools.jfxDialogTools;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaAdministrarJuegosController implements Initializable {

    private jfxDialogTools dialogoNivelAhorcado;

    private jfxDialogTools dialogoNivelPares;

    private jfxDialogTools dialogoEliminarAhorcado;

    private jfxDialogTools dialogoEliminarPares;

    @FXML
    private ImageView fondo;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private TableView tblPares;
    @FXML
    private TableColumn colNivelPares;
    @FXML
    private TableColumn colTiempoPares;
    @FXML
    private AnchorPane AnchorPaneNivelPares;
    @FXML
    private TableView tblNivelPares;
    @FXML
    private TableColumn colImagenNivelPares;
    @FXML
    private JFXTextField txtTiempoPares;
    @FXML
    private JFXTextField txtImagenPares;
    @FXML
    private TableView tblAhorcado;
    @FXML
    private TableColumn colTiempoAhorcado;
    @FXML
    private AnchorPane AnchorPaneNivelAhorcado;
    @FXML
    private TableView tblNivelAhorcado;
    @FXML
    private TableColumn colPalabraNivelAhorcado;
    @FXML
    private TableColumn colCategoriaNivelAhorcado;
    @FXML
    private TableColumn colComodinNivelAhorcado;
    @FXML
    private JFXTextField txtTiempoAhorcado;
    @FXML
    private JFXTextField txtPalabraAhorcado;
    @FXML
    private JFXTextField txtComodinAhorcado;
    @FXML
    private JFXTextField txtCategoriaAhorcado;
    @FXML
    private TableColumn colNivelAhorcado;
    @FXML
    private StackPane stckAdministracion;
    @FXML
    private AnchorPane APadministracion;

    private ControladorAdministracion ca = new ControladorAdministracion();

    @FXML
    private JFXTextField txtNombreNivelAhorcado;
    @FXML
    private JFXTextField txtNombreNivelPares;
    @FXML
    private JFXButton btnGuardarPalabrasAhorcado;
    @FXML
    private JFXButton btnActualizarPalabrasAhorcado;
    @FXML
    private AnchorPane AnchorPaneEliminarAhorcado;
    @FXML
    private AnchorPane AnchorPaneEliminarPares;
    @FXML
    private ImageView imagenEscogida;
    @FXML
    private JFXButton btnActualizarImagenesPares;
    @FXML
    private JFXButton btnGuardarNivelPares;
    @FXML
    private JFXTextField txtColumnasPares;
    @FXML
    private JFXTextField txtFilasPares;
    @FXML
    private Label lbTamanioNivelPares;
    @FXML
    private TableColumn colNroPalabrasAhorcado;
    @FXML
    private JFXTextField txtNroPalabrasNivelAhorcado;
    @FXML
    private TableColumn colDimensionesPares;
    @FXML
    private Label lbNumeroImagenes;
    @FXML
    private Label lbNumeroPalabras;
    private AudioClip cancion;
    private File audioBoton = new File(Constantes.AUDIOBOTON);
    private File audioError = new File(Constantes.AUDIOERROR);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarImagenes();
        cargarDatosAhorcado();
        cargarDatosPares();
        txtImagenPares.setEditable(false);
        btnPause.setDisable(true);
        btnPlay.setDisable(true);
    }

    private void cargarImagenes() {
        fondo.setImage(new Image(Constantes.FONDO1));
    }

    @FXML
    public void exit() {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
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
        ((Stage) btnRegresar.getScene().getWindow()).close();
    }

    public void cargarDatosAhorcado() {
        ca.llamarLista(1);
        if (!ca.getListaNiveles().isEmpty()) {
            tblAhorcado.setItems(ca.getListaNiveles());
            colNivelAhorcado.setCellValueFactory(new PropertyValueFactory("nombre"));
            colTiempoAhorcado.setCellValueFactory(new PropertyValueFactory("tiempo"));
            colNroPalabrasAhorcado.setCellValueFactory(new PropertyValueFactory("formatoResolver"));
            tblAhorcado.setFixedCellSize(25);
            tblAhorcado.setEditable(false);
        }
    }

    public void cargarDatosPares() {
        ca.llamarLista(2);
        if (!ca.getListaNiveles().isEmpty()) {
            tblPares.setItems(ca.getListaNiveles());
            colNivelPares.setCellValueFactory(new PropertyValueFactory("nombre"));
            colTiempoPares.setCellValueFactory(new PropertyValueFactory("tiempo"));
            colDimensionesPares.setCellValueFactory(new PropertyValueFactory("formatoResolver"));
            tblPares.setFixedCellSize(25);
            tblPares.setEditable(false);
        }
    }

    private void abrirVentanaNivelAhorcado() {
        try {
            audio(audioBoton);
            APadministracion.setEffect(Constantes.EFECTO_BOX_BLUR);
            AnchorPaneNivelAhorcado.setVisible(true);

            dialogoNivelAhorcado = new jfxDialogTools(AnchorPaneNivelAhorcado, stckAdministracion);

            dialogoNivelAhorcado.show();
        } catch (Exception e) {
        }
        dialogoNivelAhorcado.setOnDialogClosed(ev -> {
            APadministracion.setEffect(null);
            AnchorPaneNivelAhorcado.setVisible(false);
        });
    }

    private void abrirVentanaNivelPares() {
        try {
            audio(audioBoton);
            APadministracion.setEffect(Constantes.EFECTO_BOX_BLUR);
            AnchorPaneNivelPares.setVisible(true);

            dialogoNivelPares = new jfxDialogTools(AnchorPaneNivelPares, stckAdministracion);

            dialogoNivelPares.show();
        } catch (Exception e) {
        }
        dialogoNivelPares.setOnDialogClosed(ev -> {
            APadministracion.setEffect(null);
            AnchorPaneNivelPares.setVisible(false);

        });
    }

    @FXML
    public void abrirNuevoNivelPares() {
        ca.setImagenes(null);
        lbTamanioNivelPares.setText("0");
        txtTiempoPares.setText("");
        txtNombreNivelPares.setText("");
        btnActualizarImagenesPares.setVisible(false);
        txtColumnasPares.setText("");
        txtFilasPares.setText("");
        btnGuardarNivelPares.setVisible(true);
        cargarDatosTablaImagenes();
        abrirVentanaNivelPares();
    }

    @FXML
    public void abrirActualizarNivelPares() {
        if (tblPares.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado un nivel");
            audio(audioError);
            return;
        }
        ca.setNivel(tblPares.getSelectionModel().getSelectedItem());
        String[] a = ca.getNivel().getFormatoResolver().split(" ");
        txtColumnasPares.setText(a[0]);
        txtFilasPares.setText(a[1]);
        lbTamanioNivelPares.setText(a[2]);
        txtTiempoPares.setText(String.valueOf(ca.getNivel().getTiempo()));
        txtNombreNivelPares.setText(ca.getNivel().getNombre());
        ca.llenarImagenes(ca.getNivel().getNombre());
        btnActualizarImagenesPares.setVisible(true);
        btnGuardarNivelPares.setVisible(false);
        cargarDatosTablaImagenes();
        abrirVentanaNivelPares();
    }

    public void cargarDatosTablaImagenes() {
        tblNivelPares.setItems(ca.getListaImagenes());
        colImagenNivelPares.setCellValueFactory(new PropertyValueFactory("imagen"));
        tblNivelPares.setFixedCellSize(100);
        tblNivelPares.setCenterShape(true);
        tblNivelPares.setEditable(false);
        lbNumeroImagenes.setText("Número de Imagenes: " + ca.getImagenes().size());
    }

    @FXML
    public void abrirVentanaEliminarAhorcado() {
        if (tblAhorcado.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado un nivel");
            audio(audioError);
            return;
        }
        audio(audioBoton);
        try {
            APadministracion.setEffect(Constantes.EFECTO_BOX_BLUR);
            AnchorPaneEliminarAhorcado.setVisible(true);

            dialogoEliminarAhorcado = new jfxDialogTools(AnchorPaneEliminarAhorcado, stckAdministracion);

            dialogoEliminarAhorcado.show();
        } catch (Exception e) {
        }
        dialogoEliminarAhorcado.setOnDialogClosed(ev -> {
            APadministracion.setEffect(null);
            AnchorPaneEliminarAhorcado.setVisible(false);
        });
    }

    @FXML
    public void abrirVentanaEliminarPares() {
        if (tblPares.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado un nivel");
            audio(audioError);
            return;
        }
        audio(audioBoton);
        try {
            APadministracion.setEffect(Constantes.EFECTO_BOX_BLUR);
            AnchorPaneEliminarPares.setVisible(true);

            dialogoEliminarPares = new jfxDialogTools(AnchorPaneEliminarPares, stckAdministracion);

            dialogoEliminarPares.show();
        } catch (Exception e) {
        }
        dialogoEliminarPares.setOnDialogClosed(ev -> {
            APadministracion.setEffect(null);
            AnchorPaneEliminarPares.setVisible(false);
        });
    }

    private void esconderDialogoNivelPares() {
        if (dialogoNivelPares != null) {
            dialogoNivelPares.close();
        }
    }

    private void esconderDialogoNivelAhorcado() {
        if (dialogoNivelAhorcado != null) {
            dialogoNivelAhorcado.close();
        }
    }

    @FXML
    public void esconderDialogoEliminarPares() {
        if (dialogoEliminarPares != null) {
            dialogoEliminarPares.close();
        }
    }

    @FXML
    public void esconderDialogoEliminarAhorcado() {
        if (dialogoEliminarAhorcado != null) {
            dialogoEliminarAhorcado.close();
        }
    }

    @FXML
    public void eliminarNivelAhorcado() {
        audio(audioBoton);
        ca.setNivel(tblAhorcado.getSelectionModel().getSelectedItem());
        if (ca.eliminarPalabras(ca.getNivel().getNombre())) {
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, "Se ha eliminado");
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha eliminado");
        }
        esconderDialogoEliminarAhorcado();
        cargarDatosAhorcado();
    }

    @FXML
    public void eliminarNivelPares() {
        audio(audioBoton);
        ca.setNivel(tblPares.getSelectionModel().getSelectedItem());
        if (ca.eliminarImagenes(ca.getNivel().getNombre())) {
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, "Se ha eliminado");
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha eliminado");
        }
        esconderDialogoEliminarAhorcado();
        cargarDatosPares();
    }

    @FXML
    public void abrirVentanaNuevoAhorcado() {
        btnActualizarPalabrasAhorcado.setVisible(false);
        btnGuardarPalabrasAhorcado.setVisible(true);
        ca.setPalabras(null);
        txtTiempoAhorcado.setText("");
        txtNombreNivelAhorcado.setText("");
        txtNroPalabrasNivelAhorcado.setText("");
        ca.setNivel(null);
        cargarDatosTablaPalabras();
        abrirVentanaNivelAhorcado();
    }

    @FXML
    public void abrirVentanaModificarAhorcado() {
        if (tblAhorcado.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado un nivel");
            audio(audioError);
            return;
        }
        btnGuardarPalabrasAhorcado.setVisible(false);
        btnActualizarPalabrasAhorcado.setVisible(true);
        ca.setNivel(tblAhorcado.getSelectionModel().getSelectedItem());
        txtTiempoAhorcado.setText(String.valueOf(ca.getNivel().getTiempo()));
        txtNombreNivelAhorcado.setText(ca.getNivel().getNombre());
        txtNroPalabrasNivelAhorcado.setText(ca.getNivel().getFormatoResolver());
        ca.llenarPalabras(ca.getNivel().getNombre());
        cargarDatosTablaPalabras();
        abrirVentanaNivelAhorcado();
    }

    @FXML
    public void agregarImagenes() {
        audio(audioBoton);
        if (!txtImagenPares.getText().isEmpty()) {
            ca.setImagen(null);
            ca.getImagen().setUrl(txtImagenPares.getText());
            ca.getImagen().setImagen(ca.getImage());
            ca.getImagenes().insertData(ca.getImagen());
            cargarDatosTablaImagenes();
            imagenEscogida.setImage(null);
            txtImagenPares.setText("");
            ca.setImage(null);
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
        }
    }

    @FXML
    public void eliminarImagen() {
        audio(audioBoton);
        if (tblNivelPares.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado una imagen");
            return;
        }
        ca.eliminarImagen(tblNivelPares.getSelectionModel().getSelectedItem());
        cargarDatosTablaImagenes();
        ConstructorNotificacion.create(TipoNotificacion.SUCCESS, "Eliminado correctamente");
    }

    @FXML
    public void eliminarPalabra() {
        audio(audioBoton);
        if (tblNivelAhorcado.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado una palabra");
            return;
        }
        ca.eliminarPalabra(tblNivelAhorcado.getSelectionModel().getSelectedItem());
        cargarDatosTablaPalabras();
        ConstructorNotificacion.create(TipoNotificacion.SUCCESS, "Eliminado correctamente");
    }

    @FXML
    public void guardarNivelPares() {
        if (comprobarCamposPares()) {
            audio(audioBoton);
            ca.getNivel().setTiempo(Integer.parseInt(txtTiempoPares.getText()));
            ca.getNivel().setNombre(txtNombreNivelPares.getText());
            ca.getNivel().setFormatoResolver(txtColumnasPares.getText() + " " + txtFilasPares.getText() + " " + lbTamanioNivelPares.getText());
            Integer nivel = BDayuda.ingresarNivel(ca.getNivel(), 2);
            for (int i = 0; i < ca.getImagenes().size(); i++) {
                BDayuda.ingresarImagenes(ca.getImagenes().getDataByPosition(i).getUrl(), nivel);
            }
            cargarDatosPares();
            esconderDialogoNivelPares();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_NO_AGREGADO);
        }
    }

    @FXML
    public void actualizarNivelPares() {
        if (comprobarCamposPares()) {
            audio(audioBoton);
            ca.getNivel().setNombre(txtNombreNivelPares.getText());
            ca.getNivel().setTiempo(Integer.parseInt(txtTiempoPares.getText()));
            ca.getNivel().setFormatoResolver(txtColumnasPares.getText() + " " + txtFilasPares.getText() + " " + lbTamanioNivelPares.getText());
            BDayuda.actualizarNivel(ca.getNivel());
            for (int i = 0; i < ca.getImagenes().size(); i++) {
                if (ca.getImagenes().getDataByPosition(i).getId() == null) {
                    try {
                        BDayuda.ingresarImagenes(ca.getImagenes().getDataByPosition(i).getUrl(), ca.getNivel().getId());
                    } catch (DataTruncationException e) {
                        ConstructorNotificacion.create(TipoNotificacion.ERROR, "Imagen muy grande");
                    }
                }
            }
            cargarDatosPares();
            esconderDialogoNivelPares();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        }
    }

    private boolean comprobarCamposPares() {

        if (txtNombreNivelPares.getText().isEmpty() && txtTiempoPares.getText().isEmpty()) {
            Animacion.shake(txtNombreNivelPares);
            Animacion.shake(txtTiempoPares);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
            audio(audioError);
            return false;
        }

        if (txtNombreNivelPares.getText().isEmpty()) {
            Animacion.shake(txtNombreNivelPares);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nombre", "Vacío"));
            audio(audioError);
            return false;
        }

        if (txtTiempoPares.getText().isEmpty()) {
            Animacion.shake(txtTiempoPares);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Tiempo", "Vacío"));
            audio(audioError);
            return false;
        }

        if (Integer.parseInt(lbTamanioNivelPares.getText()) % 2 != 0) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "Resultado entre filas y columnas debe ser par");
            audio(audioError);
            return false;
        }

        if (ca.getImagenes().size() < (Integer.parseInt(lbTamanioNivelPares.getText()) / 2)) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.TAMANIO_LISTA);
            audio(audioError);
            return false;
        }

        if (!ca.getNivel().getNombre().equals(txtNombreNivelPares.getText())) {
            if (BDayuda.verificarExistencia("niveles", "id_Juego = 2 and nombre_nivel", txtNombreNivelPares.getText()) > 0) {
                Animacion.shake(txtNombreNivelPares);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nombre", "Ya existe"));
                audio(audioError);
                return false;
            }
        }

        if (Integer.parseInt(txtTiempoPares.getText()) != ca.getNivel().getTiempo()) {
            if (BDayuda.verificarExistencia("niveles", "id_Juego = 2 and tiempo_nivel", txtTiempoPares.getText()) > 0) {
                Animacion.shake(txtTiempoPares);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Tiempo", "Ya existe"));
                audio(audioError);
                return false;
            }
        }

        if (txtColumnasPares.getText().isEmpty()) {
            Animacion.shake(txtColumnasPares);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Columnas", "Vacío"));
            audio(audioError);
            return false;
        }

        if (txtFilasPares.getText().isEmpty()) {
            Animacion.shake(txtFilasPares);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Filas", "Vacío"));
            audio(audioError);
            return false;
        }
        return true;
    }

    @FXML
    public void abrirBuscador() throws IOException {
        audio(audioBoton);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Buscar Imagenes");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            if (file.length() < 100000) {
                byte[] btImagen = Files.readAllBytes(file.toPath());
                Image img = new Image(new ByteArrayInputStream(btImagen), 100, 140, true, false);
                ca.setImage(img);
                imagenEscogida.setImage(img);
                txtImagenPares.setText(file.getAbsolutePath());
            } else {
                ConstructorNotificacion.create(TipoNotificacion.INFORMATION, "Por favor seleccione un archivo más liviano");
            }
        } else {
            ConstructorNotificacion.create(TipoNotificacion.INFORMATION, "Por favor seleccione un archivo");
        }
    }

    public void cargarDatosTablaPalabras() {
        tblNivelAhorcado.setItems(ca.getListaPalabras());
        colCategoriaNivelAhorcado.setCellValueFactory(new PropertyValueFactory("categoria"));
        colPalabraNivelAhorcado.setCellValueFactory(new PropertyValueFactory("palabra"));
        colComodinNivelAhorcado.setCellValueFactory(new PropertyValueFactory("comodin"));
        tblNivelAhorcado.setEditable(false);
        lbNumeroPalabras.setText("Número de palabras: " + ca.getPalabras().size());
    }

    @FXML
    public void agregarPalabra() {
        String palabra = txtPalabraAhorcado.getText();
        String categoria = txtCategoriaAhorcado.getText();
        String comodin = txtComodinAhorcado.getText();

        if (palabra.isEmpty() && categoria.isEmpty() && comodin.isEmpty()) {
            Animacion.shake(txtPalabraAhorcado);
            Animacion.shake(txtCategoriaAhorcado);
            Animacion.shake(txtComodinAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
            audio(audioError);
            return;
        }

        if (palabra.isEmpty()) {
            txtPalabraAhorcado.requestFocus();
            Animacion.shake(txtPalabraAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Palabra", "Vacio"));
            audio(audioError);
            return;
        }

        if (categoria.isEmpty()) {
            txtCategoriaAhorcado.requestFocus();
            Animacion.shake(txtCategoriaAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Categoria", "Vacio"));
            audio(audioError);
            return;
        }

        if (comodin.isEmpty()) {
            txtComodinAhorcado.requestFocus();
            Animacion.shake(txtComodinAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Comodín", "Vacio"));
            audio(audioError);
            return;
        }

        if (palabra.length() < 3) {
            txtPalabraAhorcado.requestFocus();
            Animacion.shake(txtPalabraAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Palabra", "de una sílaba, imposible de agregar"));
            audio(audioError);
            return;
        }

        if (BDayuda.verificarExistencia("palabras", "palabra", palabra) > 0) {
            Animacion.shake(txtPalabraAhorcado);
            Animacion.shake(txtCategoriaAhorcado);
            Animacion.shake(txtComodinAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Palabra", "Ya existe"));
            audio(audioError);
            return;
        }

        ca.setPalabra(null);
        ca.getPalabra().setComodin(comodin);
        ca.getPalabra().setCategoria(categoria);
        ca.getPalabra().setPalabra(palabra);

        if (ca.getPalabras().insertData(ca.getPalabra())) {
            audio(audioBoton);
            cargarDatosTablaPalabras();
            limpiarCamposPalabras();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_NO_AGREGADO);
        }
    }

    public void limpiarCamposPalabras() {
        txtCategoriaAhorcado.setText("");
        txtComodinAhorcado.setText("");
        txtPalabraAhorcado.setText("");
    }

    @FXML
    public void guardarNivelAhorcado() {
        if (comprobacionCamposAhorcado()) {
            ca.getNivel().setTiempo(Integer.parseInt(txtTiempoAhorcado.getText()));
            ca.getNivel().setNombre(txtNombreNivelAhorcado.getText());
            ca.getNivel().setFormatoResolver(txtNroPalabrasNivelAhorcado.getText());
            Integer nivel = BDayuda.ingresarNivel(ca.getNivel(), 1);

            for (int i = 0; i < ca.getPalabras().size(); i++) {
                BDayuda.ingresarPalabras(ca.getPalabras().getDataByPosition(i), nivel);
            }

            ca.setPalabras(null);
            txtTiempoAhorcado.setText("");
            txtNombreNivelAhorcado.setText("");
            limpiarCamposPalabras();
            cargarDatosAhorcado();
            esconderDialogoNivelAhorcado();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_NO_AGREGADO);
        }
    }

    @FXML
    public void actualizarNivelAhorcado() {
        if (comprobacionCamposAhorcado()) {
            ca.getNivel().setFormatoResolver(txtNroPalabrasNivelAhorcado.getText());
            ca.getNivel().setNombre(txtNombreNivelAhorcado.getText());
            ca.getNivel().setTiempo(Integer.parseInt(txtTiempoAhorcado.getText()));
            BDayuda.actualizarNivel(ca.getNivel());
            for (int i = 0; i < ca.getPalabras().size(); i++) {
                if (BDayuda.verificarExistencia("palabras", "palabra", ca.getPalabras().getDataByPosition(i).getPalabra()) > 0) {
                    BDayuda.actualizarPalabra(ca.getPalabras().getDataByPosition(i));
                } else {
                    BDayuda.ingresarPalabras(ca.getPalabras().getDataByPosition(i), ca.getNivel().getId());
                }
            }
            ca.setPalabras(null);
            txtTiempoAhorcado.setText("");
            txtNombreNivelAhorcado.setText("");
            limpiarCamposPalabras();
            cargarDatosAhorcado();
            esconderDialogoNivelAhorcado();
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_AGREGADO);
        } else {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_NO_AGREGADO);
        }
    }

    private boolean comprobacionCamposAhorcado() {
        if (txtTiempoAhorcado.getText().isEmpty() && txtNombreNivelAhorcado.getText().isEmpty()) {
            Animacion.shake(txtTiempoAhorcado);
            Animacion.shake(txtNombreNivelAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.MENSAJE_DATOS_INSUFICIENTES);
            return false;
        }

        if (txtTiempoAhorcado.getText().isEmpty()) {
            txtTiempoAhorcado.requestFocus();
            Animacion.shake(txtTiempoAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Tiempo", "Vacio"));
            return false;
        }

        if (txtNombreNivelAhorcado.getText().isEmpty()) {
            txtNombreNivelAhorcado.requestFocus();
            Animacion.shake(txtNombreNivelAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nombre", "Vacio"));
            return false;
        }

        if (txtNroPalabrasNivelAhorcado.getText().isEmpty()) {
            txtNroPalabrasNivelAhorcado.requestFocus();
            Animacion.shake(txtNroPalabrasNivelAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nro Palabras", "Vacio"));
            return false;
        }

        if (ca.getPalabras().size() < Integer.parseInt(txtNroPalabrasNivelAhorcado.getText())) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.TAMANIO_LISTA);
            return false;
        }

        if (Integer.parseInt(txtTiempoAhorcado.getText()) == 0) {
            txtTiempoAhorcado.requestFocus();
            Animacion.shake(txtTiempoAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Tiempo", "No puede ser cero"));
            return false;
        }

        if (!ca.getNivel().getNombre().equals(txtNombreNivelAhorcado.getText())) {
            if (BDayuda.verificarExistencia("niveles", "id_Juego = 1 and nombre_nivel", txtNombreNivelAhorcado.getText()) > 0) {
                Animacion.shake(txtNombreNivelAhorcado);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nombre", "Ya existe"));
                return false;
            }
        }

        if (Integer.parseInt(txtTiempoAhorcado.getText()) != ca.getNivel().getTiempo()) {
            if (BDayuda.verificarExistencia("niveles", "id_Juego = 1 and tiempo_nivel", txtTiempoAhorcado.getText()) > 0) {
                Animacion.shake(txtTiempoAhorcado);
                ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Tiempo", "Ya existe"));
                return false;
            }
        }

        if (txtNroPalabrasNivelAhorcado.getText().isEmpty()) {
            txtNroPalabrasNivelAhorcado.requestFocus();
            Animacion.shake(txtNroPalabrasNivelAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nro Palabras", "Vacio"));
            return false;
        }

        if (Integer.parseInt(txtNroPalabrasNivelAhorcado.getText()) == 0) {
            txtNroPalabrasNivelAhorcado.requestFocus();
            Animacion.shake(txtNroPalabrasNivelAhorcado);
            ConstructorNotificacion.create(TipoNotificacion.ERROR, Constantes.CAMPO_ERROR("Nro Palabras", "No puede ser cero"));
            return false;
        }
        return true;
    }

    @FXML
    public void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtPalabraAhorcado)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) || txtPalabraAhorcado.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(txtCategoriaAhorcado)) {
            if (!Character.isLetter(event.getCharacter().charAt(0)) || txtCategoriaAhorcado.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(txtComodinAhorcado)) {
            if (txtComodinAhorcado.getText().length() > 49) {
                event.consume();
            }
        } else if (evt.equals(txtTiempoAhorcado)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) || txtTiempoAhorcado.getText().length() > 4) {
                event.consume();
            }
        } else if (evt.equals(txtTiempoPares)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) || txtTiempoPares.getText().length() > 4) {
                event.consume();
            }
        } else if (evt.equals(txtNombreNivelAhorcado)) {
            if (txtNombreNivelAhorcado.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(txtNombreNivelPares)) {
            if (txtNombreNivelPares.getText().length() > 19) {
                event.consume();
            }
        } else if (evt.equals(txtColumnasPares)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) || txtColumnasPares.getText().length() > 0) {
                event.consume();
            }
        } else if (evt.equals(txtFilasPares)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) || txtFilasPares.getText().length() > 0) {
                event.consume();
            }
        } else if (evt.equals(txtNroPalabrasNivelAhorcado)) {
            if (!Character.isDigit(event.getCharacter().charAt(0)) || txtNroPalabrasNivelAhorcado.getText().length() > 1) {
                event.consume();
            }
        }
    }

    @FXML
    public void setearTamanio() {
        Integer aux1 = 1, aux2 = 1;
        if (!txtColumnasPares.getText().isEmpty()) {
            aux1 = Integer.parseInt(txtColumnasPares.getText());
        }
        if (!txtFilasPares.getText().isEmpty()) {
            aux2 = Integer.parseInt(txtFilasPares.getText());
        }
        lbTamanioNivelPares.setText("" + (aux1 * aux2));
    }

    @FXML
    public void abrirMusica() throws IOException {
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

    @FXML
    public void reproducir() {
        if (cancion != null) {
            if (cancion.isPlaying()) {
                cancion.stop();
            }
        }
        cancion.play();
    }

    @FXML
    public void pausar() {
        cancion.stop();
    }
}
