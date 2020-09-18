package models;

import java.util.ArrayList;

public abstract class Doctor implements iDoctor {
    protected String name;
    protected String dob;
    protected char gender;
    protected String address;
    protected String contactNumber;
    protected ArrayList<Qualification> qualifications;

    public Doctor(String name, String dob, char gender, String address, String contactNumber, ArrayList<Qualification> qualifications){
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.contactNumber = contactNumber;
        this.qualifications = qualifications;
    }
    public String viewContactDetails(){
        return "Name: " + name + " Address: " +address + " Number: " +contactNumber;
    }
    private String getFullGender()
    {
        if((gender == 'M') || (gender == 'm')){
            return "Male";
        }
        else if ((gender == 'F') || (gender == 'f')){
            return "Female";
        }
        else{
            return "unknown";
        }
    }
    public double calcRegistrationFee(){
        return 0;
    }

    public void addQualification(Qualification qualification){
        qualifications.add(qualification);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ArrayList<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public String toString() {
        return "Doctor:" +
                "name = " + name + ", dob = " +
                dob +
                ", gender = " + getFullGender() +
                ", address = " + address +
                ", contactNumber = " + contactNumber +
                " \n" + qualifications;
    }
}
