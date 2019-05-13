package inf.andrzej.wieclawski.services;

import inf.andrzej.wieclawski.daos.PaymentServiceDao;
import inf.andrzej.wieclawski.daos.PaymentServiceDaoBean;
import inf.andrzej.wieclawski.models.Employee;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TestOfOperations {

    private static Logger logger = Logger.getLogger(TestOfOperations.class.getName());
    private static int iterator = 10; // iteration of data operation test
    private static Long randomId = 0L;
    private static long startTime = 0L;
    private static long endTime = 0L;

    public static Long testOfListOperations() {

        startTime = 0L;
        startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();
        System.out.println("*** Initial listOfPayDues: ");
        List<PayDue> listOfPayDues = dao.getPayDueList();
        payDueListToPrint(listOfPayDues);

        for (int i = 0; i < iterator; i++) {
            randomId = (long) (Math.random() * listOfPayDues.size() + 1);
            PayDue payDueToAdd = getNewPayDueInstance();
            PayDue payDueToAddResult = dao.addPayDueList(payDueToAdd);
            if (payDueToAddResult != null) {
                System.out.printf("payDueToAdd success, \nadded: %s\n", payDueToAddResult.toString());
            } else {
                System.out.printf("Fail! payDueToAdd with id %s not added.\n", randomId);
            }

            PayDue payDueToFind = dao.getPayDueByIdList(randomId);
            if (payDueToFind != null) {
                System.out.printf("For id %s, \npayDueToFind success, found: %s\n", randomId, payDueToFind.toString());
            } else {
                System.out.printf("For id %s, \nFail! payDueToFind not found .\n", randomId);
            }

            PayDue payDueToUpdate = getPayDueToUpdate(payDueToFind);
            if (dao.updatePayDueList(payDueToUpdate)) {
                System.out.printf("payDueToUpdate with id %s success, \nupdated: %s \n"
                        , randomId, payDueToUpdate.toString());
            } else {
                System.out.println("Fail! payDueToUpdate not updated. Searched Id: " + randomId);
            }

            if (dao.deletePayDueByIdList(randomId)) {
                System.out.printf("Deleting from list payDue with id %s operation success \n"
                        , randomId);
            } else {
                System.out.println("deletePayDueByIdList not successful. Searched Id: " + randomId);
            }
        }
        System.out.println("*** Final payDueListToPrint:");
        payDueListToPrint(dao.getPayDueList());

        endTime = 0L;
        endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public static Long testOfMapOperations() {

        startTime = 0L;
        startTime = System.nanoTime();

        PaymentServiceDao dao = new PaymentServiceDaoBean();

        Map<Long, PayDue> payDueMap = dao.getPayDueMap();
        System.out.println("*** Initial payDueMapToPrint: ");
        payDueMapToPrint(payDueMap);

        for (int i = 0; i < iterator; i++) {
            randomId = (long) (Math.random() * payDueMap.size() + 1);
            PayDue payDueToAdd = getNewPayDueInstance();
            PayDue payDueAddingResult = dao.addPayDueToMap(payDueToAdd);
            if (payDueAddingResult != null) {
                System.out.printf("payDueToAdd in map success. \npayDueToAdd: %s\n"
                        , payDueAddingResult.toString());
            } else {
                System.out.printf("Fail! payDueToAdd in map with id %s not added.\n", randomId);
            }

            PayDue payDueToFind = dao.getPayDueByMapKey(randomId);

            if (payDueToFind != null) {
                System.out.printf("For id %s, \npayDueToFind in map success. \npayDueToFind: %s\n"
                        , randomId, payDueToFind.toString());
            } else {
                System.out.printf("For id %s, \nFail! payDueToFind not found in map. \n", randomId);
            }

            PayDue payDueToUpdate = getPayDueToUpdate(payDueToFind);
            if (dao.updatePayDueMap(payDueToUpdate)) {
                System.out.printf("payDueToUpdate in map success. \npayDueToUpdate: %s\n"
                        , payDueToUpdate.toString());
            } else {
                System.out.printf("Fail! payDueToUpdate in map not successful. id: %s\n", randomId);
            }

            if (dao.deletePayDueByMapKey(randomId)) {
                System.out.printf("Deleting payDue with id %s from map operation success \n"
                        , randomId);
            } else {
                System.out.printf("Fail! deletePayDueByMapKey from map not successful. id: %s\n", randomId);
            }
        }

        System.out.println("*** Final payDueMapToPrint: ");
        payDueMapToPrint(dao.getPayDueMap());

        endTime = 0L;
        endTime = System.nanoTime();
        return (endTime - startTime);
    }

    private static void payDueListToPrint(List<PayDue> listToPrint) {
//        logger.info("payDueListToPrint size: " + listToPrint.size());
        for (PayDue thisPayDue : listToPrint) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static void payDueMapToPrint(Map<Long, PayDue> mapToPrint) {
//        logger.info("payDueMapToPrint size: " + mapToPrint.size());
        for (Map.Entry<Long, PayDue> thisPayDue : mapToPrint.entrySet()) {
            System.out.println(thisPayDue.toString());
        }
    }

    private static PayDue getNewPayDueInstance() {
//        logger.info("getNewPayDueInstance  initialisation");
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
            // Employee(Long workerId, String name, String surname, String workerTaxId, LocalDate dateOfBirth)

            Employee updatedEmployee = new Employee(payDueToOperate.getWorker().getWorkerId()
                    , payDueToOperate.getWorker().getName().concat("Updated")
                    , payDueToOperate.getWorker().getSurname()
                    , payDueToOperate.getWorker().getWorkerTaxId()
                    , payDueToOperate.getWorker().getDateOfBirth()
            );
//            logger.info("getPayDueToUpdate payDueToOperate: " + payDueToOperate.toString());
            return new PayDue(
                    payDueToOperate.getPayDueId(), updatedEmployee, payDueToOperate.getDaysOfWork(),
                    payDueToOperate.getDaysOfDelegation(), payDueToOperate.getBilledMonthYear(),
                    payDueToOperate.getPaymentPerDay()
            );
        }
        return null;
    }

}
