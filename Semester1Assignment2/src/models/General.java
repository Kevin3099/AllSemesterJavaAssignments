package models;

import java.util.ArrayList;

public class General extends RegisteredDoctor{
    public General(String name, String dob, char gender, String address, String contactNumber, ArrayList<Qualification> qualification, boolean qualifiedInIreland){
        super(name,dob,gender,address,contactNumber,qualifiedInIreland,qualification);

    }
    public double calcRegistrationFee(){
        if(super.isQualifiedInIreland()){
            return 194.00;
        }
        else{
            return 410.00;
        }
    }

    @Override
    public String toString() {
        return "GENERAL DOCTOR: " +
                " name = " + name +
                " dob = " + dob +
                " gender = " + gender +
                " address = " + address +
                " contactNumber = " + contactNumber + " \n" +
                qualifications + " qualifiedInIreland = " + isQualifiedInIreland();
    }
}
