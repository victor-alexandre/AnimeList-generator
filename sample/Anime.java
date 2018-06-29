package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class with anime properties.
 */

public class Anime {

    SimpleStringProperty name;
    SimpleIntegerProperty episodes;
    SimpleIntegerProperty seasons;
    SimpleDoubleProperty score;

    /**
     * Anime constructor.
     * @param name
     * @param episodes
     * @param seasons
     * @param score
     */
    public Anime(String name, int episodes, int seasons, double score) {
        this.name = new SimpleStringProperty(name);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.seasons = new SimpleIntegerProperty(seasons);
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Method to get anime name.
     * @return
     */
    public String getName() {
        return name.get();
    }
    /**
     * Method to set anime name.
     * @return
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    /**
     * Method to get anime number of episodes.
     * @return
     */
    public int getEpisodes() {
        return episodes.get();
    }
    /**
     * Method to set anime number of episodes.
     * @return
     */
    public void setEpisodes(int episodes) {
        this.episodes = new SimpleIntegerProperty(episodes);
    }
    /**
     * Method to get anime number of seasons.
     * @return
     */
    public int getSeasons() {
        return seasons.get();
    }
    /**
     * Method to set anime number of seasons.
     * @return
     */
    public void setSeasons(int seasons) {
        this.seasons = new SimpleIntegerProperty(seasons);
    }
    /**
     * Method to get anime score.
     * @return
     */
    public double getScore() {
        return score.get();
    }
    /**
     * Method to set anime score.
     * @return
     */
    public void setScore(double score) {
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Method to print anime info.
     * @return
     */
    @Override
    public String toString() {
        return "Anime: " + name.get() + "\nepisodes: " + episodes.get() + "\nseasons: " + seasons.get() + "\nscore:" + score.get() + "\n";
    }
}
