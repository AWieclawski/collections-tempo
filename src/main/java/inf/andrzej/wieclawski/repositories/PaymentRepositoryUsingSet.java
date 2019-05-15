package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.*;
import java.util.logging.Logger;

public class PaymentRepositoryUsingSet {

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingSet.class.getName());
    private static SortedSet<PayDue> payDueSetBase
            = new TreeSet<>(Comparator.comparing(PayDue::getPayDueId));

    public static SortedSet<PayDue> getPayDuesSetBase() {
        if (payDueSetBase.isEmpty()) {
            fillPayDuesSetBaseWithDefaults();
        }
        return payDueSetBase;
    }

    private static void fillPayDuesSetBaseWithDefaults() {
        List<PayDue> payDueListFromRepository = null;
        payDueListFromRepository = PaymentRepository.getRepositoryList();
        payDueSetBase.addAll(payDueListFromRepository);
    }

    public static boolean addPayDueToSet(PayDue payDueToAdd) {
//        logger.info("addPayDueToSet "+payDueToAdd);
        payDueSetBase.add(payDueToAdd);
        return true;
    }

    public static Long getMaxIdInSet() {
        if (payDueSetBase.stream()
                .max(Comparator.comparing(PayDue::getPayDueId))
                .isPresent()) {
            return payDueSetBase.stream()
                    .max(Comparator.comparing(PayDue::getPayDueId))
                    .get().getPayDueId() + 1L;
        } else return null;
    }

    public static PayDue findPayDueByIdInSet(Long idOdPayDueToFind) {
        return payDueSetBase.stream()
                .filter(payDue -> (idOdPayDueToFind).equals(payDue.getPayDueId()))
                .findAny()
                .orElse(null);
    }

    public static boolean updatePayDueIfExistsInSet(PayDue payDueToUpdateInSet) {
        PayDue payDueToReplace = findPayDueByIdInSet(payDueToUpdateInSet.getPayDueId());
        if (payDueSetBase.contains(payDueToReplace)) {
            payDueSetBase.remove(payDueToReplace);
            // must remove old before update element with the same id
            payDueSetBase.add(payDueToUpdateInSet);
            return true;
        }
        logger.info("Ops! Not updated payDue in set, with id: " + payDueToUpdateInSet.getPayDueId());
        return false;
    }

    public static boolean removePayDueByIdIfExistsInSet(Long payDueIdToRemoveInSet) {
        PayDue payDueToRemoveInSet = findPayDueByIdInSet(payDueIdToRemoveInSet);
//        logger.info("payDueToRemoveInSet: " + payDueToRemoveInSet);
        if (payDueToRemoveInSet != null) {
            if (payDueSetBase.contains(payDueToRemoveInSet)) {
//                logger.info("payDueToRemoveInSet returned true");
                payDueSetBase.remove(payDueToRemoveInSet);
                return true;
            }
        }
        logger.info("Ops! Not deleted payDue in set, with id " + payDueIdToRemoveInSet);
        return false;
    }
}
