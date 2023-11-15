import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ScholarshipApplicationManager test = new ScholarshipApplicationManager();
        
        test.initialize();

        int scholarshipID;
        Scholarship scholarshipInfo;
        ArrayList<Application> applications;

        scholarshipID = test.getScholarshipID("NoName1");
        applications = test.sortApplicants(scholarshipID);

        for (Application tempApplication : applications) {
            System.out.println(test.applicationToString(tempApplication));
        }

        scholarshipInfo = test.getScholarshipInfo(scholarshipID);
        System.out.println((scholarshipInfo.getApplicationIDs()).toString());
    }
}
