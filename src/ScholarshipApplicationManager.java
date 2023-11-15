import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;
import java.lang.Math;

public class ScholarshipApplicationManager {
    private final int MAX_6_BIT_HEX_NUM = 16777216 - 1;
    private Hashtable<Integer, Scholarship> Scholarships;
    private Hashtable<Integer, Application> Applications;
    private Hashtable<Integer, Applicant> Applicants;
    private Hashtable<String, Integer> ScholarshipSearchInfo;
    private List<Integer> ApplicationSearchInfo;
    private Hashtable<String, Integer> ApplicantSearchInfo;

    public ScholarshipApplicationManager() {
        Scholarships = new Hashtable<Integer, Scholarship>();
        Applications = new Hashtable<Integer, Application>();
        Applicants = new Hashtable<Integer, Applicant>();
        ScholarshipSearchInfo = new Hashtable<String, Integer>();
        ApplicationSearchInfo = new ArrayList<Integer>();
        ApplicantSearchInfo = new Hashtable<String, Integer>();
    }
    
    private int generateID() {
        Random rand = new Random();
        int ID = rand.nextInt(MAX_6_BIT_HEX_NUM);
        
        return ID;
    }

    public void initialize() {
        String[] majors = {"NA"};

        addScholarship(new Scholarship("NoName1", generateID(), 3.3, "NA1", majors, 1, 1, 2001, -1));
        addScholarship(new Scholarship("NoName2", generateID(), 4.0, "NA2", majors, 2, 2, 2002, -2));
        addScholarship(new Scholarship("NoName3", generateID(), 4.0, "NA3", majors, 3, 3, 2003, -3));

        addApplicant(new Applicant("John DoeF", generateID(), 3.3, "NA", 3));
        addApplicant(new Applicant("John Doe1", generateID(), 3.2, "NA", 3));
        addApplication(new Application(getApplicantID("John DoeF"), generateID(), getScholarshipID("NoName1"), "String letter1"));
        addApplication(new Application(getApplicantID("John Doe1"), generateID(), getScholarshipID("NoName1"), "String letter2"));
    }
    
    public ArrayList<Application> sortApplicants(int ScholarshipID) {
        ArrayList<Application> sortedList = new ArrayList<Application>();
        Scholarship currScholarship = Scholarships.get(ScholarshipID);
        List<Integer> applications = currScholarship.getApplicationIDs();
        int i;

        for (int application : applications) {
            Application currApplication = Applications.get(application);
            if (sortedList.isEmpty()) {
                sortedList.add(currApplication);
            }
            else {
                i = 0;
                for (Application tempApplication : sortedList) {
                    if (currApplication.getScore() > tempApplication.getScore()) {
                        break;
                    }
                    else if ((currApplication.getScore() == tempApplication.getScore())) {
                        if (Applicants.get(currApplication.getApplicantID()).getGPA() > Applicants.get(tempApplication.getApplicantID()).getGPA()) {
                            break;
                        }
                        else if ((Math.abs(Applicants.get(currApplication.getApplicantID()).getGPA() - Applicants.get(tempApplication.getApplicantID()).getGPA())) <= 0.01) {
                            if (currApplication.getID() > tempApplication.getID()) {
                                break;
                            }
                        }
                    }
                    ++i;
                }
                sortedList.add(i, currApplication);
            }
        }
        return sortedList;
    }

    public Integer getScholarshipID(String ScholarshipName) {
        return ScholarshipSearchInfo.get(ScholarshipName);
    }

    public Integer getApplicantID(String ApplicantName) {
        return ApplicantSearchInfo.get(ApplicantName);
    }

    public Scholarship getScholarshipInfo(int ScholarshipID) {
        return Scholarships.get(ScholarshipID);
    }

    public Applicant getApplicantInfo(int ApplicantID) {
        return Applicants.get(ApplicantID);
    }

    public void addApplication(Application newApplication) {
        try {
            if ((Integer) newApplication.getApplicantID() == null) {
                throw new NullPointerException("Application's applicant doesn't exist.");
            }
            if ((Integer) newApplication.getScholarshipID() == null) {
                throw new NullPointerException("Application's scholarship doesn't exist.");
            }

            getApplicantInfo(newApplication.getApplicantID()).addApplication(newApplication.getID());
            Scholarship scholarship = getScholarshipInfo(newApplication.getScholarshipID());
            scholarship.addApplication(newApplication.getID());
            Applications.put(newApplication.getID(), newApplication);
            ApplicationSearchInfo.add(newApplication.getID());

            if (!isApplicationMatch(newApplication, scholarship)) {
                newApplication.setScore(0);
            }
        }
        catch (NullPointerException except) {
            System.out.println(except.getMessage());
        }
    }

    public void addScholarship(Scholarship newScholarship) {
        Scholarships.put(newScholarship.getID(), newScholarship);
        ScholarshipSearchInfo.put(newScholarship.getName(), newScholarship.getID());
    }

    public void addApplicant(Applicant newApplicant) {
        Applicants.put(newApplicant.getID(), newApplicant);
        ApplicantSearchInfo.put(newApplicant.getName(), newApplicant.getID());
    }

    public String applicationToString(Application application) {
        Applicant applicant = getApplicantInfo(application.getApplicantID());
        // return ("Application: " + String.format("%06x", applicant.getID()) + "\n" + "Applicant GPA: " + applicant.getGPA() + "\n" + "Application Letter: " + application.getLetter() + "\n");
        return ("Application: " + application.getID() + "\n" + "Applicant GPA: " + applicant.getGPA() + "\n" + "Application Letter: " + application.getLetter() + "\n" + "Application Score: " + application.getScore() + "\n");
    }

    public boolean isApplicationMatch(Application application, Scholarship scholarship) {
        boolean majorMatch = false;

        for (String major : scholarship.getAcceptedMajors()) {
            majorMatch = majorMatch || major.equals(getApplicantInfo(application.getApplicantID()).getMajor());
        }
        return (getApplicantInfo(application.getApplicantID()).getGPA() >= scholarship.getMinGPA()) && majorMatch;
    }
}