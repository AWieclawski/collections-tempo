package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentRepositoryUsingMaps {

    private static Map<Long, PayDue> baseOfPayDuesMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingMaps.class.getName());

    public static Map<Long, PayDue> getPayDuesBaseMap() {

        if (baseOfPayDuesMap.size() == 0) {
            logger.info("Before fillPayDuesBaseMapWithDefaults baseOfPayDuesMap size: " + baseOfPayDuesMap.size());
            fillPayDuesBaseMapWithDefaults();
        } else {
            logger.info("baseOfPayDuesMap already initialised size: " + baseOfPayDuesMap.size());
        }
        logger.info("Return baseOfPayDuesMap size: " + baseOfPayDuesMap.size());
        return baseOfPayDuesMap;
    }

    private static void fillPayDuesBaseMapWithDefaults() {
        for (PayDue payDue : PaymentRepository.getRepositoryList()) {
            baseOfPayDuesMap.put(payDue.getPayDueId(), payDue);
        }
        logger.info("After fillPayDuesBaseMapWithDefaults baseOfPayDuesMap size: "
                + baseOfPayDuesMap.size());
    }

    public static boolean addPayDueToBaseMap(PayDue payDueToAdd) {
        logger.info("baseOfPayDues size before addPayDueToBase: " + baseOfPayDuesMap.size());
        if (checkIfPayDueNotExistsInBaseOfPayDuesMap(payDueToAdd)) {
            baseOfPayDuesMap.put(payDueToAdd.getPayDueId(), payDueToAdd);
            return true;
        }
        logger.info("baseOfPayDues size after addPayDueToBase: " + baseOfPayDuesMap.size());
        return false;
    }

    public static Long getNextIdInMap() {
        return baseOfPayDuesMap.keySet().stream()
                .mapToLong(i -> i)
                .max().orElse(0L) + 1L;
    }

    private static boolean checkIfPayDueNotExistsInBaseOfPayDuesMap(PayDue payDue) {
        return !baseOfPayDuesMap.containsKey(payDue.getPayDueId());
    }

}
