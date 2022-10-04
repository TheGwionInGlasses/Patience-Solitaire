
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import carddecks.Card;
import carddecks.FaceDownCards;
import carddecks.FaceUpCards;
import score.Score;
import javafx.application.Application;
import javafx.stage.Stage;
import score.ScoreBoard;
import uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable;

/**
 * The Game of patience main class
 * @author Faisal Rezwan, Chris Loftus, Lynda Thomas and Gwion Hughes
 * @version 4.0
 */

public class Game extends Application {

    /**
     * Class properties
     */
    private CardTable cardTable;
    private Scanner scan = new Scanner(System.in);
    private FaceDownCards faceDownCards = new FaceDownCards();
    private FaceUpCards faceUpCards = new FaceUpCards();
    private ScoreBoard scoreBoard = new ScoreBoard();

    /**
     * Constructor function. Populates the deck of undrawn cards as soon as Game object is constructed.
     */
    public Game() {
        faceDownCards.populateDeck();
    }

    /**
     * Patience is run from this method.
     * @param stage = GUI
     */
    @Override
    public void start(Stage stage) {
        cardTable = new CardTable(stage);
        initialise();

        Runnable commandLineTask = () -> {

            String response;
            do {
                ArrayList<String> cardStrings = new ArrayList<>();
                printMenu();
                response = scan.nextLine().toUpperCase();
                switch(response) {
                    case "1":
                        System.out.println(faceDownCards);
                        break;
                    case "2":
                        faceDownCards.shuffle();
                        break;
                    case "3":
                        dealCard();
                        break;
                    case "4":
                        faceUpCards.smallMove();
                        break;
                    case "5":
                        faceUpCards.furthestMove();
                        break;
                    case "6":
                        specifyMove();
                        break;
                    case "7":
                        System.out.println(faceUpCards);
                        break;
                    case "8":
                        computerPlay(1);
                        break;
                    case "9":
                        computerPlayMore();
                        break;
                    case "10":
                        System.out.println(scoreBoard.toString());
                        break;
                    case "11":
                        faceDownCards.populateDeck();
                        break;
                    case "Q":
                        quit();
                        break;
                    default:
                        System.out.println("Try again.");
                }
                cardStrings = faceUpCards.stringOfFaceUpFileCards();
                cardTable.cardDisplay(cardStrings);
            } while (!(response.equals("Q")));
        };
        Thread commandLineThread = new Thread(commandLineTask);

        commandLineThread.start();
    }

    /**
     * Function to deal card from face down deck and add to the face up deck.
     */
    public void dealCard() {
        if (faceDownCards.getSizeOfDeck() != 0) {
            Card card = faceDownCards.dealCard();
            faceUpCards.addCard(card);
        } else {
            cardTable.allDone();
        }
    }

    /**
     * Function to "amalgamate" cards at two specified index's so long as the move is legal.
     */
    public void specifyMove() {
        int atIndex;
        int toIndex;

        try {
            System.out.println("Which card would you like to move? (Starting at 1)");
            atIndex = Integer.parseInt(scan.nextLine());
            System.out.println("Which card would you like to move to? (Starting at 1)");
            toIndex = Integer.parseInt(scan.nextLine());
            faceUpCards.amalgamate(atIndex - 1, toIndex - 1);
        } catch (NumberFormatException e) {
            System.out.println("Error: Expected number.");
        }
    }

    public void computerPlayMore() {
        int response;
        System.out.println("How times would you like the computer to play?");
        try {
            response = Integer.parseInt(scan.nextLine());
            if (response < 0) {
                response = 0;
            } else if (response > 100) {
                response = 100;
            }
            computerPlay(response);
        } catch (NumberFormatException e) {
            System.out.println("Error: Expected number.");
        }
    }

    /**
     * Calls the game to make a move for the player. If there is no match to make, it will add a card.
     */
    public void computerPlay(int times) {
        int tracker;
        for (int counter = 0; counter < times; counter++) {
            tracker = faceUpCards.getSizeOfDeck();
            faceUpCards.playForMe();
            if (tracker == faceUpCards.getSizeOfDeck()) {
                dealCard();
            }
        }
    }

    /**
     * Function prints out the menu.
     */
    public void printMenu() {
        System.out.println("1 - Print the pack out");
        System.out.println("2 - Shuffle");
        System.out.println("3 - Deal a card");
        System.out.println("4 - Make a move, move last pile onto previous one");
        System.out.println("5 - Make a move, move last pile back over 2 piles");
        System.out.println("6 - Amalgamate piles in the middle by giving number of position");
        System.out.println("7 - Print the displayed cards on the command line");
        System.out.println("8 - Play for me once");
        System.out.println("9 - Play for me more than once");
        System.out.println("10 - Display top 10 results");
        System.out.println("11 - Add an extra set of playing cards to the deck");
        System.out.println("Q - Quit");
    }

    /**
     * Function to load previous scores onto the scoreboard if there are any.
     */
    public void initialise() {
        try {
            scoreBoard.load();
        } catch (FileNotFoundException e){
            System.err.println("Couldn't find Scoreboard.txt");
        } catch (IOException e) {
            System.err.println("Problem when trying to load in from Scoreboard.");
        }
    }

    /**
     * Function to log score before quitting proper.
     */
    public void quit() {
        System.out.println("Please input name for score.");
        String name = scan.nextLine();
        int num = faceDownCards.getSizeOfDeck() + faceUpCards.getSizeOfDeck();
        Score score = new Score(num, name);
        scoreBoard.addScore(score);
        scoreBoard.sortScore();
        try {
            scoreBoard.save();
        } catch (IOException e) {
            System.err.println("Problem when trying to save to Scoreboard.");
        }
    }

    /**
     * This is the main method.
     * @param args
     */
    public static void main(String args[]) {
        Application.launch(args);
    }
}
