import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.util.Objects.isNull;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;


public class ArtistListTest {

    ArtistList artistListPopulated, artistListEmpty;
    Artist artistOne, artistTwo, artistThree, artistFour;

    @Before
    public void setup(){
        //Artist Test Data
        artistOne = new Artist("Mumford and Sons", "mumford@sons.com", "232523");
        artistTwo = new Artist("The Script", "thescript@gmail.com", "4323453");
        artistThree = new Artist("Ed Sheeran", "ed@sheeran.com", "2325654");
        artistFour = new Artist("Ludwig van Beethoven", "lud@beet.com", "874422");

        //Setting up a Populated List
        artistListPopulated = new ArtistList();
        artistListPopulated.addArtist(artistOne);
        artistListPopulated.addArtist(artistTwo);
        artistListPopulated.addArtist(artistThree);

        //Setting up an Empty List
        artistListEmpty = new ArtistList();
    }

    @After
    public void teardown(){
        artistListPopulated = artistListEmpty = null;
        artistOne = artistTwo = artistThree = artistFour = null;
    }

    @Test
    public void addArtist() {
        //adding to populated list
        assertEquals(3, artistListPopulated.numberOfArtists());
        artistListPopulated.addArtist(artistFour);
        assertEquals(artistListPopulated.getArtist(3), artistFour);
        assertEquals(4, artistListPopulated.numberOfArtists());

        //adding to empty list
        assertEquals(0, artistListEmpty.numberOfArtists());
        artistListEmpty.addArtist(artistTwo);
        assertEquals(artistListEmpty.getArtist(0), artistTwo);
        assertEquals(1, artistListEmpty.numberOfArtists());
    }

    @Test
    public void getArtistVerifyIndexes() {
        //retrieving an existing Artist from populated list
        assertEquals(3, artistListPopulated.numberOfArtists());
        assertThat(artistListPopulated.getArtist(2), is(instanceOf(Artist.class)));
        assertThat(artistListPopulated.getArtist(2).getArtistName(), is("Ed Sheeran"));

        //retrieving a non-existant Artist from populated list
        assertNull(artistListPopulated.getArtist(3));
        assertNull(artistListPopulated.getArtist(-1));

        //retrieving from empty list
        assertEquals(0, artistListEmpty.numberOfArtists());
        assertNull(artistListEmpty.getArtist(0));
    }

    @Test
    public void removeArtistVerifyIndexes() {
        //removing an existing Artist from populated list
        assertEquals(3, artistListPopulated.numberOfArtists());
        artistListPopulated.removeArtist(2);
        assertEquals(2, artistListPopulated.numberOfArtists());

        //removing non-existant Artist from populated list
        artistListPopulated.removeArtist(3);
        assertEquals(2, artistListPopulated.numberOfArtists());
        artistListPopulated.removeArtist(-1);
        assertEquals(2, artistListPopulated.numberOfArtists());

        //attempting to remove from empty list
        artistListEmpty.removeArtist(0);
        assertEquals(0, artistListEmpty.numberOfArtists());
    }

    @Test
    public void listOfArtists() {
        //listing an empty list
        assertThat(artistListEmpty.listOfArtists().contains("no artists in the list"), is(true));

        //listing a populated list
        assertEquals(artistListPopulated.listOfArtists().contains("Mumford and Sons"), true);
        assertEquals(artistListPopulated.listOfArtists().contains("The Script"), true);
        assertEquals(artistListPopulated.listOfArtists().contains("Ed Sheeran"), true);
        assertEquals(artistListPopulated.listOfArtists().contains("0:"), true);
        assertEquals(artistListPopulated.listOfArtists().contains("1:"), true);
        assertEquals(artistListPopulated.listOfArtists().contains("2:"), true);
    }
}