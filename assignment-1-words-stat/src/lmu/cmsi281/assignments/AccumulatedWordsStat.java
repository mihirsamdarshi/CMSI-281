package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 1
 * 
 * @author <Samdarshi, Mihir>
 *
 */
public class AccumulatedWordsStat extends SimpleWordsStat {

    public AccumulatedWordsStat() {
        super();
    }

    /**
     * Updates the words and counts lists with an accumulated total for each word
     * and its count
     */
    @Override
    public void update(String input) {
        // no clear() because both lists are accumulated
        updateStat(input);
    }
}
