package sample;

/**
 * Class of User properties
 */
public class Usuario {
    String name;
    AnimeList personalList = new AnimeList();

    /**
     * Usuario constructor.
     * @param name
     */
    public Usuario(String name){
        this.name = name;
    }

    /**
     * Method to set user name
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method to get user name
     */

    public String getName() {
        return this.name;
    }
}
