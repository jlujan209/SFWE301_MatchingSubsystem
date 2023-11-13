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
    private String applicationID;
    private String applicantID;
    
    public Application(String applicantID, String applicationID, String letter) {
        this.applicantID = applicantID;
        this.applicationID = applicationID;
        this.letter = letter;
        this.status = ApplicationStatus.APPLIED;
    }
}
