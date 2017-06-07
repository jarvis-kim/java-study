package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Async Http Thread Example
 *
 * Servlet 3.0
 *
 * 1. 요청이 오면 즉시 반환한다.
 * 2. 오래걸리는 작업은 별도의 Worker 스레드에서 처리한다.
 * 3. 작업이 완료되면, 스레드 pool 에서 서블릿 스레드를 할당받아 Response에 쓴다.
 */
@Slf4j
@SpringBootApplication
public class AsyncHttpThreadExam implements ApplicationListener<ApplicationReadyEvent> {

    static AtomicInteger counter = new AtomicInteger(0);

    @RestController
    public static class HelloController {

        /**
         * Long time work method
         * @return
         */
        @GetMapping("/hello")
        public Callable<String> hello() {
            log.info("hello() ");

            /* Very long time work. */
            return () -> {
                log.info("long time work.");
                Thread.sleep(2000);
                return "hi!!";
            };
        }

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(() -> {
            ExecutorService  fixedThreadPool = Executors.newFixedThreadPool(100);

            RestTemplate rt = new RestTemplate();
            String url = "http://localhost:8080/hello";
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            for ( int i = 0; i < 100; i++ ) {
                fixedThreadPool.execute(() -> {
                    int index = counter.addAndGet(1);
                    log.info("thread {}", index);

                    StopWatch sw = new StopWatch();
                    sw.start();

                    String s = rt.getForObject(url, String.class);
                    sw.stop();
                    log.info("Result -> {}", s);
                    log.info("Elapsed {} -> {}", index, sw.getTotalTimeSeconds());
                });
            }

            fixedThreadPool.shutdown();
            fixedThreadPool.awaitTermination(1000, TimeUnit.SECONDS);
            stopWatch.stop();

            log.info("total time : {}", stopWatch.getTotalTimeSeconds());

            es.shutdown();

            return null;
        });

    }

    public static void main(String[] args) {
        SpringApplication.run(AsyncHttpThreadExam.class, args);
    }

}

