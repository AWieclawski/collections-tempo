package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;
import inf.andrzej.wieclawski.repositories.PaymentRepositoryUsingLists;
import inf.andrzej.wieclawski.repositories.PaymentRepositoryUsingMaps;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Stateless
public class PaymentServiceDaoBean implements PaymentServiceDao {

    private static Logger logger = Logger.getLogger(PaymentServiceDaoBean.class.getName());

    @Override
    public List<PayDue> getPayDueList() {
        List<PayDue> payDues = PaymentRepositoryUsingLists.getPayDuesBase();
        if (payDues.isEmpty()) {
            logger.info("No list of PayDues in Base");
            return payDues;
        }
        logger.info("Get list of PayDues from Base");
        return payDues;
    }

    @Override
    public PayDue getPayDueById(Long id) {
        List<PayDue> payDues = PaymentRepositoryUsingLists.getPayDuesBase();
        for (PayDue payDue : payDues) {
            if (payDue.getPayDueId() == id) {
                logger.info("getPayDueById method completed. Get: " + payDue.getPayDueId());
                return payDue;
            }
        }
        logger.info("getPayDueById method have not found any payDue with id: " + id);
        return null;
    }

    @Override
    public PayDue addPayDue(PayDue payDueToAdd) {
        Long newId = PaymentRepositoryUsingLists.getNextId();
        logger.info("Adding payDue with new Id: " + newId.toString());
        if (payDueToAdd != null) {
            PayDue payDueAdded = new PayDue(
                    newId,
                    payDueToAdd.getWorker(),
                    payDueToAdd.getDaysOfWork(),
                    payDueToAdd.getDaysOfDelegation(),
                    payDueToAdd.getBilledMonthYear(),
                    payDueToAdd.getPaymentPerDay()
            );
            if (PaymentRepositoryUsingLists.addPayDueToBase(payDueAdded)) {
                logger.info("New payDue: " + payDueAdded.toString()
                        + " successfully added to list");
                return payDueAdded;
            } else {
                logger.info("The payDue: " + payDueAdded.toString()
                        + " not successfully added to list");
                return null;
            }
        } else {
            logger.info("addPayDue is null ");
            return null;
        }
    }

    @Override
    public boolean updatePayDue(PayDue payDueToUpdate) {
        if (payDueToUpdate != null) {
            logger.info("payDue to update: " + payDueToUpdate.toString());
            List<PayDue> payDues = PaymentRepositoryUsingLists.getPayDuesBase();
            if (payDues.stream().anyMatch(o -> o.getPayDueId().equals(payDueToUpdate.getPayDueId()))) {
                logger.info("Found and updated payDue with id: " + payDueToUpdate.getPayDueId());
                int index = payDues.indexOf(PaymentRepositoryUsingLists.findPayDueById(payDueToUpdate.getPayDueId()));
                PaymentRepositoryUsingLists.getPayDuesBase().set(index, payDueToUpdate);
                logger.info("Updated payDue with id: " + payDueToUpdate.getPayDueId());
                return true;
            }
            logger.info("Not updated payDue with id: " + payDueToUpdate.getPayDueId());
            return false;
        }
        logger.info("Not updated. payDue is empty ");
        return false;
    }

    @Override
    public boolean deletePayDueById(Long idOfPayDueToDelete) {
        List<PayDue> payDues = PaymentRepositoryUsingLists.getPayDuesBase();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals((long) idOfPayDueToDelete))) {
            payDues.remove(PaymentRepositoryUsingLists.findPayDueById(idOfPayDueToDelete));
            logger.info("Deleting payDue with id: " + idOfPayDueToDelete);
            return true;
        }
        logger.info("Can not delete payDue with id: " + idOfPayDueToDelete);
        return false;
    }

    @Override
    public Map<Long, PayDue> getPayDueMap() {
        Map<Long, PayDue> payDueMapToGet = PaymentRepositoryUsingMaps.getPayDuesBaseMap();
        if (payDueMapToGet.size() == 0) {
            logger.info("No map of PayDues in Base");
            return payDueMapToGet;
        }
        logger.info("PayDues map in Base: " + payDueMapToGet.size());
        return payDueMapToGet;
    }

    @Override
    public PayDue getPayDueByMapKey(Long key) {
        Map<Long, PayDue> payDueMap = PaymentRepositoryUsingMaps.getPayDuesBaseMap();
        for (Map.Entry<Long, PayDue> payDueEntry : payDueMap.entrySet()) {
            if (payDueEntry.getKey().equals(key)) {
                logger.info("getPayDueByMapKey method completed. Found payDue with key: "
                        + payDueEntry.getKey());
                return payDueEntry.getValue();
            }
        }
        logger.info("getPayDueByMapKey method have not found any payDue with key: " + key);
        return null;
    }

    @Override
    public PayDue addPayDueToMap(PayDue payDueToAddInMap) {
        Long newKeyId = PaymentRepositoryUsingMaps.getNextIdInMap();
        logger.info("Adding payDue with new key: " + newKeyId.toString());
        if (payDueToAddInMap != null) {
            PayDue payDueAdded = new PayDue(
                    newKeyId,
                    payDueToAddInMap.getWorker(),
                    payDueToAddInMap.getDaysOfWork(),
                    payDueToAddInMap.getDaysOfDelegation(),
                    payDueToAddInMap.getBilledMonthYear(),
                    payDueToAddInMap.getPaymentPerDay()
            );
            if (PaymentRepositoryUsingMaps.addPayDueToBaseMap(payDueAdded)) {
                logger.info("New payDueAdded: " + payDueAdded.toString()
                        + " successfully added to map");
                return payDueAdded;
            } else {
                logger.info("The payDue: " + payDueToAddInMap.toString()
                        + " not successfully added to map");
                return null;
            }
        } else {
            logger.info("payDueToAddInMap is null ");
        }
        return null;
    }

    @Override
    public boolean updatePayDueMap(PayDue payDueToUpdate) {
        if (payDueToUpdate != null) {
            Map<Long, PayDue> payDueMap = PaymentRepositoryUsingMaps.getPayDuesBaseMap();
            if (payDueMap.containsKey(payDueToUpdate.getPayDueId())) {
                logger.info("Found and updated payDue with key: " + payDueToUpdate.getPayDueId());
                payDueMap.put(payDueToUpdate.getPayDueId(), payDueToUpdate);
                return true;
            }
            logger.info("Not updated payDue with key: " + payDueToUpdate.getPayDueId());
            return false;
        }
        logger.info("Not updated. payDueToUpdate is null ");
        return false;
    }

    @Override
    public boolean deletePayDueByMapKey(Long keyToDelete) {
        Map<Long, PayDue> payDueMap = PaymentRepositoryUsingMaps.getPayDuesBaseMap();
        if (payDueMap.containsKey(keyToDelete)) {
            logger.info("Found and deleted payDue with key: " + keyToDelete);
            payDueMap.remove(keyToDelete);
            return true;
        }
        logger.info("Can not delete payDue with key: " + keyToDelete);
        return false;
    }
}
