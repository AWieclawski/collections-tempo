package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.Employee;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private static List<PayDue> baseOfPayDue = new ArrayList<>();
    private static List<Employee> lisOfWorkers = new ArrayList<>();

    public static List<PayDue> getPayDueBase() {
        if (baseOfPayDue.size() == 0) {
            fillPayDueBaseWithDefaults();
        }
        return baseOfPayDue;
    }

    private static void fillPayDueBaseWithDefaults() {

        // Employee(Long workerId, String name, String surname, String workerTaxId, LocalDate dateOfBirth)

        Employee worker1 = new Employee(1L, "name1", "surname1", "TaxId001",
                LocalDate.parse("1970-03-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lisOfWorkers.add(worker1);

        Employee worker2 = new Employee(2L, "name2", "surname2", "TaxId002",
                LocalDate.parse("1971-08-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        lisOfWorkers.add(worker2);

        for (Employee worker : lisOfWorkers) {

            PayDue payDue1 = new PayDue(
                    1L, worker, 20, 3,
                    LocalDate.parse("01-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(133)
            );
            baseOfPayDue.add(payDue1);

            PayDue payDue2 = new PayDue(
                    2L, worker, 23, 0,
                    LocalDate.parse("02-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(134)
            );
            baseOfPayDue.add(payDue2);

            PayDue payDue3 = new PayDue(
                    3L, worker, 22, 1,
                    LocalDate.parse("03-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(135)
            );
            baseOfPayDue.add(payDue3);

            PayDue payDue4 = new PayDue(
                    4L, worker, 21, 2,
                    LocalDate.parse("04-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(136)
            );
            baseOfPayDue.add(payDue4);

            PayDue payDue5 = new PayDue(
                    5L, worker, 23, 0,
                    LocalDate.parse("05-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(136)
            );
            baseOfPayDue.add(payDue5);

            PayDue payDue6 = new PayDue(
                    6L, worker, 20, 3,
                    LocalDate.parse("06-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(136)
            );
            baseOfPayDue.add(payDue6);

            PayDue payDue7 = new PayDue(
                    7L, worker, 22, 1,
                    LocalDate.parse("07-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(137)
            );
            baseOfPayDue.add(payDue7);

            PayDue payDue8 = new PayDue(
                    8L, worker, 21, 2,
                    LocalDate.parse("08-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(138)
            );
            baseOfPayDue.add(payDue8);

            PayDue payDue9 = new PayDue(
                    9L, worker, 23, 0,
                    LocalDate.parse("09-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(139)
            );
            baseOfPayDue.add(payDue9);

            PayDue payDue10 = new PayDue(
                    10L, worker, 21, 2,
                    LocalDate.parse("10-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(140)
            );
            baseOfPayDue.add(payDue4);

            PayDue payDue11 = new PayDue(
                    11L, worker, 23, 0,
                    LocalDate.parse("11-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(141)
            );
            baseOfPayDue.add(payDue11);

            PayDue payDue12 = new PayDue(
                    12L, worker, 20, 3,
                    LocalDate.parse("12-2019", DateTimeFormatter.ofPattern("MM-yyyy")), BigDecimal.valueOf(142)
            );
            baseOfPayDue.add(payDue12);

        }
    }

    public static void addPayDueToBase(PayDue payDueToAdd) {
        if (getPayDueBase().stream().noneMatch(o -> o.getPayDueId().equals(payDueToAdd.getPayDueId()))) {
            baseOfPayDue.add(payDueToAdd);
        }
    }

    public static Long getNextId() {
        return (long) (baseOfPayDue.size() + 1);
    }

    public static PayDue findPayDueById(Long idOfPayDueToFind) {

        return
                baseOfPayDue.stream()
                        .filter(payDue -> (idOfPayDueToFind).equals(payDue.getPayDueId()))
                        .findAny()
                        .orElse(null);
    }

}

