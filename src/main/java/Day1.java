import java.util.ArrayList;
import java.util.HashSet;

public class Day1 {

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
