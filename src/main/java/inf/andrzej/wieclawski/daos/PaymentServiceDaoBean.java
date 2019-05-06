package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;
import inf.andrzej.wieclawski.repositories.PaymentRepository;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PaymentServiceDaoBean implements PaymentServiceDao {

    private static Logger logger = Logger.getLogger(PaymentServiceDaoBean.class.getName());

    @Override
    public List<PayDue> getPayDueList() {
        List<PayDue> payDues = PaymentRepository.getPayDueBase();
        if (payDues.isEmpty()) {
            logger.info("No list of PayDues in Base");
            return payDues;
        }
        logger.info("Get list of PayDues from Base");
        return payDues;
    }

    @Override
    public PayDue getPayDueById(Long id) {
        List<PayDue> payDues = PaymentRepository.getPayDueBase();
        for (PayDue payDue : payDues) {
            if (payDue.getPayDueId() == id) {
                logger.info("getPayDueById method completed. Get: " + payDue.getPayDueId());
                return payDue;
            }
        }
        logger.info("getPayDueById method have not found any payDue. ");
        return null;
    }

    @Override
    public void addPayDue(PayDue payDueToAdd) {
        Long newId = PaymentRepository.getNextId();
        logger.info("Adding payDue with new Id: " + newId.toString());
        PayDue payDueAdded = new PayDue(
                newId,
                payDueToAdd.getWorker(),
                payDueToAdd.getDaysOfWork(),
                payDueToAdd.getDaysOfDelegation(),
                payDueToAdd.getBilledMonthYear(),
                payDueToAdd.getPaymentPerDay()
        );
        if (PaymentRepository.addPayDueToBase(payDueAdded)) {
            logger.info("New payDue: " + payDueAdded.toString() + " successfully added");
        } else {
            logger.info("The payDue: " + payDueAdded.toString() + " not successfully added");
        }
    }

    @Override
    public PayDue updatePayDue(PayDue payDueToUpdate) {
        logger.info("payDue to update: " + payDueToUpdate.toString());
        List<PayDue> payDues = PaymentRepository.getPayDueBase();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals(payDueToUpdate.getPayDueId()))) {
            logger.info("Found payDue with id: " + payDueToUpdate.getPayDueId());
            int index = payDues.indexOf(PaymentRepository.findPayDueById(payDueToUpdate.getPayDueId()));
            PaymentRepository.getPayDueBase().set(index, payDueToUpdate);
            logger.info("Updated payDue with id: " + payDueToUpdate.getPayDueId());
            return payDueToUpdate;
        }
        logger.info("Not updated payDue with id: " + payDueToUpdate.getPayDueId());
        return payDueToUpdate;
    }

    @Override
    public PayDue DeletePayDueById(Long idOfPayDueToDelete) {
        List<PayDue> payDues = PaymentRepository.getPayDueBase();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals((long) idOfPayDueToDelete))) {
            payDues.remove(PaymentRepository.findPayDueById(idOfPayDueToDelete));
            logger.info("Deleting payDue with id: " + idOfPayDueToDelete);
            return PaymentRepository.findPayDueById(idOfPayDueToDelete);
        }
        logger.info("Can not delete user with id: " + idOfPayDueToDelete);
        return PaymentRepository.findPayDueById(idOfPayDueToDelete);
    }
}
