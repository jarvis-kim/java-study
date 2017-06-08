package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * ResponseBodyEmitter Example
 *
 * Http SEE 기술을 사용한 스트리밍 데이터 처리
 */
@SpringBootApplication
@Slf4j
public class ResponseBodyEmitterExam {

    @RestController
    public static class ResponseBodyEmitterController {

        @GetMapping("/sayHello")
        public ResponseBodyEmitter sayHello(int count) {
            ResponseBodyEmitter responseBodyEmitter = new ResponseBodyEmitter();

            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    for ( int i = 0; i < count; i++ ) {
                        responseBodyEmitter.send("<p>hello! - " + i + "</p>");
                        Thread.sleep(200);
                    }
                    responseBodyEmitter.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            return responseBodyEmitter;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ResponseBodyEmitterExam.class, args);
    }
}
