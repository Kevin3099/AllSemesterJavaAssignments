package models;

import java.util.ArrayList;

public abstract class RegisteredDoctor extends Doctor{
    private boolean qualifiedInIreland;

    public RegisteredDoctor(String name, String dob, char gender, String address, String contactNumber,boolean qualifiedInIreland,ArrayList<Qualification> qualification){
        super(name,dob,gender,address,contactNumber,qualification);
        this.qualifiedInIreland = qualifiedInIreland;
    }
    public boolean isQualifiedInIreland(){
        return qualifiedInIreland;
    }

    public void setQualifiedInIreland(){
        this.qualifiedInIreland = qualifiedInIreland;
    }

    @Override
    public String toString() {
        return "RegisteredDoctor " +
                "qualifiedInIreland = " + qualifiedInIreland;
    }
}
