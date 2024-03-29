import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;
import java.lang.Math;
import java.util.Set;


public class ScholarshipApplicationManager {
    private final int MAX_6_BIT_HEX_NUM = 16777216 - 1;
    private Hashtable<Integer, Scholarship> Scholarships;
    private Hashtable<Integer, Application> Applications;
    private Hashtable<Integer, Applicant> Applicants;
    private Hashtable<String, Integer> ScholarshipSearchInfo;
    private Hashtable<String, Integer> ApplicantSearchInfo;

    public ScholarshipApplicationManager() {
        Scholarships = new Hashtable<Integer, Scholarship>();
        Applications = new Hashtable<Integer, Application>();
        Applicants = new Hashtable<Integer, Applicant>();
        ScholarshipSearchInfo = new Hashtable<String, Integer>();
        ApplicantSearchInfo = new Hashtable<String, Integer>();
    }
    
    private int generateID() {
        Random rand = new Random();
        int ID = rand.nextInt(MAX_6_BIT_HEX_NUM);
        
        return ID;
    }

    public void initializeWithCSVs(String scholarshipCSVFilename, String applicantCSVFilename, String applicationCSVFilename){
        Input input = new Input();
        ArrayList<Application> applications = input.readApplicationsCSV(applicationCSVFilename);
        ArrayList<Scholarship> scholarships = input.readScholarshipCSV(scholarshipCSVFilename);
        ArrayList<Applicant> applicants = input.readApplicantsCSV(applicantCSVFilename);

        for(Applicant A : applicants){
            addApplicant(A);
        }
        for(Scholarship s : scholarships){
            addScholarship(s);
        }
        for(Application a : applications){
            addApplication(a);
        }
    }

    public void initialize() {
        String[] majors = {"NA", "NA2", "NA3"};

        addApplicant(new Applicant("John Doe1", generateID(), 3.3, "NA", 3));
        addApplicant(new Applicant("John Doe2", generateID(), 4.2, "NA", 3));
        addApplicant(new Applicant("John Doe3", generateID(), 3.4, "NA", 3));
        addScholarship(new Scholarship("NoName1", generateID(), getApplicantID("John Doe1"), 3.4, "NA1", majors, 1, 1, 2001, -1));
        addScholarship(new Scholarship("NoName2", generateID(), getApplicantID("John Doe1"), 4.0, "NA2", majors, 2, 2, 2002, -2));
        addScholarship(new Scholarship("NoName3", generateID(), getApplicantID("John Doe1"), 4.0, "NA3", majors, 3, 3, 2003, -3));
        addApplication(new Application(getApplicantID("John Doe1"), generateID(), getScholarshipID("NoName1"), "String letter1"));
        addApplication(new Application(getApplicantID("John Doe2"), generateID(), getScholarshipID("NoName1"), "String letter2"));
        addApplication(new Application(getApplicantID("John Doe3"), generateID(), getScholarshipID("NoName1"), "String letter3"));
    }
    
    private ArrayList<Application> sortScholarshipApplications(int ScholarshipID) {
        ArrayList<Application> sortedList = new ArrayList<Application>();
        Scholarship currScholarship = getScholarshipInfo(ScholarshipID);
        List<Integer> applications = currScholarship.getApplicationIDs();
        int i;

        for (int application : applications) {
            Application currApplication = getApplicationInfo(application);
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
                        if (getApplicantInfo(currApplication.getApplicantID()).getGPA() > getApplicantInfo(tempApplication.getApplicantID()).getGPA()) {
                            break;
                        }
                        else if ((Math.abs(getApplicantInfo(currApplication.getApplicantID()).getGPA() - getApplicantInfo(tempApplication.getApplicantID()).getGPA())) <= 0.01) {
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

    private ArrayList<Application> sortApplicantApplications(int ApplicantID) {
        ArrayList<Application> sortedList = new ArrayList<Application>();
        Applicant currApplicant = getApplicantInfo(ApplicantID);
        List<Integer> applications = currApplicant.getApplicationIDs();
        int i;

        for (int application : applications) {
            Application currApplication = getApplicationInfo(application);
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
                        if (getApplicantInfo(currApplication.getApplicantID()).getGPA() > getApplicantInfo(tempApplication.getApplicantID()).getGPA()) {
                            break;
                        }
                        else if ((Math.abs(getApplicantInfo(currApplication.getApplicantID()).getGPA() - getApplicantInfo(tempApplication.getApplicantID()).getGPA())) <= 0.01) {
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
        if (Scholarships.get(ScholarshipID) == null) {
            throw new NullPointerException("Scholarship ID " + String.format("%06x", ScholarshipID) + " does not exist.\n");
        }
        return Scholarships.get(ScholarshipID);
    }

    public Applicant getApplicantInfo(int ApplicantID) {
        if (Applicants.get(ApplicantID) == null) {
            throw new NullPointerException("Application ID " + String.format("%06x", ApplicantID) + " does not exist.\n");
        }
        return Applicants.get(ApplicantID);
    }

    public Application getApplicationInfo(int ApplicationID) {
        if (Applications.get(ApplicationID) == null) {
            throw new NullPointerException("Application ID " + String.format("%06x", ApplicationID) + " does not exist.\n");
        }
        return Applications.get(ApplicationID);
    }

    public void addApplication(Application newApplication) {
        getApplicantInfo(newApplication.getApplicantID()).addApplication(newApplication.getID());
        Scholarship scholarship = getScholarshipInfo(newApplication.getScholarshipID());
        scholarship.addApplication(newApplication.getID());
        Applications.put(newApplication.getID(), newApplication);

        if (!isApplicationMatch(newApplication, scholarship)) {
            newApplication.setScore(0);
            newApplication.setStatus(1);
        }
    }

    public void addScholarship(Scholarship newScholarship) {
        getApplicantInfo(newScholarship.getOwnerID()).addScholarship(newScholarship.getID());
        Scholarships.put(newScholarship.getID(), newScholarship);
        ScholarshipSearchInfo.put(newScholarship.getName(), newScholarship.getID());
    }

    public void addApplicant(Applicant newApplicant) {
        Applicants.put(newApplicant.getID(), newApplicant);
        ApplicantSearchInfo.put(newApplicant.getName(), newApplicant.getID());
    }

    public String applicationToString(int applicationID) {
        Application application = getApplicationInfo(applicationID);
        Applicant applicant = getApplicantInfo(application.getApplicantID());
        String result = ("Application: " + String.format("%06x", application.getID()) + "\nGPA: " + applicant.getGPA() + "\nLetter: " + application.getLetter() + "\nScore: " + application.getScore() + "\nStatus: ");

        if (application.getStatus() == 1) {
            result += "APPLIED";
        }
        else if (application.getStatus() == 2) {
            result += "REAPPLIED";
        }
        else if (application.getStatus() == 3) {
            result += "DENIED";
        }
        else if (application.getStatus() == 4) {
            result += "NO_LONGER_IN_CONSIDERATION";
        }
        else if (application.getStatus() == 5) {
            result += "REVIEWED";
        }
        else if (application.getStatus() == 6) {
            result += "APPROVED";
        }
        else {
            result += "UNKNOWN";
        }

        return result  + "\n";
    }

    public String applicantToString(int applicantID) {
        Applicant applicant = getApplicantInfo(applicantID);
        return ("Applicant: " + applicant.getName() + "\nID: " + String.format("%06x", applicant.getID()) + "\nGPA: " + applicant.getGPA() + "\n" + "\nGPA: " + applicant.getGPA() + "\nMajor: " + applicant.getMajor() + "\nNumber of Applications: " + applicant.getApplicationIDs().size() + "\n");
        
    }

    public String scholarshipToString(int scholarshipID) {
        Scholarship scholarship = getScholarshipInfo(scholarshipID);
        String[] acceptedMajors = scholarship.getAcceptedMajors();
        String acceptedMajorsString = "";
        int i = 0;
        for (String major : acceptedMajors) {
            acceptedMajorsString += major;
            ++i;
            if (i == acceptedMajors.length - 1) {
                if (i == 1) {
                    acceptedMajorsString += " and ";
                }
                else {
                    acceptedMajorsString += ", and ";
                }
                
            }
            else if (i != acceptedMajors.length) {
                acceptedMajorsString += ", ";
            }
        }
        
        return ("Scholarship: " + scholarship.getName() + "\nID: " + String.format("%06x", scholarship.getID()) + "\nMinimum GPA: " + scholarship.getMinGPA() + "\nDepartment: " + scholarship.getDepartment() + "\nAccepted Majors: " + acceptedMajorsString
                + "\nSelection Date: " + scholarship.getSelectionDate().toString() + "\nAwarded Amount: $" + String.format("%.2f", scholarship.getAwardAmount()) + "\nNumber of Applications: " + scholarship.getApplicationIDs().size() + "\n");
    }

    public ArrayList<Application> getScholarshipApplicationsAboveScore(int ScholarshipID, int minScore) {
        ArrayList<Application> result = new ArrayList<Application>();
        for (Application application : sortScholarshipApplications(ScholarshipID)) {
            if (application.getScore() < minScore) {
                break;
            }
            else if (!((application.getStatus() == 3) && (minScore != 0)) && (application.getStatus() != 4)) {
                result.add(application);
            }
        }

        return result;
    }
    
    public ArrayList<Scholarship> findScholarshipSpecificCriteria(double minGPA, String acceptedMajor, double awardAmount) {
    	Enumeration<Integer> keys = Scholarships.keys();
    	ArrayList<Scholarship> qualifiedScholarships = new ArrayList<Scholarship>();
    	while(keys.hasMoreElements()) {
    		int key = keys.nextElement();
    		boolean majorCheck = false;
    		for(String major : Scholarships.get(key).getAcceptedMajors()) {
    			if(acceptedMajor.equals(major)) {
    				majorCheck = true;
    			}
    			
    		}
    		if((minGPA < Scholarships.get(key).getMinGPA()) && majorCheck && (awardAmount <= Scholarships.get(key).getAwardAmount())) {
    			qualifiedScholarships.add(Scholarships.get(key));
    		}
    	}
    	return qualifiedScholarships;
    }

    private boolean isApplicationMatch(Application application, Scholarship scholarship) {
        boolean majorMatch = false;

        for (String major : scholarship.getAcceptedMajors()) {
            majorMatch = majorMatch || major.equals(getApplicantInfo(application.getApplicantID()).getMajor());
        }
        return (getApplicantInfo(application.getApplicantID()).getGPA() >= scholarship.getMinGPA()) && majorMatch;
    }

    public void toReports() {
        Set<Integer> ScholarshipIDs = Scholarships.keySet();

        for(Integer x: ScholarshipIDs){
            Scholarship curScholarship = Scholarships.get(x);
            ArrayList<Application> sortedApplications = sortScholarshipApplications(x);
            ArrayList<String> csvStrings = toCSVString(sortedApplications);
            Reports newCSV = new Reports(csvStrings);
            newCSV.toCSV(curScholarship.getName());
        }

    }

    private ArrayList<String> toCSVString(ArrayList<Application> list) {
        ArrayList<String> resultList = new ArrayList<String>();
        resultList.add("Application ID,Applicant ID,Letter,Score,Application Status");
        for(Application i : list){
            String toAdd = Integer.toString(i.getID()) + "," + Integer.toString(i.getApplicantID()) +
                "," + i.getLetter() + "," + Integer.toString(i.getScore()) + ",";
                if (i.getStatus() == 1) {
                    toAdd += "APPLIED";
                }
                else if (i.getStatus() == 2) {
                    toAdd += "REAPPLIED";
                }
                else if (i.getStatus() == 3) {
                    toAdd += "DENIED";
                }
                else if (i.getStatus() == 4) {
                    toAdd += "NO_LONGER_IN_CONSIDERATION";
                }
                else if (i.getStatus() == 5) {
                    toAdd += "REVIEWED";
                }
                else if (i.getStatus() == 6) {
                    toAdd += "APPROVED";
                }
                else {
                    toAdd += "UNKNOWN";
                }
            resultList.add(toAdd);
            }

        return resultList;
    }
}