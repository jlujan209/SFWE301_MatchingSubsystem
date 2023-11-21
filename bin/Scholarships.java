public class Scholarships {
    public String scholarshipName;
    public float GPA;
    public String department;
    public int month;
    public int date;
    public int year;
    public int amount;

    Scholarships(String scholarshipName, float GPA, String department, int month, int date, int year, int amount) {
        this.scholarshipName = scholarshipName;
        this.GPA = GPA;
        this.department = department;
        this.month = month;
        this.date = date;
        this.year = year;
        this.amount = amount;
    }

    public void printScholarship() {
        System.out.printf("Name: %s, GPA: %.1f, Department: %s, Due date: %d/%d/%d, Amount: %d$ \n", scholarshipName,
                GPA, department, month, date, year, amount);
    }
}
