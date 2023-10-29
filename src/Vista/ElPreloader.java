package Vista;

import Constantes.Constantes;
import java.io.File;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ElPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;

    public ElPreloader() {
    }

    @Override
    public void init() throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource(Constantes.PRELOADER));
        scene = new Scene(root1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("src/audios/IntroSony.mp3");
        javafx.scene.media.AudioClip audio = new javafx.scene.media.AudioClip(file.toURI().toString());
        audio.play();
        audio.setVolume(0.8);
        this.preloaderStage = primaryStage;
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.getIcons().add(new Image(Constantes.LOGO));
        preloaderStage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            PreloaderControl.label.setText("Cargando " + ((ProgressNotification) info).getProgress() * 100 + "%");
            PreloaderControl.statProgressBar1.setProgress(((ProgressNotification) info).getProgress());
        }
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }
    }
}
