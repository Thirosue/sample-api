package com.sample.domain.dto.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TransactionLock implements Serializable {

    public static Map<String, LocalDateTime> lockByTransactionAndFunction = Collections.synchronizedMap(new HashMap<>());

}
