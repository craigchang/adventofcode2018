import beans.Coordinate;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Day6 {
    public static void main(String[] args) {
        ArrayList<Coordinate> coordinatesList = readCoordinates("day6inputsample.txt");


    }

    

    public static ArrayList<Coordinate> readCoordinates(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<Coordinate> coordinatesList = new ArrayList<Coordinate>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(",");
                int x = Integer.parseInt(lineTokens[0].trim());
                int y = Integer.parseInt(lineTokens[1].trim());

                coordinatesList.add(new Coordinate(x,y));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return coordinatesList;

    }
}
