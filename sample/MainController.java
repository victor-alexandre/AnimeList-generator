package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainController {
    @FXML
    Button button = new Button();


    @FXML
    Pane pane2;
    @FXML
    public void callexit(){
        System.exit(0);
    }
    @FXML
    public void callnextpane(){
        pane2.setVisible(true);
    }

    @FXML
    public void callbackpane(){
        pane2.setVisible(false);
    }
    @FXML
    public void changeScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();
            ((Stage)button.getScene().getWindow()).setScene(new Scene(root, 692, 514));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
    }
}
