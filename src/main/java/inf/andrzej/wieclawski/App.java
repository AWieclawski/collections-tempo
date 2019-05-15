package inf.andrzej.wieclawski;

import inf.andrzej.wieclawski.services.OperationsManager;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());
    //    private static List<Long> timesOfOperationsMap;
    private static Map<String, Long> timesOfOperationsMap = new HashMap<>();

    public static void main(String[] args) {

        Long timeOfListOperations = OperationsManager.listOperations();
        timesOfOperationsMap.put("timeOfListOperations", timeOfListOperations);
        Long timeOfMapOperations = OperationsManager.mapOperations();
        timesOfOperationsMap.put("timeOfMapOperations", timeOfMapOperations);
        Long timeOfSetOperations = OperationsManager.setOperations();
        timesOfOperationsMap.put("timeOfSetOperations", timeOfSetOperations);

        Optional<Long> theWinnerIs = timesOfOperationsMap.values().stream()
                .min(Long::compareTo);

        Optional<Long> theLooserIs = timesOfOperationsMap.values().stream()
                .max(Long::compareTo);

        System.out.println("\n *** Operation times analyze: ");

        System.out.printf("Time of main method operations: %s, using list from PaymentRepositoryUsingList. \n"
                , timeOfListOperations);

        System.out.printf("Time of main method operations: %s, using map from PaymentRepositoryUsingMap. \n"
                , timeOfMapOperations);

        System.out.printf("Time of main method operations: %s, using set from PaymentRepositoryUsingSet. \n"
                , timeOfSetOperations);

        theWinnerIs.ifPresent(aLong -> System.out.printf("\n # The winner with time: %s, is: %s. \n"
                , aLong, timesOfOperationsMap.entrySet()
                        .stream()
                        .filter(e -> Objects.equals(e.getValue(), theWinnerIs.get()))
                        .map(Map.Entry::getKey)
                        .findAny().get()));

        theLooserIs.ifPresent(aLong -> System.out.printf("\n # The looser with time: %s, is: %s.\n"
                , aLong, timesOfOperationsMap.entrySet()
                        .stream()
                        .filter(e -> Objects.equals(e.getValue(), theLooserIs.get()))
                        .map(Map.Entry::getKey)
                        .findAny().get()));

        if (theLooserIs.isPresent() && theWinnerIs.isPresent()) {
            System.out.printf("\n The looser's time is: %s percent bigger than the winner. \n",
                    new DecimalFormat("#0.00")
                            .format(((double) theLooserIs.get() / (double) theWinnerIs.get()) * 100.00));
        }
    }

}
