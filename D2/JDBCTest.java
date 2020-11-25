import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
 
class JDBCTest {
 
    private static final String url = "jdbc:mysql://localhost/";
 
    private static final String user = "root";
 
    private static final String password = "password";
 
    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);

        welcomeMessage();

        System.out.print("Enter a number: ");
        int selection = scan.nextInt();

        branch(selection, scan);

        //printList();
    }

    public static void branch(int num, Scanner scan) {
        if (num == 1) { printList(); }
        else if (num == 2) { addItem(num, scan); }
        else { removeItem(num); }
    }

    public static void welcomeMessage() {
        System.out.println("\nWelcome to the Diablo 2 item tracker");
        System.out.println("Please make a selection from the options listed below\n");
        System.out.println("1. List items");
        System.out.println("2. Add item");
        System.out.println("3. Remove item");
    }

    public static void addItem(int num, Scanner scan) {

        scan.nextLine();
        System.out.println("Enter the name of the item:");
        String itemName = scan.nextLine();
        System.out.println("Enter the properties you wish to list");
        String properties = scan.nextLine();

        System.out.println("The item you wish to enter is " + itemName + " and has the following properties: " + properties);
    }

    public static void removeItem(int num) {

    }
    public static void printList() {
        try {
                Connection con = DriverManager.getConnection(url, user, password);
                //System.out.println("\nConnected to Database\n");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from d2.Unique");
                while (rs.next()) {
                    String item = rs.getString(1);
                    String type = rs.getString(2);

                    System.out.println(item + " " + type);
                }
    
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}