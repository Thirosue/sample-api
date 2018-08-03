package com.sample.domain.service.system;

import lombok.val;
import static java.time.LocalDateTime.now;
import static com.sample.domain.dto.common.TransactionLock.lockByTransactionAndFunction;

public class TransactionLockService {

    public static void set(String key) {
        lockByTransactionAndFunction.put(key, now().plusSeconds(2));
    }

    public static boolean exists(String key) {
        return lockByTransactionAndFunction.containsKey(key) &&
                now().isBefore(lockByTransactionAndFunction.get(key));
    }

    public static void clear(String key) {
        lockByTransactionAndFunction.remove(key);
    }
}
