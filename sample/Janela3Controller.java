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

        feedbacklabel.setText("Escolha um tema para poder gerar a lista");
        createbtn.setDisable(true);

        numCheckBoxesSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(true));
                createbtn.setDisable(false);
                feedbacklabel.setText("");
            } else {
                unselectedCheckBoxes.forEach(cb -> cb.setDisable(false));
                createbtn.setDisable(true);
                feedbacklabel.setText("Escolha um tema para poder gerar a lista");

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
        setStageScene(scene);
    }


    @FXML
    public void switchwindow(ActionEvent event) throws IOException {}


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

                Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
                aboutinfo.setTitle("Invalid caracter");
                //aboutinfo.setHeaderText("teste");
                aboutinfo.setContentText("Please use just numbers in episodes");
                aboutinfo.showAndWait();
                return;
            }
            try {
                intValue2 = Integer.parseInt(seasonfield.getText());
            }catch (NumberFormatException e) {
                System.out.println(intValue);
                Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
                aboutinfo.setTitle("Invalid caracter");
                //aboutinfo.setHeaderText("teste");
                aboutinfo.setContentText("Please use just numbers in seasons");
                aboutinfo.showAndWait();
                return;
            }
            try {
                doubleValue = Integer.parseInt(scorefield.getText());
            }catch (NumberFormatException e) {
                System.out.println(intValue);
                Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
                aboutinfo.setTitle("Invalid caracter");
                //aboutinfo.setHeaderText("teste");
                aboutinfo.setContentText("Please use just numbers in score");
                aboutinfo.showAndWait();
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

        feedbacklabel.setText("      Item adicionado com sucesso!!!");

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
            feedbacklabel.setText("");
            return;
        }

        try {
            boolean flag = false;
            String item = animefield.getText();
            for (int i = 0; i < Main.usuario.personalList.list.size(); i++) {
                if (Main.usuario.personalList.list.get(i).getName().equals(item)) {
                    Main.usuario.personalList.list.remove(i);
                    flag = true;
                }
            }
            if (flag == false) {
                throw new Excecoes("Item non existent");
            }
        } catch (Excecoes Nonexistent){
            itemInexistente();
            feedbacklabel.setText("");
        }

        feedbacklabel.setText("     Item removido com sucesso!!!");
        clearTexField();
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

    public void itemJAexistente(int i, Anime item){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("This item already exists in your list");
        alert.setContentText("Do you want to edit it?");

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
            tableView.getColumns().get(0).setVisible(false);
            tableView.getColumns().get(0).setVisible(true);
            feedbacklabel.setText("Item editado com sucesso!!!");
        }

        else {
            return;
        }
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
        feedbacklabel.setText("Lista gerada com sucesso!!!");
    }

    @FXML
    public void importList() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm new Import");
        alert.setContentText("If you import a new list your current data will be overwritten");

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            Main.usuario.personalList.list.clear();
            //Deleto todos os dados atuais e trabalho só com a lista importada!!

            FileChooser filechoosen = new FileChooser();
            File file = filechoosen.showOpenDialog(Main.getStage());

            String filename = file.getName();
            String[] extention = filename.split("\\.");
          //  System.out.println("---------------------"+filename);

            if (file == null  || extention[1].equals("html") == false) {
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
                feedbacklabel.setText("Lista importada com sucesso!!!");
            }

        } else {
            return;
        }

    }

    @FXML
    public void addImportList() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm new Import");
        alert.setContentText("Are you sure you want to add the following list");

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            FileChooser filechoosen = new FileChooser();
            File file = filechoosen.showOpenDialog(Main.getStage());

            String filename = file.getName();
            String[] extention = filename.split("\\.");
           // System.out.println("---------------------"+filename);

            if (file == null  || extention[1].equals("html") == false) {
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
                feedbacklabel.setText("Items importados com sucesso!!!");
            }

        } else {
            return;
        }

    }


    public void escolhaArquivo(){
        Alert aboutinfo = new Alert(Alert.AlertType.ERROR);
        aboutinfo.setTitle("Invalid File");
        //aboutinfo.setHeaderText("teste");
        aboutinfo.setContentText("Please choose only html files");
        aboutinfo.showAndWait();
    }

    @FXML
    public void deleteAll(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want delete all items from table?");

        ButtonType yesbtn = new ButtonType("Yes");
        ButtonType cancelbtn = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(yesbtn, cancelbtn);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == yesbtn){
            Main.usuario.personalList.list.clear();
            feedbacklabel.setText("Items removidos com sucesso!!!");

        } else {
            return;
        }

    }

    @FXML
    public void customdelete(){
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        for(int i = 0; i < Main.usuario.personalList.totalItems(); i++){
            if(name.equals(Main.usuario.personalList.list.get(i).getName())){
                Main.usuario.personalList.list.remove(i);
                feedbacklabel.setText(name + " removido com sucesso!!!");
                return;
            }
        }
    }

}
