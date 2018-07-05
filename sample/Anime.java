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
     * @param name - name of the anime
     * @param episodes - number of episodes of the anime
     * @param seasons - number of seasons of the anime
     * @param score - score that user has given to the anime
     */
    public Anime(String name, int episodes, int seasons, double score) {
        this.name = new SimpleStringProperty(name);
        this.episodes = new SimpleIntegerProperty(episodes);
        this.seasons = new SimpleIntegerProperty(seasons);
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Method to get anime name.
     * @return anime name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Method to set anime name.
     * @param name - new anime name
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }


    /**
     * Method to get anime number of episodes.
     * @return episodes of the anime
     */
    public int getEpisodes() {
        return episodes.get();
    }


    /**
     * Method to set anime number of episodes.
     * @param episodes - number of episodes
     */
    public void setEpisodes(int episodes) {
        this.episodes = new SimpleIntegerProperty(episodes);
    }


    /**
     * Method to get anime number of seasons.
     * @return number of seasons
     */
    public int getSeasons() {
        return seasons.get();
    }


    /**
     * Method to set anime number of seasons.
     * @param seasons - number of seasons
     */
    public void setSeasons(int seasons) {
        this.seasons = new SimpleIntegerProperty(seasons);
    }


    /**
     * Method to get anime score.
     * @return score
     */
    public double getScore() {
        return score.get();
    }


    /**
     * Method to set anime score.
     * @param score - score of the anime
     */
    public void setScore(double score) {
        this.score = new SimpleDoubleProperty(score);
    }

    /**
     * Method to print anime info.
     * @return String - with anime information
     */
    @Override
    public String toString() {
        return "Anime: " + name.get() + "\nepisodes: " + episodes.get() + "\nseasons: " + seasons.get() + "\nscore:" + score.get() + "\n";
    }
}
