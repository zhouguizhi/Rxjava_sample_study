package com.zgz.rxjava;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zgz.rxjava.util.LogUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
/**
 * @Description: 线程切换
 * @Author: zhouguizhi
 * @CreateDate: 2021/1/19 上午11:03
 * @Version: 1.0
 */
public class ThreadActivity extends AppCompatActivity {
    private TextView tvMsg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_operator);
        tvMsg = findViewById(R.id.tv_msg);
    }
    /**
     * subscribeOn 这是让被观察者线程处于子线程,不管这个方法调用多少次,就执行一次,其他调用的都会被忽略
     * observeOn() 每次调用都会执行,这是针对观察者线程切换的
     * @param view
     */
    public void onThreadClickListener(View view) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> emitter.onNext(Thread.currentThread().getName()+"-线程发送数据")).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> new Handler().postDelayed(() -> tvMsg.setText("线程切换操作符222"),1000));
    }
    /**
     * 这个例子说明线程异步同步的关系
     * 2021-01-19 11:53:42.350 14860-14944/com.zgz.rxjava_sample_study E/LogUtil: 上游发送数据>>>>>1
     * 2021-01-19 11:53:42.350 14860-14944/com.zgz.rxjava_sample_study E/LogUtil: 上游发送数据>>>>>2
     * 2021-01-19 11:53:42.351 14860-14944/com.zgz.rxjava_sample_study E/LogUtil: 上游发送数据>>>>>3
     * 2021-01-19 11:53:42.351 14860-14860/com.zgz.rxjava_sample_study E/LogUtil: 下游接受:1
     * 2021-01-19 11:53:42.351 14860-14860/com.zgz.rxjava_sample_study E/LogUtil: 下游接受:2
     * 2021-01-19 11:53:42.351 14860-14860/com.zgz.rxjava_sample_study E/LogUtil: 下游接受:3
     * 上面是打印的结果
     */
    public void onTestThreadClickListener(View view) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            LogUtil.e("上游发送数据>>>>>"+1);
            emitter.onNext(1);
            LogUtil.e("上游发送数据>>>>>"+2);
            emitter.onNext(2);
            LogUtil.e("上游发送数据>>>>>"+3);
            emitter.onNext(3);
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> LogUtil.e("下游接受:"+integer));
    }
}
