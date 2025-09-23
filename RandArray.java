import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RandArray {
    static int[] randomArray () {
        Random random = new Random();
        int[] randNum = new int[1000];
        for (int i = 0; i < randNum.length; i++) {
            randNum[i] = random.nextInt(1000);
            for (int j = 0; j < i; j++){
                if (randNum[i] == randNum[j]){
                    i--;
                }
            }
        }
        return randNum;
    }

    public static void main(String[] args) {
        int[] randomNumbers = randomArray();
        String numberstxt = "RandNumb.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(numberstxt))) {
            for (int item : randomNumbers) {
                writer.write(item + " ");
            }
            System.out.println("Array saved to " + numberstxt);
        } catch (IOException e) {
            System.err.println("Error saving array to file: " + e.getMessage());
        }

    }
}
