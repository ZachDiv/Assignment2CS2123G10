import java.util.*;

public class HT {
    public static void main(String[] args) {
        int[] numArr = new int[7];
        Scanner scnr = new Scanner(System.in);

        System.out.println("Enter 7 integers:");

        for (int i = 0; i < 7; i++) {
            numArr[i] = scnr.nextInt();
        }
    }
}
