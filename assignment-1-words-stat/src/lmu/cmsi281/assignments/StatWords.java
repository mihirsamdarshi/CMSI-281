package lmu.cmsi281.assignments;

public class StatWords {
    public static void main(String[] args) {
        String input1 = "Dear class, welcome to CMSI281!";
        String input2 = "Here is a test case for the first assignment";
        String input3 = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?"
                + "He would chuck, he would, as much as he could, and chuck as much wood,"
                + "as a woodchuck would if a woodchuck could chuck wood.";

        SimpleWordsStat simpleWordsStat = new SimpleWordsStat();
        AccumulatedWordsStat accumulatedWordsStat = new AccumulatedWordsStat();
        MultiFunctionWordsStat multiFunctionWordsStat = new MultiFunctionWordsStat();

        System.out.println("SimpleWordsStat:");
        simpleWordsStat.update(input1);
        simpleWordsStat.showStat();
        simpleWordsStat.update(input2);
        simpleWordsStat.showStat();
        simpleWordsStat.update(input3);
        simpleWordsStat.showStat();

        System.out.println("AccumulatedWordsStat:");
        accumulatedWordsStat.update(input1);
        accumulatedWordsStat.showStat();
        accumulatedWordsStat.update(input2);
        accumulatedWordsStat.showStat();
        accumulatedWordsStat.update(input3);
        accumulatedWordsStat.showStat();

        System.out.println("MultiFunctionWordsStat:");
        multiFunctionWordsStat.update(input1);
        multiFunctionWordsStat.showStat();
        multiFunctionWordsStat.update(input2);
        multiFunctionWordsStat.showStat();
        multiFunctionWordsStat.update(input3);
        multiFunctionWordsStat.showStat();
    }
}
