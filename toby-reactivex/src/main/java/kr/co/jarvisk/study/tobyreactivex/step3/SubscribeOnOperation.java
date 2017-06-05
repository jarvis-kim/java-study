package kr.co.jarvisk.study.tobyreactivex.step3;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * SubscribeOn 방식
 */
@Slf4j
public class SubscribeOnOperation {

    public static void main(String[] args) {

        Publisher<Integer> publisher = subscriber -> {

            subscriber.onSubscribe(new Subscription() {
                @Override
                public void request(long l) {
                    Iterable<Integer> iterable = Stream.iterate(1, v -> v + 1).limit(10).collect(Collectors.toList());
                    try {
                        iterable.forEach(subscriber::onNext);
                        subscriber.onComplete();
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }

                @Override
                public void cancel() {

                }
            });
        };

        Publisher<Integer> subscribeOnPub = subscriber -> {
            ExecutorService es = Executors.newSingleThreadExecutor();
            es.execute(() -> publisher.subscribe(subscriber));
            es.shutdown();
        };

        subscribeOnPub.subscribe(new LogSubscriber<>());
    }


    static class LogSubscriber<T> implements Subscriber<T> {
        @Override
        public void onSubscribe(Subscription subscription) {
            log.info("onSubscribe");
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(T integer) {
            log.info("onNext() : {}", integer);
        }

        @Override
        public void onError(Throwable throwable) {
            log.info("onError() : {}", throwable);
        }

        @Override
        public void onComplete() {
            log.info("onComplete()");
        }
    }
}
