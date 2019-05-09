package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.List;
import java.util.Map;

public interface PaymentServiceDao {

    List<PayDue> getPayDueList();

    PayDue getPayDueById(Long id);

    PayDue addPayDue(PayDue payDue);

    boolean updatePayDue(PayDue payDue);

    boolean deletePayDueById(Long id);

    Map<Long, PayDue> getPayDueMap();

    PayDue getPayDueByMapKey(Long key);

    PayDue addPayDueToMap(PayDue payDue);

    boolean updatePayDueMap(PayDue payDue);

    boolean deletePayDueByMapKey(Long key);

}
