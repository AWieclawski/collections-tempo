package inf.andrzej.wieclawski;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayDue {

    private Long payDueId;
    private Employee worker;
    private Integer daysOfWork;
    private Integer daysOfDelegation;
    private LocalDate billedMonthYear;
    private BigDecimal paymentPerDay;

    public PayDue(Long payDueId,
                  Employee worker,
                  Integer daysOfWork,
                  Integer daysOfDelegation,
                  LocalDate billedMonthYear,
                  BigDecimal paymentPerDay) {
        this.payDueId = payDueId;
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

    public LocalDate getBilledMonthYear() {
        return billedMonthYear;
    }

    public void setBilledMonthYear(LocalDate billedMonthYear) {
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
                ", worker=" + worker +
                ", daysOfWork=" + daysOfWork +
                ", daysOfDelegation=" + daysOfDelegation +
                ", billedMonthYear=" + billedMonthYear +
                ", paymentPerDay=" + paymentPerDay +
                '}';
    }
}
