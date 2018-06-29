package sample;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Optional;


public class Main extends Application {
    static Usuario usuario = new Usuario("jao");
    static Stage mainstage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainstage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("janela1.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Ranking List");
        Scene scene1 = new Scene(root);
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();


        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(this::callJanela2);
        delay.play();


        primaryStage.setOnCloseRequest(confirmCloseEventHandler);

        Button closeButton = new Button("Close Application");
        closeButton.setOnAction(event ->
                primaryStage.fireEvent(
                        new WindowEvent(
                                primaryStage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        )
                )
        );


    }


    public static void main(String[] args) { launch(args);}


    public void callJanela2(ActionEvent event) {
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("janela2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root1);
        setStageScene(scene);
    }


    public static void setStageScene(Scene scene){
        mainstage.setScene(scene);

    }

    public static Stage getStage(){
        return mainstage;
    }

    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit?"
        );

        ButtonType exitbtn = new ButtonType("Exit");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        closeConfirmation.getButtonTypes().setAll(exitbtn, cancelbtn);

        closeConfirmation.setTitle("Confirm exit");
        closeConfirmation.setHeaderText(null);
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(mainstage);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();

        if (closeResponse.get() == cancelbtn){
            event.consume();
        }
    };
}
