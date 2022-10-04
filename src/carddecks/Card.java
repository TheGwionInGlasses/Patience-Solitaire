package carddecks;

/**
 * A class template for a card.
 * @author Gwion
 * @version 1.0
 */
public class Card {

    /**
     * Class properties
     */
    private int suit;
    private int rank;
    private static final String suits[] = {"h", "d", "c", "s"};
    private static final String ranks[] = {"a", "2", "3", "4", "5", "6", "7", "8", "9", "t", "j", "q", "k"};

    /**
     * A constructor method for the carddecks.Card class
     * @param num1 = suit
     * @param num2 = rank
     */
    public Card (int num1, int num2) {
        suit = num1;
        rank = num2;
    }

    /**
     * Produces a string for a card file location
     * @return
     */
    public String cardToFile() {
        StringBuilder bobTheStringBuilder = new StringBuilder();
        bobTheStringBuilder.append(toString());
        bobTheStringBuilder.append(".gif");
        return bobTheStringBuilder.toString();
    }

    /**
     * Produces a description of the card object
     * @return description of card
     */
    @Override
    public String toString() {
        StringBuilder bobTheStringBuilder = new StringBuilder();
        bobTheStringBuilder.append(ranks[rank]);
        bobTheStringBuilder.append(suits[suit]);
        return bobTheStringBuilder.toString();
    }

    /**
     * A function to compare the suit and rank of a given card
     * @param o A card object
     * @return True if suit and rank match, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit || rank == card.rank;
    }

}
