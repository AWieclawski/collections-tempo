package inf.andrzej.wieclawski;

import inf.andrzej.wieclawski.models.PayDue;
import inf.andrzej.wieclawski.services.TestOfOperations;

import java.util.List;
import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingLists. "
                , TestOfOperations.testOfListOperations());

    }

    private static void payDueListToPrint(List<PayDue> listToPrint) {

        logger.info("payDueListToPrint size: " + listToPrint.size());
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }
}
