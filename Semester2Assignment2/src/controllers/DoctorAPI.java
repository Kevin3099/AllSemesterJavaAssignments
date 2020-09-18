package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Doctor;
import models.General;
import models.Intern;
import models.Specialist;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DoctorAPI {
    private ArrayList<Doctor> doctors = new ArrayList<Doctor>();


    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Doctor getDoctor(int index) {
        if (index >= 0 && index < doctors.size()) {
            return doctors.get(index);
        } else {
            return null;
        }
    }

    public ArrayList<Doctor> searchDoctorsByName(String doctorsByName){
        ArrayList<Doctor> doctorName = new ArrayList<Doctor>();

        for (Doctor doctor: this.doctors)
        {
            if(doctor.getName().equals(doctorsByName))
            {
                doctorName.add(doctor);
            }
        }
        return doctorName;
    }

    public boolean removeDoctor(int index) {
        if (index > 0 && index < doctors.size()) {
            doctors.remove(index);
            return true;
        }
        return false;
    }
    public int numberOfDoctors() {
        return doctors.size();
    }

    public String listOfDoctors() {
        if (doctors.size() == 0) {
            return "No doctors stored.";
        } else {
            String listDoctors = "";
            for (int i = 0; i < doctors.size(); i++) {
                listDoctors += "" + i + ": " + doctors.get(i) + "\n";
            }
            return listDoctors;
        }
    }

    public String listOfDoctors(String doctor) {
if(doctor.equalsIgnoreCase("intern")){
    for(int i =0; i < doctors.size(); i++){
        if(getDoctor(i) instanceof Intern){
            return doctor;
        }
    }
}
else if(doctor.equalsIgnoreCase("general")){
    for(int i =0; i < doctors.size(); i++){
        if(getDoctor(i) instanceof General){
            return doctor;
        }
    }
}
else if(doctor.equalsIgnoreCase("specialist")){
    for(int i =0; i < doctors.size(); i++){
        if(getDoctor(i) instanceof Specialist){
            return doctor;
        }
    }
}
    return null;
    }



    public int calculateAnnualFees(){
        int fee = 0;
        for(int i = 0; i< doctors.size(); i++){
           fee +=  doctors.get(i).calcRegistrationFee();
        }
        return fee;
    }

    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("doctors.xml"));
        doctors = (ArrayList<Doctor>) is.readObject();
        is.close();
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("doctors.xml"));
        out.writeObject(doctors);
        out.close();
    }
}
