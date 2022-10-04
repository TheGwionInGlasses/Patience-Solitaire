package carddecks;

import java.util.ArrayList;

/**
 * An abstract superclass for a generic Deck of cards.
 * @author Gwion
 * @version 1.0
 */
abstract class DeckCards {

    /**
     * Class properties
     */
    protected ArrayList<Card> cards = new ArrayList<>();
    protected int sizeOfDeck = 0;

    /**
     * Class constructor
     */
    public DeckCards() {
    }

    /**
     * A getter method for sizeOfDeck
     * @return Size of card Collection
     */
    public int getSizeOfDeck() {
        return sizeOfDeck;
    }

    /**
     * A function to print a description of the deck
     * @return contents of the card collection.
     */
    @Override
    public String toString() {
        StringBuilder bobTheStringBuilder = new StringBuilder();
        for (Card card : cards) {
            bobTheStringBuilder.append(card);
            bobTheStringBuilder.append(" ");
        }
        return bobTheStringBuilder.toString();
    }
}
