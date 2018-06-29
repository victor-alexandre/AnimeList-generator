package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class of Animelist properties
 */
public class AnimeList {

    ObservableList<Anime> list = FXCollections.observableArrayList();

    /**
     * Animelist constructor
     */
    public AnimeList(){ super(); }

    /**
     * Method to return number of items in the list
     * @return
     */
    public int totalItems(){
        return list.size();
    }

    /**
     * Method to return the number of all episodes stored in the list
     * @return
     */
    public int totalEpisodes(){
        int episodes = 0;

        for(int i = 0; i < list.size(); i++){
            episodes = episodes + list.get(i).getEpisodes();
        }

        return episodes;
    }
}
