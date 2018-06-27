package sample;

public class Usuario {
    String name;
    AnimeList personalList = new AnimeList();

    public Usuario(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
