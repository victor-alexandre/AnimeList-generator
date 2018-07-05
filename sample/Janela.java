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

/**
 * Interface for window.
 */

public abstract class Janela {

    /**
     * Method to show the creator's name.
     * @param event- The event is when the user clicks in the About button
     * @throws IOException -
     */
    abstract public void about(ActionEvent event)throws IOException;

    /**
     * Method to switch between windows.
     * @param event- The event is when a function or a button call this method
     * @throws IOException -
     */
    abstract public void switchwindow(ActionEvent event)throws IOException;


    /**
     * Method to end the application.
     * @param event- The event is when the user clicks in the exit button
     * @throws IOException -
     */
    abstract void exitProgram(ActionEvent event) throws IOException;
}
