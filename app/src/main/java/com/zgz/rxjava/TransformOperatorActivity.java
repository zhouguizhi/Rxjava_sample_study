package com.zgz.rxjava;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;

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
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
//                        LogUtil.e("accept:integer>>>>>"+integer);
                    }
                });
    }
}
