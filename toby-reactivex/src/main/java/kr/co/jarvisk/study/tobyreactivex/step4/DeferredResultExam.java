package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * DeferredResult Example
 *
 * DeferredResult는 스프링 3.2이상에서 사용 가능.
 */
@SpringBootApplication
@Slf4j
public class DeferredResultExam {

    @RestController
    public static class MyController {
        Queue<DeferredResult<String>> queue = new ConcurrentLinkedDeque<>();

        /**
         * 즉시 Servlet Thread 를 Pool에 반환 한다.
         * 하지만 요청중인 Connection은 물고 있는다.
         *
         * (Servlet 3.0 비동기 기술)
         * @return
         */
        @GetMapping("/dr")
        public DeferredResult<String> dr () {
            log.info("dr");
            DeferredResult<String> dr = new DeferredResult<>();
            queue.add(dr);

            return dr;
        }

        @GetMapping("/dr/count")
        public Integer count() {
            return queue.size();
        }

        /**
         * Event가 일어나면, 물고있던 객체가 Servlet Thread를 Pool에서 할당받아 Response에 쓴다.
         * 다 쓰고 나면 Pool에 반납한다.
         * 물고있던 Connection도 Close 한다.
         *
         * @param msg
         * @return
         */
        @GetMapping("/dr/event")
        public String drEvent(String msg) {
            DeferredResult<String> dr = null;
            while ( (dr = queue.poll()) != null ) {
                dr.setResult("Message : " + msg);
            }

            return "OK";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DeferredResultExam.class, args);
    }
}
