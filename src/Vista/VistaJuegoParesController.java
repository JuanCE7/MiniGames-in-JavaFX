package Vista;

import Constantes.Constantes;
import Controlador.ControladorJuegoPares;
import Listas.ListaSimple;
import Notificaciones.ConstructorNotificacion;
import Notificaciones.TipoNotificacion;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class VistaJuegoParesController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private GridPane grid;
    @FXML
    private Label lbTiempo;

    private Integer tiempo;

    private ControladorJuegoPares ctr = new ControladorJuegoPares();
    public ListaSimple<ImageView> ImagenesVistas, cartasEncontradas, imagenesNegrasVistas;
    public ListaSimple<ImagenVista> cartas, cartasNegrasVistas;
    private Image theme;
    private int clicks = 0;
    public int id1, id2;
    public ImageView imageView1, imageView2;
    public ImagenVista card1, card2;
    public int contador = 0;
    public Boolean cardsMatch;
    public Timeline time = new Timeline();
    @FXML
    private ImageView imgFinal;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private Label lbcartasEncontradas;

    private File audioBoton = new File(Constantes.AUDIOBOTON);
    private File audioError = new File(Constantes.AUDIOERROR);
    private File audioDerrota = new File(Constantes.AUDIOPERDER);
    private File audioVictoria = new File(Constantes.AUDIOGANAR);
    private File audioAcierto = new File(Constantes.AUDIOACIERTO);

    private void audio(File file) {
        javafx.scene.media.AudioClip audio = new javafx.scene.media.AudioClip(file.toURI().toString());
        audio.play();
        audio.setVolume(0.8);
    }

    public ControladorJuegoPares getCtr() {
        return ctr;
    }

    public void setCtr(ControladorJuegoPares ctr) {
        this.ctr = ctr;
    }

    public VistaJuegoParesController() {
        ImagenesVistas = new ListaSimple<>();
        cartasEncontradas = new ListaSimple<>();
        imagenesNegrasVistas = new ListaSimple<>();
        cartas = new ListaSimple<>();
        cartasNegrasVistas = new ListaSimple<>();
        cardsMatch = false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarImagenes();
        lbcartasEncontradas.setText("0");
        btnRegresar.setVisible(false);
    }

    public void gameStart() {
        timer();
        ctr.llamarListaImagenes();
        theme = new Image(Constantes.BACKCARDS);
        createImageViews(grid, ImagenesVistas);
        createImages(cartas);
        ImagenesVistas.shuffle(ImagenesVistas);
        setImages(ImagenesVistas, cartas);
        player();
    }

    public void player() {
        for (int i = 0; i < ImagenesVistas.size(); i++) {
            final ImageView imageView = ImagenesVistas.getDataByPosition(i);
            final ImagenVista card = cartas.getDataByPosition(i);
            ImagenesVistas.getDataByPosition(i).setOnMouseClicked(event -> clickEvent(imageView, card));
        }
    }

    public void clickEvent(ImageView imageView, ImagenVista card) {
        cardsMatch = false;
        clicks++;
        imageView.setDisable(true);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), imageView);
        scaleTransition.setFromX(0.5);
        scaleTransition.setToX(-0.5);
        scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            imageView.setScaleX(1);
            imageView.setImage(card.getValor());
        });

        if (clicks == 1) {
            id1 = card.getId();
            imageView1 = imageView;
            card1 = card;
            if (!imagenesNegrasVistas.searchFor(imageView1)) {
                imagenesNegrasVistas.insertData(imageView1);
                cartasNegrasVistas.insertData(card1);
            }
        }
        if (clicks == 2) {
            id2 = card.getId();
            imageView2 = imageView;
            card2 = card;
            disableAll();
            if (id1 == id2) {
                audio(audioAcierto);
                cardsMatch = true;
                cartasEncontradas.insertData(imageView1);
                cartasEncontradas.insertData(imageView2);
                imagenesNegrasVistas.removeByReference(imageView1);
                cartasNegrasVistas.removeByReference(card1);
                lbcartasEncontradas.setText("" + (cartasEncontradas.size() / 2));
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.6), event -> {
                    imageView1.setDisable(true);
                    imageView2.setDisable(true);
                    imageView1.setOpacity(0.6);
                    imageView2.setOpacity(0.6);
                    enableAll();
                }));
                timeline.play();
                contador++;
            } else {
                audio(audioError);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.2), event -> {
                    imageView1.setImage(card1.getBackground());
                    imageView2.setImage(card2.getBackground());
                    imageView1.setDisable(false);
                    imageView2.setDisable(false);
                    enableAll();
                }));
                timeline.play();
            }
            clicks = 0;
        }
        if (contador == (ctr.numeroCartas() / 2)) {
            audio(audioVictoria);
            time.stop();
            imgFinal.setImage(new Image(Constantes.GAMEWIN));
            imgFinal.setVisible(true);
            btnRegresar.setVisible(true);
            ctr.guardarPuntuacion(tiempo * ctr.numeroCartas(), 1);
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_DE_EXITO);
        }
    }

    public void eraseCards(GridPane grid) {
        for (int i = 0; i < ImagenesVistas.size(); i++) {
            grid.getChildren().remove(ImagenesVistas.getDataByPosition(i));
        }
    }

    public void enableAll() {
        for (int i = 0; i < ImagenesVistas.size(); i++) {
            ImagenesVistas.getDataByPosition(i).setDisable(false);
        }

        for (int i = 0; i < cartasEncontradas.size(); i++) {
            cartasEncontradas.getDataByPosition(i).setDisable(true);
        }
    }

    public void disableAll() {
        for (int i = 0; i < ImagenesVistas.size(); i++) {
            ImagenesVistas.getDataByPosition(i).setDisable(true);
        }
    }

    public void createImageViews(GridPane grid, ListaSimple<ImageView> imageViews) {
        grid.setHgap(10);
        grid.setVgap(10);
        for (int i = 0; i < ctr.numeroColumnas(); i++) {
            RowConstraints row = new RowConstraints(2);
            row.setMinHeight(100.0);

            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }
        for (int i = 0; i < ctr.numeroColumnas(); i++) {
            ColumnConstraints column = new ColumnConstraints(2);
            column.setMinWidth(100.0);
            column.setHgrow(Priority.SOMETIMES);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < ctr.numeroFilas(); i++) {
            for (int j = 0; j < ctr.numeroColumnas(); j++) {
                ImageView imageView = new ImageView();
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(140);
                imageView.setFitHeight(100);
                grid.add(imageView, j, i);
                imageViews.insertData(imageView);
            }
        }
    }

    public void createImages(ListaSimple<ImagenVista> cards) {
        int times = 0;
        int j = 0;
        for (int i = 1; i <= ctr.numeroCartas(); i++) {
            if (i % 2 == 1) {
                times++;
                j++;
            }
            Image value = ctr.getListaImagenes().getDataByPosition(j).getImagen();
            ImagenVista image2 = new ImagenVista(times, value, theme);
            cards.insertData(image2);
        }
    }

    public void setImages(ListaSimple<ImageView> imageViews, ListaSimple<ImagenVista> cards) {
        for (int i = 0; i < imageViews.size(); i++) {
            imageViews.getDataByPosition(i).setImage(cards.getDataByPosition(i).getBackground());
        }
    }

    private void cargarImagenes() {
        fondo.setImage(new Image(Constantes.FONDO1));
        imgFinal.setImage(new Image(Constantes.GAMEOVER));
    }

    public void timer() {
        tiempo = ctr.getNivel().getTiempo();

        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tiempo--;
                lbTiempo.setText("" + tiempo);
                if (tiempo == 0) {
                    audio(audioDerrota);
                    time.stop();
                    imgFinal.setImage(new Image(Constantes.GAMEOVER));
                    imgFinal.setVisible(true);
                    btnRegresar.setVisible(true);
                    ctr.guardarPuntuacion(0, 2);
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    @FXML
    public void exit() {
        audio(audioBoton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Constantes.NIVEL));
            Parent root = loader.load();
            VistaNivelesController nivel = loader.getController();
            nivel.setJuego(2);
            nivel.cargarDatos(2);
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
        ((Stage) lbTiempo.getScene().getWindow()).close();
    }

}
