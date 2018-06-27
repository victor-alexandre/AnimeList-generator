package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Usuario {
    String name;
    AnimeList personalList = new AnimeList();
    ObservableList<Anime> anime1 = FXCollections.observableArrayList();

    public Usuario(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
