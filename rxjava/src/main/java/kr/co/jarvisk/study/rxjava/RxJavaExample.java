package kr.co.jarvisk.study.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaExample {

    public static final void main(String[] args) throws InterruptedException {

        justExample();
        fromExample();
        deferExample();
        asyncDeferExample();
        asyncDeferMapExample();
        lambdaExample();
    }


    public static void justExample() {
        System.out.println("-----------------------(just)-----------------------");
        System.out.println("create Observable.(just)");
        Observable<String> observable =  Observable.just("개미");

        System.out.println("do subscribe.");
        observable.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("call onComplete.");
            }

            public void onError(Throwable throwable) {
                System.out.println("call onError.");
            }

            public void onNext(String s) {
                System.out.println("call onNext. : " + s);
            }
        });

        System.out.println("end.");
        System.out.println("-----------------------(just)-----------------------");
    }

    public static final void fromExample() {
        System.out.println("-----------------------(from)-----------------------");
        System.out.println("create Observable.(from)");
        Observable<String> observable =  Observable.from(new String[] { "개미", "매미", "마루"});

        System.out.println("do subscribe.");
        observable.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("call onComplete.");
            }

            public void onError(Throwable throwable) {
                System.out.println("call onError.");
            }

            public void onNext(String s) {
                System.out.println("call onNext. : " + s);
            }
        });

        System.out.println("end.");
        System.out.println("-----------------------(from)-----------------------");
    }

    public static final void deferExample() {
        System.out.println("-----------------------(defer)-----------------------");
        System.out.println("create Observable.(defer)");
        Observable<String> observable =  Observable.defer(new Func0<Observable<String>>() {
            public Observable<String> call() {
                System.out.println("call defer.");
                return Observable.from(new String[] { "개미", "매미", "마루"});
            }
        });

        System.out.println("do subscribe.");
        observable.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("call onComplete.");
            }

            public void onError(Throwable throwable) {
                System.out.println("call onError.");
            }

            public void onNext(String s) {
                System.out.println("call onNext. : " + s);
            }
        });

        System.out.println("end.");
        System.out.println("-----------------------(defer)-----------------------");
    }

    public static void asyncDeferExample() {
        System.out.println("-----------------------(async defer)-----------------------");
        System.out.println(Thread.currentThread().getName() + " # create Observable.(defer)");
        Observable<String> observable =  Observable.defer(new Func0<Observable<String>>() {
            public Observable<String> call() {
                System.out.println(Thread.currentThread().getName() + " # call defer.");
                return Observable.from(new String[] { "개미", "매미", "마루"});
            }
        });

        System.out.println(Thread.currentThread().getName() +  " # do subscribe.");

        observable
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + " # call onComplete.");
                    }

                    public void onError(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName() + " # call onError.");
                    }

                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + " # call onNext. : " + s);
                    }
                });

        System.out.println(Thread.currentThread().getName() + " # end.");
        System.out.println("-----------------------(async defer)-----------------------");

        sleep(500);
    }

    public static void asyncDeferMapExample() {
        System.out.println("-----------------------(async defer)-----------------------");
        System.out.println(Thread.currentThread().getName() + " # create Observable.(defer)");
        Observable<String> observable =  Observable.defer(new Func0<Observable<String>>() {
            public Observable<String> call() {
                System.out.println(Thread.currentThread().getName() + " # call defer.");
                return Observable.from(new String[] { "개미", "매미", "마루"});
            }
        });

        System.out.println(Thread.currentThread().getName() +  " # do subscribe.");

        Observable<String> observable2 = observable
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    public String call(String s) {
                        return "<" + s + ">";
                    }
                });

                observable2.subscribe(new Subscriber<String>() {
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + " # call onComplete.");
                    }

                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + " # call onNext. : " + s);
                    }

                    public void onError(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName() + " # call onError.");
                    }
                });

        System.out.println(Thread.currentThread().getName() + " # end.");
        System.out.println("-----------------------(async defer)-----------------------");

        sleep(500);
    }

    private static void sleep(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void lambdaExample() {
        System.out.println("-----------------------(lambda)-----------------------");
        System.out.println("create Observable.(just)");
        Observable<String> observable =  Observable.just("개미");

        System.out.println("do subscribe.");
        observable.subscribe(
                text -> System.out.println("call onNext by lambda. : " + text),
                e -> System.out.println("call onError by lambda."),
                () -> System.out.println("call onComplete by lambda.")
        );
        observable.subscribe(new Subscriber<String>() {
            public void onCompleted() {
                System.out.println("call onComplete.");
            }

            public void onError(Throwable throwable) {
                System.out.println("call onError.");
            }

            public void onNext(String s) {
                System.out.println("call onNext. : " + s);
            }
        });

        System.out.println("end.");
        System.out.println("-----------------------(lambda)-----------------------");
    }
}
