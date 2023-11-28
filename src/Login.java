import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        ScholarshipApplicationManager manager = new ScholarshipApplicationManager();
        manager.initializeWithCSVs("Scholarships.csv", "Applicants.csv", "Applications.csv");

        try (Scanner scnr = new Scanner(System.in)) {

            boolean loggedIn = false;
            int numInputs = 0;

            while (!loggedIn) {
                System.out.println("Username: ");
                String userName = scnr.nextLine();

                if ((manager.getApplicantID(userName) != null) && (manager.getApplicantInfo(manager.getApplicantID(userName)) != null)) {
                    Applicant currUser = manager.getApplicantInfo(manager.getApplicantID(userName));
                    loggedIn = true;
                    System.out.printf("Hello " + currUser.getName() + "!\n\n");
                    numInputs += 1;
                    System.out.println("Would you like to... \n" + numInputs + ") logout");
                    if ((currUser.getScholarshipIDs() != null) && (currUser.getScholarshipIDs().isEmpty())) {
                        numInputs += 1;
                        System.out.println(" " + numInputs + ") review scholarship info");
                    }
                    else if (!currUser.getApplicationIDs().isEmpty()) {
                        numInputs += 1;
                        System.out.println(" " + numInputs + ") review applications");
                    }

                    System.out.println("2) review scholarship info \n Type the number to the left of desired action. \n Action: ");













                    System.out.println("Choose user action: 1 = view all available scholarships according to desiring criteria.");
                    int action = scnr.nextInt();
                    if (action == 1) {
                        System.out.println("Enter search criteria minimum accepted GPA: ");
                        float gpa = scnr.nextFloat();
                        System.out.println("Enter search criteria scholarship department(s): ");
                        String department = scnr.next();
                        System.out.println("Enter search criteria rewarded amount: ");
                        int amount = scnr.nextInt();
                        System.out.printf(
                                "Scholarships according to desiring criteria: gpa: %.1f, departments: %s, amount: %d$\n",
                                gpa,
                                department,
                                amount);
                        ArrayList<Scholarship> scholarships = manager.findScholarshipSpecificCriteria(gpa, department, amount);
                        for (int i = 0; i < scholarships.size(); i++) {
                            manager.scholarshipToString(scholarships.get(i).getID());
                        }
                    }
                    else if (action == 2) {
                        for (int i = 0; i < currUser.getScholarshipIDs().size(); i++) {
                            System.out.println(manager.scholarshipToString(currUser.getScholarshipIDs().get(i)));
                        }
                    }
                    else {
                        System.out.println("Invalid action");
                    }
                } else {
                    System.out.println("Please login again");
                }
            }
            System.out.println("Bye!");
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
