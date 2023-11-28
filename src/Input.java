import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    private ArrayList<String[]> readCSV(String filename){
        ArrayList<String[]> in = new ArrayList<String[]>();
        String csvFile = "src/" + filename;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){

            while((line = br.readLine()) != null){
                String[] splitLine = line.split(",");
                in.add(splitLine);
            }
        }
        catch(IOException error){
            error.printStackTrace();
        }

        return in;
    }

    public ArrayList<Scholarship> readScholarshipCSV(String filename){
        ArrayList<String[]> input = readCSV(filename);
        ArrayList<Scholarship> result = new ArrayList<Scholarship>();

        for(String[] arr : input){
            String Name = "";
            double minGPA = 0.0;
            String department = "";
            String[] acceptedMajors = new String[1];
            int day = 0, month = 0, year = 0;
            float awardAmount = 0;
            int ID = 0;


            for(int i = 0; i < arr.length; i++){
                switch (i) {
                    case 0:
                        Name = arr[i];
                    break;
                    case 1:
                        minGPA = Double.parseDouble(arr[i]);
                    break;
                    case 2:
                        department = arr[i];
                    break;
                    case 3:
                        acceptedMajors = arr[i].split("|");
                    break;
                    case 4:
                        String[] date = arr[i].split("-");
                        day = Integer.parseInt(date[0]);
                        month = Integer.parseInt(date[1]);
                        year = Integer.parseInt(date[2]);
                    break;
                    case 5:
                        awardAmount = Float.parseFloat(arr[i]);
                    break;
                    case 6:
                        ID = Integer.parseInt(arr[i]);
                    break;
                    default:
                        break;
                }
            }

            Scholarship curr = new Scholarship(Name, ID, minGPA, department, acceptedMajors, day, month, year, awardAmount);
            result.add(curr);
        }

        return result;
    }

    public ArrayList<Applicant> readApplicantsCSV(String filename){
        ArrayList<String[]> input = readCSV(filename);
        ArrayList<Applicant> result = new ArrayList<Applicant>();

        for(String[] arr : input){
            String Name = "";
            double GPA = 0.0;
            String major = "";
            int year = 0;
            int ID = 0;


            for(int i = 0; i < arr.length; i++){
                switch (i) {
                    case 0:
                        Name = arr[i];
                    break;
                    case 1:
                        ID = Integer.parseInt(arr[i]);
                    break;
                    case 2:
                        GPA = Double.parseDouble(arr[i]);
                    break;
                    case 3:
                        major = arr[i];
                    break;
                    case 4:
                        year = Integer.parseInt(arr[i]);
                    break;
                    default:
                        break;
                }
            }

            Applicant curr = new Applicant(Name, ID, GPA, major, year);
            result.add(curr);
        }

        return result;

    }

    public ArrayList<Application> readApplicationsCSV(String filename){
        ArrayList<String[]> input = readCSV(filename);
        ArrayList<Application> result = new ArrayList<Application>();

        for(String[] arr : input){
            int applicantID = 0;
            int ID = 0;
            int scholarshipID = 0;
            String letter = "";

            for(int i = 0; i < arr.length; i++){
                switch (i) {
                    case 0:
                        applicantID = Integer.parseInt(arr[i]);
                    break;
                    case 1:
                        ID = Integer.parseInt(arr[i]);
                    break;
                    case 2:
                        scholarshipID = Integer.parseInt(arr[i]);
                    break;
                    case 3:
                        letter =arr[i];
                    break;
                    default:
                        break;
                }
            }

            Application curr = new Application(applicantID, ID, scholarshipID, letter);
            result.add(curr);
        }

        return result;

    }

}
