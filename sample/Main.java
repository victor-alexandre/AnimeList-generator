package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ranking List");
        Scene cena1 = new Scene(root, 692, 514);
        primaryStage.setScene(cena1);
        //primaryStage.setScene(new Scene(root, 692,514) );
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }
}