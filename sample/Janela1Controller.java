package sample;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Janela1Controller extends Janela implements Initializable {
    @FXML
    public void switchwindow(ActionEvent event) throws IOException {

    }

    @FXML
    public void about(ActionEvent event) throws IOException {
        Alert aboutinfo = new Alert(Alert.AlertType.INFORMATION);
        aboutinfo.setTitle("About");
        aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("Programa feito por universitários gugudada");
        aboutinfo.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
