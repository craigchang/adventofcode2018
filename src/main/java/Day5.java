import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Day5 {

    public static void main(String args[]) {
        ArrayList<String> stringList = new ArrayList<String>();
        stringList = readPolymer("day5input.txt");

//        ArrayList<String> resultList = getNewPolymer(stringList);
//
//        String output = "";
//        for(String s: resultList)
//            output += s;
//
//        System.out.println(output);

        int shortestPolymer = getShortestPolymer(stringList);
        System.out.println("Shortest Polymer: " + shortestPolymer);
    }

    public static int getShortestPolymer(ArrayList<String> charList) {
        ArrayList<String> newCharList = new ArrayList<String>();
        HashMap<String, ArrayList<String>> listOfPolymers = new HashMap<String, ArrayList<String>>();

        boolean unitFound = false;

        String[] listOfUnits = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

        for(int j = 0; j < listOfUnits.length; j++) {
            for (int i = 0; i < charList.size(); i++) {
                String current = charList.get(i);
                unitFound = findUnits(current, listOfUnits[j]);
                if (unitFound)
                    continue;
                else
                    newCharList.add(current);
            }
            listOfPolymers.put(listOfUnits[j], (ArrayList<String>) newCharList.clone());
            newCharList.clear();
        }


        HashMap<String, Integer> listOfRemainingPolymers = new HashMap<String, Integer>();

        Iterator itr = listOfPolymers.entrySet().iterator();
        while(itr.hasNext()) {
            Map.Entry pair = (Map.Entry) itr.next();
            String key = (String) pair.getKey();
            ArrayList<String> newPolymer = getNewPolymer( (ArrayList<String>) pair.getValue());

            listOfRemainingPolymers.put(key, newPolymer.size());
        }

        int shortestPolymer = listOfRemainingPolymers.get("a");
        for(String key: listOfPolymers.keySet()) {
            if (shortestPolymer > listOfRemainingPolymers.get(key))
                shortestPolymer = listOfRemainingPolymers.get(key);
        }

        return shortestPolymer;
    }

    public static boolean findUnits(String str, String unit) {
        return str.equals(unit.toLowerCase()) || str.equals(unit.toUpperCase());
    }

    public static ArrayList<String> getNewPolymer(ArrayList<String> charList) {
        ArrayList<String> newCharList = new ArrayList<String>();

        boolean sequenceFound = false;
        boolean sequenceDeleted = false;

        do {
            sequenceDeleted = false;
            for (int i = 0; i < charList.size(); i++) {
                String current = charList.get(i);
                String next = (i + 1) < charList.size() ? charList.get(i + 1) : " ";

                if (sequenceDeleted) {
                    newCharList.add(current);
                    continue;
                }

                sequenceFound = findSequence(current, next);
                if (sequenceFound) {
                    i += 1;
                    sequenceDeleted = true;
                    continue;
                } else {
                    newCharList.add(current);
                }
            }

            charList = (ArrayList<String>) newCharList.clone() ;
            newCharList.clear();

            if(!sequenceDeleted) break;

        } while(sequenceDeleted);

        return charList;
    }

    public static boolean findSequence(String str1, String str2) {
        return str1.toLowerCase().equals(str2.toLowerCase()) &&
                (Character.isUpperCase(str1.charAt(0)) && Character.isLowerCase(str2.charAt(0)) ||
                        Character.isLowerCase(str1.charAt(0)) && Character.isUpperCase(str2.charAt(0)));
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
