import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String name;
    private String ID;
    private double GPA;
    private String major;
    private int year;
    private List<String> applicationIDs;

    public Applicant(String name, String ID, double GPA, String major, int year) {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
        this.major = major;
        this.year = year;
        this.applicationIDs = new ArrayList<String>();

    }

    public void addApplication(String applicationID) {
        applicationIDs.add(applicationID);
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
