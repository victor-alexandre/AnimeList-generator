package sample;
import java.awt.Label;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    @FXML
    private javafx.scene.control.Label feedbacklabel;

    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureCheckBox(checkBox1);
        configureCheckBox(checkBox2);
        configureCheckBox(checkBox3);

        feedbacklabel.setText("Choose a theme before creating your list");
        createbtn.setDisable(true);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
                createbtn.setDisable(false);
                feedbacklabel.setText("");
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                createbtn.setDisable(true);
                feedbacklabel.setText("Choose a theme before creating your list");

            }
        });

        animecol.setCellValueFactory(new PropertyValueFactory<Anime, String>("name"));
        episodescol.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("episodes"));
        seasoncol.setCellValueFactory(new PropertyValueFactory<Anime, Integer>("seasons"));
        scorecol.setCellValueFactory(new PropertyValueFactory<Anime, Double>("score"));

        tableView.setItems(Main.usuario.personalList.list);
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
        Main.setStageScene(scene);
    }


    @FXML
    public void switchwindow(ActionEvent event) throws IOException {}


    @FXML
    public void about(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Program made by: Victor Alexandre & Matheus Diniz");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    @FXML
    public void adicionarNaLista(){
        Anime item;
        int i = 0;

        try{
            if(animefield.getText().equals("") || episodesfield.getText().equals("") || seasonfield.getText().equals("") || scorefield.getText().equals("")){
                throw new Excecoes("empty field");
            }

            int intValue =0;
            int intValue2 =0;
            double doubleValue = 0;
            try {
                intValue = Integer.parseInt(episodesfield.getText());
            }catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid caracter");
                alert.setHeaderText(null);
                alert.setContentText("Please use only integer numbers in episodes");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                return;
            }
            try {
                intValue2 = Integer.parseInt(seasonfield.getText());
            }catch (NumberFormatException e) {
                System.out.println(intValue);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid caracter");
                alert.setHeaderText(null);
                alert.setContentText("Please use only integer numbers in the season field");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                return;
            }
            try {
                doubleValue = Double.parseDouble(scorefield.getText());
            }catch (NumberFormatException e) {
                System.out.println(intValue);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid caracter");
                alert.setHeaderText(null);
                alert.setContentText("Please use only numbers in the score field");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.showAndWait();
                return;
            }

            item = new Anime(animefield.getText(), Integer.parseInt(episodesfield.getText()), Integer.parseInt(seasonfield.getText()), Double.parseDouble(scorefield.getText()));

        }catch (Excecoes emptyField){
            espacosVazios();
            feedbacklabel.setText("");
            return;
        }


        try {
            for (i = 0; i < Main.usuario.personalList.list.size(); i++) {
                if (Main.usuario.personalList.list.get(i).getName().equals(item.getName())) {
                    throw new Excecoes("Anime já existente");
                }
            }
        }catch (Excecoes alreadyexists){
            itemJAexistente(i, item);
            return;
        }

        Main.usuario.personalList.list.add(item);
        clearTexField();

        feedbacklabel.setText("      Item successfully added!!!");

    }

    public void clearTexField(){
        animefield.setText("");
        episodesfield.setText("");
        seasonfield.setText("");
        scorefield.setText("");
    }

    @FXML
    public void clearScreen(){
        animefield.setText("");
        episodesfield.setText("");
        seasonfield.setText("");
        scorefield.setText("");
        feedbacklabel.setText("");
    }


    public void espacosVazios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please do not leave any empty field when adding an item");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }



    public void itemJAexistente(int i, Anime item){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" This item already exists in your list");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to edit it?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            Main.usuario.personalList.list.get(i).setName(item.getName());
            Main.usuario.personalList.list.get(i).setSeasons(item.getSeasons());
            Main.usuario.personalList.list.get(i).setEpisodes(item.getEpisodes());
            Main.usuario.personalList.list.get(i).setScore(item.getScore());
            clearTexField();

            //jogadinha pra dar refresh na lista
            tableView.getColumns().get(0).setVisible(false);
            tableView.getColumns().get(0).setVisible(true);
            feedbacklabel.setText("    Item successfully edited!!!");
        }

        else {
            return;
        }
    }


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
        } else {
            return;
        }
    }

    @FXML
    public void generateList() throws IOException {
        OutputStream os = new FileOutputStream("YourAnimeList.html");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String tema;

        if(checkBox1.isSelected()){
            tema = "normal.css";
        }else if(checkBox2.isSelected()){
            tema = "dark.css";
        }else{
            tema = "happy.css";
        }

        bw.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>SUPA ANIME LIST</title>\n" +
                "    <link rel=\"stylesheet\" href=\" "+ tema +"\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1 style=\"font-size:50px;\">"+ Main.usuario.name +"'s AnimeList</h1>\n" +
                "    <p  style=\"color:#ff0000\">\n" +
                "      <strong> Total de items: "+ Main.usuario.personalList.totalItems() +  "<br />Total episodes: "+ Main.usuario.personalList.totalEpisodes()+ "</strong> \n" +
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

        for (int i = 0; i < Main.usuario.personalList.list.size(); i++) {
            bw.write("<tr>\n");
            bw.write("    <td><!---->"+Main.usuario.personalList.list.get(i).getName()+"<!----></td>\n");
            bw.write("    <td><!---->"+Main.usuario.personalList.list.get(i).getSeasons()+"<!----></td>\n");
            bw.write("    <td><!---->"+Main.usuario.personalList.list.get(i).getEpisodes()+"<!----></td>\n");
            bw.write("    <td><!---->"+Main.usuario.personalList.list.get(i).getScore()+"<!----></td>\n");
            bw.write("</tr>\n");
        }

        bw.write("    </table>\n" +
                "  </body>\n" +
                "</html>\n" +
                "\n" +
                "\n");

        bw.close();
        feedbacklabel.setText("List successfully created!");
    }

    @FXML
    public void importList() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       //
        alert.setTitle("Confirm new import");
        alert.setHeaderText(null);
        alert.setContentText("If you import a new list your current data will be overwritten. Do you want to proceed?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            Main.usuario.personalList.list.clear();
            //Deleto todos os dados atuais e trabalho só com a lista importada!!

            FileChooser filechoosen = new FileChooser();

            File file = filechoosen.showOpenDialog(Main.getStage());
            if(file == null){//evitar null pointer exception quando cancela a escolha de arquivo
                return;
            }

            String filename = file.getName();
            String[] extention = filename.split("\\.");

            if (extention.length <= 1 || extention[1].equals("html") == false) {
                escolhaArquivo();
                return;
            } else {

                FileInputStream is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String string = "";

                for (int i = 0; i < 24; i++){
                    string = br.readLine();
                    System.out.println((i+1)+string);
                }
                //for acima lê até a linha 24 do meu html

                String[] parts = string.split("<!---->");
                //aqui eu parto a primeira linha da tabela do html

                while(parts.length > 1){
                    String name;
                    int episodes;
                    int seasons;
                    double score;
                    System.out.println("\n\n\n\n"+string);

                    name = parts[1];

                    string = br.readLine();
                    parts = string.split("<!---->");

                    seasons = Integer.parseInt(parts[1]);

                    string = br.readLine();
                    parts = string.split("<!---->");

                    episodes = Integer.parseInt(parts[1]);

                    string = br.readLine();
                    parts = string.split("<!---->");

                    score = Double.parseDouble(parts[1]);

                    string = br.readLine();
                    string = br.readLine();
                    string = br.readLine();
                    parts = string.split("<!---->");
                    //parto essa linha para fazer a proxima verificaçao

                    Main.usuario.personalList.list.add(new Anime(name, episodes, seasons, score));

                }
                br.close();
                feedbacklabel.setText(" List successfully imported!");
            }

        } else {
            return;
        }

    }

    @FXML
    public void addImportList() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirm import");
        alert.setHeaderText(null);
        alert.setContentText("All items from imported list will be added into your list. Do you want to proceed?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            FileChooser filechoosen = new FileChooser();
            File file = filechoosen.showOpenDialog(Main.getStage());
            if(file == null){//evitar null pointer exception quando cancela a escolha de arquivo
                return;
            }

            String filename = file.getName();
            String[] extention = filename.split("\\.");

            if (extention.length <= 1 ||extention[1].equals("html") == false) {
                escolhaArquivo();
                return;
            } else {

                FileInputStream is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String string = "";

                for (int i = 0; i < 24; i++){
                    string = br.readLine();
                }
                //for acima lê até a linha 24 do meu html

                String[] parts = string.split("<!---->");
                //aqui eu parto a primeira linha da tabela do html

                while(parts.length > 1){
                    String name;
                    int episodes;
                    int seasons;
                    double score;

                    name = parts[1];

                    string = br.readLine();
                    parts = string.split("<!---->");

                    seasons = Integer.parseInt(parts[1]);

                    string = br.readLine();
                    parts = string.split("<!---->");

                    episodes = Integer.parseInt(parts[1]);

                    string = br.readLine();
                    parts = string.split("<!---->");

                    score = Double.parseDouble(parts[1]);

                    string = br.readLine();
                    string = br.readLine();
                    string = br.readLine();
                    parts = string.split("<!---->");
                    //parto essa linha para fazer a proxima verificaçao

                    Main.usuario.personalList.list.add(new Anime(name, episodes, seasons, score));

                }
                br.close();
                feedbacklabel.setText(" All items were successfully imported!");
            }

        } else {
            return;
        }

    }


    public void escolhaArquivo(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid File");
        alert.setHeaderText(null);

        alert.setContentText("Please choose only html files");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    @FXML
    public void deleteAll(){
        if(Main.usuario.personalList.totalItems() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid operation");
            alert.setHeaderText(null);
            alert.setContentText("There is no data to remove");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }else {


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want delete all items?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

            ButtonType yesbtn = new ButtonType("Yes");
            ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(yesbtn, cancelbtn);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == yesbtn) {
                Main.usuario.personalList.list.clear();
                feedbacklabel.setText("All items were successfully removed!");

            } else {
                return;
            }
        }
    }

    @FXML
    public void customdelete(){
        if(Main.usuario.personalList.totalItems() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid operation");
            alert.setHeaderText(null);
            alert.setContentText("There is no data to remove");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }else if(tableView.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid operation");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to remove");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }else {

            String name = tableView.getSelectionModel().getSelectedItem().getName();

            for (int i = 0; i < Main.usuario.personalList.totalItems(); i++) {
                if (name.equals(Main.usuario.personalList.list.get(i).getName())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("Confirm deletion");
                    alert.setHeaderText(null);
                    alert.setContentText("Do you want to remove" + name + " from your list?");
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

                    ButtonType yesbtn = new ButtonType("Yes");
                    ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                    alert.getButtonTypes().setAll(yesbtn, cancelbtn);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == yesbtn) {
                        Main.usuario.personalList.list.remove(i);
                        feedbacklabel.setText("   "+ name + " was removed sucessfully!");

                    } else {
                        return;
                    }

                    return;
                }
            }
        }
    }

}
