enum ApplicationStatus {
  APPLIED,
  REAPPLIED,
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

  public int getStatus() {
    switch (status) {
      case APPLIED:
        return 1;
      case REAPPLIED:
        return 2;
      case DENIED:
        return 3;
      case NO_LONGER_IN_CONSIDERATION:
        return 4;
      case REVIEWED:
        return 5;
      case APPROVED:
        return 6;
      default:
        return 0;
    }
  }

  public void setScore(int score) {
    this.score = score;
    ;
  }

  public void setStatus(int status) {
    switch (status) {
      case 0:
        this.status = ApplicationStatus.REAPPLIED;
        break;
      case 1:
        this.status = ApplicationStatus.DENIED;
        break;
      case 2:
        this.status = ApplicationStatus.NO_LONGER_IN_CONSIDERATION;
        break;
      case 3:
        this.status = ApplicationStatus.REVIEWED;
        break;
      case 4:
        this.status = ApplicationStatus.APPROVED;
        break;
    }
  }

  public void printStatus() {
    System.out.printf("ApplicantID: %d, ScholarshipID: %d, Application_Status: ", applicantID, scholarshipID);
    System.out.println(status);
  }
}
