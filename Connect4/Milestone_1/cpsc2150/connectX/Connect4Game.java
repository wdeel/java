/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #1
 * Due: 23 September, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

import java.util.*;

public class Connect4Game {
    public static void main(String[] args) {
        // local variables
        char x = 'X', o = 'O', newGame = ' ';
        int col;
        GameBoard G = new GameBoard();
        String board, playerOneWin = "OFF";
        Scanner scanner = new Scanner(System.in);

        while (newGame != 'N') {
            // player one turn
            // update gameboard, print to screen, and get column from user to place token
            board = G.toString();
            System.out.println(board);
            System.out.println("Player " + x + ", what column do you want to place your marker in?");
            col = validateCol(scanner);

            // checks if col is full, get new column from user if it is
            while (G.checkIfFree(col) == false) {
                System.out.println("Column is full");
                System.out.println("Player " + x + ", what column do you want to place your marker in?");
                col = validateCol(scanner);
            }

            // places token in column for player one
            G.placeToken(x, col);
            // check for win or tie after placing token
            if (G.checkForWin(col) == true || G.checkTie() == true) {
                // if player one wins
                if (G.checkForWin(col) == true) {
                    // update and print winning board to screen, ask if users want to play again
                    board = G.toString();
                    System.out.println(board);
                    System.out.println("Player " + x + " won!\nWould you like to play again? Y/N");
                    newGame = validateChar(scanner);
                    // if players want to play again, skip turn two and start new game
                    if (newGame == 'Y') {
                        G = new GameBoard();
                        playerOneWin = "ON";
                    }
                }
                // if game is a tie
                else {
                    // update and print tie board to screen, ask if users want to play again
                    board = G.toString();
                    System.out.println(board);
                    System.out.println("There was a tie!");
                    System.out.println("Would you like to play again Y/N");
                    newGame = validateChar(scanner);
                    // if players want to play again, skip turn two and start new game
                    if (newGame == 'Y') {
                        G = new GameBoard();
                        playerOneWin = "ON";
                    }
                }
            }
            // player two turn
            // check if player one won, and if users want to play again
            if (playerOneWin == "OFF" && newGame != 'N') {
                // update game board, prints to screen and get input from player two
                board = G.toString();
                System.out.println(board);
                System.out.println("Player " + o + ", what column do you want to place your marker in?");
                col = validateCol(scanner);

                // checks if col is full, get new column from user until the column selected is free
                while (G.checkIfFree(col) == false) {
                    System.out.println("Column is full");
                    System.out.println("Player " + o + ", what column do you want to place your marker in?");
                    col = validateCol(scanner);
                }

                // places token in column for player two
                G.placeToken(o, col);
                // check for win or tie after placing token
                if (G.checkForWin(col) == true || G.checkTie() == true) {
                    // if player two wins
                    if (G.checkForWin(col) == true) {
                        // update and print winning board to screen, ask if users want to play again
                        board = G.toString();
                        System.out.println(board);
                        System.out.println("Player " + o + " won!\nWould you like to play again? Y/N");
                        newGame = validateChar(scanner);
                        // creates new game board if users want to play again
                        if (newGame == 'Y') {
                            G = new GameBoard();
                        }
                    }
                    // game is a tie, updates and shows game board, and asks if users want to play again
                    else {
                        board = G.toString();
                        System.out.println(board);
                        System.out.println("There was a tie!");
                        System.out.println("Would you like to play again Y/N");
                        newGame = validateChar(scanner);
                        // creates new game board if users want to play again
                        if (newGame == 'Y') {
                            G = new GameBoard();
                        }
                    }
                }
            }
            // turns playerOneWin off
            else
                playerOneWin = "OFF";
        }
    }

    /**
     * @pre variable calling method is an int
     * @param localInput - column that user wishes to place token
     * @return >= 0 || <= 6
     * @post variable calling method is >= 0 && <= 6
     */
    private static int validateCol(Scanner localInput) {
        int column;
        // gets an integer from user
        column = localInput.nextInt();
        // checks if integer is invalid
        while (column < 0 || column > 6) {
            if (column < 0) {
                System.out.println("Column cannot be less than 0");
                column = localInput.nextInt();
            }
            else {
                System.out.println("Column cannot be greater than 6");
                column = localInput.nextInt();
            }
        }
        return column;
    }

    /**
     * @pre variable calling method is a char
     * @param localInput - choice of user
     * @return 'Y' || 'N'
     * @post variable calling method is 'Y' || 'N'
     */
    private static char validateChar(Scanner localInput) {
        char choice;
        // gets a char from user
        choice = localInput.next().toUpperCase().charAt(0);
        // checks that input is a Y or a N
        while (choice != 'Y' && choice != 'N') {
            System.out.println("Would you like to play again? Y/N");
            choice = localInput.next().toUpperCase().charAt(0);
        }
        return choice;
    }
}
