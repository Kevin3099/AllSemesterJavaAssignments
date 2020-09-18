import java.util.Scanner;

public class Driver {

    private Scanner input = new Scanner(System.in);
    private ArtistList artistList = new ArtistList();
    private SongList songList = new SongList();

    public Driver() {
        runMenu();
    }

    public static void main(String[] args) {
        new Driver();
    }

    private int mainMenu() {
        System.out.println("Songify Menu");
        System.out.println("---------");
        System.out.println("  1) Add an Artist");
        System.out.println("  2) List all Artists");
        System.out.println("  3) Remove an Artist (by index)");
        System.out.println("  --------------------");
        System.out.println("  4) Add a Song");
        System.out.println("  5) List all Songs");
        System.out.println("  6) List all Songs in a Genre");
        System.out.println("  7) List all Songs by Artist");
        System.out.println("  8) List Song Statistics");
        System.out.println("  9) Remove a Song (by index)");
        System.out.println("  --------------------");
        System.out.println("  10)  Save products to song.xml and artist.xml");
        System.out.println("  11) Load products from song.xml and artist.xml");
        System.out.println("  --------------------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addArtist();
                    break;
                case 2:
                    listOfArtists();
                    break;
                case 3:
                    deleteArtist();
                    break;
                case 4:
                     addSong();
                    break;
                case 5:
                    listAllSongs();
                    break;
                case 6:
                    listSongByGenre();
                    break;
                case 7:
                    listSongByArtist();
                    break;
                case 8:
                    listSongStats();
                    break;
                case 9:
                    deleteSong();
                    break;
                case 10:
                    save();
                    break;
                case 11:
                    load();
                    break;
                default:
                    System.out.println("Please choose another Option" + option);
                    break;
            }
            System.out.println("\n Press a key to continue..");//Pause to let user read what is printed
            input.nextLine();

            option = mainMenu();
        }
        System.out.println("Exiting now");
        System.exit(0); //If option 0 is chosen it leaves
    }

    private void addArtist() {
        System.out.print("Enter the Artist name: ");
        String artistName = input.nextLine();
        System.out.print("Enter the Artist Email: ");
        String artistEmail = input.nextLine();
        System.out.print("Enter the Artist Phone Number: ");
        String artistPhoneNumber = input.nextLine();
        System.out.println("The artist details are: Name: " + artistName + " Email: " + artistEmail + " Phone Number: " + artistPhoneNumber);
        artistList.addArtist(new Artist(artistName, artistEmail, artistPhoneNumber));
    }


    private void listOfArtists() {
        System.out.println("List of Artists are: ");
        System.out.println(artistList.listOfArtists());
    }

    private void deleteArtist() {
        System.out.println(artistList.listOfArtists());

        int index = ScannerInput.readNextInt("Enter the index of the Artist to delete it ==> ");

        if (index < artistList.numberOfArtists()) {
            artistList.removeArtist(index);
            System.out.println("Artist has been deleted.");
        } else {
            System.out.println("There is no artist for this index number");
        }
    }


    private void listSongStats() {
        System.out.println("Average Song Length: ");
        System.out.println(songList.averageSongLength());

        System.out.println("Length Of All Songs: ");
        System.out.println(songList.lengthOfAllSongs());

        System.out.println("Longest Song: ");
        System.out.println(songList.longestSong());

    }

    private void listSongByArtist() {
        System.out.println(artistList.listOfArtists());

        System.out.println("Enter an Artist to Search by: ");
        String artistNameToSearchBy = input.nextLine();
       System.out.println(songList.listSongsBySpecificArtist(artistNameToSearchBy));
    }

    private void listSongByGenre() {
        System.out.println(songList.listOfSongs());

        System.out.println("Enter an Genre to Search by: ");
        String genreNameToSearchBy = input.nextLine();
        System.out.println(songList.listSongsBySpecificGenre(genreNameToSearchBy).toUpperCase());
    }

    private void addSong() {
        System.out.print("Please Enter the following song details... ");
        System.out.print("\n Name (max 30 chars):  ");
        String songName = input.nextLine();
        System.out.print("\n Genre (ROCK, POP, BLUES, RAP, DANCE, CLASSICAL):  ");
        String songGenre = input.nextLine();

        int songMinutes = ScannerInput.readNextInt("Length of Song (minutes): ");
        int songSeconds = ScannerInput.readNextInt("Length of Song (seconds): ");
        int songLength = songSeconds + songMinutes*60;

       if(artistList.numberOfArtists() > 0) {
           System.out.println(artistList.listOfArtists());
           int index = ScannerInput.readNextInt("Enter the artist (by index): ");
           Artist artist = artistList.getArtist(index);         //get artist at that index and add to song
          // System.out.println("The Song information is: " + songName + songGenre +songLength + artist);
           songList.addSong(new Song(songName, songGenre, songLength, artist));
       }
       else {
           System.out.print("No Artist available please create one \n");
           addArtist();
           Artist artist = artistList.getArtist(0); //get artist at index 0
           songList.addSong(new Song(songName, songGenre, songLength, artist));
       }
    }

    private void listAllSongs() {
        System.out.println("List of Songs are: ");
        System.out.println(songList.listOfSongs());
    }
    private void deleteSong() {
        System.out.println(songList.listOfSongs());

        int index = ScannerInput.readNextInt("Enter the index of the Song to delete it ==> ");

        if (index < songList.numberOfSongs()) {
            songList.removeSong(index);
            System.out.println("Song has been deleted.");
        } else {
            System.out.println("There is no song for this index number");
        }
    }
    private void save(){
        try{
            artistList.save();
            songList.save();
        }
        catch(Exception e){
            System.err.println("Error writing to file: " + e);
        }
    }
    private void load(){
        try{
            artistList.load();
            songList.load();
        }
        catch(Exception e){
            System.err.println("Error reading from file: " + e);
        }
    }
}
