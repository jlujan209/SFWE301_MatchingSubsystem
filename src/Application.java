enum ApplicationStatus {
  APPLIED,
  DENIED,
  NO_LONGER_IN_CONSIDERATION,
  REVIEWED,
  APPROVED
}

public class Application {
    private String letter;
    private ApplicationStatus status;
    private int ID;
    private int applicantID;
    private int scholarshipID;
    private int score;
    
    public Application(int applicantID, int ID, int scholarshipID, String letter) {
        this.applicantID = applicantID;
        this.ID = ID;
        this.score = 1;
        this.letter = letter;
        this.scholarshipID = scholarshipID;
        this.status = ApplicationStatus.APPLIED;
    }

    public int getID() {
      return ID;
    }

    public int getScholarshipID() {
      return scholarshipID;
    }

    public int getApplicantID() {
      return applicantID;
    }

    public String getLetter() {
      return letter;
    }

    public int getScore() {
      return score;
    }
    public void setScore(int score) {
      this.score = score;;
    }
}
