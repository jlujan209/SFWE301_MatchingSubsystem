import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String name;
    private int ID;
    private double GPA;
    private String major;
    private int year;
    private List<Integer> applicationIDs;

    public Applicant(String name, int ID, double GPA, String major, int year) {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
        this.major = major;
        this.year = year;
        this.applicationIDs = new ArrayList<Integer>();

    }

    public void addApplication(int applicationID) {
        applicationIDs.add(applicationID);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    public String getMajor() {
        return major;
    }
}
