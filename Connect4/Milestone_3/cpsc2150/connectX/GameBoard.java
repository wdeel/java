/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #3
 * Due: 28 October, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

/**
 * Correspondence: number_of_cols = arrayCols
 *                 number_of_rows = arrayRows
 *                 number_to_win = numWin
 *
 * @invariants 3 <= arrayRows, arrayCols < 100 and
 *             3 <= numWin < 25
 *
 */
public class GameBoard implements IGameBoard{

    private static int arrayRows, arrayCols, numWin;
    private static char[][] array;

    /**
     * @pre MIN_VAL <= cols < MAX_VAL &&
     *      MIN_VAL <= rows < MAX_VAL &&
     *      MIN_VAL <= numToWin <= MAX_TO_WIN
     * @param cols - number of columns in the array
     * @param rows - number of rows in the array
     * @param numToWin - number in a row needed to win the game
     * @post array contains spaces (' ') in every row/column
     */
    GameBoard(int cols, int rows, int numToWin) {
        numWin = numToWin;
        arrayRows = rows;
        arrayCols = cols;

        array = new char[arrayRows][arrayCols];
        for (int i = 0; i < arrayRows; i++) {
            for (int j = 0; j < arrayCols; j++) {
                array[i][j] = ' ';
            }
        }
    }

    // places token 'p' in lowest column with a space ' '
    public void placeToken(char p, int c) {
        // replaces lowest element in column with a space ' ' with token p
        for (int i = 0; i < getNumRows(); i++) {
            if (array[i][c] == ' ') {
                array[i][c] = p;
                return;
            }
        }
    }

    // places token 'p' in lowest column with a space ' '
    public char whatsAtPos(int r, int c) {
        char pos = array[r][c];
        return pos;
    }

    /**
     * Returns a fully formatted string that displays the current
     * game board
     * @pre - none
     * @return - updated game board matrix as a String
     * @post variable calling toString contains updated matrix
     */
    @Override
    public String toString() {
        String str = "";
        // prints 0..number of columns at top of gameboard
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10)
                str += "| " + i;
            else
                str += "|" + i;
        }
        str += "|" + "\n";
        // prints contents of 2D array
        for (int i = getNumRows()-1; i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                str += "| " + array[i][j];
            }
            str += "|" + "\n";
        }
        return str;
    }

    // returns number of rows of game board
    public int getNumRows() { return arrayRows; }

    // returns number of columns of game board
    public int getNumColumns() {
        return arrayCols;
    }

    // returns how many in a row to win
    public int getNumToWin() {
        return numWin;
    }
}