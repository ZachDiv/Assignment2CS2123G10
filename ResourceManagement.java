import java.io.*;
import java.util.*;

/* ResourceManagement
 *
 * Stores the information needed to decide which items to purchase for the given budget and departments
 */
public class ResourceManagement
{
    private PriorityQueue<Department> departmentPQ; /* priority queue of departments */
    private Double remainingBudget;                 /* the budget left after purchases are made (should be 0 after the constructor runs) */
    private Double budget;                          /* the total budget allocated */
    private ArrayList<Department> departments;  //ZACH - fixing constructor test
    /* TO BE COMPLETED BY YOU
     * Fill in your name in the function below
     */
    public static void printName( )
    {
        /* TODO : Fill in your name */
        System.out.println("This solution was completed by:");
        System.out.println("Mathew Kuttner"); // Feel free to organize names however, I just uploaded first
        System.out.println("Zachary Blaha"); //Added the rest - Zach
        System.out.println("Gabriel Reyes");
        System.out.println("Lisan Temprana");
        System.out.println("David Dominguez");
    }

    /* Constructor for a ResourceManagement object
     * TODO
     * Simulates the algorithm from the pdf to determine what items are purchased
     * for the given budget and department item lists.
     */
    public ResourceManagement( String fileNames[], Double budget )
    {
        this.budget = budget;
        this.remainingBudget = budget;

        departments = new ArrayList<>(); //ZACH - FIXING PRINT SUMMARY TEST

        /* Create a department for each file listed in fileNames */
        departmentPQ = new PriorityQueue<>();
        for(String f : fileNames){
            Department d = new Department(f);
            d.priority = 0.0;                     // priority starts at 0
            d.itemsReceived = new LinkedList<>();
            d.itemsRemoved = new LinkedList<>();
            departmentPQ.add(d);
            departments.add(d); //ZACH - FIXING PRINT SUMMARY TEST
        }

        System.out.println("ITEMS PURCHASED");
        System.out.println("-----------------------------"); //david - dividing line 

        /* Simulate the algorithm for picking the items to purchase */
        while(!departmentPQ.isEmpty()){

            Department dept = departmentPQ.poll();   // get department with lowest current priority

            if(dept.itemsDesired.isEmpty())
                continue;                            // nothing left to buy for this department

            Item next = dept.itemsDesired.peek();    // check next desired item

            if(next.price <= remainingBudget){
                // Purchase it
                dept.itemsDesired.poll();
                dept.itemsReceived.add(next);
                dept.priority += next.price;
                remainingBudget -= next.price;

                // Printing the purchase
                String price = String.format("$%.2f", next.price);
                System.out.printf("Department of %-30s- %-30s- %30s\n",
                        dept.name, next.name, price);

                // Reinsert into priority queue with updated priority
                departmentPQ.add(dept);
            }
            else {
                // Cannot afford then skip/remove it
                dept.itemsDesired.poll();
                dept.itemsRemoved.add(next);

                // Still reinsert to allow remaining items to be checked
                departmentPQ.add(dept);
            }
        }

    }
    public void printSummary(  ){

        System.out.println();

        for (Department dept : departments) {  //ZACH - FIXING PRINT ISSUES, SWAPPED TO ARRAYLIST

            System.out.println(dept.name);

            //dept name
            System.out.println("Department of " + dept.name);

            //total spent
            String totalSpent = String.format("$%.2f", dept.priority);
            System.out.println("Total Spent       = " + totalSpent);

            // % of budget
            double percent = (budget == 0 ? 0 : (dept.priority / budget) * 100.0);
            System.out.printf("Percent of Budget = %.2f%%\n", percent);
            System.out.println("-----------------------------");

            //items received
            System.out.println("ITEMS RECEIVED");
            if (dept.itemsReceived.isEmpty()) {
                System.out.println();
            }
            else {
                for (Item item : dept.itemsReceived) {
                    String price = String.format("$%.2f", item.price);
                    System.out.printf("%-30s - %30s\n", item.name, price);
                }
                System.out.println();
            }

            //items not received
            System.out.println("ITEMS NOT RECEIVED");
            boolean printed = false;

            for (Item item : dept.itemsRemoved) {
                printed = true;
                String price = String.format("$%.2f", item.price);
                System.out.printf("%-30s - %30s\n", item.name, price);
            }

            for (Item item : dept.itemsDesired) {
                printed = true;
                String price = String.format("$%.2f", item.price);
                System.out.printf("%-30s - %30s\n", item.name, price);
            }

            if (!printed) {
                System.out.println();
            }

            System.out.println();
        }
    }
}

/* Department
 *
 * Stores the information associated with a Department at the university
 */
class Department implements Comparable<Department>
{
    String name;                /* name of this department */
    Double priority;            /* total money spent on this department */
    Queue<Item> itemsDesired;   /* list of items this department wants */
    Queue<Item> itemsReceived;  /* list of items this department received */
    Queue<Item> itemsRemoved;   /* list of items that were skipped because they exceeded the remaining budget */

    /* TODO
     * Constructor to build a Department from the information in the given fileName
     */
    public Department( String fileName ){ // This constructor strictly relies on properly formatted data
        /* Open the fileName, create items based on the contents, and add those items to itemsDesired */
        itemsDesired = new LinkedList<>(); // Not sure why this was not provided in the given code
        try{
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); // Might be too powerful, but it works

            String line;
            String itemName; // Temporary variable storing the name of each item to add
            Double itemPrice; // Temporary variable storing the price of each item to add
            Item newItem; // Temporary object variable to avoid repeated declaration and to be added to itemsDesired

            name = line = br.readLine(); // First line must always be the department name
            br.readLine(); // Read empty after department name
            while((line = br.readLine()) != null){
                if(!line.isEmpty()){ // Item must be formatted properly
                    itemName = line;
                    itemPrice = Double.parseDouble(line=br.readLine());
                    br.readLine(); // Discard empty line

                    newItem = new Item(itemName, itemPrice);
                    itemsDesired.add(newItem); // Only didn't work before because itemsDesired was not defined
                }
            }
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /*
     * Compares the data in the given Department to the data in this Department
     * Returns -1 if this Department comes first
     * Returns 0 if these Departments have equal priority
     * Returns 1 if the given Department comes first
     *
     * This function is to ensure the departments are sorted by the priority when put in the priority queue
     */
    public int compareTo( Department dept ){
        return this.priority.compareTo( dept.priority );
    }

    public boolean equals( Department dept ){
        return this.name.compareTo( dept.name ) == 0;
    }

    @Override
    @SuppressWarnings("unchecked") //Suppresses warning for cast
    public boolean equals(Object aThat) {
        if (this == aThat) //Shortcut the future comparisons if the locations in memory are the same
            return true;
        if (!(aThat instanceof Department))
            return false;
        Department that = (Department)aThat;
        return this.equals( that ); //Use above equals method
    }

    @Override
    public int hashCode() {
        return name.hashCode(); /* use the hashCode for data stored in this name */
    }

    /* Debugging tool
     * Converts this Department to a string
     */
    @Override
    public String toString() {
        return "NAME: " + name + "\nPRIORITY: " + priority + "\nDESIRED: " + itemsDesired + "\nRECEIVED " + itemsReceived + "\nREMOVED " + itemsRemoved + "\n";
    }
}

/* Item
 *
 * Stores the information associated with an Item which is desired by a Department
 */
class Item
{
    String name;    /* name of this item */
    Double price;   /* price of this item */

    /*
     * Constructor to build a Item
     */
    public Item( String name, Double price ){
        this.name = name;
        this.price = price;
    }

    /* Debugging tool
     * Converts this Item to a string
     */
    @Override
    public String toString() {
        return "{ " + name + ", " + price + " }";
    }
}
