package com.sample.web.admin.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;
import static com.sample.domain.dto.common.TransactionLock.lockByTransactionAndFunction;

@Component
@Slf4j
public class CacheClear {

    @Scheduled(initialDelay=0, fixedDelay=60000*10) //10 min
    public void clear() {
        log.info("clear expired cache");
        if(lockByTransactionAndFunction != null) {
            lockByTransactionAndFunction.entrySet().stream()
                    .filter(e -> e.getValue().isBefore(now().minusMinutes(10)))
                    .forEach(e-> lockByTransactionAndFunction.remove(e.getKey()));
        }
    }
}
