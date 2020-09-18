package models;

public class Qualification {
    private String degreeType;
    private String degreeName;
    private String college;
    private String conferringYear;

    public Qualification(String degreeType, String degreeName,String college,String conferringYear ){
        this.degreeType = degreeType;
        this.degreeName = degreeName;
        this.college = college;
        this.conferringYear = conferringYear;
    }
    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getConferringYear() {
        return conferringYear;
    }

    public void setConferringYear(String conferringYear) {
        this.conferringYear = conferringYear;
    }
    @Override
    public String toString() {
        return "Qualification = \n" +
                degreeType + " in " +
                degreeName +
                " (" + college + ", " +
                conferringYear + "). \n";
    }
}
