import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InputFileHandler {

    public static ArrayList<Integer> readFrequencyChanges(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<Integer> frequencyChangesList = new ArrayList<Integer>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {
                frequencyChangesList.add(Integer.parseInt(line));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return frequencyChangesList;
    }

    public static ArrayList<String> readIDs(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<String> idList = new ArrayList<String>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {
                idList.add(line);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return idList;

    }

}
