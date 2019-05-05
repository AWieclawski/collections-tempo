package inf.andrzej.wieclawski.daos;

import inf.andrzej.wieclawski.models.PayDue;
import inf.andrzej.wieclawski.repositories.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentServiceDaoBean implements PaymentServiceDao {

    private List<PayDue> payDues = new ArrayList<>();

    private static Logger logger = Logger.getLogger(PaymentServiceDaoBean.class.getName());

    @Override
    public List<PayDue> getPayDueList() {
        payDues.clear();
        payDues = PaymentRepository.getPayDueBase();
        if (payDues.isEmpty()) {
            logger.info("No list of PayDues in Base");
            return payDues;
        }
        logger.info("Get list of PayDues from Base");
        return payDues;
    }

    @Override
    public PayDue getPayDueById(Long id) {
        payDues.clear();
        payDues = PaymentRepository.getPayDueBase();
        for (PayDue payDue : payDues) {
            if (payDue.getPayDueId() == id) {
                logger.info("getPayDueById method completed. Get: " + payDue.toString());
                return payDue;
            }
        }
        logger.info("getPayDueById method have not found any payDue. ");
        return null;
    }

    @Override
    public void addPayDue(PayDue payDue) {
        Long newId = PaymentRepository.getNextId();
        logger.info("Adding payDue with new Id: " + newId.toString());
        PaymentRepository.addPayDueToBase(new PayDue(
                newId,
                payDue.getWorker(),
                payDue.getDaysOfWork(),
                payDue.getDaysOfDelegation(),
                payDue.getBilledMonthYear(),
                payDue.getPaymentPerDay()
        ));
        logger.info("New payDue id: " + payDue.toString() + " successfully added");
    }

    @Override
    public PayDue updatePayDue(PayDue payDueToUpdate) {
        payDues.clear();
        payDues = PaymentRepository.getPayDueBase();
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
        payDues.clear();
        payDues = PaymentRepository.getPayDueBase();
        if (payDues.stream().anyMatch(o -> o.getPayDueId().equals((long) idOfPayDueToDelete))) {
            payDues.remove(PaymentRepository.findPayDueById(idOfPayDueToDelete));
            logger.info("Deleting payDue with id: " + idOfPayDueToDelete);
            return PaymentRepository.findPayDueById(idOfPayDueToDelete);
        }
        logger.info("Can not delete user with id: " + idOfPayDueToDelete);
        return PaymentRepository.findPayDueById(idOfPayDueToDelete);
    }
}
