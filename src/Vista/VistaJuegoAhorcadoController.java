package Vista;

import Constantes.Constantes;
import Controlador.ControladorJuegoAhorcado;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class VistaJuegoAhorcadoController implements Initializable {

    @FXML
    private ImageView fondo;
    @FXML
    private ImageView imgVidas;
    @FXML
    private Label lblCantidadLetras;
    @FXML
    private Label lbPalabraOculta;
    @FXML
    private ImageView imgCuerda;
    @FXML
    private ImageView imgCabeza;
    @FXML
    private ImageView imgCuerpo;
    @FXML
    private ImageView imgBrazoDerecho;
    @FXML
    private ImageView imgBrazoIzquierdo;
    @FXML
    private ImageView imgPiernaDerecha;
    @FXML
    private ImageView imgPiernaIzquierda;
    @FXML
    private ImageView sostenAhorcado;
    @FXML
    private JFXButton btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnJ, btnK, btnL, btnM, btnN, btnEYE, btnO, btnP;
    @FXML
    private JFXButton btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ, btnPista, btnI;
    @FXML
    private Label lblTiempo;
    private Integer tiempo;
    private ControladorJuegoAhorcado ctrlAhorcado = new ControladorJuegoAhorcado();
    private char[] palabraGuionesArray;
    private String palabraOculta;
    char[] palabraOcultaArray;
    private Timeline time = new Timeline();
    @FXML
    private ImageView imgFinal;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private ImageView pista;
    private int auxop = 0; 
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

    public ControladorJuegoAhorcado getCtrlAhorcado() {
        return ctrlAhorcado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRegresar.setVisible(false);
    }

    public void cargarjuego() {
        timer();
        cargarImagenes();
        ctrlAhorcado.consultarListaNiveles();
        juegoAhorcado();
        imagenesNoVisibles();
        tiempo = ctrlAhorcado.getNivels().getTiempo();
    }

    private void bloquearBotones() {
        btnA.setDisable(true);
        btnB.setDisable(true);
        btnC.setDisable(true);
        btnD.setDisable(true);
        btnE.setDisable(true);
        btnF.setDisable(true);
        btnG.setDisable(true);
        btnH.setDisable(true);
        btnI.setDisable(true);
        btnJ.setDisable(true);
        btnK.setDisable(true);
        btnL.setDisable(true);
        btnM.setDisable(true);
        btnN.setDisable(true);
        btnO.setDisable(true);
        btnP.setDisable(true);
        btnQ.setDisable(true);
        btnR.setDisable(true);
        btnS.setDisable(true);
        btnT.setDisable(true);
        btnU.setDisable(true);
        btnV.setDisable(true);
        btnW.setDisable(true);
        btnX.setDisable(true);
        btnEYE.setDisable(true);
        btnY.setDisable(true);
        btnZ.setDisable(true);
    }

    private void habilitarBotones() {
        btnA.setDisable(false);
        btnB.setDisable(false);
        btnC.setDisable(false);
        btnD.setDisable(false);
        btnE.setDisable(false);
        btnF.setDisable(false);
        btnG.setDisable(false);
        btnH.setDisable(false);
        btnI.setDisable(false);
        btnJ.setDisable(false);
        btnK.setDisable(false);
        btnL.setDisable(false);
        btnM.setDisable(false);
        btnN.setDisable(false);
        btnO.setDisable(false);
        btnP.setDisable(false);
        btnQ.setDisable(false);
        btnR.setDisable(false);
        btnS.setDisable(false);
        btnT.setDisable(false);
        btnU.setDisable(false);
        btnV.setDisable(false);
        btnW.setDisable(false);
        btnX.setDisable(false);
        btnEYE.setDisable(false);
        btnY.setDisable(false);
        btnZ.setDisable(false);
    }

    private void cargarImagenes() {
        fondo.setImage(new Image(Constantes.FONDO1));
        pista.setImage(new Image(Constantes.PISTA));
    }

    private void imagenesNoVisibles() {
        imgCuerda.setVisible(false);
        imgCabeza.setVisible(false);
        imgCuerpo.setVisible(false);
        imgBrazoIzquierdo.setVisible(false);
        imgBrazoDerecho.setVisible(false);
        imgPiernaDerecha.setVisible(false);
        imgPiernaIzquierda.setVisible(false);
    }

    public void timer() {
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tiempo--;
                lblTiempo.setText("TIEMPO:" + tiempo);
                if (tiempo == 0) {
                    audio(audioDerrota);
                    time.stop();
                    imgFinal.setImage(new Image(Constantes.GAMEOVER));
                    imgFinal.setVisible(true);
                    btnRegresar.setVisible(true);
                    ctrlAhorcado.guardarPuntuacion(0, 2);
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    private void pista() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setGraphic(new ImageView(Constantes.PISTA));
        alert.setTitle("Pista");
        alert.setContentText("PISTA: " + ctrlAhorcado.traerComodinPalabra() + "\n"
                + "CATEGORIA: " + ctrlAhorcado.traerCategoriaPalabra());
        alert.show();
    }

    public void juegoAhorcado() {
        auxop = 0;
        habilitarBotones();
        Image imagenPalosAhorcado = new Image(Constantes.PALOSAHORCADO);
        Image imagenVidasAhorcado = new Image(Constantes.VIDAS_AHORCADO_0);
        sostenAhorcado.setImage(imagenPalosAhorcado);
        imgVidas.setImage(imagenVidasAhorcado);
        palabraOculta = ctrlAhorcado.barejearPalabra();
        System.out.println("esta es la palabra de la vista " + palabraOculta);
        palabraGuionesArray = ctrlAhorcado.guionesPalabraSecreta().toCharArray();
        lbPalabraOculta.setText(ctrlAhorcado.guionesPalabraSecreta());
        lblCantidadLetras.setText(String.valueOf(ctrlAhorcado.guionesPalabraSecreta().length()));
    }

    private void closeStage() {
        ((Stage) btnRegresar.getScene().getWindow()).close();
    }

    private void palabraAdivinada(String p) {
        if (ctrlAhorcado.evaluarPalabra(p, palabraOculta)) {
            ConstructorNotificacion.create(TipoNotificacion.SUCCESS, Constantes.MENSAJE_DE_EXITO);
            juegoAhorcado();
            tiempo += 5;
            audio(audioAcierto);
        } else {
            int intent = mostrarImagenes();
            if (intent == 8) {
                audio(audioDerrota);
                time.stop();
                imgFinal.setImage(new Image(Constantes.GAMEOVER));
                imgFinal.setVisible(true);
                btnRegresar.setVisible(true);
                ctrlAhorcado.guardarPuntuacion(0, 2);
            } else {
                if(intent > auxop){
                    auxop = intent;
                    audio(audioError);
                    ConstructorNotificacion.create(TipoNotificacion.INFORMATION, Constantes.INTENTOS_RESTANTES + (8 - intent) + " intentos");
                }else{
                    audio(audioBoton);
                }            
            }
        }
        if (ctrlAhorcado.nivelSuperado()) {
            audio(audioVictoria);
            time.stop();
            imgFinal.setImage(new Image(Constantes.GAMEWIN));
            imgFinal.setVisible(true);
            btnRegresar.setVisible(true);
            ctrlAhorcado.guardarPuntuacion(tiempo * ctrlAhorcado.getLstPalabra().size(), 1);
        }
    }

    public int mostrarImagenes() {
        int intentos = ctrlAhorcado.intentosRestantes();
        Image imagenVidas = new Image(Constantes.VIDAS_AHORCADO + intentos + ".png");
        imgVidas.setImage(imagenVidas);
        if (intentos == 1) {
            imgCuerda.setVisible(true);
        }
        if (intentos == 2) {
            imgCabeza.setVisible(true);
        }
        if (intentos == 3) {
            imgCuerpo.setVisible(true);
        }
        if (intentos == 4) {
            imgBrazoDerecho.setVisible(true);
        }
        if (intentos == 5) {
            imgBrazoIzquierdo.setVisible(true);
        }
        if (intentos == 6) {
            imgPiernaDerecha.setVisible(true);
        }
        if (intentos == 7) {
            imgPiernaIzquierda.setVisible(true);
        }
        return intentos;
    }

    @FXML
    private void botonClicleado(ActionEvent event) {
        JFXButton btnLetra = (JFXButton) event.getSource();
        String palabraTypeada = ctrlAhorcado.evaluarLetra(btnLetra.getText().toLowerCase().charAt(0), palabraGuionesArray, palabraOculta);
        deshabilitarBoton(btnLetra.getText());
        lbPalabraOculta.setText(palabraTypeada);
        palabraAdivinada(palabraTypeada);
        System.out.println("letra: " + btnLetra.getText());
    }

    private void deshabilitarBoton(String letra) {
        if (letra.equals("A")) {
            btnA.setDisable(true);
        } else if (letra.equals("B")) {
            btnB.setDisable(true);
        } else if (letra.equals("C")) {
            btnC.setDisable(true);
        } else if (letra.equals("D")) {
            btnD.setDisable(true);
        } else if (letra.equals("E")) {
            btnE.setDisable(true);
        } else if (letra.equals("F")) {
            btnF.setDisable(true);
        } else if (letra.equals("G")) {
            btnG.setDisable(true);
        } else if (letra.equals("H")) {
            btnH.setDisable(true);
        } else if (letra.equals("I")) {
            btnI.setDisable(true);
        } else if (letra.equals("J")) {
            btnJ.setDisable(true);
        } else if (letra.equals("K")) {
            btnK.setDisable(true);
        } else if (letra.equals("L")) {
            btnL.setDisable(true);
        } else if (letra.equals("M")) {
            btnM.setDisable(true);
        } else if (letra.equals("N")) {
            btnN.setDisable(true);
        } else if (letra.equals("O")) {
            btnO.setDisable(true);
        } else if (letra.equals("P")) {
            btnP.setDisable(true);
        } else if (letra.equals("Q")) {
            btnQ.setDisable(true);
        } else if (letra.equals("R")) {
            btnR.setDisable(true);
        } else if (letra.equals("S")) {
            btnS.setDisable(true);
        } else if (letra.equals("T")) {
            btnT.setDisable(true);
        } else if (letra.equals("U")) {
            btnU.setDisable(true);
        } else if (letra.equals("V")) {
            btnV.setDisable(true);
        } else if (letra.equals("W")) {
            btnW.setDisable(true);
        } else if (letra.equals("X")) {
            btnX.setDisable(true);
        } else if (letra.equals("Y")) {
            btnY.setDisable(true);
        } else if (letra.equals("Z")) {
            btnZ.setDisable(true);
        } else {
            btnEYE.setDisable(true);
        }
    }

    @FXML
    private void traerPista(ActionEvent event) {
        pista();
    }

    @FXML
    public void exit() {
        audio(audioBoton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Constantes.NIVEL));
            Parent root = loader.load();
            VistaNivelesController nivel = loader.getController();
            nivel.setJuego(1);
            nivel.cargarDatos(1);
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

}
