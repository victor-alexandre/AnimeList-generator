package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Controller of second window.
 */
public class Janela2Controller extends Janela implements Initializable {

    public javafx.scene.control.TextField usernameField;

    /**
     * Method to initialize the class.
     * @param location -
     * @param resources -
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method to switch between windows.
     * @param event - The event is when a function or a button call this method
     * @throws IOException -
     */
    @FXML
    public void switchwindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("janela3.fxml"));

        Scene scene = new Scene(root);

        Main.setStageScene(scene);

        enterUser();
    }
    /**
     * Method to show the creator's name.
     * @param event - The event is when the user clicks in the About button
     * @throws IOException -
     */
    @FXML
    public void about(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Program made by: Victor Alexandre & Matheus Diniz");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * Method to get the username from usernamefield.
     */

    @FXML
    public void enterUser() {
        Main.usuario.setName(usernameField.getText());
       // System.out.println("username: " + Main.usuario.name);
    }

    /**
     * Method to end the application.
     * @param event- The event is when the user clicks in the exit button
     * @throws IOException -
     */
    @FXML
    public void exitProgram(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm exit");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

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
