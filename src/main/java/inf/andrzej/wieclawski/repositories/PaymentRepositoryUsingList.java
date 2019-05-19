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
        if (payDueArrayListBase.size() == 0) {
            fillPayDuesBaseWithDefaults();
        }
        return payDueArrayListBase;
    }

    private static void fillPayDuesBaseWithDefaults() {
        List<PayDue> payDueListFromRepository = null;
        payDueListFromRepository = PaymentRepository.getRepositoryList();
        payDueArrayListBase.addAll(payDueListFromRepository);
    }

    public static boolean addPayDueToBaseList(PayDue payDueToAdd) {
        if (checkIfPayDueNotExistsInBaseOfPayDues(payDueToAdd)) {
            payDueArrayListBase.add(payDueToAdd);
            return true;
        }
        logger.info("Ops, addPayDueToBaseList fail!"
                + payDueArrayListBase.size());
        return false;
    }

    public static Long getNextIdInList() {
        if (payDueArrayListBase.stream()
                .max(Comparator.comparing(PayDue::getPayDueId))
                .isPresent()) {
            return payDueArrayListBase.stream()
                    .max(Comparator.comparing(PayDue::getPayDueId))
                    .get().getPayDueId() + 1L;
        }
        logger.info("Ops, getNextIdInList fail! ");
        return 1L; // supposed Map is empty
    }

    public static PayDue findPayDueByIdInList(Long idOfPayDueToFind) {
        return
                payDueArrayListBase.stream()
                        .filter(payDue -> (idOfPayDueToFind).equals(payDue.getPayDueId()))
                        .findAny()
                        .orElse(null);
    }

    public static boolean updatePayDueIfExistsInList(PayDue payDueToUpdateInList) {
        if (findPayDueByIdInList(payDueToUpdateInList.getPayDueId()) != null) {
            int index = payDueArrayListBase.indexOf(findPayDueByIdInList(payDueToUpdateInList
                    .getPayDueId()));
            payDueArrayListBase.set(index, payDueToUpdateInList);
            return true;
        }
        logger.info("Ops, updatePayDueIfExistsInList fail!");
        return false;
    }

    // check to avoid duplicated data in payDueArrayListBase
    private static boolean checkIfPayDueNotExistsInBaseOfPayDues(PayDue payDue) {
        return getPayDuesBaseList().stream().noneMatch(o -> o.getPayDueId().equals(payDue.getPayDueId()));
    }


}

