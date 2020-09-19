
class BlackJack {
    public static void main(String args []) {

        DeckOfCards Deck = new DeckOfCards();

        Deck.shuffleDeck();

        Deck.printDeck();

        Player player1 = new Player();
        Card playerCard = Deck.dealCard();
        player1.addToHand(playerCard);
        System.out.println("Player has " + playerCard.value + " of " + playerCard.suit);

        Card secondCard = Deck.dealCard();
        player1.addToHand(playerCard);
        System.out.println("Player has " + secondCard.value + " of " + secondCard.suit);

        player1.showHand();
    }
}