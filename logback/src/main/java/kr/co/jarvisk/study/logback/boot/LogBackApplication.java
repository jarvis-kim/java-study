package kr.co.jarvisk.study.logback.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LogBackApplication  implements CommandLineRunner {

    public static final void main(String[] args) {

        SpringApplication.run(LogBackApplication.class);
    }

    @Autowired
    private LogTest logTest;

    @Override
    public void run(String... strings) throws Exception {
        logTest.printLogTest();
    }
}
