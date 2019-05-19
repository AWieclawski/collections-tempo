package inf.andrzej.wieclawski.services;

import inf.andrzej.wieclawski.daos.PaymentServiceDao;
import inf.andrzej.wieclawski.daos.PaymentServiceDaoBean;
import inf.andrzej.wieclawski.models.Employee;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperationsManager {

    private static Logger logger = Logger.getLogger(OperationsManager.class.getName());
    private static long startTime = 0L;
    private static long endTime = 0L;

    public static Long listOperations() {

        startTime = 0L;
        startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();
        System.out.println("*** Initial listOfPayDues: ");
        List<PayDue> listOfPayDues = dao.getPayDueList();
        payDueListToPrint(listOfPayDues);

        for (Long iteratorOfTest : exampleListMaker()) {
            PayDue payDueToAdd = getNewPayDueInstance();
            PayDue payDueToAddResult = dao.addPayDueList(payDueToAdd);
            if (payDueToAddResult != null) {
                System.out.printf("payDueToAdd success, \nadded: %s\n", payDueToAddResult.toString());
            } else {
                System.out.printf("Fail! payDueToAdd with id %s not added.\n", iteratorOfTest);
            }

            PayDue payDueToFind = dao.getPayDueByIdList(iteratorOfTest);
            if (payDueToFind != null) {
                System.out.printf("For id %s, \npayDueToFind success, found: %s\n", iteratorOfTest, payDueToFind.toString());
            } else {
                System.out.printf("For id %s, \nFail! payDueToFind not found .\n", iteratorOfTest);
            }

            PayDue payDueToUpdate = getPayDueToUpdate(payDueToFind);
            if (dao.updatePayDueList(payDueToUpdate)) {
                System.out.printf("payDueToUpdate with id %s success, \nupdated: %s \n"
                        , iteratorOfTest, payDueToUpdate);
            } else {
                System.out.printf("Fail! payDueToUpdate not updated. Searched Id: %s\n", iteratorOfTest);
            }

            if (dao.deletePayDueByIdList(iteratorOfTest)) {
                System.out.printf("Deleting from list payDue with id %s operation success \n"
                        , iteratorOfTest);
            } else {
                System.out.println("deletePayDueByIdList not successful. Searched Id: " + iteratorOfTest);
            }
        }
        System.out.println("*** Final payDueListToPrint:");
        payDueListToPrint(dao.getPayDueList());

        endTime = 0L;
        endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public static Long mapOperations() {

        startTime = 0L;
        startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();

        Map<Long, PayDue> payDueMap = dao.getPayDueMap();
        System.out.println("*** Initial payDueMapToPrint: ");
        payDueMapToPrint(payDueMap);

        for (Long iteratorOfTest : exampleListMaker()) {
            PayDue payDueToAdd = getNewPayDueInstance();
            PayDue payDueAddingResult = dao.addPayDueToMap(payDueToAdd);
            if (payDueAddingResult != null) {
                System.out.printf("payDueToAdd in map success. \npayDueToAdd: %s\n"
                        , payDueAddingResult.toString());
            } else {
                System.out.printf("Fail! payDueToAdd in map with id %s not added.\n", iteratorOfTest);
            }

            PayDue payDueToFind = dao.getPayDueByMapKey(iteratorOfTest);

            if (payDueToFind != null) {
                System.out.printf("For id %s, \npayDueToFind in map success. \npayDueToFind: %s\n"
                        , iteratorOfTest, payDueToFind);
            } else {
                System.out.printf("For id %s, \nFail! payDueToFind not found in map. \n", iteratorOfTest);
            }

            PayDue payDueToUpdate = getPayDueToUpdate(payDueToFind);
            if (dao.updatePayDueMap(payDueToUpdate)) {
                System.out.printf("payDueToUpdate in map success. \npayDueToUpdate: %s\n"
                        , payDueToUpdate);
            } else {
                System.out.printf("Fail! payDueToUpdate in map not updated. id: %s\n", iteratorOfTest);
            }

            if (dao.deletePayDueByMapKey(iteratorOfTest)) {
                System.out.printf("Deleting payDue with id %s from map operation success \n"
                        , iteratorOfTest);
            } else {
                System.out.printf("Fail! deletePayDueByMapKey from map not deleted. id: %s\n", iteratorOfTest);
            }
        }

        System.out.println("*** Final payDueMapToPrint: ");
        payDueMapToPrint(dao.getPayDueMap());

        endTime = 0L;
        endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public static Long setOperations() {

        startTime = 0L;
        startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();

        Set<PayDue> payDueSet = dao.getPayDueSet();
        System.out.println("*** Initial payDueSetToPrint: " + payDueSet.size());
        payDueSetToPrint(payDueSet);

        for (Long iteratorOfTest : exampleListMaker()) {
            PayDue payDueToAdd = getNewPayDueInstance();
            PayDue payDueAddingResult = dao.addPayDueSet(payDueToAdd);
            if (payDueAddingResult != null) {
                System.out.printf("payDueToAdd in set success. \npayDueToAdd: %s\n"
                        , payDueAddingResult);
            } else {
                System.out.printf("Fail! payDueToAdd in set with id %s not added.\n", iteratorOfTest);
            }

            PayDue payDueToFind = dao.getPayDueByIdSet(iteratorOfTest);

            if (payDueToFind != null) {
                System.out.printf("For id %s, \npayDueToFind in set success. \npayDueToFind: %s\n"
                        , iteratorOfTest, payDueToFind);
            } else {
                System.out.printf("For id %s, \nFail! payDueToFind not found in set. \n", iteratorOfTest);
            }

            PayDue payDueToUpdate = getPayDueToUpdate(payDueToFind);
            if (dao.updatePayDueSet(payDueToUpdate)) {
                System.out.printf("payDueToUpdate in set success. \npayDueToUpdate: %s\n"
                        , payDueToUpdate);
            } else {
                System.out.printf("Fail! payDueToUpdate in set not updated. id: %s\n", iteratorOfTest);
            }

            if (dao.deletePayDueByIdSet(iteratorOfTest)) {
                System.out.printf("Deleting payDue with id %s from set operation success \n"
                        , iteratorOfTest);
            } else {
                System.out.printf("Fail! deletePayDueByMapKey from set not deleted. id: %s\n", iteratorOfTest);
            }
        }

        System.out.println("*** Final payDueSetToPrint: " + payDueSet.size());
        payDueSetToPrint(dao.getPayDueSet());

        endTime = 0L;
        endTime = System.nanoTime();
        return (endTime - startTime);
    }


    private static void payDueListToPrint(List<PayDue> listToPrint) {
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static void payDueMapToPrint(Map<Long, PayDue> mapToPrint) {
        for (Map.Entry<Long, PayDue> thisPayDue : mapToPrint.entrySet()) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static void payDueSetToPrint(Set<PayDue> setToPrint) {
        for (PayDue thisPayDue : setToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static PayDue getNewPayDueInstance() {
        Employee worker = new Employee(4L, "NewAddedName", "NewAddedSurname", "TaxId001",
                LocalDate.parse("1981-03-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return new PayDue(
                worker, 20, 3,
                YearMonth.parse("01-2020", DateTimeFormatter.ofPattern("MM-yyyy")),
                BigDecimal.valueOf(166)
        );
    }

    private static PayDue getPayDueToUpdate(PayDue payDueToOperate) {
        if (payDueToOperate != null) {

            Employee updatedEmployee = new Employee(payDueToOperate.getWorker().getWorkerId()
                    , payDueToOperate.getWorker().getName().concat("Updated")
                    , payDueToOperate.getWorker().getSurname()
                    , payDueToOperate.getWorker().getWorkerTaxId()
                    , payDueToOperate.getWorker().getDateOfBirth()
            );
            return new PayDue(
                    payDueToOperate.getPayDueId(), updatedEmployee, payDueToOperate.getDaysOfWork(),
                    payDueToOperate.getDaysOfDelegation(), payDueToOperate.getBilledMonthYear(),
                    payDueToOperate.getPaymentPerDay()
            );
        }
        return null;
    }

    private static List<Long> exampleListMaker() {

        return Stream.of(1L, 3L, 7L, 11L, 17L, 23L, 26L, 5L, 9L, 10L, 4L, 33L,29L, 2L, 66L, 15L, 19L, -1L, 11L, 3L)
                .collect(Collectors.toList());
    }

}
