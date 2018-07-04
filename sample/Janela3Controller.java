package sample;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

import java.awt.*;
import java.net.URL;

/**
 * Controller of the third window.
 */

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
    private Label feedbacklabel;

    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();

    private IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);

    private final int maxNumSelected =  1;

     File homeDir = new File(System.getProperty("user.home"));
     File dir = new File(homeDir, "YourAnimeList");
     File subDir = new File(dir, "src");




    /**
     * Function to initialize the class. Here some items, like table items, labels and buttons, are pre-configured.
     * @param location
     * @param resources
     */
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

        if (!dir.exists() && !dir.mkdirs()) {
            try {

                throw new IOException("Unable to create " + dir.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File[] dir_contents = dir.listFiles();


        //se já existirem os arquivos não é necessário baixar eles novamente!!
        if(dir_contents.length < 2) {
            subDir.mkdir();
            try {
                saveUrl("exemplo.html", "https://cdn.discordapp.com/attachments/405086253420118046/462614416068837397/HunteronList.html", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                saveUrl("city.jpg", "https://cdn.discordapp.com/attachments/449320849812619315/463847793446879233/Konachan.com_-_261020_sample.jpg", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saveUrl("normal.jpg", "https://cdn.discordapp.com/attachments/449320849812619315/463847796181565443/Konachan.com-_265452_sample.jpg", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saveUrl("dark.jpg", "https://cdn.discordapp.com/attachments/449320849812619315/463847903530844161/Konachan.com-_264691_sample.jpg", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                saveUrl("city.css", "https://cdn.discordapp.com/attachments/462607449258721290/463854592049020938/city.css", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saveUrl("normal.css", "https://cdn.discordapp.com/attachments/462607449258721290/463854597207752715/normal.css", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saveUrl("dark.css", "https://cdn.discordapp.com/attachments/462607449258721290/463854594557083649/dark.css", subDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Method to make only one checkbox available to be selected.
     * @param checkBox
     */
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

    /**
     * Method to go to the second window where user can write its name again.
     */
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


    /**
     * Method to switch between windows.
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchwindow(ActionEvent event) throws IOException {}

    /**
     * Method to show the creator's name.
     * @param event
     * @throws IOException
     */
    @FXML
    public void about(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Program made by: Victor Alexandre & Matheus Diniz");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * Method to add an item in the list. It will verify if there is an equal item, if yes it will ask to user
     * to overwrite it or not, if its not equal the method will add into the userlist.
     */
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

        feedbacklabel.setText(item.getName() + " successfully added!!!");

    }

    /**
     * Method to clear all text fields (fields where user can write its data).
     */
    public void clearTexField(){
        animefield.setText("");
        episodesfield.setText("");
        seasonfield.setText("");
        scorefield.setText("");
    }

    /**
     * Method to clear all text fields and clear the feedback label.
     */

    @FXML
    public void clearScreen(){
        clearTexField();
        feedbacklabel.setText("");
    }

    /**
     * Method to create an popup to inform user that there are empty fields.
     */
    public void espacosVazios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Empty Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please do not leave any empty field when adding an item");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }


    /**
     * Method to manage when user tries to insert an item that already exists in the list. The method will ask if the user want
     * to overwrite the item or not.
     * @param i
     * @param item
     */
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
            feedbacklabel.setText(item.getName() + " successfully edited!!!");
        }

        else {
            return;
        }
    }

    /**
     * Method to end the application.
     * @param event
     * @throws IOException
     */
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

    /**
     * Method to create an HTML file with user's data, in the folder YourAnimeList located in the user's home folder
      * @throws IOException.
     */
    @FXML
    public void generateList() throws IOException {
        if(Main.usuario.personalList.totalItems() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid operation");
            alert.setHeaderText(null);
            alert.setContentText("Please add some items before creating your list");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        }else {

            File list = new File(dir, Main.usuario.getName()+".html");
            OutputStream os = new FileOutputStream(list);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);


            String tema;

            if (checkBox1.isSelected()) {
                tema = "normal.css";
            } else if (checkBox2.isSelected()) {
                tema = "city.css";
            } else {
                tema = "dark.css";
            }

            bw.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "    <title>SUPA ANIME LIST</title>\n" +
                    "    <link rel=\"stylesheet\" href=\" " +"src/" + tema + "\">\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h1 style=\"font-size:50px;\">" + Main.usuario.name + "'s AnimeList</h1>\n" +
                    "    <p  style=\"color:#ff0000\">\n" +
                    "      <strong> Total Items: " + Main.usuario.personalList.totalItems() + "<br />Total Episodes: " + Main.usuario.personalList.totalEpisodes() + "</strong> \n" +
                    "    </p>\n" +
                    "\n" +
                    " <table>\n" +
                    "    <tr style=\"color:#000080; background-color: lightblue;\">\n" +
                    "      <th><center><b>Anime</b></center></th>\n" +
                    "      <th><center><b>Seasons</b></center></th>\n" +
                    "      <th><center><b>Episodes</b></center></th>\n" +
                    "      <th><center><b>Score</b></center></th>\n" +
                    "    </tr>\n\n" +
                    "    ");

            for (int i = 0; i < Main.usuario.personalList.list.size(); i++) {
                bw.write("<tr>\n");
                bw.write("    <td><!---->" + Main.usuario.personalList.list.get(i).getName() + "<!----></td>\n");
                bw.write("    <td><!---->" + Main.usuario.personalList.list.get(i).getSeasons() + "<!----></td>\n");
                bw.write("    <td><!---->" + Main.usuario.personalList.list.get(i).getEpisodes() + "<!----></td>\n");
                bw.write("    <td><!---->" + Main.usuario.personalList.list.get(i).getScore() + "<!----></td>\n");
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
    }

    /**
     * Function to open the html file in the default browser.
     */
    @FXML
    public void showListOnBrowser () throws URISyntaxException {
        if (Main.usuario.personalList.totalItems() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid operation");
            alert.setHeaderText(null);
            alert.setContentText("Please add some items before viewing your list");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return;
        } else {
            File htmlFile = new File(dir.getPath() + "/" + Main.usuario.getName() + ".html");

            if( Desktop.isDesktopSupported() )
            {
                new Thread(() -> {
                    try {
                        Desktop.getDesktop().browse( new URI( htmlFile.toURI().toString() ) );
                    } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }).start();
            }
/*
            Desktop desktop = null;
            desktop = Desktop.getDesktop();
            URI url = null;

            Main.getHostServices().showDocument("http://www.yahoo.com");

            HostServices services = getHostServices();


            File htmlFile = new File(dir.getPath() + "/" + Main.usuario.getName() + ".html");
            url = new URI(htmlFile.toURI().toString());
            Main.getHostServices().showDocument(htmlFile.toURI().toString());

            try {
                desktop.browse(url);
            } catch (IOException e) {
                e.printStackTrace();
                //return;

            }*/
        }
    }


    /**
     * Method to import a file with a list. This method will DELETE all of user data and will show the items from
     * imported list.
      * @throws IOException
     */
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

                Main.usuario.personalList.list.clear();
                //Deleto todos os dados atuais e trabalho só com a lista importada!!

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
                feedbacklabel.setText(" List successfully imported!");
            }

        } else {
            return;
        }

    }

    /**
     * Method to import a file with a list. This method will NOT delete user's data, it will increment user's list with data from
     * imported list.
     * @throws IOException
     */
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

    /**
     * Method to show an popup when  user tries to import an invalid file.
     */
    public void escolhaArquivo(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid File");
        alert.setHeaderText(null);

        alert.setContentText("Please choose only html files");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * Method to delet all items from user list.
     */
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

    /**
     * Method to delete only the selected item in the table.
     */
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
                        feedbacklabel.setText( name + " was sucessfully removed!");

                    } else {
                        return;
                    }

                    return;
                }
            }
        }
    }


    /**
     * Method to download the images and css files, and put them in the directory of the user animelist
     * @param filename
     * @param urlString
     * @param dir
     * @throws MalformedURLException
     * @throws IOException
     */
    public void saveUrl(final String filename, final String urlString, File dir)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;


        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(dir.toPath()+ "/"+filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }


}
