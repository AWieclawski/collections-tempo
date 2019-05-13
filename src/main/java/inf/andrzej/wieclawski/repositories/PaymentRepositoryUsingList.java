package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class PaymentRepositoryUsingList {

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingList.class.getName());

    private static List<PayDue> payDueArrayListBase = new ArrayList<>();

    public static List<PayDue> getPayDuesBaseList() {
//        logger.info("Before fillPayDuesBaseWithDefaults payDueArrayListBase. Size: " + payDueArrayListBase.size());
        if (payDueArrayListBase.size() == 0) {
            fillPayDuesBaseWithDefaults();
//            logger.info("fillPayDuesBaseWithDefaults initialised payDueArrayListBase. Size: " + payDueArrayListBase.size());
        } else {
//            logger.info("payDueArrayListBase was already initialised. Size: " + payDueArrayListBase.size());
        }
//        logger.info("Return payDueArrayListBase. Size: " + payDueArrayListBase.size());
        return payDueArrayListBase;
    }

    private static void fillPayDuesBaseWithDefaults() {
        List<PayDue> payDueListFromRepository = null;
        payDueListFromRepository = PaymentRepository.getRepositoryList();
//        logger.info("Before fillPayDuesBaseMapWithDefaults PaymentRepository.getRepositoryList() size: "
//                + payDueListFromRepository.size());
        payDueArrayListBase.addAll(payDueListFromRepository);
    }

    public static boolean addPayDueToBaseList(PayDue payDueToAdd) {
//        logger.info("payDueArrayListBase size before addPayDueToBaseList: " + payDueArrayListBase.size());
        if (checkIfPayDueNotExistsInBaseOfPayDues(payDueToAdd)) {
            payDueArrayListBase.add(payDueToAdd);
//            logger.info("payDueArrayListBase size after successful addPayDueToBaseList: " + payDueArrayListBase.size());
            return true;
        }
        logger.info("Ops! payDueToAdd id exists in payDueArrayListBase. Size after not successful addPayDueToBaseList: "
                + payDueArrayListBase.size());
        return false;
    }

    public static Long getNextIdinList() {
        if (checkIfMaxIdExistsInBaseOfPayDues()) {
            PayDue payDueWithMaxId = payDueArrayListBase.stream()
                    .max(Comparator.comparing(PayDue::getPayDueId))
                    .get(); // isPresent checked by checkIfMaxIdExistsInBaseOfPayDues
            Long newIdFound = 1L + payDueWithMaxId.getPayDueId();
//            logger.info("getNextIdinList found new id: " + newIdFound);
            return newIdFound;
        }
        logger.info("Ops! getNextIdinList not found max id. New id: " + 1L);
        return 1L; // supposed Map is empty
    }

    public static PayDue findPayDueByIdInList(Long idOfPayDueToFind) {
//        logger.info("findPayDueByIdInList looking for id: " + idOfPayDueToFind);
        return
                payDueArrayListBase.stream()
                        .filter(payDue -> (idOfPayDueToFind).equals(payDue.getPayDueId()))
                        .findAny()
                        .orElse(null);
    }

    private static boolean checkIfPayDueNotExistsInBaseOfPayDues(PayDue payDue) {
        return getPayDuesBaseList().stream().noneMatch(o -> o.getPayDueId().equals(payDue.getPayDueId()));
    }

    private static boolean checkIfMaxIdExistsInBaseOfPayDues() {
        return payDueArrayListBase.stream().max(Comparator.comparing(PayDue::getPayDueId)).isPresent();
    }

}

