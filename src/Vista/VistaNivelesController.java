package Vista;

import BaseDeDatos.BDayuda;
import Constantes.Constantes;
import Controlador.ControladorAdministracion;
import Notificaciones.ConstructorNotificacion;
import Notificaciones.TipoNotificacion;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaNivelesController implements Initializable {

    @FXML
    private TableView tblNiveles;
    @FXML
    private TableColumn colNombreNivel;
    @FXML
    private TableColumn colTiempoNivel;
    @FXML
    private ImageView fondo;

    private ControladorAdministracion ca = new ControladorAdministracion();
    
    @FXML
    private JFXButton btnRegresar;

    private Integer juego;

    public void setJuego(Integer juego) {
        this.juego = juego;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarImagenes();
        
    }

    private void cargarImagenes() {
        fondo.setImage(new Image(Constantes.FONDO1));
    }

    public void cargarDatos(Integer juego) {
        ca.llamarLista(juego);
        if (!ca.getListaNiveles().isEmpty()) {
            tblNiveles.setItems(ca.getListaNiveles());
            colNombreNivel.setCellValueFactory(new PropertyValueFactory("nombre"));
            colTiempoNivel.setCellValueFactory(new PropertyValueFactory("tiempo"));
            tblNiveles.setFixedCellSize(50);
            tblNiveles.setEditable(false);
        }
        this.juego = juego;
    }

    @FXML
    public void ingresarNivel() {
        if (tblNiveles.getSelectionModel().getSelectedItems().isEmpty()) {
            ConstructorNotificacion.create(TipoNotificacion.ERROR, "No se ha seleccionado un nivel");
            return;
        }
        try {
            if (this.juego == 1) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(Constantes.JUEGOAHORCADO));
                Parent root = loader.load();
                VistaJuegoAhorcadoController ahorcado = loader.getController();
                ahorcado.getCtrlAhorcado().setNivels(tblNiveles.getSelectionModel().getSelectedItem());
                ahorcado.cargarjuego();
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.getIcons().add(new Image(Constantes.LOGO));
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                closeStage();
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(Constantes.JUEGOPARES));
                Parent root = loader.load();
                VistaJuegoParesController pares = loader.getController();
                pares.getCtr().setNivel(tblNiveles.getSelectionModel().getSelectedItem());
                pares.gameStart();
                Stage stage = new Stage(StageStyle.UNDECORATED);
                stage.getIcons().add(new Image(Constantes.LOGO));
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                closeStage();
            }
        } catch (IOException e) {

        }
    }

    @FXML
    public void exit() {
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

}
