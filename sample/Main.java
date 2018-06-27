package sample;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;



public class Main extends Application {
    static Usuario usuario = new Usuario("jao");

    static Stage mainstage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainstage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("janela1.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ranking List");
        Scene scene1 = new Scene(root);
        primaryStage.setScene(scene1);
        //primaryStage.setScene(new Scene(root, 692,514) );
        primaryStage.setResizable(false);
        primaryStage.show();



        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(this::callJanela2);
        delay.play();
    }



    public static void main(String[] args) { launch(args);}

    public void callJanela1(){
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("janela1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root1);
        setStageScene(scene);

        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(this::callJanela2);
        delay.play();
    }

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
}
