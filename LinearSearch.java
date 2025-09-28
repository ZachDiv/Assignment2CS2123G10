import java.io.*;
import java.util.*;

public class LinearSearch {

    // My actual search program
    public static int linearSearch(int[] arr, int numberToFind) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == numberToFind) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);
        //Reading numbers from our Random numbers and adding them to a list
        try (BufferedReader br = new BufferedReader(new FileReader("RandNumb.txt"))) {
            String line = br.readLine();
            if (line != null) {
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    numbers.add(Integer.parseInt(token));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        //making an array because I hate lists
        int[] arr = numbers.stream().mapToInt(i -> i).toArray();

        System.out.print("Enter the number to search for: ");
        int numberToFind = scnr.nextInt();
        //HAD to use System.nanoTime, the ThreadMX stuff would return 0 no matter what I did due to Javas underlying overhead rounding cycles to 0
        long startTime = System.nanoTime();
        int index = linearSearch(arr, numberToFind);
        long endTime = System.nanoTime();

        double elapsedTimeSeconds = (endTime - startTime) / 1000000000.0;

        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found in the file.");
        }

        System.out.printf("Estimated run time: %.9f seconds%n", elapsedTimeSeconds);
    }
}
