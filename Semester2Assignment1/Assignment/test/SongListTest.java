import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SongListTest {

    SongList songListPopulated, songListEmpty;
    Song songOne, songTwo, songThree, songFour, songFive;
    Artist artistOne, artistTwo, artistThree, artistFour, artistFive;

    @Before
    public void setup() {
        //Artist Test Data
        artistOne = new Artist("Mumford and Sons", "mumford@sons.com", "232523");
        artistTwo = new Artist("The Script", "thescript@gmail.com", "4323453");
        artistThree = new Artist("Ed Sheeran", "ed@sheeran.com", "2325654");
        artistFour = new Artist("Ludwig van Beethoven", "lud@beet.com", "874422");
        artistFive = new Artist("Madonna", "ma@donna.com", "874322");

        //Song Test Data
        songOne = new Song("Rain", "rock", 209, artistTwo);
        songTwo = new Song("No Man is an Island", "rock", 190, artistTwo);
        songThree = new Song("Symphony No. 9", "classical", 343, artistFour);
        songFour = new Song("Shape of You", "pop", 274, artistThree);
        songFive = new Song("Galway Girl", "pop", 200, artistThree);

        //Setting up a Populated List
        songListPopulated = new SongList();
        songListPopulated.addSong(songOne);
        songListPopulated.addSong(songTwo);
        songListPopulated.addSong(songThree);
        songListPopulated.addSong(songFour);

        //Setting up an Empty List
        songListEmpty = new SongList();
    }

    @After
    public void teardown() {
        songListPopulated = songListEmpty = null;
        songOne = songTwo = songThree = songFour = songFive = null;
        artistOne = artistTwo = artistThree = artistFour = null;
    }

    @Test
    public void addSong() {
        //adding to populated list
        assertEquals(4, songListPopulated.numberOfSongs());
        songListPopulated.addSong(songFive);
        assertEquals(songListPopulated.getSong(4), songFive);
        assertEquals(5, songListPopulated.numberOfSongs());

        //adding to empty list
        assertEquals(0, songListEmpty.numberOfSongs());
        songListEmpty.addSong(songOne);
        assertEquals(songListEmpty.getSong(0), songOne);
        assertEquals(1, songListEmpty.numberOfSongs());
    }

    @Test
    public void getSongVerifyIndexes() {
        //retrieving an existing Song from populated list
        assertEquals(4, songListPopulated.numberOfSongs());
        assertThat(songListPopulated.getSong(2), is(instanceOf(Song.class)));
        assertThat(songListPopulated.getSong(2).getSongName(), is("Symphony No. 9"));

        //retrieving a non-existant Artist from populated list
        assertNull(songListPopulated.getSong(4));
        assertNull(songListPopulated.getSong(-1));

        //retrieving from empty list
        assertEquals(0, songListEmpty.numberOfSongs());
        assertNull(songListEmpty.getSong(0));
    }

    @Test
    public void removeSongVerifyIndexes() {
        //removing an existing Song from populated list
        assertEquals(4, songListPopulated.numberOfSongs());
        songListPopulated.removeSong(2);
        assertEquals(3, songListPopulated.numberOfSongs());

        //removing non-existant Song from populated list
        songListPopulated.removeSong(3);
        assertEquals(3, songListPopulated.numberOfSongs());
        songListPopulated.removeSong(-1);
        assertEquals(3, songListPopulated.numberOfSongs());

        //attempting to remove from empty list
        songListEmpty.removeSong(0);
        assertEquals(0, songListEmpty.numberOfSongs());
    }

    @Test
    public void listOfArtists() {
        //listing an empty list
        assertThat(songListEmpty.listOfSongs().contains("no songs in the list"), is(true));

        //listing a populated list
        assertTrue(songListPopulated.listOfSongs().contains("Rain"));
        assertTrue(songListPopulated.listOfSongs().contains("No Man is an Island"));
        assertTrue(songListPopulated.listOfSongs().contains("Symphony No. 9"));
        assertTrue(songListPopulated.listOfSongs().contains("Shape of You"));
        assertTrue(songListPopulated.listOfSongs().contains("0:"));
        assertTrue(songListPopulated.listOfSongs().contains("1:"));
        assertTrue(songListPopulated.listOfSongs().contains("2:"));
        assertTrue(songListPopulated.listOfSongs().contains("3:"));
    }

    @Test
    public void listSongsBySpecificGenre() {
        //listing an empty list
        assertTrue(songListEmpty.listSongsBySpecificGenre("anything").contains("no songs stored"));

        //listing a populated list with an existing genre
        assertTrue(songListPopulated.listSongsBySpecificGenre("rock").contains("Rain"));
        assertTrue(songListPopulated.listSongsBySpecificGenre("Rock").contains("No Man is an Island"));

        //listing a populated list that has no songs store for selected genre
        assertTrue(songListPopulated.listSongsBySpecificGenre("Jazz").contains("no songs with the genre"));
        assertTrue(songListPopulated.listSongsBySpecificGenre("Blues").contains("no songs with the genre"));
    }


    @Test
    public void listSongsBySpecificArtist() {
        //listing an empty list
        assertTrue(songListEmpty.listSongsBySpecificArtist("anything").contains("no songs stored"));

        //listing a populated list with an existing Artist
        songListPopulated.addSong(songFive);
        assertTrue(songListPopulated.listSongsBySpecificArtist("Sheeran").contains("Shape of You"));
        assertTrue(songListPopulated.listSongsBySpecificArtist("Ed Sheeran").contains("Galway Girl"));

        //listing a populated list that has no songs store for selected genre
        assertTrue(songListPopulated.listSongsBySpecificArtist("Madonna").contains("no songs for the artist"));
    }

    @Test
    public void longestSong() {
        //over an empty list
        assertNull(songListEmpty.longestSong());

        //over a populated list
        assertThat(songListPopulated.longestSong().getSongLength(), is(343));
        assertThat(songListPopulated.longestSong().getSongName(), is("Symphony No. 9"));
    }

    @Test
    public void averageSongLength() {
        //over an empty list
        assertEquals(0.0, songListEmpty.averageSongLength(), 0.1);
        //over a populated list
        assertEquals(254, songListPopulated.averageSongLength(), 0.1);
    }

    @Test
    public void lengthOfAllSongs() {
        //over an empty list
        assertEquals(0.0, songListEmpty.lengthOfAllSongs(), 0.1);
        //over a populated list
        assertEquals(1016, songListPopulated.lengthOfAllSongs(), 0.1);
    }

}
