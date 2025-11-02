import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    char letter;
    int frequency;
    Node left, right;

    Node(char letter, int frequency) { //FOR MY LEAF NODES
        this.letter = letter;
        this.frequency = frequency;
    }

    Node(int frequency, Node left, Node right) { //FOR MY INTERNAL NODES
        this.letter = '\0'; //Null character, since this isn't a leaf node and isn't attached to a character
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override //Needed so I can chuck my nodes into the priority queue
    public int compareTo(Node compared) {
        return Integer.compare(this.frequency, compared.frequency);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}

public class HuffmanGreedy {
    static void printCodes(Node node, String code) { //
        if (node == null) { //Base Case
            return;
        }
        if (node.isLeaf()) { //If leaf node print letter and code
            System.out.println(node.letter + ": " + code);
            return;
        }

        //RECURSIVE CALLS
        printCodes(node.right, code + "0");
        printCodes(node.left, code + "1");
        
    }
    public static void main(String[] args) {
        //LEAF NODES
        Node A = new Node('A', 6);
        Node B = new Node('B', 2);
        Node C = new Node('C', 2);
        Node D = new Node('D', 2);
        Node E = new Node('E', 2);
        Node F = new Node('F', 5);

        //PRIORITY QUEUE CREATION
        PriorityQueue<Node> minQueue = new PriorityQueue<>();
        minQueue.add(A);
        minQueue.add(B);
        minQueue.add(C);
        minQueue.add(D);
        minQueue.add(E);
        minQueue.add(F);

        //HUFFMAN APPROACH (used greedy from class on 10/30)
        while (minQueue.size() > 1) {
            Node left = minQueue.poll();
            Node right = minQueue.poll();
            Node parent = new Node((left.frequency + right.frequency), left, right); //New Internal Node
            minQueue.add(parent);
        }

        System.out.println("Huffman Codes(Greedy): ");
        Node root = minQueue.peek(); //The Single node left after the loop
        printCodes(root, ""); //empty string cuz the code starts empty and adds to each recursive call

    }
}
