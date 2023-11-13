import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Scholarship {
    private String name;
    private float minGPA;
    private String department;
    private String[] acceptedMajors;
    private LocalDate selectionDate;
    private double awardAmount;
    private List<String> applicationIDs;
    private String ID;

    public Scholarship(String name, String ID, float minGPA, String department, String[] acceptedMajors, int selectionDay, int selectionMonth, int selectionYear, float awardAmount) {
        this.name = name;
        this.ID = ID;
        this.minGPA = minGPA;
        this.department = department;
        this.acceptedMajors = new String[acceptedMajors.length];
        selectionDate = LocalDate.of(selectionYear, selectionMonth, selectionDay);;
        this.awardAmount = awardAmount;
        this.applicationIDs = new ArrayList<String>();

        try {
            for (int i = 0; i < acceptedMajors.length; ++i) {
                if (acceptedMajors[i].length() > 4) {
                    throw new IllegalArgumentException("Accepted majors input string must be at most 4 characters but found "
                                                       + acceptedMajors[i].length() + " at index " + i + ".");
                }
                else {
                    this.acceptedMajors[i] = new String(acceptedMajors[i]);
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public String getName() {
        return this.name;
    }

    public String[] getAcceptedMajors() {
        return acceptedMajors;
    }

    public void setAcceptedMajors(String[] acceptedMajors) {
        this.acceptedMajors = acceptedMajors;
    }

    public double getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(double awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getMinGPA() {
        return minGPA;
    }

    public void setMinGPA(float minGPA) {
        this.minGPA = minGPA;
    }

    public LocalDate getSelectionDate() {
        return selectionDate;
    }

    public void setSelectionDate(LocalDate selectionDate) {
        this.selectionDate = selectionDate;
    }

    public List<String> getApplicationIDs() {
        return applicationIDs;
    }

    public void addApplication(String applicationID) {
        applicationIDs.add(applicationID);
    }

    public String getID() {
        return ID;
    }
}
