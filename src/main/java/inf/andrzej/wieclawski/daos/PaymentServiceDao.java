package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.List;
import java.util.Map;

public interface PaymentServiceDao {

    List<PayDue> getPayDueList();

    PayDue getPayDueById(Long id);

    void addPayDue(PayDue payDue);

    boolean updatePayDue(PayDue payDue);

    boolean DeletePayDueById(Long id);

    Map<Long, PayDue> getPayDueMap();

    PayDue getPayDueByMapKey(Long key);

    void addPayDueToMap(PayDue payDue);

    boolean updatePayDueMap(PayDue payDue);

    boolean DeletePayDueByMapKey(Long key);

}
