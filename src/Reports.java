import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Reports {
    private List<String> RankedApplicants;
    
    public Reports(List<String> applicants){
        this.RankedApplicants = new ArrayList<String>();
        for(String applicant : applicants){
            this.RankedApplicants.add(applicant);
        }
    }

    public void addApplicant(String Applicant){
        this.RankedApplicants.add(Applicant);
    }

    public void toCSV(String ScholarshipName){
        String csvFile = ScholarshipName + ".csv";
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))){
            for(int i = 0; i < RankedApplicants.size(); i++){
                bw.write(RankedApplicants.get(i) + "\n");
            }
        }
        catch(IOException error){
            error.printStackTrace();
        }
    }
}
