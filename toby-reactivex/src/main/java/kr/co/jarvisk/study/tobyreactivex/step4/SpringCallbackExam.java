package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@SpringBootApplication
@EnableAsync
public class SpringCallbackExam {

    @Component
    public static class LongTaskService {

        @Async(value = "threadPool")
        public ListenableFuture<String> hello() throws InterruptedException {
            log.info("Async long time task.");
            Thread.sleep(2000);

            return new AsyncResult<>("Hello");
        }

    }

    @Bean
    ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor tp = new ThreadPoolTaskExecutor();
        tp.setCorePoolSize(10);
        tp.setMaxPoolSize(100);
        tp.setQueueCapacity(50);
        tp.setKeepAliveSeconds(1000);
        tp.setThreadNamePrefix("myThread-");
        tp.initialize();

        return tp;
    }

    public static void main(String[] args) {
        /* ThreadPoolTaskExecutor를 사용해서 하면 스레드를 종료(자원을 종료시켜서..) 시켜서 interrupt 나오는듯...?  (java.lang.InterruptedException: sleep interrupted) */
//        try (ConfigurableApplicationContext ca = SpringApplication.run(SpringCallbackExam.class, args) ) {
//        }
        SpringApplication.run(SpringCallbackExam.class, args);
    }

    @Autowired
    LongTaskService longTaskService;

    @Bean
    public ApplicationRunner runner () {
        return args -> {
            log.info("runner()");
            ListenableFuture<String> f = longTaskService.hello();
            f.addCallback(
                    s -> {
                        log.info("callback() : {}", s);
                        threadPool().destroy();
                    },
                    (t) -> { log.error("callback error ", t);}
                );
            log.info("exit");
        };
    }
}
