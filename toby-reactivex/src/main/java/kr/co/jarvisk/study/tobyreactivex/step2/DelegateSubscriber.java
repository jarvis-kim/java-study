package kr.co.jarvisk.study.tobyreactivex.step2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DelegateSubscriber<T> implements Subscriber<T> {

    private Subscriber<T> subscriber;

    public DelegateSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T value) {
        subscriber.onNext(value);
    }

    @Override
    public void onError(Throwable throwable) {
         subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }

    protected Subscriber<T> getOriginal() {
        return subscriber;
    }
}
