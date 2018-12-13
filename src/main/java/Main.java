import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Day 1 - Chronical Calibration
//        ArrayList<Integer> frequencyChangesList = new ArrayList<Integer>();
//        frequencyChangesList = InputFileHandler.readFrequencyChanges("input.txt");
//
//        int day1Part1 = Day1.getPartOneResult(frequencyChangesList);
//        int day1Part2 = Day1.getPartTwoResult(frequencyChangesList);
//
//        System.out.println("Resulting Frequency: " + day1Part1);
//        System.out.println("First Frequency device reaches twice: " + day1Part2);

        // Day 2 - Inventory Management System
//        ArrayList<String> idList = new ArrayList<String>();
//        idList = InputFileHandler.readIDs("day2input.txt");
//
//        int day2Part1 = Day2.getPartOneResult(idList);
//        String day2Part2 = Day2.getPartTwoResult(idList);

        // Day 3 -
        ArrayList<Claim> claimsList = new ArrayList<Claim>();
        claimsList = Day3.readClaims("day3input.txt");
        int totalSquareFoot = Day3.getPartOneResult(claimsList);
        int claimId = Day3.getPartTwoResult(claimsList);

        System.out.println("Total Square Ft: " + totalSquareFoot);
        System.out.println("Claim Id: " + claimId);




    }
}
