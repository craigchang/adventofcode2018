import java.io.*;
import java.util.ArrayList;

public class Day5 {

    public static void main(String args[]) {
        ArrayList<String> stringList = new ArrayList<String>();
        stringList = readPolymer("day5input.txt");

        ArrayList<String> resultList = getNewPolymer(stringList);

        String output = "";
        for(String s: resultList)
            output += s;

        System.out.println(output);
    }

    public static ArrayList<String> getNewPolymer(ArrayList<String> charList) {

        ArrayList<String> newCharList = new ArrayList<String>();


        int start = 0;
        int end = 0;
        boolean sequenceFound = false;
        boolean sequenceDeleted = true;


        while(sequenceDeleted) {

            start = 0;
            end = 0;
            sequenceFound = false;
            sequenceDeleted = false;

            for (int i = 0; i < charList.size(); i++) {
                String current = charList.get(i);
                String next = i < (charList.size() - 1) ? charList.get(i + 1) : " ";

                if (sequenceDeleted) {
                    newCharList.add(current);
                    continue;
                }

                if (next.toLowerCase().equals(current.toLowerCase()) && !sequenceFound &&
                        (Character.isUpperCase(current.charAt(0)) && Character.isLowerCase(next.charAt(0)) ||
                        Character.isLowerCase(current.charAt(0)) && Character.isUpperCase(next.charAt(0)))
                    ) {
                        // if current is upper and next is lower or if current is lower and next is upper
                        sequenceFound = true;
                        start = i;
                } else {
                    end = i;
                    if (end - start != 0 && sequenceFound) {
                        i = end + 1;
                        sequenceFound = false;
                        sequenceDeleted = true;
                        current = charList.get(i);
                    }
                    newCharList.add(current);
                }
            }

            charList = (ArrayList<String>) newCharList.clone() ;
            newCharList.clear();

            if(sequenceDeleted)
                continue;
            else
                break;
        }

        return charList;
    }

    public static ArrayList<String> readPolymer(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<String> charList = new ArrayList<String>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++)
                    charList.add( Character.toString( line.charAt(i) ) );
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return charList;
    }
}
