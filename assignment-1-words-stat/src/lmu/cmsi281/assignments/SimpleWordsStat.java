package lmu.cmsi281.assignments;

import java.util.ArrayList;

/**
 * CMSI Assignment 1
 * 
 * @author <Samdarshi, Mihir>
 *
 */
public class SimpleWordsStat implements WordsStat {

    private final String SPLIT_REGEX = "\\W+";
    private final String EMPTY = "";

    ArrayList<String> words = new ArrayList<String>();
    ArrayList<Integer> counts = new ArrayList<Integer>();

    public SimpleWordsStat() {
    }

    /**
     * Tokenizes the input String
     *
     * @param input
     * @return String array containing the tokenized input
     */
    protected String[] parseString(String input) {
        if (input == null || input.equals(EMPTY)) {
            return new String[0];
        }
        return input.split(SPLIT_REGEX);
    }

    /**
     * Clears the words and counts lists
     */
    protected void clear() {
        words.clear();
        counts.clear();
    }

    /**
     * Updates the words and counts lists where the count of each word in words at
     * index i corresponds to the same index in counts
     */
    protected void updateStat(String input) {
        String[] splitInput = parseString(input);
        for (int i = 0; i < splitInput.length; i++) {
            if (words.contains(splitInput[i])) {
                int index = words.indexOf(splitInput[i]);
                counts.set(index, counts.get(index) + 1);
            } else {
                words.add(splitInput[i]);
                counts.add(1);
            }
        }
    }

    /**
     * Clears the existing stats contained within words and counts and then
     * recompute the stats
     */
    @Override
    public void update(String input) {
        clear();
        updateStat(input);
    }

    /**
     * Prints a formatted text showing each word's usage in counts
     */
    @Override
    public void showStat() {
        System.out.println("Words Usage:");

        if (words.size() == 0) {
            System.out.println("No stats found!");
        }

        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i) + " : " + counts.get(i));
        }
    }
}
