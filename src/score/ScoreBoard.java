package score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * The Score.ScoreBoard class through which scores are managed
 * @author Gwion
 * @version 1.0
 */
public class ScoreBoard {

    /**
     * Class properties.
     */
    private ArrayList<Score> scores = new ArrayList<>();

    /**
     * Score.ScoreBoard construction method
     */
    public ScoreBoard() {
    }

    /**
     * Method to update Scoreboard with new score.
     * @param score = New score to add
     */
    public void addScore(Score score) {
        scores.add(score);
    }

    /**
     * Method to sort score. Lowers score = higher placement.
     */
    public void sortScore() {
        Collections.sort(scores, Collections.reverseOrder());
    }

    /**
     * Function to load scores onto the Scoreboard so long as there is still lines left to read on Scoreboard.txt.
     * @throws IOException
     */
    public void load() throws IOException {
        try (FileReader fr = new FileReader("Scoreboard.txt");
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br)) {

            infile.useDelimiter("\r?\n|\r");

            while (infile.hasNext()) {
                int num = infile.nextInt();
                infile.nextLine();
                String string = infile.nextLine();
                Score score = new Score(num, string);
                scores.add(score);
            }
        }
        sortScore();
    }

    /**
     * Function to save top 10 scores into Scoreboard.txt
     * @throws IOException
     */
    public void save() throws IOException {
        boolean finished = false;
        int counter = 0;
        try (FileWriter fw = new FileWriter("Scoreboard.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);) {

            while(!finished) {
                for (Score score : scores) {

                    outfile.println(score.getScore());
                    outfile.println(score.getName());
                    counter++;
                    if (counter == 10 || counter == scores.size()) {
                        finished = true;
                    }
                }
            }
        }
    }

    /**
     * A toString that uses StringBuilder to print a list of the top 10 scores.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder bobTheStringBuilder = new StringBuilder();
        int counter = 0;
        while (counter < scores.size() && counter < 10) {
            bobTheStringBuilder.append(counter+1);
            bobTheStringBuilder.append(": ");
            bobTheStringBuilder.append(scores.get(counter));
            bobTheStringBuilder.append("\n");
            counter++;
        }
        return bobTheStringBuilder.toString();
    }
}
