package com.zixiu.rxjavabase.ch01.operator.op03_combine_merge;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Author: Snail
 * Time:  2021/1/14 3:36 PM
 * FileName:  OpCombineMerge
 * 简介：组合/合并操作符
 * 分类：
 * [1]组合多个被观察者：
 * 1、按发送顺序：concat(),concatArray()
 * 2、按时间：    merge(),mergeArray()
 * 3、错误处理：concatDelayError(),mergeDelayError()
 * [2]合并多个事件：
 * 1、按数量：    zip()
 * 2、按时间：    combineLatest(),combineLatestDelayError()
 * 3、合并成一个事件发送：reduce(),collect()
 * [3]发送事件前，追加发送事件：startWith(),startWithArray()
 * [4]统计发送事件数量：count()
 */
public class OpCombineMerge {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 3.1 组合多个被观察者：concat(),concatArray()
         * 作用：
         *      组合多个被观察者一起发送数据，合并后按照发送顺序串行执行
         * 二者区别：
         *       concat()组合的被观察者数量<=4个
         *       concatArray()组合的被观察者数量可>4个
         */
        //备注：串行执行
//        Observable.concat(
//                Observable.just(1, 2, 3),
//                Observable.just(4, 5, 6),
//                Observable.just(7, 8, 9)
//        ).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("concat() -> onNext() -> value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        //串行执行
//        Observable.concatArray(
//                Observable.just(1, 2),
//                Observable.just(3, 4),
//                Observable.just(5, 6),
//                Observable.just(7, 8),
//                Observable.just(9, 10)
//        )
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("concatArray() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        /**
         * merge()/mergeArray()
         * 作用：组合多个被观察者一起发送数据，合并后按时间线并行执行
         * 二者区别：组合被观察者数量
         *          merge()组合被观察者数量<=4
         *          mergeArray()组合被观察者数量 > 4
         * 区别上述concat()操作符：同样是组合多个被观察者一起发送数据，但concat()操作符合并后是按发送顺序串行执行
         */
//        Observable.merge(
//                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
//                Observable.intervalRange(3, 3, 1, 1, TimeUnit.SECONDS)
//        ).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                System.out.println("merge() -> onNext() -> value = " + aLong);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        countDownLatch.await();

        /**
         * concatDelayError()/mergeDelayError()
         * 作用：
         *      当其中一个被观察者触发onError(),则会马上终止其他被观察者继续发送事件，
         *      如果想要等所有的被观察者事件发送完毕再触发onError，则使用concatDelayError()/mergeDelayError()
         */
//        Observable.concatArrayDelayError(
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        emitter.onNext(0);
//                        emitter.onNext(1);
//                        emitter.onNext(2);
//                        emitter.onNext(3);
//                        emitter.onError(new RuntimeException());
//                        emitter.onComplete();
//                    }
//                }),
//                Observable.just(4,5,6)
//        ).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("concatArrayDelayError() -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                System.out.println("concatArrayDelayError() -> onNext()  -> value = " + integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("concatArrayDelayError() -> onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("concatArrayDelayError() -> onComplete()");
//            }
//        });
        /**
         * <p>
         *     3.2 合并多个事件
         *     该类型的操作符，主要是对多个被观察者发送的事件进行合并处理（按照发送顺序进行合并，合并后的事件数量与其中一个被观察者发送事件最少的事件数量相同）
         *     3.2.1 操作符：zip()
         *      作用：合并多个被观察者发送的事件，生成一个新的事件序列（即组合过后的事件序列），并最终发送
         *      特别注意：
         *          事件组合方式 = 严格按照事件发送序列，进行对位组合
         *          最终合并的事件数量 = 多个被观察者发送事件最少的数量
         * </p>
         */
//        Observable integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                System.out.println("被观察者 1 发送事件 1 ");
//                emitter.onNext(1);
//                Thread.sleep(1000);
//
//                System.out.println("被观察者 1 发送事件 2 ");
//                emitter.onNext(2);
//                Thread.sleep(1000);
//
//                System.out.println("被观察者 1 发送事件 3 ");
//                emitter.onNext(3);
//                Thread.sleep(1000);
//
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                System.out.println("被观察者 2 发送事件 A ");
//                emitter.onNext("A");
//                Thread.sleep(1000);
//
//                System.out.println("被观察者 2 发送事件 B ");
//                emitter.onNext("B");
//                Thread.sleep(1000);
//
//                System.out.println("被观察者 2 发送事件 C ");
//                emitter.onNext("C");
//                Thread.sleep(1000);
//
//                System.out.println("被观察者 2 发送事件 D ");
//                emitter.onNext("D");
//
//                emitter.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread());
//        //zip合并
//        Observable.zip(integerObservable, stringObservable, new BiFunction<Integer, String, String>() {
//            @NonNull
//            @Override
//            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
//                return integer + s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("zip -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(String result) {
//                System.out.println("zip -> onNext() -> value = " + result);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("zip -> onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("zip -> onComplete()");
//            }
//        });

        /**
         * <p>
         *     3.3 combineLatest()
         *     作用：
         *      当两个Observable中的任何一个发送数据后，将先发送数据的Observable的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
         *     与zip()的区别：zip() = 按个数合并，即1对1合并；CombineLatest() = 按时间合并，即在同一个时间点上合并
         * </p>
         */
        Observable.combineLatest(
                Observable.just(1L, 2L, 3L),//第一个发送事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
                new BiFunction<Long, Long, Long>() {
                    /**
                     *
                     * @param aLong {第一个Observable发送的最新的（最后的）一个数据}
                     * @param aLong2{第二个Observable发送的每一个数据}
                     */
                    @NonNull
                    @Override
                    public Long apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                        System.out.println("combineLatest() -> 合并的数据是：" + aLong + " - " + aLong2);
                        return aLong + aLong2;
                    }
                }
        ).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                System.out.println("合并的结果是:" + aLong);
            }
        });

        /**
         * <p>
         *     3.4 reduce()
         *     作用：把被观察者发送的事件，合成一个事件 & 发送
         *          聚合的逻辑根据需求来写，但本质是：将前2个数据聚合，然后与后一个数据聚合，以此类推
         * </p>
         */
//        Observable.just(1,2,3,4)
//                .reduce(new BiFunction<Integer, Integer, Integer>() {
//                    @NonNull
//                    @Override
//                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
//                        System.out.println("reduce() -> integer = " + integer + "\tinteger2 = " + integer2);
//                        return integer * integer2;
//                    }
//                }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(@NonNull Integer integer) throws Exception {
//                System.out.println("reduce() -> 最终聚合的结果：" + integer);
//            }
//        });

        /**
         * <p>
         *     3.5 collect()
         *     作用：将被观察者发送的数据收集到一个容器里
         * </p>
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .collect(
//                        new Callable<ArrayList<Integer>>() {
//                            @Override
//                            public ArrayList<Integer> call() throws Exception {
//                                return new ArrayList<Integer>();
//                            }
//                        },
//                        new BiConsumer<ArrayList<Integer>, Integer>() {
//                            @Override
//                            public void accept(@NonNull ArrayList<Integer> integers, @NonNull Integer integer) throws Exception {
//                                integers.add(integer);
//                            }
//                        }
//
//                ).subscribe(new Consumer<ArrayList<Integer>>() {
//            @Override
//            public void accept(@NonNull ArrayList<Integer> integers) throws Exception {
//                System.out.println("collect() -> 收集后的结果：" + integers.toString());
//            }
//        });

        /**
         * <p>
         *     3.6 发送事件前追加发送事件 - startWith()/startWithArray()
         * </p>
         */
        //在一个被观察者发送数据事件之前，追加发送一些数据
        //追加顺序：后调用的先追加
//        Observable.just(4,5,6)
//                .startWith(0)           //追加单个数据
//                .startWithArray(1,2,3)  //追加多个数据
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("startWith/startWithAttay() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("startWith/startWithAttay() -> onNext() -> value = " +integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("startWith/startWithAttay() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("startWith/startWithAttay() -> onComplete()");
//                    }
//                });

        //在一个被观察者发送数据之前，追加发送观察者 & 发送数据
        //追加顺序：后调用的先追加
//        Observable.just(4,5,6)
//                .startWith(Observable.just(1,2,3))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("startWith/startWithArray() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("startWith/startWithArray() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("startWith/startWithArray() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("startWith/startWithArray() -> onComplete()");
//                    }
//                });

        /**
         * <p>
         *     3.7 统计事件发送数量
         *     作用：统计被观察者发送事件的数量
         * </p>
         */
//        Observable.just(1,2,3,4)
//                .count()
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        System.out.println("count() -> 发送事件数量：" + aLong);
//                    }
//                });




        CountDownLatch countDownLatch = new CountDownLatch(20);
        countDownLatch.await();

    }

}
