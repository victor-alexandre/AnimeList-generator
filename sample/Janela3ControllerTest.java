package sample;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class of tests
 */
class Janela3ControllerTest {

    /**
     * Method to test if the sum of total items is correct.
     */
    @org.junit.jupiter.api.Test
    public void checaAnimeListTotalItems() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));
        assertEquals(3, teste.personalList.totalItems());
    }
    /**
     * Method to test if the sum of total episodes is correct.
     */
    @org.junit.jupiter.api.Test
    public void checaAnimeListTotalEpisodes() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));
        assertEquals(15, teste.personalList.totalEpisodes());
    }

    /**
     * Method to test if the function to delete all items from userlist is correct.
     */
    @org.junit.jupiter.api.Test
    public void checaListClear() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime", 5,5,5));
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));

        teste.personalList.list.clear();
        assertEquals(0,teste.personalList.totalItems());
    }

    /**
     * Method to test if the convertion of simpleDoubleproperty to double in  anime.score is working properly.
     */
    @org.junit.jupiter.api.Test
    public void checaScore() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime", 5,5,5.6));
        assertEquals(5.5,teste.personalList.list.get(0).getScore(), 0.1);
    }


}