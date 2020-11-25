/* Wesley Lewis
 * CPSC 2150
 * Section 01
 * Homework #4
 * Due: 18 November, 2018 @ 11:59pm
 */
package cpsc2150.connectX;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by wdlewis on 11/15/18.
 */
public class TestIGameBoard {

    // factory method
    private IGameBoard factory(int c, int r, int numWin) {
        return new GameBoard (c, r, numWin);
    }

    // private helper method that returns string representation of game board
    private String stringRep(char arr[][], int cols, int rows) {
        String str = "";
        // prints 0..number of columns at top of game board
        for (int i = 0; i < cols; i++) {
            if (i < 10)
                str += "| " + i;
            else
                str += "|" + i;
        }
        str += "|" + "\n";
        // prints contents of 2D array
        for (int i = rows-1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                str += "| " + arr[i][j];
            }
            str += "|" + "\n";
        }
        return str;
    }

    // Constructor
    @Test
    public void testConstructor_min_cols_rows_numToWin() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testConstructor_max_cols_rows_numToWin() {
        int cols = 100;
        int rows = 100;
        int numToWin = 25;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testConstructor_random_cols_rows_numToWin() {
        int cols = 10;
        int rows = 25;
        int numToWin = 6;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // CheckIfFree
    @Test
    public void testCheckIfFree_true_min() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        assertTrue(gb.checkIfFree(1));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckIfFree_true_max() {
        int cols = 8;
        int rows = 6;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows-1; i++) {
            gb.placeToken('X', 1);
            expectedArr[i][1] = 'X';
        }

        assertTrue(gb.checkIfFree(1));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckIfFree_false() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertFalse(gb.checkIfFree(2));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // CheckHorizWin
    @Test
    public void testCheckHorizWin_true_last_marker() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<3; i++) {
            gb.placeToken('X', i);
            expectedArr[0][i] = 'X';
        }

        assertTrue(gb.checkHorizWin(0, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckHorizWin_false_() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<2; i++) {
            gb.placeToken('X', i);
            expectedArr[0][i] = 'X';
        }

        assertFalse(gb.checkHorizWin(0, 1, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckHorizWin_true_mid() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<3; i++) {
            gb.placeToken('X', i);
            expectedArr[0][i] = 'X';
        }

        assertTrue(gb.checkHorizWin(0, 1, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckHorizWin_true_left() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<3; i++) {
            gb.placeToken('X', i);
            expectedArr[0][i] = 'X';
        }

        assertTrue(gb.checkHorizWin(0, 0, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckHorizWin_false_alternating() {
        int cols = 8;
        int rows = 6;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<6; i+=2) {
            gb.placeToken('X', i);
            expectedArr[0][i] = 'X';
        }
        for (int i=1; i<6; i+=2) {
            gb.placeToken('O', i);
            expectedArr[0][i] = 'O';
        }

        assertFalse(gb.checkHorizWin(0, 6, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // CheckVertWin
    @Test
    public void testCheckVertWin_true_min_num_win() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertTrue(gb.checkVertWin(2, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckVertWin_false_min_num_win() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<2; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertFalse(gb.checkVertWin(1, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckVertWin_true_max_num_win() {
        int cols = 100;
        int rows = 100;
        int numToWin = 25;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<25; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertTrue(gb.checkVertWin(25, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckVertWin_false_max_num_win() {
        int cols = 100;
        int rows = 100;
        int numToWin = 25;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<24; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertFalse(gb.checkVertWin(24, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckVertWin_true_top() {
        int cols = 25;
        int rows = 25;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);
        for (int i=0; i<11; i++) {
            gb.placeToken('O', 2);
            gb.placeToken('X', 2);
        }

        for (int i=0; i<21; i+=2)
            expectedArr[i][2] = 'O';
        for (int i=1; i<21; i+=2)
            expectedArr[i][2] = 'X';

        for (int i=21; i<rows; i++) {
            gb.placeToken('X', 2);
            expectedArr[i][2] = 'X';
        }

        assertTrue(gb.checkVertWin(24, 2, 'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // CheckDiagWin
    @Test
    public void testCheckDiagWin_up_and_right_top() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][1] = 'X';
        expectedArr[0][2] = 'O';
        expectedArr[0][3] = 'O';
        expectedArr[0][4] = 'O';
        expectedArr[1][2] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[1][3] = 'X';
        expectedArr[1][4] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[2][4] = 'O';
        expectedArr[3][4] = 'X';

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);

        assertTrue(gb.checkDiagWin(3,4,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_up_and_right_bot() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][1] = 'X';
        expectedArr[0][2] = 'O';
        expectedArr[0][3] = 'O';
        expectedArr[0][4] = 'O';
        expectedArr[1][2] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[1][3] = 'X';
        expectedArr[1][4] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[2][4] = 'O';
        expectedArr[3][4] = 'X';

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);

        assertTrue(gb.checkDiagWin(0,1,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_up_and_right_mid() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][1] = 'X';
        expectedArr[0][2] = 'O';
        expectedArr[0][3] = 'O';
        expectedArr[0][4] = 'O';
        expectedArr[1][2] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[1][3] = 'X';
        expectedArr[1][4] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[2][4] = 'O';
        expectedArr[3][4] = 'X';

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);

        assertTrue(gb.checkDiagWin(1,2,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_up_and_left_top() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][4] = 'X';
        expectedArr[0][3] = 'O';
        expectedArr[0][2] = 'O';
        expectedArr[0][1] = 'O';
        expectedArr[1][3] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[1][1] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[2][1] = 'O';
        expectedArr[3][1] = 'X';

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);

        assertTrue(gb.checkDiagWin(3,1,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_up_and_left_bot() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][4] = 'X';
        expectedArr[0][3] = 'O';
        expectedArr[0][2] = 'O';
        expectedArr[0][1] = 'O';
        expectedArr[1][3] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[1][1] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[2][1] = 'O';
        expectedArr[3][1] = 'X';

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);

        assertTrue(gb.checkDiagWin(0,4,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_and_left_mid() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][4] = 'X';
        expectedArr[0][3] = 'O';
        expectedArr[0][2] = 'O';
        expectedArr[0][1] = 'O';
        expectedArr[1][3] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[1][1] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[2][1] = 'O';
        expectedArr[3][1] = 'X';

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);

        assertTrue(gb.checkDiagWin(0,4,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_false_up_and_left() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][4] = 'X';
        expectedArr[0][3] = 'O';
        expectedArr[0][2] = 'O';
        expectedArr[0][1] = 'O';
        expectedArr[1][3] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[1][1] = 'X';
        expectedArr[2][2] = 'X';
        expectedArr[2][1] = 'O';
        expectedArr[3][1] = 'O';

        gb.placeToken('X', 4);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);

        assertFalse(gb.checkDiagWin(0,4,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckDiagWin_8() {
        int cols = 6;
        int rows = 8;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        expectedArr[0][1] = 'X';
        expectedArr[0][2] = 'O';
        expectedArr[0][3] = 'O';
        expectedArr[0][4] = 'O';
        expectedArr[1][2] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[1][3] = 'X';
        expectedArr[1][4] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[2][4] = 'O';
        expectedArr[3][4] = 'O';

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('O', 4);

        assertFalse(gb.checkDiagWin(2,3,'X'));
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // CheckTie
    @Test
    public void testCheckTie_true_min_rows_cols() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<cols; i++)
            for (int j=0; j<rows; j++) {
                expectedArr[i][j] = 'O';
                gb.placeToken('O', j);
            }

        assertTrue(gb.checkTie());
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckTie_true_max_rows_cols() {
        int cols = 100;
        int rows = 100;
        int numToWin = 25;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<cols; i++)
            for (int j=0; j<rows; j++) {
                expectedArr[i][j] = 'X';
                gb.placeToken('X', j);
            }

        assertTrue(gb.checkTie());
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckTie_false_min_rows_cols() {
        int cols = 3;
        int rows = 3;
        int numToWin = 3;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows/2; i++)
            for (int j=0; j<cols; j++) {
                expectedArr[i][j] = 'O';
                gb.placeToken('O', j);
            }

        assertFalse(gb.checkTie());
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testCheckTie_false_max_rows_cols() {
        int cols = 100;
        int rows = 100;
        int numToWin = 25;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows/2; i++)
            for (int j=0; j<cols; j++) {
                expectedArr[i][j] = 'O';
                gb.placeToken('O', j);
            }

        assertFalse(gb.checkTie());
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // WhatsAtPos
    @Test
    public void testWhatsAtPos_bot_left() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 0);
        expectedArr[0][0] = 'X';

        assertTrue(gb.whatsAtPos(0,0) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_bot_right() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 3);
        expectedArr[0][3] = 'X';

        assertTrue(gb.whatsAtPos(0,3) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_top_right() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);


        expectedArr[0][3] = 'X';
        expectedArr[1][3] = 'X';
        expectedArr[2][3] = 'X';
        expectedArr[3][3] = 'X';

        assertTrue(gb.whatsAtPos(3,3) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_top_left() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);


        expectedArr[0][0] = 'X';
        expectedArr[1][0] = 'X';
        expectedArr[2][0] = 'X';
        expectedArr[3][0] = 'X';

        assertTrue(gb.whatsAtPos(3,0) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_full() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++) {
                expectedArr[i][j] = 'X';
                gb.placeToken('X', j);
            }

        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                assertTrue(gb.whatsAtPos(i, j) == 'X');

        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_empty() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                assertTrue(gb.whatsAtPos(i, j) == ' ');

        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testWhatsAtPos_middle() {
        int cols = 5;
        int rows = 5;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);

        expectedArr[0][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[2][2] = 'X';

        assertTrue(gb.whatsAtPos(2, 2) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }

    // PlaceToken
    @Test
    public void testPlaceToken_bot_left() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 0);

        expectedArr[0][0] = 'X';

        assertTrue(gb.whatsAtPos(0, 0) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testPlaceToken_bot_right() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        gb.placeToken('X', 3);

        expectedArr[0][3] = 'X';

        assertTrue(gb.whatsAtPos(0, 3) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testPlaceToken_top_left() {
        int cols = 4;
        int rows = 4;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows; i++) {
            gb.placeToken('X', 0);
            expectedArr[i][0] = 'X';
        }

        assertTrue(gb.whatsAtPos(3, 0) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testPlaceToken_top_right() {
        int cols = 5;
        int rows = 5;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);

        for (int i=0; i<rows; i++) {
            gb.placeToken('X', 3);
            expectedArr[i][3] = 'X';
        }

        assertTrue(gb.whatsAtPos(3, 3) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
    @Test
    public void testPlaceToken_middle() {
        int cols = 5;
        int rows = 5;
        int numToWin = 4;
        char expectedArr[][] = new char [rows][cols];
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                expectedArr[i][j] = ' ';

        IGameBoard gb = factory(cols, rows, numToWin);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);

        expectedArr[0][2] = 'X';
        expectedArr[1][2] = 'X';
        expectedArr[2][2] = 'X';

        assertTrue(gb.whatsAtPos(2, 2) == 'X');
        assertEquals(gb.toString(), stringRep(expectedArr, cols, rows));
    }
}
