import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;  //importing all the packages needed for this class

public class SongList {
    private ArrayList<Song> songs = new ArrayList<Song>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public Song getSong(int index) {
        if (index > 0 && index < songs.size()) {
            return songs.get(index);
        } else {
            return null;
        }
    }

    public boolean removeSong(int index) {
        if(index > 0 && index < songs.size()) {
            songs.remove(index);
            return true;
        }
        return false;
    }

    public int numberOfSongs() {
        return songs.size();
    }

    public String listOfSongs() {
        if (songs.size() == 0) {
            return "no songs in the list";         //if no artists do it here
        } else {
            String listOfSongs = "";
            for (int i = 0; i < songs.size(); i++) {
                listOfSongs = + +i + ": " + songs.get(i) + "/n";
            }
            return listOfSongs;
        }
    }

    public String listSongsBySpecificGenre(String songsByGenre) {
        String songGenre = "";
        if (songs.size() == 0) {
            return "There are no songs stored in the list.";
        }
        else {
            if(songGenre.equals("")){
                return "There are no songs with the genre: " + songGenre;
            }
            else {
                for (int i = 0; i < songs.size(); i++) {
                    if (songs.get(i).getSongGenre().equalsIgnoreCase(songsByGenre)) { //stack overflow
                        songGenre = songGenre + getSong(i).toString();
                    }
                }
                return songGenre;
            }
        }
    }


    public String listSongsBySpecificArtist(String songsByArtist) {
        String artistOfName = "";
        if (songs.size() == 0) {
            return "No Artists to list songs by";
        }
        else {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getArtist().getArtistName().toLowerCase().contains(songsByArtist.toLowerCase())) {
                    artistOfName = artistOfName + getSong(i).toString();
                }
                if(artistOfName.equals("")){
                    return "There are no songs for the artist: " + artistOfName;
                }
            }
            return artistOfName;
        }
    }


    public Song longestSong() {
        if (songs.size() > 0){
            Song songWithLongestLength = songs.get(0);      //get song at index 0
            for (Song song : songs){            //go through songs and store current song.
                if (song.getSongLength() > songWithLongestLength.getSongLength() )        //go through and keep longest song found, if song not longer keep current
                    songWithLongestLength = song;
            }
            return songWithLongestLength;            //return longest song found
        }
        else
            return null;        //if none found return null
    }

    public double averageSongLength() {
        int totalSongLength = 0;
        if (songs.size() != 0) {
            for (int i = 0; i < songs.size(); i++) {
                totalSongLength = totalSongLength + songs.get(i).getSongLength();
            }
            return toTwoDecimalPlaces(totalSongLength / songs.size());
        }
        else{
        return 0;
        }
    }


    public int lengthOfAllSongs() {
        int lengthOfSongs = 0;
        if(songs.size() != 0) {
            for (int i = 0; i < songs.size(); i++) {
                lengthOfSongs =+ songs.get(i).getSongLength();
            }
            return lengthOfSongs;
        }
        else{
            return 0;
        }
    }


    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("songs.xml"));
        songs = (ArrayList<Song>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("songs.xml"));
        out.writeObject(songs);
        out.close();
    }

    private double toTwoDecimalPlaces(double number) {
        return (int) (number * 100) / 100.0;
    }
}
