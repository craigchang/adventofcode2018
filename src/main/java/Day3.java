import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day3 {
    public static ArrayList<Claim> readClaims(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<Claim> claimsList = new ArrayList<Claim>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {

                String[] lineTokens = line.split("[@:]");
                String[] idTokens = lineTokens[0].split("#");
                String[] positionTokens = lineTokens[1].split(",");
                String[] dimensionTokens = lineTokens[2].split("x");

                Claim claim = new Claim();
                claim.setId(Integer.parseInt(idTokens[1].trim()));
                claim.setX(Integer.parseInt(positionTokens[0].trim()));
                claim.setY(Integer.parseInt(positionTokens[1].trim()));
                claim.setLength(Integer.parseInt(dimensionTokens[0].trim()));
                claim.setHeight(Integer.parseInt(dimensionTokens[1].trim()));

                claimsList.add(claim);

                System.out.println(lineTokens[0] + " " + lineTokens[1] + " " + lineTokens[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return claimsList;
    }

    public static int getPartOneResult(ArrayList<Claim> claimsList) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map = getMap(claimsList);

        int totalSquareFt = 0;
        for(int value: map.values()) {
            if(value > 1) {
                totalSquareFt++;
            }
        }

        return totalSquareFt;
    }

    public static int getPartTwoResult(ArrayList<Claim> claimsList) {
        HashMap<String, Integer> map = getMap(claimsList);
        int claimWithNoOverlap = 0;

        for(Claim claim: claimsList) {
            int numOverlap = 0;
            for(int i = 0; i < claim.getLength(); i++) {
                for (int j = 0; j < claim.getHeight(); j++) {
                    String key = getKey(claim, i, j);

                    if(map.get(key) > 1) {
                        numOverlap++;
                    }
                }
            }
            if(numOverlap == 0) {
                claimWithNoOverlap = claim.getId();
                break;
            }
        }

        return claimWithNoOverlap;
    }

    private static HashMap<String, Integer> getMap(ArrayList<Claim> claimsList) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(Claim claim: claimsList) {
            for(int i = 0; i < claim.getLength(); i++) {
                for(int j = 0; j < claim.getHeight(); j++) {
                    String key = getKey(claim, i, j);
                    if(!map.containsKey(key))
                        map.put(key, 1);
                    else
                        map.put(key, (map.get(key) + 1));
                }
            }
        }
        return map;
    }

    private static String getKey(Claim claim, int addX, int addY) {
        return (claim.getX() + addX) + "," + (claim.getY() + addY);
    }
}
