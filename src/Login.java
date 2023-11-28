import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        ScholarshipApplicationManager manager = new ScholarshipApplicationManager();
        manager.initializeWithCSVs("Scholarships.csv", "Applicants.csv", "Applications.csv");

        try (Scanner scnr = new Scanner(System.in)) {

            boolean loggedIn = false;
            int numInputs = 0;
            boolean exit = false;
            boolean isAdmin = false;
            int action;
            char actionName;
            boolean validInput;
            boolean internalExit = false;

            while (!exit) {
                Applicant currUser = null;
                String userName = null;
                validInput = false;

                while (!validInput) {
                    System.out.println("Would you like to exit?\n(y/n): ");
                    actionName = scnr.next().charAt(0);
                    scnr.nextLine();
                    if (actionName == 'n') {
                        validInput = true;
                    } else if (actionName == 'y') {
                        validInput = true;
                        exit = true;
                    }
                }

                while ((!loggedIn) && (!exit)) {
                    System.out.println("Username: ");
                    userName = scnr.nextLine();

                    if ((manager.getApplicantID(userName) != null) && (manager.getApplicantInfo(manager.getApplicantID(userName)) != null)) {
                        currUser = manager.getApplicantInfo(manager.getApplicantID(userName));
                        loggedIn = true;
                        System.out.println("Hello " + currUser.getName() + "!\n");
                    }
                    else {
                        System.out.println("Username " + userName + " does not exist.\n");
                    }
                }

                while (loggedIn) {
                    numInputs = 1;
                    if ((currUser.getScholarshipIDs() != null) && !(currUser.getScholarshipIDs().isEmpty())) {
                        System.out.println("Would you like to...\n 1) logout");
                        System.out.println(" 2) review scholarship info");
                        isAdmin = true;
                    }
                    else if (!currUser.getApplicationIDs().isEmpty()) {
                        System.out.println("Would you like to...\n 1) logout");
                        System.out.println(" 2) review applications");
                    }
                    else {
                        System.out.println("User " + userName + " has no access to scholarships or applications.\n");
                        loggedIn = false;
                        break;
                    }

                    System.out.println("Type the number to the left of desired action. \nAction: ");

                    action = scnr.nextInt();
                    scnr.nextLine();
                    if (action == 1) {
                        loggedIn = false;
                    }
                    else if ((action == 2) && isAdmin) {
                        for (int i = 0; i < currUser.getScholarshipIDs().size(); i++) {
                            System.out.println((i + 1) + ") " + manager.getScholarshipInfo(currUser.getScholarshipIDs().get(i)).getName());
                        }
                        
                        while(action != 0) {
                            System.out.println("Type the number to the left of scholarship to view or type 0 to return to home page. /n View: ");

                            action = scnr.nextInt();
                            scnr.nextLine();
                            if ((action > 0) && (action <= currUser.getScholarshipIDs().size())) {
                                System.out.println(manager.scholarshipToString(currUser.getScholarshipIDs().get(action - 1)));
                                
                                action = 0;
                                internalExit = false;
                                while(!internalExit) {
                                    System.out.println("Would you like to...\n 1) review applications\n 2) Exit /n Type the number to the left of desired action. /n Action: ");

                                    action = scnr.nextInt();
                                    scnr.nextLine();
                                    if (action == 1) {
                                        System.out.println("Would you like to...\n 1) view matched applicants \n 2) view all applicants\nType the number to the right of desired action.\nAction: ");
                                        System.out.println(manager.scholarshipToString(currUser.getScholarshipIDs().get(action - 1)));
                                    }
                                    else if (action == 2) {
                                        internalExit = true;
                                    }
                                    else {
                                        System.out.println("Invalid action");
                                    }
                                }
                            }
                            else if (action != 0) {
                                System.out.println("Invalid action");
                            }
                        }
                    }
                    else if (action == 0) {
                        loggedIn = false;
                    }
                }
            }
            System.out.println("Bye!");
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
