package models;

import java.util.ArrayList;

public class Intern extends Doctor{
    public Intern(String name, String dob, char gender, String address, String contactNumber, ArrayList<Qualification> qualification){
        super(name,dob,gender,address,contactNumber,qualification);
    }

    public double calcRegistrationFee(){
        return 310.00;
    }

    @Override
    public String toString() {
        return "INTERN: " +
                " name = " + name +
                " dob = " + dob +
                " gender = " + gender +
                " address = " + address +
                " contactNumber = " + contactNumber + " \n" +
                qualifications;
    }
}
