package kr.co.jarvisk.study.logback.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LogBackApplication  {

    public static final void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(LogBackApplication.class);
        LogTest logTest = applicationContext.getBean(LogTest.class);

        logTest.printLogTest();
    }
}
