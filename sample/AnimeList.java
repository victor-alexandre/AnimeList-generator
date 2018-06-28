package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AnimeList {

    ObservableList<Anime> list = FXCollections.observableArrayList();

    public AnimeList(){ super(); }

    public int totalItems(){
        return list.size();
    }

    public int totalEpisodes(){
        int episodes = 0;

        for(int i = 0; i < list.size(); i++){
            episodes = episodes + list.get(i).getEpisodes();
        }

        return episodes;
    }
}
