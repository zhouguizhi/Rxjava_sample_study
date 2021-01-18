package com.zgz.rxjava;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zgz.rxjava.util.LogUtil;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void createOperator(View view) {
        startActivity(new Intent(this,CreateOperatorActivity.class));
    }

    public void filterOperator(View view) {
        startActivity(new Intent(this,FilterOperatorActivity.class));
    }

    public void transformOperator(View view) {
        startActivity(new Intent(this,TransformOperatorActivity.class));
    }

    /**
     * map变换操作符是把发送的数据经过中间变换后再发送到观察者
     * @param view
     */
    public void onMapClickListener(View view) {
        Observable.just(1).map(String::valueOf).subscribe(new Observer<String>() {
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
}