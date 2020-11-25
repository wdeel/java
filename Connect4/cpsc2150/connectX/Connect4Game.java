/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #4
 * Due: 18 November, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

import java.util.*;

public class Connect4Game {

    public static void main(String[] args) {
        // local variables
        char newGame = ' ', game;
        char [] tokenArr;
        int col, turn = 0;
        int columns, rows, numToWin, players;
        IGameBoard G;
        String board;
        Scanner scanner = new Scanner(System.in);

        // get number of players
        players = validatePlayers(scanner);
        // get token info
        tokenArr = getPlayerInfo(scanner, players);
        // get board parameters
        rows = validateRows(scanner);
        columns = validateColumns(scanner);
        numToWin = validateToWin(scanner);
        game = validateGameType(scanner);
        if (game == 'F') {
            // construct using 2D array
            G = new GameBoard(columns, rows, numToWin);
        }
        else {
            // construct using maps
            G = new GameBoardMem(columns, rows, numToWin);
        }

        // loop until users don't want to play anymore
        while (newGame != 'N') {

            // update game board, print to screen, and get column from user to place token
            board = G.toString();
            System.out.println(board);
            System.out.println("Player " + tokenArr[turn] + ", what column do you want to place your marker in?");
            col = validateCol(scanner, G, tokenArr, turn);

            // checks if col is full, get new column from user if so
            while (G.checkIfFree(col) == false) {
                System.out.println("Column is full");
                System.out.println("Player " + tokenArr[turn] + ", what column do you want to place your marker in?");
                col = validateCol(scanner, G, tokenArr, turn);
            }

            // places token in column for player
            G.placeToken(tokenArr[turn], col);
            turn++;
            // check for win or tie after placing token
            if (G.checkForWin(col) == true || G.checkTie() == true) {
                // if player wins
                if (G.checkForWin(col) == true) {
                    // update and print winning board to screen, ask if users want to play again
                    board = G.toString();
                    System.out.println(board);
                    System.out.println("Player " + tokenArr[turn-1] + " won!\nWould you like to play again? Y/N");
                    newGame = validateChar(scanner);
                    // if players want to play again...
                    if (newGame == 'Y') {
                        // get number of players
                        players = validatePlayers(scanner);
                        // get token info
                        tokenArr = getPlayerInfo(scanner, players);
                        // get board parameters
                        rows = validateRows(scanner);
                        columns = validateColumns(scanner);
                        numToWin = validateToWin(scanner);
                        game = validateGameType(scanner);
                        // construct using 2D array
                        if (game == 'F')
                            G = new GameBoard(columns, rows, numToWin);
                        // construct using maps
                        else
                            G = new GameBoardMem(columns, rows, numToWin);
                        // reset counter
                        turn = 0;
                    }
                }
                // game is a tie
                else {
                    // update and print tie board to screen, ask if users want to play again
                    board = G.toString();
                    System.out.println(board);
                    System.out.println("There was a tie!");
                    System.out.println("Would you like to play again Y/N");
                    newGame = validateChar(scanner);
                    // if players want to play again, skip turn two and start new game
                    if (newGame == 'Y') {
                        // get number of players
                        players = validatePlayers(scanner);
                        // get token info
                        tokenArr = getPlayerInfo(scanner, players);
                        // get board parameters
                        rows = validateRows(scanner);
                        columns = validateColumns(scanner);
                        numToWin = validateToWin(scanner);
                        game = validateGameType(scanner);
                        // construct using a 2D array
                        if (game == 'F')
                            G = new GameBoard(columns, rows, numToWin);
                            // construct using a map
                        else
                            G = new GameBoardMem(columns, rows, numToWin);
                        // reset turn to zero
                        turn = 0;
                    }
                }
            }
            // reset turn counter after last players turn
            if (turn == players) turn = 0;
        }
        // close scanner
        scanner.close();
    }

    // helper methods
    /**
     * validates the column entered by user to place token in
     * @pre - none
     * @param localInput - column that user wishes to place token
     * @param Local - object of class used in validation
     * @param arr - character array of player tokens
     * @para turn - counter keeping track of player turns
     * @return column - which column to place game board token
     * @post MIN_VAL <= column < MAX_VAL
     */
    private static int validateCol(Scanner localInput, IGameBoard Local, char []arr, int turn) {
        int column;
        // gets an integer from user
        column = localInput.nextInt();
        // checks if integer is invalid
        while (column < IGameBoard.ZERO || column > (Local.getNumColumns()-1)) {
            if (column < IGameBoard.ZERO) {
                System.out.println("Column cannot be less than " + IGameBoard.ZERO);
                System.out.println("Player " + arr[turn] + ", what column do you want to place your marker in?");
                column = localInput.nextInt();
            }
            else {
                System.out.println("Column cannot be greater than " + (Local.getNumColumns()-1));
                System.out.println("Player " + arr[turn] + ", what column do you want to place your marker in?");
                column = localInput.nextInt();
            }
        }
        return column;
    }

    /**
     * validates Character entered by user is not already taken
     * @pre - none
     * @param arr - char array holding user Tokens
     * @param size - number of players
     * @param token - Character to check if in arr
     * @return - true || false
     * @post - whether array contains token (true) || array does not
     * contain token(false)
     */
    private static boolean checkIfTaken(char [] arr, int size, char token) {
        for (int i=0; i<size; i++) {
            if (arr[i] == token) {
                return true;
            }
        }
        return false;
    }

    /**
     * validates number of players
     * @pre - none
     * @param local - object of class used in validation
     * @return - number of players
     * @post - num of players are >= 2 && <= 10
     */
    private static int validatePlayers(Scanner local) {
        int num;
        // get number of players from user
        System.out.println("How many players?");
        num = local.nextInt();
        while (num < 2 || num > 10) {
            if (num < 2) {
                System.out.println("Must be at least 2 players");
                System.out.println("How many players?");
                num = local.nextInt();
            }
            else {
                System.out.println("Must be 10 or fewer");
                System.out.println("How many players?");
                num = local.nextInt();
            }
        }
        return num;
    }

    /**
     * validates that users enter 'F' or 'f' or 'M' or 'm'
     * @pre - none
     * @param local - object of class used in validation
     * @return Fast Game (F) or Memory Efficient Game (M)
     * @post - selection is 'F' or 'M'
     */
    private static char validateGameType(Scanner local) {
        char input;
        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
        input = local.next().toUpperCase().charAt(0);
        while (input != 'F' && input != 'M') {
            System.out.println("***Error***");
            System.out.println(input + " is incorrect input.");
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
            input = local.next().toUpperCase().charAt(0);
        }
        return input;
    }

    /**
     * validates Character is not used by another player
     * @pre - none
     * @param local = object of class used in validation
     * @param num - number of players
     * @return array with new token selected by user
     * @post - array contains no duplicate characters
     */
    private static char [] getPlayerInfo(Scanner local, int num) {
        char [] arr;
        char token;

        arr = new char [num];
        for (int i=0; i<num; i++) {
            System.out.println("Enter the character to represent player " + (i+1));
            token = local.next().toUpperCase().charAt(0);
            // make sure token is not already taken, get new token if it is
            while (checkIfTaken(arr, num, token) == true) {
                System.out.println(token + " already taken as a player token!");
                System.out.println("Enter the character to represent player " + (i+1));
                token = local.next().toUpperCase().charAt(0);
            }
            arr[i] = token;
        }
        return arr;
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
            }
            else {
                System.out.println("Can have at most " + IGameBoard.MAX_TO_WIN + " in a row to win.");
                System.out.println("How many in a row to win?");
                choice = localInput.nextInt();
            }
        }
        return choice;
    }
}
