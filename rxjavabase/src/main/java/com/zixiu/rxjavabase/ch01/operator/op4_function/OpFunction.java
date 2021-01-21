package com.zixiu.rxjavabase.ch01.operator.op4_function;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Author: Snail
 * Time:  2021/1/19 4:49 PM
 * FileName:  OpFunction
 * <p>
 * 简介：功能性操作符
 * [1]连接被观察者/观察者
 * subscribe()
 * [2]线程调度：
 * 2.1 subscribeOn
 * 2.2 observableOn
 * [3]延迟操作
 * delay()
 * [4]在事件的生命周期中操作
 * do()
 * [5]错误处理（遇到错误时的机制）
 * 5.1 onErrorReturn()
 * 5.2 onErrorResumeNext()
 * 5.3 onErrorExceptionNext()
 * 5.4 retry()
 * 5.5 retryUntil()
 * 5.6 retryWhen()
 * [6]重复发送操作
 * 6.1 repeat()
 * 6.2 repeatWhen()
 *
 *
 * </p>
 */
public class OpFunction {

    public static void main(String[] args) throws InterruptedException {
        /**
         * <p>
         *     4.1 subscribe()操作符不再赘述
         * </p>
         */

        /**
         * <p>
         *     4.2 线程调度单独成文（subscribeOn(),observableOn()）
         * </p>
         */

        /**
         * <p>
         *     4.3 delay() 使得被观察者延迟一段时间再发送事件
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        })
//                .delay(3, TimeUnit.SECONDS)//指定延时时间，时间单位
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("delay() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("delay() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("delay() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("delay() -> onComplete()");
//                    }
//                });

        /**
         * <p>
         *     4.4 do() 在事件的生命周期中操作
         *     需求场景：
         *      在事件发送&接收的整个生命周期过程中进行操作
         *      如发送事件前的初始化，发送事件后的回调请求等
         *     做用：在某个事件的生命周期中调用
         *     类型：
         *      do()的操作符有很多个，具体如下
         *          1、doOnEach():当Observable每发送一次数据事件就会调用一次（包含onNext(),onError(),onComplete()）
         *          2、Next事件
         *              2.1:执行Next事件前调用：doOnNext()
         *              2.2:执行Next事件后调用：doAfterNext()
         *          3、发送事件完毕后调用：
         *              3.1:发送错误事件时：doOnError()
         *              3.2:正常发送事件完毕后：doOnCompleted()
         *              3.3:无论正常发送完毕/异常终止 doOnterminate()
         *              3.4:最后执行：doFinally()
         *          4、订阅相关：
         *              4.1:观察者订阅时调用：doOnSubscribe()
         *              4.2:观察者取消订阅时调用：doOnUnSubscribe()
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onError(new Throwable("发生错误了"));
//            }
//        })
//                //1、当Observable每发送一次数据事件就会调用一次
//                .doOnEach(new Consumer<Notification<Integer>>() {
//                    @Override
//                    public void accept(@NonNull Notification<Integer> integerNotification) throws Exception {
//                        System.out.println("doOnEach() -> value = " + integerNotification.getValue());
//                    }
//                })
//                //2、执行next事件前调用
//                .doOnNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(@NonNull Integer integer) throws Exception {
//                        System.out.println("doOnNext() -> " + integer);
//                    }
//                })
//                //3、执行Next事件后调用
//                .doAfterNext(new Consumer<Integer>() {
//                    @Override
//                    public void accept(@NonNull Integer integer) throws Exception {
//                        System.out.println("doAfterNext() -> " + integer);
//                    }
//                })
//                //4、Observable正常发送完毕事件后调用
//                .doOnComplete(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("doOnComplete()");
//                    }
//                })      //5、Observable发送错误事件时调用
//                .doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        System.out.println("doOnError() -> " + throwable.getMessage());
//                    }
//                })
//                //6、观察者订阅时调用
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(@NonNull Disposable disposable) throws Exception {
//                        System.out.println("doOnSubscribe()");
//                    }
//                })
//                //7、Observable发送事件完毕后调用，无论正常发送完毕/异常终止
//                .doAfterTerminate(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("doAfterterminate()");
//                    }
//                })
//                //8、最后执行
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("doFinally()");
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("do() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("do() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("do() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("do() -> onComplete()");
//                    }
//                });

        /**
         * <p>
         *     4.5 错误处理
         *     需求场景：发送事件过程中，遇到错误时的处理机制
         *     解决方案一：
         *      发送数据：
         *          1、发送一个特殊事件&正常终止：onErrorReturn()
         *          2、发送一个新的Observable：onErrorResumeNext(),onExceptionResumeNext()
         *     解决方案二：
         *          1、重试：retry()
         *          2、让Observable重新订阅：retryUntil()
         *          3、将错误传递给另外一个Observable来决定是否要重新订阅该Observable：retryWhen()
         * </p>
         */

        /**
         * <p>
         *     4.5.1 onErrorReturn()
         *     作用：遇到错误时，发送一个特殊事件&正常终止
         *          可捕获在它之前发生的异常
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Throwable("发生错误了"));
//                emitter.onNext(3);
//            }
//        }).onErrorReturn(new Function<Throwable, Integer>() {
//            @NonNull
//            @Override
//            public Integer apply(@NonNull Throwable throwable) throws Exception {
//                System.out.println("onErrorReturn() -> 异常捕获处理");
//                return 666;
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onErrorReturn() -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("onErrorReturn() -> onNext() value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onErrorReturn() -> onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onErrorReturn() -> onComplete()");
//            }
//        });

        /**
         *  <p>
         *      4.5.6 onErrorResumeNext()
         *      作用：遇到错误时，发送一个新的Observable
         *      备注：
         *          1、onErrorResumeNext()拦截的错误 = Throwable;若需拦截Exception,请用onExceptionResumeNext()
         *          2、若onErrorResumeNext()拦截的错误 = Exception,则会将错误传递给观察者的onError()方法
         *  </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Throwable("发生错误了"));
//            }
//        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
//            @NonNull
//            @Override
//            public ObservableSource<? extends Integer> apply(@NonNull Throwable throwable) throws Exception {
//                System.out.println("onErrorResumeNext() -> 异常拦截");
//                return Observable.just(11,22);//发送一个新的Observable
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onErrorResumeNext() -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("onErrorResumeNext() -> onNext() -> value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onErrorResumeNext() -> onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onErrorResumeNext() -> onComplete()");
//            }
//        });


        /**
         * <p>
         *     4.5.7 onExceptionResumeNext()
         *     作用：遇到错误时，发送一个新的Observable
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onError(new Exception("发生错误了"));
//            }
//        }).onExceptionResumeNext(new Observable<Integer>() {
//            @Override
//            protected void subscribeActual(Observer<? super Integer> observer) {
//                observer.onNext(11);
//                observer.onNext(22);
//                observer.onComplete();
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("onExceptionResumeNext() -> onNext() -> value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onExceptionResumeNext() -> onError()" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onExceptionResumeNext() -> onComplete()");
//            }
//        });

        /**
         * <p>
         *     4.5.8 retry()
         *     作用：重试，即当出现错误时，让被观察者重新发送数据
         *      1、接收到onError()时，重新订阅&发送事件
         *      2、Throwable和Exception都可以拦截
         *     类型：
         *      1、retry():若一直出现错误，则一直发送数据
         *      2、retry(long time):有重试次数限制
         *      <-- 3. retry（Predicate predicate） -->
         * // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
         * // 参数 = 判断逻辑
         *
         * <--  4. retry（new BiPredicate<Integer, Throwable>） -->
         * // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
         * // 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
         *
         * <-- 5. retry（long time,Predicate predicate） -->
         * // 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
         * // 参数 = 设置重试次数 & 判断逻辑
         * </p>
         */

       // <-- 1. retry（） -->
// 作用：出现错误时，让被观察者重新发送数据
// 注：若一直错误，则一直重新发送

//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                        e.onNext(1);
//                        e.onNext(2);
//                        e.onError(new Exception("发生错误了"));
//                        e.onNext(3);
//                    }
//                })
//                        .retry() // 遇到错误时，让被观察者重新发射数据（若一直错误，则一直重新发送
//                        .subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//                            @Override
//                            public void onNext(Integer value) {
//                                print("retry()","接收到了事件"+ value );
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                print("retry()","对Error事件作出响应");
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                print("retry()","对Complete事件作出响应");
//                            }
//                        });

        //<-- 2. retry（long time） -->
        // 作用：出现错误时，让被观察者重新发送数据（具备重试次数限制
// 参数 = 重试次数
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                .retry(3) // 设置重试次数 = 3次
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        print("retry(long time)","接收到了事件"+ value );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("retry(long time)","对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        print("retry(long time)","对Complete事件作出响应");
//                    }
//                });

        //<-- 3. retry（Predicate predicate） -->
        // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送& 持续遇到错误，则持续重试）
// 参数 = 判断逻辑
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                // 拦截错误后，判断是否需要重新发送请求
//                .retry(new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(@NonNull Throwable throwable) throws Exception {
//                        // 捕获异常
//                        print("retry（Predicate predicate）","retry错误: "+throwable.toString());
//
//                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                        //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                        return true;
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        print("retry（Predicate predicate）", "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("retry（Predicate predicate）","对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        print("retry（Predicate predicate）","对Complete事件作出响应");
//                    }
//                });
        //<--  4. retry（new BiPredicate<Integer, Throwable>） -->
// 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
// 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                        e.onNext(1);
//                        e.onNext(2);
//                        e.onError(new Exception("发生错误了"));
//                        e.onNext(3);
//                    }
//                })
//
//                        // 拦截错误后，判断是否需要重新发送请求
//                        .retry(new BiPredicate<Integer, Throwable>() {
//                            @Override
//                            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
//                                // 捕获异常
//                                print("retry（new BiPredicate<Integer, Throwable>）","异常错误 =  "+throwable.toString());
//
//                                // 获取当前重试次数
//                                print("retry（new BiPredicate<Integer, Throwable>）","当前重试次数 =  "+integer);
//
//                                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
//                                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
//                                return true;
//                            }
//                        })
//                        .subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//                            @Override
//                            public void onNext(Integer value) {
//                                print("retry（new BiPredicate<Integer, Throwable>）","接收到了事件"+ value  );
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                print("retry（new BiPredicate<Integer, Throwable>）","对Error事件作出响应");
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                print("retry（new BiPredicate<Integer, Throwable>）","对Complete事件作出响应");
//                            }
//                        });

//        <-- 5. retry（long time,Predicate predicate） -->
// 作用：出现错误后，判断是否需要重新发送数据（具备重试次数限制
// 参数 = 设置重试次数 & 判断逻辑
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                // 拦截错误后，判断是否需要重新发送请求
//                .retry(3, new Predicate<Throwable>() {
//                    @Override
//                    public boolean test(@NonNull Throwable throwable) throws Exception {
//                        // 捕获异常
//                        print("retry（long time,Predicate predicate）", "retry错误: "+throwable.toString());
//
//                        //返回false = 不重新重新发送数据 & 调用观察者的onError（）结束
//                        //返回true = 重新发送请求（最多重新发送3次）
//                        return true;
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        print("retry（long time,Predicate predicate）", "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("retry（long time,Predicate predicate）",  "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        print("retry（long time,Predicate predicate）", "对Complete事件作出响应");
//                    }
//                });

        /**
         * <p>
         *     4.5.9 retryUntil()
         *     作用：出现错误后，判断是否需要重新发送数据
         *      1、若需要重新发送 & 持续遇到错误，则持续重试
         *      2、作用类似于retry（Predicate predicate）
         * </p>
         */

        /**
         * <p>
         *     4.5.10 retryWhen()
         *     作用：遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
         *
         * </p>
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onError(new Exception("发生错误了"));
//                e.onNext(3);
//            }
//        })
//                // 遇到error事件才会回调
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//
//                    @Override
//                    public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
//                        // 参数Observable<Throwable>中的泛型 = 上游操作符抛出的异常，可通过该条件来判断异常的类型
//                        // 返回Observable<?> = 新的被观察者 Observable（任意类型）
//                        // 此处有两种情况：
//                        // 1. 若 新的被观察者 Observable发送的事件 = Error事件，那么 原始Observable则不重新发送事件：
//                        // 2. 若 新的被观察者 Observable发送的事件 = Next事件 ，那么原始的Observable则重新发送事件：
//                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
//
//                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
//                                // 该异常错误信息可在观察者中的onError（）中获得
//                                return Observable.error(new Throwable("retryWhen终止啦"));
//
//                                // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
////                                 return Observable.just(1);
//                            }
//                        });
//
//                    }
//                })
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    public void onNext(Integer value) {
//                        print("retryWhen()", "接收到了事件"+ value  );
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("retryWhen()", "对Error事件作出响应" + e.toString());
//                        // 获取异常错误信息
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        print("retryWhen()", "对Complete事件作出响应");
//                    }
//                });


        /**
         * <p>
         *     4.6 repeat()/repeat(Integer integer)
         *      repeat():无限次重复
         *      repeat(Integer integer):有限次重复
         * </p>
         */
//        Observable.just(1, 2, 3, 4)
//                .repeat(3) // 重复创建次数 =- 3次
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        print("repeat()", "开始采用subscribe连接");
//                    }
//
//                    @Override
//                    public void onNext(Integer value) {
//                        print("repeat()", "接收到了事件" + value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("repeat()",  "对Error事件作出响应");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        print("repeat()", "对Complete事件作出响应");
//                    }
//
//                });

        /**
         * <p>
         *     4.7 repeatWhen()有条件的 重复的发送被观察者
         * </p>
         */
        Observable.just(1,2,4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        // return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        // return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                       print("repeatWhen()", "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        print("repeatWhen()","接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("repeatWhen()","对Error事件作出响应：" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        print("repeatWhen()", "对Complete事件作出响应");
                    }

                });

        CountDownLatch countDownLatch = new CountDownLatch(20);
        countDownLatch.await();

    }

    public static void print(String tag,String content){
        System.out.println(tag + " -> " + content);
    }

}
