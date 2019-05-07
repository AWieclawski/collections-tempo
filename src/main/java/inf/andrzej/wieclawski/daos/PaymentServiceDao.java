package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.List;

public interface PaymentServiceDao {

    List<PayDue> getPayDueList();

    PayDue getPayDueById(Long id);

    void addPayDue(PayDue payDue);

    PayDue updatePayDue(PayDue payDue);

    boolean DeletePayDueById(Long id);

}
