package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Anime {

    SimpleStringProperty name;
    SimpleIntegerProperty episodes;
    SimpleIntegerProperty seasons;
    SimpleDoubleProperty score;


    public Anime(String name, int episodes, int seasons, double score) {
        this.name = new SimpleStringProperty(name);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.seasons = new SimpleIntegerProperty(seasons);
        this.score = new SimpleDoubleProperty(score);
    }


    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public int getEpisodes() {
        return episodes.get();
    }

    public void setEpisodes(int episodes) {
        this.episodes = new SimpleIntegerProperty(episodes);
    }

    public int getSeasons() {
        return seasons.get();
    }

    public void setSeasons(int seasons) {
        this.seasons = new SimpleIntegerProperty(seasons);
    }

    public double getScore() {
        return score.get();
    }

    public void setScore(double score) {
        this.score = new SimpleDoubleProperty(score);
    }


    @Override
    public String toString() {
        return "Anime: " + name.get() + "\nepisodes: " + episodes.get() + "\nseasons: " + seasons.get() + "\nscore:" + score.get() + "\n";
    }
}
