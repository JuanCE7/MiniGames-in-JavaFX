package Vista;

import Constantes.Constantes;
import Controlador.ControladorInicio;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FinalProjectThirdCycle extends Application {

    private static ControladorInicio ci = new ControladorInicio();

    @Override
    public void start(Stage stage) throws Exception { 
        Parent root = FXMLLoader.load(getClass().getResource(Constantes.LOGIN));
        Scene scene = new Scene(root, 600, 500);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(Constantes.LOGO));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ci.encenderBase();        
        LauncherImpl.launchApplication(FinalProjectThirdCycle.class, ElPreloader.class, args);
    }

    @Override
    public void init() throws Exception {
        for (int i = 1; i <= 100; i++) {
            double progress = (double) i / 100;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(75);
        }
    }
}
