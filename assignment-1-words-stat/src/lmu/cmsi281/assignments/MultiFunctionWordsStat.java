package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 1
 * 
 * @author <Samdarshi, Mihir>
 *
 */
public class MultiFunctionWordsStat extends AccumulatedWordsStat {

    int mostFrequentWordIndex;
    int leastFrequentWordIndex;

    public MultiFunctionWordsStat() {
        super();
    }

    /**
     * Finds the index of the most frequently appeared word and sets the
     * mostFreqentWordIndex, if there are no elements then the mostFrequentWordIndex
     * will be -1 If there are multiple words with the same most frequent count,
     * select the one whose index is least
     */
    protected void findMostFrequent() {
        if (words.size() == 0) {
            mostFrequentWordIndex = -1;
        } else {
            int comparisonValue = counts.get(0);
            for (int i = 1; i < counts.size(); i++) {
                if (counts.get(i) > comparisonValue) {
                    comparisonValue = counts.get(i);
                }
            }
            mostFrequentWordIndex = counts.indexOf(comparisonValue);
        }
    }

    /**
     * Finds the index of the least frequently appeared word and sets the
     * leastFreqentWordIndexif there are no elements then the leastFrequentWordIndex
     * will be -1 If there are multiple words with the same least frequent count,
     * select the one whose index is least
     */
    protected void findLeastFrequent() {
        if (words.size() == 0) {
            leastFrequentWordIndex = -1;
        } else {
            int comparisonValue = counts.get(0);
            for (int i = 1; i < counts.size(); i++) {
                if (counts.get(i) < comparisonValue) {
                    comparisonValue = counts.get(i);
                }
            }
            leastFrequentWordIndex = counts.indexOf(comparisonValue);
        }
    }

    /**
     * Updates the words and counts lists with an accumulated total for each word
     * and its count and finds the most and least frequent words
     */
    @Override
    public void update(String input) {
        // no clear() because both lists are accumulated
        updateStat(input);

        findMostFrequent();
        findLeastFrequent();
    }

    /**
     * Prints a formatted text showing each word's usage in counts along with the
     * most and least frequently used words and their counts if available
     */
    @Override
    public void showStat() {
        super.showStat();
        if (mostFrequentWordIndex != -1 && leastFrequentWordIndex != -1) {
            System.out.println("Most Frequently Used: " + words.get(mostFrequentWordIndex) + " used "
                    + counts.get(mostFrequentWordIndex) + " times");
            System.out.println("Least Frequently Used: " + words.get(leastFrequentWordIndex) + " used "
                    + counts.get(leastFrequentWordIndex) + " times");
        }
    }
}
