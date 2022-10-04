package carddecks;

import java.util.Collections;

/**
 * carddecks.FaceDownCards class is for handling cards that haven't been drawn yet. Inherits carddecks.DeckCards.java.
 * @author Gwion
 * @version 1.0
 */
public class FaceDownCards extends DeckCards {

    /**
     * Constructor
     */
    public FaceDownCards() {
    }

    /**
     * A function to populate the card collection with 52 unique playing cards.
     */
    public void populateDeck() {
        for (int suit = 0; suit < 4; suit++){
            for (int rank = 0; rank < 13; rank++){
                Card card = new Card(suit, rank);
                cards.add(card);
                sizeOfDeck++;
            }
        }
        return;
    }

    /**
     * A function to randomly shuffle contents of the card collection.(Hopefully.)
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Remove the first card in the card collection.
     * @return carddecks.Card at index 0 in cards.
     */
    public Card dealCard() {
        Card card = cards.remove(0);
        sizeOfDeck--;

        return card;
    }
}
