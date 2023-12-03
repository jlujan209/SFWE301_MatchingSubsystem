import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        ScholarshipApplicationManager manager = new ScholarshipApplicationManager();
        manager.initializeWithCSVs("Scholarships.csv", "Applicants.csv", "Applications.csv");

        try (Scanner scnr = new Scanner(System.in)) {

            boolean loggedIn = false;
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
                    if ((currUser.getScholarshipIDs() != null) && !(currUser.getScholarshipIDs().isEmpty())) {
                        System.out.println("Would you like to...\n 1) logout");
                        System.out.println(" 2) review scholarship info");
                        isAdmin = true;
                    }
                    else if (!currUser.getApplicationIDs().isEmpty()) {
                        System.out.println("Would you like to...\n 1) logout");
                        System.out.println(" 2) review applications");
                        System.out.println(" 3) view scholarships");
                        isAdmin = false;
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
                            System.out.println("Type the number to the left of scholarship to view or type 0 to return to home page.");
                            
                            System.out.print("View: ");
                            Scholarship currScholarship;
                            action = scnr.nextInt();
                            scnr.nextLine();
                            if ((action > 0) && (action <= currUser.getScholarshipIDs().size())) {
                                currScholarship = manager.getScholarshipInfo(currUser.getScholarshipIDs().get(action - 1));
                                
                                action = 0;
                                internalExit = false;
                                while(!internalExit) {
                                    System.out.println(manager.scholarshipToString(currScholarship.getID()));
                                    
                                    System.out.println("Would you like to...\n 1) review applications\n 2) Exit \n Type the number to the left of desired action. \n Action: ");

                                    action = scnr.nextInt();
                                    scnr.nextLine();
                                    if (action == 1) {
                                        System.out.println("Would you like to...\n 1) view matched applicants \n 2) view all applicants\nType the number to the right of desired action.");

                                        while(!internalExit) {
                                            ArrayList<Application> applications;
                                            System.out.print("Action: ");
                                            action = scnr.nextInt();
                                            scnr.nextLine();
                                            if ((action == 1) || (action == 2)) {
                                                if (action == 1) {
                                                    applications = manager.getScholarshipApplicationsAboveScore(currScholarship.getID(), 1);
                                                }
                                                else {
                                                    applications = manager.getScholarshipApplicationsAboveScore(currScholarship.getID(), 0);
                                                }
                                                for (int i = 0; i < applications.size(); ++i) {
                                                    System.out.println((i + 1) + ") Application ID: " + String.format("%06x", applications.get(i).getID()));
                                                    System.out.println("Score: " + applications.get(i).getScore() + "\n");
                                                }

                                                System.out.println("Type the number next to the application to review or 0 to return to the scholarship information page.");
                                                
                                                while(!internalExit) {
                                                    System.out.print("Application #: ");
                                                    action = scnr.nextInt();
                                                    scnr.nextLine();
                                                    if ((action > 0) && (action <= applications.size())) {
                                                        System.out.println(manager.applicationToString(applications.get(action - 1).getID()));
                                                        
                                                        System.out.println("Would you like to...\n 1) set application priority score\n 2) set application status\n 3) return to applications page\n Type the number to the left of desired action.");
                                                

                                                        while(!internalExit) {
                                                            System.out.print("Action: ");
                                                            int action1 = scnr.nextInt();
                                                            scnr.nextLine();
                                                            if (action1 == 1) {
                                                                System.out.println("Input application priority score.");

                                                                while(!internalExit) {
                                                                    System.out.print("Score: ");
                                                                    int input = scnr.nextInt();
                                                                    scnr.nextLine();
                                                                    if (input >= 0) {
                                                                        applications.get(action - 1).setScore(input);
                                                                        System.out.println("Application " + String.format("%06x", applications.get(action - 1).getID()) + " priority score set to " + applications.get(action - 1).getScore() + ".");
                                                                        internalExit = true;
                                                                    }
                                                                    else {
                                                                        System.out.println("Invalid input");
                                                                    }
                                                                }
                                                            }
                                                            else if (action1 == 2) {
                                                                validInput = false;
                                                                System.out.println("Would you like to set " + String.format("%06x", applications.get(action - 1).getID()) + " status to (1) denied, (2) reviewed, or (3) approved?");
                                                                while (!validInput) {
                                                                    System.out.print("New Status: ");
                                                                    action1 = scnr.nextInt();
                                                                    scnr.nextLine();
                                                                    if (action1 == 1) {
                                                                        validInput = true;
                                                                        applications.get(action - 1).setScore(0);
                                                                        if (applications.get(action - 1).getStatus() == 2) {
                                                                            applications.get(action - 1).setStatus(2);
                                                                        }
                                                                        else {
                                                                            applications.get(action - 1).setStatus(1);
                                                                        }

                                                                        System.out.println("Application: " + String.format("%06x", applications.get(action - 1).getID()) + " status set to denied.");
                                                                    } else if (action1 == 2) {
                                                                        validInput = true;
                                                                        applications.get(action - 1).setStatus(3);
                                                                        System.out.println("Application: " + String.format("%06x", applications.get(action - 1).getID()) + " status set to reviewed.");
                                                                    } else if (action1 == 3) {
                                                                        validInput = true;
                                                                        applications.get(action - 1).setStatus(4);
                                                                        System.out.println("Application: " + String.format("%06x", applications.get(action - 1).getID()) + " status set to approved.");
                                                                    }
                                                                }

                                                                internalExit = true;
                                                            }
                                                            else if (action1 == 3) {
                                                                internalExit = true;
                                                            }
                                                            else {
                                                                System.out.println("Invalid action");
                                                            }
                                                        }

                                                        internalExit = true;
                                                    }
                                                    else if (action == 0) {
                                                        internalExit = true;
                                                    }
                                                    else {
                                                        System.out.println("Invalid action");
                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Invalid action");
                                            }
                                        }
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
                    else if (action == 2) {
                        ArrayList<Application> applications = new ArrayList<Application>();
                        for (int i = 0; i < currUser.getApplicationIDs().size(); ++i) {
                            applications.add(manager.getApplicationInfo(currUser.getApplicationIDs().get(i)));
                            System.out.println((i + 1) + ") Application ID: " + applications.get(i).getID());
                            System.out.println("Score: " + applications.get(i).getScore() + "\n");
                        }

                        internalExit = false;
                        System.out.println("Type the number next to the application to review or 0 to return to the scholarship information page.");
                        
                        while(!internalExit) {
                            System.out.println("Application #: ");
                            action = scnr.nextInt();
                            scnr.nextLine();
                            if ((action > 0) && (action <= applications.size())) {
                                System.out.println(manager.applicationToString(applications.get(action - 1).getID()));
                                if (applications.get(action - 1).getStatus() == 3) {
                                    System.out.println("Application " + String.format("%06x", applications.get(action - 1).getID()) + " has been denied. Would you like to resubmit the application?");
                                    validInput = false;
                                    while (!validInput) {
                                        System.out.println("(y/n): ");
                                        actionName = scnr.next().charAt(0);
                                        scnr.nextLine();
                                        if (actionName == 'y') {
                                            validInput = true;
                                            applications.get(action - 1).setStatus(0);
                                            System.out.println("Application " + String.format("%06x", applications.get(action - 1).getID()) + "has been resubmitted.");
                                        } else if (actionName == 'n') {
                                            validInput = true;
                                            exit = true;
                                        }
                                    }
                                }

                                System.out.println("Type the number next to the application to review or 0 to return to the scholarship information page.");
                            }
                            else if (action == 0) {
                                internalExit = true;
                            }
                            else {
                                System.out.println("Invalid action");
                            }
                        }
                        internalExit = true;
                    }
                    else if ((action == 3) && (!isAdmin)) {
                        internalExit = false;
                        ArrayList<Scholarship> scholarships = new ArrayList<Scholarship>();

                        System.out.println("Would you like to...\n 1) return to homepage");
                        System.out.println(" 2) view all scholarships");
                        System.out.println(" 3) view scholarships by search criteria");
                        
                        while(!internalExit) {
                            System.out.println("Action: ");
                            action = scnr.nextInt();
                            scnr.nextLine();
                            if (action == 1) {
                                internalExit = true;
                            }
                            else if (action == 2) {
                                scholarships = manager.findScholarshipSpecificCriteria(currUser.getGPA(), currUser.getMajor(), 0.0);
                                if (scholarships.size() == 0) {
                                    System.out.println("No applicable scholarships.");
                                }
                                else {
                                    for (int i = 0; i < scholarships.size(); ++i) {
                                        System.out.println((i + 1) + manager.scholarshipToString(scholarships.get(i).getID()));
                                    }
                                }
                                internalExit = true;
                            }
                            else if (action == 3) {
                                System.out.println("Type the minimum GPA.");
                                double gpa = currUser.getGPA();
                                String major = currUser.getMajor();
                                double awardAmount = 0.0;
                                
                                internalExit = false;
                                while(!internalExit) {
                                    System.out.println("GPA: ");
                                    double input;
                                    input = scnr.nextDouble();
                                    scnr.nextLine();
                                    if ((input > 0.0) && (input <= 4.0)) {
                                        gpa = input;
                                    }
                                    else {
                                        System.out.println("Invalid input");
                                    }
                                }
                                internalExit = false;
                                System.out.println("Type the accepted major.");

                                while(!internalExit) {
                                    System.out.println("Major: ");
                                    String input;
                                    input = scnr.next();
                                    scnr.nextLine();
                                    if ((input.length() > 0) && (input.length() <= 4)) {
                                        major = input;
                                    }
                                    else {
                                        System.out.println("Invalid input");
                                    }
                                }
                                internalExit = false;

                                System.out.println("Type the minimum awarded amount.");
                                while(!internalExit) {
                                    System.out.println("Awarded Amount: ");
                                    double input;
                                    input = scnr.nextDouble();
                                    scnr.nextLine();
                                    if (input > 0.0) {
                                        awardAmount = input;
                                    }
                                    else {
                                        System.out.println("Invalid input");
                                    }
                                }
                                internalExit = false;
                                
                                
                                scholarships = manager.findScholarshipSpecificCriteria(gpa, major, awardAmount);
                                if (scholarships.size() == 0) {
                                    System.out.println("No applicable scholarships.");
                                }
                                else {
                                    for (int i = 0; i < scholarships.size(); ++i) {
                                        System.out.println((i + 1) + ") " + manager.scholarshipToString(scholarships.get(i).getID()));
                                    }
                                }
                                internalExit = true;
                            }
                            else {
                                System.out.println("Invalid action");
                            }
                        }
                        internalExit = true;
                    }
                    else if (action == 0) {
                        loggedIn = false;
                    }
                    else {
                        System.out.println("Invalid action");
                    }
                }
            }
            System.out.println("Bye!");
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
