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
    private String ID;
    private String applicantID;
    private String scholarshipID;
    
    public Application(String applicantID, String ID, String scholarshipID, String letter) {
        this.applicantID = applicantID;
        this.ID = ID;
        this.letter = letter;
        this.scholarshipID = scholarshipID;
        this.status = ApplicationStatus.APPLIED;
    }

    public String getID() {
      return ID;
    }

    public String getScholarshipID() {
      return scholarshipID;
    }

        public String getApplicantID() {
      return applicantID;
    }
}
