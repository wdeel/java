/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #2
 * Due: 10 October, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

/**
 * Correspondence: number_of_cols = arrayCols
 *                 number_of_rows = arrayRows
 *                 number_to_win = numWin
 *
 * @invariants 3 <= row, arrayRows, arrayCols < 100 and
 *             3 <= numWin < 25 and
 *             token == 'X' || token == 'O'
 *
 */
public class GameBoard implements IGameBoard{

    private int row, arrayRows, arrayCols, numWin;
    private char token;
    private char[][] array;

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

    /**
     * @param c - column of matrix that user wants to place token
     * @return true if column c is not full, false if it is
     */
    public boolean checkIfFree(int c) {
        if (array[getNumRows()-1][c] == ' ')
            return true;
        else
            return false;
    }

    /**
     * @param c - column that user placed token
     * @return true if the token paced in matrix resulted in a win, otherwise returns false
     */
    public boolean checkForWin(int c) {
        if (checkHorizWin(row, c, token) == true || checkVertWin(row, c, token) == true
                || checkDiagWin(row, c, token))
            return true;
        else
            return false;
    }

    /**
     * @param p - token that is placed (X or O)
     * @param c - column that token is placed in
     */
    public void placeToken(char p, int c) {
        // replaces lowest element in column with a space ' ' with token p
        for (int i = 0; i < getNumRows(); i++) {
            if (array[i][c] == ' ') {
                array[i][c] = p;
                row = i;
                token = p;
                return;
            }
        }
    }

    /**
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed (X or O)
     * @return true if last token placed results in 4 in a row horizontally
     * returns false otherwise
     */
    public boolean checkHorizWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < getNumColumns(); i++) {
            if (array[r][i] == p)
                count++;
            else
                count = 0;
            if (count >= getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed (X or O)
     * @return true if last token placed results in a win
     * returns false otherwise
     */
    public boolean checkVertWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < getNumRows(); i++) {
            if (array[i][c] == p)
                count++;
            else
                count = 0;
            if (count >= getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * @param r - row of last token placed
     * @param c - column of last token placed
     * @param p - last token placed (X or O)
     * @return true if last token placed results in a win
     * returns false otherwise
     */
    public boolean checkDiagWin(int r, int c, char p) {
        // local variables
        int count = 0;
        int row = r;
        int col = c;

        // checks for a win diagonally up and to the right
        while (row < getNumRows() && col < getNumColumns() && array[row][col] == p) {
            count++;
            row++;
            col++;
        }
        if (count >= getNumToWin())
            return true;

        // sets local variables down and to the left one element
        // this keeps count from counting the same element twice
        row = r - 1;
        col = c - 1;

        // checks for a win diagonally down and to the left
        while (row >= 0 && col >= 0 && array[row][col] == p) {
            count++;
            row--;
            col--;
        }
        if (count >= getNumToWin())
            return true;

        // resets count to zero and sets local variables to the
        // original row/column passed in
        count = 0;
        row = r;
        col = c;

        // checks for win diagonally up and to the left
        while (row >= 0 && col < getNumColumns() && array[row][col] == p) {
            count++;
            row--;
            col++;
        }
        if (count >= getNumToWin())
            return true;

        // sets local variables down and to the right one element
        // this keeps count from counting the same element twice
        row = r + 1;
        col = c - 1;

        // checks for win diagonally down and to the right
        while (row < getNumRows() && col >= 0 && array[row][col] == p) {
            count++;
            row++;
            col--;
        }
        if (count >= getNumToWin())
            return true;

        return false;
    }

    /**
     * @param r - row of element passed in
     * @param c - column of element passed in
     * @return - token at row r and column c (X or O)
     */
    public char whatsAtPos(int r, int c) {
        char pos = array[r][c];
        return pos;
    }

    /**
     * @return - updated game board matrix as a String
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10)
                str += "| " + i;
            else
                str += "|" + i;
        }
        str += "|" + "\n";
        for (int i = getNumRows()-1; i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                str += "| " + array[i][j];
            }
            str += "|" + "\n";
        }
        return str;
    }

    /**
     * @return - true if game board is full (tie), false otherwise
     */
    public boolean checkTie() {
        int count = 0;
        // checks top column of game board for X or O token
        for (int j = 0; j < getNumRows(); j++) {
            if (array[getNumRows()-1][j] == 'X' || array[getNumRows()-1][j] == 'O')
                count++;
            // resets count if a space is found on top column of matrix
            else
                count = 0;
            if (count == getNumColumns())
                return true;
        }
        return false;
    }

    /**
     * @return number of rows
     */
    public int getNumRows() {
        return arrayRows;
    }

    /**
     * @return @return number of columns
     */
    public int getNumColumns() {
        return arrayCols;
    }

    /**
     * @return @return number needed to win game
     */
    public int getNumToWin() {
        return numWin;
    }
}
