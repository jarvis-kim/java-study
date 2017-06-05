package kr.co.jarvisk.study.tobyreactivex.step3;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IntervalExam {
    public static void main(String[] args) {

        Publisher<Integer> publisher = subscriber -> {
            subscriber.onSubscribe(new Subscription() {

                ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

                private int number = 0;

                private boolean cancelled = false;

                @Override
                public void request(long l) {
                    ses.scheduleAtFixedRate(() -> {
                        if ( cancelled ) {
                            ses.shutdown();
                            return;
                        }
                        subscriber.onNext(number++);
                    }, 0, 300, TimeUnit.MILLISECONDS);
                }

                @Override
                public void cancel() {
                    cancelled = true;
                }
            });
        };

        publisher.subscribe(new Subscriber<Integer>() {
            private Subscription subscription;

            private int count = 0;

            private int limit = 5;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                if ( ++count > limit ) {
                    subscription.cancel();
                    return;
                }
                log.info("onNext : {}", integer);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

        log.info("exit");

    }
}
