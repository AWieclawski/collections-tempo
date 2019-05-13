package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.List;
import java.util.Map;

public interface PaymentServiceDao {

    List<PayDue> getPayDueList();

    PayDue getPayDueByIdList(Long id);

    PayDue addPayDueList(PayDue payDue);

    boolean updatePayDueList(PayDue payDue);

    boolean deletePayDueByIdList(Long id);

    Map<Long, PayDue> getPayDueMap();

    PayDue getPayDueByMapKey(Long key);

    PayDue addPayDueToMap(PayDue payDue);

    boolean updatePayDueMap(PayDue payDue);

    boolean deletePayDueByMapKey(Long key);

}
