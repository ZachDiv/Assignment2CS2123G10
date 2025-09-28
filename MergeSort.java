import java.io.*;
import java.util.*;

public class MergeSort {
    public static void main(String[] args) throws Exception {

        // read numbers from RandNumb
        List<Integer> nums = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("RandNumb.txt"))) {
            while (sc.hasNextInt()) {
                nums.add(sc.nextInt());
            }
        }
        int[] arr = nums.stream().mapToInt(i -> i).toArray();

        //merge sort with timing
        long start = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        double seconds = (end - start) / 1e9; 
        System.out.printf("Merge Sort completed in %.6f seconds%n", seconds);

        //save sorted numbers 
        try (PrintWriter pw = new PrintWriter("sortednumb.txt")) {
            for (int n : arr) pw.print(n + " ");
        }

        //binary search for 1 500 900 
        int[] targets = {1, 500, 900};
        for (int t : targets) {
            int pos = Arrays.binarySearch(arr, t);
            if (pos >= 0)
                System.out.printf("Found %d at index %d%n", t, pos);
            else
                System.out.printf("%d not found%n", t);
        }
    }

    //merge sort
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
}
