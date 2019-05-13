package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentRepositoryUsingMap {

    private static Map<Long, PayDue> baseOfPayDuesMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingMap.class.getName());

    public static Map<Long, PayDue> getPayDuesBaseMap() {
//        logger.info("Before fillPayDuesBaseWithDefaults baseOfPayDues size: " + baseOfPayDuesMap.size());
        if (baseOfPayDuesMap.size() == 0) {
            fillPayDuesBaseMapWithDefaults();
//            logger.info("fillPayDuesBaseWithDefaults initialised baseOfPayDues size: " + baseOfPayDuesMap.size());
        } else {
//            logger.info("baseOfPayDuesMap already initialised size: " + baseOfPayDuesMap.size());
        }
//        logger.info("Return baseOfPayDuesMap size: " + baseOfPayDuesMap.size());
        return baseOfPayDuesMap;
    }

    private static void fillPayDuesBaseMapWithDefaults() {
        List<PayDue> payDueListToFill = null;
        payDueListToFill = PaymentRepository.getRepositoryList();
//        logger.info("Before fillPayDuesBaseMapWithDefaults PaymentRepository.getRepositoryList() size: "
//                + payDueListToFill.size());
        for (PayDue payDueInMap : payDueListToFill) {
            baseOfPayDuesMap.put(payDueInMap.getPayDueId(), payDueInMap);
        }
//        logger.info("After fillPayDuesBaseMapWithDefaults baseOfPayDuesMap size: "
//                + baseOfPayDuesMap.size());
    }

    public static boolean addPayDueToBaseMap(PayDue payDueToAdd) {
//        logger.info("baseOfPayDuesMap size before addPayDueToBaseList: " + baseOfPayDuesMap.size());
        if (checkIfPayDueNotExistsInBaseOfPayDuesMap(payDueToAdd)) {
            baseOfPayDuesMap.put(payDueToAdd.getPayDueId(), payDueToAdd);
            return true;
        }
        logger.info("Ops! PayDueExistsInBaseOfPayDuesMap baseOfPayDuesMap size after addPayDueToBaseList: "
                + baseOfPayDuesMap.size());
        return false;
    }

    public static Long getNextIdInMap() {
//        logger.info("baseOfPayDuesMap size before getNextIdInMap: " + baseOfPayDuesMap.size());
        return baseOfPayDuesMap.keySet().stream()
                .max(Long::compareTo)
                .orElse(0L) + 1L;
    }

    private static boolean checkIfPayDueNotExistsInBaseOfPayDuesMap(PayDue payDue) {
        return !baseOfPayDuesMap.containsKey(payDue.getPayDueId());
    }

}
