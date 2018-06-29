package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Main.setStageScene;

public class Janela2Controller extends Janela implements Initializable {

    public javafx.scene.control.TextField SATANAS;

    @FXML
    public void switchwindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("janela3.fxml"));

        Scene scene = new Scene(root);

        Main.setStageScene(scene);

        enterUser();
    }

    @FXML
    public void about(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("teste");
        alert.setContentText("Programa feito por universitários gugudada");
        alert.showAndWait();
    }



    @FXML
    public void enterUser() {
        Main.usuario.setName(SATANAS.getText());
        System.out.println("username: " + Main.usuario.name);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void exitProgram(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm exit");
        alert.setContentText("Are you sure you want to exit?");

        ButtonType exitbtn = new ButtonType("Exit");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(exitbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == exitbtn){
            Main.getStage().close();
        }

        else {
            return;
        }
    }





}
