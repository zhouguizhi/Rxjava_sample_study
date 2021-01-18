package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
/**
 * @Description: 变换操作符
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/18 下午2:13
 * @Version: 1.0
 */
public class TransformOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_operator);
    }
    /**
     * map变换操作符是把发送的数据经过中间变换后再发送到观察者
     * @param view
     */
    public void onMapClickListener(View view) {
        Observable
                .just(1)
                .map(String::valueOf).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.e("onSubscribe:");
            }
            @Override
            public void onNext(@NonNull String s) {
                LogUtil.e("onNext:s>>>>>>>>"+s);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.e("onError:");
            }
            @Override
            public void onComplete() {
                LogUtil.e("onComplete:");
            }
        });
    }
    /**
     * scan操作符:发送多个数据，并将这个函数的结果发射出去作为下个数据应用合格函数时的第一个参数使用
     * 下面的演示只是传入了int值,然后
     * @param view
     */
    public void onScanClickListener(View view) {
        Observable.just(5, 3, 8, 1, 7)
                .scan((integer, integer2) -> {
                   //integer:表示前二个参数和的值
                    //integer2 :表示传入第几个参数的值
                    return integer+integer2;
                })
                .subscribe(integer -> {
//                        LogUtil.e("accept:integer>>>>>"+integer);
                });
    }
    /**
     * groupBy:是将
     * @param view
     */
    public void onGroupByClickListener(View view) {
        Observable<Integer> observable = Observable.just(1,10,18,22,40,35,60);

        observable.groupBy(integer -> integer>=18?"成年人":"未成年人").subscribe(stringIntegerGroupedObservable -> stringIntegerGroupedObservable.subscribe(integer -> LogUtil.e(stringIntegerGroupedObservable.getKey()+":::"+integer)));
    }
    /**
     * flatMap：被观察者-->变换操作符ObservableSource</String>  再次发送--->观察者
     * 在这要分清楚map和flatMap  的区别
     * flatMap是不排序的
     */
    public void onFlatMapClickListener(View view) {
            Observable.just(1,2,3,4)
                    .flatMap((Function<Integer, ObservableSource<String>>) integer -> Observable.create((ObservableOnSubscribe<String>) emitter -> emitter.onNext(String.valueOf(integer)))).subscribe(s -> LogUtil.e("accept:s>>>>>"+s));
    }
    /**
     * concatMap:相比flatMap ,concatMap是排序的
     */
    public void onConcatMapClickListener(View view) {
        Observable.just("周桂枝","周杰伦","周润发")
                .concatMap((Function<String, ObservableSource<String>>) s -> {
                    List<String>  list = new ArrayList<>();
                    for(int i=0;i<3;i++){
                        list.add(s+"i:>>>>>"+(i+1));
                    }
                    return Observable.fromIterable(list).delay(5,TimeUnit.SECONDS);
                })
                .subscribe(s -> LogUtil.e("accept:s>>>>>"+s));
    }

    /**
     * buffer是缓存的意思,不要一次性全部发送,而是多少个一起发
     * @param view
     */
    public void onBufferClickListener(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i=0;i<30;i++){
                    emitter.onNext(i);
                }
            }
        })
                .buffer(5)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }
                    @Override
                    public void onNext(@NonNull List<Integer> integers) {
                        LogUtil.e("---------------");
                        for(Integer i:integers){
                            LogUtil.e("i:="+i);
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }
}
