import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Day2 {

    public static void main(String args[]) {
        ArrayList<String> idList = new ArrayList<String>();
        idList = readIDs("day2input.txt");

        int day2Part1 = Day2.getPartOneResult(idList);
        String day2Part2 = Day2.getPartTwoResult(idList);


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

    public static int getPartOneResult(ArrayList<String> idList) {

        int checksum = 0;
        int numIdsWithLetterTwoTimes = 0;
        int numIdsWithLetterThreeTimes = 0;

        for(String line: idList) {
            HashMap<Character, Integer> charDictionary = new HashMap<Character, Integer>();

            for(int i = 0; i < line.length(); i++) {
                Character c = line.charAt(i);

                if(!charDictionary.containsKey(c))
                    charDictionary.put(c, 1);
                else
                    charDictionary.put(c, charDictionary.get(c) + 1);
            }

            if(charDictionary.containsValue(2))
                numIdsWithLetterTwoTimes++;

            if(charDictionary.containsValue(3))
                numIdsWithLetterThreeTimes++;
        }

        checksum = numIdsWithLetterTwoTimes * numIdsWithLetterThreeTimes;

        return checksum;
    }

    public static String getPartTwoResult(ArrayList<String> idList) {

        //ArrayList<String> idList2 = (ArrayList<String>) idList.clone();
        int diff = 0;
        int position = 0;
        String diffLine = "";

        for(String line: idList) {

            for(String line2: idList) {

                if(line.equals(line2))
                    continue;

                int i = 0;
                diff = 0;

                for(i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != line2.charAt(i)) {
                        diff++;
                        position = i;
                    }
                }

                if(diff == 1) {
                    diffLine = line;
                    break;
                }
            }

            if(diff == 1)
                break;
        }

        String commonLetters = "";
        for(int i = 0; i < diffLine.length(); i++) {
            if (i == position)
                continue;
            else
                commonLetters += diffLine.charAt(i);
        }

        System.out.println(commonLetters);

        return commonLetters;
    }
}


