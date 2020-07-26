import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class ScoreBoard extends Application {
    private ArrayList<ArrayList<String>> dataHolder = new ArrayList<>();
    private HashMap<Integer,Float> fiveHundredRecord = new HashMap<>();
    private HashMap<Integer,Float> thousandRecord = new HashMap<>();
    private HashMap<Integer,Float> thousandFiveHundredRecord = new HashMap<>();
    private HashMap<Integer,Float> twoThousandRecord = new HashMap<>();
    private int overallPlayer;
    private int segmentWinner;
    private float segmentWinnerTime;

    private void readFile() throws IOException {
        String read_Line;
        try (BufferedReader reader = new BufferedReader(new FileReader("birc.txt"))) {
            for (int i = 0; i <= 20; i++) {
                read_Line = reader.readLine();
                String[] lineSplit;
                lineSplit = read_Line.split(";", 7);


                String name = lineSplit[0];
                String club = lineSplit[1];
                String age = lineSplit[2];
                String d1 = lineSplit[3];
                String d2 = lineSplit[4];
                String d3 = lineSplit[5];
                String d4 = lineSplit[6];

                //Creating temp Arraylist to save each record line
                ArrayList<String> temp = new ArrayList<>();
                //Adding data to the temp Arraylist
                temp.add(name);
                temp.add(club);
                temp.add(age);
                temp.add(d1);
                temp.add(d2);
                temp.add(d3);
                temp.add(d4);

                dataHolder.add(temp);
            }
        }
    }

    private void displayRecords() throws IOException {
        String read_Line;
        try (BufferedReader reader = new BufferedReader(new FileReader("birc.txt"))){
            System.out.println();
            for (int i=20; i>1; i--) {
                read_Line = reader.readLine();
                System.out.println(read_Line);
            }
        }
    }

    private void overallWinner(){
        //Getting the 2000 meter time record from each player and store in a Hashmap
        for(int c = 1; c<=20; c++){
            //Converting all time records to the seconds
            twoThousandRecord.put(c,Float.valueOf(dataHolder.get(c).get(6).substring(0,2)) * 60 +Float.valueOf(dataHolder.get(c).get(6).substring(3,7)));
        }

        //adding records in seconds to a array for bubble sort
        Float[] records = new Float[20];
        for(int h = 0; h<20; h++){
            records[h] = twoThousandRecord.get(h+1);
        }

        //Sorting to find the minimum time duration using bubble sort
        Float temp;
        for (int i = 0; i < 20; i++) {
            for (int j = i + 1; j < 20; j++) {
                if (records[i] > records[j]) {
                    temp = records[i];
                    records[i] = records[j];
                    records[j] = temp;
                }
            }
        }

        //Getting the key value of the minimum time duration
        for(int f = 1; f<=20; f++){
            if(twoThousandRecord.get(f).equals(records[0])){
                System.out.println("\n***************************************Overall Winner***************************************");
                System.out.println(dataHolder.get(0).get(0)+" "+dataHolder.get(0).get(1)+" "+dataHolder.get(0).get(2)+" "+dataHolder.get(0).get(3)+" "
                        +dataHolder.get(0).get(4)+" "+dataHolder.get(0).get(5)+" "+dataHolder.get(0).get(6));
                System.out.println(dataHolder.get(f).get(0)+" "+dataHolder.get(f).get(1)+" "+dataHolder.get(f).get(2)+" "+dataHolder.get(f).get(3)+" "
                        +dataHolder.get(f).get(4)+" "+dataHolder.get(f).get(5)+" "+dataHolder.get(f).get(6));
                System.out.print("\n********************************************************************************************");
                overallPlayer = f;
                break;
            }
        }
    }

    private void segmentWinner() {
        //Getting all the times in seconds in each intermediate stop

        //Getting the 500 meter time record from each player and store in a Hashmap=======================================================================
        for (int c = 1; c <= 20; c++) {
            //Converting all time records to the seconds
            fiveHundredRecord.put(c, Float.valueOf(dataHolder.get(c).get(3).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(3).substring(3, 7)));
        }
        //adding 500m records in seconds to a array for sort
        Float[] fiveHundredSort = new Float[20];
        for (int h = 0; h < 20; h++) {
            fiveHundredSort[h] = fiveHundredRecord.get(h + 1);
        }
        Arrays.sort(fiveHundredSort);



        //Getting the 1000 meter time record from each player and store in a Hashmap======================================================================
        for (int c = 1; c <= 20; c++) {
            //Converting all time records to the seconds
            thousandRecord.put(c, (Float.valueOf(dataHolder.get(c).get(4).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(4).substring(3, 7))) -(Float.valueOf(dataHolder.get(c).get(3).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(3).substring(3, 7))));
        }
        //adding 1500m records in seconds to a array for sort
        Float[] thousandSort = new Float[20];
        for (int h = 0; h < 20; h++) {
            thousandSort[h] = thousandRecord.get(h + 1);
        }
        Arrays.sort(thousandSort);


        //Getting the 1500 meter time record from each player and store in a Hashmap=================================================================================
        for (int c = 1; c <= 20; c++) {
            //Converting all time records to the seconds
            thousandFiveHundredRecord.put(c, (Float.valueOf(dataHolder.get(c).get(5).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(5).substring(3, 7))) - (Float.valueOf(dataHolder.get(c).get(4).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(4).substring(3, 7))));
        }
        //adding 1500m records in seconds to a array for sort
        Float[] thousandFiveHundredSort = new Float[20];
        for (int h = 0; h < 20; h++) {
            thousandFiveHundredSort[h] = thousandFiveHundredRecord.get(h + 1);
        }
        Arrays.sort(thousandFiveHundredSort);


        //Getting the 2000 meter time record from each player and store in a Hashmap===============================================================================
        for (int c = 1; c <= 20; c++) {
            //Converting all time records to the seconds
            twoThousandRecord.put(c, (Float.valueOf(dataHolder.get(c).get(6).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(6).substring(3, 7))) - (Float.valueOf(dataHolder.get(c).get(5).substring(0, 2)) * 60 + Float.valueOf(dataHolder.get(c).get(5).substring(3, 7))));
        }
        //adding 2000m records in seconds to a array for sort
        Float[] twoThousandSort = new Float[20];
        for (int h = 0; h < 20; h++) {
            twoThousandSort[h] = twoThousandRecord.get(h + 1);
        }
        Arrays.sort(twoThousandSort);

        //Finding the best time record for 500m=========================================================================
        Float[] finalSort = new Float[4];
        finalSort[0] = fiveHundredSort[0];
        finalSort[1] = thousandSort[0];
        finalSort[2] = thousandFiveHundredSort[0];
        finalSort[3] = twoThousandSort[0];
        Arrays.sort(finalSort);
        segmentWinnerTime = finalSort[0];

        //Finding the player of the minimum time
        if(finalSort[0]==fiveHundredSort[0]){
            for(int e = 1; e<20; e++){
                if (fiveHundredRecord.get(e).equals(finalSort[0])){
                    System.out.println("\n***************************************Fastest Segment Winner***************************************");
                    System.out.println("Name \t\t\t"+"\tWinning Segment \t\t"+"Time to Complete the 500 Meters");
                    System.out.println(dataHolder.get(e).get(0)+"0-500 Meters \t\t\t"+finalSort[0]+" Seconds");
                    System.out.print("********************************************************************************************");
                    segmentWinner = e;
                    break;
                }
            }
        } else if (finalSort[0]==thousandSort[0]){
            for(int e = 1; e<20; e++){
                if (fiveHundredRecord.get(e).equals(finalSort[0])){
                    System.out.println("\n***************************************Fastest Segment Winner***************************************");
                    System.out.println("Name \t\t\t"+"\tWinning Segment \t\t"+"Time to Complete the 500 Meters");
                    System.out.println(dataHolder.get(e).get(0)+" 500-1000 Meters \t\t\t"+finalSort[0]+" Seconds");
                    System.out.print("********************************************************************************************");
                    segmentWinner = e;
                    break;
                }
            }
        } else if (finalSort[0]==thousandFiveHundredSort[0]){
            for(int e = 1; e<20; e++){
                if (fiveHundredRecord.get(e).equals(finalSort[0])){
                    System.out.println("\n***************************************Fastest Segment Winner***************************************");
                    System.out.println("Name \t\t\t"+"\tWinning Segment \t\t"+"Time to Complete the 500 Meters");
                    System.out.println(dataHolder.get(e).get(0)+" 1000-1500 Meters \t\t\t"+finalSort[0]+" Seconds");
                    System.out.print("********************************************************************************************");
                    segmentWinner = e;
                    break;
                }
            }
        } else if (finalSort[0]==twoThousandSort[0]){
            for(int e = 1; e<20; e++){
                if (fiveHundredRecord.get(e).equals(finalSort[0])){
                    System.out.println("\n***************************************Fastest Segment Winner***************************************");
                    System.out.println("Name \t\t\t"+"\tWinning Segment \t\t"+"Time to Complete the 500 Meters");
                    System.out.println(dataHolder.get(e).get(0)+" 1500-2000 Meters \t\t\t"+finalSort[0]+" Seconds");
                    System.out.print("********************************************************************************************");
                    segmentWinner = e;
                    break;
                }
            }
        }
    }



    private void writeToFile() throws IOException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("Winner.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            //Writing the overall winner data
            bufferedWriter.write("\n***************************************Overall Winner***************************************\n"+
            dataHolder.get(0).get(0)+" "+dataHolder.get(0).get(1)+" "+dataHolder.get(0).get(2)+" "+dataHolder.get(0).get(3)+" "
                    +dataHolder.get(0).get(4)+" "+dataHolder.get(0).get(5)+" "+dataHolder.get(0).get(6)+"\n"+
            dataHolder.get(overallPlayer).get(0)+" "+dataHolder.get(overallPlayer).get(1)+" "+dataHolder.get(overallPlayer).get(2)+" "+dataHolder.get(overallPlayer).get(3)+" "
                    +dataHolder.get(overallPlayer).get(4)+" "+dataHolder.get(overallPlayer).get(5)+" "+dataHolder.get(overallPlayer).get(6));
            System.out.println("**File write completed**");
            bufferedWriter.newLine();

            //Writing the segment winner data
            bufferedWriter.write("***************************************Fastest Segment Winner******************************* \n"
                    +"Name \t\t\t"+"\tWinning Segment \t\t"+"Time to Complete the 500 Meters\n"
                    +dataHolder.get(segmentWinner).get(0)+" 1500-2000 Meters \t\t\t"+segmentWinnerTime+" Seconds\n"+
                    "********************************************************************************************");

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing the file !");
        }

    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //This will read the text file at the start of the programme
        readFile();

        while(true) {
            System.out.println("\n\n==========ROWING SCORE BOARD MENU==========\n");
            System.out.println("A - Display All records");
            System.out.println("B - Display the Overall Winner of the Event");
            System.out.println("C - Display the Fastest Segment Winner of the Event");
            System.out.print("D - Write winning records to a text file : ");
            Scanner scanner = new Scanner(System.in);
            String menuSelect = scanner.nextLine().toUpperCase();

            //Switching the menu
            switch (menuSelect){
                case ("A"):
                    displayRecords();
                    break;
                case ("B"):
                    overallWinner();
                    break;
                case ("C"):
                    segmentWinner();
                    break;
                case ("D"):
                    writeToFile();
                    break;
            }
        }
    }
}
