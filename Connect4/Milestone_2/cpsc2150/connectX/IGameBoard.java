/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #2
 * Due: 10 October, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

/**
 * Game board represents a 2D game board of characters.
 * Indexing starts at ZERO and is MAX_VAL x MAX_VAL or smaller
 * Initialization ensures the game board is empty
 * Defines: number_of_rows: Z
 *          number_of_columns: Z
 *          number_needed_to_win: Z_
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
     * @pre 0 <= c < number_of_columns
     * @post true || false
     */
    boolean checkIfFree(int c);

    /**
     * checks if last token placed resulted in a win
     * @pre 0 <= c < number_of_columns
     * @post true || false
     */
    boolean checkForWin(int c);

    /**
     * places a token (p) in column (c) on the game board
     * token is placed in the lowest available row
     * @pre p == 'O' || p == 'X" and
     *      0 <= c < number_of_columns
     * @post X or O placed in lowest column of matrix with a space (' ')
     */
    void placeToken(char p, int c);

    /**
     * checks if the last token placed resulted in
     * a win horizontally
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == 'X' || p == 'O'
     * @post return true || return false
     */
    boolean checkHorizWin(int r, int c, char p);

    /**
     * checks if the last token placed resulted in
     *      * a win vertically
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == 'X' || p == 'O'
     * @post return true || return false
     */
    boolean checkVertWin(int r, int c, char p);

    /**
     * checks if the last token placed resulted in
     *      * a win diagonally
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == 'X' || p == 'O'
     * @post return true || return false
     */
    boolean checkDiagWin(int r, int c, char p);

    /**
     * returns character at row r and column c
     * @pre 0 <= r < number_of_rows &&
     *      0 <= c < number_of_columns &&
     *      p == 'X' || p == 'O'
     * @post return X || return O || return ' '
    */
    char whatsAtPos(int r, int c);

    /**
     * Returns a fully formatted string that displays the current
     * game board
     * @post variable calling toSTring contains updated matrix
     */
    String toString();

    /**
     * checks if game board is full and no win conditions
     * are met
     * @pre at least one token placed in matrix
     * @post return true || return false
     */
    boolean checkTie();

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
