import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        try (Scanner scnr = new Scanner(System.in)) {

            System.out.println("Enter userName => ");
            String userName = scnr.nextLine();
            System.out.println("Enter password => ");
            String password = scnr.nextLine();

            if ("Hsinwei".equals(userName) && "abc123".equals(password)) {
                System.out.printf("User is Successfully loggined...\n\n");
                System.out.println("Enter search criteria minimum accepted GPA: ");
                float gpa = scnr.nextFloat();
                System.out.println("Enter search criteria scholarship department(s): ");
                String department = scnr.next();
                System.out.println("Enter search criteria selection date: month/date/year: ");
                String date = scnr.next();
                System.out.println("Enter search criteria rewarded amount: ");
                int amount = scnr.nextInt();
                System.out.printf("Search criteria: gpa: %.1f, departments: %s, date: %s, amount: %d$\n", gpa,
                        department,
                        date, amount);
            } else {
                System.out.println("Invalid userName or password...");
            }
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}
