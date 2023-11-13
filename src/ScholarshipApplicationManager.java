import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;

public class ScholarshipApplicationManager {
    private final int MAX_6_BIT_HEX_NUM = 16777216 - 1;
    private Hashtable<String, Scholarship> Scholarships;
    private Hashtable<String, Application> Applications;
    private Hashtable<String, Applicant> Applicants;
    private Hashtable<String, String> ScholarshipSearchInfo;
    private List<String> ApplicationSearchInfo;
    private Hashtable<String, String> ApplicantSearchInfo;

    public ScholarshipApplicationManager() {
        Scholarships = new Hashtable<String, Scholarship>();
        Applications = new Hashtable<String, Application>();
        Applicants = new Hashtable<String, Applicant>();
        ScholarshipSearchInfo = new Hashtable<String, String>();
        ApplicationSearchInfo = new ArrayList<String>();
        ApplicantSearchInfo = new Hashtable<String, String>();
    }
    
    private String generateID() {
        Random rand = new Random();
        int n = rand.nextInt(MAX_6_BIT_HEX_NUM);
        String ID = String.format("%06x", n);
        
        return ID;
    }

    public void initialize() {
        String[] majors = {"NA"};

        addScholarship(new Scholarship("NoName1", generateID(), -1, "NA1", majors, 1, 1, 2001, -1));
        addScholarship(new Scholarship("NoName2", generateID(), -2, "NA2", majors, 2, 2, 2002, -2));
        addScholarship(new Scholarship("NoName3", generateID(), -3, "NA3", majors, 3, 3, 2003, -3));

        addApplicant(new Applicant("John Doe", generateID(), 3.1, "NA", 3));
        addApplication(new Application(getApplicantID("John Doe"), generateID(), getScholarshipID("NoName1"), "String letter"));
    }
    
    public ArrayList<Application> sortApplicants(Hashtable<String, Scholarship> Scholarships) {
        
        
        return new ArrayList<Application>();
    }

    public String getScholarshipID(String ScholarshipName) {
        return ScholarshipSearchInfo.get(ScholarshipName);
    }

    public String getApplicantID(String ApplicantName) {
        return ApplicantSearchInfo.get(ApplicantName);
    }

    public Scholarship getScholarshipInfo(String ScholarshipID) {
        return Scholarships.get(ScholarshipID);
    }

    public Applicant getApplicantInfo(String ApplicantID) {
        return Applicants.get(ApplicantID);
    }


    public void addApplication(Application newApplication) {
        try {
            String tempApplicantID = newApplication.getApplicantID();
            String tempScholarshipID = newApplication.getScholarshipID();
            
            if (tempApplicantID == null) {
                throw new NullPointerException("Application's applicant doesn't exist.");
            }
            if (tempScholarshipID == null) {
                throw new NullPointerException("Application's scholarship doesn't exist.");
            }

            getApplicantInfo(newApplication.getApplicantID()).addApplication(newApplication.getID());
            getScholarshipInfo(newApplication.getScholarshipID()).addApplication(newApplication.getID());
            Applications.put(newApplication.getID(), newApplication);
            ApplicationSearchInfo.add(newApplication.getID());
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
}