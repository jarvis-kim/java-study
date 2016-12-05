package kr.co.jarvisk.study.logback.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogTest {

    public void printLogTest() {

        String methodName = Thread.currentThread().getStackTrace()[ 1 ].getMethodName();
        log.error("S-----------------------" + methodName + "-----------------------S");
        log.trace("trace");
        log.debug("debug");

        log.debug("is debug mode : {}", log.isDebugEnabled());

        log.info("info");
        log.warn("warn");
        log.error("error");
        log.error("E-----------------------" + methodName + "-----------------------E");

    }

}
