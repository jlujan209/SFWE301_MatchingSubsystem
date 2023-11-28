import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Scholarship {
    private String name;
    private double minGPA;
    private String department;
    private String[] acceptedMajors;
    private LocalDate selectionDate;
    private double awardAmount;
    private List<Integer> applicationIDs;
    private int ID;
    private int ownerID;

    public Scholarship(String name, int ID, int ownerID, double minGPA, String department, String[] acceptedMajors, int selectionDay, int selectionMonth, int selectionYear, float awardAmount) {
        this.name = name;
        this.ID = ID;
        this.ownerID = ownerID;
        this.minGPA = minGPA;
        this.department = department;
        this.acceptedMajors = new String[acceptedMajors.length];
        selectionDate = LocalDate.of(selectionYear, selectionMonth, selectionDay);;
        this.awardAmount = awardAmount;
        this.applicationIDs = new ArrayList<Integer>();

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

    public double getMinGPA() {
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

    public List<Integer> getApplicationIDs() {
        return applicationIDs;
    }

    public void addApplication(int applicationID) {
        applicationIDs.add(applicationID);
    }

    public int getID() {
        return ID;
    }

    public int getOwnerID() {
        return ownerID;
    }
}
