import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ScholarshipApplicationManager test = new ScholarshipApplicationManager();
        
        test.initialize();

        int scholarshipID;

        scholarshipID = test.getScholarshipID("NoName1");

        System.out.println(test.scholarshipToString(scholarshipID));
    }
}
