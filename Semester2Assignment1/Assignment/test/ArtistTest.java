import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArtistTest {

    Artist artistOne, artistTwo, artistThree;

    @Before
    public void setUp() throws Exception {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        artistOne = new Artist("012345678901234567890123456789",
                "artist1@visual.com",
                "01234567");
    }

    @After
    public void tearDown() throws Exception {
        artistOne = artistTwo = artistThree = null;
    }

    @Test
    public void validDataInConstructorAccepted(){
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", artistOne.getArtistName());
        assertEquals("artist1@visual.com", artistOne.getArtistEmail());
        assertEquals("01234567", artistOne.getArtistPhone());

        //name under boundary (29 chars), valid email, valid phone (trailing zero)
        artistTwo = new Artist("01234567890123456789012345678",
                "artist2@visual.ie",
                "12345670");
        assertEquals("01234567890123456789012345678", artistTwo.getArtistName());
        assertEquals("artist2@visual.ie", artistTwo.getArtistEmail());
        assertEquals("12345670", artistTwo.getArtistPhone());
    }

    @Test
    public void inValidDataInConstructorDefaultsAssigned(){
        //name (0 chars), invalid email (missing .), invalid phone (contains chars)
        artistTwo = new Artist("",
                "artist2@visualie",
                "PH:12345670");
        assertEquals("", artistTwo.getArtistName());
        assertEquals("invalid format email", artistTwo.getArtistEmail().toLowerCase());
        assertEquals("unknown", artistTwo.getArtistPhone().toLowerCase());

        //name over boundary 31 chars, invalid email (missing @), invalid phone (contains -)
        artistThree = new Artist("0123456789012345678901234567890",
                "artist1visual.com",
                "01-234567");
        assertEquals("012345678901234567890123456789", artistThree.getArtistName());
        assertEquals("invalid format email", artistThree.getArtistEmail().toLowerCase());
        assertEquals("unknown", artistThree.getArtistPhone().toLowerCase());
    }

    @Test
    public void setArtistName() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("012345678901234567890123456789", artistOne.getArtistName());

        artistOne.setArtistName("01234567890123456789012345678");   //29 chars
        assertEquals("01234567890123456789012345678", artistOne.getArtistName());

        artistOne.setArtistName("012345678901234567890123456789");  //30 chars
        assertEquals("012345678901234567890123456789", artistOne.getArtistName());

        artistOne.setArtistName("0123456789012345678901234567890"); //31 chars
        assertEquals("012345678901234567890123456789", artistOne.getArtistName());
    }

    @Test
    public void setArtistEmail() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("artist1@visual.com", artistOne.getArtistEmail());

        //no @
        artistOne.setArtistEmail("artist1visual.com");
        assertEquals("artist1@visual.com", artistOne.getArtistEmail());

        //no .
        artistOne.setArtistEmail("artist1@visualcom");
        assertEquals("artist1@visual.com", artistOne.getArtistEmail());

        //valid
        artistOne.setArtistEmail("artistOne@visual.com");
        assertEquals("artistOne@visual.com", artistOne.getArtistEmail());
    }

    @Test
    public void setArtistPhone() {
        //name on boundary 30 chars, valid email, valid phone (leading zero)
        assertEquals("01234567", artistOne.getArtistPhone());

        //contains -
        artistOne.setArtistPhone("01-234567");
        assertEquals("01234567", artistOne.getArtistPhone());

        //contains chars
        artistOne.setArtistPhone("PH: 01234567");
        assertEquals("01234567", artistOne.getArtistPhone());

        //valid
        artistOne.setArtistPhone("019122347");
        assertEquals("019122347", artistOne.getArtistPhone());
    }

    @Test
    public void toStringUsesAllFields() {
        assertThat(artistOne.toString().contains("012345678901234567890123456789"), is(true));
        assertThat(artistOne.toString().contains("artist1@visual.com"), is(true));
        assertThat(artistOne.toString().contains("01234567"), is(true));
    }
}