package kr.co.jarvisk.study.tobyreactivex.step2;

import org.reactivestreams.Subscriber;

import java.util.function.Function;

public class MapSubscriber<T, R> extends DelegateSubscriber<T> {

    private Subscriber<R> subscriber;

    private Function<T, R> func;

    public MapSubscriber(Subscriber<R> subscriber, Function<T, R> func) {
        super(subscriber);
        this.subscriber = subscriber;
        this.func = func;
    }

    @Override
    public void onNext(T value) {
        subscriber.onNext(func.apply(value));
    }
}
