/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #1
 * Due: 23 September, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

/**
 * @invariants (row >= 0 and <= 6 and token == X || token == O and MAX_X = 6 and MAX_Y = 7
 *              && array has 7 rows && 7 columns)
 */
public class GameBoard {

    private int row;
    private char token;
    private char[][] array = new char[7][7];
    static final int MAX_X = 6;
    static final int MAX_Y = 7;

    /**
     * @pre array declared with 7 rows and 7 columns
     * @post array contains spaces (' ') in rows 0-5 columns 0-6 and numbers 0-6 in row 6
     * columns 0-6
     */
    GameBoard() {
        for (int i = 0; i < MAX_X; i++) {
            for (int j = 0; j < MAX_Y; j++) {
                array[i][j] = ' ';
            }
        }
        array[6][0] = '0';
        array[6][1] = '1';
        array[6][2] = '2';
        array[6][3] = '3';
        array[6][4] = '4';
        array[6][5] = '5';
        array[6][6] = '6';
    }

    /**
     * @pre c >= 0 && c < 7
     * @param c - column of matrix that user wants to place token
     * @return true if column c is not full, false if it is
     * @post return true || return false
     */
    public boolean checkIfFree(int c) {
        if (array[5][c] == ' ')
            return true;
        else
            return false;
    }

    /**
     * @pre c >= 0 && c < 7
     * @param c - column that user placed token
     * @return true if the token paced in matrix resulted in a win, otherwise returns false
     * @post return true || return false
     */
    public boolean checkForWin(int c) {
        if (checkHorizWin(row, c, token) == true || checkVertWin(row, c, token) == true
                || checkDiagWin(row, c, token))
            return true;
        else
            return false;
    }

    /**
     * @pre p == O || p == X && c >= 0 && c < 7
     * @param p - token that is placed (X or O)
     * @param c - column that token is placed in
     * @post X or O paced in lowest column of matrix with a space (' ')
     */
    public void placeToken(char p, int c) {
        // replaces lowest element in column with a space ' ' with token p
        for (int i = 0; i < MAX_X; i++) {
            if (array[i][c] == ' ') {
                array[i][c] = p;
                row = i;
                token = p;
                return;
            }
        }
    }

    /**
     * @pre r >= 0 && r < 6 && c >=0 && c < 7 && p == X || p == O
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed (X or O)
     * @return true if last token placed results in 4 in a row horizontally
     * returns false otherwise
     * @post return true || return false
     */
    public boolean checkHorizWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < MAX_Y; i++) {
            if (array[r][i] == p)
                count++;
            else
                count = 0;
            if (count >= 4)
                return true;
        }
        return false;
    }

    /**
     * @pre r >= 0 && r < 6 && c >=0 && c < 7 && p == X || p == O
     * @param r - row that token is placed
     * @param c - column that token is placed in
     * @param p - token that is placed (X or O)
     * @return true if last token placed results in 4 in a row vertically
     * returns false otherwise
     * @post return true || return false
     */
    public boolean checkVertWin(int r, int c, char p) {
        int count = 0;
        for (int i = 0; i < MAX_X; i++) {
            if (array[i][c] == p)
                count++;
            else
                count = 0;
            if (count >= 4)
                return true;
        }
        return false;
    }

    /**
     * @pre r >= 0 && r < 6 && c >=0 && c < 7 && p == X || p == O
     * @param r - row of last token placed
     * @param c - column of last token placed
     * @param p - last token placed (X or O)
     * @return true if last token placed results in 4 in a row diagonally
     * returns false otherwise
     * @post return true || return false
     */
    public boolean checkDiagWin(int r, int c, char p) {
        // local variables
        int count = 0;
        int row = r;
        int col = c;

        // checks for a win diagonally up and to the right
        while (row < MAX_X && col < MAX_Y && array[row][col] == p) {
            count++;
            row++;
            col++;
        }
        if (count >= 4)
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
        if (count >= 4)
            return true;

        // resets count to zero and sets local variables to the
        // original row/column passed in
        count = 0;
        row = r;
        col = c;

        // checks for win diagonally up and to the left
        while (row >= 0 && col < MAX_Y && array[row][col] == p) {
            count++;
            row--;
            col++;
        }
        if (count >= 4)
            return true;

        // sets local variables down and to the right one element
        // this keeps count from counting the same element twice
        row = r + 1;
        col = c - 1;

        // checks for win diagonally down and to the right
        while (row < MAX_X && col >= 0 && array[row][col] == p) {
            count++;
            row++;
            col--;
        }
        if (count >= 4)
            return true;

        return false;
    }

    /**
     * @pre r >= 0 && r < MAX_X && c >=0 && c < MAX_Y
     * @param r - row of element passed in
     * @param c - column of element passed in
     * @return - token at row r and column c (X or O)
     * @post return X || return O || return ' '
     */
    public char whatsAtPos(int r, int c) {
        char pos = array[r][c];
        return pos;
    }

    /**
     * @pre variable calling toString is a String
     * @return - updated game board matrix as a String
     * @post variable calling toSTring contains updated matrix
     */
    public String toString() {
        String str = "";
        for (int i = MAX_X; i >= 0; i--) {
            for (int j = 0; j < MAX_Y; j++) {
                str += "|" + array[i][j];
            }
            str += "|" + "\n";
        }
        return str;
    }

    /**
     * @pre at least one token placed in matrix
     * @return - true if game board is full (tie), false otherwise
     * @post return true || return false
     */
    public boolean checkTie() {
        int count = 0;
        // checks top column of game board for X or O token
        for (int j = 0; j < MAX_Y; j++) {
            if (array[5][j] == 'X' || array[5][j] == 'O')
                count++;
            // resets count if a space is found on top column of matrix
            else
                count = 0;
            if (count == MAX_Y)
                return true;
        }
        return false;
    }
}
