import java.util.Vector;
import java.util.Iterator;

public class Player {

    private Vector<Card> hand = new Vector<Card>();

    public Player() {

    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public void showHand() {
        /*
        while (hand.hasNext()) {
            System.out.println("Player has:\n " + hand[0].value + " of " + hand.suit);
        }
        return;
    }
    */
}