package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.*;
import java.util.logging.Logger;

public class PaymentRepositoryUsingSet {

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingSet.class.getName());
    private static Set<PayDue> payDueSetBase = new HashSet<>();

    public static Set<PayDue> getPayDuesSetBase() {
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

    private static boolean addPayDueToSet(PayDue payDueToAdd) {
        payDueSetBase.add(payDueToAdd);
        return true;
    }

    private static Long getMaxIdInSet() {
        if (payDueSetBase.stream()
                .max(Comparator.comparing(PayDue::getPayDueId))
                .isPresent()) {
            return 1L + payDueSetBase.stream()
                    .max(Comparator.comparing(PayDue::getPayDueId))
                    .get().getPayDueId();
        } else return null;
    }

    private static PayDue findPayDueByIdInSet(Long idOdPayDueToFind) {
        PayDue payDueToFind = Objects.requireNonNull(payDueSetBase.stream()
                .filter(payDue -> (idOdPayDueToFind).equals(payDue.getPayDueId()))
                .findAny()
                .orElse(null)
        );
        return payDueToFind;
    }
}
