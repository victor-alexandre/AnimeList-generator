package sample;

public class User {
    String name;
    AnimeList personalList = new AnimeList();

    public User(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
