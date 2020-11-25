import java.util.*;

class Palindrone {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        boolean option = true;

        while (option) {
            System.out.println("Enter a word to see if it's a palindrone:");
            String str = scan.nextLine();
            String upperCase = str.toUpperCase();

            boolean test = checkWord(upperCase);

            clearScreen();

            if (test)
                System.out.println(str + " is a palindrone\n");
            else
                System.out.println(str + " is not a palindrone\n");
        }
        scan.close();
    }

    private static boolean checkWord(String word) {
        //base case
        if (word.length() == 0 || word.length() == 1) 
            return true;
        
        if (word.charAt(0) == word.charAt(word.length()-1)) 
            return checkWord(word.substring(1,word.length()-1));

        return false;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}