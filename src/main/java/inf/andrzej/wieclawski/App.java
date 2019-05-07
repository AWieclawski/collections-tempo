package inf.andrzej.wieclawski;

import inf.andrzej.wieclawski.daos.PaymentServiceDao;
import inf.andrzej.wieclawski.daos.PaymentServiceDaoBean;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class App {

    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();

        List<PayDue> listOfPayDues = dao.getPayDueList();
        payDueListToPrint(listOfPayDues);

        Long randomId = (long) (Math.random() * listOfPayDues.size() + 1);
        PayDue payDueToFind = dao.getPayDueById(randomId);
        System.out.println("payDueToFind: " + payDueToFind.toString());

        PayDue payDueToAdd = new PayDue(
                payDueToFind.getWorker(), 20, 3,
                YearMonth.parse("01-2020", DateTimeFormatter.ofPattern("MM-yyyy")),
                BigDecimal.valueOf(130 + randomId)
        );

        dao.addPayDue(payDueToAdd);
        payDueListToPrint(dao.getPayDueList());

        PayDue payDueToUpdate = new PayDue(
                payDueToFind.getPayDueId(), payDueToFind.getWorker(), payDueToFind.getDaysOfWork(),
                payDueToFind.getDaysOfDelegation(), payDueToFind.getBilledMonthYear(),
                payDueToFind.getPaymentPerDay().subtract(BigDecimal.valueOf(randomId))
        );

        dao.updatePayDue(payDueToUpdate);
        payDueListToPrint(dao.getPayDueList());

        dao.DeletePayDueById(randomId);
        payDueListToPrint(dao.getPayDueList());


        long endTime = System.nanoTime();
        long listsDuration = (endTime - startTime);
        System.out.printf("Time of main method operations: %s, using lists from PaymentRepositoryUsingLists. ",listsDuration);

    }

    private static void payDueListToPrint(List<PayDue> listToPrint) {

        logger.info("payDueListToPrint size: " + listToPrint.size());
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }
}
