import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        Scholarships[] s = new Scholarships[6];
        s[0] = new Scholarships(1, "Outstading SFWE", 3.2f, "SFWE", 12, 13, 2023, 1000);
        s[1] = new Scholarships(2, "SFWE Academic distinction", 3.5f, "SFWE", 12, 23, 2023, 500);
        s[2] = new Scholarships(3, "SFWE Academic Excellence", 4.0f, "SFWE", 12, 23, 2023, 500);
        s[3] = new Scholarships(4, "Outstading ECE", 3.2f, "ECE", 12, 17, 2023, 3000);
        s[4] = new Scholarships(5, "Outstading SIE", 3.0f, "SIE", 12, 30, 2023, 1000);
        s[5] = new Scholarships(6, "SIE Academic distinction", 3.0f, "SIE", 12, 25, 2023, 5000);
        // s[0].printScholarship();
        Application[] application = new Application[6];
        application[0] = new Application(1, 345, 1, "Application Letter");
        application[1] = new Application(1, 456, 2, "Application Letter");
        application[1].setStatus(1);
        application[2] = new Application(1, 789, 3, "Application Letter");
        application[2].setStatus(2);
        application[3] = new Application(1, 138, 4, "Application Letter");
        application[3].setStatus(3);
        application[4] = new Application(1, 564, 5, "Application Letter");
        application[4].setStatus(4);
        application[5] = new Application(1, 761, 6, "Application Letter");

        try (Scanner scnr = new Scanner(System.in)) {

            boolean loggined = false;

            while (!loggined) {
                System.out.println("Enter userName => ");
                String userName = scnr.nextLine();
                System.out.println("Enter password => ");
                String password = scnr.nextLine();
                if ("Hsinwei".equals(userName) && "abc123".equals(password)) {
                    loggined = true;
                    System.out.printf("User is Successfully loggined...\n\n");
                    System.out.println(
                            "Choose user action: 1 = view all available scholarships according to desiring criteria, 2 = view application status ");
                    int action = scnr.nextInt();
                    if (action == 1) {
                        System.out.println("Enter search criteria minimum accepted GPA: ");
                        float gpa = scnr.nextFloat();
                        System.out.println("Enter search criteria scholarship department(s): ");
                        String department = scnr.next();
                        System.out.println("Enter search criteria selection date: month/date/year: ");
                        String date = scnr.next();
                        System.out.println("Enter search criteria rewarded amount: ");
                        int amount = scnr.nextInt();
                        System.out.printf(
                                "Scholarships according to desiring criteria: gpa: %.1f, departments: %s, date: %s, amount: %d$\n",
                                gpa,
                                department,
                                date, amount);
                        int month = Integer.parseInt(date.substring(0, 2));
                        int day = Integer.parseInt(date.substring(3, 5));
                        int year = Integer.parseInt(date.substring(6, 10));
                        // System.out.printf("%d/%d/%d\n", month, day, year);
                        for (int i = 0; i < s.length; i++) {
                            if (s[i].GPA >= gpa && s[i].department.equals(department)
                                    && (s[i].month > month || s[i].year > year
                                            || (s[i].month == month && s[i].year == year && s[i].date >= day))
                                    && s[i].amount >= amount) {
                                s[i].printScholarship();
                            }
                        }
                    } else if (action == 2) {
                        System.out.println("Viewing all application status:");
                        for (int i = 0; i < application.length; i++) {
                            application[i].printStatus();
                        }
                        System.out.printf("\n\n");
                        int deniedIndex = -1;
                        for (int i = 0; i < application.length; i++) {
                            if (application[i].getStatus() == ApplicationStatus.DENIED) {
                                deniedIndex = i;
                                application[i].printStatus();
                                System.out.println("Input 'e' for exit or 'y' for reevaluate denied application?");
                                break;
                            }
                        }
                        int exit = scnr.next().charAt(0);
                        if (exit == 'e')
                            break;
                        else if (exit == 'y') {
                            application[deniedIndex].setStatus(0);
                            application[deniedIndex].printStatus();
                            System.out.println("Denied application is under reevaluation. Input 'e' for exit?");
                        }

                        exit = scnr.next().charAt(0);
                        if (exit == 'e')
                            break;

                    } else {
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
