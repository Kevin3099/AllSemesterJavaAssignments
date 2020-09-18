
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SongTest {

    Song songOne, songTwo, songThree;
    Artist artist;

    @Before
    public void setUp() throws Exception {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        artist = new Artist("Artist Name as 30 characters!!",
                "artist1@visual.com",
                "01234567");

        //name on boundary 30 chars, valid genre, valid song length, valid Artist
        //Genre must be: Rock, Pop, Blues, Rap, Dance, Classical
        songOne = new Song("012345678901234567890123456789",
                "roCk",163, artist);
    }

    @After
    public void tearDown() throws Exception {
        songOne = songTwo = songThree = null;
    }

    @Test
    public void validDataInConstructorAccepted(){
        //name on boundary 30 chars, valid genre, valid song length, valid Artist
        //Genre must be: Rock, Pop, Blues, Rap, Dance, Classical
        assertEquals("012345678901234567890123456789", songOne.getSongName());
        assertEquals("ROCK", songOne.getSongGenre());
        assertEquals(163, songOne.getSongLength());
        assertEquals(artist, songOne.getArtist());

        //valid genre, lower boundary song length, 10
        songTwo = new Song("01234567890123456789012345678",
                "danCE",10, artist);
        assertEquals("DANCE", songTwo.getSongGenre());
        assertEquals(10, songTwo.getSongLength());

        //valid genre, lower boundary song length, 11
        songTwo = new Song("01234567890123456789012345678",
                "blues",11, artist);
        assertEquals("BLUES", songTwo.getSongGenre());
        assertEquals(11, songTwo.getSongLength());

        //valid genre, upper boundary song length, 1199
        songTwo = new Song("01234567890123456789012345678",
                "pop",1199, artist);
        assertEquals("POP", songTwo.getSongGenre());
        assertEquals(1199, songTwo.getSongLength());

        //valid genre, upper boundary song length, 1200
        songTwo = new Song("01234567890123456789012345678",
                "classical",1200, artist);
        assertEquals("CLASSICAL", songTwo.getSongGenre());
        assertEquals(1200, songTwo.getSongLength());

        //valid genre
        songTwo = new Song("01234567890123456789012345678",
                "rap",1200, artist);
        assertEquals("RAP", songTwo.getSongGenre());
        assertEquals(1200, songTwo.getSongLength());
    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned(){
        //name (0 chars), invalid genre, invalid song length (<10), null artist
        songTwo = new Song("",
                "Jazz",
                9, null);
        assertEquals("", songTwo.getSongName());
        assertEquals("unknown", songTwo.getSongGenre().toLowerCase());
        assertEquals(0, songTwo.getSongLength());
        assertEquals("unknown", songTwo.getArtist().getArtistName());
        assertEquals("unknown", songTwo.getArtist().getArtistPhone());
        assertEquals("invalid format email", songTwo.getArtist().getArtistEmail());

        //name (31 chars), invalid genre, invalid song length (>1200), null artist
        songTwo = new Song("0123456789012345678901234567890",
                "Indie",
                1201, null);
        assertEquals("012345678901234567890123456789", songTwo.getSongName());
        assertEquals("unknown", songTwo.getSongGenre().toLowerCase());
        assertEquals(0, songTwo.getSongLength());
        assertEquals("unknown", songTwo.getArtist().getArtistName());
        assertEquals("unknown", songTwo.getArtist().getArtistPhone());
        assertEquals("invalid format email", songTwo.getArtist().getArtistEmail());
    }

    @Test
    public void setSongName() {
        //name on boundary 30 chars
        assertEquals("012345678901234567890123456789", songOne.getSongName());

        songOne.setSongName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", songOne.getSongName());

        songOne.setSongName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", songOne.getSongName());

        songOne.setSongName("0123456789012345678901234567890"); //31 chars
        assertEquals("012345678901234567890123456789", songOne.getSongName());
    }

    @Test
    public void setSongLength() {
        //valid song length set
        assertEquals(163, songOne.getSongLength());

        songOne.setSongLength(10);     //10 (lower boundary)
        assertEquals(10, songOne.getSongLength());
        songOne.setSongLength(11);     //11 (lower boundary + 1)
        assertEquals(11, songOne.getSongLength());
        songOne.setSongLength(9);     //9 (lower boundary - 1)
        assertEquals(11, songOne.getSongLength());

        songOne.setSongLength(1200);     //1200 (upper boundary)
        assertEquals(1200, songOne.getSongLength());
        songOne.setSongLength(1201);     //1201 (upper boundary + 1)
        assertEquals(1200, songOne.getSongLength());
        songOne.setSongLength(1199);     //1199 (upper boundary - 1)
        assertEquals(1199, songOne.getSongLength());
    }

    @Test
    public void setSongGenre() {
        assertEquals("ROCK", songOne.getSongGenre());

        //valid values - genre must be: Rock, Pop, Blues, Rap, Dance, Classical
        songOne.setSongGenre("pop");
        assertEquals("POP", songOne.getSongGenre());
        songOne.setSongGenre("Blues");
        assertEquals("BLUES", songOne.getSongGenre());
        songOne.setSongGenre("RAP");
        assertEquals("RAP", songOne.getSongGenre());
        songOne.setSongGenre("danCE");
        assertEquals("DANCE", songOne.getSongGenre());
        songOne.setSongGenre("CLASSICAL");
        assertEquals("CLASSICAL", songOne.getSongGenre());

        //invalid values
        songOne.setSongGenre("Jazz");
        assertEquals("CLASSICAL", songOne.getSongGenre());
    }

    @Test
    public void setArtist() {
        assertEquals("Artist Name as 30 characters!!", songOne.getArtist().getArtistName());

        //set Artist with a value artist object
        songOne.setArtist(new Artist("New Artist", "newbie@artist.com", "23211"));
        assertEquals("New Artist", songOne.getArtist().getArtistName());
        assertEquals("newbie@artist.com", songOne.getArtist().getArtistEmail());
        assertEquals("23211", songOne.getArtist().getArtistPhone());

        //attempt to set Artist with a null object
        songOne.setArtist(null);
        assertEquals("New Artist", songOne.getArtist().getArtistName());
        assertEquals("newbie@artist.com", songOne.getArtist().getArtistEmail());
        assertEquals("23211", songOne.getArtist().getArtistPhone());
    }

    @Test
    public void toStringUsesAllFields() {
        assertThat(songOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(songOne.toString().contains("ROCK"), is(true));
        assertThat(songOne.toString().contains("" + (int)(163 % 60)), is(true));
        assertThat(songOne.toString().contains("" + (int)(163 / 60)), is(true));
        assertThat(songOne.toString().contains("Artist Name as 30 characters!!"), is(true));
    }
}