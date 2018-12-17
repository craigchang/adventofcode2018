import beans.Coordinate;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {
    public static void main(String[] args) {
        ArrayList<Coordinate> coordinatesList = readCoordinates("day6input.txt");

        int largestX = 0;
        int largestY = 0;

        for(Coordinate coord: coordinatesList) {
            if (coord.getX() > largestX)
                largestX = coord.getX();
            if (coord.getY() > largestY)
                largestY = coord.getY();

            System.out.println(coord.getId() + ": " + coord.getX() + "," + coord.getY());
        }

        largestX+=2;
        largestY+=2;

        String[][] map = new String[largestX][largestY];

        int largestArea = getPartOneResult(map, coordinatesList);
        System.out.println(largestArea);

        int numLocations = getPartTwoResult(map, coordinatesList);
        System.out.println(numLocations);
    }

    public static int getPartOneResult(String[][] map, ArrayList<Coordinate> coordinatesList) {
        int largestX = map.length;
        int largestY = map[0].length;

        for(Coordinate coord: coordinatesList) {
            map[coord.getX()][coord.getY()] = "*";
        }

        for(int y = 0; y < largestY; y++) {
            for(int x = 0; x < largestX; x++) {
                Coordinate shortestCoordinate = null;
                int shortestDistance = Integer.MAX_VALUE;
                int[] distanceArray = new int[coordinatesList.size()];

                int k = 0;
                for(Coordinate coord: coordinatesList) {
                    int distance = calculateDistance(x, coord.getX(), y, coord.getY());

                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        shortestCoordinate = coord;
                    }

                    distanceArray[k] = distance;
                    k++;
                }

                int numShortestDistances = 0;
                for(k = 0; k < distanceArray.length; k++) {
                    if (distanceArray[k] == shortestDistance)
                        numShortestDistances++;
                }

                if (numShortestDistances > 1) {
                    map[x][y] = ".";
                } else {
                    map[x][y] = shortestCoordinate.getId() + "";
                }
            }
        }


        int largestArea = Integer.MIN_VALUE;
        loopCoordinates:
        for(Coordinate coord: coordinatesList) {

            int totalArea = 0;
            boolean isInfinite = false;

            loopMap:
            for(int x = 0; x < largestX; x++) {
                for(int y = 0; y < largestY; y++) {
                    String coordId = coord.getId() + "";

                    if(((x == 0 || y == 0) && (map[x][y].equals(coordId))) ||
                            ((x == (largestX-1) || (y == (largestY-1))) && (map[x][y].equals(coordId)))
                    ) {
                        isInfinite = true;
                        break loopMap;
                    }

                    if ((coordId).equals(map[x][y]) || ((coord.getX() == x) && (coord.getY() == y))) {
                        totalArea++;
                    }
                }
            }

            if(isInfinite)
                continue;

            if(largestArea < totalArea) {
                largestArea = totalArea;
            }

        }
        return largestArea;
    }

    public static int getPartTwoResult(String[][] map, ArrayList<Coordinate> coordinatesList) {
        int numLocations = 0;
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[0].length; y++) {

                int totalDistance = 0;
                for(Coordinate coord: coordinatesList) {
                    totalDistance += calculateDistance(x, coord.getX(), y, coord.getY());
                }

                if (totalDistance < 10000)
                    numLocations++;
            }
        }

        return numLocations;
    }

    public static int calculateDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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
        int i = 0;

        try {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split(",");
                int x = Integer.parseInt(lineTokens[0].trim());
                int y = Integer.parseInt(lineTokens[1].trim());

                coordinatesList.add(new Coordinate(i,x,y));
                i++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return coordinatesList;

    }
}
