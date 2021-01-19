package com.zixiu.rxjavabase.ch01.operator.op03_combine_merge;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Snail
 * Time:  2021/1/19 3:14 PM
 * FileName:  MemeryDiskCacheNetWorkDemo
 * 简介：
 * 利用RxJava实现从内存/磁盘缓存/网络数据
 * 备注：
 * <p>
 *     firstElement():从串联队列中取出并发送第一个有效事件（onNext()）
 * </p>
 */
public class MemeryDiskCacheNetWorkDemo {

    public static void main(String[] args) {
        String memoryCache = null;
        String diskCache = "从磁盘缓存中获取数据";

        //从memory获取数据
        Observable<String> memoryObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (memoryCache != null) {
                    emitter.onNext(memoryCache);
                } else {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io());

        //从disk获取数据
        Observable<String> diskObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (diskCache != null) {
                    emitter.onNext(diskCache);
                } else {
                    emitter.onComplete();
                }
            }
        }).subscribeOn(Schedulers.newThread());

        //从网络获取
        Observable<String> networkObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        }).subscribeOn(Schedulers.newThread());

        //开始获取数据
        Observable.concat(memoryObservable,diskObservable,networkObservable)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("模拟获取数据：value = " + s);
                    }
                });

    }

}
