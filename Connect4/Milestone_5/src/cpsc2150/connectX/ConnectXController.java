package cpsc2150.connectX;



/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    //I want to keep this example simple
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};

   int numPlayers;
   static int turn = 0;
   static boolean newBoard = false;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {

        int row = 0;
        String message = "";

        // finds lowest row that is empty in column of game board
        // and assigns it to row variable
        for (int i=0; i<curGame.getNumRows(); i++) {
            if (curGame.whatsAtPos(i, col) == ' ') {
                row = i;
                i = curGame.getNumRows();
            }
        }

        // sends program back to setup stage and resets static
        // variable "newBoard" to false
        if (newBoard) {

            //turn = 0;
            newGame();
            newBoard = false;
            turn = 0;
            return;
        }
        // displays error message if column is full and allows user to
        // input a different column
        if (!curGame.checkIfFree(col))
            screen.setMessage("Column is full, please choose a different column, player " + players[turn]);
        else {
            // places token on IGameBoard object's game board
            curGame.placeToken(players[turn], col);
            // checks for win and sets static bool variable
            // to true if it is
            if (curGame.checkForWin(col)) {
                message += ("Player " + players[turn] + " won!  Press any button to play again");
                newBoard = true;
            }
            // checks for tie and sets static bool variable
            // to true if it is
            if (curGame.checkTie()) {
                message += ("There was a tie!  Press any button to play again");
                newBoard = true;
            }
            // places marker on screen object for user to see
            screen.setMarker(row, col, players[turn++]);
            // resets turn if it's greater than or equal to number of players
            if (turn >= numPlayers) turn = 0;
            // displays which users turn it is
            screen.setMessage("It is " + players[turn] + "'s turn. ");
        }
        // overrides screen message if there's a win or tie instead of displaying
        // who's turn it is
        if (message != "")
            screen.setMessage(message);
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
