package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

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
public class AsyncHttpThreadExam {

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

    public static void main(String[] args) {
        SpringApplication.run(AsyncHttpThreadExam.class, args);
    }
}
