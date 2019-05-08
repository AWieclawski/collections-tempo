package inf.andrzej.wieclawski.services;

import inf.andrzej.wieclawski.daos.PaymentServiceDao;
import inf.andrzej.wieclawski.daos.PaymentServiceDaoBean;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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
        if (listOfPayDues.size() != 0) {
            System.out.println("payDueToFind: " + payDueToFind.toString());
        } else {
            System.out.println("No payDueToFind. listOfPayDues is empty ");
        }

        PayDue payDueToAdd = getNewPayDueInstance(payDueToFind);

        dao.addPayDue(payDueToAdd);
        System.out.println("payDueToAdd: " + payDueToAdd.toString());
        payDueListToPrint(dao.getPayDueList());

        PayDue payDueToUpdate = getPayDueToUpdete(payDueToFind);

        if (dao.updatePayDue(payDueToUpdate)) {
            System.out.println("payDueToUpdate: " + payDueToUpdate.toString());
        } else {
            System.out.println("payDueToUpdate not successful ");
        }
        payDueListToPrint(dao.getPayDueList());

        if (dao.DeletePayDueById(randomId)) {
            System.out.printf("Deleting payDue with id %s operation success \n"
                    , randomId);
        } else {
            System.out.println("DeletePayDueById not successful ");
        }
        payDueListToPrint(dao.getPayDueList());

        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public static Long testOfMapOperations() {

        long startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();

        Map<Long, PayDue> payDueMap = dao.getPayDueMap();
        payDueMapToPrint(payDueMap);

        Long randomId = (long) (Math.random() * payDueMap.size() + 1);
        PayDue payDueToFind = dao.getPayDueByMapKey(randomId);

        if (payDueMap.size() != 0) {
            System.out.println("payDueToFind: " + payDueToFind.toString());
        } else {
            System.out.println("No payDueToFind. payDueMap is empty ");
        }

        PayDue payDueToAdd = getNewPayDueInstance(payDueToFind);

        dao.addPayDueToMap(payDueToAdd);
        System.out.println("payDueToAdd in map: " + payDueToAdd.toString());
        payDueMapToPrint(dao.getPayDueMap());

        PayDue payDueToUpdate = getPayDueToUpdete(payDueToFind);

        if (dao.updatePayDueMap(payDueToUpdate)) {
            System.out.println("payDueToUpdate in map: " + payDueToUpdate.toString());
        } else {
            System.out.println("payDueToUpdate in ma not successful ");
        }
        payDueMapToPrint(dao.getPayDueMap());

        if (dao.DeletePayDueByMapKey(randomId)) {
            System.out.printf("Deleting payDue with id %s from map operation success \n"
                    , randomId);
        } else {
            System.out.println("DeletePayDueById from map not successful ");
        }
        payDueMapToPrint(dao.getPayDueMap());

        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    private static void payDueListToPrint(List<PayDue> listToPrint) {

        logger.info("payDueListToPrint size: " + listToPrint.size());
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static void payDueMapToPrint(Map<Long, PayDue> mapToPrint) {

        logger.info("payDueMapToPrint size: " + mapToPrint.size());
        for (Map.Entry<Long, PayDue> thisPayDue : mapToPrint.entrySet()) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static PayDue getNewPayDueInstance(PayDue payDueToOperate) {
        return new PayDue(
                payDueToOperate.getWorker(), 20, 3,
                YearMonth.parse("01-2020", DateTimeFormatter.ofPattern("MM-yyyy")),
                BigDecimal.valueOf(130 + payDueToOperate.getPayDueId())
        );
    }

    private static PayDue getPayDueToUpdete(PayDue payDueToOperate) {
        return new PayDue(
                payDueToOperate.getPayDueId(), payDueToOperate.getWorker(), payDueToOperate.getDaysOfWork(),
                payDueToOperate.getDaysOfDelegation(), payDueToOperate.getBilledMonthYear(),
                payDueToOperate.getPaymentPerDay().subtract(BigDecimal.valueOf(payDueToOperate.getPayDueId()))
        );
    }

}
