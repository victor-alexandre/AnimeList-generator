package sample;

import static org.junit.jupiter.api.Assertions.*;

class Janela3ControllerTest {

    @org.junit.jupiter.api.Test
    public void checaAnimeListTotalItems() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));
        assertEquals(3, teste.personalList.totalItems());
    }

    @org.junit.jupiter.api.Test
    public void checaAnimeListTotalEpisodes() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));
        assertEquals(15, teste.personalList.totalEpisodes());
    }

    @org.junit.jupiter.api.Test
    public void checaDeleteAll() {
        Usuario teste = new Usuario("joao");
        teste.personalList.list.add(new Anime("anime", 5,5,5));
        teste.personalList.list.add(new Anime("anime1", 5,5,5));
        teste.personalList.list.add(new Anime("anime2", 5,5,5));
        teste.personalList.list.add(new Anime("anime3", 5,5,5));

        teste.personalList.list.clear();

        assertEquals(0,teste.personalList.totalItems());

    }
}