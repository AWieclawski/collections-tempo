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
            logger.info("Ops, getPayDueList fail!");
            return payDues;
        }
        return payDues;
    }

    @Override
    public PayDue getPayDueByIdList(Long payDueIdToFindInList) {
        if (payDueIdToFindInList != null) {
            return PaymentRepositoryUsingList.findPayDueByIdInList(payDueIdToFindInList);
        }
        logger.info("Ops getPayDueByIdList fai!");
        return null;
    }

    @Override
    public PayDue addPayDueList(PayDue payDueToAdd) {
        Long newIdInList = PaymentRepositoryUsingList.getNextIdInList();
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAdd, newIdInList);
        if (PaymentRepositoryUsingList.addPayDueToBaseList(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("Ops, addPayDueList!");
        return null;
    }

    @Override
    public boolean updatePayDueList(PayDue payDueToUpdate) {
        if (payDueToUpdate != null) {
            return PaymentRepositoryUsingList.updatePayDueIfExistsInList(payDueToUpdate);
        }
        logger.info("Ops, updatePayDueList fail!");
        return false;
    }

    @Override
    public boolean deletePayDueByIdList(Long idOfPayDueToDelete) {
        List<PayDue> payDues = PaymentRepositoryUsingList.getPayDuesBaseList();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals((long) idOfPayDueToDelete))) {
            payDues.remove(PaymentRepositoryUsingList.findPayDueByIdInList(idOfPayDueToDelete));
            return true;
        }
        logger.info("Ops, deletePayDueByIdList fail!");
        return false;
    }

    @Override
    public Map<Long, PayDue> getPayDueMap() {
        Map<Long, PayDue> payDueMapToGet = PaymentRepositoryUsingMap.getPayDuesBaseMap();
        if (payDueMapToGet.size() == 0) {
            logger.info("Ops, getPayDueMap Fail! ");
            return payDueMapToGet;
        }
        return payDueMapToGet;
    }

    @Override
    public PayDue getPayDueByMapKey(Long key) {
        if (PaymentRepositoryUsingMap.findPayDueByIdInMap(key).isPresent()) {
            return PaymentRepositoryUsingMap.findPayDueByIdInMap(key).get();
        }
        logger.info("Ops, getPayDueByMapKey fail! ");
        return null;
    }

    @Override
    public PayDue addPayDueToMap(PayDue payDueToAddInMap) {
        Long newIdInMap = PaymentRepositoryUsingMap.getNextIdInMap();
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAddInMap, newIdInMap);
        if (PaymentRepositoryUsingMap.addPayDueToBaseMap(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("Ops, addPayDueToMap fail! ");
        return null;
    }

    @Override
    public boolean updatePayDueMap(PayDue payDueToUpdateInMap) {
        if (payDueToUpdateInMap != null) {
            return PaymentRepositoryUsingMap.updatePayDueIfExistsInMap(payDueToUpdateInMap);
        }
        logger.info("Ops, updatePayDueMap fail! ");
        return false;
    }

    @Override
    public boolean deletePayDueByMapKey(Long keyToDelete) {
        Map<Long, PayDue> payDueMap = PaymentRepositoryUsingMap.getPayDuesBaseMap();
        if (payDueMap.containsKey(keyToDelete)) {
            payDueMap.remove(keyToDelete);
            return true;
        }
        logger.info("Ops, deletePayDueByMapKey fail! ");
        return false;
    }

    @Override
    public SortedSet<PayDue> getPayDueSet() {
        SortedSet<PayDue> payDueSet = PaymentRepositoryUsingSet.getPayDuesSetBase();
        if (payDueSet.isEmpty()) {
            logger.info("Ops, getPayDueSet fail! ");
            return payDueSet;
        }
        return payDueSet;
    }

    @Override
    public PayDue getPayDueByIdSet(Long payDueIdToFindInSet) {
        if (payDueIdToFindInSet != null) {
            return PaymentRepositoryUsingSet.findPayDueByIdInSet(payDueIdToFindInSet);
        }
        logger.info("Ops, getPayDueByIdSet fail! ");
        return null;
    }

    @Override
    public PayDue addPayDueSet(PayDue payDueToAddInSet) {
        Long newIdInSet = PaymentRepositoryUsingSet.getMaxIdInSet();
        PayDue payDueAdded = newPayDueToAddWithMaxId(payDueToAddInSet, newIdInSet);
        if (PaymentRepositoryUsingSet.addPayDueToSet(payDueAdded)) {
            return payDueAdded;
        }
        logger.info("Ops, addPayDueSet fail! ");
        return null;
    }

    @Override
    public boolean updatePayDueSet(PayDue payDueToUpdateInSet) {
        if (payDueToUpdateInSet != null) {
            return PaymentRepositoryUsingSet.updatePayDueIfExistsInSet(payDueToUpdateInSet);
        }
        logger.info("Ops, updatePayDueSet fail! ");
        return false;
    }

    @Override
    public boolean deletePayDueByIdSet(Long payDueIdToDeleteInSet) {
        if (payDueIdToDeleteInSet != null) {
//            logger.info("deletePayDueByIdSet returns");
            return PaymentRepositoryUsingSet.removePayDueByIdIfExistsInSet(payDueIdToDeleteInSet);
        }
        logger.info("Ops, deletePayDueByIdSet fail! ");
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
        logger.info("Ops, newPayDueToAddWithMaxId fail! ");
        return null;
    }
}