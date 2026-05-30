import java.io.*;
import java.util.*;

public class Catalog {
    /*
    Reads from and writes to the .txt file storing courses.
    Can extract an array of courses from catalog.txt
     */

    public static void main(String[] args)
    {
        writelnToCatalog("MATH100");
    }

    public static void writelnToCatalog(String str) {
        try {
        FileWriter fileWriter = new FileWriter("catalog.txt", true);
        fileWriter.write(str + "\n");
        fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static ArrayList<String> readData(){
        ArrayList<String> list = new ArrayList<String>();
        try {
            // Create the Scanner with the data file
            Scanner fileIn = new Scanner(new File("data.txt"));
            while (fileIn.hasNext()) {
                // Add to the ArrayList
                list.add(fileIn.nextLine());
            }
            fileIn.close();
        }
        catch (IOException e) {
            System.out.println("No List Exists.");
        }
        return list;
    }

    private static void writeData(ArrayList<String> list) {
        try {
            FileWriter fileOut = new FileWriter("data.txt", true);
            fileOut.write(list.get(list.size() - 1));
            fileOut.close();
        }
        catch (IOException e) {
            System.out.println("Unable to write");
        }
    }

    private static void printList(ArrayList<String> l) {
        if (l.size() > 0) {
            System.out.println("Current Shopping List: ");
            for (String item : l) {
                System.out.println(item);
            }
            System.out.println();
        }

    }
}
