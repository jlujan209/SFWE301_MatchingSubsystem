import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        Scholarships[] s = new Scholarships[6];
        s[0] = new Scholarships("Outstading SFWE", 3.2f, "SFWE", 12, 13, 2023, 1000);
        s[1] = new Scholarships("SFWE Academic distinction", 3.5f, "SFWE", 12, 23, 2023, 500);
        s[2] = new Scholarships("SFWE Academic Excellence", 4.0f, "SFWE", 12, 23, 2023, 500);
        s[3] = new Scholarships("Outstading ECE", 3.2f, "ECE", 12, 17, 2023, 3000);
        s[4] = new Scholarships("Outstading SIE", 3.0f, "SIE", 12, 30, 2023, 1000);
        s[5] = new Scholarships("SIE Academic distinction", 3.0f, "SIE", 12, 25, 2023, 5000);
        // s[0].printScholarship();

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

                    } else {

                    }
                } else {
                    System.out.println("Please login again");
                }
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
