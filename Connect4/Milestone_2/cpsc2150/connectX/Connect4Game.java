/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #2
 * Due: 10 October, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

import java.util.*;

public class Connect4Game {

    public static void main(String[] args) {
        // local variables
        char x = 'X', o = 'O', newGame = ' ';
        int col;
        int columns, rows, numToWin;
        IGameBoard G;
        String board, playerOneWin = "OFF";
        Scanner scanner = new Scanner(System.in);

        // get board parameters
        rows = validateRows(scanner);
        columns = validateColumns(scanner);
        numToWin = validateToWin(scanner);
        // construct game board
        G = new GameBoard(columns, rows, numToWin);

        while (newGame != 'N') {

            // player one turn
            // update game board, print to screen, and get column from user to place token
            board = G.toString();
            System.out.println(board);
            System.out.println("Player " + x + ", what column do you want to place your marker in?");
            col = validateCol(scanner, G, x);

            // checks if col is full, get new column from user if it is
            while (G.checkIfFree(col) == false) {
                System.out.println("Column is full");
                System.out.println("Player " + x + ", what column do you want to place your marker in?");
                col = validateCol(scanner, G, x);
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
                        // get board parameters
                        rows = validateRows(scanner);
                        columns = validateColumns(scanner);
                        numToWin = validateToWin(scanner);
                        // construct new game board
                        G = new GameBoard(columns, rows, numToWin);
                        // skips turn two
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
                        // get board parameters
                        rows = validateRows(scanner);
                        columns = validateColumns(scanner);
                        numToWin = validateToWin(scanner);
                        // construct new game baord
                        G = new GameBoard(columns, rows, numToWin);
                        // skips turn two
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
                col = validateCol(scanner, G, o);

                // checks if col is full, get new column from user until the column selected is free
                while (G.checkIfFree(col) == false) {
                    System.out.println("Column is full");
                    System.out.println("Player " + o + ", what column do you want to place your marker in?");
                    col = validateCol(scanner, G, o);
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
                            // get board parameters
                            rows = validateRows(scanner);
                            columns = validateColumns(scanner);
                            numToWin = validateToWin(scanner);
                            // construct new game baord
                            G = new GameBoard(columns, rows, numToWin);
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
                            // get board parameters
                            rows = validateRows(scanner);
                            columns = validateColumns(scanner);
                            numToWin = validateToWin(scanner);
                            // construct new game baord
                            G = new GameBoard(columns, rows, numToWin);
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
     * @pre - none
     * @param localInput - column that user wishes to place token
     * @param Local - object of class used in validation
     * @param token - token of player ('X' or 'O')
     * @return column - which column to place game board token
     * @post MIN_VAL <= column < MAX_VAL
     */
    private static int validateCol(Scanner localInput, IGameBoard Local, char token) {
        int column;
        // gets an integer from user
        column = localInput.nextInt();
        // checks if integer is invalid
        while (column < IGameBoard.ZERO || column > (Local.getNumColumns()-1)) {
            if (column < IGameBoard.ZERO) {
                System.out.println("Column cannot be less than " + IGameBoard.ZERO);
                System.out.println("Player " + token + ", what column do you want to place your marker in?");
                column = localInput.nextInt();
            }
            else {
                System.out.println("Column cannot be greater than " + (Local.getNumColumns()-1));
                System.out.println("Player " + token + ", what column do you want to place your marker in?");
                column = localInput.nextInt();
            }
        }
        return column;
    }

    /**
     * @pre - none
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

    /**
     * @pre - none
     * @param localInput - choice of user
     * @return choice - how many rows on game board
     * @post MIN_VAL <= choice < MAX_VAL
     */
    private static Integer validateRows(Scanner localInput) {
        Integer choice;
        // gets an int from user
        System.out.println("How many rows should be on the board?");
        choice = localInput.nextInt();
        // checks that input is between MIN_VAL and MAX_VAL
        while (choice < IGameBoard.MIN_VAL || choice > IGameBoard.MAX_VAL) {
            if (choice < IGameBoard.MIN_VAL) {
                System.out.println("Must have at least " + IGameBoard.MIN_VAL + " rows.");
                System.out.println("How many rows should be on the board?");
                choice = localInput.nextInt();
            }
            else {
                System.out.println("Can have at most " + IGameBoard.MAX_VAL + " rows.");
                System.out.println("How many rows should be on the board?");
                choice = localInput.nextInt();
            }
        }
        return choice;
    }

    /**
     * @pre - none
     * @param localInput - choice of user
     * @return choice - how many cols on game board
     * @post MIN_VAL <= choice < MAX_VAL
     */
    private static Integer validateColumns(Scanner localInput) {
        Integer choice;
        // gets an int from user
        System.out.println("How many columns should be on the board?");
        choice = localInput.nextInt();
        // checks that input is between MIN_VAL and MAX_VAL
        while (choice < IGameBoard.MIN_VAL || choice > IGameBoard.MAX_VAL) {
            if (choice < IGameBoard.MIN_VAL) {
                System.out.println("Must have at least " + IGameBoard.MIN_VAL + " columns.");
                System.out.println("How many columns should be on the board?");
                choice = localInput.nextInt();
            }
            else {
                System.out.println("Can have at most " + IGameBoard.MAX_VAL + " columns.");
                System.out.println("How many columns should be on the board?");
                choice = localInput.nextInt();
            }
        }
        return choice;
    }

    /**
     * @pre - none
     * @param localInput - choice of user
     * @return choice - number needed in a row to win
     * @post MIN_VAL <= choice < MAX_TO_WIN
     */
    private static Integer validateToWin(Scanner localInput) {
        Integer choice;
        // gets an int from user
        System.out.println("How many in a row to win?");
        choice = localInput.nextInt();
        // checks that input is between MIN_VAL and MAX_VAL
        while (choice < IGameBoard.MIN_VAL || choice > IGameBoard.MAX_TO_WIN) {
            if (choice < IGameBoard.MIN_VAL) {
                System.out.println("Must have at least " + IGameBoard.MIN_VAL + " in a row to win.");
                System.out.println("How many in a row to win?");
                choice = localInput.nextInt();
            } else {
                System.out.println("Can have at most " + IGameBoard.MAX_TO_WIN + " in a row to win.");
                System.out.println("How many in a row to win?");
                choice = localInput.nextInt();
            }
        }
        return choice;
    }
}
