package kr.co.jarvisk.study.tobyreactivex.step1;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class PubSubSimple {

    public static void main(String[] args) {

        Iterable<Integer> iterable = Stream.iterate(1, v -> v + 1).limit(10).collect(Collectors.toList());

        Publisher<Integer> publisher = new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> subscriber) {
                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long l) {
                        try {
                            iterable.forEach(subscriber::onNext);
                            subscriber.onComplete();
                        } catch (Throwable t) {
                            subscriber.onError(t);
                        }
                    }

                    @Override
                    public void cancel() {
                        // ignore
                    }
                });
            }
        };

        publisher.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                log.info("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
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
        });
    }


}
