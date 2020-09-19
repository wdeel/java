
import java.util.Random;
public class DeckOfCards {

    public static final int NCARDS = 52;

    private String suit[] = {"Clubs", "Spades", "Hearts", "Diamonds"};
    private String value[] = {"Two", "Three", "Four", "Five", "Six", "Seven",
                              "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

    private Card[] deckOfCards;    
    private static int counter;
    public Card currentCard;
    

    public DeckOfCards() {
        counter = 0;

        int j=0;
        deckOfCards = new Card[NCARDS];

        for (int i=0; i<4; i++) {
            for (int k=0; k<13; k++) {
                if (k >= 8 && k < 12)
                    deckOfCards[j++] = new Card(suit[i], value[k], 10);
                else if (k == 12)
                    deckOfCards[j++] = new Card(suit[i], value[k], 11);
                else
                    deckOfCards[j++] = new Card(suit[i], value[k], k+2);
            }
        }
        currentCard = deckOfCards[0];
    }

    public void printDeck() {
        for (int j=0; j<NCARDS; j++) {
            System.out.println(deckOfCards[j].value + " of " + deckOfCards[j].suit + " " + deckOfCards[j].rank);
        }
    }

    public void shuffleDeck() {

        Card temp = new Card();

        Random rand = new Random();
        for (int i=0; i<10000; i++) {
            int num1 = rand.nextInt(52);
            int num2 = rand.nextInt(52);
            while (num1 == num2) {
                num1 = rand.nextInt(52);
            }
            temp = deckOfCards[num1];
            deckOfCards[num1] = deckOfCards[num2];
            deckOfCards[num2] = temp;
        }
    }

    public Card dealCard() {
        currentCard = deckOfCards[counter];
        counter++;
        return currentCard;
    }
}