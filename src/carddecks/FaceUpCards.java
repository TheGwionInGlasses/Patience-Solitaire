package carddecks;

import java.util.ArrayList;

/**
 * carddecks.FaceUpCards is for handling cards that have been drawn. Inherits from carddecks.DeckCards.java.
 * @author Gwion
 * @version 1.0
 */
public class FaceUpCards extends DeckCards{

    /**
     * Constructor
     */
    public FaceUpCards() {}

    /**
     * Add a card to the cards collection
     * @param card new card
     */
    public void addCard(Card card) {
        cards.add(card);
        sizeOfDeck++;
    }

    /**
     * Move the last card in the collection onto the previous card if the move is legal.
     */
    public void smallMove() {
        int indexConversion = sizeOfDeck - 1;

        if (isMove1Possible(indexConversion, indexConversion - 1)) {
            makeAMoveOne(indexConversion - 1);
        } else {
            System.out.println("Invalid move.");
        }
    }

    /**
     * Move the last card in the collection onto the third previous card.
     */
    public void furthestMove() {
        int indexConversion = sizeOfDeck - 1;

        if (isMove2Possible(indexConversion, indexConversion - 3)) {
            makeAMoveTwo(indexConversion);
        } else {
            System.out.println("Invalid move.");
        }
    }

    /**
     * A function to make a specific move from a specified position in the deck if legal.
     * @param atIndex The index of the card the player wants to move
     * @param toIndex The index of the card the player wants to move a card to
     */
    public void amalgamate(int atIndex, int toIndex) {
        if(isMove2Possible(atIndex, toIndex)) {
            makeAMoveTwo(atIndex);
        } else if (isMove1Possible(atIndex, toIndex)) {
            makeAMoveOne(toIndex);
        } else {
            return;
        }
    }

    /**
     * Checks for a match to make closest to the beginning.
     * Is biased towards making the "furthest" match.
     */
    public void playForMe() {
        int counter = 1;
        boolean checker = true;
        while (!(counter > sizeOfDeck) && checker) {
            if (isMove2Possible(counter, counter - 3)) {
                makeAMoveTwo(counter);
                checker = false;
            } else if (isMove1Possible(counter, counter - 1)) {
                makeAMoveOne(counter - 1);
                checker = false;
            }
            counter++;
        }
    }

    /**
     * Moves one card onto the previous card by removing the previous card.
     * @param i the index of the card to be removed.
     */
    private void makeAMoveOne(int i) {
        cards.remove(i);
        sizeOfDeck--;
    }

    /**
     * Moves one card over two other cards.
     * @param i the index of the card to be moved.
     */
    private void makeAMoveTwo(int i) {
        cards.set(i-3, cards.get(i));
        cards.remove(i);
        sizeOfDeck--;
    }

    /**
     * Checks that moving a card onto the previous card is a legal move by comparing their rank and suit.
     * @param atIndex The card to move
     * @param toIndex The position to move the card to
     * @return true if the move is legal, false if the move is not.
     */
    private boolean isMove1Possible(int atIndex, int toIndex) {
        boolean result = false;

        if(atIndex - toIndex == 1 && (atIndex < sizeOfDeck && toIndex > -1)) {

            Card card1 = cards.get(atIndex);
            Card card2 = cards.get(toIndex);

            if (card1.equals(card2)) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checks that moving a card onto the third previous card is a legal move by comparing their rank and suit.
     * @param atIndex The card to move
     * @param toIndex The position to move the card to
     * @return true if the move is legal, false if the move is not.
     */
    private boolean isMove2Possible(int atIndex, int toIndex) {
        boolean result = false;

        if(atIndex - toIndex == 3 && (atIndex < sizeOfDeck && toIndex > -1)) {

            Card card1 = cards.get(atIndex);
            Card card2 = cards.get(toIndex);

            if (card1.equals(card2)) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    /**
     * Produces a String arraylist of file names for cards in the card collections
     * @return String arraylist of file names
     */
    public ArrayList<String> stringOfFaceUpFileCards() {
        ArrayList<String> list = new ArrayList<>();
        for (Card c : cards) {
            list.add(c.cardToFile());
        }
        return list;
    }
}
