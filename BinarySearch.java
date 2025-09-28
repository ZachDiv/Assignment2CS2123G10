import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class BinarySearch {
    //binary search method
    static int binarySearch(ArrayList<Integer> arr, int x) {
        int low = 0, high = arr.size() - 1;
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid) == x)
                return mid;

            if (arr.get(mid) < x)
                low = mid + 1;

            else
                high = mid - 1;
        }
        //element not found
        return -1;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<Integer> storedNums = new ArrayList<Integer>();
        Scanner scnr = new Scanner(System.in);
        int numToCheck;

        //.txt file input from scanner
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String path = "sortednumb.txt";

        Scanner sc = new Scanner(new File(path));

        while (sc.hasNextInt()) {
            storedNums.add(sc.nextInt());
        }

        sc.close();

        //search for num
        System.out.print("Enter the number to look for: ");
        numToCheck = scnr.nextInt();

        long startTime = System.nanoTime();
        int index = binarySearch(storedNums, numToCheck);
        long endTime = System.nanoTime();

        if (index != -1) {
            System.out.println("\"" + numToCheck + "\" found");
        } else {
            System.out.println("\"" + numToCheck + "\" not found");
        }

        //calculates and prints time rounded to 5th decimal place
        double seconds = (endTime - startTime) / 1000000000.0;
        System.out.printf("Binary search took: %.9f seconds%n", seconds);

    }
}
