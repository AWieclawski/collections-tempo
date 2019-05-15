package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;
import inf.andrzej.wieclawski.repositories.PaymentRepositoryUsingList;
import inf.andrzej.wieclawski.repositories.PaymentRepositoryUsingMap;
import inf.andrzej.wieclawski.repositories.PaymentRepositoryUsingSet;

import javax.ejb.Stateless;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class PaymentServiceDaoBean implements PaymentServiceDao {

    private static Logger logger = Logger.getLogger(PaymentServiceDaoBean.class.getName());

    @Override
    public List<PayDue> getPayDueList() {
        List<PayDue> payDues = PaymentRepositoryUsingList.getPayDuesBaseList();
        if (payDues.isEmpty()) {
            logger.info("Ops! No list of PayDues in Base");
            return payDues;
        }
        return payDues;
    }

    @Override
    public PayDue getPayDueByIdList(Long payDueIdToFindInList) {
        if (payDueIdToFindInList != null) {
            return PaymentRepositoryUsingList.findPayDueByIdInList(payDueIdToFindInList);
        }
        logger.info("Ops! payDueIdToFindInList is null");
        return null;
    }

    @Override
    public PayDue addPayDueList(PayDue payDueToAdd) {
        Long newIdInList = PaymentRepositoryUsingList.getNextIdInList();
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAdd, newIdInList);
        if (PaymentRepositoryUsingList.addPayDueToBaseList(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("The payDue: " + payDueAdded
                + " not successfully added to list");
        return null;
    }

    @Override
    public boolean updatePayDueList(PayDue payDueToUpdate) {
        if (payDueToUpdate != null) {
            return PaymentRepositoryUsingList.updatePayDueIfExistsInList(payDueToUpdate);
//            return true;
        }
        logger.info("Not updated. payDue is empty ");
        return false;
    }

    @Override
    public boolean deletePayDueByIdList(Long idOfPayDueToDelete) {
        List<PayDue> payDues = PaymentRepositoryUsingList.getPayDuesBaseList();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals((long) idOfPayDueToDelete))) {
            payDues.remove(PaymentRepositoryUsingList.findPayDueByIdInList(idOfPayDueToDelete));
            return true;
        }
        logger.info("Can not delete payDue with id: " + idOfPayDueToDelete);
        return false;
    }

    @Override
    public Map<Long, PayDue> getPayDueMap() {
        Map<Long, PayDue> payDueMapToGet = PaymentRepositoryUsingMap.getPayDuesBaseMap();
        if (payDueMapToGet.size() == 0) {
            logger.info("Ops! PayDuesBaseMap is empty");
            return payDueMapToGet;
        }
        return payDueMapToGet;
    }

    @Override
    public PayDue getPayDueByMapKey(Long key) {
        if (PaymentRepositoryUsingMap.findPayDueByIdInMap(key).isPresent()) {
            return PaymentRepositoryUsingMap.findPayDueByIdInMap(key).get();
        }
        logger.info("Ops! payDueIdToFindInMap is not present " + key);
        return null;
    }

    @Override
    public PayDue addPayDueToMap(PayDue payDueToAddInMap) {
        Long newIdInMap = PaymentRepositoryUsingMap.getNextIdInMap();
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAddInMap, newIdInMap);
        if (PaymentRepositoryUsingMap.addPayDueToBaseMap(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("Ops! The payDue: " + payDueToAddInMap
                + " not added to map");
        return null;
    }

    @Override
    public boolean updatePayDueMap(PayDue payDueToUpdateInMap) {
        if (payDueToUpdateInMap != null) {
            return PaymentRepositoryUsingMap.updatePayDueIfExistsInMap(payDueToUpdateInMap);
        }
        logger.info("Ops! The payDueToUpdateInMap not updated in map");
        return false;
    }

    @Override
    public boolean deletePayDueByMapKey(Long keyToDelete) {
        Map<Long, PayDue> payDueMap = PaymentRepositoryUsingMap.getPayDuesBaseMap();
        if (payDueMap.containsKey(keyToDelete)) {
            payDueMap.remove(keyToDelete);
            return true;
        }
        logger.info("Can not delete payDue with key: " + keyToDelete);
        return false;
    }

    @Override
    public SortedSet<PayDue> getPayDueSet() {
        SortedSet<PayDue> payDueSet = PaymentRepositoryUsingSet.getPayDuesSetBase();
        if (payDueSet.isEmpty()) {
            logger.info("Ops! PayDuesSetBase is empty");
            return payDueSet;
        }
        return payDueSet;
    }

    @Override
    public PayDue getPayDueByIdSet(Long payDueIdToFindInSet) {
        if (payDueIdToFindInSet != null) {
            return PaymentRepositoryUsingSet.findPayDueByIdInSet(payDueIdToFindInSet);
        }
        logger.info("Ops! payDueIdToFindInSet not found " + payDueIdToFindInSet);
        return null;
    }

    @Override
    public PayDue addPayDueSet(PayDue payDueToAddInSet) {
        Long newIdInSet = PaymentRepositoryUsingSet.getMaxIdInSet();
//        logger.info("newIdInSet: " + newIdInSet);
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAddInSet, newIdInSet);
        if (PaymentRepositoryUsingSet.addPayDueToSet(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("The payDue: " + payDueToAddInSet
                + " not successfully added to map");
        return null;
    }

    @Override
    public boolean updatePayDueSet(PayDue payDueToUpdateInSet) {
        if (payDueToUpdateInSet != null) {
            return PaymentRepositoryUsingSet.updatePayDueIfExistsInSet(payDueToUpdateInSet);
        }
        logger.info("Ops! The payDueToUpdateInSet not updated in set " + payDueToUpdateInSet);
        return false;
    }

    @Override
    public boolean deletePayDueByIdSet(Long payDueIdToDeleteInSet) {
        if (payDueIdToDeleteInSet != null) {
//            logger.info("deletePayDueByIdSet returns");
            return PaymentRepositoryUsingSet.removePayDueByIdIfExistsInSet(payDueIdToDeleteInSet);
        }
        logger.info("Ops! The payDueToUpdateInSet not updated in set, id: " + payDueIdToDeleteInSet);
        return false;
    }

    private PayDue newPayDueToAddWithMaxId(PayDue payDueReceived, Long newId) {
        if (payDueReceived != null) {
            return new PayDue(
                    newId,
                    payDueReceived.getWorker(),
                    payDueReceived.getDaysOfDelegation(),
                    payDueReceived.getDaysOfWork(),
                    payDueReceived.getBilledMonthYear(),
                    payDueReceived.getPaymentPerDay()
            );
        }
        logger.info("payDueToAddInMap is null ");
        return null;
    }
}