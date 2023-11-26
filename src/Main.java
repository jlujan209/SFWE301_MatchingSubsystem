import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ScholarshipApplicationManager test = new ScholarshipApplicationManager();
        
        test.initialize();

        int scholarshipID;

        scholarshipID = test.getScholarshipID("NoName1");

        System.out.println(test.scholarshipToString(scholarshipID));

        System.out.println(test.printApplicationsAboveScore(scholarshipID, -1));

        Reports testing = new Reports(new ArrayList<String>());

        testing.addApplicant("Someone,GPA: 4.0,Score: 100");
        testing.addApplicant("Someone Else,GPA: 3.0,Score: 53");
        testing.addApplicant("Someone else from someone else,GPA: 3.2,Score: 45");
        testing.addApplicant("No One,GPA: 2.4,Score: 24");

        testing.toCSV("testScholarship");
    }
}
