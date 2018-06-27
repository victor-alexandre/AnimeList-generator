package sample;
import java.util.ArrayList;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.setStageScene;

public class Janela3Controller extends Janela implements Initializable {
    @FXML private TableView<Anime> tableView;
    @FXML private TableColumn<Anime, String> animecol;
    @FXML private TableColumn<Anime, Integer> seasoncol;
    @FXML private TableColumn<Anime, Integer> episodescol;
    @FXML private TableColumn<Anime, Double> scorecol;


    public TextField animefield;
    public TextField seasonfield;
    public TextField episodesfield;
    public TextField scorefield;
   // public TableColumn rankcol;
   // public TableColumn animecol;
   // public TableColumn seasoncol;
  //  public TableColumn episodescol;
  //  public TableColumn scorecol;
    @FXML
    private CheckBox checkBox1 ;
    @FXML
    private CheckBox checkBox2 ;
    @FXML
    private CheckBox checkBox3 ;
    @FXML
    private Button createbtn;
    
    
    

    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureCheckBox(checkBox1);
        configureCheckBox(checkBox2);
        configureCheckBox(checkBox3);

        createbtn.setDisable(true);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
                createbtn.setDisable(false);
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                createbtn.setDisable(true);

            }
        });

        animecol.setCellValueFactory(new PropertyValueFactory<Anime, String>("name"));
        episodescol.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("episodes"));
        seasoncol.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("seasons"));
        scorecol.setCellValueFactory(new PropertyValueFactory<Anime, Double>("score"));

        tableView.setItems(getAnime());

    }

    private void configureCheckBox(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedCheckBoxes.add(checkBox);
        } else {
            unselectedCheckBoxes.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedCheckBoxes.remove(checkBox);
                selectedCheckBoxes.add(checkBox);
            } else {
                selectedCheckBoxes.remove(checkBox);
                unselectedCheckBoxes.add(checkBox);
            }

        });

    }
    @FXML
    public void changeUsername(){
            Parent root1 = null;
            try {
                root1 = FXMLLoader.load(getClass().getResource("janela2.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root1);
            setStageScene(scene);
            }


    @FXML
    public void switchwindow(ActionEvent event) throws IOException { ;
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
    public void adicionarNaLista(){
        Anime item = new Anime(animefield.getText(), Integer.parseInt(episodesfield.getText()), Integer.parseInt(seasonfield.getText()), Double.parseDouble(scorefield.getText()));
        Main.usuario.personalList.addinLIST(item);
        for(int i = 0; i < Main.usuario.personalList.lista.size(); i++){
            System.out.println(Main.usuario.personalList.lista.get(i));

            //rankcol.setCellFactory(TextFieldTableCell.forTableColumn());
            //animecol.setCellFactory(TextFieldTableCell.forTableColumn());
           // animecol.setId(Main.usuario.personalList.lista.get(i).name);
           // seasoncol.setText(Integer.toString(Main.usuario.personalList.lista.get(i).seasons));
           // episodescol.setText(Integer.toString(Main.usuario.personalList.lista.get(i).episodes));
          //  scorecol.setText(Double.toString(Main.usuario.personalList.lista.get(i).score));

        }
    }

    public final ObservableList<Anime> getAnime()
    {
        ObservableList<Anime> anime = FXCollections.observableArrayList();
        anime.add(new Anime("Bokunopico", 5, 5 , 10.5));
        anime.add(new Anime("toradora", 25, 95 , 20.5));
        anime.add(new Anime("matrix", 15, 85 , 100.5));
        return anime;
    }

}
