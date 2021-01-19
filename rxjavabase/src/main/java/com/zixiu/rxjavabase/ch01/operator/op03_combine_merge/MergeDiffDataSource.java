package com.zixiu.rxjavabase.ch01.operator.op03_combine_merge;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Snail
 * Time:  2021/1/19 3:43 PM
 * FileName:  MergeDiffDataSource
 * 简介：
 * <p>
 * 合并不同的数据源(如网络 + 本地) & 同时展示
 * </p>
 */
class MergeDiffDataSource {
    static String result = "数据源来自 = ";

    public static void main(String[] args) {
        //模拟网络数据源
        Observable<String> netObservable = Observable.just("网络数据").subscribeOn(Schedulers.io());

        //模拟本地数据源
        Observable<String> localObservable = Observable.just("本地数据").subscribeOn(Schedulers.newThread());

        //合并数据方式一：merge() -> Observable并行发送事件
//        Observable.merge(netObservable, localObservable)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(@NonNull String s) throws Exception {
//                        System.out.println("merge()方式合并数据 value = " + s);
//                        result = result + s;
//                        System.out.println(result);
//                    }
//                });


        //方式二：zip(),从不同的数据源获取数据，合并请求的发送，统一显示结果
        Observable.zip(netObservable, localObservable, new BiFunction<String, String, String>() {
            @NonNull
            @Override
            public String apply(@NonNull String s, @NonNull String s2) throws Exception {
                System.out.println("zip()合并数据：s = " + s + "\t s2 = " + s2);
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("zip()合并数据后的结果：" + s);
            }
        });


    }

}
