package sample;

import java.util.ArrayList;

public class AnimeList {
    int totalEpisodes;
    ArrayList <Anime> lista;

    public AnimeList(){
        lista = new ArrayList<Anime>();
        totalEpisodes = 0;
    }

    public void addinLIST(Anime item){
        lista.add(item);
        totalEpisodes = totalEpisodes + item.getEpisodes();
    }
}
