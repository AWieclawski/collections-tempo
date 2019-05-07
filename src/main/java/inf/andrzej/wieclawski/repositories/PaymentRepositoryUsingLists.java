package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class PaymentRepositoryUsingLists {

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingLists.class.getName());

    private static List<PayDue> baseOfPayDues = new ArrayList<>();

    public static List<PayDue> getPayDuesBase() {
        logger.info("Before fillPayDuesBaseWithDefaults baseOfPayDues. Size: " + baseOfPayDues.size());
        if (baseOfPayDues.size() == 0) {
            fillPayDuesBaseWithDefaults();
            logger.info("fillPayDuesBaseWithDefaults initialised baseOfPayDues. Size: " + baseOfPayDues.size());
        } else {
            logger.info("baseOfPayDues was already initialised. Size: " + baseOfPayDues.size());
        }
        logger.info("Return baseOfPayDues. Size: " + baseOfPayDues.size());
        return baseOfPayDues;
    }

    private static void fillPayDuesBaseWithDefaults() {
        baseOfPayDues.addAll(PaymentRepository.getRepositoryList());
    }

    public static boolean addPayDueToBase(PayDue payDueToAdd) {
        logger.info("baseOfPayDues size before addPayDueToBase: " + baseOfPayDues.size());
        if (checkIfPayDueNotExistsInBaseOfPayDues(payDueToAdd)) {
            baseOfPayDues.add(payDueToAdd);
            logger.info("baseOfPayDues size after successful addPayDueToBase: " + baseOfPayDues.size());
            return true;
        }
        logger.info("payDueToAdd id exists in baseOfPayDues. Size after not successful addPayDueToBase: "
                + baseOfPayDues.size());
        return false;
    }

    public static Long getNextId() {
        if (checkIfMaxIdExistsInBaseOfPayDues()) {
            PayDue payDueWithMaxId = baseOfPayDues.stream()
                    .max(Comparator.comparing(PayDue::getPayDueId))
                    .get();
            logger.info("getNextId id: " + payDueWithMaxId.getPayDueId() + 1L);
            return payDueWithMaxId.getPayDueId() + 1L;
        }
        return 1L; // supposed Map is empty
    }

    public static PayDue findPayDueById(Long idOfPayDueToFind) {
        logger.info("findPayDueById looking for id: " + idOfPayDueToFind);
        return
                baseOfPayDues.stream()
                        .filter(payDue -> (idOfPayDueToFind).equals(payDue.getPayDueId()))
                        .findAny()
                        .orElse(null);
    }

    private static boolean checkIfPayDueNotExistsInBaseOfPayDues(PayDue payDue) {
        return getPayDuesBase().stream().noneMatch(o -> o.getPayDueId().equals(payDue.getPayDueId()));
    }

    private static boolean checkIfMaxIdExistsInBaseOfPayDues() {
        return baseOfPayDues.stream().max(Comparator.comparing(PayDue::getPayDueId)).isPresent();
    }

}

