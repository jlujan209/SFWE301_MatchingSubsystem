public class Scholarships {
    public int ID;
    public String scholarshipName;
    public float GPA;
    public String department;
    public int month;
    public int date;
    public int year;
    public int amount;

    Scholarships(int ID, String scholarshipName, float GPA, String department, int month, int date, int year, int amount) {
        this.ID = ID;
        this.scholarshipName = scholarshipName;
        this.GPA = GPA;
        this.department = department;
        this.month = month;
        this.date = date;
        this.year = year;
        this.amount = amount;
    }

    public void printScholarship() {
        System.out.printf("Scholarship ID:%d, Name: %s, GPA: %.1f, Department: %s, Due date: %d/%d/%d, Amount: %d$ \n", ID, scholarshipName,
                GPA, department, month, date, year, amount);
    }
}
