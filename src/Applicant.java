public class Applicant {
    private String name;
    protected String ID;
    protected float GPA;
    protected char[] major;
    protected int year;

    public Applicant(String name, String ID, float GPA, char[] major, int year) {
        this.name = name;
        this.ID = ID;
        this.GPA = GPA;
        this.major = major;
        this.year = year;
    }
}
