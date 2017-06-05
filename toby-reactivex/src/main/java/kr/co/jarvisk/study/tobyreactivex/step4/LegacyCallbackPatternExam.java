package kr.co.jarvisk.study.tobyreactivex.step4;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
public class LegacyCallbackPatternExam {

    public interface SuccessCallback {
        void onSuccess(String s);
    }

    public interface ErrorCallback {
        void onError(Throwable t);
    }

    public static class CallbackFutureTask extends FutureTask<String> {
        SuccessCallback sc;
        ErrorCallback ec;

        public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ErrorCallback ec) {
            super(callable);
            this.sc = Objects.requireNonNull(sc);
            this.ec = Objects.requireNonNull(ec);
        }

        @Override
        protected void done() {
            try {
                sc.onSuccess(get());
            } catch (InterruptedException e) {
                /* Interrupted는 어떠한 오류로 인하여 발생한것이 아니라 현재? 스레드로 interrupt를 넘기는것이 좋다. (by Toby)*/
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                /* ExecutionException은 실제 Exception을 한번 감싸고 있으므로 cause 메서드를 통해 실제 Exception을 뽑아 내야한다. */
                ec.onError(e.getCause());
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();

        es.submit(new CallbackFutureTask(() -> {
            Thread.sleep(2000);
            if ( 1 == 1 ) throw new RuntimeException("Async Error!!");
            log.info("Long time task.");

            return "Hello";
        },
                (s) -> {
                    log.info(s);
                    es.shutdown();
                },
                (t) -> {
                    log.error(t.getMessage());
                    es.shutdown();
                }));

    }

}
