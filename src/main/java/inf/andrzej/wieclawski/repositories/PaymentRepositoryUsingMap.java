package inf.andrzej.wieclawski.repositories;

import inf.andrzej.wieclawski.models.PayDue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class PaymentRepositoryUsingMap {

    private static Map<Long, PayDue> baseOfPayDuesMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(PaymentRepositoryUsingMap.class.getName());

    public static Map<Long, PayDue> getPayDuesBaseMap() {
        if (baseOfPayDuesMap.size() == 0) {
            fillPayDuesBaseMapWithDefaults();
        }
        return baseOfPayDuesMap;
    }

    private static void fillPayDuesBaseMapWithDefaults() {
        List<PayDue> payDueListToFill = null;
        payDueListToFill = PaymentRepository.getRepositoryList();
        for (PayDue payDueInMap : payDueListToFill) {
            baseOfPayDuesMap.put(payDueInMap.getPayDueId(), payDueInMap);
        }
    }

    public static boolean addPayDueToBaseMap(PayDue payDueToAdd) {
        baseOfPayDuesMap.put(payDueToAdd.getPayDueId(), payDueToAdd);
        return true;
    }

    public static Optional<PayDue> findPayDueByIdInMap(Long payDueIdTFindInMap) {
        return Optional.ofNullable(baseOfPayDuesMap.get(payDueIdTFindInMap));
    }

    public static Long getNextIdInMap() {
        return baseOfPayDuesMap.keySet().stream()
                .max(Long::compareTo)
                .orElse(0L) + 1L;
    }

    public static boolean updatePayDueIfExistsInMap(PayDue payDueToCheckInMap) {
        if (findPayDueByIdInMap(payDueToCheckInMap.getPayDueId()).isPresent()) {
            baseOfPayDuesMap.put(payDueToCheckInMap.getPayDueId(), payDueToCheckInMap);
//            logger.info("Success! Updated payDue in map with key: " + payDueToCheckInMap.getPayDueId());
            return true;
        }
        logger.info("Ops! Not updated payDue in map: " + payDueToCheckInMap);
        return false;
    }

}
