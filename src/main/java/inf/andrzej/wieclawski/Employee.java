package inf.andrzej.wieclawski;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {

    private Long workerId;
    private String name;
    private String surname;
    private String workerTaxId;
    private LocalDate dateOfBirth;

    public Employee(Long workerId, String name, String surname, String workerTaxId, LocalDate dateOfBirth) {
        this.workerId = workerId;
        this.name = name;
        this.surname = surname;
        this.workerTaxId = workerTaxId;
        this.dateOfBirth = dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkerTaxId() {
        return workerTaxId;
    }

    public void setWorkerTaxId(String workerTaxId) {
        this.workerTaxId = workerTaxId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "workerId=" + workerId +
                ", name='" + name + ' ' + surname + '\''+
                ", workerTaxId='" + workerTaxId + '\'' +
                ", dateOfBirth=" + dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+
                '}';
    }
}