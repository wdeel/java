/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #3
 * Due: 28 October, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Correspondence: number_of_cols = boardCols
 *                 number_of_rows = boardRows
 *                 number_to_win = numWin
 *
 * @invariants 3 <= boardRows, boardCols < 100 and
 *             3 <= numWin < 25
 */

public class GameBoardMem implements IGameBoard {

    private static int boardRows, boardCols, numWin;
    private static List<Character> list;
    private static Map<Integer, List<Character>> myMap;

    /**
     * @pre MIN_VAL <= cols < MAX_VAL &&
     *      MIN_VAL <= rows < MAX_VAL &&
     *      MIN_VAL <= numToWin <= MAX_TO_WIN
     * @param cols - number of keys in map
     * @param rows - number of values in List of map
     * @param numToWin - number in a row needed to win the game
     * @post private variables set to values passed in and map
     * instantiated
     */
    GameBoardMem(int cols, int rows, int numToWin) {

        boardRows = rows;
        boardCols = cols;
        numWin = numToWin;
        myMap = new HashMap<>();
    }

    // places token p in lowest column with a space ' '
    public void placeToken(char p, int c) {
        // if map doesn't contain key c, create one
        if (!myMap.containsKey(c))
            myMap.put(c, new ArrayList<>());
        // add key-value pair to map
        myMap.get(c).add(p);
    }

    // places token 'p' in list value of key c
    public char whatsAtPos(int r, int c) {
        // sets list to list of map at key c
        list = myMap.get(c);

        if (myMap.get(c) == null)
            return ' ';
        else if (r >= list.size())
            return ' ';
        else
            return list.get(r);
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
        // prints contents of map
        for (int i = getNumRows()-1; i >= 0; i--) {
            for (int j = 0; j < getNumColumns(); j++) {
                str += "| ";
                if (!myMap.containsKey(j))
                    str += " ";
                else {
                    list = myMap.get(j);
                    if (i < list.size())
                        str += list.get(i);
                    else
                        str += " ";
                }
            }
            str += "|" + "\n";
        }
        return str;
    }

    // returns number of rows of game board
    public int getNumRows() {
        return boardRows;
    }

    // returns number of columns of game board
    public int getNumColumns() {
        return boardCols;
    }

    // returns how many in a row to win
    public int getNumToWin() {
        return numWin;
    }
}


