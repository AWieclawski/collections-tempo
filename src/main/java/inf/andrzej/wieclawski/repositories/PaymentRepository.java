package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.Employee;
import inf.andrzej.wieclawski.models.PayDue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private static List<PayDue> basePayDueRepository = new ArrayList<>();
    private static List<Employee> baseWorkerRepository = new ArrayList<>();

    public static List<PayDue> getRepositoryList() {
        // Employee(Long workerId, String name, String surname, String workerTaxId, LocalDate dateOfBirth)

        Employee worker1 = new Employee(1L, "name1", "surname1", "TaxId001",
                LocalDate.parse("1981-03-22", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        baseWorkerRepository.add(worker1);

        Employee worker2 = new Employee(2L, "name2", "surname2", "TaxId002",
                LocalDate.parse("1982-08-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        baseWorkerRepository.add(worker2);

        Employee worker3 = new Employee(3L, "name3", "surname3", "TaxId003",
                LocalDate.parse("1983-11-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        baseWorkerRepository.add(worker3);

        Long count = 1L;

        for (Employee worker : baseWorkerRepository) {

            PayDue payDue1 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("01-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(133)
            );
            basePayDueRepository.add(payDue1);
            count++;

            PayDue payDue2 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("02-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(134)
            );
            basePayDueRepository.add(payDue2);
            count++;

            PayDue payDue3 = new PayDue(
                    count, worker, 22, 1,
                    YearMonth.parse("03-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(135)
            );
            basePayDueRepository.add(payDue3);
            count++;

            PayDue payDue4 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("04-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            basePayDueRepository.add(payDue4);
            count++;

            PayDue payDue5 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("05-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            basePayDueRepository.add(payDue5);
            count++;

            PayDue payDue6 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("06-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(136)
            );
            basePayDueRepository.add(payDue6);
            count++;

            PayDue payDue7 = new PayDue(
                    count, worker, 22, 1,
                    YearMonth.parse("07-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(137)
            );
            basePayDueRepository.add(payDue7);
            count++;

            PayDue payDue8 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("08-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(138)
            );
            basePayDueRepository.add(payDue8);
            count++;

            PayDue payDue9 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("09-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(139)
            );
            basePayDueRepository.add(payDue9);
            count++;

            PayDue payDue10 = new PayDue(
                    count, worker, 21, 2,
                    YearMonth.parse("10-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(140)
            );
            basePayDueRepository.add(payDue4);
            count++;

            PayDue payDue11 = new PayDue(
                    count, worker, 23, 0,
                    YearMonth.parse("11-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(141)
            );
            basePayDueRepository.add(payDue11);
            count++;

            PayDue payDue12 = new PayDue(
                    count, worker, 20, 3,
                    YearMonth.parse("12-2019", DateTimeFormatter.ofPattern("MM-yyyy")),
                    BigDecimal.valueOf(142)
            );
            basePayDueRepository.add(payDue12);
            count++;

        }
        return basePayDueRepository;
    }
}
