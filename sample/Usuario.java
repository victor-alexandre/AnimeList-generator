package sample;

/**
 * Class of User properties
 */
public class Usuario {
    String name;
    AnimeList personalList = new AnimeList();

    /**
     * Usuario constructor.
     * @param name - name of the User
     *
     */
    public Usuario(String name){
        this.name = name;
    }

    /**
     * Method to set user name
     * @param name - name of the User
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get user name
     * @return  name of the user
     */
    public String getName() {
        return this.name;
    }
}
