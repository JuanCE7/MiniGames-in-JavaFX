package Vista;

import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class PreloaderControl implements Initializable {

    @FXML
    private Label progress;

    public static Label label;

    public static JFXProgressBar statProgressBar1;

    @FXML
    private JFXProgressBar progressBar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label = progress;
        statProgressBar1 = progressBar1;  
    }
}
