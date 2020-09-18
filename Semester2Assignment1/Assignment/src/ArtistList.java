import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;     //importing all the packages needed for this class


public class ArtistList {

        private ArrayList<Artist> artists = new ArrayList<Artist>();   //private field of ArrayList of Artists


    public void addArtist(Artist artist) {
            artists.add(artist);       //adds an Artist to the Array list of artists
    }

    public Artist getArtist(int index){
        if(index > 0 && index < artists.size()){
            return artists.get(index);         //if the artists is not 0 and is also less than artists.size get the artist at the index
    } else {
            return null;
        }
    }
    public int numberOfArtists(){
            return artists.size();      //Return number of object stored in artists array list

        }

    public boolean removeArtist(int index){
        if(index > 0 && index < artists.size()) {
        artists.remove(index);
        return true;                //remove artist at index 0 if validation checks out
        }
        return false;
    }

    public String listOfArtists(){
        if (artists.size() == 0) {
            return "There are no artists in the list.";         //if no artists return this
        }
        else{
            String listOfArtists = "";      //creating empty string of list of artists
            for (int i = 0;i< artists.size(); i++){
                listOfArtists += ""+ i + ": " + artists.get(i) + "\n";       //loop to go through artist print out at point of index
            }
            return listOfArtists;
        }
    }



    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("artists.xml"));
        artists = (ArrayList<Artist>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("artists.xml"));
        out.writeObject(artists);
        out.close();
    }
}
