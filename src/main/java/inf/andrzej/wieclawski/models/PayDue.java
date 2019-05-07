package inf.andrzej.wieclawski.models;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class PayDue {

    private Long payDueId;
    private Employee worker;
    private Integer daysOfWork;
    private Integer daysOfDelegation;
    private YearMonth billedMonthYear;
    private BigDecimal paymentPerDay;

    public PayDue(Long payDueId,
                  Employee worker,
                  Integer daysOfWork,
                  Integer daysOfDelegation,
                  YearMonth billedMonthYear,
                  BigDecimal paymentPerDay) {
        this.payDueId = payDueId;
        this.worker = worker;
        this.daysOfWork = daysOfWork;
        this.daysOfDelegation = daysOfDelegation;
        this.billedMonthYear = billedMonthYear;
        this.paymentPerDay = paymentPerDay;
    }

    // Constructor for adding payDue functionality
    public PayDue(Employee worker,
                  Integer daysOfWork,
                  Integer daysOfDelegation,
                  YearMonth billedMonthYear,
                  BigDecimal paymentPerDay) {
        this.worker = worker;
        this.daysOfWork = daysOfWork;
        this.daysOfDelegation = daysOfDelegation;
        this.billedMonthYear = billedMonthYear;
        this.paymentPerDay = paymentPerDay;
    }

    public Long getPayDueId() {
        return payDueId;
    }

    public void setPayDueId(Long payDueId) {
        this.payDueId = payDueId;
    }

    public Employee getWorker() {
        return worker;
    }

    public void setWorker(Employee worker) {
        this.worker = worker;
    }

    public Integer getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(Integer daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    public Integer getDaysOfDelegation() {
        return daysOfDelegation;
    }

    public void setDaysOfDelegation(Integer daysOfDelegation) {
        this.daysOfDelegation = daysOfDelegation;
    }

    public YearMonth getBilledMonthYear() {
        return billedMonthYear;
    }

    public void setBilledMonthYear(YearMonth billedMonthYear) {
        this.billedMonthYear = billedMonthYear;
    }

    public BigDecimal getPaymentPerDay() {
        return paymentPerDay;
    }

    public void setPaymentPerDay(BigDecimal paymentPerDay) {
        this.paymentPerDay = paymentPerDay;
    }

    @Override
    public String toString() {
        return "PayDue{" +
                "payDueId=" + payDueId +
                ", worker=" + worker.getName() + " " + worker.getSurname() +
                ", daysOfWork=" + daysOfWork +
                ", daysOfDelegation=" + daysOfDelegation +
                ", billedMonthYear=" + billedMonthYear.format(DateTimeFormatter.ofPattern("MM-yyyy")) +
                ", paymentPerDay=" + paymentPerDay +
                '}';
    }
}
