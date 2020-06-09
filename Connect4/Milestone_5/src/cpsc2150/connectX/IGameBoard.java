/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #5
 * Due: 5 December, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

/**
 * Game board represents a 2D game board of characters OR a map
 * with a key-value pair of Integers as the key, and a list of Characters
 * as the value.
 * Initialization ensures the game board is empty
 * Defines: number_of_rows: Z
 *          number_of_columns: Z
 *          number_needed_to_win: Z
 * Constraints: Columns and Rows >= MIN_VAL and <= MAX_VAL
 *              Number needed to win >= MIN_VAL and <= MAX_TO_WIN
 */

public interface IGameBoard {

    // constant variables
    int ZERO = 0;
    int MAX_TO_WIN = 25;
    int MIN_VAL = 3;
    int MAX_VAL = 100;

    /**
     * checks if column is full
     * @param c - column of matrix that user wants to place token
     * @return true if column c is not full, false if it is
     * @pre 0 <= c < number_of_columns
     * @post true || false
     */
    default boolean checkIfFree(int c) {
        if (whatsAtPos(getNumRows()-1, c) == ' ') return true;
        return false;
    }

    /**
     * checks if last token placed resulted in a win
     * @param c - column that user placed token
     * @return true if the token paced in matrix resulted in a win, otherwise returns false
     * @pre 0 <= c < number_of_columns
     * @post true || false
     */
    default boolean checkForWin(int c) {

        for (int i=0; i<getNumRows(); i++) {
            if (whatsAtPos(i, c) != ' ') {
                if (checkDiagWin(i, c, whatsAtPos(i, c)) == true || checkHorizWin(i, c, whatsAtPos(i, c)) == true
                        || checkVertWin(i, c, whatsAtPos(i, c)) == true)
                    return true;
            }
        }
        return false;
    }

    /**
     * places a token (p) in column (c) on the game board
     * token is placed in the lowest available row
     * @param p - token that is placed
     * @param c - column that token is placed in
     * @pre p == any valid character
     *      0 <= c < number_of_columns
     * @post p placed in lowest column of matrix with a space (' ')
     */
    void placeToken(char p, int c);

    /**
     * checks if the last token placed resulted in
     * a win horizontally
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed
     * @return true if last token placed results in a win
     * returns false otherwise
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == any valid Character
     * @post return true || return false
     */
    default boolean checkHorizWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < getNumColumns(); i++) {
            if (whatsAtPos(r, i) == p)
                count++;
            else
                count = 0;
            if (count >= getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * checks if the last token placed resulted in
     * a win vertically
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed
     * @return true if last token placed results in a win
     * returns false otherwise
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == any valid Character
     * @post return true || return false
     */
    default boolean checkVertWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < getNumRows(); i++) {
            if (whatsAtPos(i, c) == p)
                count++;
            else
                count = 0;
            if (count >= getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * checks if the last token placed resulted in
     *      * a win diagonally
     * @param r - row of last token placed
     * @param c - column of last token placed
     * @param p - last token placed (X or O)
     * @return true if last token placed results in a win
     * returns false otherwise
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == a valid char
     * @post return true || return false
     */
    default boolean checkDiagWin(int r, int c, char p) {
        // local variables
        int count = 0;
        int row = r;
        int col = c;

        // checks for a win diagonally up and to the right
        while (row < getNumRows() && col < getNumColumns() && whatsAtPos(row, col) == p) {
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
        while (row >= 0 && col >= 0 && whatsAtPos(row, col) == p) {
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
        while (row >= 0 && col < getNumColumns() && whatsAtPos(row, col) == p) {
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
        while (row < getNumRows() && col >= 0 && whatsAtPos(row, col) == p) {
            count++;
            row++;
            col--;
        }
        if (count >= getNumToWin())
            return true;

        return false;
    }

    /**
     * returns character at row r and column c
     * @param r - row of element passed in
     * @param c - column of element passed in
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == a valid char
     * @return - token at row r and column c
     * @post return player character || return ' '
    */
    char whatsAtPos(int r, int c);

    /**
     * checks if game board is full and no win conditions
     * are met
     * @return - true if game board is full (tie), false otherwise
     * @pre at least one token placed in matrix
     * @post return true || return false
     */
    default boolean checkTie() {
        int count = 0;
        // checks top column of game board for token
        for (int j = 0; j < getNumColumns(); j++) {
            if (whatsAtPos(getNumRows()-1, j) != ' ')
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
     * @post returns number_of_rows in the game board
     */
    int getNumRows();

    /**
     * @post returns number_of_columns in the game board
     */
    int getNumColumns();

    /**
     * @post returns number_needed_to_win in the game board
     */
    int getNumToWin();
}
