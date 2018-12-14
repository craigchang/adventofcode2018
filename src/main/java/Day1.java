import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Day1 {

    public static void main(String args[]) {
        ArrayList<Integer> frequencyChangesList = new ArrayList<Integer>();
        frequencyChangesList = readFrequencyChanges("input.txt");

        int day1Part1 = Day1.getPartOneResult(frequencyChangesList);
        int day1Part2 = Day1.getPartTwoResult(frequencyChangesList);

        System.out.println("Resulting Frequency: " + day1Part1);
        System.out.println("First Frequency device reaches twice: " + day1Part2);
    }

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

    public static int getPartOneResult(ArrayList<Integer> frequencyChangesList) {
        int sum = 0;
        for (int frequencyChange : frequencyChangesList) {
            sum += frequencyChange;
        }
        return sum;
    }


    public static int getPartTwoResult(ArrayList<Integer> frequencyChangesList) {
        HashSet<Integer> resultingFrequencyList = new HashSet<Integer>();
        int firstFrequencyTwice = 0;
        boolean firstFrequencyTwiceFound = false;
        int sum = 0;

        while(!firstFrequencyTwiceFound) {

            resultingFrequencyList.add(0);

            for (int frequencyChange : frequencyChangesList) {
                sum += frequencyChange;

                for (int resultingFrequency : resultingFrequencyList) {
                    if (sum == resultingFrequency) {
                        firstFrequencyTwice = sum;
                        firstFrequencyTwiceFound = true;
                        break;
                    }
                }

                resultingFrequencyList.add(sum);

                if (firstFrequencyTwiceFound)
                    break;
            }

        }

        return firstFrequencyTwice;
    }
}
