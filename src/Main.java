import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ScholarshipApplicationManager test = new ScholarshipApplicationManager();
        String applications = "Applications.csv";
        String applicants = "Applicants.csv";
        String scholarships = "Scholarships.csv"; 
        test.initializeWithCSVs(scholarships, applicants, applications);

        test.toReports();

    }
}
