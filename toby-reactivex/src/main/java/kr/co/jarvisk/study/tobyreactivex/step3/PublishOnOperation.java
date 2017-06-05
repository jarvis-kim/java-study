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
 * PublishOn
 */
@Slf4j
public class PublishOnOperation {

    public static void main(String[] args) {

        Publisher<Integer> publisher = subscriber -> {

            subscriber.onSubscribe(new Subscription() {
                @Override
                public void request(long l) {
                    log.info("request()");
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

        Publisher<Integer> publishOnPub = subscriber -> {
            publisher.subscribe(new Subscriber<Integer>() {
                ExecutorService es = Executors.newSingleThreadExecutor();
                @Override
                public void onSubscribe(Subscription subscription) {
                    subscriber.onSubscribe(subscription);
                }

                @Override
                public void onNext(Integer integer) {
                    es.execute(() -> subscriber.onNext(integer));
                }

                @Override
                public void onError(Throwable throwable) {
                    es.execute(() -> subscriber.onError(throwable));
                    es.shutdown();
                }

                @Override
                public void onComplete() {
                    es.execute(() -> subscriber.onComplete());
                    es.shutdown();
                }
            });
        };

        publishOnPub.subscribe(new LogSubscriber<>());

        log.info("exit");
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
