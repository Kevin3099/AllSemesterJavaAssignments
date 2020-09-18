import java.util.Arrays;

public class Song {

    private String songName;
    private String songGenre;
    private int songLength;
    private Artist artist;      //fields

    public Song(String songName, String songGenre, int songLength, Artist artist) {
        if (songName.length() > 30) {     //if name is less than or equal to 30 characters return, if not error
            this.songName = songName.substring(0,30);
        } else {
            this.songName = songName;
        }
        if(Arrays.asList("ROCK", "POP", "BLUES", "RAP", "DANCE", "CLASSICAL").contains(songGenre.toUpperCase())){ //put array to a list, then see if genre value equals anything in the list
            this.songGenre = songGenre;
        }
        else {
            this.songGenre = "unknown";
        }
        if(songLength <= 1200 && songLength >= 10){     //if song length is between 1200 and 10 store that value
        this.songLength = songLength;
        }
        else{
            this.songLength = 0;        //if it isn't put it to 0
        }
        if(artist == null) {        //if artist is null create new artist
            this.artist = new Artist("unknown", "invalid format email", "unknown");
        }
        else{
            this.artist = artist;
        }
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        if (songName.length() > 30) {     //if name is less than or equal to 30 characters return, if not error
            this.songName = songName.substring(0,30);
        }
        else{
            this.songName = songName;
        }
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        if(Arrays.asList("ROCK", "POP", "BLUES", "RAP", "DANCE", "CLASSICAL").contains(songGenre.toUpperCase())){ //put array to a list, then see if genre value equals anything in the list
            this.songGenre = songGenre;
        }
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        if(songLength <= 1200 && songLength >= 10){     //if song length is between 1200 and 10 store that value
            this.songLength = songLength;
        }
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        if(artist != null) {        //if artist is null create new artist
            this.artist = artist;
        }
    }

    public String toString() {
        return songName + ", Artist: " + artist + "(" + songLength + "), Genre: " + songGenre; //MAKE SONG-LENGTH CONVERT TO MINUTES
    }
}
