package inf.andrzej.wieclawski;

import inf.andrzej.wieclawski.services.TestOfOperations;

import java.text.DecimalFormat;
import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Long timeOfListOperations = TestOfOperations.testOfListOperations();
        Long timeOfMapOperations = TestOfOperations.testOfMapOperations();

        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingList. \n"
                , timeOfListOperations);

        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingMap. \n"
                , timeOfMapOperations);

        if (timeOfListOperations > timeOfMapOperations) {
            System.out.printf("timeOfListOperations IS LONGER than timeOfMapOperations. %s percent of timeOfMapOperations\n"
                    , new DecimalFormat("#0.00").format(((double) timeOfListOperations / (double) timeOfMapOperations) * 100.00));
        } else {
            System.out.printf("timeOfListOperations IS SHORTER than timeOfMapOperations. %s percent of timeOfMapOperations\n"
                    , new DecimalFormat("#0.00").format(((double) timeOfListOperations / (double) timeOfMapOperations) * 100.00));
        }
    }

}
