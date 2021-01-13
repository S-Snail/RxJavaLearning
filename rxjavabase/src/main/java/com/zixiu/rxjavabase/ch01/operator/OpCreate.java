package com.zixiu.rxjavabase.ch01.operator;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Author: Snail
 * Time:  2021/1/13 1:43 PM
 * FileName:  Op_create
 * 简介：创建类操作符
 * <p>
 * 1.基本创建：create()
 * 2.快速创建&发送事件：just(),fromArray(),fromIterable(),never(),empty(),error()
 * 3.延迟创建：defer(),timer(),interval(),intervalRange(),range(),rangeLong()
 * </p>
 */
public class OpCreate {

    public static class Data{
        private Integer i;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }
    }

    @SuppressLint("CheckResult")
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InterruptedException {
        /**
         * <p>
         *     1.1
         *     需求场景：完整的创建被观察者
         *     对应操作符：create()
         * </p>
         */
        //1.1.1 通过crate()创建被观察者Observable对象
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //1.1.2 在复写的subscribe方法里发送事件
                emitter.onNext("A");
                emitter.onComplete();
            }
        });
        //1.1.2 在具体使用时，一般采用链式调用来创建
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("B");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("create - onSubscribe()");
            }

            @Override
            public void onNext(String s) {
                System.out.println("create - onNext() " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("create - onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("create - onComplete()");
            }
        });

        /**
         * <p>
         *     1.2 快速创建 & 发送事件
         *     需求场景：快速创建被观察者
         *     对应的操作符如下
         * </p>
         */
        /**
         * 1.2.1 just()
         * 作用：快速创建被观察者
         * 发送事件特点：直接发送事件
         * 备注：最多只能发送10个参数
         */
        Observable.just(1, 2, 3, 4).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("just -> onSubscribe()");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("just -> onNext() -> value = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("just -> onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("just -> onComplete()");
            }
        });

        /**
         * 1.2.2 fromArray()
         * 作用：快速创建一个被观察者Observable
         * 发送事件特点：直接发送传入的数组数据
         * 应用场景：快速创建被观察者 & 发送10个以上事件（数组形式）
         *          数组元素遍历
         */
        //1.2.2.1 设置需要传入的数组
        Integer[] items = {0, 1, 2, 3, 4, 5, 6, 7};
        //如若直接传入一个list集合进去，则会把list当作一个元素发送
//        List<Integer> items = new ArrayList<>();
//        items.add(0);
//        items.add(1);
//        items.add(2);
//        items.add(3);
//        items.add(4);
//        items.add(5);
//        items.add(6);
//        items.add(7);

        //1.2.2.2 创建被观察者对象时传入数组，在创建后就会将该数组转换成Observable & 发送该对象中所有数据
        Observable.fromArray(items).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("fromArray -> onSubscribe()");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("fromArray -> onNext() -> value = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("fromArray -> onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete()");
            }
        });

        /**
         * 1.2.3 fromIterable()
         * 作用：快速创建一个被观察者
         * 发送事件特点：直接发送传入的集合List数据
         * 应用场景：快速创建被观察者对象 & 发送10个以上事件（集合形式）
         *          集合元素遍历
         */
        //快速发送集合
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        //通过fromIterable()将集合中的对象/数据发送出去
        Observable.fromIterable(list).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("fromIterable -> onSubscribe()");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("fromIterable -> onNext() -> value = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("fromIterable -> onError()");
            }

            @Override
            public void onComplete() {
                System.out.println("fromIterable -> onComplete()");
            }
        });

        /**
         * 1.2.4 额外:以下方法仅一般用于测试使用
         * empty(): 该方法创建的被观察者发送事件的特点：仅发送Complete事件，直接通知完成，观察者接收后会直接调用onComplete()
         * error():仅发送Error事件，观察者接收后会直接调用onError()（可自定义异常）
         * never():不发送任何事件，即观察者接收后什么都不调用
         */
        Observable<String> observable1 = Observable.empty();
        observable1.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("empty() -> onNext() -> value = " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                System.out.println("empty() -> onError()");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("empty() -> onCompte()");
            }
        });

        Observable<String> observable2 = Observable.error(new RuntimeException());
        observable2.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("error() -> onNext() -> s = " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                System.out.println("error() -> onError() " + (throwable instanceof RuntimeException));
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("error() -> onComplete()");
            }
        });

        Observable<String> observable3 = Observable.never();
        observable3.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("never() -> onNext()");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                System.out.println("never() -> onError()");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("never() -> onComplete()");
            }
        });

        /**
         * <p>
         *     1.3 延迟创建
         *     需求场景：
         *     定时操作，需要经过x秒后，自动执行y操作
         *     周期性操作，每隔x秒后，自动执行y操作
         * </p>
         */
        /**
         * 1.3.1 defer()
         * 作用：知道有观察者（Observer）订阅时，才动态创建被观察者（Observable）& 发送事件
         * 1、通过Observable工厂方法创建被观察者（Observable）
         * 2、每次订阅后都会得到一个最新的Observable对象，可以确保Observable对象里的数据是最新的
         * 应用场景：
         *  动态创建被观察者（Observable）& 获取最新的Observable对象数据
         */
        //1、第一次对i赋值
//        Integer i = 10;
//        Data data = new Data();
//        data.setI(i);
//        //2、通过defer()定义被观察者对象，注：此时被观察者对象还没有创建，要在产生订阅时才会创建
//        Observable<Integer> deferObservable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
//            @Override
//            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(data.getI());
//            }
//        });
//        //第二次对i赋值
//        i = 15;
//        data.setI(i);
//        deferObservable.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(@NonNull Integer integer) throws Exception {
//                System.out.println("defer() -> onNext -> value = " + integer);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(@NonNull Throwable throwable) throws Exception {
//                System.out.println("defer() -> onError()");
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                System.out.println("defer() -> onComplete()");
//            }
//        });

        /**
         * 1.3.2 timer()
         * 作用：
         *  1、快速创建一个被观察者对象（Observable）
         *  2、发送事件特点：延迟指定时间后，发送一个O（Long类型）
         *      本质 = 延迟指定时间后，调用一次onNext(o)
         * 应用场景：
         *      延迟指定事件，发送一个o,一般用于检测
         */
        //该事件，延迟x秒后，发送一个long事件
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("timer() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        System.out.println("timer() -> onNext() -> value = " + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("timer() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("timer() -> onComplete()");
//                    }
//                });
        /**
         * 1.3.3 interval()
         * 作用：
         *  1、快速创建一个被观察者对象
         *  2、每隔指定时间就发送事件（周期性的发送事件）
         *  发送的事件序列 = 从0开始，无限递增1的整数序列
         */
        //延迟3秒后，每隔1秒，产生一个数字,从0开始
//        Observable.interval(3,1,TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("interval -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        System.out.println("interval -> onNext() -> value = " + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("interval -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("interval -> onComplete()");
//                    }
//                });

        /**
         * 1.3.4 intervalRange()
         * 作用：
         *  1、快速创建一个被观察者（Observable）
         *  2、发送事件特点：每隔指定时间就发送事件，可指定发送数据的数量
         */
//        Observable.intervalRange(6,5,3,1,TimeUnit.SECONDS)
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("intervalRange() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        System.out.println("intervalRange() -> onNext() -> value = " + aLong);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("intervalRange() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("intervalRange() -> onComplete()");
//                    }
//                });

        /**
         * 1.3.5 range()
         * 作用：
         *  1、快速创建一个被观察者（Observable）
         *  2、发送事件特点：连续发送一个事件序列，可指定范围
         *
         *  a:发送的事件序列 = 从0开始，无限递增1的整数序列
         *  b:有点类似于intervalRange(),区别在于：无法延迟发送事件
         *
         */
//        Observable.range(6,5)
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        System.out.println("range() -> onSubscribe()");
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        System.out.println("range() -> onNext() -> value = " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println("range() -> onError()");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        System.out.println("range() -> onComplete()");
//                    }
//                });

        /**
         * 1.3.6 rangeLong()
         * 作用类似于range()
         */
        Observable.rangeLong(5,6)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("rangeLong() -> onSubscribe()");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("rangeLong() -> onNext() value = " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("rangeLong() -> onError()");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("rangeLong() -> onComplete()");
                    }
                });

//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        countDownLatch.await();

    }
}
