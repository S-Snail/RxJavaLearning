package com.zixiu.rxjavabase.ch01.operator.op02_transform;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Author: Snail
 * Time:  2021/1/14 9:49 AM
 * FileName:  OpTransform
 * 简介：变换操作符
 * RxJava中常用的变换操作符如下：
 * Map(),FlatMap(),ConcatMap(),Buffer()
 *
 * 实际开发应用场景：
 *  变换操作符实际开发需求应用场景 = 嵌套回调
 */
public class Op02Transform {

    public static void main(String[] args) {
        /**
         * 2.1 Map()
         * 作用：对被观察者发送的每一个事件通过 指定函数 处理，从而变换成另一种事件
         * 应用场景：数据类型转换
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }).map(new Function<Integer, String>() {
//            @NonNull
//            @Override
//            public String apply(@NonNull Integer integer) throws Exception {
//                return "使用map()操作符，将事件" + integer + "的参数类型从整型 " + integer + "变换成字符串类型：" + integer;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("map() -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("map() -> onNext() -> value = " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("map() -> onError()");
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("map() -> onComplete()");
//            }
//        });

        /**
         * 2.2 FlatMap()
         * 作用：将被观察者发送的事件序列进行 拆分&单独转换，再合并成一个新的事件序列，最后再进行发送
         * 原理：
         *  1、为事件序列中的每一个对象都创建一个Observable
         *  2、将每个原始事件转换后的新事件都放到对应的Observable对象
         *  3、将新建的每一个Observable都合并到一个新建的，总的Observable对象
         *  4、新建的、总的Observable对象将新合并的事件序列发送给观察者Observer
         * 应用场景：
         *  无序的将被观察者发送的整个事件序列进行转换
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @NonNull
//            @Override
//            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
//                final List<String> newObservableList = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    newObservableList.add("我是事件" + integer + "拆分后的子事件" + i);
//                    //通过flatMap()中将被观察者发送的事件序列进行拆分，再将这个事件转换成一个新的发送三个String事件
//                    //最终合并，再发送给观察者
//                }
//                System.out.println("拆分后发送");
//                return Observable.fromIterable(newObservableList);
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("flatMap() -> onSubscribe()");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("flatMap() -> value = " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("flatMap() -> onComplete()");
//            }
//        });

        /**
         * 2.3 ConcatMap()
         * 作用：类似flatMap()操作符
         * 与flatMap()的区别在于：拆分&重新组合生成的新的事件的顺序 = 被观察者旧序列生产的顺序
         * 应用场景：
         *  有序的将被观察者发送的整个事件序列进行变换
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        }).concatMap(new Function<Integer, ObservableSource<String>>() {
//            @NonNull
//            @Override
//            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 0; i < 3; i++) {
//                    list.add("我是事件" + integer + "拆分出来的子事件" + i);
//                }
//                return Observable.fromIterable(list);
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("ConcactMap() -> onNext() -> value = " + s);
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

        /**
         * 2.4 buffer()
         * 作用：
         *  定期从被观察者（Observable）需要发送的事件中获取一定数量的事件 & 放到缓冲区，最终发送
         * 应用场景：
         *  缓存被观察者发送的事件
         */
        //Buffer()每次获取多少个事件放到缓冲区呢？下面通过一个例子来说明
        Observable.just(1,2,3,4,5)
                .buffer(3,1)//缓存区大小 & 步长
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        System.out.println("buffer() -> 缓存区里事件数量：" + integers.size());
                        for (int i = 0; i < integers.size(); i++) {
                            System.out.println("buffer() -> 事件value = " + integers.get(i));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("buffer() -> onError()");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
