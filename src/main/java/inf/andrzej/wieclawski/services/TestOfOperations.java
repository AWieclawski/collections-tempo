package inf.andrzej.wieclawski.services;

import inf.andrzej.wieclawski.daos.PaymentServiceDao;
import inf.andrzej.wieclawski.daos.PaymentServiceDaoBean;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class TestOfOperations {

    private static Logger logger = Logger.getLogger(TestOfOperations.class.getName());

    public static Long testOfListOperations() {

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

        System.out.printf("Deleting payDue with id %s operation status: %s \n"
                , randomId, dao.DeletePayDueById(randomId));
        payDueListToPrint(dao.getPayDueList());


        long endTime = System.nanoTime();
        return (endTime - startTime);

    }

    private static void payDueListToPrint(List<PayDue> listToPrint) {

        logger.info("payDueListToPrint size: " + listToPrint.size());
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }

}
