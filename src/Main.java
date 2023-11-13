public class Main {
    public static void main(String[] args) throws Exception {
        ScholarshipApplicationManager test = new ScholarshipApplicationManager();
        
        test.initialize();

        String scholarshipName;
        Scholarship scholarshipInfo;

        scholarshipName = test.getScholarshipID("NoName4");
        if (scholarshipName != null) {
            scholarshipInfo = test.getScholarshipInfo(scholarshipName);
            if (scholarshipInfo != null) {
                System.out.println((scholarshipInfo.getApplicationIDs()).toString());
            }
        }
    }
}
