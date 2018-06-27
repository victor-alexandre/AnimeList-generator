package sample;
import java.io.*;
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
import java.net.URL;
import java.util.Optional;
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

    @FXML
    private CheckBox checkBox1 ;
    @FXML
    private CheckBox checkBox2 ;
    @FXML
    private CheckBox checkBox3 ;
    @FXML
    private Button createbtn;


    ObservableList<Anime> anime = FXCollections.observableArrayList();

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
        Anime item;

        try{
            if(animefield.getText().equals("") || episodesfield.getText().equals("") || seasonfield.getText().equals("") || scorefield.getText().equals("")){
                throw new Excecoes("empty field");
            }
            item = new Anime(animefield.getText(), Integer.parseInt(episodesfield.getText()), Integer.parseInt(seasonfield.getText()), Double.parseDouble(scorefield.getText()));

        }catch (Excecoes emptyField){
            espacosVazios();
            return;
        }

        Main.usuario.personalList.addinLIST(item);
        for(int i = 0; i < Main.usuario.personalList.lista.size(); i++){
            System.out.println(Main.usuario.personalList.lista.get(i));
        }
        clearTexField();
        anime.add(item);
    }

    public void clearTexField(){
        animefield.setText("");
        episodesfield.setText("");
        seasonfield.setText("");
        scorefield.setText("");
    }

    @FXML
    public void removerDaLista(){

        try{
            if(episodesfield.getText().equals("") == false || seasonfield.getText().equals("") == false|| scorefield.getText().equals("")== false){
                throw new Excecoes("full field");
            }


        }catch (Excecoes fullField){
            espacosCheios();
            return;
        }

        try {
            boolean flag = false;
            String item = animefield.getText();
            for (int i = 0; i < Main.usuario.personalList.lista.size(); i++) {
                if (Main.usuario.personalList.lista.get(i).getName().equals(item)) {
                    Main.usuario.personalList.lista.remove(i);
                }
                if (anime.get(i).getName().equals(item)) {
                    anime.remove(i);
                    flag = true;
                }
            }
            if (flag == false) {
                throw new Excecoes("Item non existent");
            }
        } catch (Excecoes Nonexistent){
            itemInexistente();
        }


        clearTexField();
    }


    public  final ObservableList<Anime> getAnime()
    {
        //ObservableList<Anime> anime = FXCollections.observableArrayList();
        //anime.add(new Anime("Bokunopico", 5, 5 , 10.5));
        //anime.add(new Anime("toradora", 25, 95 , 20.5));
        //anime.add(new Anime("matrix", 15, 85 , 100.5));
        return anime;
    }

    public void espacosVazios(){
        Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
        aboutinfo.setTitle("Empty Fields");
        //aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("Please do not leave any empty field when adding an item");
        aboutinfo.showAndWait();
    }

    public void espacosCheios(){
        Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
        aboutinfo.setTitle("Wrong Fields");
        //aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("To remove an item you should write only its name");
        aboutinfo.showAndWait();
    }

    public void itemInexistente(){
        Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
        aboutinfo.setTitle("Item not found");
        //aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("This item doesn't exists in the list");
        aboutinfo.showAndWait();
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

    @FXML
    public void generateList() throws IOException {
        OutputStream os = new FileOutputStream(Main.usuario.name+ "list.html");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        Main.usuario.anime1 = anime;

        bw.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>SUPA ANIME LIST</title>\n" +
                " <!--  <link rel=\"stylesheet\" href=\"style.css\">     -->\n" +
                "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
                "    <link rel=\"import\" href=\"animu.html\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1 style=\"font-size:50px;\">Hunterigeno's AnimeList</h1>\n" +
                "    <p>\n" +
                "      <strong>Traps are no gay!!!!!!!!!!!!!!</strong> \n" +
                "    </p>\n" +
                "\n" +
                " <table>\n" +
                "    <tr style=\"color:#000080; background-color: lightblue;\">\n" +
                "      <th><center><b>Anime</b></center></th>\n" +
                "      <th><center><b>Temporadas</b></center></th>\n" +
                "      <th><center><b>Episódios</b></center></th>\n" +
                "      <th><center><b>Nota</b></center></th>\n" +
                "    </tr>\n\n" +
                "    ");

        for (int i = 0; i < Main.usuario.personalList.lista.size(); i++) {
 /*           bw.write("<tr>\n");
            bw.write("    <td>"+Main.usuario.personalList.lista.get(i).getName()+"</td>\n");
            bw.write("    <td>"+Main.usuario.personalList.lista.get(i).getSeasons()+"</td>\n");
            bw.write("    <td>"+Main.usuario.personalList.lista.get(i).getEpisodes()+"</td>\n");
            bw.write("    <td>"+Main.usuario.personalList.lista.get(i).getScore()+"</td>\n");
            bw.write("</tr>\n");
*/
            bw.write("<tr>\n");
            bw.write("    <td>"+Main.usuario.anime1.get(i).getName()+"</td>\n");
            bw.write("    <td>"+Main.usuario.anime1.get(i).getSeasons()+"</td>\n");
            bw.write("    <td>"+Main.usuario.anime1.get(i).getEpisodes()+"</td>\n");
            bw.write("    <td>"+Main.usuario.anime1.get(i).getScore()+"</td>\n");
            bw.write("</tr>\n");

        }

        bw.write("    </table>\n" +
                "  </body>\n" +
                "</html>\n" +
                "\n" +
                "\n");

        bw.close();
    }

}
