package sample;

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Janela2Controller extends Janela implements Initializable {

    public javafx.scene.control.TextField SATANAS;

    @FXML
    public void switchwindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("janela3.fxml"));

        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        //stage.setScene(scene);
        Main.setStagescene(scene);
        //primaryStage.setResizable(false);
        //primaryStage.show();
       // enterUser();
    }
    @FXML
    public void about(ActionEvent event) throws IOException{
        Alert aboutinfo = new Alert(Alert.AlertType.INFORMATION);
        aboutinfo.setTitle("About");
        aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("Programa feito por universitários gugudada");
        aboutinfo.showAndWait();
    }





    @FXML
    public void enterUser() {//vai da tela do username para o programa

        Main.abc.setName(SATANAS.getText());
        System.out.println("username: " + Main.abc.name);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
