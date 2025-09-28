import java.io.*;
import java.util.*;

public class BubbleSort {
    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        // bubble sort
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // then swaping
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    public static void main(String[] args) {
        String inputFile = "RandNumb.txt";
        String outputFile = "sortednumb.txt";

        try {
            List<Integer> numberList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");     // reads randnumb
                for (String p : parts) {
                    if (!p.isEmpty()) {
                        numberList.add(Integer.parseInt(p));
                    }
                }
            }
            reader.close();

            // converts it to array
            int[] numbers = numberList.stream().mapToInt(i -> i).toArray();
            // time computation
            long startTime = System.nanoTime();
            bubbleSort(numbers);
            long endTime = System.nanoTime();

            double timeInSeconds = (endTime - startTime) / 1_000_000_000.0;
            System.out.println("Bubble Sort completed in " + timeInSeconds + " seconds.");

            // writes the sorted numbers to sortednumb
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            for (int num : numbers) {
                writer.write(num + " ");
            }
            writer.close();

            System.out.println("Sorted numbers saved to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
