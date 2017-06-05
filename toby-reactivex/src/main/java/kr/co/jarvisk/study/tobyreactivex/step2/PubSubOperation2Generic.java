package kr.co.jarvisk.study.tobyreactivex.step2;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * To Generic
 */
@Slf4j
public class PubSubOperation2Generic {

    public static void main(String[] args) {

        /* iterable 1 to 10 */
        Iterable<Integer> iterable = Stream.iterate(1, v -> v + 1).limit(10).collect(Collectors.toList());

        /* make iterable publisher */
        Publisher<Integer> publisher = makeIterablePub(iterable);

        /* make map publisher ( multiply 10) */
        Publisher<Integer> mapPub = makeMapPub(publisher, v -> v * 10);

        /* make  items sum publisher */
        Publisher<Integer> sumPublisher = makeSumPublisher(mapPub);

        /* make to String publisher (Integer -> String)*/
        Publisher<String> mapPublisher = makeMapPublisher(sumPublisher, integer -> "[" + integer + "]");

        /* log subscribe */
        mapPublisher.subscribe(makeLogSub());
    }

    private static <T> Publisher<T> makeSumPublisher(Publisher<Integer> publisher) {
        return (subscriber) -> publisher.subscribe(new DelegateSubscriber<Integer>(subscriber) {
            private Integer sum = 0;

            @Override
            public void onNext(Integer integer) {
//                마지막 결과만 보낸다.
//                super.onNext(integer);
                sum += integer;
            }

            @Override
            public void onComplete() {
                super.onNext(sum);
                super.onComplete();
            }
        });
    }

    private static <T, R> Publisher<R> makeMapPublisher(Publisher<T> publisher, Function<T, R> func) {
        return subscriber -> publisher.subscribe(new MapSubscriber(subscriber, func));
    }

    private static <T> Publisher<T> makeMapPub(Publisher<T> publisher, Function<T, T> func) {
        return subscriber -> {
            publisher.subscribe(new Subscriber<T>() {
                @Override
                public void onSubscribe(Subscription subscription) {
                    subscriber.onSubscribe(subscription);
                }

                @Override
                public void onNext(T integer) {
                    subscriber.onNext(func.apply(integer));
                }

                @Override
                public void onError(Throwable throwable) {
                    subscriber.onError(throwable);
                }

                @Override
                public void onComplete() {
                    subscriber.onComplete();
                }
            });
        };
    }

    private static <T> Publisher<T> makeIterablePub(Iterable<T> iterable) {
        return subscriber -> subscriber.onSubscribe(new Subscription() {
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

    private static <T> Subscriber<T> makeLogSub() {
        return new Subscriber<T>() {
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
        };
    }


}
