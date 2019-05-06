package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.Employee;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentRepository {

    private static Logger logger = Logger.getLogger(PaymentRepository.class.getName());

    private static List<PayDue> baseOfPayDues = new ArrayList<>();
    private static List<Employee> lisOfWorkers = new ArrayList<>();

    public static List<PayDue> getPayDueBase() {
        logger.info("Before fillPayDueBaseWithDefaults baseOfPayDues. Size: " + baseOfPayDues.size());
        if (baseOfPayDues.size() == 0) {
            fillPayDueBaseWithDefaults();
            logger.info("fillPayDueBaseWithDefaults initialised baseOfPayDues. Size: " + baseOfPayDues.size());
        } else {
            logger.info("baseOfPayDues already initialised. Size: " + baseOfPayDues.size());
        }

        logger.info("Return baseOfPayDues. Size: " + baseOfPayDues.size());
        return baseOfPayDues;
    }

    private static void fillPayDueBaseWithDefaults() {

        // Employee(Long workerId, String name, String surname, String workerTaxId, LocalDate dateOfBirth)

        Employee worker1 = new Employee(1L, "name1", "surname1", "TaxId001",
                LocalDate.parse("1981-03-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lisOfWorkers.add(worker1);

        Employee worker2 = new Employee(2L, "name2", "surname2", "TaxId002",
                LocalDate.parse("1982-08-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lisOfWorkers.add(worker2);

        Employee worker3 = new Employee(3L, "name3", "surname3", "TaxId003",
                LocalDate.parse("1983-11-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lisOfWorkers.add(worker3);

        Long count = 1L;

        for (Employee worker : lisOfWorkers) {

            PayDue payDue1 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("01-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(133)
            );
            baseOfPayDues.add(payDue1);
            count++;

            PayDue payDue2 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("02-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(134)
            );
            baseOfPayDues.add(payDue2);
            count++;

            PayDue payDue3 = new PayDue(
                    count, worker, 22, 1,
                    YearMonth.parse("03-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(135)
            );
            baseOfPayDues.add(payDue3);
            count++;

            PayDue payDue4 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("04-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            baseOfPayDues.add(payDue4);
            count++;

            PayDue payDue5 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("05-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            baseOfPayDues.add(payDue5);
            count++;

            PayDue payDue6 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("06-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            baseOfPayDues.add(payDue6);
            count++;

            PayDue payDue7 = new PayDue(
                    count, worker, 22, 1,
                    YearMonth.parse("07-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(137)
            );
            baseOfPayDues.add(payDue7);
            count++;

            PayDue payDue8 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("08-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(138)
            );
            baseOfPayDues.add(payDue8);
            count++;

            PayDue payDue9 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("09-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(139)
            );
            baseOfPayDues.add(payDue9);
            count++;

            PayDue payDue10 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("10-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(140)
            );
            baseOfPayDues.add(payDue4);
            count++;

            PayDue payDue11 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("11-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(141)
            );
            baseOfPayDues.add(payDue11);
            count++;

            PayDue payDue12 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("12-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(142)
            );
            baseOfPayDues.add(payDue12);
            count++;
        }
    }

    public static boolean addPayDueToBase(PayDue payDueToAdd) {
        boolean status = false;
        logger.info("baseOfPayDues size before addPayDueToBase: " + baseOfPayDues.size());
        if (getPayDueBase().stream().noneMatch(o -> o.getPayDueId().equals(payDueToAdd.getPayDueId()))) {
            baseOfPayDues.add(payDueToAdd);
            logger.info("baseOfPayDues size after successful addPayDueToBase: " + baseOfPayDues.size());
            status=true;
        } else {
            logger.info("payDueToAdd id exists in baseOfPayDues. Size after not successful addPayDueToBase: "
                    + baseOfPayDues.size());
            return status;
        }
        return status;
    }

    public static Long getNextId() {
        logger.info("getNextId baseOfPayDues size: " + baseOfPayDues.size());
        return (long) (baseOfPayDues.size() + 1);
    }

    public static PayDue findPayDueById(Long idOfPayDueToFind) {

        return
                baseOfPayDues.stream()
                        .filter(payDue -> (idOfPayDueToFind).equals(payDue.getPayDueId()))
                        .findAny()
                        .orElse(null);
    }

}

