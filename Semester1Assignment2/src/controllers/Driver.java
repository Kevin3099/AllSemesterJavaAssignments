package controllers;
import models.*;
import utility.ScannerInput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Driver {

    private Scanner input = new Scanner(System.in);
    private DoctorAPI doctorAPI = new DoctorAPI();

    public Driver() {
        runMenu();
    }

    public static void main(String[] args) {
        new Driver();
    }

    private int mainMenu() {
        System.out.println("Medical Council Menu");
        System.out.println("-----------------");
        System.out.println("  1) Add a Doctor");
        System.out.println("  2) List all Doctors");
        System.out.println("  3) List all Doctors by name");
        System.out.println("  4) Update doctor details");
        System.out.println("  5) Update doctor qualifications");
        System.out.println("  6) Update doctor specialisms");
        System.out.println("  7) Delete Doctor");
        System.out.println("  --------------------");
        System.out.println("  8) Annual Fees Report");
        System.out.println("  9) Report Doctors (by Category)");
        System.out.println("  --------------------");
        System.out.println("  10)  Save ");
        System.out.println("  11)  Load ");
        System.out.println("  --------------------");

        System.out.println("  0) Exit");
        System.out.println("\n ==>>");
        return input.nextInt();
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    listDoctors();
                    break;
                case 3:
                    listDoctorsByName();
                    break;
                case 4:
                    updateDoctorDetails();
                    break;
                case 5:
                    updateDoctorQualifications();
                    break;
                case 6:
                    updateDoctorSpecialisms();
                    break;
                case 7:
                    deleteDoctor();
                    break;
                case 8:
                    annualFeesReport();
                    break;
                case 9:
                    reportByCategory();
                    break;
                case 10:
                    save();
                    break;
                case 11:
                    load();
                    break;
                default:
                    System.out.println("Please choose another Option " + option);
                    break;
            }
            System.out.println("\n");
            input.nextLine();
            option = mainMenu();
        }
        System.out.println("Exiting now");
        System.exit(0);
    }

    private void addDoctor() {
        String doctorName = ScannerInput.readNextString("Enter the Doctor name: ");
        String doctorDob = ScannerInput.readNextString("Enter the Doctor Date of Birth: ");
        System.out.print("Enter the Doctor's Gender (M/F): ");
        char doctorGender = input.next().charAt(0);
        String doctorAddress = ScannerInput.readNextString("Enter the Doctor's Address: ");
        String doctorContactNumber = ScannerInput.readNextString("Enter the Doctor's Contact Number: ");

        System.out.print("Now Enter the doctor's qualification details\n");
        String degreeType =  ScannerInput.readNextString("Please enter the degree type: ");
        String degreeName =  ScannerInput.readNextString("Please enter the degree name: ");
        String college =  ScannerInput.readNextString("Please enter the college he obtained it: ");
        String conferringYear =  ScannerInput.readNextString("Please enter the conferring year: ");
        ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
        qualifications.add(new Qualification(degreeType,degreeName,college,conferringYear));

        String doctorType = ScannerInput.readNextString("What type of doctor is it?(Registered Doctor/Intern): ");
        if(doctorType.equalsIgnoreCase("Registered Doctor")){
            boolean qualInIreland = false;
            char docType = ScannerInput.readNextChar("Is the doctor Qualified in Ireland?(y/n): ");
            if(docType == 'y'){
                qualInIreland = true;
            }
            String registeredDoctorType = ScannerInput.readNextString("Please select the doctor type: (General/Specialist): ");
            if(registeredDoctorType.equalsIgnoreCase("General")){
                doctorAPI.addDoctor(new General(doctorName, doctorDob, doctorGender, doctorAddress, doctorContactNumber,qualifications,qualInIreland));
            }
            else if(registeredDoctorType.equalsIgnoreCase(("Specialist"))){
                HashSet<String> Specialist = new HashSet<String>();
                String specialism = "";
                System.out.println("Type the specialism or type 0 to quit");
                while(!specialism.matches("0")){
                    specialism = ScannerInput.readNextString("Type:");
                    if (specialism.matches("0")){
                        break;
                    }
                    Specialist.add(specialism);
                }
                doctorAPI.addDoctor(new Specialist(doctorName, doctorDob, doctorGender, doctorAddress, doctorContactNumber,qualInIreland,qualifications,Specialist));
            }
        }
        else if(doctorType.equalsIgnoreCase("Intern"))
        {doctorAPI.addDoctor(new Intern(doctorName, doctorDob, doctorGender, doctorAddress, doctorContactNumber,qualifications)); }
        else{ System.out.println("Unknown"); }
    }

    private void listDoctors(){
        System.out.println("List of Doctors are: ");
        System.out.println(doctorAPI.listOfDoctors());
    }
    private void listDoctorsByName(){
        System.out.println(doctorAPI.listOfDoctors());
        String doctorNameToSearchBy = ScannerInput.readNextString("Enter a Doctor's name to search by: ");
        ArrayList<Doctor> doctorNames = doctorAPI.searchDoctorsByName(doctorNameToSearchBy);
        for(int i = 0; i < doctorNames.size(); i++){
            System.out.print(doctorNames.get(i) + " \n");
        }
    }

    private void updateDoctorDetails(){
        System.out.print(doctorAPI.listOfDoctors());
        int index = ScannerInput.readNextInt("\n Select a doctor to update (By index): ");
        if(index <= doctorAPI.numberOfDoctors() -1){
            doctorAPI.getDoctor(index).setName(ScannerInput.readNextString("Type New Doctor Name: "));
            doctorAPI.getDoctor(index).setDob(ScannerInput.readNextString("Type New Doctor Dob: "));
            doctorAPI.getDoctor(index).setAddress(ScannerInput.readNextString("Type New Doctor Address: "));
            doctorAPI.getDoctor(index).setContactNumber(ScannerInput.readNextString("Type New Doctor Contact Number: "));
            doctorAPI.getDoctor(index).setGender(ScannerInput.readNextChar("Type New Doctor Gender (M/F): "));
        }
        else{
            System.out.println("That doctor doesn't exist");
        }
    }

    private void updateDoctorQualifications(){
        System.out.print(doctorAPI.listOfDoctors());
        int index = ScannerInput.readNextInt("\n Select a doctor to update (By index): ");
        if(index <= doctorAPI.numberOfDoctors() -1){
            doctorAPI.getDoctor(index).getQualifications().get(index).setDegreeType(ScannerInput.readNextString("Type the new Degree Type: "));
            doctorAPI.getDoctor(index).getQualifications().get(index).setDegreeName(ScannerInput.readNextString("Type the new Degree Name: "));
            doctorAPI.getDoctor(index).getQualifications().get(index).setCollege(ScannerInput.readNextString("Type the new college: "));
            doctorAPI.getDoctor(index).getQualifications().get(index).setConferringYear(ScannerInput.readNextString("Type the new conferring year: "));
        }
        else{
            System.out.println("That doctor doesn't exist.");
        }
    }


    private void deleteDoctor(){
        System.out.print(doctorAPI.listOfDoctors());

        int index = ScannerInput.readNextInt("Enter the index of the doctor to delete: ");

        if (index < doctorAPI.numberOfDoctors()){
            doctorAPI.removeDoctor(index);
            System.out.print("Doctor has been deleted");
        }
        else{
            System.out.println("That Doctor index doesn't exist");
        }
    }

    private void updateDoctorSpecialisms(){
        System.out.print(doctorAPI.listOfDoctors());
        int index = ScannerInput.readNextInt("\n Select a doctor to update (By index): ");
        if (index <= doctorAPI.numberOfDoctors() - 1) {
           Doctor doctor = doctorAPI.getDoctor(index);
            if(doctor instanceof Specialist) {
                char specialism = ScannerInput.readNextChar("Would you like to add or delete a Specialism?(A/D): ");
                if(specialism == 'A'){
                    ((Specialist) doctor).getSpecialism().add(ScannerInput.readNextString("Enter the new Specialism: "));
                }
                else if(specialism == 'D'){
                    ((Specialist) doctor).getSpecialism().remove(ScannerInput.readNextString("Enter the Specialism to delete: "));
                }
            }
        }
    }
    private void annualFeesReport(){
        System.out.print("The Annual Total fee for Doctors is: " + doctorAPI.calculateAnnualFees());
    }

    private void reportByCategory(){
        System.out.println("The types of Doctors are: Intern, Specialist and General \n");
        String doctorType = ScannerInput.readNextString("Please enter what category to list doctors by: ").toLowerCase();
        if(doctorType.toLowerCase().matches("intern|specialist|general")){
            doctorAPI.listOfDoctors(doctorType);
        }
        else{
            System.out.println("That category does not exist");
        }
    }

    private void save() {
        try {
            doctorAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void load() {
        try {
            doctorAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }
}
