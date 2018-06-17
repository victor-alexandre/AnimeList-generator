package sample;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    Label visualfeedback;

    @FXML
    TextField usernamefield;
    @FXML
    AnchorPane pane1,pane2,pane3,pane4;

    @FXML
    SplitPane splitpane1;

    @FXML
    public void switchScene2(){//vai da tela inicial para a tela do username
        pane3.setVisible(true);
        pane2.setVisible(false);
    }

    @FXML
    public void switchScene3(){//vai da tela do username para o programa
        splitpane1.setVisible(true);
        pane3.setVisible(false);
        System.out.println("username: " + usernamefield.getText());
    }

    @FXML
    public void switchScene1(){// volta da tela do username para a primeira tela
        pane2.setVisible(true);
        pane3.setVisible(false);
    }

    @FXML
    public void enterAbout(){
        pane4.setVisible(true);
    }

    @FXML
    public void exitAbout(){
        pane4.setVisible(false);
    }

}

