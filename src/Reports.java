import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Reports {
    private ArrayList<String> stringsForCSV;
    
    public Reports(ArrayList<String> csvStrings){
        this.stringsForCSV = csvStrings;
    }

    public void toCSV(String ScholarshipName){
        String csvFile = ScholarshipName + ".csv";
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))){
            for(String x : stringsForCSV){
                bw.write(x + "\n");
            }
        }
        catch(IOException error){
            error.printStackTrace();
        }
    }

}
