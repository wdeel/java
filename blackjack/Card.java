public class Card {
    public String suit;
    public String value;
    public int rank;

    public Card () {
        suit = "";
        value = "";
    }
    public Card(String s, String v, int r) {
        suit = s;
        value = v;
        rank = r;
    }
}