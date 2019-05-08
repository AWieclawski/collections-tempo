package inf.andrzej.wieclawski;

import inf.andrzej.wieclawski.services.TestOfOperations;

import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Long timeOfListOperations = TestOfOperations.testOfListOperations();
        Long timeOfMapOperations = TestOfOperations.testOfMapOperations();

        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingLists. \n"
                , timeOfListOperations);

        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingMaps. \n"
                , timeOfMapOperations);

        if (timeOfListOperations > timeOfMapOperations) {
            System.out.println("timeOfListOperations IS LONGER than timeOfMapOperations");
        } else {
            System.out.println("timeOfListOperations IS SHORTER than timeOfMapOperations");
        }
    }

}
