import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Day4 {
    public static void main(String args[]) {
        ArrayList<Record> recordsList = readRecords("day4input.txt");
        int mostMinutes = findGuardAsleepMostMinutes(recordsList);
    }

    public static int findGuardAsleepMostMinutes(ArrayList<Record> recordsList) {
        HashMap<Integer, Integer> map = new  HashMap<Integer, Integer>();
        HashMap<Integer, Integer[]> schedule = new HashMap<Integer, Integer[]>();
        Calendar cal = Calendar.getInstance();

        int currentGuardId = 0;
        Date currentDateTimeFallsAsleep = new Date();

        for(Record record: recordsList){

            if(record.getAction().equals("begins shift")) {
                if(currentGuardId != record.getGuardId())
                    currentGuardId = record.getGuardId();

                if(!map.containsKey(record.getGuardId())) {
                    map.put(record.getGuardId(), 0);
                    schedule.put(record.getGuardId(), getArray());
                }
            }

            if(record.getAction().equals("falls asleep")) {
                currentDateTimeFallsAsleep = record.getDate();
            }

            if(record.getAction().equals("wakes up")) {
                int minutes = (int)( record.getDate().getTime() - currentDateTimeFallsAsleep.getTime() ) / (1000 * 60) % 60;

                cal.setTime(currentDateTimeFallsAsleep);
                int startMinute = cal.get(Calendar.MINUTE);

                cal.setTime(record.getDate());
                int endMinute = cal.get(Calendar.MINUTE);

                for(int i = startMinute; i < endMinute; i++)
                    (schedule.get(currentGuardId))[i]++;


                map.put(currentGuardId, map.get(currentGuardId) + minutes);
            }
        }

        return 0;
    }

    public static ArrayList<Record> readRecords(String fileName) {
        File file = new File(fileName);
        FileReader fr = null;
        ArrayList<Record> recordsList = new ArrayList<Record>();

        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        String line;

        try {
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split("]");

                String dateStr = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date date = dateFormat.parse(dateStr);

                int guardId = 0;
                String action;
                if(line.indexOf("begins shift") > 0) {
                    String guardInfo = line.substring(line.indexOf("#") + 1);
                    guardId = Integer.parseInt(guardInfo.substring(0, guardInfo.indexOf(" ")));
                    action = guardInfo.substring(guardInfo.indexOf(" ")).trim();
                } else if(line.indexOf("falls asleep") > 0) {
                    action = "falls asleep";
                } else
                    action = "wakes up";

                Record record = new Record();
                record.setDate(date);
                record.setGuardId(guardId);
                record.setAction(action);


                recordsList.add(record);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Collections.sort(recordsList);

        for(Record record: recordsList) {
            System.out.println(record.getDate().toString() + " " + record.getGuardId() + " " + record.getAction());
        }

        return recordsList;
    }

    private static Integer[] getArray() {
        return new Integer[]{
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0
        };
    }


}
