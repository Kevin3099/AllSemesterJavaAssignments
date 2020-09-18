package models;

import java.util.ArrayList;
import java.util.HashSet;

public class Specialist extends RegisteredDoctor {
    private HashSet<String> specialism;

    public Specialist(String name, String dob, char gender, String address, String contactNumber, boolean qualifiedInIreland, ArrayList<Qualification> qualification, HashSet<String> specialism){
        super(name,dob,gender,address,contactNumber,qualifiedInIreland,qualification);
        this.specialism = specialism;
    }
    public double calcRegistrationFee(){
        if(super.isQualifiedInIreland()){
            return 425.00;
        }
        else{
            return 641.00;
        }
    }
    public String viewContactDetails(){
        return super.viewContactDetails();
    }

    public HashSet<String> getSpecialism() {
        return specialism;
    }

    public void setSpecialism(HashSet<String> specialism) {
        this.specialism = specialism;
    }

    @Override
    public String toString() {
        return "SPECIALIST DOCTOR: " +
                " name = " + name +
                " dob = " + dob +
                " gender = " + gender +
                " address = " + address +
                " contactNumber = " + contactNumber + " \n" +
                 qualifications + " qualifiedInIreland = " + isQualifiedInIreland() +"\n Specialisms: " + specialism;
    }
}
