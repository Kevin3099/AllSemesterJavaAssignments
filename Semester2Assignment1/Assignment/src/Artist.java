import java.util.ArrayList;

public class Artist {
    private String artistName;      //private String Variable (field)
    private String artistEmail;     //private String Variable (field)
    private String artistPhone;     //private String Variable (field)

    public Artist(String artistName, String artistEmail, String artistPhone) {   //Constructor
        if (artistName.length() > 30) {     //if name is less than or equal to 30 characters return, if not error
            this.artistName = artistName.substring(0,30);
        } else{
            this.artistName = artistName;
        }
        if (artistEmail.contains("@") && artistEmail.contains(".")) { //Checks that artist email has a @ and a .
            this.artistEmail = artistEmail;
        } else {
            this.artistEmail = "invalid format email";
        }
        if (artistPhone.matches("[0-9]+")){     //Checks that the phone number only contains numbers 0-9
            this.artistPhone = artistPhone;
        } else{
            this.artistPhone = "unknown";
        }
    }


    //Getters and Setters
    public String getArtistPhone() {
            return artistPhone;
    }

    public void setArtistPhone(String artistPhone) {
        if (artistPhone.matches("[0-9]+")){
            this.artistPhone = artistPhone;
        }
    }

    public String getArtistEmail() {
            return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        if (artistEmail.contains("@") && artistEmail.contains(".")) {
            this.artistEmail = artistEmail;
        }
    }

    public String getArtistName() {
        return artistName;
    }


    public void setArtistName(String artistName) {

        if (artistName.length() > 30) {     //if name is less than or equal to 30 characters return, if not error
            this.artistName = artistName.substring(0,30);
        } else {
            this.artistName = artistName;
        }
    }


    //toString method
    public String toString() {
        return artistName + " (email: "
                + artistEmail + ", phone: "
                + artistPhone + ")";                //To string to print out like 0: Name(email: Email, phone: Phone)
    }
}
