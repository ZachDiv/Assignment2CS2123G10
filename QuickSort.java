import java.io.*;
import java.util.*;

public class QuickSort {
    public static void main(String[] args) throws Exception {

        // read the numbers from RandNumb
        List<Integer> nums = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("RandNumb.txt"))) {
            while (sc.hasNextInt()) {
                nums.add(sc.nextInt());
            }
        }
        int[] arr = nums.stream().mapToInt(i -> i).toArray();

        // quick sort with timing
        long start = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        double seconds = (end - start) / 1e9; 
        System.out.printf("Quick Sort completed in %.6f seconds%n", seconds);

        //save the sorted numbers 
        try (PrintWriter pw = new PrintWriter("sortednumb.txt")) {
            for (int n : arr) pw.print(n + " ");
        }
    }

    // quick sort numbers
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
