package score;

/**
 * The Score.Score class. Implements comparable interface.
 * @author Gwion
 * @version 1.0
 */
public class Score implements Comparable<Score>{

    /**
     * Class properties
     */
    private int score;
    private String name;

    /**
     * Constructor for score object.
     * @param num = Number of cards left to draw
     * @param string = Name of player
     */
    public Score(int num, String string) {
        score = num;
        name = string;
    }

    /**
     * A getter method to get Score.Score
     * @return Score.Score of player
     */
    public int getScore() {
        return score;
    }

    /**
     * A getter method to get the player name
     * @return Player's name, owner of the Score.Score
     */
    public String getName() {
        return name;
    }

    /**
     * A compare function to help sort by lowest score.
     * @param sToCompare
     * @return lowest score
     */
    @Override
    public int compareTo(Score sToCompare) {
        return Integer.compare(sToCompare.getScore(), this.score);
    }

    /**
     * A toString method to return a description of the Score.Score
     * @return Name: (Player name). Score.Score: (Number of cards left)
     */
    @Override
    public String toString() {
        return "Name: " + name +
                ". " + "Score.Score: " + score;
    }
}
