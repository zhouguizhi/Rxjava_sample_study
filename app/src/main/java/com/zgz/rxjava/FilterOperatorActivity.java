package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FilterOperatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);
    }
    /**
     * distinct 过滤重复发射的数据
     * @param view
     */
    public void distinctOperator(View view) {
        Observable.just(2, 3, 4, 4, 2, 1)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.e("distinct:onSubscribe:");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        LogUtil.e("distinct:onNext:"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("distinct:onError:");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("distinct:onComplete:");
                    }
                });
    }

    /**
     * filter 根据条件过滤下数据后再发射给下游(观察者)
     * @param view
     */
    public void filterOperator(View view) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter(new Predicate<Integer>(){
                    @Override
                    public boolean test(Integer i) throws Throwable {
                        return i>3;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.e("filter:onSubscribe");
                    }

                    @Override
                    public void onNext(Integer o) {
                        LogUtil.e("filter:onNext:"+o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e("filter:onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("filter:onComplete");
                    }
                });
    }

    /**
     * elementAt 根据索引 过滤下其他数据发射给下游
     * @param view
     */
    public void elementAtOperator(View view) {
        //3是代表数组或者集合中的index = 3 所以打印出来的4
        Observable.just(1,2,3,4,5,6).elementAt(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        LogUtil.e("filter:onComplete:="+integer);
                    }
                });
    }

    /**
     * take take(long count)指定观察者最多能接收到的事件数量(从前面开始数)
     * @param view
     */
    public void takeOperator(View view) {
        Observable
                .just(1,2,3,4,5,6)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        LogUtil.e("take:onComplete:="+integer);
                    }
                });
    }

    public void testDebounceOperator(View view) {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("A");

            Thread.sleep(1_500);
            emitter.onNext("B");

            Thread.sleep(500);
            emitter.onNext("C");

            Thread.sleep(250);
            emitter.onNext("D");

            Thread.sleep(2_000);
            emitter.onNext("E");
            emitter.onComplete();
        });

        source.subscribeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS)
                .blockingSubscribe(
                        item -> System.out.println("onNext: " + item),
                        Throwable::printStackTrace,
                        () -> System.out.println("onComplete"));
    }
    /**
     * distinct 过滤操作符 作用是过滤掉重复的数据源
     * @param view
     */
    public void testDistinctOperator(View view) {
        Observable.just(2, 3, 4, 4, 2, 1)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.e("onSubscribe:");
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        LogUtil.e("onNext:>>>>>>"+integer);
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
     * distinctUntilChanged 过滤操作符 过滤掉连续重复的数据源
     * @param view
     */
    public void testDistinctUntilChangedOperator(View view) {
        Observable.just(1, 2, 1, 2, 3, 4,3)
                .distinctUntilChanged()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        LogUtil.e("accept:>>>>"+integer);
                    }
                });
    }
}
