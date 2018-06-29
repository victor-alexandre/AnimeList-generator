package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;



import java.awt.*;
import java.io.IOException;

public abstract class Janela {

    abstract public void about(ActionEvent event)throws IOException;

    abstract public void switchwindow(ActionEvent event)throws IOException;

    abstract void exitProgram(ActionEvent event) throws IOException;
}
