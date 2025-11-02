import java.util.ArrayList;
import java.util.Scanner;

public class HeapTree {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter 7 integers (press Enter after each one):");
        for (int i = 0; i < 7; i++) {
            int num = input.nextInt();
            numbers.add(num);
        }

        System.out.println("Select Heap Type:");
        System.out.println("1. Max Heap");
        System.out.println("2. Min Heap");
        int choice = input.nextInt();

        // Only Max Heap is handled here *********add choice 2 for min heap
        if (choice == 1) {
            System.out.println("Fig A (Max Heap)");

            MaxHeap heap = new MaxHeap();

            // Insert all numbers
            for (int num : numbers) {
                heap.insert(num);
            }

            // Step 4: Remove and show all elements one by one
            while (!heap.isEmpty()) {
                System.out.println("Extracted Max: " + heap.extractMax());
            }

        } 

        input.close();
    }
}

// MaxHeap Class 
class MaxHeap {
    private ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Insert a new value into the heap
    public void insert(int value) {
        heap.add(value); // add at the end
        moveUp(heap.size() - 1); // moved it up to keep heap order
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Remove the largest (root) value
    public int extractMax() {
        if (heap.isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }

        int max = heap.get(0); // root element
        int last = heap.remove(heap.size() - 1); // remove last

        if (!heap.isEmpty()) {
            heap.set(0, last); // move last to root
            moveDown(0);       // fix heap order
        }

        return max;
    }

    // Move the new element up if it's larger than parent
    private void moveUp(int index) {
        int parent = (index - 1) / 2;

        // Keep moving up while the new value is bigger than its parent
        while (index > 0 && heap.get(index) > heap.get(parent)) {
            // swap
            int temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);

            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // Move down after removing max element
    private void moveDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;  // left child
            int right = 2 * index + 2; // right child
            int largest = index;

            // find largest among index, left, right
            if (left < size && heap.get(left) > heap.get(largest))
                largest = left;
            if (right < size && heap.get(right) > heap.get(largest))
                largest = right;

            if (largest == index)
                break; // heap property ok

            // swap
            int temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);

            index = largest; // continue down
        }
    }
}


